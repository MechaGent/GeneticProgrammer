package EnemyLevelScaling.GeneticProgrammer.Mk3;

import java.util.HashMap;

import Random.MkNeg01.XorShiftStarRng.Static.XorShiftStar1024;

public class ConstantAutobot
{
	private static int ConstInitUpperBounds = 1000000;
	private static int DesiredSize = 10;
	private static HashMap<String, Integer> Variables;
	private static String[] VariableNames;
	
	public static int getConstInitUpperBounds()
	{
		return ConstInitUpperBounds;
	}
	
	public static int getDesiredSize()
	{
		return DesiredSize;
	}
	
	public static void setVariables(HashMap<String, Integer> in)
	{
		Variables = in;
		VariableNames = in.keySet().toArray(new String[in.size()]);
	}
	
	public static int getVariable(String Name)
	{
		return Variables.get(Name);
	}
	
	public static String getRandomVariableName()
	{
		return VariableNames[XorShiftStar1024.nextInt(0, VariableNames.length - 1)];
	}
}
