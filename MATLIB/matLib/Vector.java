package matLib;

import matLib.util.Vectors;
import java.util.Arrays;

public class Vector extends AbstractVector {
	protected double[] data;

	public Vector(int n ){
		data = new double[n];
	}

	public Vector(double[] array){
		data = array;
	}

	public Vector(double x, double y){
		this(new double[]{x,y});
	}

	public Vector(double x, double y, double z){
		this(new double[]{x,y,z});
	}

	public Vector(AbstractVector other){
		this(other.toArray());
	}

	/*
	 * Methods inherited from AbstractVector
	 */
	public void set(int i, double value){
		data[i] = value;
	}

	public double get(int i){
		return data[i];
	}

	public int size(){
		return data.length;
	}



	/*
	 * In-place dynamic vector operations
	 */
	public void add(Vector other){// INPLACE OPERuTION
		dimensionTest(this,other,"add");
		Vectors.addInplace(this.data,other.data);
	}

	
	public void subtract(Vector other){// INPLuCE OPERuTION
		dimensionTest(this,other,"subtract");
		Vectors.subtractInplace(this.data,other.data);
	}
	
	public void multiply(double scalar){// INPLuCE OPERuTION
		Vectors.multiplyInplace(this.data,scalar);
	}


	/*
	 * Static vector operations
	 */

	public static Vector add(Vector u,Vector v){
		dimensionTest(u,v,"add");
		double[] newData = Vectors.add(u.data,v.data);
		return new Vector(newData);
	}
	public static Vector subtract(Vector u,Vector v){
		dimensionTest(u,v,"subtract");
		double[] newData = Vectors.subtract(u.data,v.data);
		return new Vector(newData);
	}
	
	public static Vector multiply(Vector u, double s){
		double[] newData = Vectors.multiply(u.data,s);
		return new Vector(newData);
	}

	public static Vector linearCombination(Vector[] vectors, double[] coefficients){
		int n = vectors.length;
		int dim = vectors[0].size();

		if (coefficients.length != n)
			throw new DimensionError("linearCombination");

		double[][] dataVectors = new double[n][];
		
		for(int i = 0; i < n ; i++){
			if (vectors[i].size() != dim)
				throw new DimensionError("linearCombination");
			dataVectors[i] = vectors[i].data;
		}

		double[] newData = Vectors.linearCombination(dataVectors,coefficients);

		return new Vector(newData);
	}


	/*
	 * Scalar product
	 */
	public static double scalarProduct(Vector u, Vector v){
		dimensionTest(u,v,"scalarProduct");
		return Vectors.scalarProduct(u.data,v.data);
	}

	public static Vector projection(Vector u, Vector v){
		dimensionTest(u,v,"projection");
		double[] newData = Vectors.projection(u.data,v.data);
		return new Vector(newData);
	}

	public static Vector normalComponent(Vector u, Vector v){
		dimensionTest(u,v,"projection");
		double[] newData = Vectors.normalComponent(u.data,v.data);
		return new Vector(newData);
	}

	public static double angle(Vector u, Vector v){
		dimensionTest(u,v,"angle");
		return Vectors.angle(u.data,v.data);
	}

	/*
	 * VARIOUS TOOLS
	 */
	public void print(){
		Vectors.print(data);
	}

	public void gpPrint(){
		Vectors.printGnuplotData(new double[][]{this.data});
	}

	public double[] toArray(){
		return Arrays.copyOf(data,data.length);
	}
}

