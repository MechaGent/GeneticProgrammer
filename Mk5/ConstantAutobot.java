package EnemyLevelScaling.GeneticProgrammer.Mk5;

import java.util.HashMap;

import EnemyLevelScaling.GeneticProgrammer.Mk5.Enums.NodeType;
import EnemyLevelScaling.GeneticProgrammer.Mk5.Enums.ReturnType;

public class ConstantAutobot
{
	private static int DriverStartValue = 1;
	private static int DriverEndValue = 45;
	private static int IntArrayLength = 10;
	private static int DubArrayLength = 10;
	private static int LoopLimiter = 1000000;
	private static int ConstDeltaMax = 3;
	private static int DesiredTreeSize = 30;
	private static int ConstBounds = 10000;

	private static double MutationChance = 0.05;

	private static char Delimiter = ';';

	private static boolean areNodeTypesCollated = false;
	private static HashMap<ReturnType, NodeType[]> Returners;
	private static HashMap<ReturnType, NodeType[]> Terminals;
	private static boolean isTableInitialized = false;
	private static RollTableRepo Tables;

	private static HashMap<String, Double> Vars;
	private static String[] VarNames;
	private static double[] VarValues;

	public static void setVariables(HashMap<String, Double> in)
	{
		Vars = in;
		VarNames = in.keySet().toArray(new String[in.size()]);
		VarValues = new double[VarNames.length];

		for (int i = 0; i < VarValues.length; i++)
		{
			VarValues[i] = Vars.get(VarNames[i]);
		}
	}

	public static double getVarValue(String Name)
	{
		return Vars.get(Name);
	}

	public static double getVarValue(int index)
	{
		return VarValues[index];
	}

	public static String getVarName(int index)
	{
		return VarNames[index];
	}

	public static String[] getVarNames()
	{
		return VarNames;
	}

	public static int getNumVars()
	{
		return VarValues.length;
	}

	public static int getLoopLimiter()
	{
		return LoopLimiter;
	}

	public static char getDelimiter()
	{
		return Delimiter;
	}

	private static void initializeNodeTypes()
	{
		ChainBelt<NodeType> tempVoid = new ChainBelt<NodeType>();
		ChainBelt<NodeType> tempVoidTerm = new ChainBelt<NodeType>();
		ChainBelt<NodeType> tempInt = new ChainBelt<NodeType>();
		ChainBelt<NodeType> tempIntTerm = new ChainBelt<NodeType>();
		ChainBelt<NodeType> tempDub = new ChainBelt<NodeType>();
		ChainBelt<NodeType> tempDubTerm = new ChainBelt<NodeType>();
		ChainBelt<NodeType> tempBool = new ChainBelt<NodeType>();
		ChainBelt<NodeType> tempBoolTerm = new ChainBelt<NodeType>();

		NodeType[] All = NodeType.values();

		for (int i = 0; i < All.length; i++)
		{
			NodeType CurrType = All[i];
			//System.out.println("CurrType: " + i + " " + CurrType);

			switch (CurrType.getReturnType())
			{
			case Boolean:
				{
					tempBool.add(CurrType);

					if (CurrType.isTerminal())
					{
						tempBoolTerm.add(CurrType);
					}

					break;
				}
			case Double:
				{
					tempDub.add(CurrType);

					if (CurrType.isTerminal())
					{
						tempDubTerm.add(CurrType);
					}

					break;
				}
			case Integer:
				{
					tempInt.add(CurrType);

					if (CurrType.isTerminal())
					{
						tempIntTerm.add(CurrType);
					}

					break;
				}
			case Void:
				{
					if (CurrType != NodeType.ConstVoid)
					{
						tempVoid.add(CurrType);
					}

					if (CurrType.isTerminal())
					{
						tempVoidTerm.add(CurrType);
					}

					break;
				}
			default:
				{
					System.out.println("What the fuck");
					break;
				}
			}
		}

		Returners = new HashMap<ReturnType, NodeType[]>();
		Returners.put(ReturnType.Void, HelperAutobot.FromChainBeltToArray(tempVoid));
		Returners.put(ReturnType.Boolean, HelperAutobot.FromChainBeltToArray(tempBool));
		Returners.put(ReturnType.Double, HelperAutobot.FromChainBeltToArray(tempDub));
		Returners.put(ReturnType.Integer, HelperAutobot.FromChainBeltToArray(tempInt));

		Terminals = new HashMap<ReturnType, NodeType[]>();
		Terminals.put(ReturnType.Boolean, HelperAutobot.FromChainBeltToArray(tempBoolTerm));
		Terminals.put(ReturnType.Double, HelperAutobot.FromChainBeltToArray(tempDubTerm));
		Terminals.put(ReturnType.Integer, HelperAutobot.FromChainBeltToArray(tempIntTerm));
		Terminals.put(ReturnType.Void, HelperAutobot.FromChainBeltToArray(tempVoidTerm));
	}

	public static NodeType[] getRollTableForMask(byte in, boolean wantsTerminalsOnly)
	{
		if (!isTableInitialized)
		{
			Tables = new RollTableRepo();
			isTableInitialized = true;
		}
		
		return Tables.getTableFromMask(in, wantsTerminalsOnly);
	}
	
	public static NodeType[] getRollTableForMask(ReturnType in, boolean wantsTerminalsOnly)
	{
		if (!isTableInitialized)
		{
			Tables = new RollTableRepo();
			isTableInitialized = true;
		}
		
		return Tables.getTableFromReturnType(in, wantsTerminalsOnly);
	}

	public static NodeType[] getReturnersOfType(ReturnType in, boolean getTerminals)
	{
		if (!areNodeTypesCollated)
		{
			initializeNodeTypes();
			areNodeTypesCollated = true;
		}

		if (getTerminals)
		{
			return Terminals.get(in);
		}
		else
		{
			return Returners.get(in);
		}
	}

	public static ChainBelt<NodeType[]> getReturnersOfType(ReturnType[] in, boolean getTerminals)
	{
		if (!areNodeTypesCollated)
		{
			initializeNodeTypes();
			areNodeTypesCollated = true;
		}

		ChainBelt<NodeType[]> result = new ChainBelt<NodeType[]>();

		for (int i = 0; i < in.length; i++)
		{
			result.add(getReturnersOfType(in[i], getTerminals));
		}

		return result;
	}

	public static int getIntArrayLength()
	{
		return IntArrayLength;
	}

	public static double getDubArrayLength()
	{
		return DubArrayLength;
	}

	public static int[] getNewTestIntArr()
	{
		return new int[IntArrayLength];
	}

	public static double[] getNewTestDubArr()
	{
		return new double[DubArrayLength];
	}

	public static double getMutationChance()
	{
		return MutationChance;
	}

	public static int getConstDeltaMax()
	{
		return ConstDeltaMax;
	}

	public static int getDesiredTreeSize()
	{
		return DesiredTreeSize;
	}

	public static int getDriverStartValue()
	{
		return DriverStartValue;
	}

	public static int getConstBounds()
	{
		return ConstBounds;
	}

	public static int getDriverEndValue()
	{
		return DriverEndValue;
	}
}
