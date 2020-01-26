
public class Triangle extends Shape{
	
	int Side0;
	int Side1;
	int Side2;
	
	public Triangle(String name) {
		super(name);
	}
	
	public void setDimensions(int side0,int side1,int side2) {
			Side0 = side0;
			Side1 = side1;
			Side2 = side2;
	}
	
	@Override
	public void printDimensions() {
		System.out.println("Side0: " + Side0);
		System.out.println("Side1: " + Side1);
		System.out.println("Side2: " + Side2);
	}
	
	@Override
	public double getArea() {
		double perim = Side0 + Side1 + Side2;
		double s = (perim/2);
		double s0 = (s - Side0);
		double s1 = (s - Side1);
		double s2 = (s - Side2);
		double sProduct = (s * (s0 * s1 * s2));
		sProduct = Math.sqrt(sProduct);
		return sProduct;
	}
}
