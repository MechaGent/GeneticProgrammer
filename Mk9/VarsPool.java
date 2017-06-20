package EnemyLevelScaling.GeneticProgrammer.Mk9;

public class VarsPool
{
	private double[] DubArray;
	
	public double getNumAt(int index)
	{
		return this.DubArray[index];
	}
	
	public void setNumAt(int index, double in)
	{
		this.DubArray[index] = in;
	}
}
