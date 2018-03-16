package package_01;

public class Layer {
	
	int neurons;
	int nneurons;
	int layer;
	double[][] weights;
	double[] sums;
	double[] activations;
	double actvalue;
	
	public Layer(int n, int l, int nn) {
		neurons = n;
		nneurons = nn;
		layer = l;
		weights = new double[nn][n];
		sums = new double[n];
		activations = new double[n];
		actvalue = 1/ (nn / 4.667);
	}
	
	public int length()
	{
		return neurons;
	}
	
	public double getActvalue()
	{
		return actvalue;
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
					if(Math.random() < .5)
					{
						weights[i][o] *= -1;
					}
				}
			}
		}

	}
	
	public void setSums(double[] inputs)
	{
		for (int o = 0; o < neurons; o++)
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
			activations[i] = (-1 / (1 + Math.exp(actvalue * sums[i]))) + 1;
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
	
	
	public double sigmoidDev(double x, double y) 
	{
		return y * Math.exp(x * y) / Math.pow(Math.exp(x * y), 2);
	}
}
