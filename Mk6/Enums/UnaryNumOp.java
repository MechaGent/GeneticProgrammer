package EnemyLevelScaling.GeneticProgrammer.Mk6.Enums;

public enum UnaryNumOp
{
	ForcePositive, ForceNegative, FlipSign, Increment, Decrement, DenominateVal, Floor, Ceiling, Round;

	private static UnaryNumOp[] All = values();

	public static UnaryNumOp getEnumAtIndex(int in)
	{
		return All[in];
	}

	public static int getNumOps()
	{
		return All.length;
	}
}
