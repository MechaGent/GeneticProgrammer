package EnemyLevelScaling.GeneticProgrammer.Mk5.Nodes.OpNodes;

import EnemyLevelScaling.GeneticProgrammer.Mk5.BaseNode;
import EnemyLevelScaling.GeneticProgrammer.Mk5.ConstantAutobot;
import EnemyLevelScaling.GeneticProgrammer.Mk5.GlobalVars;
import EnemyLevelScaling.GeneticProgrammer.Mk5.HelperAutobot;
import EnemyLevelScaling.GeneticProgrammer.Mk5.ParentRef;
import EnemyLevelScaling.GeneticProgrammer.Mk5.RandyAutobot;
import EnemyLevelScaling.GeneticProgrammer.Mk5.Enums.BinaryBoolBoolOp;
import EnemyLevelScaling.GeneticProgrammer.Mk5.Enums.ReturnType;
import EnemyLevelScaling.GeneticProgrammer.Mk5.Nodes.Interfaces.BoolNode;
import EnemyLevelScaling.GeneticProgrammer.Mk5.Nodes.Interfaces.Node;

public class BinaryBoolBool extends BaseNode implements BoolNode
{
	private ReturnType Returns;
	private ReturnType[][] Returnables;

	private BinaryBoolBoolOp Op;

	public BinaryBoolBool()
	{
		this(RandyAutobot.getRandyBinaryBoolBoolOp());
	}
	
	public BinaryBoolBool(BinaryBoolBoolOp inOp)
	{
		super();
		this.Returns = ReturnType.Double;
		this.Returnables = new ReturnType[][] { { ReturnType.Boolean }, { ReturnType.Boolean } };

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

		result.append("BinaryBoolBool(");
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
		Node result;
		
		if(ApplyMutateChance && RandyAutobot.flipCoin(ConstantAutobot.getMutationChance()))
		{
			result = new BinaryBoolBool();
		}
		else
		{
			result = new BinaryBoolBool(this.Op);
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
