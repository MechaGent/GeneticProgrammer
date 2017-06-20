package EnemyLevelScaling.GeneticProgrammer.Mk8;

import EnemyLevelScaling.GeneticProgrammer.Mk8.Autobots.ConstantAutobot;
import EnemyLevelScaling.GeneticProgrammer.Mk8.Autobots.WeeblAutobot;
import EnemyLevelScaling.GeneticProgrammer.Mk8.Nodes.AbstractClasses.BaseNode;
import EnemyLevelScaling.GeneticProgrammer.Mk8.Nodes.AbstractClasses.Recyclable;

/**
 * This class provides a reference back to the parent of the ParentRef's node, along with the index in which that node is stored;
 * @author MechaGent
 *
 */
public class ParentRef implements Recyclable
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
	
	@Override
	public void RecycleMe()
	{
		this.Parent = null;
		this.Index = ConstantAutobot.getNullIntVal();
		WeeblAutobot.Recycle(this);
	}
	
	public ParentRef unRecycleMe(BaseNode inParent, int inIndex)
	{
		this.Parent = inParent;
		this.Index = inIndex;
		
		return this;
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
