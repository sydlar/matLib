package matLib;

import matLib.Settings;
import matLib.generators.Sum;
import matLib.generators.Multiple;

public abstract class AbstractVector {

	public abstract void set(int i, double value);
	public abstract double get(int i);
	public abstract int size();

	public void add(AbstractVector other){// IN-place
		if (!dimensionCheck(this,other))
			throw new DimensionError("add");
		for(int i = 0; i < size();i++)
			this.set(i,this.get(i)+other.get(i));
	}
	
	public void subtract(AbstractVector other){ // IN-place
		if (!dimensionCheck(this,other))
			throw new DimensionError("subtract");
		for(int i = 0; i < size();i++)
			this.set(i,this.get(i) - other.get(i));
	}
	
	public void multiply(double scalar){ // IN-place
		for(int i = 0; i < size();i++)
			this.set(i ,this.get(i)*scalar );
	}


	/*
	 * Static methods for vector operations.
	 */

	public static Sum add(AbstractVector A, AbstractVector B){
		dimensionTest(A,B,"add");
		return new Sum(A,B);
	}
	
	public static Multiple multiply(AbstractVector A, double s){
		return new Multiple(A,s);
	}

	public static Sum subtract(AbstractVector A, AbstractVector B){
		dimensionTest(A,B,"subtract");
		return add(A,multiply(B,-1.0));
	}

	public static double scalarProduct(AbstractVector A, AbstractVector B){
		dimensionTest(A,B,"scalarProduct");
		double out = 0.0;
		for(int i = 0 ; i < A.size(); i++)
			out+=A.get(i)*B.get(i);	
		return Math.abs(out) > Settings.EPS ? out : 0.0;
	}

	public double norm(){
		return Math.sqrt(scalarProduct(this,this));
	}

	public double[] toArray(){
		double[] retArray = new double[size()];
		for (int i = 0; i < size(); i++)
			retArray[i] = get(i);
		return retArray;
	}

	public String toString(){
		StringBuilder buffer = new StringBuilder();
			buffer.append("[");
			for(int j = 0; j < size(); j++)
				buffer.append(String.format(" %.3e\t",this.get(j)));
			buffer.append("]");
		return buffer.toString();
	}
	
	protected static boolean dimensionCheck(AbstractVector A, AbstractVector B){
		return A.size() == B.size();
	}

	protected static void dimensionTest(AbstractVector A, AbstractVector B,String message){
		if(!dimensionCheck(A,B))
			throw new DimensionError(message);
	}

}
