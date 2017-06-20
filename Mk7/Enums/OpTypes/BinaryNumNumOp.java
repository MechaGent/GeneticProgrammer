package EnemyLevelScaling.GeneticProgrammer.Mk7.Enums.OpTypes;

import EnemyLevelScaling.GeneticProgrammer.Mk7.Autobots.ConstantAutobot;
import EnemyLevelScaling.GeneticProgrammer.Mk7.Nodes.AbstractClasses.BaseNode;

public enum BinaryNumNumOp
{
	Add, Sub, Mult, Div, Pow, Mod;

	private static BinaryNumNumOp[] All = values();

	public static BinaryNumNumOp getEnumAtIndex(int in)
	{
		return All[in];
	}

	public static int getNumOps()
	{
		return All.length;
	}

	public StringBuilder toStringBuilder(StringBuilder in, BaseNode var1, BaseNode var2)
	{
		switch (this)
		{
		case Add:
		case Div:
		case Mod:
		case Mult:
		case Sub:
			{
				in.append("(");
				var1.toStringBuilder(in);
				in.append(" " + this.toString() + " ");
				var2.toStringBuilder(in);
				in.append(")");
				break;
			}
		case Pow:
		default:
			{
				in.append(this.toString());
				in.append("(");
				var1.toStringBuilder(in);
				in.append(ConstantAutobot.getArgDelimiter());
				var2.toStringBuilder(in);
				in.append(")");
				break;
			}

		}

		return in;
	}
	
	public StringBuilder toStringBuilder(BaseNode var1, BaseNode var2)
	{
		return this.toStringBuilder(new StringBuilder(), var1, var2);
	}
}
