package EnemyLevelScaling.GeneticProgrammer.Mk8;

import EnemyLevelScaling.GeneticProgrammer.Mk8.Autobots.ConstantAutobot;
import EnemyLevelScaling.GeneticProgrammer.Mk8.Autobots.WeeblAutobot;
import EnemyLevelScaling.GeneticProgrammer.Mk8.Nodes.AbstractClasses.Recyclable;

public class GlobalVarsPool implements Recyclable
{
	private double[] Bank;
	private int Driver;
	private int TrialNum;
	
	/**
	 * Unless otherwise noted, take IntArray[0] as the result of the overall RNG'd program
	 */
	public GlobalVarsPool()
	{
		this.Bank = new double[ConstantAutobot.getNumArrayLength()];
		this.Driver = -1;
		this.TrialNum = 0;
	}
	
	@Override
	public void RecycleMe()
	{
		WeeblAutobot.Recycle(this);
	}
	
	public GlobalVarsPool unRecycleMe()
	{
		for(int i = 0; i < this.Bank.length; i++)
		{
			this.Bank[i] = 0;
		}
		
		this.Driver = -1;
		this.TrialNum = 0;
		
		return this;
	}
	
	public void setNumArrayAt(int index, double in)
	{
		this.Bank[index] = in;
	}
	
	public double getFromNumArrayAt(int index)
	{
		return this.Bank[index];
	}
	
	public void setDriver(int in)
	{
		this.Driver = in;
	}
	
	public int getDriver()
	{
		return this.Driver;
	}

	public double getAnswer()
	{
		return this.Bank[0];
	}

	public int getTrialNum()
	{
		return TrialNum;
	}

	public void setTrialNum(int trialNum)
	{
		TrialNum = trialNum;
	}
}
