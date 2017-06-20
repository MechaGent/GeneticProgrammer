package EnemyLevelScaling.GeneticProgrammer.Mk5;

import java.util.HashMap;

import EnemyLevelScaling.GeneticProgrammer.Mk5.Enums.BinaryBoolBoolOp;
import EnemyLevelScaling.GeneticProgrammer.Mk5.Enums.BinaryNumBoolOp;
import EnemyLevelScaling.GeneticProgrammer.Mk5.Enums.BinaryNumNumOp;
import EnemyLevelScaling.GeneticProgrammer.Mk5.Enums.NodeType;
import EnemyLevelScaling.GeneticProgrammer.Mk5.Enums.ReturnType;
import EnemyLevelScaling.GeneticProgrammer.Mk5.Enums.UnaryBoolOp;
import EnemyLevelScaling.GeneticProgrammer.Mk5.Enums.UnaryNumOp;
import EnemyLevelScaling.GeneticProgrammer.Mk5.Nodes.Interfaces.Node;
import Random.MkNeg01.XorShiftStarRng.Static.XorShiftStar1024;

public class RandyAutobot
{
	public static Node getRandyNodeFromListOfArbitraryNodes(ChainBelt<Node> in)
	{
		return in.get(XorShiftStar1024.nextInt(0, in.size()));
	}
	
	public static ReturnType getRandyReturnTypeGivenUnNormedAmounts(HashMap<ReturnType, Integer> unNormed)
	{
		double Total = 0;
		ReturnType[] keys = unNormed.keySet().toArray(new ReturnType[unNormed.size()]);

		for (int i = 0; i < keys.length; i++)
		{
			Total += unNormed.get(keys[i]);
		}

		double[] Odds = new double[keys.length];

		for (int i = 0; i < Odds.length; i++)
		{
			Odds[i] = ((double) unNormed.get(keys[i])) / Total;
		}

		int index = XorShiftStar1024.getIndexGivenWeightedOdds(Odds);

		if (index == -1)
		{
			return null;
		}
		else
		{
			return keys[index];
		}
	}
	
	public static ReturnType getRandyReturnTypeGivenUnNormedAmounts(ReturnType[] inRet, int[] inOdds)
	{
		double Total = 0;
		
		for (int i = 0; i < inOdds.length; i++)
		{
			Total += inOdds[i];
		}
		
		double[] Odds = new double[inOdds.length];

		for (int i = 0; i < Odds.length; i++)
		{
			Odds[i] = ((double) inOdds[i]) / Total;
		}

		int index = XorShiftStar1024.getIndexGivenWeightedOdds(Odds);

		if (index == -1)
		{
			return null;
		}
		else
		{
			return inRet[index];
		}
	}

	public static NodeType getRandomNodeTypeOfTypes(ChainBelt<NodeType[]> in)
	{
		NodeType result;
		int Total = 0;
		
		for(NodeType[] Arr: in)
		{
			Total += Arr.length;
		}

		int RelIndex = XorShiftStar1024.nextInt(0, Total - 1);
		boolean FoundFlag = false;
		NodeType[] CurrArr = null;

		while (!in.isEmpty() && !FoundFlag)
		{
			CurrArr = in.pop();
			System.out.println("\tCurrArr: " + HelperAutobot.getStringFromArrayOfNodeTypes(CurrArr));
			
			if (RelIndex - CurrArr.length < 0)
			{
				FoundFlag = true;
			}
			else
			{
				RelIndex = RelIndex - CurrArr.length;
			}
		}
		
		result = CurrArr[RelIndex];

		//System.out.println(result + " " + RelIndex);
		return result;
	}

	public static NodeType getRandomNodeTypeOfTypes(ReturnType[] in)
	{
		ChainBelt<NodeType[]> HotList = new ChainBelt<NodeType[]>();

		for (int i = 0; i < in.length; i++)
		{
			HotList.add(ConstantAutobot.getReturnersOfType(in[i], false));
		}

		return getRandomNodeTypeOfTypes(HotList);
	}

	public static NodeType getRandomNodeTypeOfTypes(ReturnType in)
	{
		NodeType[] CurrArr = ConstantAutobot.getReturnersOfType(in, false);

		return CurrArr[getRandyInt(0, CurrArr.length - 1)];
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

	public static boolean flipCoin(double chancePos)
	{
		return XorShiftStar1024.flipCoin(chancePos);
	}

	public static boolean flipCoin()
	{
		return XorShiftStar1024.flipCoin();
	}

	public static int getRandyInt(int min, int max)
	{
		return XorShiftStar1024.nextInt(min, max);
	}

	public static double getRandyDub(int min, int max)
	{
		return XorShiftStar1024.nextDouble(min, max);
	}
}
