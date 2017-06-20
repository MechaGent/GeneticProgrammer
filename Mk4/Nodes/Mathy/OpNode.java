package EnemyLevelScaling.GeneticProgrammer.Mk4.Nodes.Mathy;

import EnemyLevelScaling.GeneticProgrammer.Mk4.Nodes.Standards.FuncNode;
import EnemyLevelScaling.GeneticProgrammer.Mk4.Nodes.Standards.ParentRef;
import EnemyLevelScaling.GeneticProgrammer.Mk4.Nodes.Standards.Enums.MathOpType;
import EnemyLevelScaling.GeneticProgrammer.Mk4.Nodes.Standards.Enums.NodeType;

public class OpNode implements FuncNode
{
	private NodeType Type;
	private ParentRef Parent;
	private FuncNode[] Children;

	private MathOpType OpType;

	public OpNode()
	{
		this(null, null, MathOpType.getRandomOpType());
	}

	public OpNode(ParentRef inParent, FuncNode[] inChildren, MathOpType inOpType)
	{
		this.Type = NodeType.Op;
		this.Parent = inParent;
		this.Children = inChildren;
		this.OpType = inOpType;
	}

	@Override
	public void setParentRef(ParentRef inParent)
	{
		this.Parent = inParent;
	}

	@Override
	public void setChildren(FuncNode[] inChildren)
	{
		this.Children = inChildren;
	}

	@Override
	public NodeType getType()
	{
		return this.Type;
	}

	@Override
	public void dooWop()
	{

	}

	@Override
	public ParentRef getParent()
	{
		return this.Parent;
	}

	@Override
	public FuncNode getChildAtIndex(byte in)
	{
		return this.Children[in];
	}

	@Override
	public Object getValue()
	{
		double result;
		double a = (double) this.Children[0].getValue();
		double b = (double) this.Children[0].getValue();

		switch (this.OpType)
		{
		case Add:
			{
				result = a + b;
				break;
			}
		case Divide:
			{
				result = a / b;
				break;
			}
		case Multiply:
			{
				result = a * b;
				break;
			}
		case Subtract:
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
		return new StringBuilder(this.Children[0].toStringBuilder() + this.OpType.getStringForm() + this.Children[1].toStringBuilder() + ";\n");
	}

}
