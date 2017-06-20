package EnemyLevelScaling.GeneticProgrammer.Mk9;

import EnemyLevelScaling.GeneticProgrammer.Mk9.Abstracts.BaseNode;
import EnemyLevelScaling.GeneticProgrammer.Mk9.Abstracts.Recyclable;
import EnemyLevelScaling.GeneticProgrammer.Mk9.Autobots.ConstantAutobot;

public class ParentRef implements Recyclable
{
	private BaseNode Parent;
	private int Index;
	
	public ParentRef(BaseNode inParent, int inIndex)
	{
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
	public void RecycleMe()
	{
		this.Parent = null;
		this.Index = ConstantAutobot.getNullInt();
	}
	
	public ParentRef unRecycleMe(BaseNode inParent, int inIndex)
	{
		this.Parent = inParent;
		this.Index = inIndex;
		
		return this;
	}
}
