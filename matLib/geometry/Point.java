package matLib.geometry;

import matLib.Vector;
import matLib.util.Vectors;

public class Point {
	protected double[] data;
	protected int size;

	public Point(double[] array){
		data = array;
		size = array.length;
	}

	public Point(double x, double y){
		this(new double[]{x,y});
	}

	public Point(double x, double y,double z){
		this(new double[]{x,y,z});
	}

	public Point(Vector v){
		this (v.toArray());
	}

	public Vector positionVector(){
		return new Vector(data);
	}

	public static Vector displacementVector(Point source, Point target) {
		double[] displacement = Vectors.subtract(target.data,source.data);
		return new Vector(displacement);
	}

	/*
	 * VARIOUS TOOLS
	 */
	public String toString(){
		StringBuilder buffer = new StringBuilder();
		buffer.append("(");
		for(int i = 0; i < data.length; i++)
			buffer.append(" "+data[i]+" ");
		buffer.append(")");
		return buffer.toString();
	}
}
