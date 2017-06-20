package EnemyLevelScaling.GeneticProgrammer.Mk4.Autobots;

import Random.MkNeg01.XorShiftStarRng.Static.XorShiftStar1024;

public class RandyAutobot
{
	public static double getRandomDouble(double min, double max)
	{
		return XorShiftStar1024.nextDouble(min, max);
	}
	
	public static double getRandomDubConst()
	{
		return XorShiftStar1024.nextDouble(-ConstantAutobot.getConstMax(), ConstantAutobot.getConstMax());
	}
	
	public static int getRandomIntConst()
	{
		return XorShiftStar1024.nextInt(-ConstantAutobot.getConstMax(), ConstantAutobot.getConstMax());
	}
	
	public static String getRandomLookUpVarName()
	{
		String[] Names = ConstantAutobot.getVarNames();
		return Names[XorShiftStar1024.nextInt(0, Names.length - 1)];
	}
	
	public static boolean flipCoin()
	{
		return XorShiftStar1024.flipCoin();
	}
}
