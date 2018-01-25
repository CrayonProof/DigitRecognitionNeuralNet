package package_01;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class CoolerImageReader {

    public static void main(String args[]) throws Exception {
    	
    	String dataString = "";
		try {
			//this should not be accessed from my home computer, silly, cause now how will anyone get to it
			dataString = new String(Files.readAllBytes(Paths.get(System.getProperty("user.dir") + "\\mnist_train_100.csv")));
		} 
		catch (IOException e) {
			e.printStackTrace();
		}
		
		int[][] pixels = new int [28][28];
		
		String[] dataArray = dataString.split(",");
		
		int label;
		int i = 0;
		//outer loop doesn't work :( 
		for(int image = 0; image < 10; image++)
		{
			label = Integer.parseInt(dataArray[i]);
			System.out.println("label: " + label);
			i++;
			
			for(int y = 0; y < 28; y++)
			{
				for(int x = 0; x < 28; x++)
				{
					if (!(dataArray[i] == "\r\n"))
					{
						try
						{
							pixels[y][x] = Integer.parseInt(dataArray[i]);
							System.out.print(pixels[y][x] + " ");
						}
						catch(java.lang.NumberFormatException e)
						{
							System.out.println("SMOORF: " + dataArray[i]);
						}
						i++;
					}
				}
				
				System.out.println();
			}
		}
		
    }
}
/* Things to do: 
 * --make a bufferedimage out of the array so we can actually see what it looks like
 * --figure out why the outer for loop is broken -_-
 * --figure out how to access the data file from the cloud, so it isn't limited to local use 
 */

