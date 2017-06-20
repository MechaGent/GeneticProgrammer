package EnemyLevelScaling.GeneticProgrammer.Mk6.Nodes;

import EnemyLevelScaling.GeneticProgrammer.Mk6.BaseNode;
import EnemyLevelScaling.GeneticProgrammer.Mk6.GlobalVars;
import EnemyLevelScaling.GeneticProgrammer.Mk6.ParentRef;
import EnemyLevelScaling.GeneticProgrammer.Mk6.Autobots.ConstantAutobot;
import EnemyLevelScaling.GeneticProgrammer.Mk6.Autobots.RandyAutobot;
import EnemyLevelScaling.GeneticProgrammer.Mk6.Enums.NodeType;
import EnemyLevelScaling.GeneticProgrammer.Mk6.Enums.ReturnType;
import EnemyLevelScaling.GeneticProgrammer.Mk6.Nodes.Interfaces.DubNode;
import EnemyLevelScaling.GeneticProgrammer.Mk6.Nodes.Interfaces.Node;

public class GetVarNode extends BaseNode implements DubNode
{
	private int NameIndex;

	public GetVarNode()
	{
		this(RandyAutobot.getRandyInt(0, ConstantAutobot.getNumVars() - 1));
	}
	
	public GetVarNode(int inName)
	{
		super(NodeType.GetVar);
		this.NameIndex = inName;
	}
	
	public GetVarNode(int inName, ParentRef StepParent)
	{
		super(StepParent, NodeType.GetVar);
		this.NameIndex = inName;
	}

	@Override
	public ReturnType getReturnType()
	{
		return super.getReturnType();
	}

	@Override
	public ReturnType[] getExpectedArgumentsFromChildAt(int index)
	{
		return super.getExpectedArgumentsFromChildAt(index);
	}

	@Override
	public StringBuilder toStringBuilder(StringBuilder in)
	{
		in.append("GetVar(");
		in.append(ConstantAutobot.getVarName(NameIndex));
		in.append(")");
		
		return in;
	}

	@Override
	public double getValue()
	{
		return ConstantAutobot.getVarValue(NameIndex);
	}
	
	@Override
	public Node cascadeClone(ParentRef StepParent, GlobalVars newVars, boolean ApplyMutateChance)
	{
		if(ApplyMutateChance && RandyAutobot.flipCoin(ConstantAutobot.getMutationChance()))
		{
			return new GetVarNode(RandyAutobot.getRandyInt(0, ConstantAutobot.getNumVars() - 1), StepParent);
		}
		else
		{
			return new GetVarNode(this.NameIndex, StepParent);
		}
	}
	
	@Override
	public int getNumChildren()
	{
		return super.getNumChildren();
	}
}
