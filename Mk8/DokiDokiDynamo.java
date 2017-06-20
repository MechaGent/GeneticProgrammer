package EnemyLevelScaling.GeneticProgrammer.Mk8;

import java.util.Arrays;

import DataStructures.Linkages.SingleLinkedList.Unsorted.Mk01.SingleLinkedList;
import EnemyLevelScaling.GeneticProgrammer.Mk8.Autobots.ConstantAutobot;
import EnemyLevelScaling.GeneticProgrammer.Mk8.Autobots.RandyAutobot;
import EnemyLevelScaling.GeneticProgrammer.Mk8.Autobots.WeeblAutobot;

public class DokiDokiDynamo
{
	private SpasticMonkey[] Zoo;
	
	public DokiDokiDynamo(int ZooSize, int DesiredSize)
	{
		this.Zoo = new SpasticMonkey[ZooSize];
		
		for(int i = 0; i < this.Zoo.length; i++)
		{
			this.Zoo[i] = WeeblAutobot.requestNewSpasticMonkey(DesiredSize);
		}
	}
	
	public SpasticMonkey[] getNextGeneration()
	{
		Arrays.sort(Zoo);
		SpasticMonkey[] result = new SpasticMonkey[this.Zoo.length];
		int resultPlace = 0;
		SingleLinkedList<SpasticMonkey> TunnelOfLove = new SingleLinkedList<SpasticMonkey>();
		StringBuilder Diag = new StringBuilder("Generational Proportions: ");
		
		for(int i = 0; i < result.length && resultPlace < result.length; i++)
		{
			if(RandyAutobot.getRandyBool(ConstantAutobot.getProbabilityOfMovingOnFor(i)))
			{
				result[resultPlace] = this.Zoo[i];
				resultPlace++;
			}
			
			if(RandyAutobot.getRandyBool(ConstantAutobot.getProbabilityOfMovingOnFor(i)))
			{
				TunnelOfLove.add(this.Zoo[i]);
			}
		}
		
		int NumCopied = resultPlace;
		Diag.append("\n\tCopied: " + ((double) NumCopied / (double) result.length) + "%");
		
		TunnelOfLove = TunnelOfLove.shuffleList();
		
		while(TunnelOfLove.size() > 1 && resultPlace < result.length)
		{
			SpasticMonkey Boner1 = TunnelOfLove.pop();
			SpasticMonkey Boner2 = TunnelOfLove.pop();
			
			result[resultPlace] = SpasticMonkey.makeBaby(Boner1, Boner2);
			resultPlace++;
		}
		
		int NumBabied = resultPlace - NumCopied;
		Diag.append("\n\tBabied: " + ((double) NumBabied / (double) result.length) + "%"
				+ "\n\tRandied: " + ((double) (result.length - NumBabied - NumCopied) / (double) result.length) + "%");
		
		if(resultPlace < result.length)
		{
			for(int i = resultPlace; i < result.length; i++)
			{
				result[i] = WeeblAutobot.requestNewSpasticMonkey(ConstantAutobot.getDesiredTreeSize());
			}
		}
		
		for(int i = 0; i < Zoo.length; i++)
		{
			Zoo[i].RecycleMe();
			
			result[i].checkForMutation();
		}
		
		this.Zoo = result;
		
		return result;
	}
}
