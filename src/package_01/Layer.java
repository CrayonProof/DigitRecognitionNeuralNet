package package_01;

public class Layer {
	
	int neurons;
	double[][] weights;
	double[] sums;
	double[] activations;
	
	public Layer(int n) {
		int neurons = n;
	}
	
	public int getNeurons()
	{
		return neurons;
	}
	
	public void setWeights(int inputs)
	{
		for (int i = 0; i < inputs; i++)
		{
			for (int o = 0; o < this.getNeurons(); o++)
			{
				weights[i][o] = Math.random();
			}
		}
	}
	
	public void setSums(double[] inputs)
	{
		for (int o = 0; o < this.getNeurons(); o++)
		{
			for (int i = 0; i < inputs.length; i++)
			{
				sums[o] += (weights[i][o] * inputs[i]);
			}
		}
	}
	
	public void setActivations(int inputs)
	{
		for(int i = 0; i < this.getNeurons(); i++)
		{
			//activations[i] = 1+ (-2 / (1 + Math.exp(sums[i] / 42)));
			activations[i] = sums[i]/inputs;
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
