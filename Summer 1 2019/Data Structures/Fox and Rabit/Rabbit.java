public class Rabbit extends Animal {


    public Rabbit(Model model, int row, int column) {
        super(model, row, column);
    }
    
    int decideMove() {
    	int[][] objects = new int[8][3];
    	for(int i = Model.MIN_DIRECTION ;i < Model.STAY;i++ ) {
    		int obj = look(i);
    		int dis = distance(i);
    		int dir = i;
    		objects[i][0] = obj;
    		objects[i][1] = dis;
    		objects[i][2] = dir;//obviously got what the columns were representing mixed up
    		//rayToString(objects[i]);
    	}
    	//System.out.println("objects array size: " + objects.length);
    	if(wolfDetect(objects)) {
    		return runAway(objects);
    	}else {
    		//System.out.println("No Wolf Detected");
    		return findCenter(objects);
    	}
    	
    }
    
    int findCenter(int[][] view) {
    	int buffer = 10;
    	if(view[0][1] > view[4][1] && (view[0][1]-view[4][1] > buffer)) {
    		//System.out.println("      Finding Center of Area");
    		return 0;
    	}else if ((view[0][1] < view[4][1]) && (view[4][1]-view[0][1] > buffer)){
    		//System.out.println("      Finding Center of Area");
    		return 4;
    	}else if ((view[2][1] > view[6][1]) && (view[2][1]-view[6][1] > buffer)){
    		//System.out.println("      Finding Center of Area");
    		return 2;
    	}else if ((view[2][1] < view[6][1]) && (view[6][1]-view[2][1] > buffer)){
    		//System.out.println("      Finding Center of Area");
    		return 6;
    	}else {
    		//System.out.println("      Staying Put");
    		return 8;
    	}
    	
    	
    	/*
    	int rand = random(0,7);
    	if((view[rand][1] < view[((rand+4)%8)][1]) && (!wallDetectLite(view[((rand+4)%8)]))) {
    		return ((rand+4)%8);
    	}else if((view[rand][1] > view[((rand+4)%8)][1]) && (!wallDetectLite(view[rand]))){
    		return rand;
    	}else {
    		return findCenter(view);
    	}
    	*/
    }
    
    boolean wallDetect(int[] ray) {
    	if((ray[0] == 0 || ray[0] == 3) && (ray[1]==1 || ray[1]==2)) {
    		return true;
    	}
    	else {
    		return false;
    	}
    }
    
    boolean wallDetectLite(int[] ray) {
    	if((ray[0] == 0 || ray[0] == 3) && (ray[1]==1)) {
    		return true;
    	}
    	else {
    		return false;
    	}
    }
    
    int viewCheck(int[][] view) {
    	int weight = 0;
    	for(int[] i : view) {
    		weight += (20-i[1])*i[0];
    	}
    	return weight;
    }
    int viewCheck(int[] ray) {
    	int weight = 0;
    	weight += (20-ray[1])*ray[0];
    	return weight;
    }
    
    boolean bushCheck(int[] ray) {
    	if(ray[0] == 3) {
    		return true;
    	}else {
    		return false;
    	}
    }
    
    int runAway(int[][] view) {
    	//System.out.println("Try to Run Away");
    	int wolfDir = wolfSee(view);
    	if(wolfDir < 0) { // dont move if the wolf isnt seen this time for some reason
    		//System.out.println("Boy Cired Wolf");
    		return 8; 
    	}else {			// start moving away fro mthe wolf 
    		int wolfDis = wolfSeeFar(view); //wolf distance from rabbit
    		if (wolfDis < 3) {
    			if((wolfDis == 3)&& (bushCheck(view[(8+wolfDir-1)%8]))){ //positioning should be right to move behind a bush out of view of the wolf
    				//System.out.println("Hide Behind the bush!");
    				return ((wolfDir+6)%8);
    			}
    			if((wolfDis == 3)&& (bushCheck(view[(wolfDir+1)]))){//positioning should be right to move behind a bush out of view of the wolf
    				//System.out.println("Hide Behind the bush!");
    				return ((wolfDir+2)%8);
    			}
    			//System.out.println("The wolf is close!");
    			int rand = random(0,100);
    			if(rand%2 == 0) {
    				for(int i = 3; i <6; i++) { //checks the three opposite facing directions for walls and moves if there isnt much in the way
        				if(!wallDetect(view[((wolfDir+i)%8)])) {
        					//System.out.println("Get Away Quickly!");
        					return view[((wolfDir+i)%8)][2];
        				}
        			}
        			for(int i = 3; i <6; i++) { //checks the three opposite facing directions for walls and moves if it isnt up against the walls
        				if(!wallDetectLite(view[((wolfDir+i)%8)])) {
        					//System.out.println("Get Away VERY Quickly!!!!");
        					return view[((wolfDir+i)%8)][2];
        				}
        			}
    			}else {
    				for(int i = 5; i > 2; i--) { //checks the three opposite facing directions for walls and moves if there isnt much in the way
        				if(!wallDetect(view[((wolfDir+i)%8)])) {
        					//System.out.println("Get Away Quickly!");
        					return view[((wolfDir+i)%8)][2];
        				}
        			}
        			for(int i = 5; i >2; i--) { //checks the three opposite facing directions for walls and moves if it isnt up against the walls
        				if(!wallDetectLite(view[((wolfDir+i)%8)])) {
        					//System.out.println("Get Away VERY Quickly!!!!");
        					return view[((wolfDir+i)%8)][2];
        				}
        			}
    			}
    				
    		}
    		//System.out.println("Wolf is coming from the " + wolfDir + " Direction"); //describe the direction of the wolf
    		int[][] temp1 = new int[][]{view[((8+wolfDir)-1)%8],view[(8+wolfDir-2)%8],view[(8+wolfDir-3)%8]};	//create a list of the info on the objects found to the left if facing the wolf
    		int[][] temp2 = new int[][]{view[(wolfDir+1)%8],view[(wolfDir+2)%8],view[(wolfDir+3)%8]};		//create a list of the info on the objects found to the right if facing the wolf
    		//System.out.println("The left is: " + viewCheck(temp1)); //somewhat calculate the safety of the area to the left
    		//System.out.println("The Right is: " + viewCheck(temp2)); //somewhat calculate the safety of the area to the Right
    		if(viewCheck(temp1) > viewCheck(temp2)) { //check for walls on the best looking side and look to the other if walls are in the way
    			for(int i = 2; i >= 0; i--) {
    				if(!wallDetect(temp1[i])) {
    					//System.out.println("Running to the Left");
    					return temp1[i][2];
    				}
    			}
    			for(int i = 2; i >= 0; i--) {
    				if(!wallDetectLite(temp2[i])) {
    					//System.out.println("Running to the Right since the Left was no good");
    					return temp2[i][2];
    				}
    			}
    		}else {
    			for(int i = 2; i >= 0; i--) {
    				if(!wallDetect(temp2[i])) {
    					//System.out.println("Running to the Right");
    					return temp2[i][2];
    				}
    			}
    			for(int i = 2; i >= 0; i--) {
    				if(!wallDetectLite(temp1[i])) {
    					//System.out.println("Running to the Left Since the Right was no good");
    					return temp1[i][2];
    				}
    			}
    		}
    		//System.out.println("Running Directly Away");
    		return ((wolfDir+4)%8);
    	}
    }
    
    int wolfSeeFar(int[][] view) {
    	for(int[] i: view) {
    		if(i[0] == 1) {
    			return i[1];
    		}
    	}
    	return -1;
    }
    
    void rayToString(int[] ray) {
    	System.out.println("***********Direction of Object: " + ray[2]);
    	System.out.println("Object found: " + ray[0]);
    	System.out.println("Distance of Object: " + ray[1]);
    }
   
    boolean wolfDetect(int[][] view){
    	for(int[] i: view) {
    		if(i[0] == 1) {
    			//System.out.println("Wolf has been detected");
    			return true;
    		}
    	}
    	return false;
    }
    
    int wolfSee(int[][] view) {
    	for(int[] i: view) {
    		if(i[0] == 1) {
    			return i[2];
    		}
    	}
    	return -1;
    }

}
