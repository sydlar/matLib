package matLib.functions;

public abstract class Function {

	public Function add(Function other){
	return new Sum(this,other);
	}

	public Function multiply(double scalar) {
		return new Multiple(this,scalar);	
	};

	public Function subtract(Function other) {
		return this.add(other.multiply(-1.0));
	}

	public abstract double evaluate(double x);
}
