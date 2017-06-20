package EnemyLevelScaling.GeneticProgrammer.Mk4.Nodes.Terminals;

import EnemyLevelScaling.GeneticProgrammer.Mk4.Autobots.RandyAutobot;
import EnemyLevelScaling.GeneticProgrammer.Mk4.Nodes.Standards.FuncNode;
import EnemyLevelScaling.GeneticProgrammer.Mk4.Nodes.Standards.ParentRef;
import EnemyLevelScaling.GeneticProgrammer.Mk4.Nodes.Standards.Enums.NodeType;

public class IntConstNode implements FuncNode
{
	private NodeType Type;
	private ParentRef Parent;
	
	private int Const;
	
	public IntConstNode()
	{
		this(null, RandyAutobot.getRandomIntConst());
	}
	
	public IntConstNode(ParentRef inParent, int inConst)
	{
		this.Type = NodeType.IntConst;
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
