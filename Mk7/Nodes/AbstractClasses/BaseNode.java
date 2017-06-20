package EnemyLevelScaling.GeneticProgrammer.Mk7.Nodes.AbstractClasses;

import EnemyLevelScaling.GeneticProgrammer.Mk7.Enums.ReturnType;
import EnemyLevelScaling.GeneticProgrammer.Mk7.GlobalVarsPool;
import EnemyLevelScaling.GeneticProgrammer.Mk7.ParentRef;
import EnemyLevelScaling.GeneticProgrammer.Mk7.Autobots.HelperAutobot;
import EnemyLevelScaling.GeneticProgrammer.Mk7.Enums.NodeType;

public abstract class BaseNode
{
	private ParentRef Parent;
	private NodeType Type;
	private BaseNode[] Children;
	private int[] TotalOffspring; //Tracks all children, including children's children and so on
	private boolean TotalOffspringIsAccurate;
	private boolean TotalOffspringIsInclusive;

	public BaseNode(NodeType inType)
	{
		this(null, inType, new BaseNode[inType.getNumberOfExpectedArgumentSets()]);
	}

	public BaseNode(ParentRef inParent, NodeType inType)
	{
		this(inParent, inType, new BaseNode[inType.getNumberOfExpectedArgumentSets()]);
	}

	public BaseNode(ParentRef inParent, NodeType inType, BaseNode[] inChildren)
	{
		this.Parent = inParent;
		this.Type = inType;
		this.Children = inChildren;
		this.TotalOffspring = new int[ReturnType.getNumElements()];
		this.TotalOffspringIsAccurate = false;
		this.TotalOffspringIsInclusive = false;
	}

	public abstract StringBuilder toStringBuilder();

	public abstract StringBuilder toStringBuilder(boolean wantsDiagnosticSyntax);

	public abstract StringBuilder toStringBuilder(StringBuilder in);

	/**
	 * Returns a deep copy of itself, already linked to the new tree
	 * 
	 * @param StepParent
	 * @param newVars
	 * @param ApplyMutateChance
	 * @return
	 */
	public abstract BaseNode cascadeClone(ParentRef StepParent, GlobalVarsPool newVars, boolean ApplyMutateChance);

	public BaseNode[] getChildren()
	{
		return this.Children;
	}

	public int getNumChildren()
	{
		return this.Children.length;
	}

	public BaseNode getChildAt(int index)
	{
		return this.Children[index];
	}

	public void setChildAt(int index, BaseNode newChild)
	{
		this.Children[index] = newChild;
	}

	public ReturnType[] getExpectedArgumentsFromChildAt(int index)
	{
		return this.Type.getExpectedArgumentsForChildAt(index);
	}

	public void setChildren(BaseNode[] children)
	{
		this.Children = children;
	}

	public ReturnType getReturnType()
	{
		return this.Type.getReturnType();
	}

	public NodeType getNodeType()
	{
		return this.Type;
	}

	public ParentRef getParentRef()
	{
		return this.Parent;
	}

	/**
	 * 
	 * @return the subtotals for offspring of current node, non-inclusive of self
	 */
	public int[] getTotalOffspring()
	{
		return this.getTotalOffspring(false);
	}
	
	public int[] getTotalOffspring(boolean includeSelf)
	{
		if (!this.TotalOffspringIsAccurate)
		{
			this.TotalOffspring = new int[ReturnType.getNumElements()];

			for (int i = 0; i < this.Children.length; i++)
			{
				BaseNode CurrChild = this.Children[i];

				if (CurrChild != null)
				{
					int[] TotalKids = CurrChild.getTotalOffspring();

					for (int j = 0; j < TotalKids.length; j++)
					{
						if (i == 0)
						{
							this.TotalOffspring[j] = TotalKids[j];
						}
						else
						{
							this.TotalOffspring[j] += TotalKids[j];
						}

						if (CurrChild.getReturnType() == ReturnType.getReturnTypeAt(j))
						{
							this.TotalOffspring[j] += 1;
						}
					}
				}
			}

			this.TotalOffspringIsAccurate = true;
		}
		
		if(includeSelf)
		{
			if(this.TotalOffspringIsInclusive)
			{
				return this.TotalOffspring;
			}
			else
			{
				this.TotalOffspring[this.getReturnType().getIndex()]++;
				this.TotalOffspringIsInclusive = true;
				return this.TotalOffspring;
			}
		}
		else
		{
			if(this.TotalOffspringIsInclusive)
			{
				this.TotalOffspring[this.getReturnType().getIndex()]--;
				this.TotalOffspringIsInclusive = false;
				return this.TotalOffspring;
			}
			else
			{
				return this.TotalOffspring;
			}
		}
	}

	public int getTotalOffspringAt(int index)
	{
		if (this.TotalOffspringIsAccurate)
		{
			return this.TotalOffspring[index];
		}
		else
		{
			return this.getTotalOffspring()[index];
		}
	}
	
	public int getTotalOffspringAt(int index, boolean includeSelf)
	{
		return this.getTotalOffspring(includeSelf)[index];
	}

	public String getTotalOffspringAsString()
	{
		return "[" + HelperAutobot.getStringFromArr(getTotalOffspring()) + "]";
	}

	public void setParent(ParentRef inParent)
	{
		Parent = inParent;

		if (inParent != null)
		{

			Parent.setAtRef(this);
		}
		else
		{
			//System.out.println("Triggered");
			throw new NullPointerException();
		}

		this.setTotalOffspringAsInaccurate();
	}

	private void setTotalOffspringAsInaccurate()
	{
		this.TotalOffspringIsAccurate = false;
		ParentRef tempRef = this.getParentRef();

		if (tempRef != null)
		{
			BaseNode tempParent = tempRef.getParent();

			if (tempParent != null)
			{
				tempParent.setTotalOffspringAsInaccurate();
			}
		}
	}

	public static void swapParents(BaseNode var1, BaseNode var2)
	{
		//System.out.println("Here I am");
		ParentRef temp = var1.Parent;
		var1.setParent(var2.Parent);
		var2.setParent(temp);
	}
}
