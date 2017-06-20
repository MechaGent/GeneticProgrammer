package EnemyLevelScaling.GeneticProgrammer.Mk2;

public class RandoForm
{
	private class Node
	{
		private Node Parent;
		private int ParentIndex;
		private NodeType Type;
		private Node[] Children;
		private byte Index;

		public Node()
		{
			this.Parent = null;
			this.ParentIndex = 0;
			this.Type = null;
			this.Children = null;
			this.Index = 0;
		}

		public NodeType getType()
		{
			return this.Type;
		}

		public int getValue()
		{
			int result = 0;

			switch (this.Type)
			{
			case AccAdd:
				{
					
					break;
				}
			case AccDiv:
				{
					break;
				}
			case AccMult:
				{
					break;
				}
			case AccSub:
				{
					break;
				}
			case Add:
				{
					break;
				}
			case Const:
				{
					break;
				}
			case Div:
				{
					break;
				}
			case Mult:
				{
					break;
				}
			case Pow:
				{
					break;
				}
			case Sub:
				{
					break;
				}
			case Var:
				{
					break;
				}
			default:
				{
					break;
				}

			}

			return result;
		}
	}

	private class VarNode extends Node
	{
		private String Var;

		@Override
		public int getValue()
		{
			return ConstantAutobot.getVariable(Var);
		}
	}

	private class ConstNode extends Node
	{
		private int Const;

		@Override
		public int getValue()
		{
			return this.Const;
		}
	}

	private Node Root;
	private Accumulator Acc;
	private int Size;

	public int getValue(String IncreVar, int SwapIn)
	{

	}

	private int Op_Add(Node Var1, Node Var2)
	{
		int a;
		int b;
		
		if()
	}
}
