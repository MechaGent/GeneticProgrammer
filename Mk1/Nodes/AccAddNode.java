package EnemyLevelScaling.GeneticProgrammer.Mk1.Nodes;

import EnemyLevelScaling.GeneticProgrammer.Mk1.Accumulator;
import EnemyLevelScaling.GeneticProgrammer.Mk1.ConstantAutobot;
import EnemyLevelScaling.GeneticProgrammer.Mk1.Enums.NodeType;
import Random.MkNeg01.XorShiftStarRng.Static.XorShiftStar1024;

public class AccAddNode implements ExpressionNode
{
	private Accumulator Acc;
	private ExpressionNode RightNode;
	private boolean isAccAddOp;
	
	public AccAddNode(Accumulator inLeft, boolean inSign)
	{
		this.Acc = inLeft;
		this.isAccAddOp = inSign;
	}
	
	public AccAddNode(Accumulator inLeft, boolean inSign, ExpressionNode inRight)
	{
		this.Acc = inLeft;
		this.RightNode = inRight;
		this.isAccAddOp = inSign;
	}
	
	@Override
	public NodeType getType()
	{
		return NodeType.AccAdd;
	}

	@Override
	public int getValue()
	{
		if(this.isAccAddOp)
		{
			return this.Acc.addToAcc(this.RightNode.getValue());
		}
		else
		{
			return this.Acc.subFromAcc(this.RightNode.getValue());
		}
	}

	@Override
	public StringBuilder toFormattedString()
	{
		StringBuilder result = new StringBuilder("(Accumulator");
		
		if(this.isAccAddOp)
		{
			result.append("+");
		}
		else
		{
			result.append("-");
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
		return new AccAddNode(new Accumulator(), this.isAccAddOp, this.RightNode.Copy());
	}

	@Override
	public void MutateSelf()
	{
		if(XorShiftStar1024.flipCoin(ConstantAutobot.getMutationChance()))
		{
			this.isAccAddOp = !this.isAccAddOp;
		}
	}

	@Override
	public ExpressionNode getRandomChildNode()
	{
		return this.RightNode;
	}
}
