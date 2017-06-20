package EnemyLevelScaling.GeneticProgrammer.Mk8.ControlData;

public class ControlData
{
	private TrialData[] Data;
	
	public ControlData(TrialData[] inData)
	{
		this.Data = inData;
	}
	
	public int getNumTrials()
	{
		return this.Data.length;
	}
	
	public int getFitnessWeight(int TrialNum)
	{
		return this.Data[TrialNum].getFitnessWeight();
	}
	
	public String getVarName(int TrialNum, int index)
	{
		return this.Data[TrialNum].getVarName(index);
	}
	
	/**
	 * Caution: this method is greedy as hell
	 * @param name
	 * @return
	 */
	public int getVarIndex(int TrialNum, String inName)
	{
		return this.Data[TrialNum].getVarIndex(inName);
	}
	
	public double getVarValue(int TrialNum, int index)
	{
		return this.Data[TrialNum].getVarValue(index);
	}
	
	public int getNumVars(int TrialNum)
	{
		return this.Data[TrialNum].getNumVars();
	}
	
	public int[] getLevels(int TrialNum)
	{
		return this.Data[TrialNum].getLevels();
	}
}
