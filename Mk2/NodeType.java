package EnemyLevelScaling.GeneticProgrammer.Mk2;

import Random.MkNeg01.XorShiftStarRng.Static.XorShiftStar1024;

public enum NodeType
{
	Const, Var, Add, AccAdd, Sub, AccSub, Mult, AccMult, Div, AccDiv, Pow, ;
	
	private static NodeType[] NonTerminals = new NodeType[] {Add, AccAdd, Sub, AccSub, Mult, AccMult, Div, AccDiv, Pow};
	private static NodeType[] Mutable1 = new NodeType[] {AccAdd, AccSub, AccMult, AccDiv};
	private static NodeType[] Mutable2 = new NodeType[] {Add, Sub, Mult, Div, Pow};
	private static NodeType[] Mutable3 = new NodeType[] {Const, Var};
	
	public static NodeType getRandomNonTerminal()
	{
		return NonTerminals[XorShiftStar1024.nextInt(0, NonTerminals.length - 1)];
	}
	
	public static NodeType getRandomMutable1()
	{
		return Mutable1[XorShiftStar1024.nextInt(0, Mutable1.length - 1)];
	}
	
	public static NodeType getRandomMutable2()
	{
		return Mutable2[XorShiftStar1024.nextInt(0, Mutable2.length - 1)];
	}
	
	public static NodeType getRandomMutable3()
	{
		return Mutable3[XorShiftStar1024.nextInt(0, Mutable3.length - 1)];
	}
}
