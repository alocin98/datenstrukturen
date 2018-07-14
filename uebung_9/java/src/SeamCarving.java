import java.awt.*;
import java.awt.image.*;
import java.io.File;
import java.util.Arrays;

import javax.imageio.ImageIO;

public class SeamCarving {

	/**
	 * Computes a table that, for each pixel, stores the smallest cost for
	 * a vertical seam that goes through that pixel. Uses dynamic programming
	 * by stepping through all rows from top to bottom of the image. The cost
	 * for introducing a seam at each pixel is determined using the energy()
	 * method below.
	 *
	 * @param img the input image
	 * @return the table storing the costs
	 */
	public static float[][] computeCosts(BufferedImage img)
	{
		int width = img.getWidth();
		int height = img.getHeight();
		float[][] costTable = new float[width][height];
		for(int x = 0; x < width; x++){
			for(int y = 0; y < height; y++)
				costTable[x][y] = energy(img, x, y);
		}
		return costTable;

	}

	/**
	 * Reconstructs a vertical seam from the cost table. A vertical seam is stored
	 * as an array where element y in the array stores an index seam[y], which indicates
	 * that in row y the seam goes through column seam[y].
	 *
	 * @param costs the cost table
	 * @param width of the cost table
	 * @param height of the cost table
	 * @return the seam
	 */
	public static int[] computeSeam(float[][] costs, int width, int height) {

		int[] seam = new int[height];

		//Get starting X coordinate
		float tmpCost = 0;
		int startX = 0;
		for (int i = 0; i < width; i++) {
			if (costs[i][0] > tmpCost) {
				startX = i;
				tmpCost = costs[i][0];
			}
		}
		seam[0] = startX;

		for(int i = 1; i < height-1; i++){
			seam[i] = getLowestUp(seam[i-1], i-1, costs);
		}
		return seam;
	}

	public static int getLowestUp(int x, int y, float[][] map){
		float[] costs = new float[3];

		if(x > 0)
			costs[0] = map[x-1][y+1];
		else
			costs[0] = Float.POSITIVE_INFINITY;
		if(x < map.length-1)
			costs[1] = map[x][y+1];
		else
			costs[1] = Float.POSITIVE_INFINITY;
		if(x < map.length-1)
			costs[2] = map[x+1][y+1];
		else
			costs[2] = Float.POSITIVE_INFINITY;

		if(costs[0] < costs[1] && costs[0] < costs[2])
			return x-1;
		if(costs[1] < costs[0] && costs[1] < costs[2])
			return x;
		return x+1;
	}


	/**
	 * Removes a vertical seam from the image. The seam is an array that stores
	 * for each row y in the image the index of the column where the seam lies.
	 * The resulting image after removing the seam has one column less than
	 * the original (its width is reduced by one).
	 *
	 * @param img the input image
	 * @param seam the seam to be removed
	 * @return the new image
	 */
	public static BufferedImage removeSeam(BufferedImage img, int[] seam)
	{
		int width, height;
		width = img.getWidth();
		height = img.getHeight();

		// The width of the new image is reduced by one
		BufferedImage newImg = new BufferedImage(width-1, height, BufferedImage.TYPE_INT_RGB);

		// For all rows in the image
		for(int y=0; y<height; y++)
		{
			// Copy columns up to seam
			for(int x=0; x<seam[y]; x++)
			{
				newImg.setRGB(x, y, img.getRGB(x,y));
			}
			// Skip seam and copy the rest of the columns
			for(int x=seam[y]; x<width-1; x++)
			{
				newImg.setRGB(x, y, img.getRGB(x+1,y));
			}
		}
		return newImg;
	}

	/**
	 * Computes the energy of a pixel in the image. This energy is used as the cost
	 * for introducing a seam at this pixel. The energy here approximates the sum
	 * of the absolute values of the first derivatives of the image in x and y direction.
	 *
	 * @param img the input image
	 * @param x x-coordinate of the pixel
	 * @param y y-coordinate of the pixel
	 * @return energy of the pixel
	 */
	public static float energy(BufferedImage img, int x, int y)
	{
		int width = img.getWidth();
		int height = img.getHeight();

		if(x<0 || x>=width || y<0 || y>=height)
			return 0.f;

		float c0[],c1[];
		c0 = new float[3];
		c1 = new float[3];
		float didx = 0.f;
		float didy = 0.f;

		if(x+1<width)
		{
			c0[0] = (float)(img.getRGB(x, y) & 0xFF);
			c0[1] = (float)((img.getRGB(x, y) >> 8) & 0xFF);
			c0[2] = (float)((img.getRGB(x, y) >> 16) & 0xFF);

			c1[0] = (float)(img.getRGB(x+1, y) & 0xFF);
			c1[1] = (float)((img.getRGB(x+1, y) >> 8) & 0xFF);
			c1[2] = (float)((img.getRGB(x+1, y) >> 16) & 0xFF);
		} else
		{
			c0[0] = (float)(img.getRGB(x-1, y) & 0xFF);
			c0[1] = (float)((img.getRGB(x-1, y) >> 8) & 0xFF);
			c0[2] = (float)((img.getRGB(x-1, y) >> 16) & 0xFF);

			c1[0] = (float)(img.getRGB(x, y) & 0xFF);
			c1[1] = (float)((img.getRGB(x, y) >> 8) & 0xFF);
			c1[2] = (float)((img.getRGB(x, y) >> 16) & 0xFF);
		}

		for(int i=0; i<3; i++)
		{
			didx += (float)((c1[i]-c0[i])*(c1[i]-c0[i]));
		}
		didx = (float)Math.sqrt(didx);

		if(y+1<height)
		{
			c0[0] = (float)(img.getRGB(x, y) & 0xFF);
			c0[1] = (float)((img.getRGB(x, y) >> 8) & 0xFF);
			c0[2] = (float)((img.getRGB(x, y) >> 16) & 0xFF);

			c1[0] = (float)(img.getRGB(x, y+1) & 0xFF);
			c1[1] = (float)((img.getRGB(x, y+1) >> 8) & 0xFF);
			c1[2] = (float)((img.getRGB(x, y+1) >> 16) & 0xFF);
		} else
		{
			c0[0] = (float)(img.getRGB(x, y-1) & 0xFF);
			c0[1] = (float)((img.getRGB(x, y-1) >> 8) & 0xFF);
			c0[2] = (float)((img.getRGB(x, y-1) >> 16) & 0xFF);

			c1[0] = (float)(img.getRGB(x, y) & 0xFF);
			c1[1] = (float)((img.getRGB(x, y) >> 8) & 0xFF);
			c1[2] = (float)((img.getRGB(x, y) >> 16) & 0xFF);
		}

		for(int i=0; i<3; i++)
		{
			didy += (float)((c1[i]-c0[i])*(c1[i]-c0[i]));
		}
		didy = (float)Math.sqrt(didy);

		return didx+didy;
	}

    public static void main(String[] args) {

    	int width;
    	int height;
    	BufferedImage img;
    	float costs[][];
    	int seam[];

		try {
			img = ImageIO.read(new File("High_Sierra.png"));
		} catch(Exception e)
		{
			System.out.printf("Could not read image file!\n");
			return;
		}

		// Try different n between 1 and 100!
		for(int n=0; n<100; n++)
		{
			width = img.getWidth();
			height = img.getHeight();

			// Compute costs for seams.
			costs = computeCosts(img);
			// Extract seam with lowest cost.
			seam = computeSeam(costs, width, height);
			// Remove the seam from the image.
			img = removeSeam(img, seam);
		}

		try {
			ImageIO.write(img, "bmp", new File("processed.bmp"));
		} catch(Exception e)
		{
			System.out.printf("Could not write image file!\n");
			return;
		}

    }
}
