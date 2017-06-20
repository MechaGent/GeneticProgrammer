package EnemyLevelScaling.GeneticProgrammer.Mk8.Nodes.AbstractClasses;

import EnemyLevelScaling.GeneticProgrammer.Mk8.ParentRef;
import EnemyLevelScaling.GeneticProgrammer.Mk8.Enums.NodeType;

public abstract class BoolNode extends BaseNode
{
	public BoolNode(ParentRef inParent, NodeType inType)
	{
		super(inParent, inType);
	}

	public abstract boolean getValue();
}
