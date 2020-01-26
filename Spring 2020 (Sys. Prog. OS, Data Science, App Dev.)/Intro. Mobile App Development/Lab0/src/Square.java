
public class Square extends Shape{
	int Length;
	int Height;
	public Square(String name) {
		super(name);
	}
	
	public void setDimensions(int length, int height) {
		Length = length;
		Height = height;
	}
	
	@Override
	public void printDimensions() {
		System.out.println("Length: " + Length);
		System.out.println("Height: " + Height);
	}
	
	@Override
	public double getArea() {
		double area = (Length * Height);
		return area;
	}
}
