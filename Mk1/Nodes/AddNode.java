package EnemyLevelScaling.GeneticProgrammer.Mk1.Nodes;

import EnemyLevelScaling.GeneticProgrammer.Mk1.ConstantAutobot;
import EnemyLevelScaling.GeneticProgrammer.Mk1.Enums.NodeType;
import Random.MkNeg01.XorShiftStarRng.Static.XorShiftStar1024;

public class AddNode implements ExpressionNode
{
	private ExpressionNode LeftNode;
	private ExpressionNode RightNode;
	private boolean isAddOp;
	
	public AddNode()
	{
		this.LeftNode = null;
		this.RightNode = null;
		this.isAddOp = true;
	}
	
	public AddNode(boolean inSign)
	{
		this.isAddOp = inSign;
	}
	
	public AddNode(ExpressionNode inLeft, boolean inSign, ExpressionNode inRight)
	{
		this.LeftNode = inLeft;
		this.RightNode = inRight;
		this.isAddOp = inSign;
	}
	
	@Override
	public NodeType getType()
	{
		return NodeType.Add;
	}

	@Override
	public int getValue()
	{
		if(this.isAddOp)
		{
			return this.LeftNode.getValue() + this.RightNode.getValue();
		}
		else
		{
			return this.LeftNode.getValue() - this.RightNode.getValue();
		}
	}
	
	@Override
	public StringBuilder toFormattedString()
	{
		StringBuilder result = new StringBuilder("(" + this.LeftNode.toFormattedString());
		
		if(this.isAddOp)
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
		if(InputNodes.length == 2)
		{
			this.LeftNode = InputNodes[0];
			this.RightNode = InputNodes[1];
		}
	}

	@Override
	public ExpressionNode Copy()
	{
		return new AddNode(this.LeftNode.Copy(), this.isAddOp, this.RightNode.Copy());
	}

	@Override
	public void MutateSelf()
	{
		if(XorShiftStar1024.flipCoin(ConstantAutobot.getMutationChance()))
		{
			this.isAddOp = !this.isAddOp;
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
