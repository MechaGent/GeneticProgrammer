package EnemyLevelScaling.GeneticProgrammer.Mk3;

import java.util.HashMap;

import Random.MkNeg01.XorShiftStarRng.Static.XorShiftStar1024;

public class GeneticProgrammer
{
	public static void main(String[] args)
	{
		/*
		ConstantAutobot.setVariables(setInputs());
		
		SwapTree[] Forest = new SwapTree[10];
		
		for(int i = 0; i< Forest.length; i++)
		{
			Forest[i] = new SwapTree(ConstantAutobot.getDesiredSize());
			System.out.println(Forest[i].toFormattedString());;
		}
		*/
		
		StringBuilder test = new StringBuilder();
		test.append(getRandInt());
		
		for(int i = 0; i < 10; i++)
		{
			test.append("\n" + getRandInt());
		}
		
		System.out.println(test.toString());
	}
	
	private static HashMap<String, Integer> setInputs()
	{
		HashMap<String, Integer> result = new HashMap<String, Integer>();
		
		result.put("NumPlayers", 1);
		
		return result;
	}
	
	private static int getRandInt()
	{
		return XorShiftStar1024.nextInt(0, 100);
	}
}
