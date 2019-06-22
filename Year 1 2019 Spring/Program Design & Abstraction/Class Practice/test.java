
public class test {
	public static void main(String[] args) {
		Task my = new Task("math",1,60);
		System.out.println(my.getEMTC());
		System.out.println(my.getName());
		System.out.println(my.getPriority());
		System.out.println(my.toString());
		my.setName("reed");
		my.setEMTC(4);
		my.setPriority(20);
		System.out.println(my.getEMTC());
		System.out.println(my.getName());
		System.out.println(my.getPriority());
		System.out.println(my.toString());
		Task yes = new Task();
		System.out.println(yes.toString());
	}
}
