package EnemyLevelScaling.GeneticProgrammer.Mk7.Nodes.ReturnsVoid;

import EnemyLevelScaling.GeneticProgrammer.Mk7.Autobots.ConstantAutobot;
import EnemyLevelScaling.GeneticProgrammer.Mk7.Autobots.HelperAutobot;
import EnemyLevelScaling.GeneticProgrammer.Mk7.Autobots.RandyAutobot;
import EnemyLevelScaling.GeneticProgrammer.Mk7.GlobalVarsPool;
import EnemyLevelScaling.GeneticProgrammer.Mk7.ParentRef;
import EnemyLevelScaling.GeneticProgrammer.Mk7.Enums.NodeType;
import EnemyLevelScaling.GeneticProgrammer.Mk7.Nodes.AbstractClasses.BaseNode;
import EnemyLevelScaling.GeneticProgrammer.Mk7.Nodes.AbstractClasses.VoidNode;

public class IntSetterNode extends VoidNode
{
	private GlobalVarsPool CargoVars;
	
	public IntSetterNode(GlobalVarsPool inCargoVars)
	{
		this(null, inCargoVars, new BaseNode[NodeType.IntSetter.getNumberOfExpectedArgumentSets()]);
	}

	public IntSetterNode(ParentRef inParent, GlobalVarsPool inCargoVars, BaseNode[] inChildren)
	{
		super(inParent, NodeType.IntSetter, inChildren);
		
		this.CargoVars = inCargoVars;
	}

	public IntSetterNode(IntSetterNode old, ParentRef StepParent, GlobalVarsPool newVars, boolean ApplyMutateChance)
	{
		this(StepParent, newVars, null);

		//Can't in-line this because one can't use the 'this' keyword in a constructor
		this.setChildren(new BaseNode[] {
											old.getChildAt(0).cascadeClone(new ParentRef(this, 0), newVars, ApplyMutateChance),
											old.getChildAt(1).cascadeClone(new ParentRef(this, 1), newVars, ApplyMutateChance),
											old.getChildAt(2).cascadeClone(new ParentRef(this, 2), newVars, ApplyMutateChance),
											});
	}
	
	public IntSetterNode(DubSetterNode old, ParentRef StepParent, GlobalVarsPool newVars, boolean ApplyMutateChance)
	{
		this(StepParent, newVars, null);

		//Can't in-line this because one can't use the 'this' keyword in a constructor
		this.setChildren(new BaseNode[] {
											old.getChildAt(0).cascadeClone(new ParentRef(this, 0), newVars, ApplyMutateChance),
											old.getChildAt(1).cascadeClone(new ParentRef(this, 1), newVars, ApplyMutateChance),
											old.getChildAt(2).cascadeClone(new ParentRef(this, 2), newVars, ApplyMutateChance),});
	}
	
	public IntSetterNode(BaseNode Var1, BaseNode Var2, BaseNode Var3, GlobalVarsPool inVars)
	{
		this(null, inVars, new BaseNode[]{Var1, Var2, Var3});
	}

	@Override
	public void DooWop()
	{
		this.CargoVars.setIntArrayAt(this.evalIndex(), this.evalInput());
	}
	
	private int evalIndex()
	{
		return HelperAutobot.getIndexFromNode(super.getChildAt(0), this.CargoVars.getIntArrLength());
	}
	
	private int evalInput()
	{
		return HelperAutobot.getIntFromNode(super.getChildAt(1));
	}

	@Override
	public StringBuilder toStringBuilder()
	{
		StringBuilder in = new StringBuilder();
		
		in.append("IntArray[");
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
			result.append("IntSetter");
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
		in.append("SetIntAt(");
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
			return new DubSetterNode(this, StepParent, newVars, ApplyMutateChance);
		}
		else
		{
			return new IntSetterNode(this, StepParent, newVars, ApplyMutateChance);
		}
	}
}
