package EnemyLevelScaling.GeneticProgrammer.Mk8.Enums.OpTypes;

import EnemyLevelScaling.GeneticProgrammer.Mk8.Nodes.AbstractClasses.BaseNode;
import EnemyLevelScaling.GeneticProgrammer.Mk8.Autobots.ConstantAutobot;
import EnemyLevelScaling.GeneticProgrammer.Mk8.Enums.OutputMode;

public enum BinNumNumOp
{
	Add("+"), Sub("-"), Mult("*"), Div("/"), Pow("^"), Mod("%");

	private String OperatorSymbol;
	
	private static BinNumNumOp[] All = values();
	
	private BinNumNumOp(String in)
	{
		this.OperatorSymbol = in;
	}
	
	public String getOperatorSymbol()
	{
		return OperatorSymbol;
	}

	public static BinNumNumOp getEnumAtIndex(int in)
	{
		return All[in];
	}

	public static int getNumOps()
	{
		return All.length;
	}

	public StringBuilder toStringBuilder(BaseNode var1, BaseNode var2, OutputMode in)
	{
		StringBuilder result = new StringBuilder();
		
		switch (this)
		{
		case Add:
		case Div:
		case Mod:
		case Mult:
		case Sub:
			{
				result.append("(");
				result.append(var1.toStringBuilder(in));
				result.append(" " + this.toString() + " ");
				result.append(var2.toStringBuilder(in));
				result.append(")");
				break;
			}
		case Pow:
		default:
			{
				result.append(this.toString());
				result.append("(");
				result.append(var1.toStringBuilder(in));
				result.append(ConstantAutobot.getArgDelimiter());
				result.append(var1.toStringBuilder(in));
				result.append(")");
				break;
			}

		}

		return result;
	}
}
