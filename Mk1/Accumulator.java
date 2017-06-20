package EnemyLevelScaling.GeneticProgrammer.Mk1;

public class Accumulator
{
	private int Acc;
	
	public Accumulator()
	{
		this.Acc = 0;
	}
	
	public int getAcc()
	{
		return this.Acc;
	}
	
	public int addToAcc(int Value)
	{
		this.Acc += Value;
		return this.getAcc();
	}
	
	public int subFromAcc(int Value)
	{
		this.Acc -= Value;
		return this.getAcc();
	}
	
	public int multAccBy(int Value)
	{
		this.Acc *= Value;
		return this.getAcc();
	}
	
	public int divAccBy(int Value)
	{
		this.Acc /= Value;
		return this.getAcc();
	}
	
	public void resetAcc()
	{
		this.Acc = 0;
	}
}
