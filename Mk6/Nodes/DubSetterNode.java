package EnemyLevelScaling.GeneticProgrammer.Mk6.Nodes;

import EnemyLevelScaling.GeneticProgrammer.Mk6.BaseNode;
import EnemyLevelScaling.GeneticProgrammer.Mk6.GlobalVars;
import EnemyLevelScaling.GeneticProgrammer.Mk6.ParentRef;
import EnemyLevelScaling.GeneticProgrammer.Mk6.Autobots.ConstantAutobot;
import EnemyLevelScaling.GeneticProgrammer.Mk6.Autobots.HelperAutobot;
import EnemyLevelScaling.GeneticProgrammer.Mk6.Autobots.RandyAutobot;
import EnemyLevelScaling.GeneticProgrammer.Mk6.Enums.NodeType;
import EnemyLevelScaling.GeneticProgrammer.Mk6.Enums.ReturnType;
import EnemyLevelScaling.GeneticProgrammer.Mk6.Nodes.Interfaces.Node;
import EnemyLevelScaling.GeneticProgrammer.Mk6.Nodes.Interfaces.VoidNode;

public class DubSetterNode extends BaseNode implements VoidNode
{
	private GlobalVars CargArr;

	public DubSetterNode(GlobalVars inCargArr)
	{
		super(NodeType.DubSetter);
		this.CargArr = inCargArr;
	}
	
	public DubSetterNode(DubSetterNode old, ParentRef StepParent, GlobalVars newVars, boolean ApplyMutateChance)
	{
		super(StepParent, NodeType.DubSetter);
		super.setChildren(new Node[]{
				old.getChildAt(0).cascadeClone(new ParentRef(this, 0), newVars, ApplyMutateChance),
				old.getChildAt(1).cascadeClone(new ParentRef(this, 1), newVars, ApplyMutateChance)
				});
		this.CargArr = newVars;
	}
	
	public DubSetterNode(IntSetterNode old, ParentRef StepParent, GlobalVars newVars, boolean ApplyMutateChance)
	{
		super(StepParent, NodeType.DubSetter);
		super.setChildren(new Node[]{
				old.getChildAt(0).cascadeClone(new ParentRef(this, 0), newVars, ApplyMutateChance),
				old.getChildAt(1).cascadeClone(new ParentRef(this, 1), newVars, ApplyMutateChance)
				});
		this.CargArr = newVars;
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
		in.append("SetDubAt(");
		super.getChildAt(0).toStringBuilder(in);
		in.append(ConstantAutobot.getArgDelimiter());
		super.getChildAt(1).toStringBuilder(in);
		in.append(")");

		return in;
	}

	@Override
	public void DooWop()
	{
		int index = HelperAutobot.getIntFromNode(super.getChildAt(0));
		double input = HelperAutobot.getDubFromNode(super.getChildAt(1));
		
		this.CargArr.setDubArrayAt(index, input);
	}
	
	@Override
	public Node cascadeClone(ParentRef StepParent, GlobalVars newVars, boolean ApplyMutateChance)
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

	@Override
	public int getNumChildren()
	{
		return super.getNumChildren();
	}
}
