package package_01;

import java.util.Scanner;
import java.io.IOException;
import java.io.File;
import java.io.FileNotFoundException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class CVSScanner {
	
	//constants
	private static int IMAGE_COUNT = 100;
	private static int IMAGE_HEIGHT = 28;
	private static int IMAGE_WIDTH = 28;


	public static void main(String[] args) throws FileNotFoundException 
	{
		int[][][] testImages = new int[IMAGE_COUNT][IMAGE_HEIGHT][IMAGE_WIDTH]; //3d array containing every image in training set. Form: image index, pixel y, pixel x
		int[] labels = new int[IMAGE_COUNT]; //1d array containing the corresponding labels for every image in training set
		Scanner in = new Scanner(new File(System.getProperty("user.dir") + "/mnist_train_100.csv")); //scanner to read files from csv training file
		
		int i;
		
		//for every image, creates a string and parses through the string, adding every digit to a 2d array
		for (int image = 0; image < IMAGE_COUNT; image ++)
		{
			//creates a string that contains current image pixel grey values
			String line = in.nextLine();
			String[] lineArray = line.split(",");
			
			//parses the above string into a one dimensional array whose every index is a pixel in the current image
			labels[image]= Integer.parseInt(lineArray[0]);
			
			i = 1; //current pixel for tracking during for loop
			
			for (int y = 0; y < IMAGE_HEIGHT; y++) //iterates through pixel rows
			{
				for (int x = 0; x < IMAGE_WIDTH; x++) //iterates through pixel columns
				{
					
					//adds pixel to the 3d array of all images, updates current pixel
					testImages[image][y][x] = Integer.parseInt(lineArray[i]);
					i++;
				}
			}
		}
		
		//prints out the images from the array at user request
		while (true)
		{
			int imageNum = IMAGE_COUNT + 1; //user selected image to print
			Scanner req = new Scanner(System.in); //user input scanner
			
			//prompts user for input until input in proper range is given
			while (imageNum > IMAGE_COUNT)
			{
			System.out.println("Which image (1 - " + IMAGE_COUNT + ") would you like to display?");
			imageNum = req.nextInt();
			}
			
			//prints the label for the requested image
			System.out.println();
			System.out.println("The following image is labeled as a " + labels[imageNum -1]);
			
			//prints out the image in ascii
			for (int y = 0; y < IMAGE_HEIGHT; y++) //iterates through pixel rows
			{
				for (int x = 0; x < IMAGE_WIDTH; x++) //iterates through pixel columns
				{
					//prints || for blank pixel, ][ for colored pixel
					if (testImages[imageNum - 1][y][x] == 0)
					{
						System.out.print("||");
					}
					else
					{
						System.out.print("][");
					}
				}
				System.out.println(); //next row of pixels
			}
		}
		
	}
}

