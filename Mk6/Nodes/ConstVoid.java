package EnemyLevelScaling.GeneticProgrammer.Mk6.Nodes;

import EnemyLevelScaling.GeneticProgrammer.Mk6.BaseNode;
import EnemyLevelScaling.GeneticProgrammer.Mk6.GlobalVars;
import EnemyLevelScaling.GeneticProgrammer.Mk6.ParentRef;
import EnemyLevelScaling.GeneticProgrammer.Mk6.Enums.NodeType;
import EnemyLevelScaling.GeneticProgrammer.Mk6.Enums.ReturnType;
import EnemyLevelScaling.GeneticProgrammer.Mk6.Nodes.Interfaces.Node;
import EnemyLevelScaling.GeneticProgrammer.Mk6.Nodes.Interfaces.VoidNode;

public class ConstVoid extends BaseNode implements VoidNode
{
	public ConstVoid()
	{
		super(NodeType.ConstVoid);
	}
	
	public ConstVoid(ParentRef StepParent)
	{
		super(StepParent, NodeType.ConstVoid);
	}

	@Override
	public ReturnType getReturnType()
	{
		return super.getReturnType();
	}

	@Override
	public ReturnType[] getExpectedArgumentsFromChildAt(int index)
	{
		return super.getExpectedArgumentsFromChildAt(index);
	}

	@Override
	public int getNumChildren()
	{
		return super.getNumChildren();
	}

	@Override
	public StringBuilder toStringBuilder(StringBuilder in)
	{
		return in.append("VoidConst()");
	}
	
	@Override
	public Node cascadeClone(ParentRef StepParent, GlobalVars newVars, boolean ApplyMutateChance)
	{
		return new ConstVoid(StepParent);
	}
	
	@Override
	public void DooWop()
	{
		
	}
}
