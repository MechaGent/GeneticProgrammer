package EnemyLevelScaling.GeneticProgrammer.Mk1.Enums;

import Random.MkNeg01.XorShiftStarRng.Static.XorShiftStar1024;

public enum NodeType
{
	Variable(0), Constant(0), Add(2), AccAdd(1), Mult(2), AccMult(1), Power(2), 
	//Function(1)
	;
	
	private static NodeType[] AllNodes = NodeType.values();
	private static NodeType[] OpNodes = new NodeType[]{Add, AccAdd, Mult, AccMult, Power};
	private static NodeType[] TerminalNodes = new NodeType[]{Variable, Constant};
	private int Worth;
	
	private NodeType(int inWorth)
	{
		this.Worth = inWorth;
	}
	
	public int getWorth()
	{
		return this.Worth;
	}
	
	public static NodeType getRandomOpNodeType()
	{
		return OpNodes[XorShiftStar1024.nextInt(0, OpNodes.length - 1)];
	}
	
	public static NodeType getRandomNodeType(int TreeSize)
	{
		if(TreeSize > 0)
		{
			return AllNodes[XorShiftStar1024.nextInt(0, AllNodes.length - 1)];
		}
		else
		{
			return getRandomTerminalNodeType();
		}
	}
	
	public static NodeType getRandomTerminalNodeType()
	{
		return TerminalNodes[XorShiftStar1024.nextInt(0, TerminalNodes.length - 1)];
	}
	
	public static boolean isTerminal(NodeType in)
	{
		return ((in == Variable) || (in == Constant));
	}
}
