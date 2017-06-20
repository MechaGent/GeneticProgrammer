package EnemyLevelScaling.GeneticProgrammer.Mk6.Enums;

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
