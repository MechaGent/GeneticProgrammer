package EnemyLevelScaling.GeneticProgrammer.Mk4.Nodes.Mathy;

import EnemyLevelScaling.GeneticProgrammer.Mk4.Nodes.Standards.FuncNode;
import EnemyLevelScaling.GeneticProgrammer.Mk4.Nodes.Standards.ParentRef;
import EnemyLevelScaling.GeneticProgrammer.Mk4.Nodes.Standards.Enums.CurvesOpType;
import EnemyLevelScaling.GeneticProgrammer.Mk4.Nodes.Standards.Enums.NodeType;

public class CurvesNode implements FuncNode
{
	private NodeType Type;
	private ParentRef Parent;
	private FuncNode Child;
	private CurvesOpType CurveType;
	
	public CurvesNode()
	{
		this(null, null, CurvesOpType.getRandomCurvesType());
	}
	
	public CurvesNode(ParentRef inParent, FuncNode inChild, CurvesOpType inCurveType)
	{
		this.Type = NodeType.Curves;
		this.Parent = inParent;
		this.Child = inChild;
		this.CurveType = inCurveType;
	}
	
	@Override
	public void setParentRef(ParentRef inParent)
	{
		this.Parent = inParent;
	}

	@Override
	public void setChildren(FuncNode[] inChildren)
	{
		this.Child = inChildren[0];
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
		return this.Child;
	}

	@Override
	public Object getValue()
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public StringBuilder toStringBuilder()
	{
		return new StringBuilder(this.CurveType.toString() + "(" + this.Child.toStringBuilder() + ")");
	}

}
