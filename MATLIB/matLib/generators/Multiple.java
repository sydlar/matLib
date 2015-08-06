package matLib.generators;

import matLib.AbstractVector;

public class Multiple extends AbstractVector {
	private AbstractVector A;
	private double S;

	public Multiple(AbstractVector theA, double theS){
		A = theA;
		S = theS;
	}

	public double get(int n){
		return S*A.get(n);
	}

	public void set(int i, double value){
		throw new UnsupportedOperationException("Multiple Object");
	}

	public int size(){
		return A.size();
	}
}
