package matLib;

import java.util.Arrays;

public class RowVector extends Matrix {
	
	public RowVector(int n){
		super(1,n);
	}

	public RowVector(double[] array){
		this(array.length);
		data[0] = Arrays.copyOf(array,array.length);
	}

	public RowVector(Vector other){
		this(other.size());
		for (int i = 0; i < size(); i++)
			this.set(i,other.get(i));
	}

	public RowVector(Matrix other, int row){
		this(other.numberOfColumns);
		for(int i = 0; i < size(); i++)
			this.set(i,other.get(i,row));
	}

	public void set(int i, double value){
		data[0][i] = value;
	}

	public double get(int i){
		return data[0][i];
	}

	public int size(){
		return numberOfColumns;
	}

	public RowVector copy(){
		return new RowVector(this.toArray());
	}

	public double[] toArray(){
		return Arrays.copyOf(data[0],size());
	}
}
