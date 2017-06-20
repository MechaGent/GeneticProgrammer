package EnemyLevelScaling.GeneticProgrammer.Mk6.Nodes;

import EnemyLevelScaling.GeneticProgrammer.Mk6.BaseNode;
import EnemyLevelScaling.GeneticProgrammer.Mk6.GlobalVars;
import EnemyLevelScaling.GeneticProgrammer.Mk6.ParentRef;
import EnemyLevelScaling.GeneticProgrammer.Mk6.Autobots.ConstantAutobot;
import EnemyLevelScaling.GeneticProgrammer.Mk6.Autobots.HelperAutobot;
import EnemyLevelScaling.GeneticProgrammer.Mk6.Enums.NodeType;
import EnemyLevelScaling.GeneticProgrammer.Mk6.Nodes.Interfaces.Node;
import EnemyLevelScaling.GeneticProgrammer.Mk6.Nodes.Interfaces.VoidNode;

/**
 * Children scheme is:
 * Bool - CompareVal
 * Void - WhileNode
 * 
 * @author MechaGent
 *
 */
public class WhileNode extends BaseNode implements VoidNode
{
	public WhileNode()
	{
		super(NodeType.While);
	}
	
	public WhileNode(WhileNode old, ParentRef StepParent, GlobalVars newVars, boolean ApplyMutateChance)
	{
		super(StepParent, NodeType.While);
		super.setChildren(new Node[]{
				old.getChildAt(0).cascadeClone(new ParentRef(this, 0), newVars, ApplyMutateChance),
				old.getChildAt(1).cascadeClone(new ParentRef(this, 1), newVars, ApplyMutateChance),
				});
	}
	
	@Override
	public void DooWop()
	{
		int Limiter = ConstantAutobot.getLoopLimiter();
		
		while((Limiter > 0) && HelperAutobot.getBoolFromNode(super.getChildAt(0)))
		{
			((VoidNode) super.getChildAt(1)).DooWop();
			Limiter--;
		}
	}
	
	@Override
	public StringBuilder toStringBuilder(StringBuilder in)
	{
		in.append("While(");
		super.getChildAt(0).toStringBuilder(in);
		in.append(ConstantAutobot.getArgDelimiter());
		super.getChildAt(1).toStringBuilder(in);
		in.append(")");
		
		return in;
	}
	
	/**
	 * Can't currently think of a way to mutate this
	 */
	@Override
	public Node cascadeClone(ParentRef StepParent, GlobalVars newVars, boolean ApplyMutateChance)
	{
		return new WhileNode(this, StepParent, newVars, ApplyMutateChance);
	}
}
