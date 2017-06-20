package EnemyLevelScaling.GeneticProgrammer.Mk5.Nodes;

import EnemyLevelScaling.GeneticProgrammer.Mk5.BaseNode;
import EnemyLevelScaling.GeneticProgrammer.Mk5.ConstantAutobot;
import EnemyLevelScaling.GeneticProgrammer.Mk5.GlobalVars;
import EnemyLevelScaling.GeneticProgrammer.Mk5.ParentRef;
import EnemyLevelScaling.GeneticProgrammer.Mk5.Enums.ReturnType;
import EnemyLevelScaling.GeneticProgrammer.Mk5.Nodes.Interfaces.BoolNode;
import EnemyLevelScaling.GeneticProgrammer.Mk5.Nodes.Interfaces.Node;
import EnemyLevelScaling.GeneticProgrammer.Mk5.Nodes.Interfaces.VoidNode;

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
	private ReturnType Returns;
	private ReturnType[][] Returnables;
	
	public WhileNode()
	{
		super();
		this.Returns = ReturnType.Void;
		this.Returnables = new ReturnType[][]{{ReturnType.Boolean}, {ReturnType.Void}};
	}
	
	@Override
	public ReturnType getReturnType()
	{
		return this.Returns;
	}
	
	@Override
	public ReturnType[] getAllowedReturnTypesForChildAt(int index)
	{
		return this.Returnables[index];
	}
	
	@Override
	public void DooWop()
	{
		int Limiter = ConstantAutobot.getLoopLimiter();
		
		while((Limiter > 0) && ((BoolNode) super.getChildAt(0)).getValue())
		{
			((VoidNode) super.getChildAt(1)).DooWop();
			Limiter--;
		}
	}
	
	@Override
	public StringBuilder toStringBuilder()
	{
		StringBuilder result = new StringBuilder("While(");
		result.append(super.getChildAt(0).toStringBuilder());
		result.append(ConstantAutobot.getDelimiter());
		result.append(super.getChildAt(1).toStringBuilder());
		result.append(")");
		
		return result;
	}

	/**
	 * Can't currently think of a way to mutate this
	 */
	@Override
	public Node cascadeClone(ParentRef StepParent, GlobalVars newVars, boolean ApplyMutateChance)
	{
		Node result = new WhileNode();
		
		result.setParent(StepParent);
		
		Node[] StepChildren = new Node[this.getNumChildren()];
		
		for(int i = 0; i < StepChildren.length; i++)
		{
			StepChildren[i] = super.getChildAt(i).cascadeClone(new ParentRef(this, i), newVars, ApplyMutateChance);
		}
		
		result.setChildren(StepChildren);
		
		return result;
	}

	@Override
	public int getNumChildren()
	{
		return this.Returnables.length;
	}
}
