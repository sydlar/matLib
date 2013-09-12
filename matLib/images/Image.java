package matLib.images;

import matLib.AbstractVector;
import matLib.Vector;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Image extends Vector {

	protected int width;
	protected int height;


	public Image(double[] array, int hSize, int vSize){
		super(hSize*vSize*3);
		for(int i = 0 ; i < size() || i < array.length; i++)
			this.set(i,array[i]);
		width = hSize;
		height = vSize;
	}
	
	public Image(AbstractVector vector, int h, int v){
		this(vector.toArray(),h,v);
	}

	public void writeToFile(String fileName){
		this.trim();

		BufferedImage image = new BufferedImage(width,height,BufferedImage.TYPE_INT_RGB);

		int r,g,b;
		int[] rgb = new int[height*width*3];

		for(int i = 0; i < size() && i < width*height; i++){ 
			r = (int) get(3*i+RED) + INTENSITY_SHIFT;
			g = (int) get(3*i+GREEN) + INTENSITY_SHIFT;
			b = (int) get(3*i+BLUE) + INTENSITY_SHIFT;
			rgb[i] = (r << 16) | (g << 8) | b;	
		}

		image.setRGB(0,0,width,height,rgb,0,width);

		try{ImageIO.write(image,"png",new File(fileName+".png"));} 
		catch (IOException e) {e.printStackTrace();
		}
	}

	public int getWidth(){
		return width;
	}
	
	public int getHeight(){
		return height;
	}
	
	private void trim(){
		for(int i = 0 ; i < size(); i++)
			data[i] = cut(data[i],127,-127);
	}
	
	/*
	 * STATIC METHODS
	 *
	 */
	
	public static Image getImage(String fileName){
		int[] dims = new int[2];
		double[] buffer = Image.readFromFile(fileName,dims);
		System.out.println(" "+dims[0]+"x"+dims[1]);
		return new Image(buffer,dims[0],dims[1]);
	}



	/*
	 *
	 * IMAGE MANIPULATION
	 *
	 */

	public void convertToGrayscale(){
		for(int i = 0; i < size(); i+=3){
			double average = (get(i+RED)+get(i+GREEN)+get(i+BLUE))/4;
			set(i,average);
			set(i+1,average);
			set(i+2,average);	
		}
	}


	/*
	 * CORE FUNCTIONS:
	 */
	private static final int RED = 0;
	private static final int GREEN = 1;
	private static final int BLUE = 2;
	
	private static final int INTENSITY_SHIFT = 127;

	public static double[] readFromFile(String fileName,int[] dimensions){

		BufferedImage image = null;
		int width = 0;
		int height = 0;

		try {	
			image = ImageIO.read(new File(fileName));
			width = image.getWidth();
			height = image.getHeight();
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}

		int[] rgb = image.getRGB(0,0,width,height,null,0,width);

		double[] out = new double[width*height*3];

		for (int i = 0; i < out.length; i++)
			out[i] = getColorIntensity(rgb[i/3],i%3) - INTENSITY_SHIFT;

		if (dimensions != null && dimensions.length > 1){
			dimensions[0] = width;
			dimensions[1] = height;
		}
		return out;
	}

	public static void dumpVectorToImage(double[] data, int width, int height, String fileName){
		BufferedImage image = new BufferedImage(width,height,BufferedImage.TYPE_INT_RGB);
		int r,g,b;
		int[] rgb = new int[height*width*10];
		for(int i = 0; i < data.length && i < width*height; i++){ 
			r = (int) data[3*i+RED] + INTENSITY_SHIFT;
			g = (int) data[3*i+GREEN] + INTENSITY_SHIFT;
			b = (int) data[3*i+BLUE] + INTENSITY_SHIFT;
			rgb[i] = (r << 16) | (g << 8) | b;	
		}

		image.setRGB(0,0,width,height,rgb,0,width);

		try{
		ImageIO.write(image,"png",new File(fileName+".png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}


	private static double getColorIntensity(int color, int RGB){
		color >>= 8*(2-RGB);
		color &= ~((color >> 8)<<8);
		return (double) (color);
	}


	/*
	 * Methods intended for pixelwise image operations.
	 */
	
	public static double powerFilter(double x, double exponent){ // To make image brighter or darker
		return 255*Math.pow((x+INTENSITY_SHIFT)/255.0,exponent)-INTENSITY_SHIFT;
	}

	public static double inversionFilter(double x){ // To make the negative.
		return 255-x;
	}
			

	public static double sinusFilter(double x, int scale){ // To make it crazy
		return 255*Math.sin(x/scale);
	}

	public static double segmentFilter(double x,int n){ // To reduce the number of intensity levels
		double segmentLength = 255.0/n;
		double out = 0.0;
		for(int i = 0; i < n; i++)
			out += cut(x,(i+1)*segmentLength,i*segmentLength);
		return out;
	}

	public static double cutoffFilter(double x, double cutoff){ // Hardly useful?
		return x > cutoff ? cutoff : x;
	}

	private static double cut(double x, double upper, double lower){ // To adjust the range of values. Typically, we don't want intensity values above or below certain levels.
		return x >= upper ? upper  : (x <= lower ? lower : x);
	}
}
