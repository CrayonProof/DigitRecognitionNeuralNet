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
	
	public void setActivations(double[] inputs)
	{
		for(int i = 0; i < this.getNeurons(); i++)
		{
			activations[i] = 1+ (-2 / (1 + Math.exp(sums[i] / 42))); 
		}
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
}
