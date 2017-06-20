package EnemyLevelScaling.GeneticProgrammer.Mk6.Enums;

import Masquerade.Mk1.Mask;

public enum ReturnType
{
	Boolean, Double, Integer, Void;

	private static ReturnType[] All = values();

	public static int getNumElements()
	{
		return All.length;
	}

	public static ReturnType getReturnTypeAt(int index)
	{
		return All[index];
	}

	public static int getIndexOfReturnType(ReturnType in)
	{
		int result = 0;
		
		switch (in)
		{
		case Boolean:
			{
				result = 0;
				break;
			}
		case Double:
			{
				result = 1;
				break;
			}
		case Integer:
			{
				result = 2;
				break;
			}
		case Void:
			{
				result = 3;
				break;
			}
		}
		
		return result;
	}
	
	
	/**
	 * bit at lowest index shows whether table was bounded to terminals-only or not, ie TRUE = isTerminals.
	 * @param in
	 * @param isTerminals
	 * @return
	 */
	public static byte getBoolMaskFromArr(ReturnType[] in, boolean isTerminals)
	{
		byte result = 0;
		
		if(isTerminals)
		{
			result = Mask.TurnBitOn(result, 0);
		}

		for (ReturnType CurrRet : in)
		{
			int CurrOff = getIndexOfReturnType(CurrRet) + 1;
			result = Mask.TurnBitOn(result, CurrOff);
			//System.out.println("TRIGGERING, with new result: " + HelperAutobot.getAsBinaryString(result));
		}

		//System.out.println("Turned ReturnType[] into mask.\n\tReturnTypes: " + getArrAsString(in) + "\n\tMask: " + HelperAutobot.getAsBinaryString(result));
		
		return result;
	}
	
	public static String getArrAsString(ReturnType[] in)
	{
		StringBuilder result = new StringBuilder();
		
		for(int i = 0; i < in.length; i++)
		{
			if(i != 0)
			{
				result.append(" ");
			}
			
			result.append(in[i].toString());
		}
		
		return result.toString();
	}
}
