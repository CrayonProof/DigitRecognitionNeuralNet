package package_01;

public class Layer {
	
	int inputs = 0;
	int outputs = 0;
	
	double[][] weights = new double[inputs][outputs];
	double[] sums = new double[outputs];
	double[] activations = new double[outputs];
	
	public Layer(int in, int out, int layer)
	{
		inputs = in;
		outputs = out;
	}
	
	//access input array: in 
	
	public double[][] setWeights(int inp, int outp) 
	{	
		for (int i = 0; i < inp; i++)
		{
			for (int o = 0; o < outp; o++)
			{
				weights[i][o] = Math.random();
			}
		}
		return weights;
	}
	
	
	public double[] setSums(int inp[], int outp)
	{	
		for (int o = 0; o < outp; o++)
		{
			for (int i = 0; i < inp.length; i++)
			{
				sums[o] += (weights[i][o] * inp[i]);
			}
		}
		
		return sums;
	}
	
	public double[] setActivations(int outp)
	{
		
		for (int o = 0; o < outp; o++)
		{
			activations[o] = (sums[o]); //apply sigmoid function
		}
		
		return activations;
	}
	
	
}
