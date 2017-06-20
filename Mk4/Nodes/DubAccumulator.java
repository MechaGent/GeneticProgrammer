package EnemyLevelScaling.GeneticProgrammer.Mk4.Nodes;

import EnemyLevelScaling.GeneticProgrammer.Mk4.Nodes.Standards.Accumulator;

public class DubAccumulator implements Accumulator<Double>
{
	private double Acc;
	
	public DubAccumulator()
	{
		this.Acc = 0;
	}
	
	public Double getAcc()
	{
		return this.Acc;
	}
	
	public void addToAcc(int Value)
	{
		this.addToAcc((double) Value);
	}
	
	public void addToAcc(double Value)
	{
		this.Acc = this.Acc + Value;
	}
	
	public void subFromAcc(int Value)
	{
		this.subFromAcc((double) Value);
	}
	
	public void subFromAcc(double Value)
	{
		this.Acc = this.Acc - Value;
	}
	
	public void multAccBy(int Value)
	{
		this.multAccBy((double) Value);
	}
	
	public void multAccBy(double Value)
	{
		this.Acc = this.Acc * Value;
	}
	
	public void divAccBy(int Value)
	{
		this.divAccBy((double) Value);
	}
	
	public void divAccBy(double Value)
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
		return "DubAcc";
	}
}
