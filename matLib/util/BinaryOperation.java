package matLib.util;

public interface BinaryOperation {

	public static final BinaryOperation ADD = new BinaryOperation(){
			public double apply(double x,double y){
				return x+y;
			}
	};


	public static final BinaryOperation SUBTRACT = new BinaryOperation(){
			public double apply(double x,double y){
				return x-y;
			}
	};

	
	public static final BinaryOperation MUL = new BinaryOperation(){
			public double apply(double x, double y){
				return x*y;
			}
	};

	public double apply(double x, double y);
}
