package EnemyLevelScaling.GeneticProgrammer.Mk7.Nodes.ReturnsDouble;

import EnemyLevelScaling.GeneticProgrammer.Mk7.Autobots.ConstantAutobot;
import EnemyLevelScaling.GeneticProgrammer.Mk7.Autobots.HelperAutobot;
import EnemyLevelScaling.GeneticProgrammer.Mk7.GlobalVarsPool;
import EnemyLevelScaling.GeneticProgrammer.Mk7.ParentRef;
import EnemyLevelScaling.GeneticProgrammer.Mk7.Autobots.RandyAutobot;
import EnemyLevelScaling.GeneticProgrammer.Mk7.Enums.NodeType;
import EnemyLevelScaling.GeneticProgrammer.Mk7.Enums.OpTypes.UnaryNumOp;
import EnemyLevelScaling.GeneticProgrammer.Mk7.Nodes.AbstractClasses.BaseNode;
import EnemyLevelScaling.GeneticProgrammer.Mk7.Nodes.AbstractClasses.DubNode;

public class UnaryNumNode extends DubNode
{
	private UnaryNumOp Op;

	public UnaryNumNode()
	{
		this(null, RandyAutobot.getRandyUnaryNumOp());
	}

	public UnaryNumNode(ParentRef inParent, UnaryNumOp inOp)
	{
		super(inParent, NodeType.UnaryNum, new BaseNode[NodeType.UnaryNum.getNumberOfExpectedArgumentSets()]);
		this.Op = inOp;
	}

	public UnaryNumNode(UnaryNumNode old, ParentRef StepParent, UnaryNumOp inOp, GlobalVarsPool inVars, boolean ApplyMutateChance)
	{
		this(StepParent, inOp);
		this.setChildren(new BaseNode[] {
											old.getChildAt(0).cascadeClone(new ParentRef(this, 0), inVars, ApplyMutateChance) });
	}
	
	public UnaryNumNode(UnaryNumOp inOp, BaseNode Var1)
	{
		super(null, NodeType.UnaryNum, new BaseNode[]{Var1});
		this.Op = inOp;
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
				result = -69;
				break;
			}

		}

		return result;
	}

	@Override
	public StringBuilder toStringBuilder()
	{
		return this.Op.toStringBuilder(this.getChildAt(0));
	}
	
	@Override
	public StringBuilder toStringBuilder(boolean WantsDiagnosticSyntax)
	{
		if(WantsDiagnosticSyntax)
		{
			StringBuilder result = new StringBuilder();
			
			result.append("UnaryNum");
			result.append(this.getTotalOffspringAsString());
			result.append("(");
			result.append(this.Op.toString());
			result.append(ConstantAutobot.getArgDelimiter());
			result.append(this.getChildAt(0).toStringBuilder(WantsDiagnosticSyntax));
			result.append(")");
			
			return result;
		}
		else
		{
			return this.toStringBuilder();
		}
	}
	
	@Override
	public StringBuilder toStringBuilder(StringBuilder in)
	{
		in.append("UnaryNum(");
		in.append(this.Op.toString());
		in.append(ConstantAutobot.getArgDelimiter());
		super.getChildAt(0).toStringBuilder(in);
		in.append(")");

		return in;
	}

	@Override
	public BaseNode cascadeClone(ParentRef StepParent, GlobalVarsPool newVars, boolean ApplyMutateChance)
	{
		if (ApplyMutateChance && RandyAutobot.flipCoin(ConstantAutobot.getMutationChance()))
		{
			return new UnaryNumNode(this, StepParent, RandyAutobot.getRandyUnaryNumOp(), newVars, ApplyMutateChance);
		}
		else
		{
			return new UnaryNumNode(this, StepParent, this.Op, newVars, ApplyMutateChance);
		}
	}
}
