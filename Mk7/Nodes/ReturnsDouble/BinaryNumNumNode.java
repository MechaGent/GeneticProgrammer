package EnemyLevelScaling.GeneticProgrammer.Mk7.Nodes.ReturnsDouble;

import EnemyLevelScaling.GeneticProgrammer.Mk7.Autobots.ConstantAutobot;
import EnemyLevelScaling.GeneticProgrammer.Mk7.Autobots.HelperAutobot;
import EnemyLevelScaling.GeneticProgrammer.Mk7.GlobalVarsPool;
import EnemyLevelScaling.GeneticProgrammer.Mk7.ParentRef;
import EnemyLevelScaling.GeneticProgrammer.Mk7.Autobots.RandyAutobot;
import EnemyLevelScaling.GeneticProgrammer.Mk7.Enums.NodeType;
import EnemyLevelScaling.GeneticProgrammer.Mk7.Enums.OpTypes.BinaryNumNumOp;
import EnemyLevelScaling.GeneticProgrammer.Mk7.Nodes.AbstractClasses.BaseNode;
import EnemyLevelScaling.GeneticProgrammer.Mk7.Nodes.AbstractClasses.DubNode;

public class BinaryNumNumNode extends DubNode
{
private BinaryNumNumOp Op;
	
	public BinaryNumNumNode()
	{
		this(null, RandyAutobot.getRandyBinaryNumNumOp(), new BaseNode[NodeType.BinaryNumNum.getNumberOfExpectedArgumentSets()]);
	}
	
	public BinaryNumNumNode(ParentRef inParent, BinaryNumNumOp inOp, BaseNode[] inChildren)
	{
		super(inParent, NodeType.BinaryNumNum, inChildren);
		this.Op = inOp;
	}
	
	public BinaryNumNumNode(BinaryNumNumNode old, BinaryNumNumOp inOp, ParentRef StepParent, GlobalVarsPool newVars, boolean ApplyMutateChance)
	{
		this(StepParent, inOp, null);

		//Can't in-line this because one can't use the 'this' keyword in a constructor
		this.setChildren(new BaseNode[] {
											old.getChildAt(0).cascadeClone(new ParentRef(this, 0), newVars, ApplyMutateChance),
											old.getChildAt(1).cascadeClone(new ParentRef(this, 1), newVars, ApplyMutateChance), });
	}
	
	/**
	 * Constructor used by the parser
	 * @param inOp
	 * @param Arg1
	 * @param Arg2
	 */
	public BinaryNumNumNode(BinaryNumNumOp inOp, BaseNode Arg1, BaseNode Arg2)
	{
		this(null, inOp, new BaseNode[]{Arg1, Arg2});
	}

	@Override
	public double getValue()
	{
		double result;
		double a = HelperAutobot.getDubFromNode(this.getChildAt(0));
		double b = HelperAutobot.getDubFromNode(this.getChildAt(1));

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
	public StringBuilder toStringBuilder()
	{
		return this.Op.toStringBuilder(this.getChildAt(0), this.getChildAt(1));
	}
	
	@Override
	public StringBuilder toStringBuilder(boolean WantsDiagnosticSyntax)
	{
		if(WantsDiagnosticSyntax)
		{
			StringBuilder result = new StringBuilder();
			
			result.append("BinaryNumNum");
			result.append(this.getTotalOffspringAsString());
			result.append("(");
			result.append(this.Op.toString());
			result.append(ConstantAutobot.getArgDelimiter());
			result.append(this.getChildAt(0).toStringBuilder(WantsDiagnosticSyntax));
			result.append(ConstantAutobot.getArgDelimiter());
			result.append(this.getChildAt(1).toStringBuilder(WantsDiagnosticSyntax));
			result.append(")");
			
			return result;
		}
		else
		{
			return this.toStringBuilder();
		}
	}
	
	@Override
	public StringBuilder toStringBuilder(StringBuilder in)
	{
		/*
		in.append("BinaryNumNum(");
		in.append(this.Op.toString());
		in.append(ConstantAutobot.getArgDelimiter());
		super.getChildAt(0).toStringBuilder(in);
		in.append(ConstantAutobot.getArgDelimiter());
		super.getChildAt(1).toStringBuilder(in);
		in.append(")");
		*/
		
		this.Op.toStringBuilder(in, this.getChildAt(0), this.getChildAt(1));

		return in;
	}

	@Override
	public BaseNode cascadeClone(ParentRef StepParent, GlobalVarsPool newVars, boolean ApplyMutateChance)
	{
		if(ApplyMutateChance && RandyAutobot.flipCoin(ConstantAutobot.getMutationChance()))
		{
			return new BinaryNumNumNode(this, RandyAutobot.getRandyBinaryNumNumOp(), StepParent, newVars, ApplyMutateChance);
		}
		else
		{
			return new BinaryNumNumNode(this, this.Op, StepParent, newVars, ApplyMutateChance);
		}
	}
}
