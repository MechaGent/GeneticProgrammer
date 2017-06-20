package EnemyLevelScaling.GeneticProgrammer.Mk7.Nodes.ReturnsVoid;

import EnemyLevelScaling.GeneticProgrammer.Mk7.GlobalVarsPool;
import EnemyLevelScaling.GeneticProgrammer.Mk7.ParentRef;
import EnemyLevelScaling.GeneticProgrammer.Mk7.Enums.NodeType;
import EnemyLevelScaling.GeneticProgrammer.Mk7.Nodes.AbstractClasses.BaseNode;
import EnemyLevelScaling.GeneticProgrammer.Mk7.Nodes.AbstractClasses.VoidNode;

public class ConstVoidNode extends VoidNode
{
	public ConstVoidNode()
	{
		super(null, NodeType.ConstVoid, new BaseNode[0]);
	}
	
	public ConstVoidNode(ParentRef inParent)
	{
		super(inParent, NodeType.ConstVoid, new BaseNode[0]);
	}
	
	public ConstVoidNode(String in)
	{
		super(null, NodeType.ConstVoid, new BaseNode[0]);
		
		if(in.length() != 0)
		{
			throw new NullPointerException("Erroneous init value: " + in.length());
		}
	}

	@Override
	public void DooWop()
	{
		
	}

	@Override
	public StringBuilder toStringBuilder()
	{
		return new StringBuilder(this.getNodeType().toString() + "()");
	}
	
	@Override
	public StringBuilder toStringBuilder(boolean WantsDiagnosticSyntax)
	{
		return new StringBuilder(this.getNodeType().toString() + this.getTotalOffspringAsString() + "()");
	}
	
	@Override
	public StringBuilder toStringBuilder(StringBuilder in)
	{
		return in.append(this.getNodeType().toString() + "()");
	}

	@Override
	public BaseNode cascadeClone(ParentRef StepParent, GlobalVarsPool newVars, boolean ApplyMutateChance)
	{
		return new ConstVoidNode(StepParent);
	}
}
