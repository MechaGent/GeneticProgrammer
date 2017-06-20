package EnemyLevelScaling.GeneticProgrammer.Mk6.Autobots;

import java.util.Iterator;

import DataStructures.Linkages.SingleLinkedList.Unsorted.Mk01.SingleLinkedList;
import EnemyLevelScaling.GeneticProgrammer.Mk6.Enums.BinaryBoolBoolOp;
import EnemyLevelScaling.GeneticProgrammer.Mk6.Enums.BinaryNumBoolOp;
import EnemyLevelScaling.GeneticProgrammer.Mk6.Enums.BinaryNumNumOp;
import EnemyLevelScaling.GeneticProgrammer.Mk6.Enums.NodeType;
import EnemyLevelScaling.GeneticProgrammer.Mk6.Enums.ReturnType;
import EnemyLevelScaling.GeneticProgrammer.Mk6.Enums.UnaryBoolOp;
import EnemyLevelScaling.GeneticProgrammer.Mk6.Enums.UnaryNumOp;
import EnemyLevelScaling.GeneticProgrammer.Mk6.Nodes.Interfaces.Node;
import Random.MkNeg01.XorShiftStarRng.Static.XorShiftStar1024;

public class RandyAutobot
{
	public static boolean flipCoin()
	{
		return XorShiftStar1024.flipCoin();
	}

	public static boolean flipCoin(double chancePos)
	{
		return XorShiftStar1024.flipCoin(chancePos);
	}

	public static int getRandyInt(int min, int max)
	{
		return XorShiftStar1024.nextInt(min, max);
	}

	public static double getRandyDub(int min, int max)
	{
		return XorShiftStar1024.nextDouble(min, max);
	}

	public static BinaryNumNumOp getRandyBinaryNumNumOp()
	{
		return BinaryNumNumOp.getEnumAtIndex(getRandyInt(0, BinaryNumNumOp.getNumOps() - 1));
	}

	public static BinaryBoolBoolOp getRandyBinaryBoolBoolOp()
	{
		return BinaryBoolBoolOp.getEnumAtIndex(getRandyInt(0, BinaryBoolBoolOp.getNumOps() - 1));
	}

	public static BinaryNumBoolOp getRandyBinaryNumBoolOp()
	{
		return BinaryNumBoolOp.getEnumAtIndex(getRandyInt(0, BinaryNumBoolOp.getNumOps() - 1));
	}

	public static UnaryBoolOp getRandyUnaryBoolOp()
	{
		return UnaryBoolOp.getEnumAtIndex(getRandyInt(0, UnaryBoolOp.getNumOps() - 1));
	}

	public static UnaryNumOp getRandyUnaryNumOp()
	{
		return UnaryNumOp.getEnumAtIndex(getRandyInt(0, UnaryNumOp.getNumOps() - 1));
	}

	public static NodeType getRandomNodeTypeOfTypes(ReturnType in, boolean wantsTerminals)
	{
		NodeType[] result = NodeType.getNodeTypesFromReturnType(in, wantsTerminals);

		return result[getRandyInt(0, result.length - 1)];
	}

	public static ReturnType getWeightedRandomReturnType(ReturnType[] Listy, int[] Weights)
	{
		int result = XorShiftStar1024.getIndexGivenWeightedUnnormedOdds(Weights);
		
		//System.out.println("Weighted Odds: " + HelperAutobot.getStringFromArr(Weights) + "\nwith resulting index of: " + result);

		if (result != -1)
		{
			return Listy[result];
		}
		else
		{
			return null;
		}
	}

	public static Node getRandyNodeFromList(SingleLinkedList<Node> in)
	{
		Iterator<Node> chug = in.iterator();
		int Roller = XorShiftStar1024.nextInt(0, in.size());
		int place = 1;
		Node result = null;
		System.out.println(place + " out of " + Roller);

		while (chug.hasNext() && place < Roller)
		{
			System.out.println(place + " out of " + Roller);
			result = chug.next();
			place++;
		}

		return result;
	}
}
