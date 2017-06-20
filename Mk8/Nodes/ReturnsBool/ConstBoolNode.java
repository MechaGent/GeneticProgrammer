package EnemyLevelScaling.GeneticProgrammer.Mk8.Nodes.ReturnsBool;

import EnemyLevelScaling.GeneticProgrammer.Mk8.GlobalVarsPool;
import EnemyLevelScaling.GeneticProgrammer.Mk8.ParentRef;
import EnemyLevelScaling.GeneticProgrammer.Mk8.Autobots.ConstantAutobot;
import EnemyLevelScaling.GeneticProgrammer.Mk8.Autobots.HelperAutobot;
import EnemyLevelScaling.GeneticProgrammer.Mk8.Autobots.RandyAutobot;
import EnemyLevelScaling.GeneticProgrammer.Mk8.Autobots.WeeblAutobot;
import EnemyLevelScaling.GeneticProgrammer.Mk8.Enums.NodeType;
import EnemyLevelScaling.GeneticProgrammer.Mk8.Enums.OutputMode;
import EnemyLevelScaling.GeneticProgrammer.Mk8.Nodes.AbstractClasses.BaseNode;
import EnemyLevelScaling.GeneticProgrammer.Mk8.Nodes.AbstractClasses.BoolNode;

public class ConstBoolNode extends BoolNode
{
	private boolean Value;

	public ConstBoolNode(ParentRef inParent, boolean inValue)
	{
		super(inParent, NodeType.ConstBool);
		this.Value = inValue;
	}
	
	@Override
	public void RecycleMe()
	{
		this.RecycleBaseNode();
		WeeblAutobot.Recycle(this);
	}
	
	public ConstBoolNode unRecycleMe(ParentRef inParent, boolean inValue)
	{
		this.unRecycleBaseNode(inParent, NodeType.ConstBool);
		this.Value = inValue;
		return this;
	}

	@Override
	public boolean getValue()
	{
		return this.Value;
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
				result.append(this.Value + "");
				result.append(")");
				break;
			}
		case DiagnosticWithTotals:
			{
				result.append(this.getNodeType().toString());
				result.append("[" + HelperAutobot.fromArrayToStringBuilder(getTotalOffspring(), ConstantAutobot.getArgDelimiter()) + "]");
				result.append("(");
				result.append(this.Value + "");
				result.append(")");
				break;
			}
		case Pseudocode:
			{
				if (this.Value)
				{
					result.append("TRUE");
				}
				else
				{
					result.append("FALSE");
				}
				break;
			}
		}

		return result;
	}

	@Override
	public BaseNode cascadeClone(ParentRef StepParent, GlobalVarsPool newVars)
	{
		return WeeblAutobot.requestNewConstBoolNode(StepParent, Value);
	}

	@Override
	public void applyMutationChance(boolean shouldCascade)
	{
		if(RandyAutobot.testForMutation())
		{
			this.Value = !this.Value;
		}
	}

}
