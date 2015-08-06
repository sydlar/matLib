package matLib.polar;

import matLib.Vector;
import matLib.DimensionError;

public class CylindricalPolarPoint {
	double r;
	double theta;
	double z;

	public CylindricalPolarPoint(double theR, double theTheta,double theZ){
		r = theR;
		theta = theTheta;
		z = theZ;
	}

	public CylindricalPolarPoint(Vector v){
		if ( v.size() != 3)
			throw new DimensionError("PolarPoint");
		// TODO
	}

	public boolean equals(Object other){
		if (!(other instanceof CylindricalPolarPoint))
			return false;

		return false; // TODO
	}

	public CylindricalPolarPoint canonicalForm(){
		return null; // TODO
	}

	public Vector getVector(){
		return null; //TODO
	}
}
