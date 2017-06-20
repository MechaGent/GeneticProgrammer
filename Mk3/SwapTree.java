package EnemyLevelScaling.GeneticProgrammer.Mk3;

import java.util.LinkedList;

import Random.MkNeg01.XorShiftStarRng.Static.XorShiftStar1024;

public class SwapTree
{
	private class Node
	{
		private Node Parent;
		private byte ParentIndex;
		private NodeType Type;
		private Object Cargo;
		private Node[] Children;

		public Node(Node inParent, int inIndex, NodeType inType, Object inCargo)
		{
			this(inParent, inIndex, inType, inCargo, new Node[inType.getNumKids()]);
		}

		public Node(Node inParent, int inIndex, NodeType inType, Object inCargo, Node[] inChildren)
		{
			this.Parent = inParent;
			this.ParentIndex = (byte) inIndex;
			this.Type = inType;
			this.Cargo = inCargo;
			this.Children = inChildren;
		}

		public NodeType getType()
		{
			return this.Type;
		}

		public Node copyNode()
		{
			Node result = new Node(this.Parent, this.ParentIndex, this.Type, this.Cargo);

			for (int i = 0; i < result.Children.length; i++)
			{
				result.Children[i] = this.Children[i].copyNode();
			}

			return result;
		}

		public void SwapSelfWith(Node Other)
		{
			Node SelfParent = this.Parent;
			byte SelfIndex = this.ParentIndex;
			Node OtherParent = Other.Parent;
			byte OtherIndex = Other.ParentIndex;

			SelfParent.Children[SelfIndex] = Other;
			OtherParent.Children[OtherIndex] = this;
		}
	}

	private Node Root;
	private int Size;
	private Accumulator Acc;

	public SwapTree(int DesiredSize)
	{
		this.Root = this.createNode(NodeType.getRandomNonTerminal());
		this.Size = 1;
		this.Acc = new Accumulator();
		LinkedList<Node> HotList = new LinkedList<Node>();
		HotList.add(Root);
		this.fillTree(HotList, DesiredSize);
	}

	private void fillTree(LinkedList<Node> HotList, int DesiredSize)
	{
		while (!HotList.isEmpty())
		{
			Node CurrNode = HotList.pop();

			for (byte i = 0; i < CurrNode.getType().getNumKids(); i++)
			{
				if (this.Size < DesiredSize)
				{
					CurrNode.Children[i] = this.createNode(NodeType.getRandomNodeType());
				} else
				{
					CurrNode.Children[i] = this.createNode(NodeType.getRandomTerminal());
				}

				this.Size++;
				HotList.add(CurrNode.Children[i]);
			}
		}
	}

	public static Node[] getSwappedChildren(Node Root1, int Swap1, Node Root2, int Swap2)
	{
		Node[] result = new Node[2];

		result[0] = Root1.copyNode();
		result[1] = Root2.copyNode();

		Node SwapNode1 = getNodeAtIndex(result[0], Swap1);
		Node SwapNode2 = getNodeAtIndex(result[1], Swap2);

		SwapNode1.SwapSelfWith(SwapNode2);

		return result;
	}

	private static Node getNodeAtIndex(Node Root, int index)
	{
		Node CurrNode = null;
		LinkedList<Node> LastBranch = new LinkedList<Node>();
		LastBranch.add(Root);
		int place = 0;

		while (place < index)
		{
			CurrNode = LastBranch.pop();

			for (int i = 0; i < CurrNode.Children.length; i++)
			{
				LastBranch.add(CurrNode.Children[i]);
			}

			place++;
		}

		return CurrNode;
	}

	private Node createNode(NodeType in)
	{
		Object inCargo = null;

		if (in == NodeType.Const)
		{
			inCargo = XorShiftStar1024.nextDouble(0, ConstantAutobot.getConstInitUpperBounds());
		} else if (in == NodeType.Var)
		{
			inCargo = ConstantAutobot.getRandomVariableName();
		}

		return new Node(null, -1, in, inCargo, new Node[in.getNumKids()]);
	}

	public int getValue()
	{
		return (int) this.getValue(Root);
	}

	private double getValue(Node in)
	{
		double result = 0;

		switch (in.Type)
		{
		case AccAdd:
			{
				result = this.Acc.addToAcc((int) this.getValue(in.Children[0]));
				break;
			}
		case AccDiv:
			{
				result = this.Acc.divAccBy((int) this.getValue(in.Children[0]));
				break;
			}
		case AccMult:
			{
				result = this.Acc.multAccBy((int) this.getValue(in.Children[0]));
				break;
			}
		case AccSub:
			{
				result = this.Acc.subFromAcc((int) this.getValue(in.Children[0]));
				break;
			}
		case Add:
			{
				result = this.getValue(in.Children[0]) + this.getValue(in.Children[1]);
				break;
			}
		case Ceiling:
			{
				//result = Math.ceil(this.getValue(in.Children[0]));
				result = this.getValue(in.Children[0]);
				break;
			}
		case Const:
			{
				result = (Double) in.Cargo;
				break;
			}
		case Div:
			{
				result = this.getValue(in.Children[0]) / this.getValue(in.Children[1]);
				break;
			}
		case Floor:
			{
				//result = Math.floor(this.getValue(in.Children[0]));
				result = this.getValue(in.Children[0]);
				break;
			}
		case Mult:
			{
				result = this.getValue(in.Children[0]) / this.getValue(in.Children[1]);
				break;
			}
		case Pow:
			{
				result = Math.pow(this.getValue(in.Children[0]), this.getValue(in.Children[1]));
				break;
			}
		case Round:
			{
				//result = Math.round(this.getValue(in.Children[0]));
				result = this.getValue(in.Children[0]);
				break;
			}
		case Sub:
			{
				result = this.getValue(in.Children[0]) - this.getValue(in.Children[1]);
				break;
			}
		case Var:
			{
				result = ConstantAutobot.getVariable((String) in.Cargo);
				break;
			}
		case ReadAcc:
			{
				result = this.Acc.getAcc();
				break;
			}
		}

		return result;
	}

	public String getFormula()
	{
		return this.getFormula(Root).toString();
	}

	private StringBuilder getFormula(Node in)
	{
		StringBuilder result = new StringBuilder();

		switch (in.Type)
		{
		case AccAdd:
			{
				result.append("AccAdd(" + this.getFormula(in.Children[0]) + ")");
				break;
			}
		case AccDiv:
			{
				result.append("AccDiv(" + this.getFormula(in.Children[0]) + ")");
				break;
			}
		case AccMult:
			{
				result.append("AccMult(" + this.getFormula(in.Children[0]) + ")");
				break;
			}
		case AccSub:
			{
				result.append("AccSub(" + this.getFormula(in.Children[0]) + ")");
				break;
			}
		case Add:
			{
				result.append("(" + this.getFormula(in.Children[0]) + "+" + this.getFormula(in.Children[1]) + ")");
				break;
			}
		case Ceiling:
			{
				result.append("Ceiling(" + this.getFormula(in.Children[0]) + ")");
				break;
			}
		case Const:
			{
				result.append(in.Cargo.toString());
				break;
			}
		case Div:
			{
				result.append("(" + this.getFormula(in.Children[0]) + "/" + this.getFormula(in.Children[1]) + ")");
				break;
			}
		case Floor:
			{
				result.append("Floor(" + this.getFormula(in.Children[0]) + ")");
				break;
			}
		case Mult:
			{
				result.append("(" + this.getFormula(in.Children[0]) + "*" + this.getFormula(in.Children[1]) + ")");
				break;
			}
		case Pow:
			{
				result.append("(" + this.getFormula(in.Children[0]) + "^" + this.getFormula(in.Children[1]) + ")");
				break;
			}
		case ReadAcc:
			{
				result.append("ReadAcc()");
				break;
			}
		case Round:
			{
				result.append("Round(" + this.getFormula(in.Children[0]) + ")");
				break;
			}
		case Sub:
			{
				result.append("(" + this.getFormula(in.Children[0]) + "-" + this.getFormula(in.Children[1]) + ")");
				break;
			}
		case Var:
			{
				result.append("Var_" + in.Cargo.toString());
				break;
			}
		}

		return result;
	}
	
	public String toFormattedString()
	{
		return this.getFormula() + " = " + this.getValue();
	}
}
