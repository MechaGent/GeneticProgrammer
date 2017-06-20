package EnemyLevelScaling.GeneticProgrammer.Mk1;

import java.util.HashMap;

public class Sperm implements Comparable<Sperm>
{
	private RandoFormula Star;
	private int[] Reps;
	private double FitnessScore;
	
	public Sperm(int RoughNodeCount, HashMap<String, Integer> Inputs, int NumReps)
	{
		this.Star = new RandoFormula(Inputs, RoughNodeCount);
		this.Reps = new int[NumReps];
	}
	
	public void BroDown()
	{
		for(int i = 0; i < this.Reps.length; i++)
		{
			this.Star.setInputValue("WaveNum", i);
			
			this.Reps[i] = this.Star.getResult();
		}
		
		this.FitnessScore = this.getMAE(Reps, ConstantAutobot.getAllKnownDeltas());
	}
	
	private double getMAE(int[] Actual, int[] Expected)
	{
		double result = 0;
		
		for(int i = 0; i < Actual.length; i++)
		{
			result += Math.abs(Actual[i] - Expected[i]);
		}
		
		return result / ((double) Actual.length);
	}

	@Override
	public int compareTo(Sperm arg0)
	{
		double a = FitnessScore;
		double b = arg0.FitnessScore;
		
		if(a == b)
		{
			return 0;
		}
		else if(a < b)
		{
			return -1;
		}
		else
		{
			return 1;
		}
	}
	
	public String toFormattedString()
	{
		return "Formula: " + this.Star.getFormula() + " = " + this.Star.getResult() + "\n\twith Fitness of: " + this.FitnessScore;
	}
}
