package matLib.functions;

import java.util.Arrays;

public class Polynomial extends Function {

	private double[] coeffs;

	public Polynomial(double[] theCoeffs){
		coeffs = theCoeffs;
	}

	
	public Polynomial add(Polynomial other){
		int newSize = Math.max(other.coeffs.length,this.coeffs.length);
		double[] newArray = Arrays.copyOf(coeffs,newSize);
		for ( int i = 0; i < other.coeffs.length; i++){
			newArray[i]+=other.coeffs[i];
		}
		return new Polynomial(newArray);
	}

	@Override
	public Polynomial multiply(double scalar){
		double[] retArray = new double[coeffs.length];
		for(int i = 0; i < coeffs.length; i++){
			retArray[i] = coeffs[i]*scalar;
		}
		return new Polynomial(retArray);
	}

	public double evaluate(double x){ // Horner's rule
		double result = 0.0;
		for(int i = coeffs.length-1; i >= 0; i--){
			result = coeffs[i] + x*result;
		}
		return result;
	}

	public String toString(){
		return coeffs.toString();	
	}
}
