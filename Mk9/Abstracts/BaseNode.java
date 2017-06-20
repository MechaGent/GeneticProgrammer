package EnemyLevelScaling.GeneticProgrammer.Mk9.Abstracts;

import EnemyLevelScaling.GeneticProgrammer.Mk9.VarsPool;
import EnemyLevelScaling.GeneticProgrammer.Mk9.ParentRef;
import EnemyLevelScaling.GeneticProgrammer.Mk9.Enums.OutputMode;
import EnemyLevelScaling.GeneticProgrammer.Mk9.Enums.ReturnType;
import EnemyLevelScaling.GeneticProgrammer.Mk9.Enums.NodeType;

public abstract class BaseNode implements Recyclable
{
	private ParentRef Parent;
	private NodeType Type;
	private BaseNode[] Children;
	private int[] TotalOffspring; //Sums all children and itself
	private boolean TotalOffspringIsAccurate;
	
	public BaseNode(ParentRef inParent, NodeType inType)
	{
		this.Parent = inParent;
		this.Type = inType;
		this.Children = new BaseNode[this.Type.getNumberOfExpectedArgumentSets()];

		this.TotalOffspringIsAccurate = false;
	}

	public void RecycleBaseNode()
	{
		this.Parent.RecycleMe();
		this.Type = null;
		this.TotalOffspringIsAccurate = false;

		if (this.Children.length != 0)
		{
			for(int i = 0; i < this.Children.length; i++)
			{
				this.Children[i] = null;
			}
		}
	}

	public void unRecycleBaseNode(ParentRef inParent, NodeType inType)
	{
		this.Parent = inParent;
		this.Type = inType;
	}
	
	public void cascadeRecycle()
	{
		if (this.Children.length != 0)
		{
			for(int i = 0; i < this.Children.length; i++)
			{
				this.Children[i].cascadeRecycle();
			}
		}
		
		this.RecycleMe();
	}

	public abstract StringBuilder toStringBuilder(OutputMode in);

	public abstract BaseNode cascadeClone(ParentRef StepParent, VarsPool newVars);

	public abstract void applyMutationChance(boolean shouldCascade);

	public ParentRef getParentRef()
	{
		return Parent;
	}

	public NodeType getNodeType()
	{
		return Type;
	}

	public ReturnType getReturnType()
	{
		return Type.getReturnType();
	}

	public BaseNode getChildAt(int i)
	{
		return Children[i];
	}

	/**
	 * REMEMBER: this method returns the sum of this node and all of its children
	 * 
	 * @return
	 */
	public int[] getTotalOffspring()
	{
		if (this.TotalOffspringIsAccurate)
		{
			return this.TotalOffspring;
		}
		else
		{
			if (this.TotalOffspring == null)
			{
				this.TotalOffspring = new int[ReturnType.getNumElements()];
			}

			for (int CurrKidIndex = 0; CurrKidIndex < this.Children.length; CurrKidIndex++)
			{
				int[] CurrSubSum = this.Children[CurrKidIndex].getTotalOffspring();

				for (int CurrRetIndex = 0; CurrRetIndex < this.TotalOffspring.length; CurrRetIndex++)
				{
					if (CurrKidIndex == 0)
					{
						this.TotalOffspring[CurrRetIndex] = 0;
					}

					this.TotalOffspring[CurrRetIndex] += CurrSubSum[CurrRetIndex];

					if (CurrRetIndex == this.getReturnType().getIndex())
					{
						this.TotalOffspring[CurrRetIndex]++;
					}
				}
			}

			this.TotalOffspringIsAccurate = true;

			return this.TotalOffspring;
		}
	}

	public int getSubTotalOffspring(ReturnType in)
	{
		return this.getTotalOffspring()[in.getIndex()];
	}

	public void setChildAt(int index, BaseNode Child)
	{
		this.Children[index] = Child;
		Child.Parent = new ParentRef(this, index);
		this.TotalOffspringIsAccurate = false;
	}

	public int getNumChildren()
	{
		return this.Type.getNumberOfExpectedArgumentSets();
	}

	public ReturnType[] getExpectedArgumentsFromChildAt(int i)
	{
		return this.Type.getExpectedArgumentsForChildAt(i);
	}

	public void setChildren(BaseNode[] inChildren)
	{
		this.Children = inChildren;
	}
}
