package EnemyLevelScaling.GeneticProgrammer.Mk1.Nodes;

import EnemyLevelScaling.GeneticProgrammer.Mk1.Enums.NodeType;

public interface ExpressionNode
{
	public NodeType getType();
	public int getValue();
	public void SetInputNodes(ExpressionNode[] InputNodes);
	public StringBuilder toFormattedString();
	public ExpressionNode Copy();
	public void MutateSelf();
	public ExpressionNode getRandomChildNode();
}
