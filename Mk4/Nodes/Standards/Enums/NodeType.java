package EnemyLevelScaling.GeneticProgrammer.Mk4.Nodes.Standards.Enums;

import java.util.LinkedList;

import Random.MkNeg01.XorShiftStarRng.Static.XorShiftStar1024;

public enum NodeType
{

	While((byte) 3, ReturnType.Void), // Children[0],Children[1]: compared for control statement, Children[2]: function node
	DubConst((byte) 0, ReturnType.Number), // double-based constant
	IntConst((byte) 0, ReturnType.Number), // integer-based constant
	LookUpVar((byte) 0, ReturnType.Number),
	ReadIntAcc((byte) 0, ReturnType.Number),
	ReadDubAcc((byte) 0, ReturnType.Number),
	AccOp((byte) 1, ReturnType.Void),
	Op((byte) 2, ReturnType.Number),
	IfElse((byte) 4, ReturnType.Void), // Children[0],Children[1]: compared for control statement, Children[2], Children[3]: "if"/"else" function nodes
	Curves((byte) 1, ReturnType.Number), // Handles Floor, Ceiling, and Round operations
	;

	private byte NumKids;
	private ReturnType Returns;

	private static NodeType[] All;
	private static NodeType[] Terminals;
	private static NodeType[] NonTerminals;
	private static NodeType[] NumReturners;
	private static NodeType[] VoidReturners;

	private NodeType(byte inNumKids, ReturnType inReturns)
	{
		this.NumKids = inNumKids;
		this.Returns = inReturns;
	}

	public byte getNumKids()
	{
		return this.NumKids;
	}

	public ReturnType getReturnType()
	{
		return this.Returns;
	}

	public boolean isTerminal()
	{
		return this.NumKids == 0;
	}

	public static void initialize()
	{
		All = values();

		LinkedList<NodeType> tempTerminals = new LinkedList<NodeType>();
		LinkedList<NodeType> tempNonTerminals = new LinkedList<NodeType>();
		LinkedList<NodeType> tempNumReturners = new LinkedList<NodeType>();
		LinkedList<NodeType> tempVoidReturners = new LinkedList<NodeType>();

		for (int i = 0; i < All.length; i++)
		{
			NodeType CurrType = All[i];

			if (CurrType.NumKids == 0)
			{
				tempTerminals.add(CurrType);
			}
			else
			{
				tempNonTerminals.add(CurrType);
			}

			if (CurrType.Returns == ReturnType.Number)
			{
				tempNumReturners.add(CurrType);
			}
			else if (CurrType.Returns == ReturnType.Number)
			{
				tempVoidReturners.add(CurrType);
			}
		}

		Terminals = tempTerminals.toArray(new NodeType[tempTerminals.size()]);
		NonTerminals = tempNonTerminals.toArray(new NodeType[tempNonTerminals.size()]);
		NumReturners = tempNumReturners.toArray(new NodeType[tempNumReturners.size()]);
		VoidReturners = tempVoidReturners.toArray(new NodeType[tempVoidReturners.size()]);
	}

	private static NodeType getRandFromSet(NodeType[] in)
	{
		return in[XorShiftStar1024.nextInt(0, in.length - 1)];
	}

	public static NodeType getRandomNode()
	{
		return getRandFromSet(All);
	}

	public static NodeType getRandomTerminal()
	{
		return getRandFromSet(Terminals);
	}

	public static NodeType getRandomNonTerminal()
	{
		return getRandFromSet(NonTerminals);
	}

	public static NodeType getRandomNumberReturner()
	{
		return getRandFromSet(NumReturners);
	}

	public static NodeType getRandomVoidReturner()
	{
		return getRandFromSet(VoidReturners);
	}
}
