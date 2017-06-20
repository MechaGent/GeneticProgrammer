package EnemyLevelScaling.GeneticProgrammer.Mk5.Nodes.OpNodes;

import EnemyLevelScaling.GeneticProgrammer.Mk5.BaseNode;
import EnemyLevelScaling.GeneticProgrammer.Mk5.ConstantAutobot;
import EnemyLevelScaling.GeneticProgrammer.Mk5.GlobalVars;
import EnemyLevelScaling.GeneticProgrammer.Mk5.HelperAutobot;
import EnemyLevelScaling.GeneticProgrammer.Mk5.ParentRef;
import EnemyLevelScaling.GeneticProgrammer.Mk5.RandyAutobot;
import EnemyLevelScaling.GeneticProgrammer.Mk5.Enums.BinaryNumBoolOp;
import EnemyLevelScaling.GeneticProgrammer.Mk5.Enums.ReturnType;
import EnemyLevelScaling.GeneticProgrammer.Mk5.Nodes.Interfaces.BoolNode;
import EnemyLevelScaling.GeneticProgrammer.Mk5.Nodes.Interfaces.Node;

public class BinaryNumBool extends BaseNode implements BoolNode
{
	private ReturnType Returns;
	private ReturnType[][] Returnables;

	private BinaryNumBoolOp Op;

	public BinaryNumBool()
	{
		this(RandyAutobot.getRandyBinaryNumBoolOp());
	}

	public BinaryNumBool(BinaryNumBoolOp inOp)
	{
		super();
		this.Returns = ReturnType.Boolean;
		this.Returnables = new ReturnType[][] { { ReturnType.Integer, ReturnType.Double },
				{ ReturnType.Integer, ReturnType.Double } };

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

		result.append("BinaryNumBool(");
		result.append(this.Op.toString());
		result.append(ConstantAutobot.getDelimiter());
		result.append(super.getChildAt(0).toStringBuilder());
		result.append(ConstantAutobot.getDelimiter());
		result.append(super.getChildAt(1).toStringBuilder());
		result.append(")");

		return result;
	}

	@Override
	public boolean getValue()
	{
		boolean result;
		double a = HelperAutobot.getDubFromNode(super.getChildAt(0));
		double b = HelperAutobot.getDubFromNode(super.getChildAt(1));

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
	public Node cascadeClone(ParentRef StepParent, GlobalVars newVars, boolean ApplyMutateChance)
	{
		Node result;

		if (ApplyMutateChance && RandyAutobot.flipCoin(ConstantAutobot.getMutationChance()))
		{
			result = new BinaryNumBool();
		}
		else
		{
			result = new BinaryNumBool(this.Op);
		}

		result.setParent(StepParent);

		Node[] StepChildren = new Node[this.getNumChildren()];

		for (int i = 0; i < StepChildren.length; i++)
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
