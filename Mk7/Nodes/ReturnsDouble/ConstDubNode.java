package EnemyLevelScaling.GeneticProgrammer.Mk7.Nodes.ReturnsDouble;

import EnemyLevelScaling.GeneticProgrammer.Mk7.GlobalVarsPool;
import EnemyLevelScaling.GeneticProgrammer.Mk7.ParentRef;
import EnemyLevelScaling.GeneticProgrammer.Mk7.Autobots.ConstantAutobot;
import EnemyLevelScaling.GeneticProgrammer.Mk7.Autobots.RandyAutobot;
import EnemyLevelScaling.GeneticProgrammer.Mk7.Enums.NodeType;
import EnemyLevelScaling.GeneticProgrammer.Mk7.Nodes.AbstractClasses.BaseNode;
import EnemyLevelScaling.GeneticProgrammer.Mk7.Nodes.AbstractClasses.DubNode;

public class ConstDubNode extends DubNode
{
	private double Value;
	
	public ConstDubNode()
	{
		this(null, RandyAutobot.getRandyDub(-ConstantAutobot.getConstBounds(), ConstantAutobot.getConstBounds()), new BaseNode[0]);
	}
	
	public ConstDubNode(double inValue, ParentRef inParent)
	{
		this(inParent, inValue, new BaseNode[0]);
	}
	
	public ConstDubNode(ParentRef inParent, double inValue, BaseNode[] inChildren)
	{
		super(inParent, NodeType.ConstDub, inChildren);
		this.Value = inValue;
	}
	
	/**
	 * Constructor used by the parser
	 * @param inValue
	 */
	public ConstDubNode(double inValue)
	{
		this(null, inValue, new BaseNode[0]);
	}

	@Override
	public double getValue()
	{
		return this.Value;
	}
	
	@Override
	public StringBuilder toStringBuilder()
	{
		return new StringBuilder(this.Value + "");
	}

	@Override
	public StringBuilder toStringBuilder(boolean WantsDiagnosticSyntax)
	{
		if(WantsDiagnosticSyntax)
		{
			StringBuilder result = new StringBuilder();
			
			result.append("ConstDub");
			result.append(this.getTotalOffspringAsString());
			result.append("(" + this.Value + ")");
			
			return result;
		}
		else
		{
			return this.toStringBuilder();
		}
	}
	
	@Override
	public StringBuilder toStringBuilder(StringBuilder in)
	{
		return in.append(this.Value);
	}

	@Override
	public BaseNode cascadeClone(ParentRef StepParent, GlobalVarsPool newVars, boolean ApplyMutateChance)
	{
		if (ApplyMutateChance && RandyAutobot.flipCoin(ConstantAutobot.getMutationChance()))
		{
			return new ConstDubNode(
					this.Value + RandyAutobot.getRandyDub(-ConstantAutobot.getConstDeltaMax(), 
					ConstantAutobot.getConstDeltaMax()), 
					StepParent);
		}
		else
		{
			return new ConstDubNode(this.Value, StepParent);
		}
	}
}
