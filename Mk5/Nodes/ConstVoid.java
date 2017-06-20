package EnemyLevelScaling.GeneticProgrammer.Mk5.Nodes;

import EnemyLevelScaling.GeneticProgrammer.Mk5.BaseNode;
import EnemyLevelScaling.GeneticProgrammer.Mk5.GlobalVars;
import EnemyLevelScaling.GeneticProgrammer.Mk5.ParentRef;
import EnemyLevelScaling.GeneticProgrammer.Mk5.Enums.ReturnType;
import EnemyLevelScaling.GeneticProgrammer.Mk5.Nodes.Interfaces.Node;
import EnemyLevelScaling.GeneticProgrammer.Mk5.Nodes.Interfaces.VoidNode;

public class ConstVoid extends BaseNode implements VoidNode
{
	private ReturnType Returns;
	private ReturnType[][] Returnables;

	public ConstVoid()
	{
		super();
		this.Returns = ReturnType.Boolean;
		this.Returnables = new ReturnType[0][0];
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
		return new StringBuilder("VoidConst()");
	}

	@Override
	public Node cascadeClone(ParentRef StepParent, GlobalVars newVars, boolean ApplyMutateChance)
	{
		Node result = new ConstVoid();
		
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
	public void DooWop()
	{
		
	}
}
