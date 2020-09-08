#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <pthread.h>  
#include <sys/time.h> 
#include <signal.h>


#define N_GENS 3
#define N_HANDS 4 

//the maximum number of threads.
#define MAX_SIG 1000


int total = 0;  //counter of total signals
int sig1 = 0;   //	of SIGUSR1
int sig2 = 0;   //	of SIGUSR2
int s1rcnt = 0; //	of received SIGUSR1
int s2rcnt = 0; //	of received SIGUSR2

pthread_t genTA[N_GENS]; 	//gen thread array
pthread_t handTA[N_HANDS]; 	//hand thread Array
pthread_t reportT; 		//the reporter thread.


void mkThreads(int i); 		//create threads
void clrThreads(int i); 	//clear threads
void *genThreads(void * p); 	//generation threads
void *hand1Threads(void * p); 	//SIGUSR1 processes
void *hand2Threads(void * p); 	//SIGUSR2 processes
void *reportThread(void * p); 	//reporter function


pthread_mutex_t s1s; 	//SIGUSR1 Sent
pthread_mutex_t s2s; 	//SIGUSR2 Sent
pthread_mutex_t totals; //Total Sent
pthread_mutex_t s1r; 	//SIGUSR1 Recieved
pthread_mutex_t s2r; 	//SIGUSR2 Recieved
pthread_mutex_t totalr; //Total Recieved


int main(void){

	srand(time(NULL));
	struct timeval start;
	struct timeval end;
	gettimeofday(&start, NULL);//start time
	int i;
  	//init threads
  	mkThreads(i);
  	//clear threads when complete
  	clrThreads(i);
  
  	gettimeofday(&end, NULL);//end time

  	//print the total number of signals (total, 1, and 2)
  	printf("Total: %d\n", total);
  	printf("Total SIGUSR1: %d\n", sig1);
  	printf("Total SIGUSR2: %d\n", sig2); 
	

  	//total time
	int s = (1000000 * (end.tv_sec - start.tv_sec));
	int u = (end.tv_usec - start.tv_usec);
  	double time = (double) (s + u);
	double s1Avg = time/sig1;
	double s2Avg = time/sig2;
	printf("Average SIGUSR1 time: %f \n", s1Avg/1000000);
	printf("Average SIGUSR2 time: %f \n", s2Avg/1000000);
  	printf("Total Time:( %f ) seconds\n", (time/1000000));
	printf("Total Missed : %d \n", ((sig1 - s1rcnt)+(sig2 - s2rcnt)));
  	printf("\n");
  	return EXIT_SUCCESS;
}

void clrThreads(int i){
	//CLEAR GEN THREADS
	for(i = 0; i <N_GENS;i++){
    		pthread_join(genTA[i], NULL);
  	}

  	//CLEAR HAND THREADS
  	for(i = 0; i< N_HANDS; i++){
    		pthread_cancel(handTA[i]);
    		pthread_join(handTA[i], NULL);
  	}

  	//CLEAR REPORTER THREAD
  	pthread_join(reportT, NULL);
}

void mkThreads(int i){
	//REPORTER THREAD
  	if(pthread_create(&reportT, NULL, reportThread, NULL)) {
    		printf("error creating reporter\n");
    		exit(EXIT_FAILURE);
  	}

  	//HAND THREADS
  	for(i = 0; i<(N_HANDS); i++){
    		if(i<(N_HANDS/2)){
      			if(pthread_create(&handTA[i], NULL, hand1Threads, NULL)) {
        			printf("error for hands at i = %d\n", i);
        			exit(EXIT_FAILURE);
      			}
    		}else{
      			if(pthread_create(&handTA[i], NULL, hand2Threads, NULL)) {
        			printf("error for hands at i = %d\n", i);
        			exit(EXIT_FAILURE);
      			}
    		}
  	}

  	//GEN THREADS
  	for(i = 0; i < N_GENS; i++){
    		if(pthread_create(&genTA[i], NULL, genThreads, NULL)) {
      			printf("error creating gen threads at i = %d \n", i);
      			exit(EXIT_FAILURE);
    		}
  	}
}

void * genThreads(void * p){
	sleep(1);
  	while(total < MAX_SIG) {//for the max number of signals
    		int r = rand() % 2; //get a random number with which we determine SIGUSR1 or SIGUSR2
    		if(r == 0) {
      			//if r = 0 SIGUSR1 is sent to the SIGUSR1 handler threads and reporter
      			pthread_kill(handTA[r], SIGUSR1);
      			pthread_kill(handTA[r+1], SIGUSR1);
      			pthread_kill(reportT, SIGUSR1);

      			//lock shared data from other threads
      			pthread_mutex_lock(&s1s);
      			sig1 += 2; //increment for both signals
      			pthread_mutex_unlock(&s1s);

    		}else{
      			r = r + 1;
      			//if r = 1 we send SIGUSR2 to the SIGUSR2 handler threads and reporter
      			pthread_kill(handTA[r], SIGUSR2);
      			pthread_kill(handTA[r+1], SIGUSR2);
      			pthread_kill(reportT, SIGUSR2);      

      			//lock shared data from other threads
      			pthread_mutex_lock(&s2s);
      			sig2 += 2;//increment for both signals
      			pthread_mutex_unlock(&s2s);
      
    		}
    		pthread_mutex_lock(&totals);
    		total += 2;//plus 2 for the 2 threads created for either sig type
    		pthread_mutex_unlock(&totals);

    		//get random number 1-10
    		double rtime = (double)((rand() % 10) + 1);//"random time delay of between .01 and .1 second before moving to the next repetition"
    		//printf("RTIME: %f \n", rtime);
		rtime = rtime * 10000;
		//printf("RTIME: %f \n", rtime);
    		usleep((int)(rtime));//usleep to then convert to a number between 0.1 and 0.01
  	}

  	// end the reporter now that there's no more processes to report of
  	pthread_cancel(reportT);
  	pthread_exit(NULL);
  	return NULL;
}


//functions to handle the threads from the SIGUSR1 and SIGUSR2 handler Thread Arrays
void * hand1Threads(void * p){
	//create signals for hand1 not to block: SIGUSR1
  
  	//create and clear a set to hold the signals
  	sigset_t sigset;
  	sigemptyset(&sigset);

  	//add both signals to the set to catch both
  	sigaddset(&sigset, SIGUSR1);
	
  	//make a mask for the set
  	pthread_sigmask(SIG_BLOCK, &sigset, NULL);

  	int temp;
  	while(1){
    		temp = sigwaitinfo(&sigset, NULL);
    		if(temp == -1){
      			break;//break if failed
    		}else{
      			//increment on recieved signal
      			pthread_mutex_lock(&s1r);
      			s1rcnt++;
      			pthread_mutex_unlock(&s1r);
    		}
  	}

  	pthread_exit(NULL);
  	return NULL;
}

void * hand2Threads(void * p) {
  	//create signals for hand2 not to block: SIGUSR2
  
  	//create and clear a set to hold the signals
  	sigset_t sigset;
  	sigemptyset(&sigset);
  
  	//add both signals to the set to catch both
  	sigaddset(&sigset, SIGUSR2);

  	//make a mask for the set
  	pthread_sigmask(SIG_BLOCK, &sigset, NULL);

  	int temp;
  	while(1){
		temp = sigwaitinfo(&sigset, NULL);
    		if(temp == -1)
      			break;
    		else {
      			pthread_mutex_lock(&s2r);
      			s2rcnt++;
      			pthread_mutex_unlock(&s2r);
    		}
  	}

  	pthread_exit(NULL);
  	return NULL;
}


void * reportThread(void * p){
	//create signals for reporter not to block: SIGUSR1, SIGUSR2
  
  	//create and clear a set to hold the signals
  	sigset_t sigset;
  	sigemptyset(&sigset);

  	//add both signals to the set to catch both
  	sigaddset(&sigset, SIGUSR1);
  	sigaddset(&sigset, SIGUSR2);

  	//make a mask for the set
  	pthread_sigmask(SIG_BLOCK, &sigset, NULL);

  	//prepare timeval structs to hold recieving time
  	struct timeval s1re;
  	struct timeval s2re;
  	int cnt;
  	int temp; //sigwaitinfo
  	int ls1cnt; //counter for SIGUSR1
  	int ls2cnt; //counter for SIGUSR2

  	//append data to a file for logging the events
  	FILE * fp = fopen("log.csv", "a");
	fprintf(fp, "i,SIGUSR1 Count,SIGUSR2 Count, SIGUSR1 Average Time, SIGUSR2 Average Time\n");
  	int i = 1; //iterate
  	while(1){
    		//contain times of initial messages
    		int s1sec = 0;
    		int s1usec = 0;
    		int s2sec = 0;
    		int s2usec = 0;
    		int s1totusec = 0;//total times
    		int s2totusec = 0;

    		//10 signal intervals
    		while(cnt < 10){
      
      			temp = sigwaitinfo(&sigset, NULL);
      			if(temp == -1){
	 			break;//break on fail
      			}

      			if(temp == SIGUSR1){
				//get time when SIGUSR1 is recieved
				gettimeofday(&s1re, NULL);
				int s = (int) s1re.tv_sec;
				int us = (int) s1re.tv_usec;

				//on first signal save the time
				if(s1sec == 0) {
					//get current time
	  				s1sec = s1re.tv_sec;
	  				s1usec = s1re.tv_usec;
				}else {
	  				//get ellapsed time
	  				s1totusec += (1000000 * (s - s1sec)) + (us - s1usec);
	  				s1usec = us;
				}

				//increment total number of SIGUSR1
				ls1cnt++;
      			}

      			if(temp == SIGUSR2){
				//get time when SIGUSR2 is recieved
				gettimeofday(&s2re, NULL);
				int s = (int) s2re.tv_sec;
				int us = (int) s2re.tv_usec;

				//on first signal save the time
				if(s2sec == 0) {
					//get current time
	  				s2sec = s2re.tv_sec;
	  				s2usec = s2re.tv_usec;
				}else {
	  				//get ellapsed time
	  				s2totusec += (1000000 * (s - s2sec)) + (us - s2usec);
	  				s2usec = us;
				}

				//increment total number of SIGUSR2
				ls2cnt++;
      			}

      			cnt++;
    		}

    		double avgs1t = 0.0;
    		double avgs2t = 0.0;    
		
		
    		if(ls1cnt){ 
      			avgs1t = (s1totusec / ls1cnt);
    		}
    		if(ls2cnt){ 
      			avgs2t = (s2totusec / ls2cnt);
    		}
		//the file is being written to by a single reporting thread so a lock is not needed
    		printf("Average SIGUSR1 time: %f seconds \n", (avgs1t / 1000000));
    		printf("Average SIGUSR2 time: %f seconds \n", (avgs2t / 1000000));
    		fprintf(fp, "%d, %d, %d, %f, %f \n", i, ls1cnt, ls2cnt, avgs1t, avgs2t);
    		cnt = 0;
    		ls1cnt = 0;
    		ls2cnt = 0;
    		i++;
  	}
}
