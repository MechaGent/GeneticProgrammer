package EnemyLevelScaling.GeneticProgrammer.Mk5.Nodes;

import EnemyLevelScaling.GeneticProgrammer.Mk5.BaseNode;
import EnemyLevelScaling.GeneticProgrammer.Mk5.ConstantAutobot;
import EnemyLevelScaling.GeneticProgrammer.Mk5.GlobalVars;
import EnemyLevelScaling.GeneticProgrammer.Mk5.ParentRef;
import EnemyLevelScaling.GeneticProgrammer.Mk5.RandyAutobot;
import EnemyLevelScaling.GeneticProgrammer.Mk5.Enums.ReturnType;
import EnemyLevelScaling.GeneticProgrammer.Mk5.Nodes.Interfaces.BoolNode;
import EnemyLevelScaling.GeneticProgrammer.Mk5.Nodes.Interfaces.Node;

public class ConstBool extends BaseNode implements BoolNode
{
	private ReturnType Returns;
	private ReturnType[][] Returnables;
	
	private boolean Value;

	public ConstBool()
	{
		this(RandyAutobot.flipCoin());
	}
	
	public ConstBool(boolean inValue)
	{
		super();
		this.Returns = ReturnType.Boolean;
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
	public int getNumChildren()
	{
		return this.Returnables.length;
	}

	@Override
	public StringBuilder toStringBuilder()
	{
		return new StringBuilder(this.Value + "");
	}

	@Override
	public Node cascadeClone(ParentRef StepParent, GlobalVars newVars, boolean ApplyMutateChance)
	{
		Node result;
		
		if(ApplyMutateChance && RandyAutobot.flipCoin(ConstantAutobot.getMutationChance()))
		{
			result = new ConstBool();
		}
		else
		{
			result = new ConstBool(this.Value);
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
	public boolean getValue()
	{
		return this.Value;
	}
}
