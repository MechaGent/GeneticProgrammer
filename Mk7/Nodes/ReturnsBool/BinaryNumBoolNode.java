package EnemyLevelScaling.GeneticProgrammer.Mk7.Nodes.ReturnsBool;

import EnemyLevelScaling.GeneticProgrammer.Mk7.Autobots.ConstantAutobot;
import EnemyLevelScaling.GeneticProgrammer.Mk7.Autobots.HelperAutobot;
import EnemyLevelScaling.GeneticProgrammer.Mk7.GlobalVarsPool;
import EnemyLevelScaling.GeneticProgrammer.Mk7.ParentRef;
import EnemyLevelScaling.GeneticProgrammer.Mk7.Autobots.RandyAutobot;
import EnemyLevelScaling.GeneticProgrammer.Mk7.Enums.NodeType;
import EnemyLevelScaling.GeneticProgrammer.Mk7.Enums.OpTypes.BinaryNumBoolOp;
import EnemyLevelScaling.GeneticProgrammer.Mk7.Nodes.AbstractClasses.BaseNode;
import EnemyLevelScaling.GeneticProgrammer.Mk7.Nodes.AbstractClasses.BoolNode;

public class BinaryNumBoolNode extends BoolNode
{
	private BinaryNumBoolOp Op;
	
	public BinaryNumBoolNode()
	{
		this(null, RandyAutobot.getRandyBinaryNumBoolOp(), new BaseNode[NodeType.BinaryNumBool.getNumberOfExpectedArgumentSets()]);
	}
	
	public BinaryNumBoolNode(ParentRef inParent, BinaryNumBoolOp inOp, BaseNode[] inChildren)
	{
		super(inParent, NodeType.BinaryNumBool, inChildren);
		this.Op = inOp;
	}
	
	public BinaryNumBoolNode(BinaryNumBoolNode old, BinaryNumBoolOp inOp, ParentRef StepParent, GlobalVarsPool newVars, boolean ApplyMutateChance)
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
	public BinaryNumBoolNode(BinaryNumBoolOp inOp, BaseNode Arg1, BaseNode Arg2)
	{
		this(null, inOp, new BaseNode[]{Arg1, Arg2});
	}

	@Override
	public boolean getValue()
	{
		boolean result;
		double a = HelperAutobot.getDubFromNode(this.getChildAt(0));
		double b = HelperAutobot.getDubFromNode(this.getChildAt(1));

		switch (this.Op)
		{
		case EqualTo:
			{
				result = a == b;
				break;
			}
		case GreaterThan:
			{
				result = a > b;
				break;
			}
		case GreaterThanOrEqualTo:
			{
				result = a >= b;
				break;
			}
		case LessThan:
			{
				result = a < b;
				break;
			}
		case LessThanOrEqualTo:
			{
				result = a <= b;
				break;
			}
		case NotEqualTo:
			{
				result = a != b;
				break;
			}
		default:
			{
				result = false;
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
			
			result.append("BinaryNumBool");
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
		in.append("BinaryNumBool(");
		in.append(this.Op.toString());
		in.append(ConstantAutobot.getArgDelimiter());
		super.getChildAt(0).toStringBuilder(in);
		in.append(ConstantAutobot.getArgDelimiter());
		super.getChildAt(1).toStringBuilder(in);
		in.append(")");

		return in;
	}

	@Override
	public BaseNode cascadeClone(ParentRef StepParent, GlobalVarsPool newVars, boolean ApplyMutateChance)
	{
		if(ApplyMutateChance && RandyAutobot.flipCoin(ConstantAutobot.getMutationChance()))
		{
			return new BinaryNumBoolNode(this, RandyAutobot.getRandyBinaryNumBoolOp(), StepParent, newVars, ApplyMutateChance);
		}
		else
		{
			return new BinaryNumBoolNode(this, this.Op, StepParent, newVars, ApplyMutateChance);
		}
	}
}
