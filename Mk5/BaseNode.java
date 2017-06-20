package EnemyLevelScaling.GeneticProgrammer.Mk5;

import EnemyLevelScaling.GeneticProgrammer.Mk5.Nodes.Interfaces.Node;

public class BaseNode
{
	private ParentRef Parent;
	private Node[] Children;
	
	public BaseNode()
	{
		this.Parent = null;
		this.Children = null;
	}

	public ParentRef getParent()
	{
		return Parent;
	}

	public void setParent(ParentRef parent)
	{
		Parent = parent;
	}

	public Node[] getChildren()
	{
		return Children;
	}
	
	public Node getChildAt(int index)
	{
		return this.Children[index];
	}

	public void setChildren(Node[] children)
	{
		Children = children;
	}
}
