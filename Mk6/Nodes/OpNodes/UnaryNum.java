package EnemyLevelScaling.GeneticProgrammer.Mk6.Nodes.OpNodes;

import EnemyLevelScaling.GeneticProgrammer.Mk6.BaseNode;
import EnemyLevelScaling.GeneticProgrammer.Mk6.GlobalVars;
import EnemyLevelScaling.GeneticProgrammer.Mk6.ParentRef;
import EnemyLevelScaling.GeneticProgrammer.Mk6.Autobots.ConstantAutobot;
import EnemyLevelScaling.GeneticProgrammer.Mk6.Autobots.HelperAutobot;
import EnemyLevelScaling.GeneticProgrammer.Mk6.Autobots.RandyAutobot;
import EnemyLevelScaling.GeneticProgrammer.Mk6.Enums.NodeType;
import EnemyLevelScaling.GeneticProgrammer.Mk6.Enums.ReturnType;
import EnemyLevelScaling.GeneticProgrammer.Mk6.Enums.UnaryNumOp;
import EnemyLevelScaling.GeneticProgrammer.Mk6.Nodes.Interfaces.DubNode;
import EnemyLevelScaling.GeneticProgrammer.Mk6.Nodes.Interfaces.Node;

public class UnaryNum extends BaseNode implements DubNode
{
	private UnaryNumOp Op;

	public UnaryNum()
	{
		this(RandyAutobot.getRandyUnaryNumOp());
	}

	public UnaryNum(UnaryNumOp inOp)
	{
		super(NodeType.UnaryNum);
		this.Op = inOp;
	}
	
	public UnaryNum(ParentRef StepParent, UnaryNum old, UnaryNumOp inOp, GlobalVars newVars, boolean ApplyMutateChance)
	{
		super(StepParent, NodeType.UnaryNum);
		super.setChildren(new Node[]{
				old.getChildAt(0).cascadeClone(new ParentRef(this, 0), newVars, ApplyMutateChance)
				});
		this.Op = inOp;
	}

	@Override
	public ReturnType getReturnType()
	{
		return super.getReturnType();
	}

	@Override
	public ReturnType[] getExpectedArgumentsFromChildAt(int index)
	{
		return super.getExpectedArgumentsFromChildAt(index);
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
	public Node cascadeClone(ParentRef StepParent, GlobalVars newVars, boolean ApplyMutateChance)
	{
		if (ApplyMutateChance && RandyAutobot.flipCoin(ConstantAutobot.getMutationChance()))
		{
			return new UnaryNum(StepParent, this, RandyAutobot.getRandyUnaryNumOp(), newVars, ApplyMutateChance);
		}
		else
		{
			return new UnaryNum(StepParent, this, this.Op, newVars, ApplyMutateChance);
		}
	}
	
	@Override
	public int getNumChildren()
	{
		return super.getNumChildren();
	}
}
