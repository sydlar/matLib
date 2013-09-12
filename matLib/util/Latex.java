package matLib.util;

public class Latex {
	public static void printMatrix(double[][] A){
		System.out.println("\\begin{bmatrix}");
		for(int row = 0; row < A.length; row++){
			for(int col = 0; col < A[0].length - 1; col++){
				System.out.printf(" %f &",A[row][col]);
			}
			System.out.printf(" %f \\cr%n",A[row][A[0].length -1]);
		}
		System.out.println("\\end{bmatrix}");
	}
}
