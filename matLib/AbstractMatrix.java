package matLib;

import java.util.Arrays;
import matLib.util.Matrices;

public abstract class AbstractMatrix  extends AbstractVector {

	public abstract void set(int i, int j, double value);
	public abstract double get(int i, int j);
	public abstract int numberOfRows();
	public abstract int numberOfColumns();

	public void set(int i ,double value){
		set(i%numberOfColumns(), i/numberOfColumns());
	}

	public double get(int i ){
		return get(i%numberOfColumns(),i/numberOfColumns());
	}

	public int size(){
		return numberOfRows()*numberOfColumns();
	}



	public String toString(){
		StringBuilder buffer = new StringBuilder();
		for ( int i = 0; i < this.numberOfRows(); i++){
			buffer.append("[");
			for(int j = 0; j < this.numberOfColumns(); j++)
				buffer.append(String.format(" %.3e\t",get(i,j)));
			buffer.append("]\n");
		}
		return buffer.toString();
	}


	protected static boolean dimensionCheck(AbstractMatrix A, AbstractMatrix B){
		if ( A.numberOfRows() != B.numberOfRows())
			return false;
		if (A.numberOfColumns() != B.numberOfColumns())
			return false;
		return true;
	}

	protected static boolean canBeMultiplied(AbstractMatrix A, AbstractMatrix B){
		return A.numberOfColumns() == B.numberOfRows();
	}
}
