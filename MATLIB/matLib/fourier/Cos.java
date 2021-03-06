package matLib.fourier;

public class Cos extends AbstractTrig {
	
	private static double SQRT_OF_ONE_HALF = Math.sqrt(.5);

	public Cos(int n){
		super(n);
	}

	public Cos(int n, int m){
		super(n,m);
	}

	public double get(int i){
		if (factor == 0)
			return SQRT_OF_ONE_HALF;
		else
			return Math.cos(factor*i);
	}
}
