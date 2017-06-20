package EnemyLevelScaling.GeneticProgrammer.Mk4.Nodes.Terminals;

import EnemyLevelScaling.GeneticProgrammer.Mk4.Autobots.RandyAutobot;
import EnemyLevelScaling.GeneticProgrammer.Mk4.Nodes.Standards.FuncNode;
import EnemyLevelScaling.GeneticProgrammer.Mk4.Nodes.Standards.ParentRef;
import EnemyLevelScaling.GeneticProgrammer.Mk4.Nodes.Standards.Enums.NodeType;

public class DubConstNode implements FuncNode
{
	private NodeType Type;
	private ParentRef Parent;
	
	private double Const;
	
	public DubConstNode()
	{
		this(null, RandyAutobot.getRandomDubConst());
	}
	
	public DubConstNode(ParentRef inParent, double inConst)
	{
		this.Type = NodeType.DubConst;
		this.Parent = inParent;
		this.Const = inConst;
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
		return this.Const;
	}

	@Override
	public StringBuilder toStringBuilder()
	{
		return new StringBuilder(this.Const + "");
	}
}
