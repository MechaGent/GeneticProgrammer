package EnemyLevelScaling.GeneticProgrammer.Mk9.Abstracts;

import EnemyLevelScaling.GeneticProgrammer.Mk9.ParentRef;
import EnemyLevelScaling.GeneticProgrammer.Mk9.Enums.NodeType;

public abstract class VoidNode extends BaseNode
{
	public VoidNode(ParentRef inParent, NodeType inType)
	{
		super(inParent, inType);
	}
	
	public abstract void DooWop();
}
