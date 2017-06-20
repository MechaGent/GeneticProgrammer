package EnemyLevelScaling.GeneticProgrammer.Mk7.MonkeyBusiness;

import DataStructures.Linkages.SingleLinkedList.Unsorted.Mk01.SingleLinkedList;
import EnemyLevelScaling.GeneticProgrammer.Mk7.GlobalVarsPool;
import EnemyLevelScaling.GeneticProgrammer.Mk7.ParentRef;
import EnemyLevelScaling.GeneticProgrammer.Mk7.ParsePrepString;
import EnemyLevelScaling.GeneticProgrammer.Mk7.Autobots.ConstantAutobot;
import EnemyLevelScaling.GeneticProgrammer.Mk7.Autobots.HelperAutobot;
import EnemyLevelScaling.GeneticProgrammer.Mk7.Autobots.RandyAutobot;
import EnemyLevelScaling.GeneticProgrammer.Mk7.Enums.NodeType;
import EnemyLevelScaling.GeneticProgrammer.Mk7.Enums.ReturnType;
import EnemyLevelScaling.GeneticProgrammer.Mk7.Enums.OpTypes.BinaryBoolBoolOp;
import EnemyLevelScaling.GeneticProgrammer.Mk7.Enums.OpTypes.BinaryNumBoolOp;
import EnemyLevelScaling.GeneticProgrammer.Mk7.Enums.OpTypes.BinaryNumNumOp;
import EnemyLevelScaling.GeneticProgrammer.Mk7.Enums.OpTypes.UnaryBoolOp;
import EnemyLevelScaling.GeneticProgrammer.Mk7.Enums.OpTypes.UnaryNumOp;
import EnemyLevelScaling.GeneticProgrammer.Mk7.Nodes.AbstractClasses.BaseNode;
import EnemyLevelScaling.GeneticProgrammer.Mk7.Nodes.AbstractClasses.VoidNode;
import EnemyLevelScaling.GeneticProgrammer.Mk7.Nodes.ReturnsBool.BinaryBoolBoolNode;
import EnemyLevelScaling.GeneticProgrammer.Mk7.Nodes.ReturnsBool.BinaryNumBoolNode;
import EnemyLevelScaling.GeneticProgrammer.Mk7.Nodes.ReturnsBool.ConstBoolNode;
import EnemyLevelScaling.GeneticProgrammer.Mk7.Nodes.ReturnsBool.UnaryBoolNode;
import EnemyLevelScaling.GeneticProgrammer.Mk7.Nodes.ReturnsDouble.BinaryNumNumNode;
import EnemyLevelScaling.GeneticProgrammer.Mk7.Nodes.ReturnsDouble.ConstDubNode;
import EnemyLevelScaling.GeneticProgrammer.Mk7.Nodes.ReturnsDouble.DubGetterNode;
import EnemyLevelScaling.GeneticProgrammer.Mk7.Nodes.ReturnsDouble.VarGetterNode;
import EnemyLevelScaling.GeneticProgrammer.Mk7.Nodes.ReturnsDouble.UnaryNumNode;
import EnemyLevelScaling.GeneticProgrammer.Mk7.Nodes.ReturnsInteger.ConstIntNode;
import EnemyLevelScaling.GeneticProgrammer.Mk7.Nodes.ReturnsInteger.IntGetterNode;
import EnemyLevelScaling.GeneticProgrammer.Mk7.Nodes.ReturnsVoid.ConstVoidNode;
import EnemyLevelScaling.GeneticProgrammer.Mk7.Nodes.ReturnsVoid.DubSetterNode;
import EnemyLevelScaling.GeneticProgrammer.Mk7.Nodes.ReturnsVoid.IfElseNode;
import EnemyLevelScaling.GeneticProgrammer.Mk7.Nodes.ReturnsVoid.IntSetterNode;
import EnemyLevelScaling.GeneticProgrammer.Mk7.Nodes.ReturnsVoid.WhileNode;

public class SpasticMonkey
{
	private GlobalVarsPool Vars;
	private VoidNode Root;
	private int NumNonTerminals;

	private int[] Run = null;
	private double Fitness = -1;
	private long ElapsedRunTime;

	public SpasticMonkey(int DesiredSize)
	{
		this.Vars = new GlobalVarsPool();
		this.Root = (VoidNode) this.createNode(RandyAutobot.getRandyNodeTypeFromLegalReturnTypes(ReturnType.Void, false));
		this.NumNonTerminals = 1;

		SingleLinkedList<BaseNode> HotList = new SingleLinkedList<BaseNode>();
		HotList.add(Root);
		this.Fling(HotList, DesiredSize);

		this.getFitness();
	}

	public SpasticMonkey(SpasticMonkey old, boolean ApplyMutateChance)
	{
		this.Vars = new GlobalVarsPool();
		this.Root = (VoidNode) old.Root.cascadeClone(null, this.Vars, ApplyMutateChance);
		this.NumNonTerminals = old.NumNonTerminals;

		this.getFitness();
	}

	private BaseNode createNode(NodeType in)
	{
		BaseNode result;

		switch (in)
		{
		case BinaryBoolBool:
			{
				result = new BinaryBoolBoolNode();
				break;
			}
		case BinaryNumBool:
			{
				result = new BinaryNumBoolNode();
				break;
			}
		case BinaryNumNum:
			{
				result = new BinaryNumNumNode();
				break;
			}
		case ConstBool:
			{
				result = new ConstBoolNode();
				break;
			}
		case ConstDub:
			{
				result = new ConstDubNode();
				break;
			}
		case ConstInt:
			{
				result = new ConstIntNode();
				break;
			}
		case ConstVoid:
			{
				result = new ConstVoidNode();
				break;
			}
		case DubGetter:
			{
				result = new DubGetterNode(this.Vars);
				break;
			}
		case DubSetter:
			{
				result = new DubSetterNode(this.Vars);
				break;
			}
		case VarGetter:
			{
				result = new VarGetterNode();
				break;
			}
		case IfElse:
			{
				result = new IfElseNode();
				break;
			}
		case IntGetter:
			{
				result = new IntGetterNode(this.Vars);
				break;
			}
		case IntSetter:
			{
				result = new IntSetterNode(this.Vars);
				break;
			}
		case UnaryBool:
			{
				result = new UnaryBoolNode();
				break;
			}
		case UnaryNum:
			{
				result = new UnaryNumNode();
				break;
			}
		case While:
			{
				result = new WhileNode();
				break;
			}
		default:
			{
				System.out.println("this should not be");
				result = null;
				break;
			}
		}

		//System.out.println("Created new node: " + in.toString());

		return result;
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

				NodeType SelectedBaseNode = WhiteList[RandyAutobot.getRandyInt(0, WhiteList.length - 1)];
				Children[i] = this.createNode(SelectedBaseNode);
				Children[i].setParent(new ParentRef(CurrBaseNode, i));

				if (!SelectedBaseNode.isTerminal())
				{
					this.NumNonTerminals++;
				}

				HotList.add(Children[i]);
			}

			CurrBaseNode.setChildren(Children);
		}
	}

	public BaseNode getIndexedNodeOfReturnType(int inIndex, int ReturnTypeIndex)
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
				int SubTotal = CurrChild.getTotalOffspringAt(ReturnTypeIndex, true);

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

			if (CurrNode.getReturnType().getIndex() == ReturnTypeIndex)
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

	public int getSize()
	{
		int result = 0;
		int[] rootSize = this.Root.getTotalOffspring(true);
		
		for(int i = 0; i < rootSize.length; i++)
		{
			result += rootSize[i];
		}
		
		return result;
	}

	public int[] getSubSizes()
	{
		return this.Root.getTotalOffspring();
	}

	public long getElapsedRunTime()
	{
		return this.ElapsedRunTime;
	}

	@Override
	public String toString()
	{
		return this.toStringBuilder().toString();
	}

	public StringBuilder toStringBuilder()
	{
		return this.Root.toStringBuilder();
	}

	public StringBuilder toStringBuilder(boolean wantsDiagnosticSyntax)
	{
		return this.Root.toStringBuilder(wantsDiagnosticSyntax);
	}

	public StringBuilder toStringBuilder(StringBuilder in)
	{
		return this.Root.toStringBuilder(in);
	}

	public SpasticMonkey cascadeClone(boolean ApplyMutateChance)
	{
		return new SpasticMonkey(this, ApplyMutateChance);
	}

	public int[] getRun()
	{
		if (this.Run == null)
		{
			int[] result = new int[ConstantAutobot.getDriverEndValue() - ConstantAutobot.getDriverStartValue()];
			long StartTime = System.nanoTime();

			for (int i = 0; i < result.length; i++)
			{
				this.Vars.setDriver(ConstantAutobot.getDriverStartValue() + i);
				this.Root.DooWop();
				result[i] = this.Vars.getAnswer();
			}

			long EndTime = System.nanoTime();
			this.ElapsedRunTime = EndTime - StartTime;
			this.Run = result;
		}

		return this.Run;
	}

	public double getFitness()
	{
		if (this.Fitness == -1)
		{
			double result = 0;
			int[] Experimental = this.getRun();
			int[] GoodData = ConstantAutobot.getWaveLevels();
			int GoodOffset = ConstantAutobot.getDriverStartValue();
			boolean ZeroFlag = true;

			for (int i = 0; i < Experimental.length; i++)
			{
				double Delta = (Experimental[i]) - (GoodData[i+GoodOffset-1]);
				result += Delta * Delta;
				ZeroFlag &= Experimental[i] == 0;
			}

			double RawFitness = result / ((double) Experimental.length);
			double FinalFitness;

			if (ZeroFlag)
			{
				FinalFitness = Double.MAX_VALUE;
			}
			else
			{
				FinalFitness = RawFitness + this.getSize();
				int i = 1;
				
				while(i < Experimental.length && Experimental[i] - Experimental[i-1] == 1)
				{
					i++;
				}
				
				if(i == Experimental.length)
				{
					FinalFitness *= 2;
				}
			}

			this.Fitness = FinalFitness;
			return FinalFitness;
		}
		else
		{
			return this.Fitness;
		}
	}

	public static SpasticMonkey[] cloneAndBone(SpasticMonkey Boner1, SpasticMonkey Boner2, boolean ApplyMutateChance)
	{

		SpasticMonkey[] result = new SpasticMonkey[] {
														Boner1.cascadeClone(ApplyMutateChance),
														Boner2.cascadeClone(ApplyMutateChance) };

		int[] Boner1Size = result[0].getSubSizes();
		int[] Boner2Size = result[1].getSubSizes();
		int[] ComboBonerSize = HelperAutobot.MultiplyArrays(Boner1Size, Boner2Size);

		int ReturnTypeIndex = RandyAutobot.getIndexGivenWeightedUnnormedOdds(ComboBonerSize);
		int Pivot1SubIndex = RandyAutobot.getRandyInt(1, Boner1Size[ReturnTypeIndex]);
		int Pivot2SubIndex = RandyAutobot.getRandyInt(1, Boner2Size[ReturnTypeIndex]);

		BaseNode Pivot1 = result[0].getIndexedNodeOfReturnType(Pivot1SubIndex, ReturnTypeIndex);
		BaseNode Pivot2 = result[1].getIndexedNodeOfReturnType(Pivot2SubIndex, ReturnTypeIndex);
		//System.out.println("Switch subtrees:\n\t" + Pivot1.toStringBuilder().toString() + "\n\t" + Pivot2.toStringBuilder().toString() + "\nof whole trees:\n\t" + result[0].toStringBuilder() + "\n\t" + result[1].toStringBuilder());

		BaseNode.swapParents(Pivot1, Pivot2);

		return result;
	}

	public static BaseNode parseNodeFromString(ParsePrepString in, GlobalVarsPool inVars)
	{
		NodeType result = getNodeTypeFromParsePrepString(in);

		return parseNodeFromString(in, result, inVars);
	}

	private static BaseNode parseNodeFromString(ParsePrepString in, NodeType Type, GlobalVarsPool inVars)
	{
		BaseNode result;

		switch (Type)
		{
		case BinaryBoolBool:
			{
				//Args: enum <>Op, BaseNode Arg1, BaseNode Arg2
				BinaryBoolBoolOp Arg0 = BinaryBoolBoolOp.valueOf(in.getNextEnumToken());
				NodeType Arg1Type = getNodeTypeFromParsePrepString(in);
				BaseNode Arg1 = parseNodeFromString(in, Arg1Type, inVars);
				NodeType Arg2Type = getNodeTypeFromParsePrepString(in);
				BaseNode Arg2 = parseNodeFromString(in, Arg2Type, inVars);
				result = new BinaryBoolBoolNode(Arg0, Arg1, Arg2);
				Arg1.setParent(new ParentRef(result, 0));
				Arg2.setParent(new ParentRef(result, 1));
				break;
			}
		case BinaryNumBool:
			{
				//Args: enum <>Op, BaseNode Arg1, BaseNode Arg2
				BinaryNumBoolOp Arg0 = BinaryNumBoolOp.valueOf(in.getNextEnumToken());
				NodeType Arg1Type = getNodeTypeFromParsePrepString(in);
				BaseNode Arg1 = parseNodeFromString(in, Arg1Type, inVars);
				NodeType Arg2Type = getNodeTypeFromParsePrepString(in);
				BaseNode Arg2 = parseNodeFromString(in, Arg2Type, inVars);
				result = new BinaryNumBoolNode(Arg0, Arg1, Arg2);
				Arg1.setParent(new ParentRef(result, 0));
				Arg2.setParent(new ParentRef(result, 1));
				break;
			}
		case BinaryNumNum:
			{
				//Args: enum <>Op, BaseNode Arg1, BaseNode Arg2
				BinaryNumNumOp Arg0 = BinaryNumNumOp.valueOf(in.getNextEnumToken());
				NodeType Arg1Type = getNodeTypeFromParsePrepString(in);
				BaseNode Arg1 = parseNodeFromString(in, Arg1Type, inVars);
				NodeType Arg2Type = getNodeTypeFromParsePrepString(in);
				BaseNode Arg2 = parseNodeFromString(in, Arg2Type, inVars);
				result = new BinaryNumNumNode(Arg0, Arg1, Arg2);
				Arg1.setParent(new ParentRef(result, 0));
				Arg2.setParent(new ParentRef(result, 1));
				break;
			}
		case ConstBool:
			{
				boolean val = Boolean.parseBoolean(in.getNextEnumToken());
				result = new ConstBoolNode(val);
				break;
			}
		case ConstDub:
			{
				double val = Double.parseDouble(in.getNextEnumToken());
				result = new ConstDubNode(val);
				break;
			}
		case ConstInt:
			{
				int val = Integer.parseInt(in.getNextEnumToken());
				result = new ConstIntNode(val);
				break;
			}
		case ConstVoid:
			{
				String temp = in.getNextEnumToken();
				result = new ConstVoidNode(temp);
				break;
			}
		case DubGetter:
			{
				NodeType Arg1Type = getNodeTypeFromParsePrepString(in);
				BaseNode Arg1 = parseNodeFromString(in, Arg1Type, inVars);
				result = new DubGetterNode(Arg1, inVars);
				Arg1.setParent(new ParentRef(result, 0));
				break;
			}
		case DubSetter:
			{
				NodeType Arg1Type = getNodeTypeFromParsePrepString(in);
				BaseNode Arg1 = parseNodeFromString(in, Arg1Type, inVars);
				NodeType Arg2Type = getNodeTypeFromParsePrepString(in);
				BaseNode Arg2 = parseNodeFromString(in, Arg2Type, inVars);
				NodeType Arg3Type = getNodeTypeFromParsePrepString(in);
				BaseNode Arg3 = parseNodeFromString(in, Arg3Type, inVars);
				result = new DubSetterNode(Arg1, Arg2, Arg3, inVars);
				Arg1.setParent(new ParentRef(result, 0));
				Arg2.setParent(new ParentRef(result, 1));
				Arg3.setParent(new ParentRef(result, 2));
				break;
			}
		case VarGetter:
			{
				int index = ConstantAutobot.getVarIndex(in.getNextEnumToken());
				result = new VarGetterNode(index);
				break;
			}
		case IfElse:
			{
				NodeType Arg1Type = getNodeTypeFromParsePrepString(in);
				BaseNode Arg1 = parseNodeFromString(in, Arg1Type, inVars);
				NodeType Arg2Type = getNodeTypeFromParsePrepString(in);
				BaseNode Arg2 = parseNodeFromString(in, Arg2Type, inVars);
				NodeType Arg3Type = getNodeTypeFromParsePrepString(in);
				BaseNode Arg3 = parseNodeFromString(in, Arg3Type, inVars);
				NodeType Arg4Type = getNodeTypeFromParsePrepString(in);
				BaseNode Arg4 = parseNodeFromString(in, Arg4Type, inVars);
				result = new IfElseNode(Arg1, Arg2, Arg3, Arg4);
				Arg1.setParent(new ParentRef(result, 0));
				Arg2.setParent(new ParentRef(result, 1));
				Arg3.setParent(new ParentRef(result, 2));
				Arg4.setParent(new ParentRef(result, 3));
				break;
			}
		case IntGetter:
			{
				NodeType Arg1Type = getNodeTypeFromParsePrepString(in);
				BaseNode Arg1 = parseNodeFromString(in, Arg1Type, inVars);
				result = new IntGetterNode(Arg1, inVars);
				Arg1.setParent(new ParentRef(result, 0));
				break;
			}
		case IntSetter:
			{
				NodeType Arg1Type = getNodeTypeFromParsePrepString(in);
				BaseNode Arg1 = parseNodeFromString(in, Arg1Type, inVars);
				NodeType Arg2Type = getNodeTypeFromParsePrepString(in);
				BaseNode Arg2 = parseNodeFromString(in, Arg2Type, inVars);
				NodeType Arg3Type = getNodeTypeFromParsePrepString(in);
				BaseNode Arg3 = parseNodeFromString(in, Arg3Type, inVars);
				result = new IntSetterNode(Arg1, Arg2, Arg3, inVars);
				Arg1.setParent(new ParentRef(result, 0));
				Arg2.setParent(new ParentRef(result, 1));
				Arg3.setParent(new ParentRef(result, 2));
				break;
			}
		case UnaryBool:
			{
				UnaryBoolOp Op = UnaryBoolOp.valueOf(in.getNextEnumToken());
				NodeType Arg1Type = getNodeTypeFromParsePrepString(in);
				BaseNode Arg1 = parseNodeFromString(in, Arg1Type, inVars);
				result = new UnaryBoolNode(Op, Arg1);
				Arg1.setParent(new ParentRef(result, 0));
				break;
			}
		case UnaryNum:
			{
				UnaryNumOp Op = UnaryNumOp.valueOf(in.getNextEnumToken());
				NodeType Arg1Type = getNodeTypeFromParsePrepString(in);
				BaseNode Arg1 = parseNodeFromString(in, Arg1Type, inVars);
				result = new UnaryNumNode(Op, Arg1);
				Arg1.setParent(new ParentRef(result, 0));
				break;
			}
		case While:
			{
				NodeType Arg1Type = getNodeTypeFromParsePrepString(in);
				BaseNode Arg1 = parseNodeFromString(in, Arg1Type, inVars);
				NodeType Arg2Type = getNodeTypeFromParsePrepString(in);
				BaseNode Arg2 = parseNodeFromString(in, Arg2Type, inVars);
				NodeType Arg3Type = getNodeTypeFromParsePrepString(in);
				BaseNode Arg3 = parseNodeFromString(in, Arg3Type, inVars);
				result = new WhileNode(Arg1, Arg2, Arg3);
				Arg1.setParent(new ParentRef(result, 0));
				Arg2.setParent(new ParentRef(result, 1));
				Arg3.setParent(new ParentRef(result, 2));
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

	/**
	 * Consumes ParsePrepString up to and including '('.
	 * 
	 * @param in
	 * @return
	 */
	private static NodeType getNodeTypeFromParsePrepString(ParsePrepString in)
	{
		StringBuilder literal = new StringBuilder();
		boolean FoundFlag = false;
		NodeType result = null;

		while (!FoundFlag)
		{
			char CurrChar = in.getNextChar();
			//System.out.println(CurrChar);

			if (CurrChar == '(' || CurrChar == ConstantAutobot.getArgDelimiter() || CurrChar == ')')
			{
				if (literal.length() != 0)
				{
					FoundFlag = true;
					result = NodeType.valueOf(literal.toString());
				}
			}
			else
			{
				literal.append(CurrChar);
			}
		}

		return result;
	}
}
