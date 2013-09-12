package matLib.util;

import java.util.Arrays;

public class Solvers {

	/*
	 * Input: Coefficient matrix of linear system (the augemented matrix)
	 * Performs in-place GaussJordanElimination
	 * Output: true or false depending on sucess or failure.
	 */
	public static boolean simpleGaussJordanElimination(double[][] matrix){
		int rows = matrix.length;

		for ( int row = 0; row < rows; row++){
			for (int i = 0; i < rows; i ++){
				if ( i == row)
					continue;
				eliminateVariableFromRow(matrix,row,i);
			}
		}
		normalizeRows(matrix);
		return true;
	}

	private static void eliminateVariableFromRow(double[][] matrix, int constantRow, int changingRow) {
		double multiplicator = matrix[changingRow][constantRow] / matrix[constantRow][constantRow];
		applyRow(matrix, constantRow,changingRow,multiplicator);
	}

	private static void applyRow(double[][] matrix, int row, int changingRow, double multiplicator){
		for (int j = 0; j < matrix[0].length; j++)
			matrix[changingRow][j] -= multiplicator*matrix[row][j];
	}

	private static void normalizeRows(double[][] matrix){
		for (int i = 0; i < matrix.length; i++){
			double multiplicator = 1.0/matrix[i][i];
			for (int j = 0; j < matrix[0].length; j++)
				matrix[i][j]*=multiplicator;
		}
	}


	/*
	 * Input: Coefficient matrix of linear system (the augemented matrix)
	 * Performs in-place GaussJordanElimination
	 * Output: true or false depending on sucess or failure.
	 */
	public static boolean scaledGaussJordanElimination(double[][] matrix){
		ScaledGaussJordanEliminator G = new ScaledGaussJordanEliminator(matrix);
		G.eliminate();
		G.normalizeRows();
		return G.success;
	}


	/*
	 * Input: Matrix.
	 * Output: Determinant.
	 *
	 * Method: 
	 * (i) Scaled Gauss-Jordan elimination without rescaling of rows. 
	 * (ii) Multiplication of diagonal elements.
	 *
	 */
	public static double detByElim(double[][] matrix){
		ScaledGaussJordanEliminator G = new ScaledGaussJordanEliminator(matrix);
		G.eliminate();
		if (!G.success)
			return 0.0;
		
		double result = 1.0;
		for(int i = 0; i < matrix.length; i++)
			result*=matrix[i][i];
		return result;
	}

	/*
	 *
	 * Solves Ax=b by Gauss-Seidel iteration. 
	 * NOTE: Convergence is NOT guaranteed at all.
	 *
	 * Input: 
	 *  - Matrix (double[][]) A
	 *  - Vector (double[]) b
	 *  - Vector (double[]) x: Solution. The iteration takes place in-place in x.
	 *
	 *  Output: true or false depending on sucess or failure.
	 *
	 */
	public static boolean gaussSeidel(double[][] A, double[] b, double[] x) {

			try {
				GaussSeidel solver = new GaussSeidel(A,b,x);
				solver.solve();
				return true;
			} catch (GSException e){
				System.out.println(e);
				e.printStackTrace();
				return false;
			}
	}
}
	

class GaussJordanEliminator {
	static final int EPS = (int)1e-9;
	static final int INVALID_VARIABLE = -1;
	
	double[][] matrix;
	int rows,cols;
	boolean success;
	
	public GaussJordanEliminator(double[][] theMatrix){
		matrix = theMatrix;
		rows = matrix.length;
		cols = matrix[0].length;
		success = false;
	}

	public void eliminate(){
		success = true;
		for(int i = 0; i < cols;  i++){
			eliminateVariable(i);
		}
		rearrange();
	}


	void eliminateVariable(int var ){
		int row = chooseRow(var);
		if (row == INVALID_VARIABLE)
			return;
		
		for(int j = 0; j < rows; j++){
			if (j == row)
				continue;
			try {
				eliminateVariableBetweenRows(var,row,j);
			} catch (GJException e){
				success = false;
			}
		}
	}

	void eliminateVariableBetweenRows(int var, int invariantRow, int changingRow){
		double multiplicator = matrix[changingRow][var]/matrix[invariantRow][var];
		testValidity(multiplicator);

		for(int i = 0; i < cols; i++){
			matrix[changingRow][i] = matrix[changingRow][i] - matrix[invariantRow][i]*multiplicator;
		}
		matrix[changingRow][var] = 0.0; // to prevent roundoff errors here.
	}

	void normalizeRows(){
		for(int i = 0; i < rows; i++){
			normalizeRow(i);
		}
	}

	void normalizeRow(int row){
		double multiplicator = 0.0;
		for(int i = 0; i < cols; i++){
			if (multiplicator == 0.0) {
				if(Math.abs(matrix[row][i]) > EPS){
					multiplicator = 1.0/matrix[row][i];
					matrix[row][i] = 1.0;
				}
			} else {
				matrix[row][i] *= multiplicator;
			}

		}
	}
	
	void rearrange(){
		return;
	}

	int chooseRow(int var){
		if(var >= rows)
			return INVALID_VARIABLE; 
		return var;
	}

	static void testValidity(double d){
		if ( d == Double.NaN || d == Double.POSITIVE_INFINITY || d == Double.NEGATIVE_INFINITY)
			throw new GJException();
	}
}



class ScaledGaussJordanEliminator extends GaussJordanEliminator{
	boolean[] rowIsUsed;
	int[] permutationRecord;

	ScaledGaussJordanEliminator(double[][] array){
		super(array);
		rowIsUsed = new boolean[rows];
		permutationRecord = new int[cols];
	}

	int chooseRow(int var){
		int bestOption = 0;
		double bestScore = 0.0, currentScore = 0.0;

		for (int i = 0; i < rows ; i++){
			if(rowIsUsed[i])
				continue;

			currentScore = scalingScore(i,var);

			if (currentScore > bestScore && currentScore !=Double.POSITIVE_INFINITY){
				bestOption = i;
				bestScore = currentScore;
			}
		}
		if (bestScore == 0.0)
			return INVALID_VARIABLE;

		rowIsUsed[bestOption] = true;
		permutationRecord[var] = bestOption;
		return bestOption;
	}


	double scalingScore(int row,int var){
		if (Math.abs(matrix[row][var]) < EPS)
			return 0.0;

		double maxValue = 0.0, currentValue =0.0;

		for (int i = 0; i < cols ; i ++){
			currentValue = Math.abs( matrix[row][i]);

			if (currentValue > maxValue)
				maxValue = currentValue;
		}
		return Math.abs(matrix[row][var]/maxValue); // størrelsen til foreslått pivotelement, relativt til største elementet i raden.
	}

	void rearrange(){//Brutal algoritm: Uses a copy of the matrix.
		double[][] tmpArray = new double[rows][cols];
		for(int i = 0; i < rows ; i++)
			tmpArray[i] = Arrays.copyOf(matrix[i],cols);

		for(int var = 0; var < cols && var < rows ; var++) {
			matrix[var] = Arrays.copyOf(tmpArray[permutationRecord[var]],cols);
		}
	}
}





class GaussSeidel {
	private static final double EPS = 1e-6;
	private static final double TOL = 1e-6;

	double[][] A;
	double[] b;
	double[] x;
	double[] AdiagInv;

	double errorIndicator = Double.POSITIVE_INFINITY; // This is NOT a valid error estimate.

	int n;
	boolean success = false;
	int count = 0;

	GaussSeidel(double[][] theA, double[] theB, double[] theX){
		A = theA;
		b = theB;
		x = theX;
		n = b.length;
		testSize();
		AdiagInv = new double[n];
		calculateInverseDiagonalElements();
	}

	void simpleStep(){
		for (int i = 0; i < n ; i++){
			x[i] = b[i];
			for (int j = 0 ;j < i ; j++){
				x[i] -= A[i][j]*x[j];
			}
			for (int j = i+1 ;j < n ; j++){
				x[i] -= A[i][j]*x[j];
			}
			x[i]*=AdiagInv[i];
		}
		count++;
	}

	void stepWithTest(){
		double accumulator;
		double error = 0;
		success = true; // A hypothesis to test.
		for (int i = 0; i < n ; i++){
			accumulator = b[i];
			for ( int j = 0 ; j < n ; j++){
				accumulator -= A[i][j]*x[j];
			}
			x[i] = AdiagInv[i]*(accumulator + A[i][i]*x[i]);
			accumulator = Math.abs(accumulator);
			if ( accumulator > TOL)
				success = false;
			error += accumulator;
		}
		if ( error > 10*errorIndicator || error == Double.POSITIVE_INFINITY || error == Double.NaN) {
			System.out.printf("ERROR %E after %d iterations%n%n",error,count);
			throw new GSException.Convergence();
		}
		errorIndicator = error;
		count++;
	}

	void solve(){
		while(!success){
			for ( int i = 0; i < 0; i++)
				simpleStep();
			stepWithTest();
		}
	}

	private void testSize(){
		if (A.length != n || x.length != n)
			throw new GSException.Dimensions();
		for(int i = 0; i < n ; i++) {
			if (A[i].length != n)
				throw new GSException.Dimensions();
		}
	}

	private void calculateInverseDiagonalElements(){
		for(int i = 0 ; i < n ; i++){
			if (Math.abs(A[i][i]) < EPS)
				throw new GSException.Convergence();
			else
				AdiagInv[i] = 1/A[i][i];
		}
	}


	
}


class GJException extends RuntimeException {
	GJException(){
		super("Unsuccessful Gauss-elimination");
	}
}

class GSException extends RuntimeException {
	
	GSException(String message){
		super("Unsuccessful Gauss-Seidel-iteration: " + message);
	}

	GSException(){
		super("Unsuccessful Gauss-Seidel-iteration.");
	}

	static class Convergence extends GSException {
		Convergence(){
			super("Method does not converge");
		}
	}
	
	static class Dimensions extends GSException {
		Dimensions(){
			super("Incompatible dimensions");
		}
	}
}
