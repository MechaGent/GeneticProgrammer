package EnemyLevelScaling.GeneticProgrammer.Mk7;

import java.util.Arrays;

import EnemyLevelScaling.GeneticProgrammer.Mk7.MonkeyBusiness.FitnessComparator;
import EnemyLevelScaling.GeneticProgrammer.Mk7.MonkeyBusiness.SpasticMonkey;

public class Zoo
{	
	private SpasticMonkey[] Cage1;
	private SpasticMonkey[] Cage2;
	private boolean isOnCage1;
	
	private int NumGens;
	private int PopSize;
	private int DesiredTreeSize;
	private boolean ApplyMutateChance;
	private double ErrorAllowed;
	
	public Zoo(int inPopSize, int inDesiredTreeSize, boolean inApplyMutateChance, double inErrorAllowed)
	{
		this.isOnCage1 = true;
		this.NumGens = 0;
		this.PopSize = inPopSize;
		this.DesiredTreeSize = inDesiredTreeSize;
		this.ApplyMutateChance = inApplyMutateChance;
		this.ErrorAllowed = inErrorAllowed;
	}
	
	public SpasticMonkey doNextGen()
	{
		SpasticMonkey[] local;
		//System.out.println("Starting GenLoop");
		
		if(this.isOnCage1)
		{
			if(this.NumGens == 0)
			{
				local = this.getFirstGen();
			}
			else
			{
				local = this.getNextGen(Cage2);
			}
			
			Cage1 = local;
		}
		else
		{
			local = this.getNextGen(Cage1);
			Cage2 = local;
		}
		
		this.NumGens++;
		//System.out.println("Ending GenLoop");
		
		return local[0];
	}
	
	private SpasticMonkey[] getFirstGen()
	{
		SpasticMonkey[] result = new SpasticMonkey[PopSize];
		
		for(int i = 0; i < result.length; i++)
		{
			result[i] = new SpasticMonkey(DesiredTreeSize);
		}
		
		Arrays.parallelSort(result, new FitnessComparator());
		
		this.isOnCage1 = false;
		
		return result;
	}
	
	/**
	 * Assumes previous generation was sorted by fitness
	 */
	private SpasticMonkey[] getNextGen(SpasticMonkey[] PrevGen)
	{
		
		int PopChunk = PrevGen.length / 3;
		
		//Initializes the result array, and 
		SpasticMonkey[] result = new SpasticMonkey[PrevGen.length];
		//System.out.println("Gen subloop starting");
		
		for(int i = 0; i < PopChunk - 1; i+=2)
		{
			//System.out.println("Gen subloop " + i);
			//fills the first third with the top contenders from the previous generation
			result[i] = PrevGen[i];
			result[i+1] = PrevGen[i+1];
			
			//Fills the second third of the cage with the children of the first third
			SpasticMonkey[] temp = SpasticMonkey.cloneAndBone(result[i], result[i + 1], ApplyMutateChance);
			result[i + PopChunk] = temp[0];
			result[i + PopChunk + 1] = temp[1];
			
			//Fills the third third with new SpasticMonkeys
			result[i + (PopChunk * 2)] = new SpasticMonkey(DesiredTreeSize);
			result[i + (PopChunk * 2) + 1] = new SpasticMonkey(DesiredTreeSize);
			
			
		}
		
		Arrays.parallelSort(result, new FitnessComparator());
		
		this.isOnCage1 = !this.isOnCage1;
		
		return result;
	}
	
	public int getNumGens()
	{
		return this.NumGens;
	}
	
	public SpasticMonkey[] getCurrentGen()
	{
		if(isOnCage1)
		{
			return this.Cage2;
		}
		else
		{
			return this.Cage1;
		}
	}
}
