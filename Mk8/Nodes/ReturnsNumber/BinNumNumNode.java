package EnemyLevelScaling.GeneticProgrammer.Mk8.Nodes.ReturnsNumber;

import EnemyLevelScaling.GeneticProgrammer.Mk8.GlobalVarsPool;
import EnemyLevelScaling.GeneticProgrammer.Mk8.ParentRef;
import EnemyLevelScaling.GeneticProgrammer.Mk8.Autobots.ConstantAutobot;
import EnemyLevelScaling.GeneticProgrammer.Mk8.Autobots.HelperAutobot;
import EnemyLevelScaling.GeneticProgrammer.Mk8.Autobots.RandyAutobot;
import EnemyLevelScaling.GeneticProgrammer.Mk8.Autobots.WeeblAutobot;
import EnemyLevelScaling.GeneticProgrammer.Mk8.Enums.NodeType;
import EnemyLevelScaling.GeneticProgrammer.Mk8.Enums.OutputMode;
import EnemyLevelScaling.GeneticProgrammer.Mk8.Enums.OpTypes.BinNumNumOp;
import EnemyLevelScaling.GeneticProgrammer.Mk8.Nodes.AbstractClasses.BaseNode;
import EnemyLevelScaling.GeneticProgrammer.Mk8.Nodes.AbstractClasses.NumNode;

public class BinNumNumNode extends NumNode
{
	private BinNumNumOp Op;

	public BinNumNumNode(ParentRef inParent, BinNumNumOp inOp)
	{
		super(inParent, NodeType.BinNumNum);

		if (inOp != null)
		{
			this.Op = inOp;
		}
		else
		{
			this.Op = BinNumNumOp.getEnumAtIndex(RandyAutobot.getRandyIndex(BinNumNumOp.getNumOps()));
		}
	}
	
	@Override
	public void RecycleMe()
	{
		this.RecycleBaseNode();
		this.Op = null;
		WeeblAutobot.Recycle(this);
	}
	
	public BinNumNumNode unRecycleMe(ParentRef inParent, BinNumNumOp inOp)
	{
		this.unRecycleBaseNode(inParent, NodeType.BinNumNum);
		this.Op = inOp;
		return this;
	}

	@Override
	public double getValue()
	{
		double result;
		double a = HelperAutobot.getDubFromNode(this.getChildAt(0));
		double b = HelperAutobot.getDubFromNode(this.getChildAt(1));

		switch (this.Op)
		{
		case Add:
			{
				result = a + b;
				break;
			}
		case Div:
			{
				result = a / b;
				break;
			}
		case Mod:
			{
				result = (a % b + b) % b; //Apparently Java's Mod operator behaves oddly, which this fixes
				break;
			}
		case Mult:
			{
				result = a * b;
				break;
			}
		case Pow:
			{
				result = Math.pow(a, b);
				break;
			}
		case Sub:
			{
				result = a - b;
				break;
			}
		default:
			{
				result = -0;
				break;
			}
		}

		return result;
	}

	@Override
	public StringBuilder toStringBuilder(OutputMode in)
	{
		StringBuilder result = new StringBuilder();

		switch (in)
		{
		case Diagnostic:
			{
				result.append(this.getNodeType().toString());
				result.append("('");
				result.append(this.Op.toString());
				result.append("'");
				result.append(ConstantAutobot.getArgDelimiter());
				result.append(this.getChildAt(0).toStringBuilder(in));
				result.append(ConstantAutobot.getArgDelimiter());
				result.append(this.getChildAt(1).toStringBuilder(in));
				result.append(")");
				break;
			}
		case DiagnosticWithTotals:
			{
				result.append(this.getNodeType().toString());
				result.append("[" + HelperAutobot.fromArrayToStringBuilder(getTotalOffspring(), ConstantAutobot.getArgDelimiter()) + "]");
				result.append("('");
				result.append(this.Op.toString());
				result.append("'");
				result.append(ConstantAutobot.getArgDelimiter());
				result.append(this.getChildAt(0).toStringBuilder(in));
				result.append(ConstantAutobot.getArgDelimiter());
				result.append(this.getChildAt(1).toStringBuilder(in));
				result.append(")");
				break;
			}
		case Pseudocode:
			{
				result.append("(");
				result.append(this.getChildAt(0).toStringBuilder(in));
				result.append(" " + this.Op.getOperatorSymbol() + " ");
				result.append(this.getChildAt(1).toStringBuilder(in));
				result.append(")");
				break;
			}
		}

		return result;
	}

	@Override
	public BaseNode cascadeClone(ParentRef StepParent, GlobalVarsPool newVars)
	{
		BaseNode result = WeeblAutobot.requestNewBinNumNumNode(StepParent, Op);
		result.setChildAt(0, this.getChildAt(0).cascadeClone(WeeblAutobot.requestNewParentRef(this, 0), newVars));
		result.setChildAt(1, this.getChildAt(1).cascadeClone(WeeblAutobot.requestNewParentRef(this, 1), newVars));
		return result;
	}

	@Override
	public void applyMutationChance(boolean shouldCascade)
	{
		if (RandyAutobot.testForMutation())
		{
			this.Op = BinNumNumOp.getEnumAtIndex(RandyAutobot.getRandyIndex(BinNumNumOp.getNumOps()));
		}

		if (shouldCascade)
		{
			this.getChildAt(0).applyMutationChance(true);
			this.getChildAt(1).applyMutationChance(true);
		}
	}

}
