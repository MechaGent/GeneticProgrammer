package EnemyLevelScaling.GeneticProgrammer.Mk8.Nodes.ReturnsVoid;

import EnemyLevelScaling.GeneticProgrammer.Mk8.Autobots.ConstantAutobot;
import EnemyLevelScaling.GeneticProgrammer.Mk8.Autobots.HelperAutobot;
import EnemyLevelScaling.GeneticProgrammer.Mk8.Autobots.WeeblAutobot;
import EnemyLevelScaling.GeneticProgrammer.Mk8.GlobalVarsPool;
import EnemyLevelScaling.GeneticProgrammer.Mk8.ParentRef;
import EnemyLevelScaling.GeneticProgrammer.Mk8.Enums.NodeType;
import EnemyLevelScaling.GeneticProgrammer.Mk8.Enums.OutputMode;
import EnemyLevelScaling.GeneticProgrammer.Mk8.Nodes.AbstractClasses.BaseNode;
import EnemyLevelScaling.GeneticProgrammer.Mk8.Nodes.AbstractClasses.VoidNode;

public class NumSetterNode extends VoidNode
{
	private GlobalVarsPool Vars;
	
	public NumSetterNode(ParentRef inParent, GlobalVarsPool inVars)
	{
		super(inParent, NodeType.NumSetter);
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
	
	public NumSetterNode unRecycleMe(ParentRef inParent, GlobalVarsPool inVars)
	{
		this.unRecycleBaseNode(inParent, NodeType.NumSetter);
		this.Vars = inVars;
		return this;
	}

	@Override
	public void DooWop()
	{
		int index = HelperAutobot.getIndexFromNode(this.getChildAt(0));
		double value = HelperAutobot.getDubFromNode(this.getChildAt(1));
		
		this.Vars.setNumArrayAt(index, value);
		
		((VoidNode) this.getChildAt(2)).DooWop();
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
				result.append(ConstantAutobot.getArgDelimiter());
				result.append(this.getChildAt(1).toStringBuilder(in));
				result.append(ConstantAutobot.getArgDelimiter());
				result.append(this.getChildAt(2).toStringBuilder(in));
				result.append(")");
				break;
			}
		case DiagnosticWithTotals:
			{
				result.append(this.getNodeType().toString());
				result.append("[" + HelperAutobot.fromArrayToStringBuilder(getTotalOffspring(), ConstantAutobot.getArgDelimiter()) + "]");
				result.append("(");
				result.append(this.getChildAt(0).toStringBuilder(in));
				result.append(ConstantAutobot.getArgDelimiter());
				result.append(this.getChildAt(1).toStringBuilder(in));
				result.append(ConstantAutobot.getArgDelimiter());
				result.append(this.getChildAt(2).toStringBuilder(in));
				result.append(")");
				break;
			}
		case Pseudocode:
			{
				result.append("NumArray[");
				result.append(this.getChildAt(0).toStringBuilder(in));
				result.append("] = ");
				result.append(this.getChildAt(1).toStringBuilder(in));
				result.append(";");
				result.append(this.getChildAt(2).toStringBuilder(in));
				break;
			}
		}

		return result;
	}

	@Override
	public BaseNode cascadeClone(ParentRef StepParent, GlobalVarsPool newVars)
	{
		BaseNode result = WeeblAutobot.requestNewNumSetterNode(StepParent, newVars);
		result.setChildAt(0, this.getChildAt(0).cascadeClone(WeeblAutobot.requestNewParentRef(this, 0), newVars));
		result.setChildAt(1, this.getChildAt(1).cascadeClone(WeeblAutobot.requestNewParentRef(this, 1), newVars));
		result.setChildAt(2, this.getChildAt(2).cascadeClone(WeeblAutobot.requestNewParentRef(this, 2), newVars));
		return result;
	}

	@Override
	public void applyMutationChance(boolean shouldCascade)
	{
		if(shouldCascade)
		{
			this.getChildAt(0).applyMutationChance(shouldCascade);
			this.getChildAt(1).applyMutationChance(shouldCascade);
			this.getChildAt(2).applyMutationChance(shouldCascade);
		}
	}

}
