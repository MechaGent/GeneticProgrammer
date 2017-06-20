package EnemyLevelScaling.GeneticProgrammer.Mk8.Nodes.ReturnsVoid;

import EnemyLevelScaling.GeneticProgrammer.Mk8.GlobalVarsPool;
import EnemyLevelScaling.GeneticProgrammer.Mk8.ParentRef;
import EnemyLevelScaling.GeneticProgrammer.Mk8.Autobots.ConstantAutobot;
import EnemyLevelScaling.GeneticProgrammer.Mk8.Autobots.HelperAutobot;
import EnemyLevelScaling.GeneticProgrammer.Mk8.Autobots.WeeblAutobot;
import EnemyLevelScaling.GeneticProgrammer.Mk8.Enums.NodeType;
import EnemyLevelScaling.GeneticProgrammer.Mk8.Enums.OutputMode;
import EnemyLevelScaling.GeneticProgrammer.Mk8.Nodes.AbstractClasses.BaseNode;
import EnemyLevelScaling.GeneticProgrammer.Mk8.Nodes.AbstractClasses.VoidNode;

public class ConstVoidNode extends VoidNode
{
	public ConstVoidNode(ParentRef inParent)
	{
		super(inParent, NodeType.ConstVoid);
	}

	@Override
	public void RecycleMe()
	{
		this.RecycleBaseNode();
		WeeblAutobot.Recycle(this);
	}
	
	public ConstVoidNode unRecycleMe(ParentRef inParent)
	{
		this.unRecycleBaseNode(inParent, NodeType.ConstVoid);
		return this;
	}
	
	@Override
	public void DooWop()
	{
		
	}

	@Override
	public StringBuilder toStringBuilder(OutputMode in)
	{
		StringBuilder result = new StringBuilder();

		switch (in)
		{
		case Diagnostic:
			{
				result.append(this.getNodeType().toString());
				result.append("(");
				result.append(")");
				break;
			}
		case DiagnosticWithTotals:
			{
				result.append(this.getNodeType().toString());
				result.append("[" + HelperAutobot.fromArrayToStringBuilder(getTotalOffspring(), ConstantAutobot.getArgDelimiter()) + "]");
				result.append("(");
				result.append(")");
				break;
			}
		case Pseudocode:
			{
				result.append(this.getNodeType().toString());
				result.append("();");
				break;
			}
		}

		return result;
	}

	@Override
	public BaseNode cascadeClone(ParentRef StepParent, GlobalVarsPool newVars)
	{
		return WeeblAutobot.requestNewConstVoidNode(StepParent);
	}

	@Override
	public void applyMutationChance(boolean shouldCascade)
	{
		
	}

}
