package matLib.test;

import matLib.fourier.Fourier2D;
import matLib.images.Image;
import matLib.util.Matrices;
import matLib.AbstractVector;
import matLib.Vector;

public class ImageTest {

//	private static final String FILE = "data/test.png";
	private static final String FILE = "IMG_7615.JPG";

	private static final int N = 40;

	public static void main(String[] args){
		
		Image img = Image.getImage(FILE);


		//FOURIER ANALYSIS:
		Fourier2D TMP = new Fourier2D(img.getWidth(),img.getHeight(),0);	
		TMP.setWaveNumbers(1,1);
		double multiplicator = 1.0/AbstractVector.scalarProduct(TMP,TMP);
		

		double[][][] coefficients = new double[3][N][N];

		for (int color = 0; color < 3; color ++){
			TMP.setColorChannel(color);
			for (int i = 0; i < N; i++){
				for (int j = 0; j < N; j++){
					TMP.setWaveNumbers(i,j);
					coefficients[color][i][j] = AbstractVector.scalarProduct(TMP,img)*multiplicator;
				}
			}
		}
	
		//RECONSTRUCTION
		Vector output = new Vector(img.size());
		Vector tmp = null;
		for (int color = 0; color < 3; color ++){
			TMP.setColorChannel(color);
			for (int i = 0; i < N; i++){
				for (int j = 0; j < N; j++){
					TMP.setWaveNumbers(i,j);
					tmp = new Vector(TMP);
					tmp.multiply(coefficients[color][i][j]);
					output.add(tmp);
				}
			}
		}
	
		Image outputImage = new Image(output,img.getWidth(), img.getHeight());

		//outputImage.trim();

		outputImage.writeToFile("imageTestOutput");
		
	}

	private static double powerFilter(double x, double exponent){
		return 255.0*Math.pow(x/255.0,exponent);
	}

	private static double inversionFilter(double x){
		return 255-x;
	}
			

	public static double sinusFilter(double x, int scale){
		return 255*Math.sin(x/scale);
	}
	private static double segmentFilter(double x,int n){
		double segmentLength = 255.0/n;
		double out = 0.0;
		for(int i = 0; i < n; i++)
			out += cut(x,(i+1)*segmentLength,i*segmentLength);
		return out;
	}

	private static double cutoffFilter(double x, double cutoff){
		return x > cutoff ? cutoff : x;
	}

	private static double cut(double x, double upper, double lower){
		return x >= upper ? 0  : (x < lower ? 0 : lower);

	}
}
