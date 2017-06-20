package EnemyLevelScaling.GeneticProgrammer.Mk6.Nodes;

import EnemyLevelScaling.GeneticProgrammer.Mk6.BaseNode;
import EnemyLevelScaling.GeneticProgrammer.Mk6.GlobalVars;
import EnemyLevelScaling.GeneticProgrammer.Mk6.ParentRef;
import EnemyLevelScaling.GeneticProgrammer.Mk6.Autobots.ConstantAutobot;
import EnemyLevelScaling.GeneticProgrammer.Mk6.Autobots.HelperAutobot;
import EnemyLevelScaling.GeneticProgrammer.Mk6.Autobots.RandyAutobot;
import EnemyLevelScaling.GeneticProgrammer.Mk6.Enums.NodeType;
import EnemyLevelScaling.GeneticProgrammer.Mk6.Enums.ReturnType;
import EnemyLevelScaling.GeneticProgrammer.Mk6.Nodes.Interfaces.IntNode;
import EnemyLevelScaling.GeneticProgrammer.Mk6.Nodes.Interfaces.Node;

public class IntGetterNode extends BaseNode implements IntNode
{
	private GlobalVars CargArr;

	public IntGetterNode(GlobalVars inCargArr)
	{
		super(NodeType.IntGetter);
		this.CargArr = inCargArr;
	}
	
	public IntGetterNode(IntGetterNode old, ParentRef StepParent, GlobalVars newVars, boolean ApplyMutateChance)
	{
		super(StepParent, NodeType.IntGetter);
		super.setChildren(new Node[]{
				old.getChildAt(0).cascadeClone(new ParentRef(this, 0), newVars, ApplyMutateChance)
				});
		this.CargArr = newVars;
	}
	
	public IntGetterNode(DubGetterNode old, ParentRef StepParent, GlobalVars newVars, boolean ApplyMutateChance)
	{
		super(StepParent, NodeType.IntGetter);
		super.setChildren(new Node[]{
				old.getChildAt(0).cascadeClone(new ParentRef(this, 0), newVars, ApplyMutateChance)
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
		in.append("GetIntAt(");
		super.getChildAt(0).toStringBuilder(in);
		in.append(")");

		return in;
	}

	@Override
	public int getValue()
	{
		int RawIndex = HelperAutobot.getIntFromNode(super.getChildAt(0));
		int ArrayLength = this.CargArr.getIntArrLength();
		int index = (RawIndex % ArrayLength + ArrayLength) % ArrayLength;
		
		return this.CargArr.getIntAt(index);
	}
	
	@Override
	public Node cascadeClone(ParentRef StepParent, GlobalVars newVars, boolean ApplyMutateChance)
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
	
	@Override
	public int getNumChildren()
	{
		return super.getNumChildren();
	}
}
