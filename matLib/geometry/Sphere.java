package matLib.geometry;

import matLib.Vector;
import matLib.util.Vectors;

public class Sphere {

	private Point c;
	private double r;

	public Sphere(Point centre, double radius){
		c = centre;
		r = radius;
	}

	public boolean isOutside(Point p){
		return false; // TODO
	}
	
	public Point stdParametrization(double t){
		return null; // TODO
	}

	/*
	 * VARIOUS TOOLS
	 */
	
	public String toString(){
		StringBuilder buffer = new StringBuilder();
		buffer.append("\n*** SPHERE ***");
		buffer.append("\n*   CENTRE = "+c);
		buffer.append("\n*   RADIUS = " +r);
		buffer.append("\n****************\n");
		return buffer.toString();
	}


}
