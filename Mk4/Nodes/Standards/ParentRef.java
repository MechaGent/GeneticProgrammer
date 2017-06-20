package EnemyLevelScaling.GeneticProgrammer.Mk4.Nodes.Standards;

public class ParentRef
{
	private FuncNode Parent;
	private byte ParentIndex;
	
	public ParentRef(FuncNode inPar, byte inIndex)
	{
		this.Parent = inPar;
		this.ParentIndex = inIndex;
	}
	
	public FuncNode getParent()
	{
		return this.Parent;
	}
	
	public byte getParentIndex()
	{
		return this.ParentIndex;
	}
}
