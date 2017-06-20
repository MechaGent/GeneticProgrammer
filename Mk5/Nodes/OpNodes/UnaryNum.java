package EnemyLevelScaling.GeneticProgrammer.Mk5.Nodes.OpNodes;

import EnemyLevelScaling.GeneticProgrammer.Mk5.BaseNode;
import EnemyLevelScaling.GeneticProgrammer.Mk5.ConstantAutobot;
import EnemyLevelScaling.GeneticProgrammer.Mk5.GlobalVars;
import EnemyLevelScaling.GeneticProgrammer.Mk5.HelperAutobot;
import EnemyLevelScaling.GeneticProgrammer.Mk5.ParentRef;
import EnemyLevelScaling.GeneticProgrammer.Mk5.RandyAutobot;
import EnemyLevelScaling.GeneticProgrammer.Mk5.Enums.ReturnType;
import EnemyLevelScaling.GeneticProgrammer.Mk5.Enums.UnaryNumOp;
import EnemyLevelScaling.GeneticProgrammer.Mk5.Nodes.Interfaces.DubNode;
import EnemyLevelScaling.GeneticProgrammer.Mk5.Nodes.Interfaces.Node;

public class UnaryNum extends BaseNode implements DubNode
{
	private ReturnType Returns;
	private ReturnType[][] Returnables;

	private UnaryNumOp Op;

	public UnaryNum()
	{
		this(RandyAutobot.getRandyUnaryNumOp());
	}
	
	public UnaryNum(UnaryNumOp inOp)
	{
		super();
		this.Returns = ReturnType.Double;
		this.Returnables = new ReturnType[][] { { ReturnType.Integer, ReturnType.Double } };

		this.Op = inOp;
	}

	@Override
	public ReturnType getReturnType()
	{
		return this.Returns;
	}

	@Override
	public ReturnType[] getAllowedReturnTypesForChildAt(int index)
	{
		return this.Returnables[index];
	}

	@Override
	public StringBuilder toStringBuilder()
	{
		StringBuilder result = new StringBuilder();

		result.append("UnaryNum(");
		result.append(this.Op.toString());
		result.append(ConstantAutobot.getDelimiter());
		result.append(super.getChildAt(0).toStringBuilder());
		result.append(")");

		return result;
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
		Node result;
		
		if(ApplyMutateChance && RandyAutobot.flipCoin(ConstantAutobot.getMutationChance()))
		{
			result = new UnaryNum();
		}
		else
		{
			result = new UnaryNum(this.Op);
		}
		
		result.setParent(StepParent);
		
		Node[] StepChildren = new Node[this.getNumChildren()];
		
		for(int i = 0; i < StepChildren.length; i++)
		{
			StepChildren[i] = super.getChildAt(i).cascadeClone(new ParentRef(this, i), newVars, ApplyMutateChance);
		}
		
		result.setChildren(StepChildren);
		
		return result;
	}
	
	@Override
	public int getNumChildren()
	{
		return this.Returnables.length;
	}
}
