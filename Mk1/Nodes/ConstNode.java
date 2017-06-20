package EnemyLevelScaling.GeneticProgrammer.Mk1.Nodes;

import EnemyLevelScaling.GeneticProgrammer.Mk1.ConstantAutobot;
import EnemyLevelScaling.GeneticProgrammer.Mk1.Enums.NodeType;
import Random.MkNeg01.XorShiftStarRng.Static.XorShiftStar1024;

public class ConstNode implements ExpressionNode
{
	private int Value;
	
	public ConstNode(int inValue)
	{
		this.Value = inValue;
	}
	
	@Override
	public int getValue()
	{
		return this.Value;
	}

	@Override
	public NodeType getType()
	{
		return NodeType.Constant;
	}

	@Override
	public StringBuilder toFormattedString()
	{
		return new StringBuilder("(" + Integer.toString(this.getValue()) + ")");
	}

	@Override
	public void SetInputNodes(ExpressionNode[] InputNodes)
	{
		
	}

	@Override
	public ExpressionNode Copy()
	{
		return new ConstNode(this.Value);
	}

	@Override
	public void MutateSelf()
	{
		if(XorShiftStar1024.flipCoin(ConstantAutobot.getMutationChance()))
		{
			this.Value += XorShiftStar1024.nextInt(-ConstantAutobot.getConstMutIncrem(), ConstantAutobot.getConstMutIncrem());
		}
	}

	@Override
	public ExpressionNode getRandomChildNode()
	{
		return null;
	}
}
