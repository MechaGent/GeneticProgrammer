package EnemyLevelScaling.GeneticProgrammer.Mk1.Nodes;

import EnemyLevelScaling.GeneticProgrammer.Mk1.Enums.NodeType;
import Random.MkNeg01.XorShiftStarRng.Static.XorShiftStar1024;

public class PowerNode implements ExpressionNode
{
	private ExpressionNode Base;
	private ExpressionNode Expon;
	
	public PowerNode()
	{
		this.Base = null;
		this.Expon = null;
	}
	
	public PowerNode(ExpressionNode inBase, ExpressionNode inExpon)
	{
		this.Base = inBase;
		this.Expon = inExpon;
	}
	
	@Override
	public NodeType getType()
	{
		return NodeType.Power;
	}

	@Override
	public int getValue()
	{
		return (int) Math.pow(this.Base.getValue(), this.Expon.getValue());
	}

	@Override
	public StringBuilder toFormattedString()
	{
		return new StringBuilder(this.Base.getValue() + "^" + this.Expon.getValue());
	}

	@Override
	public void SetInputNodes(ExpressionNode[] InputNodes)
	{
		if(InputNodes.length == 2)
		{
			this.Base = InputNodes[0];
			this.Expon = InputNodes[1];
		}
	}

	@Override
	public ExpressionNode Copy()
	{
		return new PowerNode(this.Base.Copy(), this.Expon.Copy());
	}

	@Override
	public void MutateSelf()
	{
		
	}

	@Override
	public ExpressionNode getRandomChildNode()
	{
		if(XorShiftStar1024.flipCoin())
		{
			return this.Base;
		}
		else
		{
			return this.Expon;
		}
	}
}
