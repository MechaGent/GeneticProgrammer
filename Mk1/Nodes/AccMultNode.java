package EnemyLevelScaling.GeneticProgrammer.Mk1.Nodes;

import EnemyLevelScaling.GeneticProgrammer.Mk1.Accumulator;
import EnemyLevelScaling.GeneticProgrammer.Mk1.ConstantAutobot;
import EnemyLevelScaling.GeneticProgrammer.Mk1.Enums.NodeType;
import Random.MkNeg01.XorShiftStarRng.Static.XorShiftStar1024;

public class AccMultNode implements ExpressionNode
{
	private Accumulator Acc;
	private ExpressionNode RightNode;
	private boolean isAccMultOp;

	public AccMultNode(Accumulator inLeft, boolean inSign)
	{
		this.Acc = inLeft;
		this.isAccMultOp = inSign;
	}
	
	public AccMultNode(Accumulator inLeft, boolean inSign, ExpressionNode inRight)
	{
		this.Acc = inLeft;
		this.RightNode = inRight;
		this.isAccMultOp = inSign;
	}

	@Override
	public NodeType getType()
	{
		return NodeType.AccMult;
	}

	@Override
	public int getValue()
	{
		if (this.isAccMultOp)
		{
			return this.Acc.multAccBy(this.RightNode.getValue());
		} else
		{
			return this.Acc.divAccBy(this.RightNode.getValue());
		}
	}

	@Override
	public StringBuilder toFormattedString()
	{
		StringBuilder result = new StringBuilder("(Accumulator");

		if (this.isAccMultOp)
		{
			result.append("*");
		} else
		{
			result.append("/");
		}
		result.append(this.RightNode.toFormattedString() + ")");

		return result;
	}
	
	@Override
	public void SetInputNodes(ExpressionNode[] InputNodes)
	{
		if(InputNodes.length == 1)
		{
			this.RightNode = InputNodes[0];
		}
	}

	@Override
	public ExpressionNode Copy()
	{
		return new AccMultNode(new Accumulator(), this.isAccMultOp, this.RightNode.Copy());
	}

	@Override
	public void MutateSelf()
	{
		if(XorShiftStar1024.flipCoin(ConstantAutobot.getMutationChance()))
		{
			this.isAccMultOp = !this.isAccMultOp;
		}
	}

	@Override
	public ExpressionNode getRandomChildNode()
	{
		return this.RightNode;
	}
}
