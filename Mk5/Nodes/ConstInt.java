package EnemyLevelScaling.GeneticProgrammer.Mk5.Nodes;

import EnemyLevelScaling.GeneticProgrammer.Mk5.BaseNode;
import EnemyLevelScaling.GeneticProgrammer.Mk5.ConstantAutobot;
import EnemyLevelScaling.GeneticProgrammer.Mk5.GlobalVars;
import EnemyLevelScaling.GeneticProgrammer.Mk5.ParentRef;
import EnemyLevelScaling.GeneticProgrammer.Mk5.RandyAutobot;
import EnemyLevelScaling.GeneticProgrammer.Mk5.Enums.ReturnType;
import EnemyLevelScaling.GeneticProgrammer.Mk5.Nodes.Interfaces.IntNode;
import EnemyLevelScaling.GeneticProgrammer.Mk5.Nodes.Interfaces.Node;

public class ConstInt extends BaseNode implements IntNode
{
	private ReturnType Returns;
	private ReturnType[][] Returnables;
	
	private int Value;

	public ConstInt()
	{
		this(RandyAutobot.getRandyInt(-ConstantAutobot.getConstBounds(), ConstantAutobot.getConstBounds()));
	}
	
	public ConstInt(int inValue)
	{
		super();
		this.Returns = ReturnType.Integer;
		this.Returnables = new ReturnType[0][0];
		
		this.Value = inValue;
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
		return new StringBuilder(this.Value + "");
	}

	@Override
	public int getValue()
	{
		return this.Value;
	}
	
	@Override
	public Node cascadeClone(ParentRef StepParent, GlobalVars newVars, boolean ApplyMutateChance)
	{
		Node result;
		int tempValue;
		
		if(ApplyMutateChance && RandyAutobot.flipCoin(ConstantAutobot.getMutationChance()))
		{
			tempValue = this.Value + RandyAutobot.getRandyInt(-ConstantAutobot.getConstDeltaMax(), ConstantAutobot.getConstDeltaMax());
		}
		else
		{
			tempValue = this.Value;
		}
		
		result = new ConstInt(tempValue);
		
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
