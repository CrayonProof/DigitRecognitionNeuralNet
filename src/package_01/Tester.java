package package_01;

import java.util.Scanner;

public class Tester {

	public static void main(String[] args) {
		
		double[] imageInputs = new double[784];
		Scanner sc = new Scanner(System.in);
		
		System.out.println("How many layers do you want?");
		final int layers = sc.nextInt();
		
		Layer[] larray = new Layer[layers];
		
		for(int i = 0; i < layers; i++)
		{
			System.out.println("How many neurons do you want layer " + (i + 1) + " to have?");
			larray[i] = new Layer(sc.nextInt());
			
			if (i == 0)
			{
				larray[i].setWeights(imageInputs.length);
				larray[i].setSums(imageInputs);
				larray[i].setActivations(larray[i].getSums());
			}
			else
			{
				larray[i].setWeights(larray[i-1].getNeurons());
				larray[i].setSums(larray[i-1].getActivations());
				larray[i].setActivations(larray[i-1].getSums());
			}
		}
		
		/*for(int i = 0; i < larray[layers - 1].getNeurons(); i++)
		{
			System.out.println(larray[layers - 1].getActivations()[i]);
		}
		*/
	}

}
