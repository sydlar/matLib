package matLib.dataVectors;

import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import matLib.RowVector;

public class DataVectors {

	public static double[] getData(String fileName){
		return getData(fileName,0);
	}


	public static double[] getData(String fileName,int column){
		int count = 0;
		String[] line;
		ArrayList<Double> outputList = new ArrayList<Double>();

		try {
			Scanner fileScanner = new Scanner(new File(fileName));
			Scanner doubleScanner;
			while(fileScanner.hasNextLine()){
				line = fileScanner.nextLine().trim().split("\\s");

				if ( line.length > column){
					doubleScanner = new Scanner(line[column]);
					if (doubleScanner.hasNextDouble() && line.length >column){
						outputList.add(doubleScanner.nextDouble());
						count++;
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		double[] output = new double[count];
		for(int i = 0; i < output.length; i++)
			output[i] = outputList.get(i);

		return output;
	}
}
