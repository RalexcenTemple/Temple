import java.util.Scanner;

public class Shape {
	
	private String name;
	
	public Shape(String name){
		this.name = name;
	}/** returns the name of the shape */
	
	
	public String getName() {
		return name;
		}/** returns the area of the shape */
	
	
	public double getArea() {
		return 0.0;
		}
	
	
	public void printDimensions(){
		System.out.println("No deminsions");
	}
	
	public static void main(String args[]) {
		Scanner in = new Scanner(System.in);
		
		Square square = new Square("square");
		Circle circle = new Circle("cirlce");
		Triangle triangle = new Triangle("triangle");
		EquilateralTriangle equiltri = new EquilateralTriangle("equiltri");
		
		//get and set square information
		System.out.print("Please Enter a whole number for the Length for a Square: ");
		int length = in.nextInt();
		System.out.println("");
		System.out.print("Please Enter a whole number for the Height of a Square: ");
		int height = in.nextInt();
		System.out.println("");
		square.setDimensions(length, height);
		
		//get and set circle information
		System.out.print("Please enter the Radius for a Circle: ");
		int radius = in.nextInt();
		System.out.println("");
		circle.setDimensions(radius);
		
		//get and set triangle information
		System.out.print("Please enter the length of the first side of a Triangle: ");
		int side0 = in.nextInt();
		System.out.println("");
		System.out.print("Please enter the length of the second side of a Triangle: ");
		int side1 = in.nextInt();
		System.out.println("");
		System.out.print("Please enter the length of the third side of a Triangle: ");
		int side2 = in.nextInt();
		System.out.println("");
		triangle.setDimensions(side0, side1, side2);
		
		//get and set equaltri information
		System.out.print("Please Enter the length of a side of a Equilateral Triangle: ");
		int side = in.nextInt();
		System.out.println("");
		equiltri.setDimensions(side);
		
		//PRINT INFORMATION
		
		//print square:
		System.out.println("SQUARE INFORMATION:");
		System.out.println("SQUARE NAME: " + square.getName());
		square.printDimensions();
		System.out.println("AREA: " + square.getArea());
		System.out.println("");
		
		//print square:
		System.out.println("CIRCLE INFORMATION:");
		System.out.println("CIRCLE NAME: " + circle.getName());
		circle.printDimensions();
		System.out.println("AREA: " + circle.getArea());
		System.out.println("");
		
		//print square:
		System.out.println("TRIANGLE INFORMATION:");
		System.out.println("TRIANGLE NAME: " + triangle.getName());
		triangle.printDimensions();
		System.out.println("AREA: " + triangle.getArea());		
		System.out.println("");
		
		//print square:
		System.out.println("EQUILATERAL TRIANGLE INFORMATION:");
		System.out.println("EQUILATERAL TRIANGLE NAME: " + equiltri.getName());
		equiltri.printDimensions();
		System.out.println("AREA: " + equiltri.getArea());		
		System.out.println("");
		
		
		
		in.close();
	}
	
}
