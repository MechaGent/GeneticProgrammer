package EnemyLevelScaling.GeneticProgrammer.Mk8.Nodes.ReturnsBool;

import EnemyLevelScaling.GeneticProgrammer.Mk8.Autobots.ConstantAutobot;
import EnemyLevelScaling.GeneticProgrammer.Mk8.Autobots.HelperAutobot;
import EnemyLevelScaling.GeneticProgrammer.Mk8.Autobots.RandyAutobot;
import EnemyLevelScaling.GeneticProgrammer.Mk8.Autobots.WeeblAutobot;
import EnemyLevelScaling.GeneticProgrammer.Mk8.GlobalVarsPool;
import EnemyLevelScaling.GeneticProgrammer.Mk8.ParentRef;
import EnemyLevelScaling.GeneticProgrammer.Mk8.Enums.NodeType;
import EnemyLevelScaling.GeneticProgrammer.Mk8.Enums.OutputMode;
import EnemyLevelScaling.GeneticProgrammer.Mk8.Enums.OpTypes.BinNumBoolOp;
import EnemyLevelScaling.GeneticProgrammer.Mk8.Enums.OpTypes.BinNumNumOp;
import EnemyLevelScaling.GeneticProgrammer.Mk8.Nodes.AbstractClasses.BaseNode;
import EnemyLevelScaling.GeneticProgrammer.Mk8.Nodes.AbstractClasses.BoolNode;

public class BinNumBoolNode extends BoolNode
{
	private BinNumBoolOp Op;
	
	public BinNumBoolNode(ParentRef inParent, BinNumBoolOp inOp)
	{
		super(inParent, NodeType.BinNumBool);
		this.Op = inOp;
	}
	
	@Override
	public void RecycleMe()
	{
		this.RecycleBaseNode();
		this.Op = null;
		WeeblAutobot.Recycle(this);
	}
	
	public BinNumBoolNode unRecycleMe(ParentRef inParent, BinNumBoolOp inOp)
	{
		this.unRecycleBaseNode(inParent, NodeType.BinNumBool);
		this.Op = inOp;
		return this;
	}

	@Override
	public boolean getValue()
	{
		boolean result;
		double a = HelperAutobot.getDubFromNode(this.getChildAt(0));
		double b = HelperAutobot.getDubFromNode(this.getChildAt(1));

		switch (this.Op)
		{
		case EqualTo:
			{
				result = a == b;
				break;
			}
		case GreaterThan:
			{
				result = a > b;
				break;
			}
		case GreaterThanOrEqualTo:
			{
				result = a >= b;
				break;
			}
		case LessThan:
			{
				result = a < b;
				break;
			}
		case LessThanOrEqualTo:
			{
				result = a <= b;
				break;
			}
		case NotEqualTo:
			{
				result = a != b;
				break;
			}
		default:
			{
				result = false;
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
		BaseNode result = WeeblAutobot.requestNewBinNumBoolNode(StepParent, Op);
		result.setChildAt(0, this.getChildAt(0).cascadeClone(WeeblAutobot.requestNewParentRef(this, 0), newVars));
		result.setChildAt(1, this.getChildAt(1).cascadeClone(WeeblAutobot.requestNewParentRef(this, 1), newVars));
		return result;
	}

	@Override
	public void applyMutationChance(boolean shouldCascade)
	{
		if (RandyAutobot.testForMutation())
		{
			this.Op = BinNumBoolOp.getEnumAtIndex(RandyAutobot.getRandyIndex(BinNumNumOp.getNumOps()));
		}

		if (shouldCascade)
		{
			this.getChildAt(0).applyMutationChance(true);
			this.getChildAt(1).applyMutationChance(true);
		}
	}

}
