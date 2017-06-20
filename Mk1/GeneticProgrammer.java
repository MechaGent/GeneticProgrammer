package EnemyLevelScaling.GeneticProgrammer.Mk1;

import java.util.Arrays;
import java.util.HashMap;

public class GeneticProgrammer
{
	private static HashMap<String, Integer> Inputs;
	
	public static void main(String[] args)
	{
		setInputs();
		int[] dummyExpected = new int[40];
		Arrays.fill(dummyExpected, 0);
		
		ConstantAutobot.setKnownDeltas(dummyExpected);
		
		Curator test = new Curator(1, 1, 10, copyInputsMap());
		
		System.out.println(test.getResults());
	}
	
	private static void setInputs()
	{
		Inputs = new HashMap<String, Integer>();
		Inputs.put("NumPlayers", 1);
		Inputs.put("WaveNum", 11);
	}
	
	private static HashMap<String, Integer> copyInputsMap()
	{
		HashMap<String, Integer> result = new HashMap<String, Integer>();
		result.putAll(Inputs);
		
		return result;
	}
}
