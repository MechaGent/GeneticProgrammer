package EnemyLevelScaling.GeneticProgrammer.Mk7.Nodes.ReturnsVoid;

import EnemyLevelScaling.GeneticProgrammer.Mk7.GlobalVarsPool;
import EnemyLevelScaling.GeneticProgrammer.Mk7.ParentRef;
import EnemyLevelScaling.GeneticProgrammer.Mk7.Autobots.ConstantAutobot;
import EnemyLevelScaling.GeneticProgrammer.Mk7.Autobots.HelperAutobot;
import EnemyLevelScaling.GeneticProgrammer.Mk7.Enums.NodeType;
import EnemyLevelScaling.GeneticProgrammer.Mk7.Nodes.AbstractClasses.BaseNode;
import EnemyLevelScaling.GeneticProgrammer.Mk7.Nodes.AbstractClasses.VoidNode;

public class IfElseNode extends VoidNode
{
	public IfElseNode()
	{
		this(null, new BaseNode[NodeType.IfElse.getNumberOfExpectedArgumentSets()]);
	}

	public IfElseNode(ParentRef inParent)
	{
		this(inParent, new BaseNode[NodeType.IfElse.getNumberOfExpectedArgumentSets()]);
	}

	public IfElseNode(ParentRef inParent, BaseNode[] inChildren)
	{
		super(inParent, NodeType.IfElse, inChildren);
	}

	public IfElseNode(IfElseNode old, ParentRef StepParent, GlobalVarsPool newVars, boolean ApplyMutateChance)
	{
		this(StepParent, null);
		
		//Can't in-line this because one can't use the 'this' keyword in a constructor
		this.setChildren(new BaseNode[] {
											old.getChildAt(0).cascadeClone(new ParentRef(this, 0), newVars, ApplyMutateChance),
											old.getChildAt(1).cascadeClone(new ParentRef(this, 1), newVars, ApplyMutateChance),
											old.getChildAt(2).cascadeClone(new ParentRef(this, 2), newVars, ApplyMutateChance),
											old.getChildAt(3).cascadeClone(new ParentRef(this, 3), newVars, ApplyMutateChance), });
	}
	
	public IfElseNode(BaseNode Var1, BaseNode Var2, BaseNode Var3, BaseNode Var4)
	{
		this(null, new BaseNode[]{Var1, Var2, Var3, Var4});
	}

	@Override
	public StringBuilder toStringBuilder()
	{
		StringBuilder in = new StringBuilder();
		
		in.append("If(");
		in.append(this.getChildAt(0).toStringBuilder());
		in.append(")" + ConstantAutobot.getBlockOpener());
		in.append(this.getChildAt(1).toStringBuilder());
		in.append(ConstantAutobot.getBlockCloser() + "else" + ConstantAutobot.getBlockOpener());
		in.append(this.getChildAt(2).toStringBuilder());
		in.append(ConstantAutobot.getBlockCloser());
		in.append(this.getChildAt(3).toStringBuilder());
		
		return in;
	}
	
	@Override
	public StringBuilder toStringBuilder(boolean WantsDiagnosticSyntax)
	{
		if(WantsDiagnosticSyntax)
		{
			StringBuilder result = new StringBuilder();
			
			result.append("IfElse");
			result.append(this.getTotalOffspringAsString());
			result.append("(");
			result.append(this.getChildAt(0).toStringBuilder(WantsDiagnosticSyntax));
			result.append(ConstantAutobot.getArgDelimiter());
			result.append(this.getChildAt(1).toStringBuilder(WantsDiagnosticSyntax));
			result.append(ConstantAutobot.getArgDelimiter());
			result.append(this.getChildAt(2).toStringBuilder(WantsDiagnosticSyntax));
			result.append(ConstantAutobot.getArgDelimiter());
			result.append(this.getChildAt(3).toStringBuilder(WantsDiagnosticSyntax));
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
		in.append("IfElse(");
		this.getChildAt(0).toStringBuilder(in);
		in.append(ConstantAutobot.getArgDelimiter());
		this.getChildAt(1).toStringBuilder(in);
		in.append(ConstantAutobot.getArgDelimiter());
		this.getChildAt(2).toStringBuilder(in);
		in.append(")" + ConstantAutobot.getSerialDelimiter() + this.getChildAt(3).toStringBuilder(in));
		
		return in;
	}

	/**
	 * Can't currently think of a way to mutate this
	 */
	@Override
	public BaseNode cascadeClone(ParentRef StepParent, GlobalVarsPool newVars, boolean ApplyMutateChance)
	{
		return new IfElseNode(this, StepParent, newVars, ApplyMutateChance);
	}

	@Override
	public void DooWop()
	{
		boolean Child0 = HelperAutobot.getBoolFromNode(this.getChildAt(0));

		if (Child0)
		{
			((VoidNode) this.getChildAt(1)).DooWop();
		}
		else
		{
			((VoidNode) this.getChildAt(2)).DooWop();
		}
		
		((VoidNode) this.getChildAt(3)).DooWop();
	}

}
