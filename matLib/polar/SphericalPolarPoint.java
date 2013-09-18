package matLib.polar;

import matLib.Vector;
import matLib.DimensionError;

public class SphericalPolarPoint {
	double r;
	double theta;
	double phi;

	public SphericalPolarPoint(double theR, double theTheta,double thePhi){
		r = theR;
		theta = theTheta;
		phi = thePhi;
	}

	public SphericalPolarPoint(Vector v){
		if ( v.size() != 3)
			throw new DimensionError("PolarPoint");
		// TODO
	}

	public boolean equals(Object other){
		if (!(other instanceof SphericalPolarPoint))
			return false;

		return false; // TODO
	}

	public SphericalPolarPoint canonicalForm(){
		return null;//TODO
	}
	
	public Vector getVector(){
		return null;// TODO
	}
}
