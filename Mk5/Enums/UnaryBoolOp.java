package EnemyLevelScaling.GeneticProgrammer.Mk5.Enums;

public enum UnaryBoolOp
{
	NOT;

	private static UnaryBoolOp[] All = values();

	public static UnaryBoolOp getEnumAtIndex(int in)
	{
		return All[in];
	}

	public static int getNumOps()
	{
		return All.length;
	}
}
