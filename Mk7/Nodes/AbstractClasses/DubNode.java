package EnemyLevelScaling.GeneticProgrammer.Mk7.Nodes.AbstractClasses;

import EnemyLevelScaling.GeneticProgrammer.Mk7.ParentRef;
import EnemyLevelScaling.GeneticProgrammer.Mk7.Enums.NodeType;

public abstract class DubNode extends BaseNode
{
	public DubNode(ParentRef inParent, NodeType inType, BaseNode[] inChildren)
	{
		super(inParent, inType, inChildren);
	}
	
	public abstract double getValue();
}
