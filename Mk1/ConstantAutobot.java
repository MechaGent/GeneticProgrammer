package EnemyLevelScaling.GeneticProgrammer.Mk1;

public class ConstantAutobot
{
	private static int[] KnownDeltas;
	private static final int NumReps = 40;
	private static final double MutationChance = 0.05;
	private static final int ConstMutIncrem = 10;
	
	public static void setKnownDeltas(int[] in)
	{
		KnownDeltas = in;
	}
	
	public static int[] getAllKnownDeltas()
	{
		return KnownDeltas;
	}
	
	public static int getKnownDeltaForOffsetWave(int OffsetWave)
	{
		return KnownDeltas[OffsetWave + 10];
	}
	
	public static int getNumReps()
	{
		return NumReps;
	}
	
	public static double getMutationChance()
	{
		return MutationChance;
	}
	
	public static int getConstMutIncrem()
	{
		return ConstMutIncrem;
	}
}
