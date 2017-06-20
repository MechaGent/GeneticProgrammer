package EnemyLevelScaling.GeneticProgrammer.Mk7;

import EnemyLevelScaling.GeneticProgrammer.Mk7.Nodes.AbstractClasses.BaseNode;

/**
 * This class provides a reference back to the parent of the ParentRef's node, along with the index in which that node is stored;
 * @author MechaGent
 *
 */
public class ParentRef
{
	private BaseNode Parent;
	private int Index;
	
	public ParentRef(BaseNode inParent, int inIndex)
	{
		if(inParent == null)
		{
			System.out.println("Null Parent initialized");
		}
		this.Parent = inParent;
		this.Index = inIndex;
	}

	public BaseNode getParent()
	{
		return Parent;
	}

	public int getIndex()
	{
		return Index;
	}
	
	@Override
	public String toString()
	{
		return "Parent: " + this.Parent + "at index: " + this.Index;
	}
	
	public void setAtRef(BaseNode Child)
	{
		this.Parent.setChildAt(Index, Child);
	}
}
