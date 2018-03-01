package package_01;

public class Layer {
	
	int neurons;
	int nneurons;
	int layer;
	double[][] weights;
	double[] sums;
	double[] activations;
	
	public Layer(int n, int l, int nn) {
		neurons = n;
		nneurons = nn;
		layer = l;
		weights = new double[nn][n];
		sums = new double[n];
		activations = new double[n];
	}
	
	public int length()
	{
		return neurons;
	}
	
	public void setWeights()
	{
		if (layer == 0)
		{
			for (int o = 0; o < neurons; o++)
				{
					weights[0][o] = 1.0;
				}
		}
		else
		{
			for (int i = 0; i < weights.length; i++)
			{
				for (int o = 0; o < weights[i].length; o++)
				{
					weights[i][o] = Math.random();
				}
			}
		}

	}
	
	public void setSums(double[] inputs)
	{
		for (int o = 0; o < this.length(); o++)
		{
			for (int i = 0; i < inputs.length; i++)
			{
				sums[o] += (weights[i][o] * inputs[i]);
			}
		}
	}
	
	public void setActivations()
	{
		for(int i = 0; i < this.length(); i++)
		{
			//activations[i] = 1+ (-2 / (1 + Math.exp(sums[i] / 42)));
			activations[i] = sums[i]/nneurons;
		}
	}
	
	public void setInputActivations(double[] pixels)
	{
		activations = pixels.clone();
	}
	
	public void changeWeights(double[][] newWeights)
	{
		weights = newWeights;
	}
	
	public double[][] getWeights() 
	{
		return weights;
	}
	
	public double[] getSums()
	{
		return sums;
	}
	
	public double[] getActivations()
	{
		return activations;
	}
	
	public double getAWeight(int i, int o)
	{
		return weights[i][o];
	}

	public double getASum(int i)
	{
		return sums[i];
	}
	
	public double getAnActivation(int i)
	{
		return activations[i];
	}
}
