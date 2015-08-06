package matLib.dataVectors;

import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

import matLib.RowVector;

/**
 * This class provides static methods for reading data from text-files.
 *
 * @author sydlar
 *
 */
public class DataVectors {

	/**
	 * Tries to read the first column of double-values from a text-file
	 * 
	 * @param fileName 
	 * @return A double[]-array, containing numbers read from the first column of the file fileName
	 */
	public static double[] getData(String fileName){
		return getData(fileName,0);
	}

	/**
	 * Tries to read double values from `column` in `fileName`.
	 * 
	 * @param fileName
	 * @param column
	 * @return A `double[]`-array that contains the 
	 */

	public static double[] getData(String fileName,int column){
		
		ArrayList<Double> outputList = new ArrayList<Double>();
		
		Scanner fileScanner;
		try {
			fileScanner = new Scanner(new File(fileName));
		} catch (FileNotFoundException e1) {
			return new double[0];
		}
		
		while(fileScanner.hasNextLine()){
			String[] line = fileScanner.nextLine().trim().split("\\s");
			if (line.length > column){
				Scanner doubleScanner = new Scanner(line[column]);
				if (doubleScanner.hasNextDouble() && line.length >column)
					outputList.add(doubleScanner.nextDouble());
				doubleScanner.close();
			}
		}
		fileScanner.close();
		double[] output = new double[outputList.size()];
		for(int i = 0; i < output.length; i++)
			output[i] = outputList.get(i);
		
		return output;
	}
}
