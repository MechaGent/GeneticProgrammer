package EnemyLevelScaling.GeneticProgrammer.Mk7.Nodes.ReturnsBool;

import EnemyLevelScaling.GeneticProgrammer.Mk7.Autobots.ConstantAutobot;
import EnemyLevelScaling.GeneticProgrammer.Mk7.GlobalVarsPool;
import EnemyLevelScaling.GeneticProgrammer.Mk7.ParentRef;
import EnemyLevelScaling.GeneticProgrammer.Mk7.Autobots.RandyAutobot;
import EnemyLevelScaling.GeneticProgrammer.Mk7.Enums.NodeType;
import EnemyLevelScaling.GeneticProgrammer.Mk7.Nodes.AbstractClasses.BaseNode;
import EnemyLevelScaling.GeneticProgrammer.Mk7.Nodes.AbstractClasses.BoolNode;

public class ConstBoolNode extends BoolNode
{
	private boolean Value;
	
	public ConstBoolNode()
	{
		this(null, RandyAutobot.flipCoin(), new BaseNode[0]);
	}
	
	public ConstBoolNode(ParentRef inParent, boolean inValue)
	{
		this(inParent, inValue, new BaseNode[0]);
	}
	
	public ConstBoolNode(ParentRef inParent, boolean inValue, BaseNode[] inChildren)
	{
		super(inParent, NodeType.ConstBool, inChildren);
		this.Value = inValue;
	}
	
	/**
	 * Constructor used by the parser
	 * @param inValue
	 */
	public ConstBoolNode(boolean inValue)
	{
		this(null, inValue, new BaseNode[0]);
	}

	@Override
	public boolean getValue()
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
			
			result.append(this.getNodeType().toString());
			result.append(this.getTotalOffspringAsString());
			result.append("(");
			result.append(this.Value + "");
			result.append(")");
			
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
		if(ApplyMutateChance && RandyAutobot.flipCoin(ConstantAutobot.getMutationChance()))
		{
			return new ConstBoolNode(StepParent, !this.Value);
		}
		else
		{
			return new ConstBoolNode(StepParent, this.Value);
		}
	}
	
}
