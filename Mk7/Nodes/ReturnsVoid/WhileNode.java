package EnemyLevelScaling.GeneticProgrammer.Mk7.Nodes.ReturnsVoid;

import EnemyLevelScaling.GeneticProgrammer.Mk7.GlobalVarsPool;
import EnemyLevelScaling.GeneticProgrammer.Mk7.ParentRef;
import EnemyLevelScaling.GeneticProgrammer.Mk7.Autobots.ConstantAutobot;
import EnemyLevelScaling.GeneticProgrammer.Mk7.Autobots.HelperAutobot;
import EnemyLevelScaling.GeneticProgrammer.Mk7.Enums.NodeType;
import EnemyLevelScaling.GeneticProgrammer.Mk7.Nodes.AbstractClasses.BaseNode;
import EnemyLevelScaling.GeneticProgrammer.Mk7.Nodes.AbstractClasses.VoidNode;

public class WhileNode extends VoidNode
{
	public WhileNode()
	{
		this(null, new BaseNode[NodeType.While.getNumberOfExpectedArgumentSets()]);
	}

	public WhileNode(ParentRef inParent)
	{
		this(inParent, new BaseNode[NodeType.While.getNumberOfExpectedArgumentSets()]);
	}

	public WhileNode(ParentRef inParent, BaseNode[] inChildren)
	{
		super(inParent, NodeType.While, inChildren);
	}

	public WhileNode(WhileNode old, ParentRef StepParent, GlobalVarsPool newVars, boolean ApplyMutateChance)
	{
		this(StepParent, null);

		//Can't in-line this because one can't use the 'this' keyword in a constructor
		this.setChildren(new BaseNode[] {
											old.getChildAt(0).cascadeClone(new ParentRef(this, 0), newVars, ApplyMutateChance),
											old.getChildAt(1).cascadeClone(new ParentRef(this, 1), newVars, ApplyMutateChance),
											old.getChildAt(2).cascadeClone(new ParentRef(this, 2), newVars, ApplyMutateChance), });
	}
	
	public WhileNode(BaseNode Var1, BaseNode Var2, BaseNode Var3)
	{
		this(null, new BaseNode[]{Var1, Var2, Var3});
	}

	@Override
	public void DooWop()
	{
		int Limiter = ConstantAutobot.getLoopLimiter();

		while ((Limiter > 0) && HelperAutobot.getBoolFromNode(super.getChildAt(0)))
		{
			((VoidNode) super.getChildAt(1)).DooWop();
			Limiter--;
		}
		
		((VoidNode) this.getChildAt(2)).DooWop();
	}

	@Override
	public StringBuilder toStringBuilder()
	{
		StringBuilder result = new StringBuilder();
		result.append("While(");
		result.append(this.getChildAt(0).toStringBuilder());
		result.append(")" + ConstantAutobot.getBlockOpener());
		result.append(this.getChildAt(1).toStringBuilder());
		result.append(ConstantAutobot.getBlockCloser());
		result.append(this.getChildAt(2).toStringBuilder());
		
		return result;
	}
	
	@Override
	public StringBuilder toStringBuilder(boolean WantsDiagnosticSyntax)
	{
		if(WantsDiagnosticSyntax)
		{
			StringBuilder result = new StringBuilder();
			
			result.append("While");
			result.append(this.getTotalOffspringAsString());
			result.append("(");
			result.append(this.getChildAt(0).toStringBuilder(WantsDiagnosticSyntax));
			result.append(ConstantAutobot.getArgDelimiter());
			result.append(this.getChildAt(1).toStringBuilder(WantsDiagnosticSyntax));
			result.append(ConstantAutobot.getArgDelimiter());
			result.append(this.getChildAt(2).toStringBuilder(WantsDiagnosticSyntax));
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
		in.append("While(");
		super.getChildAt(0).toStringBuilder(in);
		in.append(ConstantAutobot.getArgDelimiter());
		super.getChildAt(1).toStringBuilder(in);
		in.append(")" + ConstantAutobot.getSerialDelimiter() + this.getChildAt(2).toStringBuilder(in));
		
		return in;
	}

	@Override
	public BaseNode cascadeClone(ParentRef StepParent, GlobalVarsPool newVars, boolean ApplyMutateChance)
	{
		return new WhileNode(this, StepParent, newVars, ApplyMutateChance);
	}
}
