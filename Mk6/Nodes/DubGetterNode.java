package EnemyLevelScaling.GeneticProgrammer.Mk6.Nodes;

import EnemyLevelScaling.GeneticProgrammer.Mk6.BaseNode;
import EnemyLevelScaling.GeneticProgrammer.Mk6.GlobalVars;
import EnemyLevelScaling.GeneticProgrammer.Mk6.ParentRef;
import EnemyLevelScaling.GeneticProgrammer.Mk6.Autobots.ConstantAutobot;
import EnemyLevelScaling.GeneticProgrammer.Mk6.Autobots.HelperAutobot;
import EnemyLevelScaling.GeneticProgrammer.Mk6.Autobots.RandyAutobot;
import EnemyLevelScaling.GeneticProgrammer.Mk6.Enums.NodeType;
import EnemyLevelScaling.GeneticProgrammer.Mk6.Enums.ReturnType;
import EnemyLevelScaling.GeneticProgrammer.Mk6.Nodes.Interfaces.DubNode;
import EnemyLevelScaling.GeneticProgrammer.Mk6.Nodes.Interfaces.Node;

public class DubGetterNode extends BaseNode implements DubNode
{
	private GlobalVars CargArr;

	public DubGetterNode(GlobalVars inCargArr)
	{
		super(NodeType.DubGetter);
		this.CargArr = inCargArr;
	}
	
	public DubGetterNode(DubGetterNode old, ParentRef StepParent, GlobalVars newVars, boolean ApplyMutateChance)
	{
		super(StepParent, NodeType.DubGetter);
		super.setChildren(new Node[]{
				old.getChildAt(0).cascadeClone(new ParentRef(this, 0), newVars, ApplyMutateChance)
				});
		this.CargArr = newVars;
	}
	
	public DubGetterNode(IntGetterNode old, ParentRef StepParent, GlobalVars newVars, boolean ApplyMutateChance)
	{
		super(StepParent, NodeType.DubGetter);
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
		in.append("GetDubAt(");
		super.getChildAt(0).toStringBuilder(in);
		in.append(")");
		
		return in;
	}

	@Override
	public double getValue()
	{
		int RawIndex = HelperAutobot.getIntFromNode(super.getChildAt(0));
		int ArrayLength = this.CargArr.getIntArrLength();
		int index = (RawIndex % ArrayLength + ArrayLength) % ArrayLength;
		
		return this.CargArr.getDoubleAt(index);
	}
	
	@Override
	public Node cascadeClone(ParentRef StepParent, GlobalVars newVars, boolean ApplyMutateChance)
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
	
	@Override
	public int getNumChildren()
	{
		return super.getNumChildren();
	}
}
