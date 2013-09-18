package matLib.polar;

import matLib.Vector;
import matLib.DimensionError;

public class PolarPoint {
	double r;
	double theta;

	public PolarPoint(double theR, double theTheta){
		r = theR;
		theta = theTheta;
	}

	public PolarPoint(Vector v){
		if ( v.size() != 2)
			throw new DimensionError("PolarPoint");
		// TODO
	}

	public boolean equals(PolarPoint other){
		return false; // TODO
	}

	public PolarPoint canonicalForm(){
		return null; // TODO
	}

	public Vector getVector(){
		return null;// TODO
	}
}
