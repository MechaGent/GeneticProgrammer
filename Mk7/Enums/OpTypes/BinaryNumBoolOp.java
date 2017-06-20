package EnemyLevelScaling.GeneticProgrammer.Mk7.Enums.OpTypes;

import EnemyLevelScaling.GeneticProgrammer.Mk7.Nodes.AbstractClasses.BaseNode;

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
