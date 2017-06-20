package EnemyLevelScaling.GeneticProgrammer.Mk7.Nodes.ReturnsBool;

import EnemyLevelScaling.GeneticProgrammer.Mk7.Autobots.HelperAutobot;
import EnemyLevelScaling.GeneticProgrammer.Mk7.Autobots.ConstantAutobot;
import EnemyLevelScaling.GeneticProgrammer.Mk7.Autobots.RandyAutobot;
import EnemyLevelScaling.GeneticProgrammer.Mk7.GlobalVarsPool;
import EnemyLevelScaling.GeneticProgrammer.Mk7.ParentRef;
import EnemyLevelScaling.GeneticProgrammer.Mk7.Enums.NodeType;
import EnemyLevelScaling.GeneticProgrammer.Mk7.Enums.OpTypes.UnaryBoolOp;
import EnemyLevelScaling.GeneticProgrammer.Mk7.Nodes.AbstractClasses.BaseNode;
import EnemyLevelScaling.GeneticProgrammer.Mk7.Nodes.AbstractClasses.BoolNode;

public class UnaryBoolNode extends BoolNode
{
	private UnaryBoolOp Op;

	public UnaryBoolNode()
	{
		this(null, RandyAutobot.getRandyUnaryBoolOp());
	}

	public UnaryBoolNode(ParentRef inParent, UnaryBoolOp inOp)
	{
		super(inParent, NodeType.UnaryBool, new BaseNode[NodeType.UnaryBool.getNumberOfExpectedArgumentSets()]);
		this.Op = inOp;
	}

	public UnaryBoolNode(UnaryBoolNode old, ParentRef StepParent, UnaryBoolOp inOp, GlobalVarsPool inVars, boolean ApplyMutateChance)
	{
		this(StepParent, inOp);
		this.setChildren(new BaseNode[] {
											old.getChildAt(0).cascadeClone(new ParentRef(this, 0), inVars, ApplyMutateChance) });
	}
	
	public UnaryBoolNode(UnaryBoolOp inOp, BaseNode Var1)
	{
		super(null, NodeType.UnaryBool, new BaseNode[]{Var1});
		this.Op = inOp;
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
	public StringBuilder toStringBuilder()
	{
		return this.Op.toStringBuilder(this.getChildAt(0));
	}

	@Override
	public StringBuilder toStringBuilder(boolean WantsDiagnosticSyntax)
	{
		if(WantsDiagnosticSyntax)
		{
			StringBuilder result = new StringBuilder();
			
			result.append("UnaryBool");
			result.append(this.getTotalOffspringAsString());
			result.append("(");
			result.append(this.Op.toString());
			result.append(ConstantAutobot.getArgDelimiter());
			result.append(this.getChildAt(0).toStringBuilder(WantsDiagnosticSyntax));
			result.append(")");
			
			return result;
		}
		else
		{
			return this.toStringBuilder();
		}
	}
	
	@Override
	public StringBuilder toStringBuilder(StringBuilder in)
	{
		in.append("UnaryBool(");
		in.append(this.Op.toString());
		in.append(ConstantAutobot.getArgDelimiter());
		super.getChildAt(0).toStringBuilder(in);
		in.append(")");

		return in;
	}

	@Override
	public BaseNode cascadeClone(ParentRef StepParent, GlobalVarsPool newVars, boolean ApplyMutateChance)
	{
		if (ApplyMutateChance && RandyAutobot.flipCoin(ConstantAutobot.getMutationChance()))
		{
			return new UnaryBoolNode(this, StepParent, RandyAutobot.getRandyUnaryBoolOp(), newVars, ApplyMutateChance);
		}
		else
		{
			return new UnaryBoolNode(this, StepParent, this.Op, newVars, ApplyMutateChance);
		}
	}
}
