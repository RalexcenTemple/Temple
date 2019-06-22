public class HoneyDoList {
	private Task[] tasks;
	private int numTasks;
	private int INITIAL_CAPACITY;
	
	//full init
	public HoneyDoList(Task[] TASKS, int NUMTASKS, int INIT_CAP) {
		tasks = TASKS;
		numTasks = NUMTASKS;
		INITIAL_CAPACITY = INIT_CAP;
	}
	//assumed init
	public HoneyDoList(int INIT_CAP) {
		tasks = new Task[INIT_CAP];
		numTasks = 0;
		INITIAL_CAPACITY = INIT_CAP;
	}
	
	public String toString() {
		String all = "";
		if(this.tasks.length > 0) {
		for(int i = 0; i < this.numTasks;i++) {
			if(!(this.tasks[i].getName() == (null))){
				all = all + this.tasks[i].toString() + "\n";
				}
			}
		}
		return all;
	}
	
	public int find(String name) {
		for(int i = 0; i < this.numTasks;i++) {
			//System.out.println(this.tasks[i].getName());
			if((this.tasks[i].getName() == (name))){
				return i;
			}
		}
		return -1;
	}
	
	//you never specified if this has to be capable of adding a task with value
	public void addTask() {
		if(this.numTasks == this.INITIAL_CAPACITY) {
			HoneyDoList temp = new HoneyDoList(this.INITIAL_CAPACITY+1);
			for(int i = 0;i < temp.INITIAL_CAPACITY;i++) {
				if(this.numTasks < i) {
					temp.tasks[i] = this.tasks[i];
				}else {
					temp.tasks[i] = new Task();
				}
			}
			temp.numTasks = this.numTasks + 1;
			this.tasks = temp.tasks;
			this.INITIAL_CAPACITY = temp.INITIAL_CAPACITY;
			this.numTasks = temp.numTasks;
		}else {
			//you already have the null task in the array, just add 1 to the number of tasks
			this.numTasks++;
		}
	}
	//assuming you wanted this to be part of the functionality as well
	public void addTask(Task add) {
		if(this.numTasks == this.INITIAL_CAPACITY) {
			HoneyDoList temp = new HoneyDoList(this.INITIAL_CAPACITY+1);
			for(int i = 0;i < temp.INITIAL_CAPACITY;i++) {
				if(this.numTasks > i) {
					temp.tasks[i] = this.tasks[i];
				}else {
					temp.tasks[i] = add;
				}
			}
			temp.numTasks = this.numTasks + 1;
			this.tasks = temp.tasks;
			this.INITIAL_CAPACITY = temp.INITIAL_CAPACITY;
			this.numTasks = temp.numTasks;
		}else {
			//you already have the null task in the array, just add 1 to the number of tasks
			this.tasks[numTasks] = add;
			this.numTasks++;
		}
	}
	
	public int totalTime() {
		int all = 0;
		for(int i = 0; i < this.INITIAL_CAPACITY;i++) {
			all += this.tasks[i].getEMTC();
		}
		return all;
	}
	public int shortestTime() {
		int shrt = this.tasks[0].getEMTC();
		for(int i = 0; i < this.INITIAL_CAPACITY;i++) {
			int temp = this.tasks[i].getEMTC();
			if(shrt > temp) {
				shrt = temp;
			}
		}
		return shrt;
	}
	
	//shouldnt this take the name of the task that was completed and then use the 
	//find function within this to get the index
	public Task completeTask(int ind) {
		Task tTemp = new Task();
		int init_cap = this.INITIAL_CAPACITY;
		if(ind < 0 || ind > init_cap) {
			return null;
		}else {
			HoneyDoList temp = new HoneyDoList(init_cap);
			int i = 0;
			int j = 0;
			for(i = 0; i < init_cap;i++) {
				if(i == ind) {
					j--;
					tTemp = this.tasks[i];
				}else {
					temp.tasks[j] = this.tasks[i];
				}
				j++;
			}
			this.tasks = temp.tasks;
			this.numTasks -= 1;
			this.INITIAL_CAPACITY = temp.INITIAL_CAPACITY;
		}
		
		return tTemp;
	}
	//seems useful
	public Task completeTask(String name) {
		int ind = this.find(name);
		Task tTemp = new Task();
		int init_cap = this.INITIAL_CAPACITY;
		if(ind < 0 || ind > init_cap) {
			return null;
		}else {
			HoneyDoList temp = new HoneyDoList(init_cap);
			int i = 0;
			int j = 0;
			for(i = 0; i < init_cap;i++) {
				if(i == ind) {
					j--;
					tTemp = this.tasks[i];
				}else {
					temp.tasks[j] = this.tasks[i];
				}
				j++;
			}
			this.tasks = temp.tasks;
			this.numTasks -= 1;
			this.INITIAL_CAPACITY = temp.INITIAL_CAPACITY;
		}
		
		return tTemp;
	}

}
