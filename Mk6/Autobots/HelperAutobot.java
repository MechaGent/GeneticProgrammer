package EnemyLevelScaling.GeneticProgrammer.Mk6.Autobots;

import EnemyLevelScaling.GeneticProgrammer.Mk6.SpasticMonkey;
import EnemyLevelScaling.GeneticProgrammer.Mk6.Enums.NodeType;
import EnemyLevelScaling.GeneticProgrammer.Mk6.Enums.ReturnType;
import EnemyLevelScaling.GeneticProgrammer.Mk6.Nodes.Interfaces.BoolNode;
import EnemyLevelScaling.GeneticProgrammer.Mk6.Nodes.Interfaces.DubNode;
import EnemyLevelScaling.GeneticProgrammer.Mk6.Nodes.Interfaces.IntNode;
import EnemyLevelScaling.GeneticProgrammer.Mk6.Nodes.Interfaces.Node;
import KennyLoggers.Mk01.Loggins;

public class HelperAutobot
{
	public static Loggins Logger = new Loggins(2, 500);
	
	public static boolean interpretIntAsBool(int in)
	{
		return in >= 0;
	}

	public static boolean interpretIntAsBool(double in)
	{
		return in >= 0;
	}

	public static boolean getBoolFromNode(Node in)
	{
		boolean result;
		ReturnType Ret = in.getReturnType();

		if (Ret == ReturnType.Boolean)
		{
			result = ((BoolNode) in).getValue();
		}
		else if (Ret == ReturnType.Double)
		{
			result = HelperAutobot.interpretIntAsBool(((DubNode) in).getValue());
		}
		else if (Ret == ReturnType.Integer)
		{
			result = HelperAutobot.interpretIntAsBool(((IntNode) in).getValue());
		}
		else
		{
			result = false;
		}

		return result;
	}

	public static boolean bulkEquals(NodeType var1, NodeType[] varRest)
	{
		boolean result = false;

		for (int i = 0; i < varRest.length && !result; i++)
		{
			result |= var1 == varRest[i];
		}

		return result;
	}

	public static double convertNanosecondsToSeconds(long in)
	{
		return (double) in / 1000000000.0;
	}

	public static String getAsBinaryString(byte in)
	{
		return Integer.toBinaryString(in);
	}

	public static String getStringFromArr(Node[] in)
	{
		StringBuilder result = new StringBuilder();

		for (int i = 0; i < in.length; i++)
		{
			if (i != 0)
			{
				result.append(" ");
			}

			in[i].toStringBuilder(result);
		}

		return result.toString();
	}

	public static String getStringFromArr(int[] in)
	{
		StringBuilder result = new StringBuilder();

		for (int i = 0; i < in.length; i++)
		{
			if (i != 0)
			{
				result.append(",");
			}

			result.append(in[i]);
		}

		return result.toString();
	}

	public static String getStringFromArr(SpasticMonkey[] in)
	{
		StringBuilder result = new StringBuilder();

		for (int i = 0; i < in.length; i++)
		{
			if (i != 0)
			{
				result.append("\n");
			}

			result.append(in[i].getMeatAndSkeleton());
		}

		return result.toString();
	}

	public static String getStringFromArr(NodeType[] in)
	{
		StringBuilder result = new StringBuilder();

		for (int i = 0; i < in.length; i++)
		{
			if (i != 0)
			{
				result.append(" ");
			}

			result.append(in[i].toString());
		}

		return result.toString();
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
		case Boolean:
			{
				if (((BoolNode) in).getValue())
				{
					result = 1;
				}
				else
				{
					result = 0;
				}
				break;
			}
		case Void:
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
