package EnemyLevelScaling.GeneticProgrammer.Mk8.Autobots;

import EnemyLevelScaling.GeneticProgrammer.Mk8.VarContainers.DeVars;
import EnemyLevelScaling.GeneticProgrammer.Mk8.VarContainers.IndeVars;

public class ConstantAutobot
{
	private static int DriverStartValue = 11;
	private static int DriverEndValue = 40;
	private static int NumArrayLength = 11;
	private static int LoopHardLimit = 20;
	private static int NullIntVal = -69;
	private static int PopulationSize = 10;
	private static int DesiredTreeSize = 10;

	private static double MutationChance = 0.1;
	private static double ConstantNumberBounds = 10000;
	
	private static double[] GaussBounds = null;
	
	private static String ArgDelimiter = ", ";
	
	private static IndeVars[] Inde = null;
	private static DeVars[] De = null;
	private static boolean VarsAreSet = false;
	
	public static int getDriverStartValue()
	{
		return DriverStartValue;
	}

	public static int getDriverEndValue()
	{
		return DriverEndValue;
	}

	public static int getNumArrayLength()
	{
		return NumArrayLength;
	}
	
	public static int getLoopHardLimit()
	{
		return LoopHardLimit;
	}
	
	public static int getNullIntVal()
	{
		return NullIntVal;
	}

	public static double getMutationChance()
	{
		return MutationChance;
	}
	
	public static String getArgDelimiter()
	{
		return ArgDelimiter;
	}

	public static double getConstantNumberBounds()
	{
		return ConstantNumberBounds;
	}
	
	public static void initVars(IndeVars[] inInde, DeVars[] inDe)
	{
		Inde = inInde;
		De = inDe;
		VarsAreSet = true;
	}

	public static int getNumVars(int TrialNum)
	{
		if (!VarsAreSet)
		{
			throw new NullPointerException();
		}

		return Inde[TrialNum].getNumVars();
	}

	public static String getVarName(int TrialNum, int index)
	{
		if (!VarsAreSet)
		{
			throw new NullPointerException();
		}

		return Inde[TrialNum].getVarNameAt(index);
	}

	public static int getVarIndex(int TrialNum, String name)
	{
		if (!VarsAreSet)
		{
			throw new NullPointerException();
		}

		return Inde[TrialNum].getVarIndex(name);
	}

	public static double getVarValue(int TrialNum, int index)
	{
		if (!VarsAreSet)
		{
			throw new NullPointerException();
		}

		return Inde[TrialNum].getVarValueAt(index);
	}

	public static int getWaveLevelAt(int TrialNum, int index)
	{
		return De[TrialNum].getLevelAt(index);
	}
	
	public static int[] getWaveLevels(int TrialNum)
	{
		return De[TrialNum].getLevels();
	}
	
	public static int getNumTrials()
	{
		return De.length;
	}
	
	public static int getPopulationSize()
	{
		return PopulationSize;
	}
	
	public static int getDesiredTreeSize()
	{
		return DesiredTreeSize;
	}
	
	public static double getProbabilityOfMovingOnFor(int index)
	{
		if(GaussBounds == null)
		{
			GaussBounds = initGaussBounds();
		}
		
		return GaussBounds[index];
	}
	
	/**
	 * this is constructed such that the maximum probability, at index 0, is about 95%.
	 * Re-adjust by entering, on Wolfram Alpha, "PDF[NormalDistribution[0, x], 0] = DP", where DP = desired probability
	 * @return an array of the probabilities, out of 1, of the element at that index being chosen
	 */
	private static double[] initGaussBounds()
	{
		double[] result = new double[getPopulationSize()];
		
		for(int i = 0; i < result.length; i++)
		{
			result[i] = NeatMath.NeatMath.getNormalProbabilityFor(i, 0, 0.419939);
		}
		
		return result;
	}
}
