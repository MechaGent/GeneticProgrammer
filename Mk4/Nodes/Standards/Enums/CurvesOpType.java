package EnemyLevelScaling.GeneticProgrammer.Mk4.Nodes.Standards.Enums;

import Random.MkNeg01.XorShiftStarRng.Static.XorShiftStar1024;

public enum CurvesOpType
{
	Floor, Ceiling, Round;
	
	private static CurvesOpType[] All = values();
	
	public static CurvesOpType getRandomCurvesType()
	{
		return All[XorShiftStar1024.nextInt(0, All.length - 1)];
	}
}
