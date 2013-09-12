package matLib.fourier;

public class Sin extends AbstractTrig {
	
	public Sin(int n){
		super(n);
	}

	public Sin(int n, int m){
		super(n,m);
	}

	public double get(int i){
		return Math.sin(factor*i);
	}
}
