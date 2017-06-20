package EnemyLevelScaling.GeneticProgrammer.Mk8.Enums.OpTypes;

import EnemyLevelScaling.GeneticProgrammer.Mk8.Enums.OutputMode;
import EnemyLevelScaling.GeneticProgrammer.Mk8.Nodes.AbstractClasses.BaseNode;

public enum BinNumBoolOp
{
	LessThan("<"), LessThanOrEqualTo("=<"), GreaterThan(">"), GreaterThanOrEqualTo(">="), EqualTo("=="), NotEqualTo("!=");
	
	private String OperatorSymbol;
	
	private BinNumBoolOp(String in)
	{
		this.OperatorSymbol = in;
	}
	
	private static BinNumBoolOp[] All = values();
	
	public static BinNumBoolOp getEnumAtIndex(int in)
	{
		return All[in];
	}
	
	public static int getNumOps()
	{
		return All.length;
	}
	
	public String getOperatorSymbol()
	{
		return this.OperatorSymbol;
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
