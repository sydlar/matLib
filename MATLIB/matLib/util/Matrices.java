package matLib.util;

import java.util.Arrays;
import java.util.Random;

/*
 * Merknad: Ikke ta feilhåndteringen for seriøst her.
 * 
 * Det ligger i sakens natur at operasjonene ikke alltid
 * er veldefinert. Klassen Matrices kan gjerne fraskrive seg
 * ansvaret.
 *
 * En løsning som sannsynligvis fungerer ok er å finne opp noen 
 * passende unntak som arver fra RuntimeException. (Se nederst)
 *
 */

public class Matrices {

	public static final double EPS = 1e-11;

	public static void main(String[] args){
		System.out.println("SIMPLE TESTING");
		int n = 2;

		System.out.println("A:");
		double[][] A = randomSquare(n);
/*
		print(A);

		System.out.println("B:");
		double[][] B = randomSquare(n);
		print(B);
	
		System.out.println("b:");
		double[] b = randomArray(n);
		print(b);

		System.out.print("A*B");
		print(multiply(A,B));
		
		System.out.print("B*A");
		print(multiply(B,A));
		
		System.out.print("b*A");
		print(multiply(b,A));

		System.out.print("A*b");
		print(multiply(A,b));


		System.out.print("SYSTEM");
		double[][] S = randomSystem(3);
		print(S);
		Solvers.simpleGaussJordanElimination(S);
		print(S);

		System.out.println("Beregner A-invers");
		double[][] Ainv = invert(A);
		print(Ainv);
		System.out.println("A*Ainv");
		Latex.printMatrix(multiply(A,Ainv));
		System.out.println("Ainv*");
		print(multiply(Ainv,A));

		System.out.println(det(A));
		System.out.println(det(Ainv));
		System.out.println(det(A)*det(Ainv));
		

		System.out.println("\n\nBRUTE FORCE DETERMINANT:");
		System.out.println(laplaceDeterminant(A));*/
		System.out.println("\n\nQUICK DETERMINANT:");
		System.out.println(det(A));
	}

	/*
	 * Vector operations
	 */

	public static double[][] add(double[][] A, double[][] B){
		int rows = A.length;
		int cols = A[0].length;

		double[][] out = new double[rows][cols];
		for(int i = 0; i <rows; i++){
			for (int j = 0; j < cols; j++){
				out[i][j] = A[i][j] + B[i][j];
				
				if (Math.abs(out[i][j]) < EPS)
					out[i][j]=0.0;
			}
		}
		return out;
	}
	
	public static double[][] subtract(double[][] A, double[][] B){
		int rows = A.length;
		int cols = A[0].length;

		double[][] out = new double[rows][cols];
		for(int i = 0; i <rows; i++){
			for (int j = 0; j < cols; j++){
				out[i][j] = A[i][j] - B[i][j];

				if (Math.abs(out[i][j]) < EPS)
						out[i][j]=0.0;
			}
		}
		return out;
	}
	
	public static double[][] multiply(double[][] A, double scalar){
		int rows = A.length;
		int cols = A[0].length;

		double[][] out = new double[rows][cols];
		for(int i = 0; i <rows; i++)
			for (int j = 0; j < cols; j++)
				out[i][j] = A[i][j]*scalar;
		return out;
	}

	/*
	 * Inplace vector operations
	 */

	public static void addInplace(double[][] A, double[][] B){
		for(int i = 0; i <A.length; i++)
			for (int j = 0; j < A[0].length; j++)
				A[i][j] += B[i][j];
	}
	
	public static void subtractInplace(double[][] A, double[][] B){
		for(int i = 0; i < A.length; i++)
			for (int j = 0; j < A[0].length; j++)
				A[i][j] -= B[i][j];
	}
	
	public static void multiplyInplace(double[][] A, double scalar){
		for(int i = 0; i < A.length; i++)
			for (int j = 0; j < A[0].length; j++)
				A[i][j]*=scalar;
	}

	/*
	 * Matrix operations
	 */
	public static double[][] multiply(double[][] A, double[] b){
		return multiply(A,asColumnVector(b));
	}

	public static double[][] multiply(double[] b, double[][] A){
		return multiply(asRowVector(b),A);
	}

	public static double[][] multiply(double[][] A, double[][] B){
		int rows = A.length;
		int cols = B[0].length;
		int n = B.length;
		
		double[][] out = new double[rows][cols];

		for (int i = 0; i< rows; i++){
			for (int j = 0; j < cols; j++){
				for (int k = 0; k < n ; k++)
					out[i][j] += A[i][k]*B[k][j];
				if (Math.abs(out[i][j]) < EPS)
						out[i][j]=0.0;
			}
		}

		return out;
	}

	public static double[][] inverse(double[][] A){
		int n = A.length;
		double[][] augMatrix = concatenate(A,identity(n));
		Solvers.simpleGaussJordanElimination(augMatrix);
		return extract(augMatrix,0,n,n,n);
	}

	public static double[][] transpose(double[][] A){
		double[][] output = new double[A[0].length][A.length];
		for (int i = 0; i < A.length; i++)
			for (int j = 0; j < A[0].length; j++)
				output[j][i] = A[i][j];
		return output;
	}

	public static double det(double[][] A){
		return Solvers.detByElim(A);
	}

	public static double laplaceDeterminant(double[][] A){
		if (A.length == 1)
			return A[0][0];
		
		double determinant = 0.0;
		short sign = 1;
		for (int i = 0; i < A.length; i++){
			determinant += sign*A[0][i]*laplaceDeterminant(getCofactorMatrix(A,0,i));
			sign *= -1;
		}
		return determinant;
	}




	/*
	 * Potentially useful tools: copy and print.
	 ********************************************/

	public static double[][] copy(double[][] array){
		double[][] out = new double[array.length][];
		for(int i = 0 ; i < array.length; i++){
				out[i] = Arrays.copyOf(array[i],array[i].length);
		}
		return out;
	}
	
	public static double[] copy(double[] array){
		return Arrays.copyOf(array,array.length);
	}

	public static double[][] asColumnVector(double[] array){
		double[][] out = new double[array.length][1];
		for(int i = 0; i < array.length; i++)
			out[i][0] = array[i];
		return out;
	}

	public static double[][] asRowVector(double[] array){
		double[][] out = new double[1][];
		out[0] = Arrays.copyOf(array,array.length);
		return out;
	}

	public static double[][] concatenate(double[][] A, double[][] B) {
		int rows = A.length;
		int cols = A[0].length + B[0].length;
		
		double[][] out = new double[rows][cols];

		for(int i = 0; i < rows; i++){
			int offset = A[0].length;
			for (int j = 0; j < offset; j++)
				out[i][j] = A[i][j];
			for (int j = 0; j < B[0].length; j++)
				out[i][j+offset] = B[i][j];
		}
		return out;
	}
	
	public static double[][] concatenate(double[][] A, double[] b) {
		int rows = A.length;
		int cols = A[0].length + 1;
		
		double[][] out = new double[rows][cols];

		for(int i = 0; i < rows; i++){
			int j = 0; 
			while (j < A[0].length){
				out[i][j] = A[i][j];
				j++;
			}
			out[i][j] = b[i];
		}
		return out;
	}

	public static double[][] extract(double[][] A,int iStart,int iCount, int jStart, int jCount){
		double[][] out = new double[iCount][jCount];
		for(int i = 0; i < iCount; i++)
			for (int j= 0; j < jCount; j++)
				out[i][j] = A[iStart+i][jStart+j];
		return out;
	}

	public static double[][] getCofactorMatrix(double[][] A,int i,int j){
		double[][] out = new double[A.length-1][A[0].length-1];
		
		for (int k = 0, l = 0; l < out.length; k++,l++){
			if (k == i)
				k++;
			for (int m = 0, n = 0 ; n < out[0].length; m++,n++){
				if (m == j) 
					m++;
				out[l][n]=A[k][m];
			}
		}
		return out;
	}



	public static void print(double[][] array) {
		System.out.println();
		for (double[] row : array)
			print(row);
		System.out.println();
	}
	
	public static void print(double[] array) {
		System.out.print("[");
		for ( int i = 0; i < array.length; i++)
			System.out.printf("  %2.2e ",array[i]);
		System.out.println("]");
	}

	public static void printGnuplotData( double[] y){
		printGnuplotData(new double[][]{y});
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
	

	/*
	 * Creation of random arrays
	 *******************************************/

/**
 * Produces a random n x (n+1)-matrix that is useful 
 * for testing Gauss Elimination methods.
 *
 * The entries are supposed to follow the Gauss-distribution.
 */
	public static double[][] randomSystem(int n){
		Random r = new Random();
		double[][] array = new double[n][n+1];
		for (int i = 0; i < n; i++)
			for (int j = 0; j < n+1; j++)
				array[i][j] = r.nextGaussian();
		return array;
	}
/**
 * Produces a random n x n-matrix that is well suited
 * for Jacobi iteration and Gauss-Seidel iteration.
 *
 * The random choice is based on Gauss-distributions,
 * but the results are tuned in order to make the diagonal 
 * elements sufficiently dominant.
 */
	public static double[][] randomSquare(int n){
		Random r = new Random();
		double[][] array = new double[n][n];
		for (int i = 0; i < n; i++)
			for (int j = 0; j < n; j++)
				array[i][j] = r.nextGaussian()/(1+(n+1)*Math.pow(i-j,2));
		return array;
	}
	

/**
 * Produces random array of length n.
 *
 * The elements are supposed to follow the Gauss-distribution.
 */
	public static double[] randomArray(int n){
		Random r = new Random();
		double[] array = new double[n];
		for (int i = 0; i < n; i++)
			array[i] = r.nextGaussian();
		return array;
	}


	/*
	 * Create special matrices:
	 */

	public static double[][] identity(int n){
		double[][] out = new double[n][n];
		for(int i = 0;i < n ; i++)
			out[i][i] = 1.0;
		return out;
	}

	public static double[] elementaryVector(int n,int i){
		double[] out = new double[n];
		out[i]=1;
		return out;
	}
}
