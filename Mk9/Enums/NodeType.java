package EnemyLevelScaling.GeneticProgrammer.Mk9.Enums;

import java.util.HashMap;

import DataStructures.Linkages.SingleLinkedList.Unsorted.Mk01.SingleLinkedList;
import EnemyLevelScaling.GeneticProgrammer.Mk9.Enums.ReturnType;
import Masquerade.Mk1.Mask;

public enum NodeType
{
	/**
	 * Children: 
	 * Boolean LogicStatement 
	 * Void CodeBlock_ifChild0isTrue
	 * Void CodeBlock_ifChild0isFalse
	 * Void CodeBlock_executeAfterEvalIsDone
	 */
	IfElse(
			ReturnType.Void,
			new ReturnType[][] {
									{
										ReturnType.Boolean },
									{
										ReturnType.Void },
									{
										ReturnType.Void },
									{
										ReturnType.Void } },
			true),

	/**
	 * Children: 
	 * Boolean LogicStatement 
	 * Void CodeBlock_ifChild0isTrue
	 * Void CodeBlock_executeAfterEvalIsDone
	 */
	While(
			ReturnType.Void,
			new ReturnType[][] {
									{
										ReturnType.Boolean, },
									{
										ReturnType.Void },
									{
										ReturnType.Void } },
			true),

	/**
	 * Number indexOfPertinentNumber (like a pseudo-pointer)
	 */
	NumGetter(
			ReturnType.Number,
			new ReturnType[][] {
									{
										ReturnType.Number } },
			true),

	/**
	 * Number indexOfPertinentNumber (like a pseudo-pointer) 
	 * Number valueToWhichPertinentNumberShallBeSet 
	 * Void CodeBlock_executeAfterEvalIsDone
	 */
	NumSetter(
			ReturnType.Void,
			new ReturnType[][] {
									{
										ReturnType.Number },
									{
										ReturnType.Number },
									{
										ReturnType.Void } },
			true),

	/**
	 * Leaf node
	 */
	VarGetter(
			ReturnType.Number,
			new ReturnType[0][0],
			true),

	/**
	 * Leaf node
	 */
	ConstNum(
			ReturnType.Number,
			new ReturnType[0][0],
			true),

	/**
	 * Leaf node
	 */
	ConstBool(
			ReturnType.Boolean,
			new ReturnType[0][0],
			true),

	/**
	 * Leaf node
	 */
	ConstVoid(
			ReturnType.Void,
			new ReturnType[0][0],
			true),

	/**
	 * Combines two numbers, ie addition, subtraction, etc
	 */
	BinNumNum(
			ReturnType.Number,
			new ReturnType[][] {
									{
										ReturnType.Number },
									{
										ReturnType.Number } },
			true),

	/**
	 * Compares two numbers, ie less than, greater than, etc
	 */
	BinNumBool(
			ReturnType.Boolean,
			new ReturnType[][] {
									{
										ReturnType.Number },
									{
										ReturnType.Number } },
			true),

	/**
	 * Performs boolean operations on two booleans, ie OR, AND, etc
	 */
	BinBoolBool(
			ReturnType.Boolean,
			new ReturnType[][] {
									{
										ReturnType.Boolean },
									{
										ReturnType.Boolean } },
			true),

	/**
	 * Absolute value, flip sign, etc
	 */
	UnaryNumNum(
			ReturnType.Number,
			new ReturnType[][] {
									{
										ReturnType.Number } },
			true),

	/**
	 * only NOT for right now
	 */
	UnaryBoolBool(
			ReturnType.Boolean,
			new ReturnType[][] {
									{
										ReturnType.Boolean } },
			true),;

	private ReturnType Returns;
	private ReturnType[][] ExpectedArguments;
	private ReturnType[][] UnExpectedArguments;
	private boolean isTerminal;
	private boolean shouldInclude;
	
	private static NodeType[] All = NodeType.values();

	private NodeType(ReturnType inReturns, ReturnType[][] inExpectedArguments, boolean inShouldInclude)
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
		this.shouldInclude = inShouldInclude;
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

	/**
	 * @return ReturnType of the enum
	 */
	public ReturnType getReturnType()
	{
		return this.Returns;
	}

	/**
	 * @return true if NodeType is terminal, false otherwise
	 */
	public boolean isTerminal()
	{
		return this.isTerminal;
	}

	/**
	 * 
	 * @return number of children expected of this NodeType
	 */
	public int getNumberOfExpectedArgumentSets()
	{
		return this.ExpectedArguments.length;
	}

	public ReturnType[] getExpectedArgumentsForChildAt(int index)
	{
		return this.ExpectedArguments[index];
	}

	private static HashMap<Byte, NodeType[]> ComboTables = new HashMap<Byte, NodeType[]>();

	/**
	 * bit at lowest index shows whether table was bounded to terminals-only or not, ie TRUE = isTerminals.
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

	/**
	 * Note to self: ConstVoid will ONLY be included in the Terminals array for Void, not the All array.
	 * 
	 * @param in
	 * @param wantsTerminals
	 * @return
	 */
	private static SingleLinkedList<NodeType> getNodeTypesFromReturnTypeAsList(ReturnType in, boolean wantsTerminals)
	{
		SingleLinkedList<NodeType> result = new SingleLinkedList<NodeType>();

		for (NodeType CurrIndex : NodeType.values())
		{
			if (CurrIndex.Returns == in && CurrIndex.shouldInclude)
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
					if (!CurrIndex.isTerminal)
					{
						result.add(CurrIndex);
					}
				}
			}
		}

		return result;
	}

	public static NodeType[] getNodeTypesFromReturnTypes(ReturnType[] in, boolean isTerminals)
	{
		return getNodeTypesFromComboMask(ReturnType.getBoolMaskFromArr(in, isTerminals));
	}

	public static NodeType[] getNodeTypesFromReturnTypes(ReturnType in, boolean isTerminals)
	{
		return getNodeTypesFromComboMask(ReturnType.getBoolMaskFromArr(in, isTerminals));
	}
	
	public static int getNumTypes()
	{
		return All.length;
	}
}
