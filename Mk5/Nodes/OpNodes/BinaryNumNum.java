package EnemyLevelScaling.GeneticProgrammer.Mk5.Nodes.OpNodes;

import EnemyLevelScaling.GeneticProgrammer.Mk5.BaseNode;
import EnemyLevelScaling.GeneticProgrammer.Mk5.ConstantAutobot;
import EnemyLevelScaling.GeneticProgrammer.Mk5.GlobalVars;
import EnemyLevelScaling.GeneticProgrammer.Mk5.HelperAutobot;
import EnemyLevelScaling.GeneticProgrammer.Mk5.ParentRef;
import EnemyLevelScaling.GeneticProgrammer.Mk5.RandyAutobot;
import EnemyLevelScaling.GeneticProgrammer.Mk5.Enums.BinaryNumNumOp;
import EnemyLevelScaling.GeneticProgrammer.Mk5.Enums.ReturnType;
import EnemyLevelScaling.GeneticProgrammer.Mk5.Nodes.Interfaces.DubNode;
import EnemyLevelScaling.GeneticProgrammer.Mk5.Nodes.Interfaces.Node;

public class BinaryNumNum extends BaseNode implements DubNode
{
	private ReturnType Returns;
	private ReturnType[][] Returnables;

	private BinaryNumNumOp Op;

	public BinaryNumNum()
	{
		this(RandyAutobot.getRandyBinaryNumNumOp());
	}
	
	public BinaryNumNum(BinaryNumNumOp inOp)
	{
		super();
		this.Returns = ReturnType.Double;
		this.Returnables = new ReturnType[][] { { ReturnType.Integer, ReturnType.Double }, { ReturnType.Integer, ReturnType.Double } };

		this.Op = inOp;
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
		
		result.append("BinaryNumNum(");
		result.append(this.Op.toString());
		result.append(ConstantAutobot.getDelimiter());
		result.append(super.getChildAt(0).toStringBuilder());
		result.append(ConstantAutobot.getDelimiter());
		result.append(super.getChildAt(1).toStringBuilder());
		result.append(")");
		
		return result;
	}

	@Override
	public double getValue()
	{
		double result;
		double a = HelperAutobot.getDubFromNode(super.getChildAt(0));
		double b = HelperAutobot.getDubFromNode(super.getChildAt(1));

		switch (this.Op)
		{
		case Add:
			{
				result = a + b;
				break;
			}
		case Div:
			{
				result = a / b;
				break;
			}
		case Mod:
			{
				result = (a % b + b) % b;	//Apparently Java's Mod operator behaves oddly, which this fixes
				break;
			}
		case Mult:
			{
				result = a * b;
				break;
			}
		case Pow:
			{
				result = Math.pow(a, b);
				break;
			}
		case Sub:
			{
				result = a - b;
				break;
			}
		default:
			{
				result = -69;
				break;
			}
		}
		
		return result;
	}
	
	@Override
	public Node cascadeClone(ParentRef StepParent, GlobalVars newVars, boolean ApplyMutateChance)
	{
		Node result;

		if (ApplyMutateChance && RandyAutobot.flipCoin(ConstantAutobot.getMutationChance()))
		{
			result = new BinaryNumNum();
		}
		else
		{
			result = new BinaryNumNum(this.Op);
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
