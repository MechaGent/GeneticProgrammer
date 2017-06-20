package EnemyLevelScaling.GeneticProgrammer.Mk1.Nodes;

import EnemyLevelScaling.GeneticProgrammer.Mk1.ConstantAutobot;
import EnemyLevelScaling.GeneticProgrammer.Mk1.Enums.NodeType;
import Random.MkNeg01.XorShiftStarRng.Static.XorShiftStar1024;

public class MultNode implements ExpressionNode
{
	private ExpressionNode LeftNode;
	private ExpressionNode RightNode;
	private boolean isMultOp;
	
	public MultNode(boolean inSign)
	{
		this.isMultOp = inSign;
	}
	
	public MultNode(ExpressionNode inLeft, boolean inSign, ExpressionNode inRight)
	{
		this.LeftNode = inLeft;
		this.RightNode = inRight;
		this.isMultOp = inSign;
	}
	
	@Override
	public NodeType getType()
	{
		return NodeType.Mult;
	}

	@Override
	public int getValue()
	{
		if(this.isMultOp)
		{
			return this.LeftNode.getValue() * this.RightNode.getValue();
		}
		else
		{
			return this.LeftNode.getValue() / this.RightNode.getValue();
		}
	}
	
	@Override
	public StringBuilder toFormattedString()
	{
		StringBuilder result = new StringBuilder("(" + this.LeftNode.toFormattedString());
		
		if(this.isMultOp)
		{
			result.append("*");
		}
		else
		{
			result.append("/");
		}
		result.append(this.RightNode.toFormattedString() + ")");
		
		return result;
	}
	
	@Override
	public void SetInputNodes(ExpressionNode[] InputNodes)
	{
		if(InputNodes.length == 2)
		{
			this.LeftNode = InputNodes[0];
			this.RightNode = InputNodes[1];
		}
	}

	@Override
	public ExpressionNode Copy()
	{
		return new MultNode(this.LeftNode.Copy(), this.isMultOp, this.RightNode.Copy());
	}

	@Override
	public void MutateSelf()
	{
		if(XorShiftStar1024.flipCoin(ConstantAutobot.getMutationChance()))
		{
			this.isMultOp = !this.isMultOp;
		}
	}

	@Override
	public ExpressionNode getRandomChildNode()
	{
		if(XorShiftStar1024.flipCoin())
		{
			return this.LeftNode;
		}
		else
		{
			return this.RightNode;
		}
	}
}
