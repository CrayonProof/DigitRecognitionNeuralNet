package package_01;

import java.io.File;
import java.util.Scanner;
import java.io.FileNotFoundException;

public class Tester 
{
	public double bonzi(int n, int i, int o, double[] outs)
	{
		if (n == 0)
		{
			return(outs[o] - )
		}
	}
	private static int IMAGE_HEIGHT = 28;
	private static int IMAGE_WIDTH = 28;

	public static void main(String[] args) throws FileNotFoundException
	{  
		Scanner in = new Scanner(new File(System.getProperty("user.dir") + "/mnist_train_100.csv")); //scanner to read files from csv training file
		Scanner sc = new Scanner(System.in);
		
		System.out.println("Layers: ");
		final int layers = sc.nextInt();
		Layer[] larray = new Layer[layers];

		System.out.println("Images per batch: ");
		int batchSize = sc.nextInt();
		
		double[][] imageInputs = new double[batchSize][IMAGE_HEIGHT * IMAGE_WIDTH];
		int[] labels = new int[batchSize];
		
		//reads csv file into imageInputs array
		int p;
		for (int image = 0; image < batchSize; image ++)
		{
			String line = in.nextLine();
			String[] lineArray = line.split(",");
			
			labels[image]= Integer.parseInt(lineArray[0]);
			
			p = 1; 
			
			for (int i = 0; i < IMAGE_HEIGHT * IMAGE_WIDTH; i++) 
			{
				imageInputs[image][i] = Integer.parseInt(lineArray[p]);
				p++;
			}
		}
			
		//initializes layers
		for(int i = 0; i < layers; i++)
		{
			if(i == (layers - 1))
			{
				System.out.println("10 output neurons in layer " + layers + ", final layer");
				larray[i] = new Layer(10);
			}
			else
			{
				System.out.println("Neurons in layer " + (i + 1) + ": ");
				larray[i] = new Layer(sc.nextInt());
			}

			if (i == 0)
			{
				larray[i].setWeights(imageInputs.length);
				larray[i].setSums(imageInputs[i]);
				larray[i].setActivations(larray[i].getSums());
			}
			else
			{
				larray[i].setWeights(larray[i-1].getNeurons());
				larray[i].setSums(larray[i-1].getActivations());
				larray[i].setActivations(larray[i].getSums());
			}
		}
		
		double[] yhat = new double[10];
		
		for (int i = 0; i < batchSize; i++)
		{
			for(int j = 0; j < 10; j++)
			{
				if (labels[i] == j)
				{
					yhat[j] = 1.0;
				}
				else
				{
					yhat[j] = 0.0;
				}
			}
		}


	}
}
