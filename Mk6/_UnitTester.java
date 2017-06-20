package EnemyLevelScaling.GeneticProgrammer.Mk6;

import java.util.HashMap;

import EnemyLevelScaling.GeneticProgrammer.Mk6.Autobots.ConstantAutobot;
import EnemyLevelScaling.GeneticProgrammer.Mk6.Autobots.HelperAutobot;
import Random.MkNeg01.XorShiftStarRng.Static.XorShiftStar1024;

public class _UnitTester
{
	public static void main(String[] args)
	{
		initVars();
		
		testSpasticMonkeySwapping();
	}
	
	public static void testSpasticMonkeySwapping()
	{
		SpasticMonkey test1 = new SpasticMonkey();
		test1.getRun();
		SpasticMonkey test2 = new SpasticMonkey();
		test2.getRun();
		//System.out.println("Originals:\n" + test1.getSkeleton() + "\n" + test2.getSkeleton());
		
		
		SpasticMonkey[] Babies = test1.MakeBabies(test2);
		
		SpasticMonkey[] result = new SpasticMonkey[]{test1, test2, Babies[0], Babies[1]};
		
		System.out.println(HelperAutobot.getStringFromArr(result));
		System.out.println("poop");
	}

	public static int[] testXorShiftStar1024_WeightedRandomPicker_Bulk()
	{
		int[] test = new int[100];
		
		for(int i = 0; i < test.length; i++)
		{
			test[i] = XorShiftStar1024.getIndexGivenWeightedUnnormedOdds(getRandomlyFilledIntArrOfLength(5, 0, 10));
		}
		
		return test;
	}
	
	private static int[] getRandomlyFilledIntArrOfLength(int inLength, int MinBounds, int MaxBounds)
	{
		int[] result = new int[inLength];
		
		for(int i = 0; i < result.length; i++)
		{
			result[i] = XorShiftStar1024.nextInt(MinBounds, MaxBounds);
		}
		
		return result;
	}
	
	public static int testXorShiftStar1024_WeightedRandomPicker()
	{
		int[] test = new int[]{XorShiftStar1024.nextInt(0, 10),
				XorShiftStar1024.nextInt(0, 10),
				XorShiftStar1024.nextInt(0, 10),
				XorShiftStar1024.nextInt(0, 10)};
		
		int result = XorShiftStar1024.getIndexGivenWeightedUnnormedOdds(test);
		
		//System.out.println("Weights: " + HelperAutobot.getStringFromArr(test) + "\n\twith result: " + result);
		
		return result;
	}
	
	/*
	 * Seemed to work fine
	 */
	public static void testBulkSpasticMonkeyGeneration()
	{
		SpasticMonkey[] test = new SpasticMonkey[10];

		for (int i = 0; i < test.length; i++)
		{
			test[i] = new SpasticMonkey(ConstantAutobot.getDesiredTreeSize());
		}

		//System.out.println(HelperAutobot.getStringFromArr(test));

		for (SpasticMonkey CurrMonkey : test)
		{
			printResults(CurrMonkey);
		}
	}

	public static void initVars()
	{
		HashMap<String, Double> inVars = new HashMap<String, Double>();
		inVars.put("TestVar1", 69.0d);
		inVars.put("TestVar2", -69.0d);
		inVars.put("TestVar3", 96.0d);
		inVars.put("TestVar4", -96.0d);
		ConstantAutobot.initVars(inVars);
	}

	public static void printResults(SpasticMonkey in)
	{
		System.out.println(in.getSkeleton());
		System.out.println(in.getMeat());
	}
}
