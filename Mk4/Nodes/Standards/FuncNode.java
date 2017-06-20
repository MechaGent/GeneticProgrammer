package EnemyLevelScaling.GeneticProgrammer.Mk4.Nodes.Standards;

import EnemyLevelScaling.GeneticProgrammer.Mk4.Nodes.Standards.Enums.NodeType;

public interface FuncNode
{
	public void setParentRef(ParentRef inParent);
	public void setChildren(FuncNode[] inChildren);
	public NodeType getType();
	public void dooWop();
	public ParentRef getParent();
	public FuncNode getChildAtIndex(byte in);
	public Object getValue();
	public StringBuilder toStringBuilder();
}
