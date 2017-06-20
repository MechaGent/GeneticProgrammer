package EnemyLevelScaling.GeneticProgrammer.Mk8.Enums.OpTypes;

import EnemyLevelScaling.GeneticProgrammer.Mk8.Enums.OutputMode;
import EnemyLevelScaling.GeneticProgrammer.Mk8.Nodes.AbstractClasses.BaseNode;

public enum BinBoolBoolOp
{
	AND("&&"), OR("||"), NAND("!&"), NOR("!|"), XOR("X|"), XNOR("!X|");
	
	private String OperatorSymbol;
	
	private BinBoolBoolOp(String in)
	{
		this.OperatorSymbol = in;
	}

	private static BinBoolBoolOp[] All = values();

	public static BinBoolBoolOp getEnumAtIndex(int in)
	{
		return All[in];
	}

	public static int getNumOps()
	{
		return All.length;
	}
	
	public String getOperatorSymbol()
	{
		return OperatorSymbol;
	}

	public StringBuilder toStringBuilder(BaseNode var1, BaseNode var2, OutputMode in)
	{
		StringBuilder result = new StringBuilder();

		result.append("(");
		result.append(var1.toStringBuilder(in));
		result.append(" " + this.toString() + " ");
		result.append(var2.toStringBuilder(in));
		result.append(")");

		return result;
	}
}
