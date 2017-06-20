package EnemyLevelScaling.GeneticProgrammer.Mk6.Nodes.OpNodes;

import EnemyLevelScaling.GeneticProgrammer.Mk6.BaseNode;
import EnemyLevelScaling.GeneticProgrammer.Mk6.GlobalVars;
import EnemyLevelScaling.GeneticProgrammer.Mk6.ParentRef;
import EnemyLevelScaling.GeneticProgrammer.Mk6.Autobots.ConstantAutobot;
import EnemyLevelScaling.GeneticProgrammer.Mk6.Autobots.HelperAutobot;
import EnemyLevelScaling.GeneticProgrammer.Mk6.Autobots.RandyAutobot;
import EnemyLevelScaling.GeneticProgrammer.Mk6.Enums.BinaryBoolBoolOp;
import EnemyLevelScaling.GeneticProgrammer.Mk6.Enums.NodeType;
import EnemyLevelScaling.GeneticProgrammer.Mk6.Enums.ReturnType;
import EnemyLevelScaling.GeneticProgrammer.Mk6.Nodes.Interfaces.BoolNode;
import EnemyLevelScaling.GeneticProgrammer.Mk6.Nodes.Interfaces.Node;

public class BinaryBoolBool extends BaseNode implements BoolNode
{
	private BinaryBoolBoolOp Op;

	public BinaryBoolBool()
	{
		this(RandyAutobot.getRandyBinaryBoolBoolOp());
	}
	
	public BinaryBoolBool(BinaryBoolBoolOp inOp)
	{
		super(NodeType.BinaryBoolBool);
		this.Op = inOp;
	}
	
	public BinaryBoolBool(ParentRef StepParent, BinaryBoolBool old, BinaryBoolBoolOp inOp, GlobalVars newVars, boolean ApplyMutateChance)
	{
		super(StepParent, NodeType.BinaryBoolBool);
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
		result.append("BinaryBoolBool(");
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
		boolean a = HelperAutobot.getBoolFromNode(super.getChildAt(0));
		boolean b = HelperAutobot.getBoolFromNode(super.getChildAt(1));

		switch (this.Op)
		{
		case AND:
			{
				result = a && b;
				break;
			}
		case NAND:
			{
				result = !(a && b);
				break;
			}
		case NOR:
			{
				result = !(a || b);
				break;
			}
		case OR:
			{
				result = a || b;
				break;
			}
		case XNOR:
			{
				result = (a && b) || !(a && b);
				break;
			}
		case XOR:
			{
				result = (a || b) && !(a && b);
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
		if(ApplyMutateChance && RandyAutobot.flipCoin(ConstantAutobot.getMutationChance()))
		{
			return new BinaryBoolBool(StepParent, this, RandyAutobot.getRandyBinaryBoolBoolOp(), newVars, ApplyMutateChance);
		}
		else
		{
			return new BinaryBoolBool(StepParent, this, this.Op, newVars, ApplyMutateChance);
		}
	}
	
	@Override
	public int getNumChildren()
	{
		return super.getNumChildren();
	}
}
