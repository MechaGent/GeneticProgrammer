package EnemyLevelScaling.GeneticProgrammer.Mk6;

import EnemyLevelScaling.GeneticProgrammer.Mk6.Autobots.HelperAutobot;
import EnemyLevelScaling.GeneticProgrammer.Mk6.Enums.NodeType;
import EnemyLevelScaling.GeneticProgrammer.Mk6.Enums.ReturnType;
import EnemyLevelScaling.GeneticProgrammer.Mk6.Nodes.Interfaces.Node;

public class BaseNode
{
	private ParentRef Parent;
	private NodeType Type;
	private Node[] Children;
	private int[] TotalOffspring; //Tracks all children, including children's children and so on
	private boolean TotalOffspringIsAccurate;

	public BaseNode(NodeType inType)
	{
		this(null, inType, new Node[inType.getNumberOfExpectedArgumentSets()]);
	}

	public BaseNode(ParentRef inParent, NodeType inType)
	{
		this(inParent, inType, new Node[inType.getNumberOfExpectedArgumentSets()]);
	}

	public BaseNode(ParentRef inParent, NodeType inType, Node[] inChildren)
	{
		this.Parent = inParent;
		this.Type = inType;
		this.Children = inChildren;
		this.TotalOffspring = new int[ReturnType.getNumElements()];
		this.TotalOffspringIsAccurate = false;
		//HelperAutobot.Logger.append(0, " New " + this.Type.toString() + " created");
	}

	public int[] getTotalOffspring()
	{
		if (this.TotalOffspringIsAccurate)
		{
			return this.TotalOffspring;
		}
		else
		{
			this.TotalOffspring = new int[ReturnType.getNumElements()];
			
			for (int i = 0; i < this.Children.length; i++)
			{
				if (this.Children[i] != null)
				{
					int[] TotalKids = this.Children[i].getTotalOffspring();
					
					for(int j = 0; j < TotalKids.length; j++)
					{
						if(i == 0)
						{
							this.TotalOffspring[j] = TotalKids[j];
						}
						else
						{
							this.TotalOffspring[j] += TotalKids[j];
						}
						
						if(ReturnType.getIndexOfReturnType(this.Children[i].getReturnType()) == j)
						{
							this.TotalOffspring[j] += 1;
						}
					}
				}
			}

			this.TotalOffspringIsAccurate = true;

			return this.TotalOffspring;
		}
	}
	
	public void setTotalOffspringAsInaccurate()
	{
		this.TotalOffspringIsAccurate = false;
		ParentRef tempRef = this.getParentRef();
		
		if(tempRef != null)
		{
			Node tempParent = tempRef.getParent();
			
			if(tempParent != null)
			{
				tempParent.setTotalOffspringAsInaccurate();
			}
		}
	}

	public ReturnType getReturnType()
	{
		return this.Type.getReturnType();
	}

	public int getNumChildren()
	{
		return this.Type.getNumberOfExpectedArgumentSets();
	}

	public ParentRef getParentRef()
	{
		return Parent;
	}

	public void setParent(ParentRef parent)
	{
		Parent = parent;
	}

	public Node[] getChildren()
	{
		return Children;
	}

	public ReturnType[] getExpectedArgumentsFromChildAt(int index)
	{
		return this.Type.getExpectedArgumentsForChildAt(index);
	}

	public ReturnType[][] getAllExpectedArgumentsFromChildren()
	{
		return this.Type.getAllExpectedArguments();
	}

	public Node getChildAt(int index)
	{
		return this.Children[index];
	}

	public void setChildren(Node[] children)
	{
		Children = children;
		this.setTotalOffspringAsInaccurate();
	}

	public NodeType getNodeType()
	{
		return this.Type;
	}
}
