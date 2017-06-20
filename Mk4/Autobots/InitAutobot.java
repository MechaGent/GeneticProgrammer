package EnemyLevelScaling.GeneticProgrammer.Mk4.Autobots;

import EnemyLevelScaling.GeneticProgrammer.Mk4.Nodes.Standards.Enums.NodeType;

public class InitAutobot
{
	public static void initAll()
	{
		initNodeType();
	}
	
	private static void initNodeType()
	{
		NodeType.initialize();
	}
}
