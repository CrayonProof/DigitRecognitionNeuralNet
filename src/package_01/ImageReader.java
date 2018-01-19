package package_01;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class ImageReader {
	public static void main(String[] args) throws IOException {
		BufferedImage image = ImageIO.read(new File("H:\\rsz_1one.png"));
		int[][] colors = new int [28][28];
	
		for(int y = 0; y < 28; y++)
		{
			for(int x = 0; x < 28; x++)
			{
				int pixcolor = image.getRGB(x, y);
				pixcolor = pixcolor & 0xFF;
				System.out.println(pixcolor);
				colors[x][y] = pixcolor;
			}
		}
	}
}


