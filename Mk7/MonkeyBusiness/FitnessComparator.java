package EnemyLevelScaling.GeneticProgrammer.Mk7.MonkeyBusiness;

import java.util.Comparator;

public class FitnessComparator implements Comparator<SpasticMonkey>
{	
	@Override
	public int compare(SpasticMonkey o1, SpasticMonkey o2)
	{
		//Descending order is o2 - o1
		//double value = o2.getFitness() - o1.getFitness();
		
		//Ascending order is o1 - o2
		double value = Math.abs(o1.getFitness()) - Math.abs(o2.getFitness());
		
		if(value < 0)
		{
			return -1;
		}
		else if(value == 0)
		{
			return 0;
		}
		else
		{
			return 1;
		}
	}
}
