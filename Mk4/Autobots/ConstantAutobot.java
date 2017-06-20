package EnemyLevelScaling.GeneticProgrammer.Mk4.Autobots;

import java.util.HashMap;

public class ConstantAutobot
{
	private static int ConstMax = 10000;
	private static HashMap<String, Integer> Variables;
	private static String[] VarNames;
	
	public static int getConstMax()
	{
		return ConstMax;
	}
	
	public static void setVariables(HashMap<String, Integer> in)
	{
		Variables = in;
		VarNames = in.keySet().toArray(new String[in.size()]);
	}
	
	public static int getVariable(String Name)
	{
		return Variables.get(Name);
	}
	
	public static String[] getVarNames()
	{
		return VarNames;
	}
}
