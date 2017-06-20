package EnemyLevelScaling.GeneticProgrammer.Mk1;

public class EvaluationException extends RuntimeException
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 6435875721301182924L;

	public EvaluationException()
	{
		
	}
	
	public EvaluationException(String message)
	{
		super(message);
	}
	
	public EvaluationException(Throwable cause)
	{
		super(cause);
	}
	
	public EvaluationException(String message, Throwable cause)
	{
		super(message, cause);
	}
}
