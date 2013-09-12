package matLib.generators;

import matLib.AbstractVector;

public class LinearCombination extends AbstractVector {

	private AbstractVector[] vectors;
	private double[] coefficients;
	private int n;

	public LinearCombination(AbstractVector[] theVectors, double[] coeffs){
		vectors = theVectors;
		coefficients = coeffs;
		n = vectors.length;
	}

	public double get(int j){
		double out = 0.0;
		for (int i = 0; i < n; i++)
			out += vectors[i].get(j)*coefficients[i];
		return out;
	}

	public void set(int i , double val){
		throw new UnsupportedOperationException("LinearCombination");
	}

	public int size(){
		return n;
	}
}
