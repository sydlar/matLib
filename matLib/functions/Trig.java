package matLib.functions;

public class Trig extends Function {
	
	public static final Trig sin = new Trig(0,1.0,2*Math.PI);
	public static final Trig cos = new Trig(-0.5*Math.PI,1.0,2*Math.PI);

	private double shift;
	private double amplitude;
	private double k;

	public Trig(double s, double a, double period){
		shift = s;
		amplitude = a;
		k = 2*Math.PI/period;

	}

	public double evaluate(double x){
		return amplitude*Math.sin(k*(x-shift));
	}
}
