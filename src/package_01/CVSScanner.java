package package_01;

import java.util.Scanner;
import java.io.IOException;
import java.io.File;
import java.io.FileNotFoundException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class CVSScanner {


	public static void main(String[] args) throws FileNotFoundException 
	{
		int[][][] testImages = new int[100][28][28]; //image index, pixel y, pixel x
		int[] labels = new int[100];
		Scanner in = new Scanner(new File(System.getProperty("user.dir") + "/mnist_train_100.csv"));
		
		int i;
		
		//for every image, creates a string and parses through the string, adding every digit to a 2d array
		for (int image = 0; image < 100; image ++)
		{
			String line = in.nextLine();
			String[] lineArray = line.split(",");
			
			labels[image]= Integer.parseInt(lineArray[0]);
			//System.out.println("label: " + labels[image]);
			
			i = 1;
			
			for (int y = 0; y < 28; y++)
			{
				for (int x = 0; x < 28; x++)
				{
					testImages[image][y][x] = Integer.parseInt(lineArray[i]);
					i++;
					//System.out.print(testImages[image][y][x]);
				}
				//System.out.println();
			}
			//System.out.println();
		}
		
		//prints out the images from the array
		while (true)
		{
			int imageNum = 101;
			Scanner req = new Scanner(System.in);
			
			while (imageNum > 100)
			{
			System.out.println("Which image (1 - 100) would you like to display?");
			imageNum = req.nextInt();
			}
			
			for (int y = 0; y < 28; y++)
			{
				for (int x = 0; x < 28; x++)
				{
					if (testImages[imageNum - 1][y][x] == 0)
					{
						System.out.print("||");
					}
					else
					{
						System.out.print("][");
					}
				}
				System.out.println();
			}
		}
		
	}
}

