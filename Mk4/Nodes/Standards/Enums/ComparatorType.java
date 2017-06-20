package EnemyLevelScaling.GeneticProgrammer.Mk4.Nodes.Standards.Enums;

import Random.MkNeg01.XorShiftStarRng.Static.XorShiftStar1024;

public enum ComparatorType
{
	LessThanNumNum("<"), GreaterThanNumNum(">"), EqualToNumNum("=="), NotEqualToNumNum("!=");
	
	private String StringForm;
	
	private ComparatorType(String in)
	{
		this.StringForm = in;
	}
	
	public String getStringForm()
	{
		return this.StringForm;
	}
	
	private static ComparatorType[] All = ComparatorType.values();
	
	public static ComparatorType getRandomComparType()
	{
		return All[XorShiftStar1024.nextInt(0, All.length - 1)];
	}
}
