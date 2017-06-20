package EnemyLevelScaling.GeneticProgrammer.Mk5.Nodes.OpNodes;

import EnemyLevelScaling.GeneticProgrammer.Mk5.BaseNode;
import EnemyLevelScaling.GeneticProgrammer.Mk5.ConstantAutobot;
import EnemyLevelScaling.GeneticProgrammer.Mk5.GlobalVars;
import EnemyLevelScaling.GeneticProgrammer.Mk5.HelperAutobot;
import EnemyLevelScaling.GeneticProgrammer.Mk5.ParentRef;
import EnemyLevelScaling.GeneticProgrammer.Mk5.RandyAutobot;
import EnemyLevelScaling.GeneticProgrammer.Mk5.Enums.ReturnType;
import EnemyLevelScaling.GeneticProgrammer.Mk5.Enums.UnaryBoolOp;
import EnemyLevelScaling.GeneticProgrammer.Mk5.Nodes.Interfaces.BoolNode;
import EnemyLevelScaling.GeneticProgrammer.Mk5.Nodes.Interfaces.Node;

public class UnaryBool extends BaseNode implements BoolNode
{
	private ReturnType Returns;
	private ReturnType[][] Returnables;

	private UnaryBoolOp Op;
	
	public UnaryBool()
	{
		this(RandyAutobot.getRandyUnaryBoolOp());
	}

	public UnaryBool(UnaryBoolOp inOp)
	{
		super();
		this.Returns = ReturnType.Boolean;
		this.Returnables = new ReturnType[][] { { ReturnType.Boolean } };

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

		result.append("UnaryBool(");
		result.append(this.Op.toString());
		result.append(ConstantAutobot.getDelimiter());
		result.append(super.getChildAt(0).toStringBuilder());
		result.append(")");

		return result;
	}

	@Override
	public boolean getValue()
	{
		boolean result;
		boolean a = HelperAutobot.getBoolFromNode(super.getChildAt(0));

		switch (this.Op)
		{
		case NOT:
			{
				result = !a;
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
			result = new UnaryBool();
		}
		else
		{
			result = new UnaryBool(this.Op);
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
