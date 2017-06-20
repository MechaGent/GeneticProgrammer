package EnemyLevelScaling.GeneticProgrammer.Mk8.Nodes.ReturnsNumber;

import EnemyLevelScaling.GeneticProgrammer.Mk8.GlobalVarsPool;
import EnemyLevelScaling.GeneticProgrammer.Mk8.ParentRef;
import EnemyLevelScaling.GeneticProgrammer.Mk8.Autobots.ConstantAutobot;
import EnemyLevelScaling.GeneticProgrammer.Mk8.Autobots.HelperAutobot;
import EnemyLevelScaling.GeneticProgrammer.Mk8.Autobots.RandyAutobot;
import EnemyLevelScaling.GeneticProgrammer.Mk8.Autobots.WeeblAutobot;
import EnemyLevelScaling.GeneticProgrammer.Mk8.Enums.NodeType;
import EnemyLevelScaling.GeneticProgrammer.Mk8.Enums.OutputMode;
import EnemyLevelScaling.GeneticProgrammer.Mk8.Nodes.AbstractClasses.BaseNode;
import EnemyLevelScaling.GeneticProgrammer.Mk8.Nodes.AbstractClasses.NumNode;

public class ConstNumNode extends NumNode
{
	private double Value;

	public ConstNumNode(ParentRef inParent, double inValue)
	{
		super(inParent, NodeType.ConstNum);
		Value = inValue;
	}

	@Override
	public void RecycleMe()
	{
		this.RecycleBaseNode();
		WeeblAutobot.Recycle(this);
	}
	
	public ConstNumNode unRecycleMe(ParentRef inParent, double inValue)
	{
		this.unRecycleBaseNode(inParent, NodeType.ConstNum);
		this.Value = inValue;
		return this;
	}
	
	@Override
	public double getValue()
	{
		return Value;
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
				result.append("(");
				result.append(this.Value + "");
				result.append(")");
				break;
			}
		}

		return result;
	}

	@Override
	public BaseNode cascadeClone(ParentRef StepParent, GlobalVarsPool newVars)
	{
		return WeeblAutobot.requestNewConstNumNode(StepParent, Value);
	}

	@Override
	public void applyMutationChance(boolean shouldCascade)
	{
		if(RandyAutobot.testForMutation())
		{
			this.Value = RandyAutobot.shakeNumberRandily(Value, ConstantAutobot.getMutationChance());
		}
	}
}
