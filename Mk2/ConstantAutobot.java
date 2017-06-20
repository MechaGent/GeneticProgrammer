package EnemyLevelScaling.GeneticProgrammer.Mk2;

import java.util.HashMap;

public class ConstantAutobot
{
	private static HashMap<String, Integer> Variables;
	private static double MutationChance = 0.05;
	
	public static void setVariables(HashMap<String, Integer> in)
	{
		Variables = in;
	}
	
	public static int getVariable(String name)
	{
		return Variables.get(name);
	}
	
	public static double getMutationChance()
	{
		return MutationChance;
	}
}
