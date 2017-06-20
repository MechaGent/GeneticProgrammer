package EnemyLevelScaling.GeneticProgrammer.Mk8.Enums.OpTypes;

import EnemyLevelScaling.GeneticProgrammer.Mk8.Nodes.AbstractClasses.BaseNode;
import EnemyLevelScaling.GeneticProgrammer.Mk8.Enums.OutputMode;

public enum UnaryNumNumOp
{
	ForcePositive, ForceNegative, FlipSign, Increment, Decrement, DenominateVal, Floor, Ceiling, Round;

	private static UnaryNumNumOp[] All = values();

	public static UnaryNumNumOp getEnumAtIndex(int in)
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
