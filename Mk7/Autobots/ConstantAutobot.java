package EnemyLevelScaling.GeneticProgrammer.Mk7.Autobots;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import EnemyLevelScaling.GeneticProgrammer.Mk7.ControlData;

public class ConstantAutobot
{
	private static int DriverStartValue = 11;
	private static int DriverEndValue = 40;
	private static int IntArrayLength = 5;
	private static int DubArrayLength = 5;
	private static int LoopLimiter = 15;
	private static int ConstDeltaMax = 10;
	private static int DesiredTreeSize = 5;
	private static int ConstBounds = 10000;

	private static double MutationChance = 0.1;

	private static char ArgDelimiter = ',';
	private static char SerialDelimiter = '>';
	private static char LineDelimiter = ';';
	private static char BlockOpener = '{';
	private static char BlockCloser = '}';

	private static ControlData Data = null;
	private static boolean VarsAreSet = false;

	private static PrintWriter loggins = null;
	private static String outFileDir = "C:/Users/MechaGent/Documents/GAME STUFF/Warframe/GP_Out.txt";

	public static int getDriverStartValue()
	{
		return DriverStartValue;
	}

	public static int getDriverEndValue()
	{
		return DriverEndValue;
	}

	public static int getIntArrayLength()
	{
		return IntArrayLength;
	}

	public static int getDubArrayLength()
	{
		return DubArrayLength;
	}

	public static int getLoopLimiter()
	{
		return LoopLimiter;
	}

	public static int getConstDeltaMax()
	{
		return ConstDeltaMax;
	}

	public static int getDesiredTreeSize()
	{
		return DesiredTreeSize;
	}

	public static int getConstBounds()
	{
		return ConstBounds;
	}

	public static double getMutationChance()
	{
		return MutationChance;
	}

	public static int[] getNewTestIntArr()
	{
		return new int[IntArrayLength];
	}

	public static double[] getNewTestDubArr()
	{
		return new double[DubArrayLength];
	}

	public static char getArgDelimiter()
	{
		return ArgDelimiter;
	}

	public static char getSerialDelimiter()
	{
		return SerialDelimiter;
	}

	public static char getLineDelimiter()
	{
		return LineDelimiter;
	}

	public static char getBlockOpener()
	{
		return BlockOpener;
	}

	public static char getBlockCloser()
	{
		return BlockCloser;
	}

	public static void initVars(ControlData in)
	{
		Data = in;
		VarsAreSet = true;
	}

	public static int getNumVars()
	{
		if (!VarsAreSet)
		{
			throw new NullPointerException();
		}

		return Data.getNumVars();
	}

	public static String getVarName(int index)
	{
		if (!VarsAreSet)
		{
			throw new NullPointerException();
		}

		return Data.getVarName(index);
	}

	public static int getVarIndex(String name)
	{
		if (!VarsAreSet)
		{
			throw new NullPointerException();
		}

		return Data.getVarIndex(name);
	}

	public static double getVarValue(int index)
	{
		if (!VarsAreSet)
		{
			throw new NullPointerException();
		}

		return Data.getVarValue(index);
	}

	public static int[] getWaveLevels()
	{
		return Data.getLevels();
	}

	public static PrintWriter getLoggins()
	{
		if (loggins == null)
		{
			try
			{
				loggins = new PrintWriter(new BufferedWriter(new FileWriter(outFileDir, true)));
				// loggins.println("the text");
			}
			catch (IOException e)
			{
				//exception handling left as an exercise for the reader
			}
			
			return loggins;
		}
		else
		{
			return loggins;
		}
	}
	
	public static void writeToLoggins(String in)
	{
		if (loggins == null)
		{
			try
			{
				loggins = new PrintWriter(new BufferedWriter(new FileWriter(outFileDir, true)));
				
				DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
				Date date = new Date();
				//System.out.println(dateFormat.format(date)); //2014/08/06 15:59:48
				
				loggins.println("*****New Run started on: " + dateFormat.format(date));
			}
			catch (IOException e)
			{
				//exception handling left as an exercise for the reader
			}
		}
		
		loggins.println(in);
		loggins.flush();
	}
}
