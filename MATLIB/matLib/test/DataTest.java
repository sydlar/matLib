package matLib.test;

import matLib.dataVectors.DataVectors;
import matLib.util.Vectors;

public class DataTest {
	public static void main(String[] args){
		
		double[] x = DataVectors.getData("tall.txt",0);
		double[] y = DataVectors.getData("tall.txt",1);
		Vectors.printGnuplotData(x,y);
	}
}
