package package_01;

import java.io.File;
import java.util.Scanner;
import java.io.FileNotFoundException;

//todo
//implement testing
//stochastic batches
//user input
//read to and from a text file


public class Tester 
{	
	private static int IMAGE_HEIGHT = 28;
	private static int IMAGE_WIDTH = 28;
	private static int INPUT_PIXELS = IMAGE_HEIGHT * IMAGE_WIDTH;

	public static void main(String[] args) throws FileNotFoundException
	{  
		//takes user input for various parameters
		Scanner sc = new Scanner(System.in);
		
		//System.out.println("Images per batch: ");
		int batchSize = 10;//sc.nextInt();
		
		//System.out.println("Learning rate: ");
		double k = 1;//sc.nextDouble();
		
		//System.out.println("Layers: (including output layer, not including input layer)");
		final int layers = 4;//sc.nextInt() + 1;
		Layer[] larray = new Layer[layers];
		
		//initializes input data arrays, and desired output array
		double[][] imageInputs = new double[batchSize][INPUT_PIXELS];
		int[] labels = new int[batchSize];
		double[] yhat = new double[10];
		
		//initializes input layer
		System.out.println("Layer 0 contains " + INPUT_PIXELS + " input neurons.");
		larray[0] = new Layer(INPUT_PIXELS, 0, 0);
		
		//initializes all other layers
		for(int i = 1; i < layers; i++)
		{
			if(i == (layers - 1))
			{
				System.out.println("10 output neurons in layer " + (layers - 1) + ", final layer");
				larray[i] = new Layer(10, i, larray[i - 1].length());
			}
			else
			{
				System.out.println("Neurons in layer " + (i) + ": ");
				larray[i] = new Layer(sc.nextInt(), i, larray[i - 1].length());
			}

				larray[i].setWeights();
		}
		
		//reads CSV file into imageInputs array
		Scanner in = new Scanner(new File(System.getProperty("user.dir") + "/mnist_train_100.csv")); 
		
		int p;
		for (int image = 0; image < batchSize; image ++)
		{
			String line = in.nextLine();
			String[] lineArray = line.split(",");
			
			labels[image]= Integer.parseInt(lineArray[0]);
			
			p = 1; 
			
			for (int i = 0; i < INPUT_PIXELS; i++) 
			{
				imageInputs[image][i] = ((double)Integer.parseInt(lineArray[p])) / 255;
				p++;
			}
		}
						
		//run the network for every image in the batch
		for(int i = 0; i < imageInputs.length; i++)
		{
			//set desired outputs
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
			
			//set 1st layer activations
			larray[0].setInputActivations(imageInputs[i]);
			
			//set sums and activations for all other layers
			for(int l = 1; l < layers; l++)
			{
				larray[l].setSums(larray[l-1].getActivations());
				larray[l].setActivations(larray[l-1].length());
			}
			
			//update weights of each layer
			for(int l = layers - 1; l > 0; l--)
			{
				larray[l].changeWeights(updateWeights(l, k, larray, yhat));
			}
			
			//print the average cost for each training example, to see if it decreases over time. 
			double cost = 0;
			for(int o = 0; o < 10; o++)
			{
				cost += Math.pow((larray[layers - 1].getActivations()[o] - yhat[o]), 2);
			}
			cost /= 10;
			System.out.println("cost: " + cost);
		}
	}
	
	public static double[][] updateWeights(int l, double k, Layer[] layers, double[] outs)
	{
		double[][] oldWeights = layers[l].getWeights();
		double[][] newWeights;
		int n = l;
		
		newWeights = new double[layers[l - 1].length()][layers[l].length()];
		
		for (int i = 0; i < layers[l - 1].length(); i++)
		{
			for(int o = 0; o < layers[l].length(); o++)
			{
				//should this be + or - ?
				newWeights[i][o] = oldWeights[i][o] + bonzi(l, n, k, i, o, layers, outs);
			}
		}
		return newWeights;
	}
	
	public static double bonzi(int l, int n, double k, int i, int o, Layer[] layers, double[] outs)
	{	
		double deltaw = 0;
		int N = layers.length - 1;
		if (l == N)
		{
			deltaw = k * (outs[o]  - layers[l].getAnActivation(o)) * (1/layers[l - 1].length()) * layers[l - 1].getAnActivation(i);
		}
		else if (n == N)
		{
			deltaw = k * (outs[o]  - layers[l].getAnActivation(o)) * (1/layers[l-1].length()) * layers[l-1].getAWeight(i, o);
		}
		else if (n == l)
		{
			n++;
			for(int a = 0; a < layers[l + 1].length(); a++)
			{
				deltaw += bonzi(l, n, k, i, o, layers, outs);
			}
			deltaw *= (1/layers[l - 1].length()) * layers[l - 1].getAnActivation(i);
		}
		else
		{
			n++;
			for(int a = 0; a < layers[l + 1].length(); a++)
			{
				deltaw += bonzi(l, n, k, i, o, layers, outs);
			}
			//possibly getting the wrong weight I'm too tired to think through it
			deltaw *= (1/layers[l - 1].length()) * layers[l - 1].getAWeight(i, o);
		}
		return deltaw;
	}
	
}
