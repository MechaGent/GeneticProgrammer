package EnemyLevelScaling.GeneticProgrammer.Mk8;

import DataStructures.Linkages.SingleLinkedList.Unsorted.Mk01.SingleLinkedList;
import EnemyLevelScaling.GeneticProgrammer.Mk8.ParentRef;
import EnemyLevelScaling.GeneticProgrammer.Mk8.Autobots.ConstantAutobot;
import EnemyLevelScaling.GeneticProgrammer.Mk8.Autobots.HelperAutobot;
import EnemyLevelScaling.GeneticProgrammer.Mk8.Autobots.RandyAutobot;
import EnemyLevelScaling.GeneticProgrammer.Mk8.Autobots.WeeblAutobot;
import EnemyLevelScaling.GeneticProgrammer.Mk8.Enums.NodeType;
import EnemyLevelScaling.GeneticProgrammer.Mk8.Enums.ReturnType;
import EnemyLevelScaling.GeneticProgrammer.Mk8.Enums.OpTypes.BinBoolBoolOp;
import EnemyLevelScaling.GeneticProgrammer.Mk8.Enums.OpTypes.BinNumBoolOp;
import EnemyLevelScaling.GeneticProgrammer.Mk8.Enums.OpTypes.BinNumNumOp;
import EnemyLevelScaling.GeneticProgrammer.Mk8.Enums.OpTypes.UnaryBoolOp;
import EnemyLevelScaling.GeneticProgrammer.Mk8.Enums.OpTypes.UnaryNumNumOp;
import EnemyLevelScaling.GeneticProgrammer.Mk8.Nodes.AbstractClasses.BaseNode;
import EnemyLevelScaling.GeneticProgrammer.Mk8.Nodes.AbstractClasses.Recyclable;
import EnemyLevelScaling.GeneticProgrammer.Mk8.Nodes.AbstractClasses.VoidNode;
import NeatMath.NeatMath;

public class SpasticMonkey implements Recyclable, Comparable<SpasticMonkey>
{
	private VoidNode Root;
	private GlobalVarsPool Vars;

	private int NumNonTerminals;
	private double[][] Run;
	private boolean RunIsOld;
	private double Fitness;
	private long ElapsedRunTime;

	public SpasticMonkey(int DesiredSize)
	{
		this.Run = null;
		this.RunIsOld = true;
		this.Fitness = -1;
		this.ElapsedRunTime = -1;
		this.Vars = WeeblAutobot.requestNewGlobalVarsPool();
		this.Root = (VoidNode) createNewNode(RandyAutobot.getRandyNodeTypeFromReturnType(ReturnType.Void, false), null, 0);
		this.NumNonTerminals = 1;

		SingleLinkedList<BaseNode> HotList = new SingleLinkedList<BaseNode>();
		HotList.add(Root);
		this.Fling(HotList, DesiredSize);
	}
	
	public SpasticMonkey(SpasticMonkey old)
	{
		this.Run = null;
		this.RunIsOld = true;
		this.Fitness = -1;
		this.ElapsedRunTime = -1;
		this.Vars = WeeblAutobot.requestNewGlobalVarsPool();
		this.NumNonTerminals = 1;
		this.Root = (VoidNode) old.Root.cascadeClone(null, Vars);
	}
	
	@Override
	public void RecycleMe()
	{
		this.Root.cascadeRecycle();
		this.Vars.RecycleMe();
		WeeblAutobot.Recycle(this);
	}
	
	public SpasticMonkey unRecycleMe(int DesiredSize)
	{
		this.RunIsOld = true;
		this.Fitness = -1;
		this.ElapsedRunTime = -1;
		this.Vars = WeeblAutobot.requestNewGlobalVarsPool();
		this.NumNonTerminals = 1;
		this.Root = (VoidNode) createNewNode(RandyAutobot.getRandyNodeTypeFromReturnType(ReturnType.Void, false), null, 0);

		SingleLinkedList<BaseNode> HotList = new SingleLinkedList<BaseNode>();
		HotList.add(Root);
		this.Fling(HotList, DesiredSize);
		return this;
	}
	
	public SpasticMonkey unRecycleMe(SpasticMonkey old)
	{
		this.RunIsOld = true;
		this.Fitness = -1;
		this.ElapsedRunTime = -1;
		this.Vars = WeeblAutobot.requestNewGlobalVarsPool();
		this.NumNonTerminals = 1;
		this.Root = (VoidNode) old.Root.cascadeClone(null, Vars);
		return this;
	}

	private void Fling(SingleLinkedList<BaseNode> HotList, int DesiredSize)
	{
		while (!HotList.isEmpty())
		{
			BaseNode CurrBaseNode = HotList.pop();
			int NumKids = CurrBaseNode.getNumChildren();
			BaseNode[] Children = new BaseNode[NumKids];

			for (int i = 0; i < Children.length; i++)
			{
				ReturnType[] returners = CurrBaseNode.getExpectedArgumentsFromChildAt(i);
				NodeType[] WhiteList = NodeType.getNodeTypesFromReturnTypes(returners, !(this.NumNonTerminals < DesiredSize));

				NodeType SelectedBaseNode = WhiteList[RandyAutobot.getRandyIndex(WhiteList.length)];
				Children[i] = this.createNewNode(SelectedBaseNode, CurrBaseNode, i);

				if (!SelectedBaseNode.isTerminal())
				{
					this.NumNonTerminals++;
				}

				HotList.add(Children[i]);
			}

			CurrBaseNode.setChildren(Children);
		}
	}

	public BaseNode createNewNode(NodeType in, BaseNode Parent, int ChildsIndex)
	{
		BaseNode result;
		ParentRef Raffi = WeeblAutobot.requestNewParentRef(Parent, ChildsIndex);

		switch (in)
		{
		case BinBoolBool:
			{
				result = WeeblAutobot.requestNewBinBoolBoolNode(Raffi, BinBoolBoolOp.getEnumAtIndex(RandyAutobot.getRandyIndex(BinNumNumOp.getNumOps())));
				break;
			}
		case BinNumBool:
			{
				result = WeeblAutobot.requestNewBinNumBoolNode(Raffi, BinNumBoolOp.getEnumAtIndex(RandyAutobot.getRandyIndex(BinNumNumOp.getNumOps())));
				break;
			}
		case BinNumNum:
			{
				result = WeeblAutobot.requestNewBinNumNumNode(Raffi, BinNumNumOp.getEnumAtIndex(RandyAutobot.getRandyIndex(BinNumNumOp.getNumOps())));
				break;
			}
		case ConstBool:
			{
				result = WeeblAutobot.requestNewConstBoolNode(Raffi, RandyAutobot.getRandyBool());
				break;
			}
		case ConstNum:
			{
				result = WeeblAutobot.requestNewConstNumNode(Raffi, RandyAutobot.getRandyNumber(ConstantAutobot.getConstantNumberBounds()));
				break;
			}
		case ConstVoid:
			{
				result = WeeblAutobot.requestNewConstVoidNode(Raffi);
				break;
			}
		case IfElse:
			{
				result = WeeblAutobot.requestNewIfElseNode(Raffi);
				break;
			}
		case NumGetter:
			{
				result = WeeblAutobot.requestNewNumGetterNode(Raffi, this.Vars);
				break;
			}
		case NumSetter:
			{
				result = WeeblAutobot.requestNewNumSetterNode(Raffi, this.Vars);
				break;
			}
		case UnaryBoolBool:
			{
				result = WeeblAutobot.requestNewUnaryBoolNode(Raffi, UnaryBoolOp.getEnumAtIndex(RandyAutobot.getRandyIndex(UnaryBoolOp.getNumOps())));
				break;
			}
		case UnaryNumNum:
			{
				result = WeeblAutobot.requestNewUnaryNumNumNode(Raffi, UnaryNumNumOp.getEnumAtIndex(RandyAutobot.getRandyIndex(UnaryNumNumOp.getNumOps())));
				break;
			}
		case VarGetter:
			{
				result = WeeblAutobot.requestNewVarGetterNode(Raffi, this.Vars, RandyAutobot.getRandyIndex(ConstantAutobot.getNumVars(0)));
				break;
			}
		case While:
			{
				result = WeeblAutobot.requestNewWhileNode(Raffi);
				break;
			}
		default:
			{
				result = null;
				break;
			}
		}

		return result;
	}

	public BaseNode getIndexedNodeOfReturnType(int inIndex, ReturnType inReturn)
	{
		//System.out.println(ReturnTypeIndex+"");
		BaseNode CurrNode = this.Root;
		int locIndex = inIndex - 1; //This is just so the comparisons aren't hybrids, ie "<="
		boolean FoundResult = false;

		while (!FoundResult)
		{
			//System.out.println("starting while loop");

			boolean FoundGoodChild = false;
			int Children = CurrNode.getNumChildren();

			for (int i = 0; !FoundGoodChild && i < Children; i++)
			{
				BaseNode CurrChild = CurrNode.getChildAt(i);
				int SubTotal = CurrChild.getSubTotalOffspring(inReturn);

				if (locIndex < SubTotal)
				{
					CurrNode = CurrChild;
					FoundGoodChild = true;

				}
				else
				{
					locIndex = locIndex - SubTotal;

				}

				//System.out.println("Current Seeking Index:\n\t" + locIndex + "\n\tChild:\n\t" + i + " out of " + Children);
			}

			if (CurrNode.getReturnType() == inReturn)
			{
				if (locIndex == 0)
				{
					FoundResult = true;
				}
				else
				{
					locIndex--;
				}
			}
			else
			{
				//System.out.println(CurrNode + CurrNode.getTotalOffspringAsString());
			}
		}

		return CurrNode;
	}
	
	public int[] getTotalOffspring()
	{
		int[] rawResult = this.Root.getTotalOffspring();
		int[] result = new int[rawResult.length];
		
		for(int i = 0; i < result.length; i++)
		{
			if(i == ReturnType.Void.ordinal())
			{
				result[i] = rawResult[i] - 1;
			}
			else
			{
				result[i] = rawResult[i];
			}
		}
		
		return result;
	}

	public int getSize()
	{
		int result = 0;
		int[] temp = this.Root.getTotalOffspring();

		for (int i = 0; i < temp.length; i++)
		{
			result += temp[i];
		}

		return result;
	}
	
	public long getElapsedRunTime()
	{
		return this.ElapsedRunTime;
	}

	public double[][] getRun()
	{
		if (this.RunIsOld)
		{
			double[][] result = new double[ConstantAutobot.getDriverEndValue() - ConstantAutobot.getDriverStartValue()][ConstantAutobot.getNumTrials()];
			long[] Times = new long[ConstantAutobot.getNumTrials()];

			for (int CurrTrial = 0; CurrTrial < Times.length; CurrTrial++)
			{
				long StartTime = System.nanoTime();
				this.Vars.setTrialNum(CurrTrial);

				for (int i = 0; i < result[CurrTrial].length; i++)
				{
					this.Vars.setDriver(ConstantAutobot.getDriverStartValue() + i);
					this.Root.DooWop();
					result[i][CurrTrial] = this.Vars.getAnswer();
				}

				long EndTime = System.nanoTime();
				Times[CurrTrial] = EndTime - StartTime;
			}
			
			this.Vars.setTrialNum(0);
			this.ElapsedRunTime = NeatMath.getMean(Times);
			this.Run = result;
			this.RunIsOld = false;
		}

		return this.Run;
	}
	
	public double getFitness()
	{
		if(this.Fitness == -1)
		{
			double[][] Eval = this.getRun();
			double result = 0;
			
			for (int CurrTrial = 0; CurrTrial < Eval.length; CurrTrial++)
			{
				double RowResult = 0;
				this.Vars.setTrialNum(CurrTrial);
				
				for (int i = 0; i < Eval[CurrTrial].length; i++)
				{
					this.Vars.setDriver(i + 1);
					double Desired = ConstantAutobot.getVarValue(CurrTrial, i);
					double Actual = ConstantAutobot.getWaveLevelAt(CurrTrial, i);
					double RawError = Desired - Actual;
					
					RowResult += (RawError * RawError) / (Actual * Actual);
				}
				
				result += RowResult;
			}
			
			result = result / ((double) Eval.length);
			
			this.Fitness = result;
		}
		
		return this.Fitness;
	}

	/**
	 * Sorts in ascending order
	 */
	@Override
	public int compareTo(SpasticMonkey o)
	{
		return (int) (this.getFitness() - o.getFitness());
	}
	
	public static SpasticMonkey makeBaby(SpasticMonkey var1, SpasticMonkey var2)
	{
		SpasticMonkey result;
		SpasticMonkey NotTheMomma;
		
		if(RandyAutobot.getRandyBool())
		{
			result = WeeblAutobot.requestNewSpasticMonkey(var1);
			NotTheMomma = var2;
		}
		else
		{
			result = WeeblAutobot.requestNewSpasticMonkey(var2);
			NotTheMomma = var1;
		}
		
		int[] SubMults = HelperAutobot.MultiplyArrays(result.getTotalOffspring(), NotTheMomma.getTotalOffspring());
		ReturnType SwapType = RandyAutobot.getRandyReturnTypeFromUnNormedOdds(SubMults);
		
		int NodeIndex1;
		int NodeIndex2;
		
		//compensating for the fact that RootNode is not summed in
		if(SwapType == ReturnType.Void)
		{
			NodeIndex1 = RandyAutobot.getRandyIndex(result.getTotalOffspring()[ReturnType.getIndexOfReturnType(SwapType)]) + 1;
			NodeIndex2 = RandyAutobot.getRandyIndex(NotTheMomma.getTotalOffspring()[ReturnType.getIndexOfReturnType(SwapType)]) + 1;
		}
		else
		{
			NodeIndex1 = RandyAutobot.getRandyIndex(result.getTotalOffspring()[ReturnType.getIndexOfReturnType(SwapType)]);
			NodeIndex2 = RandyAutobot.getRandyIndex(NotTheMomma.getTotalOffspring()[ReturnType.getIndexOfReturnType(SwapType)]);
		}
		
		BaseNode SwapNode1 = result.getIndexedNodeOfReturnType(NodeIndex1, SwapType);
		BaseNode SwapNode2 = NotTheMomma.getIndexedNodeOfReturnType(NodeIndex2, SwapType)
				.cascadeClone(WeeblAutobot.requestNewParentRef(SwapNode1.getParentRef()), result.Vars);
		SwapNode1.getParentRef().setAtRef(SwapNode2);
		SwapNode1.cascadeRecycle();
		
		return result;
	}
	
	public SpasticMonkey checkForMutation()
	{
		this.Root.applyMutationChance(true);
		return this;
	}
}
