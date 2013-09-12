package matLib.geometry;

import matLib.Vector;

public class Triangle {

	private Point[] vertices = null;
	private Vector[] edges = null;
	private double[] angles = null;
	private double[] sides = null;

	public Triangle(Point p1, Point p2, Point p3){
		vertices = new Point[]{p1,p2,p3};
	}


	public Point getPoint(int i){
		return vertices[i%3];
	}

	public Vector getEdge(int i){
		if (edges == null)
			calculateEdges();
		return edges[i%3];
	}

	public double getAngle(int i){
		if (angles == null)
			calculateAngles();
		return angles[i%3];
	}

	public double getSideLength(int i ){
		if (sides == null)
			calculateSides();
		return sides[i%3];
	}

	private void calculateEdges(){
		//TODO
		return;
	}

	private void calculateAngles(){
		//TODO
		return;
	}
	
	private void calculateSides(){
		//TODO
		return;
	}

	public Vector getUnitNormalVector(){
		//TODO
		return null;
	}

	public double area(){
		return 0.0;
	}

	private boolean isAboveTriangle(Point p){
		//TODO
		return false;
	}


	/*
	 * VARIOUS TOOLS
	 */
	
	public String toString(){
		StringBuilder buffer = new StringBuilder();
		buffer.append("\n*** TRIANGLE ***\n*   POINTS = ");
		for (Point p: vertices)
			buffer.append(" "+p+" ");
		buffer.append("\n*   AREA = "+area());
		buffer.append("\n*   NORMAL VECTOR = "+getUnitNormalVector());
		buffer.append("\n****************\n");
		return buffer.toString();
	}

}
