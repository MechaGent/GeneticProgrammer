package EnemyLevelScaling.GeneticProgrammer.Mk6.Nodes.OpNodes;

import EnemyLevelScaling.GeneticProgrammer.Mk6.BaseNode;
import EnemyLevelScaling.GeneticProgrammer.Mk6.GlobalVars;
import EnemyLevelScaling.GeneticProgrammer.Mk6.ParentRef;
import EnemyLevelScaling.GeneticProgrammer.Mk6.Autobots.ConstantAutobot;
import EnemyLevelScaling.GeneticProgrammer.Mk6.Autobots.HelperAutobot;
import EnemyLevelScaling.GeneticProgrammer.Mk6.Autobots.RandyAutobot;
import EnemyLevelScaling.GeneticProgrammer.Mk6.Enums.BinaryNumNumOp;
import EnemyLevelScaling.GeneticProgrammer.Mk6.Enums.NodeType;
import EnemyLevelScaling.GeneticProgrammer.Mk6.Enums.ReturnType;
import EnemyLevelScaling.GeneticProgrammer.Mk6.Nodes.Interfaces.DubNode;
import EnemyLevelScaling.GeneticProgrammer.Mk6.Nodes.Interfaces.Node;

public class BinaryNumNum extends BaseNode implements DubNode
{
	private BinaryNumNumOp Op;

	public BinaryNumNum()
	{
		this(RandyAutobot.getRandyBinaryNumNumOp());
	}
	
	public BinaryNumNum(BinaryNumNumOp inOp)
	{
		super(NodeType.BinaryNumNum);
		this.Op = inOp;
	}
	
	public BinaryNumNum(ParentRef StepParent, BinaryNumNum old, BinaryNumNumOp inOp, GlobalVars newVars, boolean ApplyMutateChance)
	{
		super(StepParent, NodeType.BinaryNumNum);
		super.setChildren(new Node[]{
				old.getChildAt(0).cascadeClone(new ParentRef(this, 0), newVars, ApplyMutateChance),
				old.getChildAt(1).cascadeClone(new ParentRef(this, 1), newVars, ApplyMutateChance)
				});
		this.Op = inOp;
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
	public StringBuilder toStringBuilder(StringBuilder result)
	{
		result.append("BinaryNumNum(");
		result.append(this.Op.toString());
		result.append(ConstantAutobot.getArgDelimiter());
		super.getChildAt(0).toStringBuilder(result);
		result.append(ConstantAutobot.getArgDelimiter());
		super.getChildAt(1).toStringBuilder(result);
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
		if (ApplyMutateChance && RandyAutobot.flipCoin(ConstantAutobot.getMutationChance()))
		{
			return new BinaryNumNum(StepParent, this, RandyAutobot.getRandyBinaryNumNumOp(), newVars, ApplyMutateChance);
		}
		else
		{
			return new BinaryNumNum(StepParent, this, this.Op, newVars, ApplyMutateChance);
		}
	}
	
	@Override
	public int getNumChildren()
	{
		return super.getNumChildren();
	}
}
