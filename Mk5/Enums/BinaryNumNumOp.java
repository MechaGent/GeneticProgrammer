package EnemyLevelScaling.GeneticProgrammer.Mk5.Enums;

public enum BinaryNumNumOp
{
	Add, Sub, Mult, Div, Pow, Mod;
	
	private static BinaryNumNumOp[] All = values();
	
	public static BinaryNumNumOp getEnumAtIndex(int in)
	{
		return All[in];
	}
	
	public static int getNumOps()
	{
		return All.length;
	}
}
