package EnemyLevelScaling.GeneticProgrammer.Mk8.Nodes.ReturnsBool;

import EnemyLevelScaling.GeneticProgrammer.Mk8.Autobots.ConstantAutobot;
import EnemyLevelScaling.GeneticProgrammer.Mk8.Autobots.HelperAutobot;
import EnemyLevelScaling.GeneticProgrammer.Mk8.Autobots.RandyAutobot;
import EnemyLevelScaling.GeneticProgrammer.Mk8.Autobots.WeeblAutobot;
import EnemyLevelScaling.GeneticProgrammer.Mk8.GlobalVarsPool;
import EnemyLevelScaling.GeneticProgrammer.Mk8.ParentRef;
import EnemyLevelScaling.GeneticProgrammer.Mk8.Enums.NodeType;
import EnemyLevelScaling.GeneticProgrammer.Mk8.Enums.OutputMode;
import EnemyLevelScaling.GeneticProgrammer.Mk8.Enums.OpTypes.UnaryBoolOp;
import EnemyLevelScaling.GeneticProgrammer.Mk8.Nodes.AbstractClasses.BaseNode;
import EnemyLevelScaling.GeneticProgrammer.Mk8.Nodes.AbstractClasses.BoolNode;

public class UnaryBoolBoolNode extends BoolNode
{
	private UnaryBoolOp Op;

	public UnaryBoolBoolNode(ParentRef inParent, UnaryBoolOp inOp)
	{
		super(inParent, NodeType.UnaryBoolBool);
		this.Op = inOp;
	}
	
	@Override
	public void RecycleMe()
	{
		this.RecycleBaseNode();
		this.Op = null;
		WeeblAutobot.Recycle(this);
	}
	
	public UnaryBoolBoolNode unRecycleMe(ParentRef inParent, UnaryBoolOp inOp)
	{
		this.unRecycleBaseNode(inParent, NodeType.UnaryBoolBool);
		this.Op = inOp;
		return this;
	}

	@Override
	public boolean getValue()
	{
		boolean result;
		boolean a = HelperAutobot.getBoolFromNode(super.getChildAt(0));

		switch (this.Op)
		{
		case NOT:
			{
				result = !a;
				break;
			}
		default:
			{
				result = false;
				break;
			}
		}

		return result;
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
				result.append("('");
				result.append(this.Op.toString());
				result.append("'");
				result.append(ConstantAutobot.getArgDelimiter());
				result.append(this.getChildAt(0).toStringBuilder(in));
				result.append(")");
				break;
			}
		case DiagnosticWithTotals:
			{
				result.append(this.getNodeType().toString());
				result.append("[" + HelperAutobot.fromArrayToStringBuilder(getTotalOffspring(), ConstantAutobot.getArgDelimiter()) + "]");
				result.append("('");
				result.append(this.Op.toString());
				result.append("'");
				result.append(ConstantAutobot.getArgDelimiter());
				result.append(this.getChildAt(0).toStringBuilder(in));
				result.append(")");
				break;
			}
		case Pseudocode:
			{
				result.append(this.Op.toString());
				result.append("(");
				result.append(this.getChildAt(0).toStringBuilder(in));
				result.append(")");
				break;
			}
		}

		return result;
	}

	@Override
	public BaseNode cascadeClone(ParentRef StepParent, GlobalVarsPool newVars)
	{
		BaseNode result = WeeblAutobot.requestNewUnaryBoolNode(StepParent, Op);
		result.setChildAt(0, this.getChildAt(0).cascadeClone(new ParentRef(this, 0), newVars));
		return result;
	}

	@Override
	public void applyMutationChance(boolean shouldCascade)
	{
		if(RandyAutobot.testForMutation())
		{
			this.Op = UnaryBoolOp.getEnumAtIndex(RandyAutobot.getRandyIndex(UnaryBoolOp.getNumOps()));
		}
		
		if (shouldCascade)
		{
			this.getChildAt(0).applyMutationChance(true);
		}
	}

}
