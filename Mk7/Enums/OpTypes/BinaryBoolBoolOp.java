package EnemyLevelScaling.GeneticProgrammer.Mk7.Enums.OpTypes;

import EnemyLevelScaling.GeneticProgrammer.Mk7.Nodes.AbstractClasses.BaseNode;

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
	
	public StringBuilder toStringBuilder(BaseNode var1, BaseNode var2)
	{
		StringBuilder result = new StringBuilder();
		
		result.append("(");
		var1.toStringBuilder(result);
		result.append(" " + this.toString() + " ");
		var2.toStringBuilder(result);
		result.append(")");
		
		return result;
	}
}
