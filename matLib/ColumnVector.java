package matLib;

public class ColumnVector extends Matrix {
	
	public ColumnVector(int n){
		super(n,1);
	}

	public ColumnVector(double[] array){
		super(array.length,1);
		for(int i = 0 ; i < numberOfRows; i++)
			data[i][0] = array[i];
	}

	public ColumnVector(Vector other){
		this(other.size());
		for (int i = 0; i < size(); i++)
			this.set(i,other.get(i));
	}

	public ColumnVector(Matrix other,int column){
		this(other.numberOfRows);
		for (int i = 0; i < size(); i++)
			this.set(i,other.get(column,i));
	}

	public void set(int i, double value){
		data[i][0] = value;
	}

	public double get(int i){
		return data[i][0];
	}

	public int size(){
		return numberOfRows;
	}
	
	public ColumnVector copy(){
		return new ColumnVector(this.toArray());
	}
}
