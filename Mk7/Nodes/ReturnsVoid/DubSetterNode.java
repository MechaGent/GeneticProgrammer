package EnemyLevelScaling.GeneticProgrammer.Mk7.Nodes.ReturnsVoid;

import EnemyLevelScaling.GeneticProgrammer.Mk7.GlobalVarsPool;
import EnemyLevelScaling.GeneticProgrammer.Mk7.ParentRef;
import EnemyLevelScaling.GeneticProgrammer.Mk7.Autobots.ConstantAutobot;
import EnemyLevelScaling.GeneticProgrammer.Mk7.Autobots.HelperAutobot;
import EnemyLevelScaling.GeneticProgrammer.Mk7.Autobots.RandyAutobot;
import EnemyLevelScaling.GeneticProgrammer.Mk7.Enums.NodeType;
import EnemyLevelScaling.GeneticProgrammer.Mk7.Nodes.AbstractClasses.BaseNode;
import EnemyLevelScaling.GeneticProgrammer.Mk7.Nodes.AbstractClasses.VoidNode;

public class DubSetterNode extends VoidNode
{
private GlobalVarsPool CargoVars;
	
	public DubSetterNode(GlobalVarsPool inCargoVars)
	{
		this(null, inCargoVars, new BaseNode[NodeType.DubSetter.getNumberOfExpectedArgumentSets()]);
	}

	public DubSetterNode(ParentRef inParent, GlobalVarsPool inCargoVars, BaseNode[] inChildren)
	{
		super(inParent, NodeType.DubSetter, inChildren);
		
		this.CargoVars = inCargoVars;
	}

	public DubSetterNode(DubSetterNode old, ParentRef StepParent, GlobalVarsPool newVars, boolean ApplyMutateChance)
	{
		this(StepParent, newVars, null);

		//Can't in-line this because one can't use the 'this' keyword in a constructor
		this.setChildren(new BaseNode[] {
											old.getChildAt(0).cascadeClone(new ParentRef(this, 0), newVars, ApplyMutateChance),
											old.getChildAt(1).cascadeClone(new ParentRef(this, 1), newVars, ApplyMutateChance),
											old.getChildAt(2).cascadeClone(new ParentRef(this, 2), newVars, ApplyMutateChance),});
	}
	
	public DubSetterNode(IntSetterNode old, ParentRef StepParent, GlobalVarsPool newVars, boolean ApplyMutateChance)
	{
		this(StepParent, newVars, null);

		//Can't in-line this because one can't use the 'this' keyword in a constructor
		this.setChildren(new BaseNode[] {
											old.getChildAt(0).cascadeClone(new ParentRef(this, 0), newVars, ApplyMutateChance),
											old.getChildAt(1).cascadeClone(new ParentRef(this, 1), newVars, ApplyMutateChance),
											old.getChildAt(2).cascadeClone(new ParentRef(this, 2), newVars, ApplyMutateChance),});
	}
	
	public DubSetterNode(BaseNode Var1, BaseNode Var2, BaseNode Var3, GlobalVarsPool inVars)
	{
		super(null, NodeType.DubSetter, new BaseNode[]{Var1, Var2, Var3});
		
		this.CargoVars = inVars;
	}

	@Override
	public void DooWop()
	{
		int index = HelperAutobot.getIntFromNode(super.getChildAt(0));
		double input = HelperAutobot.getDubFromNode(super.getChildAt(1));
		
		this.CargoVars.setDubArrayAt(index, input);
	}

	@Override
	public StringBuilder toStringBuilder()
	{
		StringBuilder in = new StringBuilder();
		
		in.append("DubArray[");
		in.append(this.getChildAt(0).toStringBuilder());
		in.append("] = ");
		in.append(this.getChildAt(1).toStringBuilder());
		in.append(ConstantAutobot.getLineDelimiter());
		
		return in;
	}
	
	@Override
	public StringBuilder toStringBuilder(boolean WantsDiagnosticSyntax)
	{
		if(WantsDiagnosticSyntax)
		{
			StringBuilder result = new StringBuilder();
			
			result.append("DubSetter");
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
		in.append("SetDubAt(");
		super.getChildAt(0).toStringBuilder(in);
		in.append(ConstantAutobot.getArgDelimiter());
		super.getChildAt(1).toStringBuilder(in);
		in.append(")" + ConstantAutobot.getSerialDelimiter() + this.getChildAt(2).toStringBuilder(in));

		return in;
	}

	@Override
	public BaseNode cascadeClone(ParentRef StepParent, GlobalVarsPool newVars, boolean ApplyMutateChance)
	{
		if(ApplyMutateChance && RandyAutobot.flipCoin(ConstantAutobot.getMutationChance()))
		{
			return new IntSetterNode(this, StepParent, newVars, ApplyMutateChance);
		}
		else
		{
			return new DubSetterNode(this, StepParent, newVars, ApplyMutateChance);
		}
	}
}
