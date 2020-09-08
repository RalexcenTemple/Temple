#include <stdio.h>
#include <stdlib.h>
#include <string.h>

#define CPU_FIN 1
#define DISK1_FIN 2
#define DISK2_FIN 3
#define NET_FIN 4
#define PROC_FIN = 5

int SEED,INIT_TIME,FIN_TIME,ARRIVE_MIN,ARRIVE_MAX,QUIT_PROB,NETWORK_PROB,CPU_MIN,CPU_MAX,DISK1_MIN,DISK1_MAX,DISK2_MIN,DISK2_MAX,NETWORK_MIN,NETWORK_MAX;
int jobCount = 0;

typedef struct job{
	char* name;
	int interval;
	struct job* next;
}Job;

Job* newJob(char* NAME, int INTVAL){
	Job* temp = (Job*)malloc(sizeof(Job));
	temp->name = NAME;
	temp->interval = INTVAL;
	temp->next = NULL;
	return temp;
}

Job* pop(Job** head){
	Job* temp = *head;
	*head = (*head)->next;
	return temp;
}

int isEmpty(Job** head){
	if(head){
		return 0;//not empty
	}else{
		return 1;//empty
	}	
}

void pushJob(Job** head, Job* temp){
	temp->next = *head;
	*head = temp;
}

void pushNewJob(Job** head, char* NAME, int INTVAL){
	Job* temp = newJob(NAME, INTVAL);
	temp->next = *head;
	*head = temp;
}

void pushJobPri(Job** head, Job* temp){
	Job* check = *head;
	if(check->interval > temp->interval){
		temp->next = *head;
		*head = temp;
	}else{
		while((check->next->interval < temp->interval)&&(check->next != NULL)){
			check = check->next;
		}
		temp->next = check->next;
		check->next = temp;
	}
}

Job* jobGen(int min, int max){
	char *job = (char*)malloc(sizeof(char)*32);
	char *jobCnt = (char*)malloc(sizeof(char)*32);
	strcat(job,"Job");
	sprintf(jobCnt,"%d",jobCount);
	strcat(job ,jobCnt);
	int runtime = ((rand()%(max-min))+(min));
	Job* temp = newJob(job,runtime);
	return temp;
}

Job* jobGenwTime(int min, int max, int time){
        char *job = (char*)malloc(sizeof(char)*32);
        char *jobCnt = (char*)malloc(sizeof(char)*32);
        strcat(job,"Job");
        sprintf(jobCnt,"%d",jobCount);
        strcat(job ,jobCnt);
        int runtime = ((rand()%(max-min))+(min))+time;
        Job* temp = newJob(job,runtime);
        return temp;
}




void printJob(Job* proc){
	printf("JOB NAME: %s | JOB INTERVAL: %d\n",proc->name,proc->interval);
}

void printQueue(Job** head){
	Job* temp = *head;
	while(temp != NULL){
		printJob(temp);
		temp = temp->next;
	}
}

int getQLen(Job** head){
	Job* temp = *head;
	int cnt = 0;
	while(temp->next != NULL){
		cnt++;
	}
	return cnt;
}

Job* setNewInterval(Job* proc, int min, int max, int time){
	int newInterval = ((rand()%(max-min))+min)+time;
	proc->interval = newInterval;
	return proc;
}


void eventWrite(FILE log, int event, Job* proc){
	
}

int main(int args,char** argv){
	FILE *conf = fopen("config.txt", "r");
        FILE *log  = fopen("log.txt", "w");
	
	char* buff;
	size_t buffsize = 64;
	size_t chars;

	int attr[15];


	buff = (char*)malloc(buffsize * sizeof(char));

	if(buff == NULL){
		return(-2);//failed allocation
	}

	int pos = 0;
	while(((chars = getline(&buff,&buffsize,conf)) != -1) && (pos < 15)){
		char temp[64];
		int i = 13;
		int j = 0;
		while((&buff[i] != "\n") && (i < chars)){
			temp[j] = buff[i];
			i++;
			j++;
		}
		attr[pos] = atoi(temp);
		fprintf(log,"CONFIG POSITION: %d | CONFIG VALUE: %d\n",pos,attr[pos]);
		pos++;
	}

	SEED 		= attr[0];
        INIT_TIME 	= attr[1];
        FIN_TIME 	= attr[2];
        ARRIVE_MIN 	= attr[3];
        ARRIVE_MAX 	= attr[4];
        QUIT_PROB 	= attr[5];
        NETWORK_PROB 	= attr[6];
        CPU_MIN 	= attr[7];
        CPU_MAX 	= attr[8];
        DISK1_MIN 	= attr[9];
        DISK1_MAX 	= attr[10];
        DISK2_MIN 	= attr[11];
        DISK2_MAX 	= attr[12];
        NETWORK_MIN 	= attr[13];
        NETWORK_MAX 	= attr[14];
	
	//use seed in generation of random numbers
        srand((unsigned)SEED);

	//create priority queue for jobs entering the system
	//get the time the first job will will take, also representing the priority in the event queue
	//and add it to the queue so the simulation has something to start with
	Job* EVENTS 	= newJob("simulation finished",100000);
	pushJobPri(&EVENTS, newJob("JOB",0));


	//create space where the 'running' job will be held
	Job* CPU 	= NULL;
	Job* DISK1 	= NULL;
	Job* DISK2 	= NULL;
	Job* NETWORK 	= NULL;
	int cpuInterval, disk1Interval, disk2Interval,networkInterval;

	//create queues for waiting jobs for component
	Job* cpuWait 	= NULL;
	Job* disk1Wait 	= NULL;
	Job* disk2Wait 	= NULL;
	Job* networkWait = NULL;

        int time = INIT_TIME;
	FIN_TIME = 100;
	while(time < FIN_TIME){
		//make sure there is an event waiting to be processed
		pushJobPri(&EVENTS, jobGenwTime(ARRIVE_MIN,ARRIVE_MAX,time));
		
		//handle if an event is to in the cpu yet
		if(EVENTS->interval == time){
			if(CPU == NULL && cpuWait == NULL){
				pushJob(&CPU, setNewInterval(pop(&EVENTS),CPU_MIN,CPU_MAX,time));
			}else if(CPU != NULL){//could really just be and else statement
				pushJob(&cpuWait, setNewInterval(pop(&EVENTS),CPU_MIN,CPU_MAX,CPU->interval));
			}
		}

		if(CPU->interval == time){
			if(DISK1 == NULL){
				pushJob(&DISK1, setNewInterval(pop(&CPU),DISK1_MIN,DISK1_MAX,time));
			}else if(DISK2 == NULL){
				pushJob(&DISK2, setNewInterval(pop(&CPU),DISK2_MIN,DISK2_MAX,time));
			}else if((getQLen(&disk1Wait)) < (getQLen(&disk2Wait))){
				pushJob(&disk1Wait, setNewInterval(pop(&CPU),DISK1_MIN,DISK1_MAX,DISK1->interval));
			}else if((getQLen(&disk1Wait)) > (getQLen(&disk2Wait))){
				pushJob(&disk2Wait, setNewInterval(pop(&CPU),DISK2_MIN,DISK2_MAX,DISK2->interval));
			}else{
				int flip = (rand()%2);
				if(flip){
					
				}else{

				}
			}
		}		
		time++;
	}
	printQueue(&EVENTS);
	printf("-------------------\n");
	printJob(CPU);
	printQueue(&cpuWait);

}
