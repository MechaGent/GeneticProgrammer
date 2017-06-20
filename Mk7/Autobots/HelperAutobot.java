package EnemyLevelScaling.GeneticProgrammer.Mk7.Autobots;

import java.lang.reflect.Array;
import java.util.Arrays;

import EnemyLevelScaling.GeneticProgrammer.Mk7.Enums.NodeType;
import EnemyLevelScaling.GeneticProgrammer.Mk7.Enums.ReturnType;
import EnemyLevelScaling.GeneticProgrammer.Mk7.Nodes.AbstractClasses.BaseNode;
import EnemyLevelScaling.GeneticProgrammer.Mk7.Nodes.AbstractClasses.BoolNode;
import EnemyLevelScaling.GeneticProgrammer.Mk7.Nodes.AbstractClasses.DubNode;
import EnemyLevelScaling.GeneticProgrammer.Mk7.Nodes.AbstractClasses.IntNode;

public class HelperAutobot
{
	public <T> T[] concatenate(T[] a, T[] b)
	{
		int aLen = a.length;
		int bLen = b.length;

		@SuppressWarnings("unchecked")
		T[] c = (T[]) Array.newInstance(a.getClass().getComponentType(), aLen + bLen);
		System.arraycopy(a, 0, c, 0, aLen);
		System.arraycopy(b, 0, c, aLen, bLen);

		return c;
	}

	public static NodeType[] concatAll(NodeType[]... in)
	{
		NodeType[] result;

		if (!(in == null || in.length == 0))
		{
			int totalLength = in[0].length;

			if (in.length > 1)
			{
				for (int i = 1; i < in.length; i++)
				{
					totalLength += in[i].length;
				}

				result = Arrays.copyOf(in[0], totalLength);

				int offset = in[0].length;

				for (int i = 1; i < in.length; i++)
				{
					System.arraycopy(in[i], 0, result, offset, in[i].length);
					offset += in[i].length;
				}
			}
			else
			{
				result = in[0];

			}
		}
		else
		{
			result = null;
		}

		return result;
	}
	
	public static String getStringFromArr(int[] in)
	{
		StringBuilder result = new StringBuilder();
		
		for(int i = 0; i < in.length; i++)
		{
			if(i != 0)
			{
				result.append(", ");
			}
			
			result.append(in[i]);
		}
		
		return result.toString();
	}
	
	public static String getStringFromArr(int[] in, int start)
	{
		StringBuilder result = new StringBuilder();
		
		for(int i = start; i < in.length; i++)
		{
			if(i != start)
			{
				result.append(", ");
			}
			
			result.append(in[i]);
		}
		
		return result.toString();
	}
	
	public static boolean getBoolFromNode(BaseNode in)
	{
		boolean result;
		ReturnType Ret = in.getReturnType();

		if (Ret == ReturnType.Boolean)
		{
			result = ((BoolNode) in).getValue();
		}
		else if (Ret == ReturnType.Double)
		{
			result = HelperAutobot.interpretNumAsBool(((DubNode) in).getValue());
		}
		else if (Ret == ReturnType.Integer)
		{
			result = HelperAutobot.interpretNumAsBool(((IntNode) in).getValue());
		}
		else
		{
			result = false;
		}

		return result;
	}
	
	public static int getIntFromNode(BaseNode in)
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
	
	public static int getIndexFromNode(BaseNode in, int ArrayLength)
	{
		int RawIndex = getIntFromNode(in);
		
		return (RawIndex % ArrayLength + ArrayLength) % ArrayLength;
	}
	
	public static double getDubFromNode(BaseNode in)
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
	
	public static boolean interpretNumAsBool(double in)
	{
		return in >= 0;
	}
	
	public static int[] MultiplyArrays(int[] var1, int[] var2)
	{
		int[] result = new int[var1.length];
		//StringBuilder test = new StringBuilder();
		
		for(int i = 0; i < result.length; i++)
		{
			result[i] = var1[i] * var2[i];
			//test.append(" " + result[i]);
		}
		
		//System.out.println("[" + test.toString() + "]");
		return result;
	}
}
