package matLib.images;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Images {

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


	public static void convertToGrayscale(double[] image){
		for(int i = 0; i < image.length; i+=3){
			double average = (image[i+RED]+2*image[i+GREEN]+image[i+BLUE])/4;
			image[i] = average;
			image[i+1] = average;
			image[i+2] = average;
		}
	}

}

