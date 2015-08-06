package matLib.geometry;

import matLib.Vector;
import matLib.util.Vectors;

public class Plane {

	private Vector n;
	private double d;

	public Plane(Vector theN, double theD){
		n = Vector.multiply(theN,1.0/theN.norm());
		d = theD/theN.norm();
	}

	public Plane(Point p, Vector theN){
		this(theN,0.0); // TODO
	}

	public boolean isAbove(Point p){
		return false; // TODO
	}
	
	public static Plane perpendicularBisector(Point p, Point q){
		return new Plane(p,Point.displacementVector(p,q));
	}

	public static Plane planeThrough(Point p1, Point p2, Point p3){
		return null;//Todo
	}

	/*
	 * VARIOUS TOOLS
	 */
	
	public String toString(){
		StringBuilder buffer = new StringBuilder();
		buffer.append("\n*** PLANE ***");
		buffer.append("\n*   NORMAL VECTOR = "+n);
		buffer.append("\n*   DISTANCE_TO_ORIGIN = " +d);
		buffer.append("\n****************\n");
		return buffer.toString();
	}


}
