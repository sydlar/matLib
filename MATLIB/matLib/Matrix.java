package matLib;

import java.util.Arrays;
import matLib.util.Matrices;

public class Matrix extends AbstractMatrix {
	public final int numberOfRows;
	public final int numberOfColumns;

	protected double[][] data;

	public Matrix(int m, int n){
		numberOfRows = m;
		numberOfColumns = n;
		data = new double[m][n]; 
	}

	public Matrix(double[][] array){
		this(array.length, array[0].length);
		
		for(int i = 0; i < numberOfRows; i++)
			data[i] = Arrays.copyOf(array[i],numberOfColumns);
	}

	public void set(int i, int j, double value){
		data[i][j] = value;
	}

	public double get(int i, int j){
		return data[i][j];
	}

	public int numberOfRows(){
		return numberOfRows;
	}

	public int numberOfColumns(){
		return numberOfColumns;
	}


	/*
	 * In-place dynamic vector operations.
	 */
	public void add(Matrix other){ // In-place operation !!
		dimensionTest(this,other,"add");
		Matrices.addInplace(this.data,other.data);

	}


	public void subtract(Matrix other){ // In-place operation !!
		dimensionTest(this,other,"subtract");
		Matrices.subtractInplace(this.data,other.data);
	}


	public void multiply(double scalar){// In-place operation !!
		Matrices.multiplyInplace(this.data,scalar);
	}


	/*
	 * Static Matrix operation methods:
	 */

	public static Matrix add(Matrix A, Matrix B){
		dimensionTest(A,B,"add");
		return new Matrix(Matrices.add(A.data,B.data));
	}
	
	public static Matrix subtract(Matrix A, Matrix B){
		dimensionTest(A,B,"add");
		return new Matrix(Matrices.subtract(A.data,B.data));
	}

	public static Matrix multiply(Matrix A,double s){
		return new Matrix(Matrices.multiply(A.data,s));
	}
	public Matrix multiply(Matrix A,Matrix B){ 
		if (!canBeMultiplied(A,B))
			throw new DimensionError("multiplication");

		return new Matrix(Matrices.multiply(A.data,B.data));
	}
	
	public Matrix transpose(Matrix A){
		return new Matrix(Matrices.transpose(A.data));
	}

	public Matrix inverse(Matrix A){
		if (!canBeMultiplied(A,A))
			throw new DimensionError("inversion");
		if (Matrices.det(A.data) == 0)
			throw new InversionError("inversion: The determinant is 0");
		return new Matrix(Matrices.inverse(A.data));
	}

	/*
	 * VARIOUS TOOLS
	 */
	
	public String toString(){
		StringBuilder buffer = new StringBuilder();
		for ( int i = 0; i < numberOfRows; i++){
			buffer.append("{");
			for(int j = 0; j < numberOfColumns; j++)
				buffer.append(String.format(" %.3e\t",data[i][j]));
			buffer.append("}\n");
		}
		return buffer.toString();
	}


}
