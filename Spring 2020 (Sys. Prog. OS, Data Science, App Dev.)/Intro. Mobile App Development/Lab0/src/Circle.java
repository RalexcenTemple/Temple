
public class Circle extends Shape{
	int Radius;
	public Circle(String name) {
		super(name);
	}
	
	public void setDimensions(int radius) {
		Radius = radius;
	}
	
	@Override
	public void printDimensions() {
		System.out.println("Radius: " + Radius);
	}
	
	@Override
	public double getArea() {
		double pi = 3.14159;
		double rSqur = (Radius * Radius);
		double out = (pi * rSqur);
		return out;
	}
}
