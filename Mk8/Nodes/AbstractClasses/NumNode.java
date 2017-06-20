package EnemyLevelScaling.GeneticProgrammer.Mk8.Nodes.AbstractClasses;

import EnemyLevelScaling.GeneticProgrammer.Mk8.ParentRef;
import EnemyLevelScaling.GeneticProgrammer.Mk8.Enums.NodeType;

public abstract class NumNode extends BaseNode
{
	public NumNode(ParentRef inParent, NodeType inType)
	{
		super(inParent, inType);
	}
	
	public abstract double getValue();
}
