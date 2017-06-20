package EnemyLevelScaling.GeneticProgrammer.Mk6;

import EnemyLevelScaling.GeneticProgrammer.Mk6.Autobots.ConstantAutobot;

public class GlobalVars
{
	private int[] IntArray;
	private double[] DubArray;
	private int Driver;

	/**
	 * Unless otherwise noted, take IntArray[0] as the result of the overall RNG'd program
	 */
	public GlobalVars()
	{
		this.IntArray = ConstantAutobot.getNewTestIntArr();
		this.DubArray = ConstantAutobot.getNewTestDubArr();
		this.Driver = ConstantAutobot.getDriverStartValue();
	}

	public void setIntArrayAt(int index, int input)
	{
		this.IntArray[Math.abs(index%this.IntArray.length)] = input;
	}

	public void setDubArrayAt(int index, double input)
	{
		this.DubArray[Math.abs(index%this.DubArray.length)] = input;
	}

	public int getIntAt(int index)
	{	
		if (index == this.IntArray.length)
		{
			return this.Driver;
		}
		else
		{
			return this.IntArray[Math.abs(index%this.IntArray.length)];
		}
	}

	public double getDoubleAt(int index)
	{
		return this.DubArray[Math.abs(index%this.DubArray.length)];
	}
	
	public int getIntArrLength()
	{
		return this.IntArray.length + 1;
	}
	
	public int getDubArrLength()
	{
		return this.DubArray.length;
	}
	
	public int getAnswer()
	{
		return this.IntArray[0];
	}
	
	public void setDriver(int in)
	{
		this.Driver = in;
	}
}
