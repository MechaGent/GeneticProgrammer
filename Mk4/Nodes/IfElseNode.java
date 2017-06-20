package EnemyLevelScaling.GeneticProgrammer.Mk4.Nodes;

import EnemyLevelScaling.GeneticProgrammer.Mk4.Nodes.Standards.FuncNode;
import EnemyLevelScaling.GeneticProgrammer.Mk4.Nodes.Standards.ParentRef;
import EnemyLevelScaling.GeneticProgrammer.Mk4.Nodes.Standards.Enums.ComparatorType;
import EnemyLevelScaling.GeneticProgrammer.Mk4.Nodes.Standards.Enums.NodeType;

public class IfElseNode implements FuncNode
{
	private NodeType Type;
	private ParentRef Parent;
	private FuncNode[] Children;
	private ComparatorType CompType;
	
	public IfElseNode()
	{
		this(null, ComparatorType.getRandomComparType(), null);
	}

	public IfElseNode(ParentRef inParent, ComparatorType inCompType, FuncNode[] inChildren)
	{
		this.Type = NodeType.IfElse;
		this.Parent = inParent;
		this.CompType = inCompType;
		this.Children = inChildren;
	}
	
	@Override
	public void setParentRef(ParentRef inParent)
	{
		this.Parent = inParent;
	}

	@Override
	public void setChildren(FuncNode[] inChildren)
	{
		this.Children = inChildren;
	}

	@Override
	public NodeType getType()
	{
		return this.Type;
	}

	@Override
	public void dooWop()
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public ParentRef getParent()
	{
		return this.Parent;
	}

	@Override
	public FuncNode getChildAtIndex(byte in)
	{
		return this.Children[in];
	}

	@Override
	public Object getValue()
	{
		return null;
	}

	@Override
	public StringBuilder toStringBuilder()
	{
		StringBuilder result = new StringBuilder("if(");
		result.append(this.Children[0].toStringBuilder() + this.CompType.getStringForm() + this.Children[1].toStringBuilder() + ")\n{\n");
		result.append(this.Children[2].toStringBuilder() + "\n}\nelse\n{\n" + this.Children[3].toStringBuilder() + "\n}\n");
		return result;
	}

}
