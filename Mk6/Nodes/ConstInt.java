package EnemyLevelScaling.GeneticProgrammer.Mk6.Nodes;

import EnemyLevelScaling.GeneticProgrammer.Mk6.BaseNode;
import EnemyLevelScaling.GeneticProgrammer.Mk6.GlobalVars;
import EnemyLevelScaling.GeneticProgrammer.Mk6.ParentRef;
import EnemyLevelScaling.GeneticProgrammer.Mk6.Autobots.ConstantAutobot;
import EnemyLevelScaling.GeneticProgrammer.Mk6.Autobots.RandyAutobot;
import EnemyLevelScaling.GeneticProgrammer.Mk6.Enums.NodeType;
import EnemyLevelScaling.GeneticProgrammer.Mk6.Enums.ReturnType;
import EnemyLevelScaling.GeneticProgrammer.Mk6.Nodes.Interfaces.IntNode;
import EnemyLevelScaling.GeneticProgrammer.Mk6.Nodes.Interfaces.Node;

public class ConstInt extends BaseNode implements IntNode
{
	private int Value;

	public ConstInt()
	{
		this(RandyAutobot.getRandyInt(-ConstantAutobot.getConstBounds(), ConstantAutobot.getConstBounds()));
	}

	public ConstInt(int inValue)
	{
		super(NodeType.ConstInt);
		this.Value = inValue;
	}

	public ConstInt(int inValue, ParentRef StepParent)
	{
		super(StepParent, NodeType.ConstInt);
		this.Value = inValue;
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
		return in.append(this.Value);
	}

	@Override
	public int getValue()
	{
		return this.Value;
	}

	@Override
	public Node cascadeClone(ParentRef StepParent, GlobalVars newVars, boolean ApplyMutateChance)
	{
		if (ApplyMutateChance && RandyAutobot.flipCoin(ConstantAutobot.getMutationChance()))
		{
			return new ConstInt(
					this.Value + RandyAutobot.getRandyInt(-ConstantAutobot.getConstDeltaMax(), 
					ConstantAutobot.getConstDeltaMax()), 
					StepParent);
		}
		else
		{
			return new ConstInt(this.Value, StepParent);
		}
	}

	@Override
	public int getNumChildren()
	{
		return super.getNumChildren();
	}
}
