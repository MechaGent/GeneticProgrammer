package EnemyLevelScaling.GeneticProgrammer.Mk6.Nodes;

import EnemyLevelScaling.GeneticProgrammer.Mk6.BaseNode;
import EnemyLevelScaling.GeneticProgrammer.Mk6.GlobalVars;
import EnemyLevelScaling.GeneticProgrammer.Mk6.ParentRef;
import EnemyLevelScaling.GeneticProgrammer.Mk6.Autobots.ConstantAutobot;
import EnemyLevelScaling.GeneticProgrammer.Mk6.Autobots.HelperAutobot;
import EnemyLevelScaling.GeneticProgrammer.Mk6.Enums.NodeType;
import EnemyLevelScaling.GeneticProgrammer.Mk6.Enums.ReturnType;
import EnemyLevelScaling.GeneticProgrammer.Mk6.Nodes.Interfaces.Node;
import EnemyLevelScaling.GeneticProgrammer.Mk6.Nodes.Interfaces.VoidNode;

/**
 * Children scheme is:
 * Bool - CompareVal
 * Void - IfNode
 * Void - ElseNode
 * 
 * @author MechaGent
 *
 */
public class IfElseNode extends BaseNode implements VoidNode
{
	public IfElseNode()
	{
		super(NodeType.IfElse);
	}
	
	public IfElseNode(IfElseNode old, ParentRef StepParent, GlobalVars newVars, boolean ApplyMutateChance)
	{
		super(StepParent, NodeType.IfElse);
		super.setChildren(new Node[]{
				old.getChildAt(0).cascadeClone(new ParentRef(this, 0), newVars, ApplyMutateChance),
				old.getChildAt(1).cascadeClone(new ParentRef(this, 1), newVars, ApplyMutateChance),
				old.getChildAt(2).cascadeClone(new ParentRef(this, 2), newVars, ApplyMutateChance),
				});
	}
	
	@Override
	public ReturnType getReturnType()
	{
		return super.getReturnType();
	}
	
	@Override
	public ReturnType[] getExpectedArgumentsFromChildAt(int index)
	{
		return super.getExpectedArgumentsFromChildAt(index);
	}
	
	@Override
	public void DooWop()
	{
		boolean Child0 = HelperAutobot.getBoolFromNode(super.getChildAt(0));
		
		if(Child0)
		{
			((VoidNode) super.getChildAt(1)).DooWop();
		}
		else
		{
			((VoidNode) super.getChildAt(2)).DooWop();
		}
	}

	@Override
	public StringBuilder toStringBuilder(StringBuilder in)
	{
		in.append("IfElse(");
		super.getChildAt(0).toStringBuilder(in);
		in.append(ConstantAutobot.getArgDelimiter());
		super.getChildAt(1).toStringBuilder(in);
		in.append(ConstantAutobot.getArgDelimiter());
		super.getChildAt(2).toStringBuilder(in);
		in.append(")");
		
		return in;
	}
	
	/**
	 * Can't currently think of a way to mutate this
	 */
	@Override
	public Node cascadeClone(ParentRef StepParent, GlobalVars newVars, boolean ApplyMutateChance)
	{
		return new IfElseNode(this, StepParent, newVars, ApplyMutateChance);
	}
	
	@Override
	public int getNumChildren()
	{
		return super.getNumChildren();
	}
}
