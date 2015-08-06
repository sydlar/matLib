package matLib.functions;

public class Sum extends Function {
	private Function A;
	private Function B;

	public Sum(Function theA, Function theB) {
		A = theA;
		B = theB;
	}

	public double  evaluate(double x){
		return A.evaluate(x) + B.evaluate(x);
	}

}
