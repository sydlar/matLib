package matLib.util;

public interface UnaryOperation {

	public static final UnaryOperation NEG = new UnaryOperation(){
			public double apply(double x){
				return -1.0*x;
			}
	};


	public static final UnaryOperation SIN = new UnaryOperation(){
			public double apply(double x){
				return Math.sin(x);
			}
	};

	
	public static final UnaryOperation COS = new UnaryOperation(){
			public double apply(double x){
				return Math.cos(x);
			}
	};

	public double apply(double x);
}
