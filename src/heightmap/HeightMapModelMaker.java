package heightmap;

import java.awt.image.BufferedImage;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import javax.imageio.ImageIO;

public class HeightMapModelMaker {

	//Returns a BufferedImage loaded from a file at a URL.
	public static BufferedImage loadImage(String url) {
		//System.out.println("Trying to load image.");
		try {
			return ImageIO.read(new File(url));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static void writeToFile(String s, String file) {
		try {
			BufferedWriter writer = new BufferedWriter(new FileWriter(file, true));
			writer.write(s);
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void flushFile(String file) {
		try {
			BufferedWriter writer = new BufferedWriter(new FileWriter(file, false));
			writer.write("");
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// Calculates the mid-point colour at a certain between two colours linearly. Each channel is manually interpolated.
	// Input: Two ints, representing colours and a double, representing where between the inputs the desired colour lies (between 0 and 1, 0.5 is exact mid-point)
	// Output: The resulting colour
	public static int interpolCol(int col1, int col2, double ratio) {
		if (col1 == col2) {
			return col1;
		}
		double iRatio = 1 - ratio;
		int a1 = (col1 >> 24) & 0xFF;
		int r1 = (col1 >> 16) & 0xFF;
		int g1 = (col1 >> 8) & 0xFF;
		int b1 = col1 & 0xFF;
		int a2 = (col2 >> 24) & 0xFF;
		int r2 = (col2 >> 16) & 0xFF;
		int g2 = (col2 >> 8) & 0xFF;
		int b2 = col2 & 0xFF;
		int aF = (int) Math.round(a1 * iRatio + a2 * ratio);
		int rF = (int) Math.round(r1 * iRatio + r2 * ratio);
		int gF = (int) Math.round(g1 * iRatio + g2 * ratio);
		int bF = (int) Math.round(b1 * iRatio + b2 * ratio);
		return aF << 24 | rF << 16 | gF << 8 | bF;
	}

	public static int getAvgCol(BufferedImage in, double x, double y) {
		if (Math.ceil(y) >= in .getHeight()) {
			return interpolCol(interpolCol( in .getRGB((int) Math.floor(x), (int) Math.floor(y)), in .getRGB((int) Math.ceil(x) % in .getWidth(), (int) Math.floor(y)), x % 1), interpolCol( in .getRGB((int) Math.floor(x), (int) Math.floor(y)), in .getRGB((int) Math.ceil(x) % in .getWidth(), (int) Math.floor(y)), x % 1), y % 1);
		}
		return interpolCol(interpolCol( in .getRGB((int) Math.floor(x), (int) Math.floor(y)), in .getRGB((int) Math.ceil(x) % in .getWidth(), (int) Math.floor(y)), x % 1), interpolCol( in .getRGB((int) Math.floor(x), (int) Math.ceil(y)), in .getRGB((int) Math.ceil(x) % in .getWidth(), (int) Math.ceil(y)), x % 1), y % 1);
	}

	public static void main(String[] args) throws Exception {
		flushFile(args[4]);
		BufferedImage input = loadImage(args[0]);
		//System.out.println("Image Loaded");
		if(input.getWidth() != input.getHeight()){
			throw new Exception("Image not square!");
		}
		double limitLow = Double.valueOf(args[1]);
		double limitHigh = Double.valueOf(args[2]);
		double sideLength = Double.valueOf(args[3]);
		double scaleFactor = sideLength/input.getHeight();
		//System.out.println("Generating verticies.");
		for(double vert = 0; vert < input.getHeight(); vert+=7d/8d) {
			for(double hort = vert%(14d/8d)==0?0:0.5; vert%(14d/8d)==0?hort<input.getWidth():hort<input.getWidth()-1; hort++) {
				//out += "v "+hort*scaleFactor+" "+(limitLow+(limitHigh-limitLow)*(getAvgCol(input, hort, vert)&0xFF)/255d)+" "+vert*scaleFactor+"\n";
				writeToFile("v "+hort*scaleFactor+" "+(limitLow+(limitHigh-limitLow)*(getAvgCol(input, hort, vert)&0xFF)/255d)+" "+vert*scaleFactor+"\n", args[4]);
			}
		}

		//System.out.println("Verticies generated. Making faces.");
		writeToFile("\n", args[4]);

		for(int vert = 0; vert < 8d/7d*(input.getHeight()); vert++) {
			for(int hort = 1; vert%2==0?hort<input.getWidth():hort<input.getWidth(); hort++) {
				if(vert%2==0) { 
					//out += "f "+(vert*input.getWidth()-vert/2+hort+1)+" "+(vert*input.getWidth()-vert/2+hort)+" "+((vert+1)*input.getWidth()-vert/2+hort)+"\n";
					//if(hort < input.getWidth()-1) out += "f "+((vert+1)*input.getWidth()-vert/2+hort)+" "+((vert+1)*input.getWidth()-vert/2+hort+1)+" "+(vert*input.getWidth()-vert/2+hort+1)+"\n";
					writeToFile("f "+(vert*input.getWidth()-vert/2+hort+1)+" "+(vert*input.getWidth()-vert/2+hort)+" "+((vert+1)*input.getWidth()-vert/2+hort)+"\n", args[4]);
					if(hort < input.getWidth()-1) writeToFile("f "+((vert+1)*input.getWidth()-vert/2+hort)+" "+((vert+1)*input.getWidth()-vert/2+hort+1)+" "+(vert*input.getWidth()-vert/2+hort+1)+"\n", args[4]);
				}else {
					//if(hort < input.getWidth()-1) out += "f "+(vert*input.getWidth()-vert/2+hort+1)+" "+(vert*input.getWidth()-vert/2+hort)+" "+((vert+1)*input.getWidth()-vert/2+hort)+"\n";
					//out += "f "+((vert+1)*input.getWidth()-vert/2+hort-1)+" "+((vert+1)*input.getWidth()-vert/2+hort)+" "+(vert*input.getWidth()-vert/2+hort)+"\n";
					if(hort < input.getWidth()-1) writeToFile("f "+(vert*input.getWidth()-vert/2+hort+1)+" "+(vert*input.getWidth()-vert/2+hort)+" "+((vert+1)*input.getWidth()-vert/2+hort)+"\n", args[4]);
					writeToFile("f "+((vert+1)*input.getWidth()-vert/2+hort-1)+" "+((vert+1)*input.getWidth()-vert/2+hort)+" "+(vert*input.getWidth()-vert/2+hort)+"\n", args[4]);

				}
			}
		}
		//System.out.println("Faces made. You can now close the console.");
	}
}
