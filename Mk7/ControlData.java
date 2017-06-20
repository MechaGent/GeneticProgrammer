package EnemyLevelScaling.GeneticProgrammer.Mk7;

import java.util.HashMap;

public class ControlData
{
	private String[] VarNames;
	private double[] VarValues;
	private int[] Levels;
	
	public ControlData(String[] inVarNames, double[] inVarValues, int[] inLevels)
	{
		this.VarNames = inVarNames;
		this.VarValues = inVarValues;
		this.Levels = inLevels;
	}
	
	/*
	public ControlData(HashMap<String, Double> inVars, int[] inLevels)
	{
		
	}
	*/
	
	public String getVarName(int index)
	{
		return this.VarNames[index];
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
	
	public double getVarValue(int index)
	{
		return this.VarValues[index];
	}
	
	public int getNumVars()
	{
		return this.VarNames.length;
	}
	
	public int[] getLevels()
	{
		return this.Levels;
	}
}
