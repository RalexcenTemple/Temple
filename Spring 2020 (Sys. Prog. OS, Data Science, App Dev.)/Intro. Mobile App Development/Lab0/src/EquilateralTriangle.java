
public class EquilateralTriangle extends Triangle{
	int Side;
	public EquilateralTriangle(String name) {
		super(name);
		// TODO Auto-generated constructor stub
	}
	
	public void setDimensions(int side) {
		Side = side;
	}
	
	@Override
	public void printDimensions() {
		System.out.println("Side: " + Side);
	}
	
	@Override
	public double getArea() {
		double s = (Side * 3);
		s = (s/2);
		double s0 = (s - Side);
		double sSet = (s0 * s0 * s0);
		double sProduct = (sSet * s);
		sProduct = Math.sqrt(sProduct);
		return sProduct;
	}

}
