package EnemyLevelScaling.GeneticProgrammer.Mk5.Enums;

public enum BinaryBoolBoolOp
{
	AND, OR, NAND, NOR, XOR, XNOR;
	
private static BinaryBoolBoolOp[] All = values();
	
	public static BinaryBoolBoolOp getEnumAtIndex(int in)
	{
		return All[in];
	}
	
	public static int getNumOps()
	{
		return All.length;
	}
}
