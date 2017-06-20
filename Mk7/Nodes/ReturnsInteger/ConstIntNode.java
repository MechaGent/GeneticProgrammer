package EnemyLevelScaling.GeneticProgrammer.Mk7.Nodes.ReturnsInteger;

import EnemyLevelScaling.GeneticProgrammer.Mk7.GlobalVarsPool;
import EnemyLevelScaling.GeneticProgrammer.Mk7.ParentRef;
import EnemyLevelScaling.GeneticProgrammer.Mk7.Autobots.ConstantAutobot;
import EnemyLevelScaling.GeneticProgrammer.Mk7.Autobots.RandyAutobot;
import EnemyLevelScaling.GeneticProgrammer.Mk7.Enums.NodeType;
import EnemyLevelScaling.GeneticProgrammer.Mk7.Nodes.AbstractClasses.BaseNode;
import EnemyLevelScaling.GeneticProgrammer.Mk7.Nodes.AbstractClasses.IntNode;

public class ConstIntNode extends IntNode
{
	private int Value;
	
	public ConstIntNode()
	{
		this(null, RandyAutobot.getRandyInt(-ConstantAutobot.getConstBounds(), ConstantAutobot.getConstBounds()), new BaseNode[0]);
	}
	
	public ConstIntNode(int inValue, ParentRef inParent)
	{
		this(inParent, inValue, new BaseNode[0]);
	}
	
	public ConstIntNode(ParentRef inParent, int inValue, BaseNode[] inChildren)
	{
		super(inParent, NodeType.ConstInt, inChildren);
		this.Value = inValue;
	}

	/**
	 * Constructor used by the parser
	 * @param inValue
	 */
	public ConstIntNode(int inValue)
	{
		this(null, inValue, new BaseNode[0]);
	}
	
	@Override
	public int getValue()
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
			
			result.append("ConstInt");
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
			return new ConstIntNode(
					this.Value + RandyAutobot.getRandyInt(-ConstantAutobot.getConstDeltaMax(), 
					ConstantAutobot.getConstDeltaMax()), 
					StepParent);
		}
		else
		{
			return new ConstIntNode(this.Value, StepParent);
		}
	}
}
