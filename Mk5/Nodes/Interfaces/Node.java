package EnemyLevelScaling.GeneticProgrammer.Mk5.Nodes.Interfaces;

import EnemyLevelScaling.GeneticProgrammer.Mk5.GlobalVars;
import EnemyLevelScaling.GeneticProgrammer.Mk5.ParentRef;
import EnemyLevelScaling.GeneticProgrammer.Mk5.Enums.ReturnType;

public interface Node
{
	public ParentRef getParent();
	public void setParent(ParentRef parent);
	public Node[] getChildren();
	public int getNumChildren();
	public Node getChildAt(int index);
	public ReturnType[] getAllowedReturnTypesForChildAt(int index);
	public void setChildren(Node[] children);
	public ReturnType getReturnType();
	public StringBuilder toStringBuilder();
	public Node cascadeClone(ParentRef StepParent, GlobalVars newVars, boolean ApplyMutateChance);
}
