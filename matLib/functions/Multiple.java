package matLib.functions;

public class Multiple extends Function {
	private Function A;
	private double S;

	public Multiple(Function theA, double theS){
		A = theA;
		S = theS;
	}

	public double evaluate(double x){
		return S*A.evaluate(x);
	}

}
