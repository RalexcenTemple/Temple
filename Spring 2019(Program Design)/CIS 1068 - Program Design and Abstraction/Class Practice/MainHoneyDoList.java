
public class MainHoneyDoList {
	public static void main(String[] args) {
		HoneyDoList my = new HoneyDoList(0);
		System.out.println(my.toString() + " Empty");
		my.addTask();
		System.out.println(my.toString() + " 1 empty");
		Task[] temp = new Task[3];
		temp[0] = new Task("reed", 1,1);
		temp[1] = new Task("jason",2,2);
		temp[2] = new Task("chris",3,3);
		HoneyDoList test = new HoneyDoList(temp,3,3);
		System.out.println("test new list");
		System.out.println(test.toString());
		System.out.println("find reed");
		System.out.println(test.find("reed"));
		Task jerry = new Task("jerry",4,4);
		System.out.println("add jerry");
		test.addTask(jerry);
		System.out.println(test.toString());
		System.out.println("total time");
		System.out.println(test.totalTime());
		System.out.println(test.toString());
		System.out.println("shortest time");
		System.out.println(test.shortestTime());
		System.out.println(test.toString());
		System.out.println("complete reed");
		System.out.println((test.completeTask(0)).toString());
		System.out.println("complete");
		System.out.println(test.toString());
		System.out.println((test.completeTask("jason")).toString());
		System.out.println("complete");
		System.out.println(test.toString());
	}
}
