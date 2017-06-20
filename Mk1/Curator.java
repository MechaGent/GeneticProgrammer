package EnemyLevelScaling.GeneticProgrammer.Mk1;

import java.util.HashMap;
import java.util.LinkedList;

public class Curator
{
	private Sperm[] Pool;
	private final int MaxNumGens;
	private int CurrGen;
	private final int RoughNodeCount;
	private final HashMap<String, Integer> Inputs;
	
	public Curator(int PopSizeDesired, int inMaxNumGens, int inRoughNodeCount, HashMap<String, Integer> inInputs)
	{
		this.Pool = new Sperm[PopSizeDesired];
		this.MaxNumGens = inMaxNumGens;
		this.CurrGen = 0;
		this.RoughNodeCount = inRoughNodeCount;
		this.Inputs = inInputs;
		
		for(int i = 0; i < this.Pool.length; i++)
		{
			this.Pool[i] = new Sperm(inRoughNodeCount, inInputs, ConstantAutobot.getNumReps());
			this.Pool[i].BroDown();
		}
	}
	
	public String getResults()
	{
		StringBuilder result = new StringBuilder();
		
		for(int i = 0; i < this.Pool.length; i++)
		{
			result.append(this.Pool[i].toFormattedString() + "\n");
		}
		
		return result.toString();
	}
}
