package EnemyLevelScaling.GeneticProgrammer.Mk5.Nodes;

import EnemyLevelScaling.GeneticProgrammer.Mk5.BaseNode;
import EnemyLevelScaling.GeneticProgrammer.Mk5.ConstantAutobot;
import EnemyLevelScaling.GeneticProgrammer.Mk5.GlobalVars;
import EnemyLevelScaling.GeneticProgrammer.Mk5.ParentRef;
import EnemyLevelScaling.GeneticProgrammer.Mk5.RandyAutobot;
import EnemyLevelScaling.GeneticProgrammer.Mk5.Enums.ReturnType;
import EnemyLevelScaling.GeneticProgrammer.Mk5.Nodes.Interfaces.DubNode;
import EnemyLevelScaling.GeneticProgrammer.Mk5.Nodes.Interfaces.IntNode;
import EnemyLevelScaling.GeneticProgrammer.Mk5.Nodes.Interfaces.Node;

public class DubGetterNode extends BaseNode implements DubNode
{
	private ReturnType Returns;
	private ReturnType[][] Returnables;

	private GlobalVars CargArr;

	public DubGetterNode(GlobalVars inCargArr)
	{
		super();
		this.Returns = ReturnType.Double;
		this.Returnables = new ReturnType[][] { { ReturnType.Integer, ReturnType.Double } };

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
		result.append("GetDubAt(");
		result.append(super.getChildAt(0).toStringBuilder());
		result.append(")");
		
		return result;
	}

	@Override
	public double getValue()
	{
		Node IndexNode = super.getChildAt(0);
		int a;
		int b = this.CargArr.getIntArrLength();
		int index;

		switch (IndexNode.getReturnType())
		{
		case Double:
			{
				a = (int) ((DubNode) IndexNode).getValue();
				break;
			}
		case Integer:
			{
				a = ((IntNode) IndexNode).getValue();
				break;
			}
		case Void:
		case Boolean:
		default:
			{
				a = -69;
				index = -69;
				break;
			}
		}

		index = (a % b + b) % b;
		
		return this.CargArr.getDoubleAt(index);
	}
	
	@Override
	public Node cascadeClone(ParentRef StepParent, GlobalVars newVars, boolean ApplyMutateChance)
	{
		Node result;

		if (ApplyMutateChance && RandyAutobot.flipCoin(ConstantAutobot.getMutationChance()))
		{
			result = new IntGetterNode(this.CargArr);
		}
		else
		{
			result = new DubGetterNode(this.CargArr);
		}

		result.setParent(StepParent);

		Node[] StepChildren = new Node[this.getNumChildren()];

		for (int i = 0; i < StepChildren.length; i++)
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
