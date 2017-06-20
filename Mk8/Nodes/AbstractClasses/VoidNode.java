package EnemyLevelScaling.GeneticProgrammer.Mk8.Nodes.AbstractClasses;

import EnemyLevelScaling.GeneticProgrammer.Mk8.ParentRef;
import EnemyLevelScaling.GeneticProgrammer.Mk8.Enums.NodeType;

public abstract class VoidNode extends BaseNode
{
	public VoidNode(ParentRef inParent, NodeType inType)
	{
		super(inParent, inType);
	}
	
	public abstract void DooWop();
}
