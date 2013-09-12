package matLib.test;

import matLib.*;
import matLib.util.*;

public class Test {
	public static void main(String[] args){
		Matrix A = new Matrix(3,3);

		Matrix B = new Matrix(new double[][]{
			{1,0,0},
			{0,1,1},
			{0,0,1}
		});

		double[][] arrayMatrix = new double[][]{
			{1,0,1.01},
			{0,1,1},
			{100,0,1}
		};

		Matrix C = new Matrix(arrayMatrix);

		Matrix D = new Matrix(new double[][]{
			{0,1,1},
			{-1,0,1}
		});



		AbstractVector v = new ColumnVector(new double[]{ 1,2});
		AbstractVector w = new RowVector(new double[]{ 2,-1.0});

		System.out.println(AbstractVector.multiply(AbstractVector.add(B,C),.01));

		System.out.println(v);

		double[] array = w.toArray();
		for (int i = 0; i < array.length; System.out.println(array[i++]));

		double[][] arrayMatrixII = new double[][]{
			{1,2,-3,6,3},
			{1,-1,4,1,4},
			{1,-1,1,3,5},
		};
		System.out.println(new Matrix(arrayMatrixII));
		System.out.println(Solvers.simpleGaussJordanElimination(arrayMatrixII));
		System.out.println(new Matrix(arrayMatrixII));

		System.out.println("====================");

		double[][] arrayMatrixIII = new double[][]{
			{1,2,-3,6,2},
			{1,2,4,1,3},
			{1,2,1,3,4},
		};
		
		System.out.println(Solvers.scaledGaussJordanElimination(arrayMatrixIII));

		System.out.println(new Matrix(arrayMatrixIII));

	}
}
