package EnemyLevelScaling.GeneticProgrammer.Mk2.Nodes;

import EnemyLevelScaling.GeneticProgrammer.Mk2.NodeType;

public class Node
{
	protected Node Parent;
	protected int ParentIndex;
	protected NodeType Type;
	protected Node[] Children;

	public Node()
	{
		this.Parent = null;
		this.ParentIndex = 0;
		this.Type = null;
		this.Children = null;
	}

	public NodeType getType()
	{
		return this.Type;
	}
	
	public int getValue()
	{
		return -1;
	}
}
