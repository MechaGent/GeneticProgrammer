package EnemyLevelScaling.GeneticProgrammer.Mk6.Autobots;

import java.util.HashMap;

import EnemyLevelScaling.GeneticProgrammer.Mk6.Enums.NodeType;

public class ConstantAutobot
{
	private static int DriverStartValue = 1;
	private static int DriverEndValue = 45;
	private static int IntArrayLength = 10;
	private static int DubArrayLength = 10;
	private static int LoopLimiter = 100000;
	private static int ConstDeltaMax = 3;
	private static int DesiredTreeSize = 30;
	private static int ConstBounds = 10000;
	
	private static double MutationChance = 0.05;
	
	//private static char Delimiter = ';';
	private static char ArgDelimiter = ',';
	
	private static HashMap<String, Double> Vars;
	private static String[] VarNames;
	
	private static NodeType[] SimpleNodes = new NodeType[]{NodeType.ConstBool, NodeType.ConstDub, NodeType.ConstInt, NodeType.ConstVoid};
	
	/**
	 * Do not forget to do this
	 */
	public static void initVars(HashMap<String, Double> in)
	{
		Vars = in;
		VarNames = in.keySet().toArray(new String[in.size()]);
	}
	
	public static String getVarName(int index)
	{
		return VarNames[index];
	}

	public static String[] getVarNames()
	{
		return VarNames;
	}

	public static int getNumVars()
	{
		return VarNames.length;
	}
	
	public static double getVarValue(String Name)
	{
		return Vars.get(Name);
	}
	
	public static double getVarValue(int index)
	{
		return Vars.get(VarNames[index]);
	}
	
	public static int getIntArrayLength()
	{
		return IntArrayLength;
	}

	public static double getDubArrayLength()
	{
		return DubArrayLength;
	}

	public static int[] getNewTestIntArr()
	{
		return new int[IntArrayLength];
	}

	public static double[] getNewTestDubArr()
	{
		return new double[DubArrayLength];
	}
	
	public static int getDriverStartValue()
	{
		return DriverStartValue;
	}
	
	public static int getDriverEndValue()
	{
		return DriverEndValue;
	}
	
	public static double getMutationChance()
	{
		return MutationChance;
	}
	
	public static int getConstDeltaMax()
	{
		return ConstDeltaMax;
	}

	public static int getDesiredTreeSize()
	{
		return DesiredTreeSize;
	}

	public static int getConstBounds()
	{
		return ConstBounds;
	}
	
	public static int getLoopLimiter()
	{
		return LoopLimiter;
	}
	
	public static char getArgDelimiter()
	{
		return ArgDelimiter;
	}
	
	public static NodeType[] getSimpleNodes()
	{
		return SimpleNodes;
	}
}
