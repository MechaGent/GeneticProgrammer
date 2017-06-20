package EnemyLevelScaling.GeneticProgrammer.Mk3;

import Random.MkNeg01.XorShiftStarRng.Static.XorShiftStar1024;

public enum NodeType
{
	Const(0), Var(0), ReadAcc(0), Add(2), AccAdd(1), Sub(2), AccSub(1), Mult(2), AccMult(1), Div(2), AccDiv(1), Pow(2), Floor(1), Ceiling(1), Round(1);
	
	private byte NumKids;
	
	private static NodeType[] All = values();
	private static NodeType[] NonTerminals = new NodeType[] {Add, AccAdd, Sub, AccSub, Mult, AccMult, Div, AccDiv, Pow, Floor, Ceiling, Round};
	private static NodeType[] AccOps = new NodeType[] {AccAdd, AccSub, AccMult, AccDiv};
	private static NodeType[] Ops = new NodeType[] {Add, Sub, Mult, Div, Pow, Floor, Ceiling, Round};
	private static NodeType[] Terminals = new NodeType[] {Const, Var, ReadAcc};
	
	private NodeType(int in)
	{
		this.NumKids = (byte) in;
	}
	
	public byte getNumKids()
	{
		return this.NumKids;
	}
	
	public static NodeType getRandomNodeType()
	{
		return All[XorShiftStar1024.nextInt(0, All.length - 1)];
	}
	
	public static NodeType getRandomNonTerminal()
	{
		return NonTerminals[XorShiftStar1024.nextInt(0, NonTerminals.length - 1)];
	}
	
	public static NodeType getRandomAccOp()
	{
		return AccOps[XorShiftStar1024.nextInt(0, AccOps.length - 1)];
	}
	
	public static NodeType getRandomOp()
	{
		return Ops[XorShiftStar1024.nextInt(0, Ops.length - 1)];
	}
	
	public static NodeType getRandomTerminal()
	{
		return Terminals[XorShiftStar1024.nextInt(0, Terminals.length - 1)];
	}
}
