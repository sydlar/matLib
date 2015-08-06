package matLib.fourier;

import matLib.AbstractVector;

public abstract class AbstractTrig extends AbstractVector {
	
	protected double factor;
	protected int size;

	protected AbstractTrig(int n){
		size = n;
		setWaveNumber(1);
	}
	
	protected AbstractTrig(int n,int waveNumber){
		size = n;
		setWaveNumber(waveNumber);
	}
	
	public void setWaveNumber(int n){
		factor = (2.0*Math.PI*n)/size;
	}

	public void set(int i, double value){
		throw new UnsupportedOperationException("AbstractTrig");
	}

	public int size(){
		return size;
	}


}
