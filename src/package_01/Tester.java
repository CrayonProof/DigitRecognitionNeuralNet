package package_01;

import java.io.File;
import java.util.Scanner;
import java.io.FileNotFoundException;

public class Tester 
{
	public double[][] bonzi(int l, double k, Layer[] layers, double[] outs)
	{	
		double deltaw;
		double[][] updatedWeights;
		int N = layers.length;
		for(int i = 0; i < layers[l - 1].getNeurons(); i++)
		{
			for(int o = 0; o < layers[l].getNeurons(); o++)
			{
				int n = l + 1;
				if (l == N)
				{
					deltaw = k * (outs[o]  - layers[l].getAnActivation(o)) * (1/layers[l - 1].getNeurons()) * layers[l - 1].getAnActivation(i);
				}
				else if (n == N)
				{
					deltaw = k * (outs[o]  - layers[l].getAnActivation(o)) * (1/layers[l-1].getNeurons()) * layers[l-1].getWeights(i, o);
				}
				else if (n == l)
				{
					n++;
					for(int a = 0; a < layers[l + 1].getNeurons(); a++)
					{
						deltaw += bonzi(l, k, layers, outs)[i][o];
					}
				}
				updatedWeights[i][o] = layers[l].getWeights(i, o) + deltaw;
			}
		}
			
			// N = layers.length
		return updatedWeights;
	}
	private static int IMAGE_HEIGHT = 28;
	private static int IMAGE_WIDTH = 28;

	public static void main(String[] args) throws FileNotFoundException
	{  
		Scanner in = new Scanner(new File(System.getProperty("user.dir") + "/mnist_train_100.csv")); //scanner to read files from csv training file
		Scanner sc = new Scanner(System.in);
		
		System.out.println("Images per batch: ");
		int batchSize = sc.nextInt();
		
		System.out.println("Learning rate: ");
		double k = sc.nextDouble();
		
		System.out.println("Layers: ");
		final int layers = sc.nextInt();
		Layer[] larray = new Layer[layers];
		
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
				larray[i].setWeights(imageInputs[0].length);
				larray[i].setSums(imageInputs[i]);
				larray[i].setActivations(imageInputs.length);
			}
			else
			{
				larray[i].setWeights(larray[i-1].getNeurons());
				larray[i].setSums(larray[i-1].getActivations());
				larray[i].setActivations(larray[i-1].getNeurons());
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
