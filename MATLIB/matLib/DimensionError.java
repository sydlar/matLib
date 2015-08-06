package matLib;

public class DimensionError extends UnsupportedOperationException {
 	public DimensionError(String operation){
		super("Incompatible matrix dimensions in "+operation+".");
	}
}
