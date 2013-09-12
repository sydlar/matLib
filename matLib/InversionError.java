package matLib;

public class InversionError extends UnsupportedOperationException {
	InversionError(String operation){
		super("Non-invertible matrix in "+operation+".");
	}
}
