package EnemyLevelScaling.GeneticProgrammer.Mk8.Nodes.ReturnsNumber;

import EnemyLevelScaling.GeneticProgrammer.Mk8.Autobots.ConstantAutobot;
import EnemyLevelScaling.GeneticProgrammer.Mk8.Autobots.HelperAutobot;
import EnemyLevelScaling.GeneticProgrammer.Mk8.Autobots.RandyAutobot;
import EnemyLevelScaling.GeneticProgrammer.Mk8.Autobots.WeeblAutobot;
import EnemyLevelScaling.GeneticProgrammer.Mk8.GlobalVarsPool;
import EnemyLevelScaling.GeneticProgrammer.Mk8.ParentRef;
import EnemyLevelScaling.GeneticProgrammer.Mk8.Enums.NodeType;
import EnemyLevelScaling.GeneticProgrammer.Mk8.Enums.OutputMode;
import EnemyLevelScaling.GeneticProgrammer.Mk8.Enums.OpTypes.UnaryNumNumOp;
import EnemyLevelScaling.GeneticProgrammer.Mk8.Nodes.AbstractClasses.BaseNode;
import EnemyLevelScaling.GeneticProgrammer.Mk8.Nodes.AbstractClasses.NumNode;

public class UnaryNumNumNode extends NumNode
{
	private UnaryNumNumOp Op;

	public UnaryNumNumNode(ParentRef inParent, UnaryNumNumOp inOp)
	{
		super(inParent, NodeType.UnaryNumNum);
		this.Op = inOp;
	}
	
	@Override
	public void RecycleMe()
	{
		this.RecycleBaseNode();
		this.Op = null;
		WeeblAutobot.Recycle(this);
	}
	
	public UnaryNumNumNode unRecycleMe(ParentRef inParent, UnaryNumNumOp inOp)
	{
		this.unRecycleBaseNode(inParent, NodeType.UnaryNumNum);
		this.Op = inOp;
		return this;
	}

	@Override
	public double getValue()
	{
		double result;
		double a = HelperAutobot.getDubFromNode(super.getChildAt(0));

		switch (this.Op)
		{
		case Ceiling:
			{
				result = Math.ceil(a);
				break;
			}
		case Decrement:
			{
				result = a - 1;
				break;
			}
		case DenominateVal:
			{
				result = 1 / a;
				break;
			}
		case FlipSign:
			{
				result = -a;
				break;
			}
		case Floor:
			{
				result = Math.floor(a);
				break;
			}
		case ForceNegative:
			{
				result = -Math.abs(a);
				break;
			}
		case ForcePositive:
			{
				result = Math.abs(a);
				break;
			}
		case Increment:
			{
				result = a + 1;
				break;
			}
		case Round:
			{
				result = Math.round(a);
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
				result.append(")");
				break;
			}
		case Pseudocode:
			{
				result.append(this.Op.toString());
				result.append("(");
				result.append(this.getChildAt(0).toStringBuilder(in));
				result.append(")");
				break;
			}
		}

		return result;
	}

	@Override
	public BaseNode cascadeClone(ParentRef StepParent, GlobalVarsPool newVars)
	{
		BaseNode result = WeeblAutobot.requestNewUnaryNumNumNode(StepParent, Op);
		result.setChildAt(0, this.getChildAt(0).cascadeClone(new ParentRef(this, 0), newVars));
		return result;
	}

	@Override
	public void applyMutationChance(boolean shouldCascade)
	{
		if (RandyAutobot.testForMutation())
		{
			this.Op = UnaryNumNumOp.getEnumAtIndex(RandyAutobot.getRandyIndex(UnaryNumNumOp.getNumOps()));
		}

		if (shouldCascade)
		{
			this.getChildAt(0).applyMutationChance(true);
		}
	}

}
