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

public class VarGetterNode extends NumNode
{
	private GlobalVarsPool GlobalVars;
	private int VarIndex;

	public VarGetterNode(ParentRef inParent, GlobalVarsPool inGlobalVars, int inIndex)
	{
		super(inParent, NodeType.VarGetter);
		this.GlobalVars = inGlobalVars;
		this.VarIndex = inIndex;
	}
	
	@Override
	public void RecycleMe()
	{
		this.RecycleBaseNode();
		this.VarIndex = -1;
		WeeblAutobot.Recycle(this);
	}
	
	public VarGetterNode unRecycleMe(ParentRef inParent, GlobalVarsPool inGlobalVars, int inOp)
	{
		this.unRecycleBaseNode(inParent, NodeType.VarGetter);
		this.GlobalVars = inGlobalVars;
		this.VarIndex = inOp;
		return this;
	}

	@Override
	public double getValue()
	{
		return ConstantAutobot.getVarValue(0, VarIndex);
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
				result.append("#VarIndex:" + this.VarIndex);
				result.append(")");
				break;
			}
		case DiagnosticWithTotals:
			{
				result.append(this.getNodeType().toString());
				result.append("[" + HelperAutobot.fromArrayToStringBuilder(getTotalOffspring(), ConstantAutobot.getArgDelimiter()) + "]");
				result.append("(");
				result.append("#VarIndex:" + this.VarIndex);
				result.append(")");
				break;
			}
		case Pseudocode:
			{
				result.append("\"" + ConstantAutobot.getVarName(this.getTrialNum(), VarIndex) + "\"");
				break;
			}
		}

		return result;
	}
	
	private int getTrialNum()
	{
		return this.GlobalVars.getTrialNum();
	}

	@Override
	public BaseNode cascadeClone(ParentRef StepParent, GlobalVarsPool newVars)
	{
		return WeeblAutobot.requestNewVarGetterNode(StepParent, newVars, this.VarIndex);
	}

	@Override
	public void applyMutationChance(boolean shouldCascade)
	{
		if (RandyAutobot.testForMutation())
		{
			this.VarIndex = RandyAutobot.getRandyIndex(ConstantAutobot.getNumVars(this.getTrialNum()));
		}
	}
}
