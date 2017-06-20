package EnemyLevelScaling.GeneticProgrammer.Mk4.Nodes;

import EnemyLevelScaling.GeneticProgrammer.Mk4.Nodes.Standards.FuncNode;
import EnemyLevelScaling.GeneticProgrammer.Mk4.Nodes.Standards.ParentRef;
import EnemyLevelScaling.GeneticProgrammer.Mk4.Nodes.Standards.Enums.ComparatorType;
import EnemyLevelScaling.GeneticProgrammer.Mk4.Nodes.Standards.Enums.NodeType;

public class WhileNode implements FuncNode
{
	private NodeType Type;
	private ParentRef Parent;
	private FuncNode[] Children;
	private ComparatorType CompType;

	public WhileNode()
	{
		this(null, ComparatorType.getRandomComparType(), null);
	}

	public WhileNode(ParentRef inParent, ComparatorType inCompType, FuncNode[] inChildren)
	{
		this.Type = NodeType.While;
		this.Parent = inParent;
		this.CompType = inCompType;
		this.Children = inChildren;
	}

	@Override
	public void setParentRef(ParentRef inParent)
	{
		this.Parent = inParent;
	}

	@Override
	public NodeType getType()
	{
		return this.Type;
	}

	@Override
	public void dooWop()
	{
		while (this.evalControl())
		{
			this.Children[2].dooWop();
		}
	}

	private boolean evalControl()
	{
		boolean result;
		double a = (double) this.Children[0].getValue();
		double b = (double) this.Children[1].getValue();
		
		switch (this.CompType)
		{
		case EqualToNumNum:
			{
				result = a == b;
				break;
			}
		case GreaterThanNumNum:
			{
				result = a > b;
				break;
			}
		case LessThanNumNum:
			{
				result = a < b;
				break;
			}
		case NotEqualToNumNum:
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
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setChildren(FuncNode[] inChildren)
	{
		this.Children = inChildren;
	}

	@Override
	public StringBuilder toStringBuilder()
	{
		StringBuilder result = new StringBuilder("while(");
		result.append(this.Children[0].toStringBuilder() + this.CompType.getStringForm() + this.Children[1].toStringBuilder() + ")\n{\n");
		result.append(this.Children[2].toStringBuilder() + "\n}\n");
		return result;
	}

}
