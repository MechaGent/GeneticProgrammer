package EnemyLevelScaling.GeneticProgrammer.Mk7;

import java.util.HashMap;

import EnemyLevelScaling.GeneticProgrammer.Mk7.Autobots.ConstantAutobot;

public class ParsePrepString
{
	private char[] Cargo;
	private HashMap<Integer, Integer> Pairs;
	int place;

	public ParsePrepString(String in)
	{
		String clean = in.replaceAll("\\[.*?\\]", "");
		//System.out.println(clean);
		this.Cargo = clean.toCharArray();
		this.Pairs = new HashMap<Integer, Integer>();
		this.place = -1;

		/*
		 * Because ' ' does not have a recognizable closer (because durr), this should cause the method to run until it hits the end of the array.
		 */
		this.addEndIndex(0);
	}

	private int addEndIndex(int StartIndex)
	{
		int CurrIndex = StartIndex + 1;

		int result = -1;
		boolean FoundFlag = false;

		while (CurrIndex < this.Cargo.length && !FoundFlag)
		{
			char CurrChar = this.Cargo[CurrIndex];

			if (CurrChar == '(')
			{
				CurrIndex = this.addEndIndex(CurrIndex);
			}
			else if (CurrChar == ')')
			{
				result = CurrIndex;
				this.Pairs.put(StartIndex, result);
				FoundFlag = true;
			}

			CurrIndex++;
		}

		return result;
	}

	public int getEndIndex(int StartIndex)
	{
		return this.Pairs.get(StartIndex);
	}

	public char getCharAt(int index)
	{
		return this.Cargo[index];
	}

	public char getNextChar()
	{
		if (this.hasNextChar())
		{
			this.place++;
			char result = this.Cargo[this.place];
			
			//System.out.println(result);
			
			return result;
		}
		else
		{
			throw new NullPointerException();
		}
	}

	public boolean hasNextChar()
	{
		return this.place + 1 < this.Cargo.length;
	}

	/**
	 * Use with caution, algo may not be sound
	 * 
	 * @return
	 */
	public StringBuilder getNextOpenChunk()
	{
		StringBuilder result = new StringBuilder();
		char CurrChar = this.getNextChar();

		while ((CurrChar != '[') || (CurrChar != '{') || (CurrChar != '('))
		{
			result.append(CurrChar);
			CurrChar = this.getNextChar();
		}
		result.append(CurrChar);

		return result;
	}

	public String getNextEnumToken()
	{
		StringBuilder result = new StringBuilder();

		char CurrChar = this.getNextChar();

		while (this.hasNextChar() && !(CurrChar == ConstantAutobot.getArgDelimiter() || CurrChar == ')'))
		{
			result.append(CurrChar);
			CurrChar = this.getNextChar();
		}

		//System.out.println("getNextEnumToken: " + result);
		return result.toString();
	}

	/**
	 * 
	 * @param Start
	 *            represents first index to include
	 * @param End
	 *            represents last index to include, inclusive
	 * @return
	 */
	public StringBuilder getRangeAsStringBuilder(int Start, int End)
	{
		StringBuilder result = new StringBuilder();

		for (int i = Start; i <= End; i++)
		{
			result.append(this.Cargo[i]);
		}

		return result;
	}
}
