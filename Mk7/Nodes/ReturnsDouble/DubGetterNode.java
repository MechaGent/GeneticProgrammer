package EnemyLevelScaling.GeneticProgrammer.Mk7.Nodes.ReturnsDouble;

import EnemyLevelScaling.GeneticProgrammer.Mk7.Autobots.HelperAutobot;
import EnemyLevelScaling.GeneticProgrammer.Mk7.Autobots.ConstantAutobot;
import EnemyLevelScaling.GeneticProgrammer.Mk7.Autobots.RandyAutobot;
import EnemyLevelScaling.GeneticProgrammer.Mk7.GlobalVarsPool;
import EnemyLevelScaling.GeneticProgrammer.Mk7.ParentRef;
import EnemyLevelScaling.GeneticProgrammer.Mk7.Enums.NodeType;
import EnemyLevelScaling.GeneticProgrammer.Mk7.Nodes.AbstractClasses.BaseNode;
import EnemyLevelScaling.GeneticProgrammer.Mk7.Nodes.AbstractClasses.DubNode;
import EnemyLevelScaling.GeneticProgrammer.Mk7.Nodes.ReturnsInteger.IntGetterNode;

public class DubGetterNode extends DubNode
{
	private GlobalVarsPool CargoVars;
	
	public DubGetterNode(GlobalVarsPool inCargoVars)
	{
		this(null, inCargoVars, new BaseNode[NodeType.DubGetter.getNumberOfExpectedArgumentSets()]);
	}

	public DubGetterNode(ParentRef inParent, GlobalVarsPool inCargoVars, BaseNode[] inChildren)
	{
		super(inParent, NodeType.DubGetter, inChildren);
		
		this.CargoVars = inCargoVars;
	}

	public DubGetterNode(DubGetterNode old, ParentRef StepParent, GlobalVarsPool newVars, boolean ApplyMutateChance)
	{
		this(StepParent, newVars, null);

		//Can't in-line this because one can't use the 'this' keyword in a constructor
		this.setChildren(new BaseNode[] {
											old.getChildAt(0).cascadeClone(new ParentRef(this, 0), newVars, ApplyMutateChance), });
	}
	
	public DubGetterNode(IntGetterNode old, ParentRef StepParent, GlobalVarsPool newVars, boolean ApplyMutateChance)
	{
		this(StepParent, newVars, null);

		//Can't in-line this because one can't use the 'this' keyword in a constructor
		this.setChildren(new BaseNode[] {
											old.getChildAt(0).cascadeClone(new ParentRef(this, 0), newVars, ApplyMutateChance), });
	}
	
	public DubGetterNode(BaseNode Var1, GlobalVarsPool inVars)
	{
		super(null, NodeType.DubGetter, new BaseNode[]{Var1});
		this.CargoVars = inVars;
	}

	@Override
	public double getValue()
	{
		int index = HelperAutobot.getIndexFromNode(this.getChildAt(0), this.CargoVars.getDubArrLength());
		
		return this.CargoVars.getDoubleAt(index);
	}

	@Override
	public StringBuilder toStringBuilder()
	{
		StringBuilder result = new StringBuilder();
		
		result.append("DubArray[");
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
			
			result.append("DubGetter");
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
		in.append("GetDubAt(");
		super.getChildAt(0).toStringBuilder(in);
		in.append(")");
		
		return in;
	}

	@Override
	public BaseNode cascadeClone(ParentRef StepParent, GlobalVarsPool newVars, boolean ApplyMutateChance)
	{
		if (ApplyMutateChance && RandyAutobot.flipCoin(ConstantAutobot.getMutationChance()))
		{
			return new IntGetterNode(this, StepParent, newVars, ApplyMutateChance);
		}
		else
		{
			return new DubGetterNode(this, StepParent, newVars, ApplyMutateChance);
		}
	}
}
