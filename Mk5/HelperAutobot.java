package EnemyLevelScaling.GeneticProgrammer.Mk5;

import java.util.Iterator;
import java.util.LinkedList;

import EnemyLevelScaling.GeneticProgrammer.Mk5.Enums.NodeType;
import EnemyLevelScaling.GeneticProgrammer.Mk5.Enums.ReturnType;
import EnemyLevelScaling.GeneticProgrammer.Mk5.Nodes.Interfaces.BoolNode;
import EnemyLevelScaling.GeneticProgrammer.Mk5.Nodes.Interfaces.DubNode;
import EnemyLevelScaling.GeneticProgrammer.Mk5.Nodes.Interfaces.IntNode;
import EnemyLevelScaling.GeneticProgrammer.Mk5.Nodes.Interfaces.Node;

public class HelperAutobot
{
	public static NodeType[] FromChainBeltToArray(ChainBelt<NodeType> in)
	{
		NodeType[] result = new NodeType[in.size()];
		Iterator<NodeType> Smooth = in.iterator();
		int i = 0;
		
		while(Smooth.hasNext())
		{
			result[i] = Smooth.next();
		}
		
		return result;
	}
	
	public static String getStringFromLinkedListOfNodeTypes(LinkedList<Node> in)
	{
		StringBuilder result = new StringBuilder();

		for (int i = 0; i < in.size(); i++)
		{
			if (i == 0)
			{
				result.append(in.get(i));
			}
			else
			{
				result.append(" " + in.get(i));
			}
		}

		return result.toString();
	}
	
	public static String getStringFromArrayOfNodeTypes(NodeType[] in)
	{
		StringBuilder result = new StringBuilder();

		for (int i = 0; i < in.length; i++)
		{
			if (i == 0)
			{
				result.append(in[i]);
			}
			else
			{
				result.append(" " + in[i]);
			}
		}

		return result.toString();
	}

	public static String getStringFromArrayOfReturnTypes(ReturnType[] in)
	{
		StringBuilder result = new StringBuilder();

		for (int i = 0; i < in.length; i++)
		{
			if (i == 0)
			{
				result.append(in[i]);
			}
			else
			{
				result.append(" " + in[i]);
			}
		}

		return result.toString();
	}

	public static boolean getBoolFromNode(Node in)
	{
		boolean result;

		switch (in.getReturnType())
		{
		case Boolean:
			{
				result = (boolean) ((BoolNode) in).getValue();
				break;
			}
		case Double:
		case Integer:
		case Void:
		default:
			{
				result = false;
				break;
			}
		}

		return result;
	}

	public static int getIntFromNode(Node in)
	{
		int result;

		switch (in.getReturnType())
		{
		case Double:
			{
				result = (int) ((DubNode) in).getValue();
				break;
			}
		case Integer:
			{
				result = ((IntNode) in).getValue();
				break;
			}
		case Void:
		case Boolean:
		default:
			{
				result = -1;
				break;
			}
		}

		return result;
	}

	public static double getDubFromNode(Node in)
	{
		double result;

		switch (in.getReturnType())
		{
		case Double:
			{
				result = ((DubNode) in).getValue();
				break;
			}
		case Integer:
			{
				result = ((IntNode) in).getValue();
				break;
			}
		case Void:
		case Boolean:
		default:
			{
				result = -1;
				break;
			}
		}

		return result;
	}
}
