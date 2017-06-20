package EnemyLevelScaling.GeneticProgrammer.Mk4.Nodes;

import EnemyLevelScaling.GeneticProgrammer.Mk4.Nodes.Standards.Accumulator;

public class IntAccumulator implements Accumulator<Integer>
{
	private int Acc;
	
	public IntAccumulator()
	{
		this.Acc = 0;
	}
	
	public Integer getAcc()
	{
		return this.Acc;
	}
	
	public void addToAcc(double Value)
	{
		this.addToAcc((int) Value);
	}
	
	public void addToAcc(int Value)
	{
		this.Acc = this.Acc + Value;
	}
	
	public void subFromAcc(double Value)
	{
		this.subFromAcc((int) Value);
	}
	
	public void subFromAcc(int Value)
	{
		this.Acc = this.Acc - Value;
	}
	
	public void multAccBy(double Value)
	{
		this.multAccBy((int) Value);
	}
	
	public void multAccBy(int Value)
	{
		this.Acc = this.Acc * Value;
	}
	
	public void divAccBy(double Value)
	{
		this.divAccBy((int) Value);
	}
	
	public void divAccBy(int Value)
	{
		if(Value != 0)
		{
			this.Acc = this.Acc / Value;
		}
	}
	
	public void resetAcc()
	{
		this.Acc = 0;
	}

	@Override
	public String getType()
	{
		return "IntAcc";
	}
}
