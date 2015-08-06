package matLib.fourier;

import matLib.AbstractVector;

public class Fourier extends AbstractTrig {

	private AbstractTrig function;
	private Sin sin;
	private Cos cos;

	public Fourier(int n){
		this(n,0);
	}

	public Fourier(int n, int index){
		super(n,index);
		sin = new Sin(n);
		cos = new Cos(n);
		if (index%2 == 0){
			function = cos;
		} else {
			function = sin;
		}
		this.setIndex(index);
	}

	public void setIndex(int index){
		if(index%2==0)
			function = cos;
		else if (index%2==1)
			function = sin;
		function.setWaveNumber((index +1 )/ 2 );
	}

	public double get(int i){
		return function.get(i);
	}
}
