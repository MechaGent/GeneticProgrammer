package EnemyLevelScaling.GeneticProgrammer.Mk7.Nodes.ReturnsDouble;

import EnemyLevelScaling.GeneticProgrammer.Mk7.GlobalVarsPool;
import EnemyLevelScaling.GeneticProgrammer.Mk7.ParentRef;
import EnemyLevelScaling.GeneticProgrammer.Mk7.Autobots.ConstantAutobot;
import EnemyLevelScaling.GeneticProgrammer.Mk7.Autobots.RandyAutobot;
import EnemyLevelScaling.GeneticProgrammer.Mk7.Enums.NodeType;
import EnemyLevelScaling.GeneticProgrammer.Mk7.Nodes.AbstractClasses.BaseNode;
import EnemyLevelScaling.GeneticProgrammer.Mk7.Nodes.AbstractClasses.DubNode;

public class VarGetterNode extends DubNode
{
	private int VarIndex;
	
	public VarGetterNode()
	{
		this(null, RandyAutobot.getRandyInt(0, ConstantAutobot.getNumVars() - 1));
	}
	
	public VarGetterNode(ParentRef inParent, int inIndex)
	{
		super(inParent, NodeType.VarGetter, new BaseNode[0]);
		this.VarIndex = inIndex;
	}
	
	public VarGetterNode(VarGetterNode old, int inIndex, ParentRef StepParent, GlobalVarsPool newVars, boolean ApplyMutateChance)
	{
		this(StepParent, inIndex);
	}
	
	/**
	 * Constructor used by the parser
	 * @param index
	 */
	public VarGetterNode(int index)
	{
		this(null, index);
	}

	@Override
	public double getValue()
	{
		return ConstantAutobot.getVarValue(VarIndex);
	}

	@Override
	public StringBuilder toStringBuilder()
	{
		StringBuilder result = new StringBuilder();
		
		result.append("VarArray[\"");
		result.append(ConstantAutobot.getVarName(VarIndex));
		result.append("\"]");
		
		return result;
	}
	
	@Override
	public StringBuilder toStringBuilder(boolean WantsDiagnosticSyntax)
	{
		if(WantsDiagnosticSyntax)
		{
			StringBuilder result = new StringBuilder();
			
			result.append("VarGetter");
			result.append(this.getTotalOffspringAsString());
			result.append("(\"" + ConstantAutobot.getVarName(VarIndex) + "\")");
			
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
		in.append("GetVar(\"");
		in.append(ConstantAutobot.getVarName(VarIndex));
		in.append("\")");
		
		return in;
	}

	@Override
	public BaseNode cascadeClone(ParentRef StepParent, GlobalVarsPool newVars, boolean ApplyMutateChance)
	{
		if(ApplyMutateChance && RandyAutobot.flipCoin(ConstantAutobot.getMutationChance()))
		{
			return new VarGetterNode(this, RandyAutobot.getRandyInt(0, ConstantAutobot.getNumVars() - 1), StepParent, newVars, ApplyMutateChance);
		}
		else
		{
			return new VarGetterNode(this, this.VarIndex, StepParent, newVars, ApplyMutateChance);
		}
	}
}
