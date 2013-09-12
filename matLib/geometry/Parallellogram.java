package matLib.geometry;

import matLib.Vector;

public class Parallellogram {

	private Point vertex;
	private Vector firstEdge;
	private Vector secondEdge;

	public Parallellogram(Point p, Vector v1, Vector v2){
		vertex = p;
		firstEdge = v1;
		secondEdge = v2;
	}


	public Point getPoint(int i){
		// The corners can be labeled from 0 to 3
		return null;
	}

	public Vector getUnitNormalVector(){
		//TODO
		return null;
	}

	public double area(){
		return 0.0;
	}

	private boolean isAbove(Point p){
		//TODO
		return false;
	}


	/*
	 * VARIOUS TOOLS
	 */
	
	public String toString(){
		StringBuilder buffer = new StringBuilder();
		buffer.append("\n*** PARALLELLOGRAM ***\n*   POINTS = ");
		for (int i = 0; i < 4; i++)
			buffer.append(" "+getPoint(i)+" ");
		buffer.append("\n*   AREA = "+area());
		buffer.append("\n*   NORMAL VECTOR = "+getUnitNormalVector());
		buffer.append("\n****************\n");
		return buffer.toString();
	}

}
