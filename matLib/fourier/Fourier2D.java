package matLib.fourier;

import matLib.AbstractVector;

public class Fourier2D extends AbstractVector {
	
	public static int RED = 0;
	public static int GREEN = 1;
	public static int BLUE = 2;

	private int width;
	private int height;
	private int colorChannel;

	private Fourier horizontalFunction;
	private Fourier verticalFunction;


	public Fourier2D(int w, int h){
		this(w,h,0);
	}

	public Fourier2D(int theWidth,int theHeight,int theColorChannel){
		width = theWidth;
		height = theHeight;
		colorChannel=theColorChannel;		
		
		horizontalFunction = new Fourier(width,0);
		verticalFunction = new Fourier(height,0);
	}
	
	public Fourier2D(int theWidth,int theHeight, int horizontalWaveNumber, int verticalWaveNumber, int theColorChannel){
		this(theWidth,theHeight,theColorChannel);
		this.setWaveNumbers(horizontalWaveNumber,verticalWaveNumber);
	}

	public double get(int i){
		if ( i%3!=colorChannel )
			return 0.0;
		int horizontalPosition = (i/3) % width;
		int verticalPosition = (i/3) / width;

		return  127.0*(horizontalFunction.get(horizontalPosition)*verticalFunction.get(verticalPosition));
	}

	public void set(int i, double value){
		throw new UnsupportedOperationException("Fourier2D");
	}
	
	public void setWaveNumbers(int horizontalWaveNumber,int verticalWaveNumber){
		horizontalFunction.setIndex(horizontalWaveNumber);
		verticalFunction.setIndex(verticalWaveNumber);
	}

	public void setColorChannel(int color){
		colorChannel = color % 3;
	}

	public int size(){
		return width*height*3;
	}



}
