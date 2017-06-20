package EnemyLevelScaling.GeneticProgrammer.Mk5.Enums;

public enum BinaryNumBoolOp
{
	LessThan, LessThanOrEqualTo, GreaterThan, GreaterThanOrEqualTo, EqualTo, NotEqualTo;
	
private static BinaryNumBoolOp[] All = values();
	
	public static BinaryNumBoolOp getEnumAtIndex(int in)
	{
		return All[in];
	}
	
	public static int getNumOps()
	{
		return All.length;
	}
}
