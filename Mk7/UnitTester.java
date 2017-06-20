package EnemyLevelScaling.GeneticProgrammer.Mk7;

import EnemyLevelScaling.GeneticProgrammer.Mk7.Autobots.ConstantAutobot;
import EnemyLevelScaling.GeneticProgrammer.Mk7.Autobots.HelperAutobot;
import EnemyLevelScaling.GeneticProgrammer.Mk7.Enums.ReturnType;
import EnemyLevelScaling.GeneticProgrammer.Mk7.MonkeyBusiness.SpasticMonkey;
import EnemyLevelScaling.GeneticProgrammer.Mk7.Nodes.AbstractClasses.BaseNode;
import Random.MkNeg01.XorShiftStarRng.Static.XorShiftStar1024;

public class UnitTester
{
	static String[] dummyVarNames = new String[] {
											"NumPlayers",
											"EnemyLevel_Min",
											"EnemyLevel_Max"
											};
	static double[] dummyVarValues = new double[] {
												1,
												11,
												16 
												};
	static int[] Levels = new int[] {
								11,
								12,
								12,
								13,
								13,
								14,
								14,
								15,
								15,
								16,
								17,
								18,
								19,
								20,
								21,
								22,
								23,
								24,
								25,
								27,
								28,
								29,
								31,
								32,
								33,
								35,
								36,
								38,
								39,
								41,
								43,
								44,
								46,
								48,
								50,
								52,
								53,
								56,
								58,
								60 };
	
	public static void main(String[] args)
	{
		
		System.out.println("Levels: " + Levels.length);
		ControlData Data = new ControlData(dummyVarNames, dummyVarValues, Levels);
		ConstantAutobot.initVars(Data);

		//HeavyDuty();
		
		//String result = logTester();
		//String result = testRunZooSeveralTimes();
		//String result = testIndexedNodeGetterOnce();

		System.out.println(convertTreeOutType("IntSetter[0, 5, 1, 1](ConstDub[0, -1, 0, 0](-4759.263651156795),BinaryNumNum[0, 4, 1, 0](Mult,IntGetter[0, 1, 0, 0](ConstDub[0, 0, 0, 0](-4759.263651156795)),BinaryNumNum[0, 2, 0, 0](Div,VarGetter[0, 0, 0, 0](\"EnemyLevel_Max\"),VarGetter[0, 0, 0, 0](\"EnemyLevel_Min\"))),ConstVoid[0, 0, 0, 0]())"));
	}

	private static void HeavyDuty()
	{
		int NumGensDesired = 60000;
		int PopSize = 600;
		int DesiredSize = 7;
		double ErrorAllowed = 0;
		int LoopInterval = 300;
		int nextSigLoop = 0;

		Zoo test = new Zoo(PopSize, DesiredSize, true, ErrorAllowed);
		
		for (int i = 0; i < NumGensDesired; i++)
		{
			SpasticMonkey TheBestAround = test.doNextGen();
			SpasticMonkey[] All = test.getCurrentGen();

			if (i == nextSigLoop)
			{
				nextSigLoop += LoopInterval;
				StringBuilder toFile = new StringBuilder();
				toFile.append("Generation " + i);
				
				for(int j = 0; j < All.length; j++)
				{
					toFile.append("\n" + All[j].getFitness() + " " + All[j].toStringBuilder(true));
				}
				
				ConstantAutobot.writeToLoggins(toFile.toString());
				System.out.println("On Generation: " + i 
						+ "\n\tCurrent best:\n\t\t" + TheBestAround.toStringBuilder(true).toString()
						+ "\n\t\twith fitness of: " + TheBestAround.getFitness()
						+ "\n\t\tsize of: " + TheBestAround.getSize()
						+ "\n\t\tand run of:\t" + HelperAutobot.getStringFromArr(TheBestAround.getRun())
						+ "\n\t\tcompared to:\t" + HelperAutobot.getStringFromArr(Levels, ConstantAutobot.getDriverStartValue()));
			}
		}
	}
	
	private static String logTester()
	{
		int NumGensDesired = 6000;
		int PopSize = 600;
		int DesiredSize = 20;
		double ErrorAllowed = 0;
		int LoopInterval = 100;
		int nextSigLoop = 0;

		Zoo test = new Zoo(PopSize, DesiredSize, true, ErrorAllowed);
		StringBuilder result = new StringBuilder();

		for (int i = 0; i < NumGensDesired; i++)
		{
			SpasticMonkey TheBestAround = test.doNextGen();

			String CurrString = "\nGeneration: " + i + "\n\tTree: " + TheBestAround.toString() + "\n\tDiagTree: " + TheBestAround.toStringBuilder(true) + "\n\tSize: " + TheBestAround.getSize() + "\n\tRun: " + HelperAutobot.getStringFromArr(TheBestAround.getRun()) + "\n\tFitness: " + TheBestAround.getFitness();

			result.append(CurrString);

			if (i == nextSigLoop)
			{
				nextSigLoop += LoopInterval;
				ConstantAutobot.writeToLoggins(CurrString);
				System.out.println("On Generation: " + i);
			}
		}
		
		SpasticMonkey[] Cage = test.getCurrentGen();

		result.append("\nFirstFitness: " + Cage[0].getFitness() + "\nLastFitness: " + Cage[Cage.length - 1].getFitness());

		return result.toString();
	}
	
	private static String convertTreeOutType(String in)
	{
		BaseNode labGrown = SpasticMonkey.parseNodeFromString(new ParsePrepString(in), new GlobalVarsPool());
		return labGrown.toStringBuilder().toString();
	}

	private static String testParser()
	{
		int DesiredSize = 10;
		int NumMonkeys = 10;
		SpasticMonkey[] testMonkeys = new SpasticMonkey[NumMonkeys];
		String[] runResults = new String[testMonkeys.length];
		String[] parseResults = new String[testMonkeys.length];

		for (int i = 0; i < testMonkeys.length; i++)
		{
			testMonkeys[i] = new SpasticMonkey(DesiredSize);
			runResults[i] = testMonkeys[i].toStringBuilder(true).toString();
			//System.out.println(runResults[i]);
			BaseNode labGrown = SpasticMonkey.parseNodeFromString(new ParsePrepString(runResults[i]), new GlobalVarsPool());
			parseResults[i] = labGrown.toStringBuilder(true).toString();
		}

		StringBuilder result = new StringBuilder();
		for (int i = 0; i < runResults.length; i++)
		{
			//result.append(runResults[i] + "\n" + parseResults[i]);
			result.append("\n" + runResults[i].equals(parseResults[i]));
		}

		return result.toString();
	}

	private static String testIndexedNodeGetterOnce()
	{
		System.out.println("Starting");
		int DesiredSize = 20;
		StringBuilder result = new StringBuilder();

		SpasticMonkey test = new SpasticMonkey(DesiredSize);
		result.append("Full String:\n\t" + test.toStringBuilder(true));
		//System.out.println(test.toString());

		int[] GrandSubtotals = test.getSubSizes();
		int BoolSubtotal = GrandSubtotals[ReturnType.Boolean.getIndex()];
		int DubSubtotal = GrandSubtotals[ReturnType.Double.getIndex()];
		int IntSubtotal = GrandSubtotals[ReturnType.Integer.getIndex()];
		int VoidSubtotal = GrandSubtotals[ReturnType.Void.getIndex()];

		if (BoolSubtotal != 0)
		{
			int BoolNum = XorShiftStar1024.nextInt(0, BoolSubtotal - 1) + 1;
			BaseNode testBool = test.getIndexedNodeOfReturnType(BoolNum, ReturnType.Boolean.getIndex());
			result.append("\nChosen Bool:\n\tIndex: " + BoolNum + "\n\t" + testBool.toStringBuilder(true));
		}

		if (DubSubtotal != 0)
		{
			int DubNum = XorShiftStar1024.nextInt(1, DubSubtotal - 1) + 1;
			BaseNode testDub = test.getIndexedNodeOfReturnType(DubNum, ReturnType.Double.getIndex());
			result.append("\nChosen Dub:\n\tIndex: " + DubNum + "\n\t" + testDub.toStringBuilder(true));
		}

		if (IntSubtotal != 0)
		{
			int IntNum = XorShiftStar1024.nextInt(1, IntSubtotal - 1) + 1;
			BaseNode testInt = test.getIndexedNodeOfReturnType(IntNum, ReturnType.Integer.getIndex());
			result.append("\nChosen Int:\n\tIndex: " + IntNum + "\n\t" + testInt.toStringBuilder(true));
		}

		if (VoidSubtotal != 0)
		{
			int VoidNum = XorShiftStar1024.nextInt(1, VoidSubtotal - 1) + 1;
			BaseNode testVoid = test.getIndexedNodeOfReturnType(VoidNum, ReturnType.Void.getIndex());
			result.append("\nChosen Void:\n\tIndex: " + VoidNum + "\n\t" + testVoid.toStringBuilder(true));
		}

		return result.toString();
	}

	private static String generateTreeStrings()
	{
		int DesiredSize = 10;
		SpasticMonkey[] testMonkeys = new SpasticMonkey[100];
		String[] results = new String[testMonkeys.length];

		for (int i = 0; i < testMonkeys.length; i++)
		{
			testMonkeys[i] = new SpasticMonkey(DesiredSize);
			results[i] = testMonkeys[i].toStringBuilder(true).toString();
		}

		StringBuilder result = new StringBuilder();
		for (int i = 0; i < results.length; i++)
		{
			result.append(results[i] + "\n");
		}

		return result.toString();
	}

	private static String testRunZooSeveralTimes()
	{
		int NumGensDesired = 600;
		int PopSize = 600;
		int DesiredSize = 20;
		double ErrorAllowed = 0;
		int LoopInterval = 30;
		int nextSigLoop = 0;

		Zoo test = new Zoo(PopSize, DesiredSize, false, ErrorAllowed);
		StringBuilder result = new StringBuilder();

		for (int i = 0; i < NumGensDesired; i++)
		{
			SpasticMonkey TheBestAround = test.doNextGen();

			String CurrString = "\nGeneration: " + i + "\n\tTree: " + TheBestAround.toString() + "\n\tDiagTree: " + TheBestAround.toStringBuilder(true) + "\n\tSize: " + TheBestAround.getSize() + "\n\tRun: " + HelperAutobot.getStringFromArr(TheBestAround.getRun()) + "\n\tFitness: " + TheBestAround.getFitness();

			result.append(CurrString);

			if (i == nextSigLoop)
			{
				nextSigLoop += LoopInterval;
				System.out.println(CurrString);
			}
		}

		SpasticMonkey[] Cage = test.getCurrentGen();

		result.append("\nFirstFitness: " + Cage[0].getFitness() + "\nLastFitness: " + Cage[Cage.length - 1].getFitness());

		return result.toString();
	}

	private static void testRunZooOnce()
	{
		int PopSize = 600;
		int DesiredSize = 10;
		double ErrorAllowed = 0;

		Zoo test = new Zoo(PopSize, DesiredSize, true, ErrorAllowed);

		SpasticMonkey TheBestAround = test.doNextGen();

		System.out.println("Tree:\n\t" + TheBestAround.toString() + "\nRun:\n\t" + HelperAutobot.getStringFromArr(TheBestAround.getRun()) + "\nFitness:\n\t" + TheBestAround.getFitness());
	}

	private static void testRunManyMonkeysWithFitness()
	{
		int DesiredSize = 10;
		SpasticMonkey[] testMonkeys = new SpasticMonkey[100];
		String[] results = new String[testMonkeys.length];

		for (int i = 0; i < testMonkeys.length; i++)
		{
			testMonkeys[i] = new SpasticMonkey(DesiredSize);
			results[i] = "Tree:\n\t" + testMonkeys[i].toString() + "\nRun:\n\t" + HelperAutobot.getStringFromArr(testMonkeys[i].getRun()) + "\nFitness:\n\t" + testMonkeys[i].getFitness();
		}

		StringBuilder result = new StringBuilder();
		for (int i = 0; i < results.length; i++)
		{
			result.append(results[i] + "\n");
		}

		System.out.println(result.toString());
	}

	private static void testRunSingleMonkeyWithFitness()
	{
		int DesiredSize = 10;
		SpasticMonkey test = new SpasticMonkey(DesiredSize);

		int[] result = test.getRun();

		System.out.println("Tree:\n\t" + test.toString() + "\nRun:\n\t" + HelperAutobot.getStringFromArr(result) + "\nFitness:\n\t" + test.getFitness());
	}

	private static void testRunManyMonkeys()
	{
		int DesiredSize = 10;
		SpasticMonkey[] testMonkeys = new SpasticMonkey[100];
		String[] results = new String[testMonkeys.length];

		for (int i = 0; i < testMonkeys.length; i++)
		{
			testMonkeys[i] = new SpasticMonkey(DesiredSize);
			results[i] = "Tree:\n\t" + testMonkeys[i].toString() + "\nRun:\n\t" + HelperAutobot.getStringFromArr(testMonkeys[i].getRun());
		}

		StringBuilder result = new StringBuilder();
		for (int i = 0; i < results.length; i++)
		{
			result.append(results[i] + "\n");
		}

		System.out.println(result.toString());
	}

	private static void testRunSingleMonkey()
	{
		int DesiredSize = 10;
		SpasticMonkey test = new SpasticMonkey(DesiredSize);

		int[] result = test.getRun();

		System.out.println("Tree:\n\t" + test.toString() + "\nRun:\n\t" + HelperAutobot.getStringFromArr(result));
	}

	private static String testCloneAndBone()
	{
		int DesiredSize = 10;
		SpasticMonkey[] Parents = new SpasticMonkey[] {
														new SpasticMonkey(DesiredSize),
														new SpasticMonkey(DesiredSize) };
		SpasticMonkey[] Kids = SpasticMonkey.cloneAndBone(Parents[0], Parents[1], false);

		return "Originals:\n\t" + Parents[0].toStringBuilder().toString() + "\n\t" + Parents[1].toStringBuilder().toString() + "\nKids:\n\t" + Kids[0].toStringBuilder().toString() + "\n\t" + Kids[1].toStringBuilder().toString();
	}

	private static void testManyMonkeys()
	{
		int DesiredSize = 10;
		SpasticMonkey[] testMonkeys = new SpasticMonkey[100];
		String[] results = new String[testMonkeys.length];

		for (int i = 0; i < testMonkeys.length; i++)
		{
			testMonkeys[i] = new SpasticMonkey(DesiredSize);
			results[i] = testMonkeys[i].toStringBuilder().toString();
		}

		StringBuilder result = new StringBuilder();
		for (int i = 0; i < results.length; i++)
		{
			result.append(results[i] + "\n");
		}

		System.out.println(result.toString());
	}

	private static void testSingleMonkey()
	{
		SpasticMonkey testMonkey = new SpasticMonkey(10);

		StringBuilder result = testMonkey.toStringBuilder();

		System.out.println(result.toString());
	}
}
