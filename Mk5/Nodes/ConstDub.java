package EnemyLevelScaling.GeneticProgrammer.Mk5.Nodes;

import EnemyLevelScaling.GeneticProgrammer.Mk5.BaseNode;
import EnemyLevelScaling.GeneticProgrammer.Mk5.ConstantAutobot;
import EnemyLevelScaling.GeneticProgrammer.Mk5.GlobalVars;
import EnemyLevelScaling.GeneticProgrammer.Mk5.ParentRef;
import EnemyLevelScaling.GeneticProgrammer.Mk5.RandyAutobot;
import EnemyLevelScaling.GeneticProgrammer.Mk5.Enums.ReturnType;
import EnemyLevelScaling.GeneticProgrammer.Mk5.Nodes.Interfaces.DubNode;
import EnemyLevelScaling.GeneticProgrammer.Mk5.Nodes.Interfaces.Node;

public class ConstDub extends BaseNode implements DubNode
{
	private ReturnType Returns;
	private ReturnType[][] Returnables;
	
	private double Value;

	public ConstDub()
	{
		this(RandyAutobot.getRandyDub(-ConstantAutobot.getConstBounds(), ConstantAutobot.getConstBounds()));
	}
	
	public ConstDub(double inValue)
	{
		super();
		this.Returns = ReturnType.Double;
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
	public double getValue()
	{
		return this.Value;
	}
	
	@Override
	public Node cascadeClone(ParentRef StepParent, GlobalVars newVars, boolean ApplyMutateChance)
	{
		Node result;
		double tempValue;
		
		if(ApplyMutateChance && RandyAutobot.flipCoin(ConstantAutobot.getMutationChance()))
		{
			tempValue = this.Value + RandyAutobot.getRandyDub(-ConstantAutobot.getConstDeltaMax(), ConstantAutobot.getConstDeltaMax());
		}
		else
		{
			tempValue = this.Value;
		}
		
		result = new ConstDub(tempValue);
		
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
