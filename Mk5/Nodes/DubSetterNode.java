package EnemyLevelScaling.GeneticProgrammer.Mk5.Nodes;

import EnemyLevelScaling.GeneticProgrammer.Mk5.BaseNode;
import EnemyLevelScaling.GeneticProgrammer.Mk5.ConstantAutobot;
import EnemyLevelScaling.GeneticProgrammer.Mk5.GlobalVars;
import EnemyLevelScaling.GeneticProgrammer.Mk5.HelperAutobot;
import EnemyLevelScaling.GeneticProgrammer.Mk5.ParentRef;
import EnemyLevelScaling.GeneticProgrammer.Mk5.RandyAutobot;
import EnemyLevelScaling.GeneticProgrammer.Mk5.Enums.ReturnType;
import EnemyLevelScaling.GeneticProgrammer.Mk5.Nodes.Interfaces.Node;
import EnemyLevelScaling.GeneticProgrammer.Mk5.Nodes.Interfaces.VoidNode;

public class DubSetterNode extends BaseNode implements VoidNode
{
	private ReturnType Returns;
	private ReturnType[][] Returnables;

	private GlobalVars CargArr;

	public DubSetterNode(GlobalVars inCargArr)
	{
		super();
		this.Returns = ReturnType.Void;
		this.Returnables = new ReturnType[][] { { ReturnType.Integer, ReturnType.Double },{ ReturnType.Integer, ReturnType.Double } };

		this.CargArr = inCargArr;
	}

	@Override
	public ReturnType getReturnType()
	{
		return this.Returns;
	}

	@Override
	public ReturnType[] getAllowedReturnTypesForChildAt(int index)
	{
		return this.Returnables[index];
	}

	@Override
	public StringBuilder toStringBuilder()
	{
		StringBuilder result = new StringBuilder();
		result.append("SetDubAt(");
		result.append(super.getChildAt(0).toStringBuilder());
		result.append(ConstantAutobot.getDelimiter());
		result.append(super.getChildAt(1).toStringBuilder());
		result.append(")");
		
		return result;
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
		Node result;
		
		if(ApplyMutateChance && RandyAutobot.flipCoin(ConstantAutobot.getMutationChance()))
		{
			result = new IntSetterNode(this.CargArr);
		}
		else
		{
			result = new DubSetterNode(this.CargArr);
		}
		
		result.setParent(StepParent);
		
		Node[] StepChildren = new Node[this.getNumChildren()];
		
		for(int i = 0; i < StepChildren.length; i++)
		{
			StepChildren[i] = super.getChildAt(i).cascadeClone(new ParentRef(this, i), newVars, ApplyMutateChance);
		}
		
		result.setChildren(StepChildren);
		
		return result;
	}
	
	@Override
	public int getNumChildren()
	{
		return this.Returnables.length;
	}
}
