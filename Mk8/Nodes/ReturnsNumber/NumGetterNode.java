package EnemyLevelScaling.GeneticProgrammer.Mk8.Nodes.ReturnsNumber;

import EnemyLevelScaling.GeneticProgrammer.Mk8.GlobalVarsPool;
import EnemyLevelScaling.GeneticProgrammer.Mk8.ParentRef;
import EnemyLevelScaling.GeneticProgrammer.Mk8.Autobots.ConstantAutobot;
import EnemyLevelScaling.GeneticProgrammer.Mk8.Autobots.HelperAutobot;
import EnemyLevelScaling.GeneticProgrammer.Mk8.Autobots.WeeblAutobot;
import EnemyLevelScaling.GeneticProgrammer.Mk8.Enums.NodeType;
import EnemyLevelScaling.GeneticProgrammer.Mk8.Enums.OutputMode;
import EnemyLevelScaling.GeneticProgrammer.Mk8.Nodes.AbstractClasses.BaseNode;
import EnemyLevelScaling.GeneticProgrammer.Mk8.Nodes.AbstractClasses.NumNode;

public class NumGetterNode extends NumNode
{
	private GlobalVarsPool Vars;

	public NumGetterNode(ParentRef inParent, GlobalVarsPool inVars)
	{
		super(inParent, NodeType.NumGetter);
		this.Vars = inVars;
	}
	
	@Override
	public void RecycleMe()
	{
		this.RecycleBaseNode();
		this.Vars.RecycleMe();
		this.Vars = null;
		WeeblAutobot.Recycle(this);
	}
	
	public NumGetterNode unRecycleMe(ParentRef inParent, GlobalVarsPool inVars)
	{
		this.unRecycleBaseNode(inParent, NodeType.NumGetter);
		this.Vars = inVars;
		return this;
	}

	@Override
	public double getValue()
	{
		NumNode Index = (NumNode) this.getChildAt(0);

		return this.Vars.getFromNumArrayAt(HelperAutobot.cleanIndex(Index.getValue(), ConstantAutobot.getNumArrayLength()));
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
				result.append(this.getChildAt(0).toStringBuilder(in));
				result.append(")");
				break;
			}
		case DiagnosticWithTotals:
			{
				result.append(this.getNodeType().toString());
				result.append("[" + HelperAutobot.fromArrayToStringBuilder(getTotalOffspring(), ConstantAutobot.getArgDelimiter()) + "]");
				result.append("(");
				result.append(this.getChildAt(0).toStringBuilder(in));
				result.append(")");
				break;
			}
		case Pseudocode:
			{
				result.append("NumArray[");
				result.append(this.getChildAt(0).toStringBuilder(in));
				result.append("]");
				break;
			}
		}

		return result;
	}

	@Override
	public BaseNode cascadeClone(ParentRef StepParent, GlobalVarsPool newVars)
	{
		BaseNode result = WeeblAutobot.requestNewNumGetterNode(StepParent, newVars);
		result.setChildAt(0, this.getChildAt(0).cascadeClone(WeeblAutobot.requestNewParentRef(this, 0), newVars));
		return result;
	}

	@Override
	public void applyMutationChance(boolean shouldCascade)
	{
		/*
		 * Can't think of any way to mutate this
		 */

		if (shouldCascade)
		{
			this.getChildAt(0).applyMutationChance(true);
		}
	}
}
