package EnemyLevelScaling.GeneticProgrammer.Mk7.Autobots;

import EnemyLevelScaling.GeneticProgrammer.Mk7.Enums.NodeType;
import EnemyLevelScaling.GeneticProgrammer.Mk7.Enums.ReturnType;
import EnemyLevelScaling.GeneticProgrammer.Mk7.Enums.OpTypes.BinaryBoolBoolOp;
import EnemyLevelScaling.GeneticProgrammer.Mk7.Enums.OpTypes.BinaryNumBoolOp;
import EnemyLevelScaling.GeneticProgrammer.Mk7.Enums.OpTypes.BinaryNumNumOp;
import EnemyLevelScaling.GeneticProgrammer.Mk7.Enums.OpTypes.UnaryBoolOp;
import EnemyLevelScaling.GeneticProgrammer.Mk7.Enums.OpTypes.UnaryNumOp;
import Random.MkNeg01.XorShiftStarRng.Static.XorShiftStar1024;

public class RandyAutobot
{
	public static NodeType getRandyNodeTypeFromLegalReturnTypes(ReturnType[] in, boolean wantsTerminals)
	{
		NodeType[] Listy = NodeType.getNodeTypesFromReturnTypes(in, wantsTerminals);
		
		return Listy[XorShiftStar1024.nextInt(0, Listy.length - 1)];
	}
	
	public static NodeType getRandyNodeTypeFromLegalReturnTypes(ReturnType in, boolean wantsTerminals)
	{
		NodeType[] Listy = NodeType.getNodeTypesFromReturnTypes(in, wantsTerminals);
		
		return Listy[XorShiftStar1024.nextInt(0, Listy.length - 1)];
	}
	
	public static int getRandyInt(int min, int max)
	{
		return XorShiftStar1024.nextInt(min, max);
	}
	
	public static double getRandyDub(double min, double max)
	{
		return XorShiftStar1024.nextDouble(min, max);
	}
	
	public static BinaryBoolBoolOp getRandyBinaryBoolBoolOp()
	{
		return BinaryBoolBoolOp.getEnumAtIndex(getRandyInt(0, BinaryBoolBoolOp.getNumOps() - 1));
	}
	
	public static BinaryNumBoolOp getRandyBinaryNumBoolOp()
	{
		return BinaryNumBoolOp.getEnumAtIndex(getRandyInt(0, BinaryNumBoolOp.getNumOps() - 1));
	}
	
	public static BinaryNumNumOp getRandyBinaryNumNumOp()
	{
		return BinaryNumNumOp.getEnumAtIndex(getRandyInt(0, BinaryNumNumOp.getNumOps() - 1));
	}
	
	public static UnaryBoolOp getRandyUnaryBoolOp()
	{
		return UnaryBoolOp.getEnumAtIndex(getRandyInt(0, UnaryBoolOp.getNumOps() - 1));
	}
	
	public static UnaryNumOp getRandyUnaryNumOp()
	{
		return UnaryNumOp.getEnumAtIndex(getRandyInt(0, UnaryNumOp.getNumOps() - 1));
	}
	
	public static boolean flipCoin()
	{
		return XorShiftStar1024.flipCoin();
	}

	public static boolean flipCoin(double chancePos)
	{
		return XorShiftStar1024.flipCoin(chancePos);
	}
	
	public static int getIndexGivenWeightedUnnormedOdds(int[] Odds)
	{
		int result = XorShiftStar1024.getIndexGivenWeightedUnnormedOdds(Odds);
		
		//System.out.println("Given: " + HelperAutobot.getStringFromArr(Odds) + " as odds, chose: " + result);
		
		return result;
	}
}
