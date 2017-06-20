package EnemyLevelScaling.GeneticProgrammer.Mk8.VarContainers;

public class DeVars
{
	private int[] Levels;
	
	public DeVars(int[] inLevels)
	{
		this.Levels = inLevels;
	}
	
	public int getLevelAt(int index)
	{
		return this.Levels[index];
	}
	
	public int[] getLevels()
	{
		return this.Levels;
	}
}
