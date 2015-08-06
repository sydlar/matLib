package matLib.test;

import matLib.dataVectors.DataVectors;
import matLib.util.Vectors;

public class DataTest {
	public static void main(String[] args){
		
		double[] x = DataVectors.getData("weatherData/OsloSeries",5);

		Vectors.printGnuplotData(x);
	}
}
