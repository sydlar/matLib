package matLib.util;

import java.util.Arrays;
import java.util.Random;

/*
 * Class containing static methods for doing vector operations
 * on arrays of the type 'double[]'
 *
 * The methods are static. 
 *
 * Design philosophy:
 * - We operate directly on arrays of numbers.
 * - No error handling: It is up to the caller to ensure that
 *   the use is safe and meaningful.
 * - It may be a good idea to write one or more wrapper classes
 *   that use these methods. This class can for instance do 
 *   sone error handling.  
 */

public class Vectors {

	/*
	 * VECTOR OPERATIONS
	 ********************************************/
	public static double[] add(double[] u, double[] v){
	  	double[] output = new double[u.length];
		for(int i = 0;  i < output.length; i++)
			output[i] = u[i]+v[i];
		return output;
	}

	
	public static double[] subtract(double[] u, double[] v){
	  	double[] output = new double[u.length];
		for(int i = 0;  i < output.length; i++)
			output[i] = u[i]-v[i];
		return output;
	}

	
	public static double[] multiply(double[] u, double s){
	  	double[] output = new double[u.length];
		for(int i = 0;  i < output.length; i++)
			output[i] = s*u[i];
		return output;
	}
	

	public static double[] linearCombination(double[][] vectors, double[] constants){
		double[] output = new double[vectors[0].length];
		for (int i = 0; i < output.length; i++)
			for ( int j = 0; j < vectors.length; j++)
				output[i] += constants[i]*vectors[j][i];
		return output;
	}

	/*
	 * IN-PLACE VECTOR OPERATIONS: 
	 * The contents of 'vector' will be modified.
	 ********************************************/
	
	public static void addInplace(double[] vector, double[] other){
		for(int i = 0; i < vector.length; i++)
			vector[i] += other[i];
	}
	
	public static void subtractInplace(double[] vector, double[] other){
		for(int i = 0; i < vector.length; i++)
			vector[i] -= other[i];
	}

	public static void multiplyInplace(double[] vector, double scalar){
		for(int i = 0; i < vector.length; i++)
			vector[i] *=scalar;
	}

	/*
	 * SCALAR PRODUCT OPERATIONS
	 ************************************************/
	public static double scalarProduct(double[] u, double[] v){
		double output = 0.0;
		for (int i = 0; i < u.length; i++)
			output += u[i]*v[i];
		return output;
	}


	public static double angle(double[] v, double[] u){
		//TODO
		return 0.0;
	}

	/*
	 * Calculates the projection of 'vector' along 'direction'
	 */
	public static double[] projection(double[] direction, double[] vector){
		double nominator = scalarProduct(direction,vector);
		double denominator = scalarProduct(direction,direction);
		return multiply(direction,nominator/denominator);
	}

	public static double[] normalComponent(double[] direction, double[] vector){
		double[] proj = projection(direction,vector);
		return subtract(vector,proj);
	}

	/*
	 * Calculates the length of the vector 'u'
	 */
	public static double norm(double[] u){
		return Math.sqrt(scalarProduct(u,u));
	}


	public static double[] displacementVector(double[] A, double[] B){
		return subtract(B,A);
	}

	public static double distance(double[] A, double[] B){
		return norm(displacementVector(A,B));
	}



	/*
	 * APPLICATION OF OPERATIONS
	 **********************************************/

	public static double[] applyBinaryOperation(double[] v, double[] u, BinaryOperation op){
		double[] out = new double[v.length];
		for (int i = 0; i < v.length; i++)
			out[i] = op.apply(v[i],u[i]);
		return out;
	}
	
	public static void applyBinaryOperationInplace(double[] v, double[] u, BinaryOperation op){
		for (int i = 0; i < v.length; i++)
			v[i] = op.apply(v[i],u[i]);
	}
	
	public static double[] applyUnaryOperation(double[] v, UnaryOperation op){
		double[] out = new double[v.length];
		for (int i = 0; i < v.length; i++)
			out[i] = op.apply(v[i]);
		return out;
	}
	
	public static void applyUnaryOperationInplace(double[] v,UnaryOperation op){
		for (int i = 0; i < v.length; i++)
			v[i] = op.apply(v[i]);
	}

	/*
	 * CROSS PRODUCT: (DUE Fredag den 13.)
	 ********************************************/

	/*
	 * Assumes that u,v have 3 components
	 */
	public static double[] crossProduct(double[] u, double[] v){
		//LATER
		return null;
	}

	/*
	 * Assumes that u,v have 2 components
	 */
	public static double determinant(double[] u, double[] v){
		return u[0]*v[1]-u[1]*v[0];
	}


	/*
	 * USEFUL TOOLS FOR TESTING AND DEBUGGING
	 *******************************************/


	public static double[] copy(double[] v){
		return Arrays.copyOf(v,v.length);
	}

	public static double[] zero(int n){
		return new double[n];
	}

	public static double[] elementary(int n, int i){
		double[] out = new double[n];
		out[i] = 1.0;
		return out;
	}

	public static double[] range (double begin, double end, int size){
		double[] out = new double[size];
		double length = end - begin;
		int endIndex = size-1;
		for(int i = 0, j =  endIndex; i < size; i++,j--){
			out[i] = (j*begin+i*end)/endIndex;
		}
		return out;
	}


	public static double[] random(int n){
		Random r = new Random();
		double[] out = new double[n];
		for(int i = 0; i < n; i++)
			out[i] = 10.0*r.nextGaussian();
		return out;
	}
	
	public static void print(double s){
			System.out.printf(" %f ",s);
	}

	public static void print(double[] v){
		System.out.print("[");
		for(int i = 0; i < v.length; i++)
			print(v[i]);
		System.out.println("]");
	}

	public static void print(double[][] vectors){
		System.out.println();
		for(int i = 0; i < vectors.length; i++)
			print(vectors[i]);
		System.out.println();
	}
	
	public static void printGnuplotData(double[] x){
		printGnuplotData(new double[][]{x});
	}

	public static void printGnuplotData(double[] x, double[] y){
		printGnuplotData(new double[][]{x,y});
	}
	
	public static void printGnuplotData(double[][] vectors){
		System.out.print("#AUTO-GENERATED PLOTTING DATA\n#");
		System.out.print(" x \t");
		for(int i = 1; i < vectors.length; i++)
			System.out.printf("\ty_%d\t",i);
		printRawData(vectors);
	}

	private static void printRawData(double[][] vectors){
		for(int i = 0; i < vectors[0].length; i++){
			System.out.println();
			for (int j = 0; j < vectors.length; j++)
				System.out.printf("%e\t",vectors[j][i]);
		}
	}
}
