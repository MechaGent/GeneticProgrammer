package EnemyLevelScaling.GeneticProgrammer.Mk1.Nodes;

import java.util.HashMap;

import EnemyLevelScaling.GeneticProgrammer.Mk1.Enums.NodeType;

public class VarNode implements ExpressionNode
{
	private String Name;
	private HashMap<String, Integer> VarPool;
	
	public VarNode(String inName, HashMap<String, Integer> inVarPool)
	{
		this.Name = inName;
		this.VarPool = inVarPool;
	}
	
	@Override
	public NodeType getType()
	{
		return NodeType.Variable;
	}

	@Override
	public int getValue()
	{
		return this.VarPool.get(this.Name);
	}

	@Override
	public StringBuilder toFormattedString()
	{
		return new StringBuilder(this.Name);
	}

	@Override
	public void SetInputNodes(ExpressionNode[] InputNodes)
	{
		
	}

	@Override
	public ExpressionNode Copy()
	{
		return new VarNode(this.Name, this.copyInputsMap());
	}
	
	private HashMap<String, Integer> copyInputsMap()
	{
		HashMap<String, Integer> result = new HashMap<String, Integer>();
		result.putAll(VarPool);
		
		return result;
	}

	@Override
	public void MutateSelf()
	{
		
	}

	@Override
	public ExpressionNode getRandomChildNode()
	{
		return null;
	}
}
