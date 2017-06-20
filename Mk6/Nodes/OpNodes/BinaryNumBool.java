package EnemyLevelScaling.GeneticProgrammer.Mk6.Nodes.OpNodes;

import EnemyLevelScaling.GeneticProgrammer.Mk6.BaseNode;
import EnemyLevelScaling.GeneticProgrammer.Mk6.GlobalVars;
import EnemyLevelScaling.GeneticProgrammer.Mk6.ParentRef;
import EnemyLevelScaling.GeneticProgrammer.Mk6.Autobots.ConstantAutobot;
import EnemyLevelScaling.GeneticProgrammer.Mk6.Autobots.HelperAutobot;
import EnemyLevelScaling.GeneticProgrammer.Mk6.Autobots.RandyAutobot;
import EnemyLevelScaling.GeneticProgrammer.Mk6.Enums.BinaryNumBoolOp;
import EnemyLevelScaling.GeneticProgrammer.Mk6.Enums.NodeType;
import EnemyLevelScaling.GeneticProgrammer.Mk6.Enums.ReturnType;
import EnemyLevelScaling.GeneticProgrammer.Mk6.Nodes.Interfaces.BoolNode;
import EnemyLevelScaling.GeneticProgrammer.Mk6.Nodes.Interfaces.Node;

public class BinaryNumBool extends BaseNode implements BoolNode
{
	private BinaryNumBoolOp Op;

	public BinaryNumBool()
	{
		this(RandyAutobot.getRandyBinaryNumBoolOp());
	}
	
	public BinaryNumBool(BinaryNumBoolOp inOp)
	{
		super(NodeType.BinaryNumBool);
		this.Op = inOp;
	}
	
	public BinaryNumBool(ParentRef StepParent, BinaryNumBool old, BinaryNumBoolOp inOp, GlobalVars newVars, boolean ApplyMutateChance)
	{
		super(StepParent, NodeType.BinaryNumBool);
		super.setChildren(new Node[]{
				old.getChildAt(0).cascadeClone(new ParentRef(this, 0), newVars, ApplyMutateChance),
				old.getChildAt(1).cascadeClone(new ParentRef(this, 1), newVars, ApplyMutateChance)
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
	public StringBuilder toStringBuilder(StringBuilder result)
	{
		result.append("BinaryNumBool(");
		result.append(this.Op.toString());
		result.append(ConstantAutobot.getArgDelimiter());
		super.getChildAt(0).toStringBuilder(result);
		result.append(ConstantAutobot.getArgDelimiter());
		super.getChildAt(1).toStringBuilder(result);
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
		if (ApplyMutateChance && RandyAutobot.flipCoin(ConstantAutobot.getMutationChance()))
		{
			return new BinaryNumBool(StepParent, this, RandyAutobot.getRandyBinaryNumBoolOp(), newVars, ApplyMutateChance);
		}
		else
		{
			return new BinaryNumBool(StepParent, this, this.Op, newVars, ApplyMutateChance);
		}
	}

	@Override
	public int getNumChildren()
	{
		return super.getNumChildren();
	}
}
