package EnemyLevelScaling.GeneticProgrammer.Mk8.Autobots;

import EnemyLevelScaling.GeneticProgrammer.Mk8.Enums.NodeType;
import EnemyLevelScaling.GeneticProgrammer.Mk8.Enums.ReturnType;
import Random.MkNeg01.XorShiftStarRng.Instanced.XorShiftStar1024;

public class RandyAutobot
{
	private static XorShiftStar1024 Randy = new XorShiftStar1024();
	
	public static boolean testForMutation()
	{
		return Randy.flipCoin(ConstantAutobot.getMutationChance());
	}
	
	public static boolean getRandyBool()
	{
		return Randy.flipCoin();
	}
	
	public static boolean getRandyBool(double PosChance)
	{
		return Randy.flipCoin(PosChance);
	}
	
	public static double shakeNumberRandily(double in, double bounds)
	{
		return in + Randy.nextDouble(-bounds, bounds);
	}
	
	public static double getRandyNumber(double bounds)
	{
		return Randy.nextDouble(-bounds, bounds);
	}
	
	public static int getRandyIndex(int ArrLength)
	{
		return Randy.nextInt(0, ArrLength - 1);
	}
	
	public static NodeType getRandyNodeTypeFromReturnType(ReturnType in, boolean limitToTerminals)
	{
		NodeType[] Listy = NodeType.getNodeTypesFromReturnTypes(in, limitToTerminals);
		
		return Listy[getRandyIndex(Listy.length)];
	}
	
	public static ReturnType getRandyReturnTypeFromUnNormedOdds(int[] odds)
	{
		return ReturnType.getReturnTypeAt(Randy.getIndexGivenWeightedUnnormedOdds(odds));
	}
}
