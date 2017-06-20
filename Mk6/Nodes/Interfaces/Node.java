package EnemyLevelScaling.GeneticProgrammer.Mk6.Nodes.Interfaces;

import EnemyLevelScaling.GeneticProgrammer.Mk6.GlobalVars;
import EnemyLevelScaling.GeneticProgrammer.Mk6.ParentRef;
import EnemyLevelScaling.GeneticProgrammer.Mk6.Enums.NodeType;
import EnemyLevelScaling.GeneticProgrammer.Mk6.Enums.ReturnType;

public interface Node
{
	public NodeType getNodeType();
	public int[] getTotalOffspring();
	public void setTotalOffspringAsInaccurate();
	public ParentRef getParentRef();
	public void setParent(ParentRef parent);
	public Node[] getChildren();
	public int getNumChildren();
	public Node getChildAt(int index);
	public ReturnType[] getExpectedArgumentsFromChildAt(int index);
	public void setChildren(Node[] children);
	public ReturnType getReturnType();
	public StringBuilder toStringBuilder(StringBuilder in);
	public Node cascadeClone(ParentRef StepParent, GlobalVars newVars, boolean ApplyMutateChance);
}
