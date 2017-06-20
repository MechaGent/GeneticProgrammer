package EnemyLevelScaling.GeneticProgrammer.Mk4.Nodes.Terminals;

import EnemyLevelScaling.GeneticProgrammer.Mk4.Nodes.DubAccumulator;
import EnemyLevelScaling.GeneticProgrammer.Mk4.Nodes.Standards.Accumulator;
import EnemyLevelScaling.GeneticProgrammer.Mk4.Nodes.Standards.FuncNode;
import EnemyLevelScaling.GeneticProgrammer.Mk4.Nodes.Standards.ParentRef;
import EnemyLevelScaling.GeneticProgrammer.Mk4.Nodes.Standards.Enums.NodeType;

public class ReadAcc implements FuncNode
{
	private NodeType Type;
	private ParentRef Parent;
	
	private Accumulator<?> Acc;
	
	public ReadAcc(Accumulator<?> inAcc)
	{
		this(null, inAcc);
	}
	
	public ReadAcc(ParentRef inParent, Accumulator<?> inAcc)
	{
		if(inAcc instanceof DubAccumulator)
		{
			this.Type = NodeType.ReadDubAcc;
		}
		else
		{
			this.Type = NodeType.ReadIntAcc;
		}
		
		this.Parent = inParent;
		this.Acc = inAcc;
	}
	
	@Override
	public void setParentRef(ParentRef inParent)
	{
		this.Parent = inParent;
	}

	@Override
	public void setChildren(FuncNode[] inChildren)
	{
		
	}

	@Override
	public NodeType getType()
	{
		return this.Type;
	}

	@Override
	public void dooWop()
	{
		
	}

	@Override
	public ParentRef getParent()
	{
		return this.Parent;
	}

	@Override
	public FuncNode getChildAtIndex(byte in)
	{
		return null;
	}

	@Override
	public Object getValue()
	{
		return this.Acc.getAcc();
	}

	@Override
	public StringBuilder toStringBuilder()
	{
		return new StringBuilder(this.Type.toString() + "()");
	}
}
