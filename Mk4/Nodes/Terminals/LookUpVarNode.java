package EnemyLevelScaling.GeneticProgrammer.Mk4.Nodes.Terminals;

import EnemyLevelScaling.GeneticProgrammer.Mk4.Autobots.ConstantAutobot;
import EnemyLevelScaling.GeneticProgrammer.Mk4.Autobots.RandyAutobot;
import EnemyLevelScaling.GeneticProgrammer.Mk4.Nodes.Standards.FuncNode;
import EnemyLevelScaling.GeneticProgrammer.Mk4.Nodes.Standards.ParentRef;
import EnemyLevelScaling.GeneticProgrammer.Mk4.Nodes.Standards.Enums.NodeType;

public class LookUpVarNode implements FuncNode
{
	private NodeType Type;
	private ParentRef Parent;
	
	private String Name;
	
	public LookUpVarNode()
	{
		this(null, RandyAutobot.getRandomLookUpVarName());
	}
	
	public LookUpVarNode(ParentRef inParent, String inName)
	{
		this.Type = NodeType.LookUpVar;
		this.Parent = inParent;
		this.Name = inName;
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
		return ConstantAutobot.getVariable(this.Name);
	}

	@Override
	public StringBuilder toStringBuilder()
	{
		return new StringBuilder(this.Name);
	}
	
}
