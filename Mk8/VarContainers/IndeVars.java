package EnemyLevelScaling.GeneticProgrammer.Mk8.VarContainers;

public class IndeVars
{
	private String[] VarNames;
	private double[] VarValues;
	
	public IndeVars(String[] inVarNames, double[] inVarValues)
	{
		this.VarNames = inVarNames;
		this.VarValues = inVarValues;
	}
	
	public String getVarNameAt(int index)
	{
		return this.VarNames[index];
	}
	
	public double getVarValueAt(int index)
	{
		return this.VarValues[index];
	}
	
	public int getNumVars()
	{
		return this.VarNames.length;
	}
	
	/**
	 * Caution: this method is greedy as hell
	 * @param name
	 * @return
	 */
	public int getVarIndex(String inName)
	{
		int result = 0;
		String name = inName.replaceAll("\"", "");
		
		while(result < this.VarNames.length)
		{
			if(name.equals(this.VarNames[result]))
			{
				//System.out.println(name + " with size " + name.length() + " == " + this.VarNames[result] + " with size " + name.length() + " resulted in true");
				return result;
			}
			else
			{
				//System.out.println(name + " with size " + name.length() + " == " + this.VarNames[result] + " with size " + name.length() + " resulted in false");
				result++;
			}
		}
		
		return result;
	}
}
