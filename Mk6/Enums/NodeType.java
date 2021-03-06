package EnemyLevelScaling.GeneticProgrammer.Mk6.Enums;

import java.util.HashMap;

import DataStructures.Linkages.SingleLinkedList.Unsorted.Mk01.SingleLinkedList;
import EnemyLevelScaling.GeneticProgrammer.Mk6.Autobots.HelperAutobot;
import EnemyLevelScaling.GeneticProgrammer.Mk6.Nodes.Interfaces.Node;
import Masquerade.Mk1.Mask;

public enum NodeType
{
	/**
	 * Children: 
	 * Boolean/Double/Integer BooleanStatement (Double/Integer evaluated as Boolean) 
	 * Void CodeBlock_ifChild0isTrue Void CodeBlock_ifChild0isFalse 
	 * Void CodeBlock_executeAfterEvalIsDone
	 */
	IfElse(
			ReturnType.Void,
			new ReturnType[][] {
									{
										ReturnType.Boolean,
										ReturnType.Double,
										ReturnType.Integer },
									{
										ReturnType.Void },
									{
										ReturnType.Void },
									{
										ReturnType.Void } }),

	/**
	 * Boolean/Double/Integer BooleanStatement (Double/Integer evaluated as Boolean)
	 * Void CodeBlock_loopIfChild0isTrue 
	 * Void CodeBlock_executeAfterEvalIsDone
	 */
	While(
			ReturnType.Void,
			new ReturnType[][] {
									{
										ReturnType.Boolean,
										ReturnType.Double,
										ReturnType.Integer },
									{
										ReturnType.Void },
									{
										ReturnType.Void } }),

	/**
	 * Boolean/Double/Integer indexOfPertinentInteger (like a pseudo-pointer)
	 */
	IntGetter(
			ReturnType.Integer,
			new ReturnType[][] {
									{
										ReturnType.Boolean,
										ReturnType.Double,
										ReturnType.Integer } }),

	/**
	 * Boolean/Double/Integer indexOfPertinentInteger (like a pseudo-pointer)
	 * Boolean/Double/Integer valueToWhichPertinentIntegerShallBeSet
	 * Void CodeBlock_executeAfterEvalIsDone
	 */
	IntSetter(
			ReturnType.Void,
			new ReturnType[][] {
									{
										ReturnType.Boolean,
										ReturnType.Double,
										ReturnType.Integer },
									{
										ReturnType.Integer,
										ReturnType.Double },
									{
										ReturnType.Void } }),
	
	/**
	 * Boolean/Double/Integer indexOfPertinentDouble (like a pseudo-pointer)
	 */
	DubGetter(
			ReturnType.Double,
			new ReturnType[][] {
									{
										ReturnType.Boolean,
										ReturnType.Double,
										ReturnType.Integer } }),
	
	/**
	 * Boolean/Double/Integer indexOfPertinentDouble (like a pseudo-pointer)
	 * Boolean/Double/Integer valueToWhichPertinentDoubleShallBeSet
	 * Void CodeBlock_executeAfterEvalIsDone
	 */
	DubSetter(
			ReturnType.Void,
			new ReturnType[][] {
									{
										ReturnType.Boolean,
										ReturnType.Double,
										ReturnType.Integer },
									{
										ReturnType.Integer,
										ReturnType.Double } }),
	
	/**
	 * Leaf node
	 */
	GetVar(
			ReturnType.Double,
			new ReturnType[0][0]),
	
	/**
	 * Leaf node
	 */
	ConstInt(
			ReturnType.Integer,
			new ReturnType[0][0]),
	
	/**
	 * Leaf node
	 */
	ConstDub(
			ReturnType.Double,
			new ReturnType[0][0]),
	
	/**
	 * Leaf node
	 */
	ConstBool(
			ReturnType.Boolean,
			new ReturnType[0][0]),
	
	/**
	 * Leaf node
	 */
	ConstVoid(
			ReturnType.Void,
			new ReturnType[0][0]),
	
	/**
	 * Combines two numbers, ie addition, subtraction, etc
	 */
	BinaryNumNum(
			ReturnType.Double,
			new ReturnType[][] {
									{
										ReturnType.Boolean,
										ReturnType.Double,
										ReturnType.Integer },
									{
										ReturnType.Boolean,
										ReturnType.Double,
										ReturnType.Integer } }),
	
	/**
	 * Compares two numbers, ie less than, greater than, etc
	 */
	BinaryNumBool(
			ReturnType.Boolean,
			new ReturnType[][] {
									{
										ReturnType.Boolean,
										ReturnType.Double,
										ReturnType.Integer },
									{
										ReturnType.Boolean,
										ReturnType.Double,
										ReturnType.Integer } }),
	
	/**
	 * Performs boolean operations on two booleans, ie OR, AND, etc
	 */
	BinaryBoolBool(
			ReturnType.Boolean,
			new ReturnType[][] {
									{
										ReturnType.Boolean,
										ReturnType.Double,
										ReturnType.Integer },
									{
										ReturnType.Boolean,
										ReturnType.Double,
										ReturnType.Integer } }),
	
	/**
	 * Absolute value, flip sign, etc
	 */
	UnaryNum(
			ReturnType.Double,
			new ReturnType[][] {
									{
										ReturnType.Integer,
										ReturnType.Double } }),
	
	/**
	 * only NOT for right now
	 */
	UnaryBool(
			ReturnType.Boolean,
			new ReturnType[][] {
									{
										ReturnType.Boolean } }),;

	private static NodeType[] All = values();
	private static HashMap<ReturnType, NodeType[]> NonTerms = new HashMap<ReturnType, NodeType[]>();
	private static HashMap<ReturnType, NodeType[]> Terms = new HashMap<ReturnType, NodeType[]>();
	private static HashMap<Byte, NodeType[]> ComboTables = new HashMap<Byte, NodeType[]>();

	private ReturnType Returns;
	private ReturnType[][] ExpectedArguments;
	private ReturnType[][] UnExpectedArguments;
	private boolean isTerminal;

	private NodeType(ReturnType inReturns, ReturnType[][] inExpectedArguments)
	{
		this.Returns = inReturns;
		this.ExpectedArguments = inExpectedArguments;
		this.UnExpectedArguments = new ReturnType[this.ExpectedArguments.length][];

		for (int i = 0; i < this.ExpectedArguments.length; i++)
		{
			SingleLinkedList<ReturnType> tempUnRow = new SingleLinkedList<ReturnType>();

			for (int j = 0; j < ReturnType.getNumElements(); j++)
			{
				if (!this.checkIfIncluded_Lazy(ReturnType.getReturnTypeAt(j), this.ExpectedArguments[i]))
				{
					tempUnRow.add(ReturnType.getReturnTypeAt(j));
				}
			}

			this.UnExpectedArguments[i] = tempUnRow.toArray(new ReturnType[tempUnRow.size()]);
		}

		this.isTerminal = this.ExpectedArguments.length == 0;
	}

	private boolean checkIfIncluded_Lazy(ReturnType in, ReturnType[] Set)
	{
		boolean result = false;

		for (int i = 0; (i < Set.length) && !result; i++)
		{
			result = in == Set[i];
		}

		return result;
	}

	public boolean isLegalChildOf(Node in, int ChildIndex)
	{
		NodeType check = in.getNodeType();
		boolean result;

		if (ChildIndex < check.ExpectedArguments.length)
		{
			if (check.ExpectedArguments[ChildIndex].length < check.UnExpectedArguments[ChildIndex].length)
			{
				result = this.checkIfIncluded_Lazy(this.Returns, check.ExpectedArguments[ChildIndex]);
			}
			else
			{
				result = !this.checkIfIncluded_Lazy(this.Returns, check.UnExpectedArguments[ChildIndex]);
			}
		}
		else
		{
			result = false;
		}

		return result;
	}

	public ReturnType[][] getAllExpectedArguments()
	{
		return this.ExpectedArguments;
	}

	public int getNumberOfExpectedArgumentSets()
	{
		return this.ExpectedArguments.length;
	}

	public ReturnType[] getExpectedArgumentsForChildAt(int index)
	{
		return this.ExpectedArguments[index];
	}

	public ReturnType getReturnType()
	{
		return this.Returns;
	}

	public boolean isTerminal()
	{
		return this.isTerminal;
	}

	public static NodeType getEnumAtIndex(int in)
	{
		return All[in];
	}

	public static int getNumValues()
	{
		return All.length;
	}

	/**
	 * Note to self: ConstVoid will ONLY be included in the Terminals array for
	 * Void, not the All array.
	 * 
	 * @param in
	 * @param wantsTerminals
	 * @return
	 */
	private static SingleLinkedList<NodeType> getNodeTypesFromReturnTypeAsList(ReturnType in, boolean wantsTerminals)
	{
		SingleLinkedList<NodeType> result = new SingleLinkedList<NodeType>();

		for (NodeType CurrIndex : All)
		{
			if (CurrIndex.Returns == in)
			{
				if (wantsTerminals)
				{
					if (CurrIndex.isTerminal)
					{
						result.add(CurrIndex);
					}
				}
				else
				{
					if (CurrIndex != ConstVoid)
					{
						result.add(CurrIndex);
					}
				}
			}
		}

		return result;
	}

	public static NodeType[] getNodeTypesFromReturnType(ReturnType in, boolean wantsTerminals)
	{
		HashMap<ReturnType, NodeType[]> resultMap;

		if (wantsTerminals)
		{
			resultMap = Terms;
		}
		else
		{
			resultMap = NonTerms;
		}

		NodeType[] result = resultMap.get(in);

		if (result == null)
		{
			SingleLinkedList<NodeType> resultList = getNodeTypesFromReturnTypeAsList(in, wantsTerminals);

			result = resultList.toArray(new NodeType[resultList.size()]);

			resultMap.put(in, result);
		}

		return result;
	}

	/**
	 * bit at lowest index shows whether table was bounded to terminals-only or
	 * not, ie TRUE = isTerminals.
	 * 
	 * @param in
	 * @return
	 */
	public static NodeType[] getNodeTypesFromComboMask(byte in)
	{
		NodeType[] result = ComboTables.get(in);
		//HelperAutobot.Logger.append(1, "\nCurrMask: " + Integer.toBinaryString(in));

		if (result == null)
		{
			boolean isTerminals = Masquerade.Mk1.Mask.getBoolAt(in, 0);
			//HelperAutobot.Logger.append(1, "\nisTerminals: " + isTerminals);
			SingleLinkedList<NodeType> resultList = new SingleLinkedList<NodeType>();

			for (byte i = 0; i < ReturnType.getNumElements(); i++)
			{
				ReturnType CurrRet = ReturnType.getReturnTypeAt(i);

				if (Mask.getBoolAt(in, i + 1))
				{
					resultList.add(getNodeTypesFromReturnTypeAsList(CurrRet, isTerminals));
				}
			}

			result = resultList.toArray(new NodeType[resultList.size()]);
			ComboTables.put(in, result);
			//System.out.println("resultList size: " + resultList.size());
		}

		return result;
	}

	public static NodeType[] getNodeTypesFromReturnTypes(ReturnType[] in, boolean isTerminals)
	{
		return getNodeTypesFromComboMask(ReturnType.getBoolMaskFromArr(in, isTerminals));
	}
}
