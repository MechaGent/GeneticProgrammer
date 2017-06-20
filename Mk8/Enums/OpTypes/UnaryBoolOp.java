package EnemyLevelScaling.GeneticProgrammer.Mk8.Enums.OpTypes;

import EnemyLevelScaling.GeneticProgrammer.Mk8.Enums.OutputMode;
import EnemyLevelScaling.GeneticProgrammer.Mk8.Nodes.AbstractClasses.BaseNode;

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
	
	public StringBuilder toStringBuilder(BaseNode var1, OutputMode in)
	{
		return new StringBuilder(this.toString() + "(" + var1.toStringBuilder(in) + ")");
	}
}
