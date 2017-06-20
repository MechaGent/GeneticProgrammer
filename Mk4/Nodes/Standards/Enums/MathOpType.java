package EnemyLevelScaling.GeneticProgrammer.Mk4.Nodes.Standards.Enums;

import Random.MkNeg01.XorShiftStarRng.Static.XorShiftStar1024;

public enum MathOpType
{
	Add("+"), Subtract("-"), Multiply("*"), Divide("/"),;
	
	private String StringForm;
	
	private MathOpType(String in)
	{
		this.StringForm = in;
	}
	
	public String getStringForm()
	{
		return this.StringForm;
	}
	
	private static MathOpType[] All = values();
	
	public static MathOpType getRandomOpType()
	{
		return All[XorShiftStar1024.nextInt(0, All.length - 1)];
	}
}
