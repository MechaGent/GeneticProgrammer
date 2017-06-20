package EnemyLevelScaling.GeneticProgrammer.Mk7.Nodes.ReturnsInteger;

import EnemyLevelScaling.GeneticProgrammer.Mk7.Autobots.ConstantAutobot;
import EnemyLevelScaling.GeneticProgrammer.Mk7.Autobots.HelperAutobot;
import EnemyLevelScaling.GeneticProgrammer.Mk7.Autobots.RandyAutobot;
import EnemyLevelScaling.GeneticProgrammer.Mk7.GlobalVarsPool;
import EnemyLevelScaling.GeneticProgrammer.Mk7.ParentRef;
import EnemyLevelScaling.GeneticProgrammer.Mk7.Enums.NodeType;
import EnemyLevelScaling.GeneticProgrammer.Mk7.Nodes.AbstractClasses.BaseNode;
import EnemyLevelScaling.GeneticProgrammer.Mk7.Nodes.AbstractClasses.IntNode;
import EnemyLevelScaling.GeneticProgrammer.Mk7.Nodes.ReturnsDouble.DubGetterNode;

public class IntGetterNode extends IntNode
{
	private GlobalVarsPool CargoVars;

	public IntGetterNode(GlobalVarsPool inCargoVars)
	{
		this(null, inCargoVars, new BaseNode[NodeType.IntGetter.getNumberOfExpectedArgumentSets()]);
	}

	public IntGetterNode(ParentRef inParent, GlobalVarsPool inCargoVars, BaseNode[] inChildren)
	{
		super(inParent, NodeType.IntGetter, inChildren);

		this.CargoVars = inCargoVars;
	}

	public IntGetterNode(IntGetterNode old, ParentRef StepParent, GlobalVarsPool newVars, boolean ApplyMutateChance)
	{
		this(StepParent, newVars, null);

		//Can't in-line this because one can't use the 'this' keyword in a constructor
		this.setChildren(new BaseNode[] {
											old.getChildAt(0).cascadeClone(new ParentRef(this, 0), newVars, ApplyMutateChance), });
	}

	public IntGetterNode(DubGetterNode old, ParentRef StepParent, GlobalVarsPool newVars, boolean ApplyMutateChance)
	{
		this(StepParent, newVars, null);

		//Can't in-line this because one can't use the 'this' keyword in a constructor
		this.setChildren(new BaseNode[] {
											old.getChildAt(0).cascadeClone(new ParentRef(this, 0), newVars, ApplyMutateChance), });
	}
	
	public IntGetterNode(BaseNode Var1, GlobalVarsPool inVars)
	{
		this(null, inVars, new BaseNode[]{Var1});
	}

	@Override
	public int getValue()
	{
		int RawIndex = HelperAutobot.getIntFromNode(super.getChildAt(0));
		int ArrayLength = this.CargoVars.getIntArrLength();
		int index = (RawIndex % ArrayLength + ArrayLength) % ArrayLength;

		return this.CargoVars.getIntAt(index);
	}

	@Override
	public StringBuilder toStringBuilder()
	{
		StringBuilder result = new StringBuilder();

		result.append("IntArray[");
		result.append(this.getChildAt(0).toStringBuilder());
		result.append("]");

		return result;
	}

	@Override
	public StringBuilder toStringBuilder(boolean WantsDiagnosticSyntax)
	{
		if(WantsDiagnosticSyntax)
		{
			StringBuilder result = new StringBuilder();
			
			result.append("IntGetter");
			result.append(this.getTotalOffspringAsString());
			result.append("(");
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
		in.append("GetIntAt(");
		super.getChildAt(0).toStringBuilder(in);
		in.append(")");

		return in;
	}

	@Override
	public BaseNode cascadeClone(ParentRef StepParent, GlobalVarsPool newVars, boolean ApplyMutateChance)
	{
		if (ApplyMutateChance && RandyAutobot.flipCoin(ConstantAutobot.getMutationChance()))
		{
			return new DubGetterNode(this, StepParent, newVars, ApplyMutateChance);
		}
		else
		{
			return new IntGetterNode(this, StepParent, newVars, ApplyMutateChance);
		}
	}
}
