package EnemyLevelScaling.GeneticProgrammer.Mk7.Nodes.AbstractClasses;

import EnemyLevelScaling.GeneticProgrammer.Mk7.ParentRef;
import EnemyLevelScaling.GeneticProgrammer.Mk7.Enums.NodeType;

public abstract class BoolNode extends BaseNode
{
	public BoolNode(ParentRef inParent, NodeType inType, BaseNode[] inChildren)
	{
		super(inParent, inType, inChildren);
	}

	public abstract boolean getValue();
}
