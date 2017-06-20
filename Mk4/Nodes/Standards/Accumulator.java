package EnemyLevelScaling.GeneticProgrammer.Mk4.Nodes.Standards;

public interface Accumulator<E>
{
	public E getAcc();
	public void addToAcc(double Value);
	public void subFromAcc(double Value);
	public void multAccBy(double Value);
	public void divAccBy(double Value);
	
	public void addToAcc(int Value);
	public void subFromAcc(int Value);
	public void multAccBy(int Value);
	public void divAccBy(int Value);
	
	public void resetAcc();
	public String getType();
}
