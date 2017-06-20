package EnemyLevelScaling.GeneticProgrammer.Mk6.Nodes.OpNodes;

import EnemyLevelScaling.GeneticProgrammer.Mk6.BaseNode;
import EnemyLevelScaling.GeneticProgrammer.Mk6.GlobalVars;
import EnemyLevelScaling.GeneticProgrammer.Mk6.ParentRef;
import EnemyLevelScaling.GeneticProgrammer.Mk6.Autobots.ConstantAutobot;
import EnemyLevelScaling.GeneticProgrammer.Mk6.Autobots.HelperAutobot;
import EnemyLevelScaling.GeneticProgrammer.Mk6.Autobots.RandyAutobot;
import EnemyLevelScaling.GeneticProgrammer.Mk6.Enums.UnaryBoolOp;
import EnemyLevelScaling.GeneticProgrammer.Mk6.Enums.NodeType;
import EnemyLevelScaling.GeneticProgrammer.Mk6.Enums.ReturnType;
import EnemyLevelScaling.GeneticProgrammer.Mk6.Nodes.Interfaces.BoolNode;
import EnemyLevelScaling.GeneticProgrammer.Mk6.Nodes.Interfaces.Node;

public class UnaryBool extends BaseNode implements BoolNode
{
	private UnaryBoolOp Op;
	
	public UnaryBool()
	{
		this(RandyAutobot.getRandyUnaryBoolOp());
	}

	public UnaryBool(UnaryBoolOp inOp)
	{
		super(NodeType.UnaryBool);
		this.Op = inOp;
	}
	
	public UnaryBool(ParentRef StepParent, UnaryBool old, UnaryBoolOp inOp, GlobalVars newVars, boolean ApplyMutateChance)
	{
		super(StepParent, NodeType.UnaryBool);
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
		in.append("UnaryBool(");
		in.append(this.Op.toString());
		in.append(ConstantAutobot.getArgDelimiter());
		super.getChildAt(0).toStringBuilder(in);
		in.append(")");

		return in;
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
		if (ApplyMutateChance && RandyAutobot.flipCoin(ConstantAutobot.getMutationChance()))
		{
			return new UnaryBool(StepParent, this, RandyAutobot.getRandyUnaryBoolOp(), newVars, ApplyMutateChance);
		}
		else
		{
			return new UnaryBool(StepParent, this, this.Op, newVars, ApplyMutateChance);
		}
	}
	
	@Override
	public int getNumChildren()
	{
		return super.getNumChildren();
	}
}
