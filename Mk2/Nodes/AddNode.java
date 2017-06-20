package EnemyLevelScaling.GeneticProgrammer.Mk2.Nodes;

import EnemyLevelScaling.GeneticProgrammer.Mk2.ConstantAutobot;
import EnemyLevelScaling.GeneticProgrammer.Mk2.NodeType;
import Random.MkNeg01.XorShiftStarRng.Static.XorShiftStar1024;

public class AddNode extends Node
{
	public AddNode(Node inParent, int inParIndex)
	{
		this.Parent = inParent;
		this.ParentIndex = inParIndex;
		this.Type = NodeType.Add;
	}
	
	public void setChildren(Node[] inKids)
	{
		this.Children = inKids;
	}
	
	public int getValue()
	{
		return this.Children[0].getValue() + this.Children[1].getValue();
	}
	
	public void Mutate()
	{
		if(XorShiftStar1024.flipCoin(ConstantAutobot.getMutationChance()))
		{
			NodeType NextSelf = NodeType.getRandomMutable2();
		}
	}
}
