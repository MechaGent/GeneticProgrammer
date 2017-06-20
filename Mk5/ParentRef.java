package EnemyLevelScaling.GeneticProgrammer.Mk5;

import EnemyLevelScaling.GeneticProgrammer.Mk5.Nodes.Interfaces.Node;

public class ParentRef
{
	private Node Parent;
	private int Index;
	
	public ParentRef(Node inParent, int inIndex)
	{
		this.Parent = inParent;
		this.Index = inIndex;
	}

	public Node getParent()
	{
		return Parent;
	}

	public int getIndex()
	{
		return Index;
	}
	
	public String toString()
	{
		return "Parent: " + this.Parent + "at index: " + this.Index;
	}
}
