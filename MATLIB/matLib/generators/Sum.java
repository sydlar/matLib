package matLib.generators;

import matLib.AbstractVector;

public class Sum extends AbstractVector {
	private AbstractVector A;
	private AbstractVector B;

	public Sum(AbstractVector theA, AbstractVector theB) {
		A = theA;
		B = theB;
	}

	public double  get(int n){
		return A.get(n) + B.get(n);
	}

	public void set(int i, double value){
		throw new UnsupportedOperationException("Sum-object");
	}

	public int size(){
		return A.size();
	}

}
