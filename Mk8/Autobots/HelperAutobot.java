package EnemyLevelScaling.GeneticProgrammer.Mk8.Autobots;

import EnemyLevelScaling.GeneticProgrammer.Mk8.Enums.ReturnType;
import EnemyLevelScaling.GeneticProgrammer.Mk8.Nodes.AbstractClasses.BaseNode;
import EnemyLevelScaling.GeneticProgrammer.Mk8.Nodes.AbstractClasses.BoolNode;
import EnemyLevelScaling.GeneticProgrammer.Mk8.Nodes.AbstractClasses.NumNode;

public class HelperAutobot
{
	public static int cleanIndex(double in, int ArrayLength)
	{
		return (((int) in) % ArrayLength + ArrayLength) % ArrayLength;
	}
	
	public static StringBuilder fromArrayToStringBuilder(int[] arr, String delimiter)
	{
		StringBuilder result = new StringBuilder();
		
		for(int i = 0; i < arr.length; i++)
		{
			if(i != 0)
			{
				result.append(delimiter);
			}
			
			result.append(arr[i] + "");
		}
		
		return result;
	}
	
	public static double getDubFromNode(BaseNode in)
	{
		double result;
		
		if(in.getReturnType() == ReturnType.Number)
		{
			result = ((NumNode) in).getValue();
		}
		else
		{
			result = -0;
		}
		
		return result;
	}
	
	public static int getIndexFromNode(BaseNode in)
	{
		int result;
		
		if(in.getReturnType() == ReturnType.Number)
		{
			result = (int) ((NumNode) in).getValue();
		}
		else
		{
			result = -0;
		}
		
		return result;
	}
	
	public static boolean getBoolFromNode(BaseNode in)
	{
		boolean result;
		
		if(in.getReturnType() == ReturnType.Boolean)
		{
			result = ((BoolNode) in).getValue();
		}
		else
		{
			result = false;
		}
		
		return result;
	}
	
	public static int[] MultiplyArrays(int[] var1, int[] var2)
	{
		int[] result = new int[var1.length];
		
		for(int i = 0; i < var1.length; i++)
		{
			result[i] = var1[i] * var2[i];
		}
		
		return result;
	}
}
