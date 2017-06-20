package EnemyLevelScaling.GeneticProgrammer.Mk4.Nodes.Mathy;

import EnemyLevelScaling.GeneticProgrammer.Mk4.Nodes.Standards.Accumulator;
import EnemyLevelScaling.GeneticProgrammer.Mk4.Nodes.Standards.FuncNode;
import EnemyLevelScaling.GeneticProgrammer.Mk4.Nodes.Standards.ParentRef;
import EnemyLevelScaling.GeneticProgrammer.Mk4.Nodes.Standards.Enums.MathOpType;
import EnemyLevelScaling.GeneticProgrammer.Mk4.Nodes.Standards.Enums.NodeType;

public class AccOpNode implements FuncNode
{
	private NodeType Type;
	private ParentRef Parent;
	private FuncNode Child;

	private MathOpType OpType;
	private Accumulator<?> Acc;

	public AccOpNode(Accumulator<?> inAcc)
	{
		this(null, null, MathOpType.getRandomOpType(), inAcc);
	}

	public AccOpNode(ParentRef inParent, FuncNode inChild, MathOpType inOpType, Accumulator<?> inAcc)
	{
		this.Type = NodeType.AccOp;
		this.Parent = inParent;
		this.Child = inChild;
		this.OpType = inOpType;
		this.Acc = inAcc;
	}

	@Override
	public void setParentRef(ParentRef inParent)
	{
		this.Parent = inParent;
	}

	@Override
	public void setChildren(FuncNode[] inChildren)
	{
		this.Child = inChildren[0];
	}

	@Override
	public NodeType getType()
	{
		return this.Type;
	}

	@Override
	public void dooWop()
	{
		double bucket = (double) this.Child.getValue();
		
		switch (this.OpType)
		{
		case Add:
			{
				this.Acc.addToAcc(bucket);
				break;
			}
		case Divide:
			{
				this.Acc.divAccBy(bucket);
				break;
			}
		case Multiply:
			{
				this.Acc.multAccBy(bucket);
				break;
			}
		case Subtract:
			{
				this.Acc.subFromAcc(bucket);
				break;
			}
		default:
			{
				break;
			}
		}
	}

	@Override
	public ParentRef getParent()
	{
		return this.Parent;
	}

	@Override
	public FuncNode getChildAtIndex(byte in)
	{
		return this.Child;
	}

	@Override
	public Object getValue()
	{
		return null;
	}

	@Override
	public StringBuilder toStringBuilder()
	{
		return new StringBuilder(this.Acc.getType() + this.OpType.getStringForm() + this.Child.toStringBuilder() + ";\n");
	}

}
