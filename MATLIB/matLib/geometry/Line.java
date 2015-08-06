package matLib.geometry;

import matLib.Vector;
import matLib.util.Vectors;

public class Line {

	private Vector n;
	private double d;

	public Line(Vector theN, double theD){
		n = Vector.multiply(theN,1.0/theN.norm());
		d = theD/theN.norm();
	}

	public Line(Point p, Vector theN){
		this(theN,0.0); // TODO
	}

	public boolean isAbove(Point p){
		return false; // TODO
	}
	
	public static Line perpendicularBisector(Point p, Point q){
		return new Line(p,Point.displacementVector(p,q));
	}

	public static Line lineAlongVector(Point p, Vector v){
		return new Line(p,perpendicularVector(v));
	}

	/*
	 * VARIOUS TOOLS
	 */
	
	public String toString(){
		StringBuilder buffer = new StringBuilder();
		buffer.append("\n*** LINE ***");
		buffer.append("\n*   NORMAL VECTOR = "+n);
		buffer.append("\n*   DISTANCE_TO_ORIGIN = " +d);
		buffer.append("\n****************\n");
		return buffer.toString();
	}

	public static Vector perpendicularVector(Vector v){
		return new Vector(-v.get(1),v.get(0));
	}

}
