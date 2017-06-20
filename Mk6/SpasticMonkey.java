package EnemyLevelScaling.GeneticProgrammer.Mk6;

import EnemyLevelScaling.GeneticProgrammer.Mk6.Enums.ReturnType;
import EnemyLevelScaling.GeneticProgrammer.Mk6.Nodes.Interfaces.Node;
import EnemyLevelScaling.GeneticProgrammer.Mk6.Enums.NodeType;
import EnemyLevelScaling.GeneticProgrammer.Mk6.Nodes.ConstBool;
import EnemyLevelScaling.GeneticProgrammer.Mk6.Nodes.ConstDub;
import EnemyLevelScaling.GeneticProgrammer.Mk6.Nodes.ConstInt;
import EnemyLevelScaling.GeneticProgrammer.Mk6.Nodes.ConstVoid;
import EnemyLevelScaling.GeneticProgrammer.Mk6.Nodes.DubGetterNode;
import EnemyLevelScaling.GeneticProgrammer.Mk6.Nodes.DubSetterNode;
import EnemyLevelScaling.GeneticProgrammer.Mk6.Nodes.GetVarNode;
import EnemyLevelScaling.GeneticProgrammer.Mk6.Nodes.IfElseNode;
import EnemyLevelScaling.GeneticProgrammer.Mk6.Nodes.IntGetterNode;
import EnemyLevelScaling.GeneticProgrammer.Mk6.Nodes.IntSetterNode;
import EnemyLevelScaling.GeneticProgrammer.Mk6.Nodes.WhileNode;
import EnemyLevelScaling.GeneticProgrammer.Mk6.Nodes.OpNodes.BinaryBoolBool;
import EnemyLevelScaling.GeneticProgrammer.Mk6.Nodes.OpNodes.BinaryNumBool;
import EnemyLevelScaling.GeneticProgrammer.Mk6.Nodes.OpNodes.BinaryNumNum;
import EnemyLevelScaling.GeneticProgrammer.Mk6.Nodes.OpNodes.UnaryBool;
import EnemyLevelScaling.GeneticProgrammer.Mk6.Nodes.OpNodes.UnaryNum;
import EnemyLevelScaling.GeneticProgrammer.Mk6.Autobots.ConstantAutobot;
import EnemyLevelScaling.GeneticProgrammer.Mk6.ParentRef;
import DataStructures.Linkages.SingleLinkedList.Unsorted.Mk01.SingleLinkedList;
import EnemyLevelScaling.GeneticProgrammer.Mk6.GlobalVars;
import EnemyLevelScaling.GeneticProgrammer.Mk6.Autobots.HelperAutobot;
import EnemyLevelScaling.GeneticProgrammer.Mk6.Autobots.RandyAutobot;
import EnemyLevelScaling.GeneticProgrammer.Mk6.Nodes.Interfaces.VoidNode;

public class SpasticMonkey
{
	private GlobalVars Vars;
	private VoidNode Root;
	private int FuncsSize;
	private int Size;

	private SingleLinkedList<Node> BoolGroup;
	private SingleLinkedList<Node> DubGroup;
	private SingleLinkedList<Node> IntGroup;
	private SingleLinkedList<Node> VoidGroup;

	private long TimeElapsed;

	public SpasticMonkey(SpasticMonkey old, boolean ApplyMutationChance)
	{
		this.instantiateGroups();
		this.Vars = new GlobalVars();
		this.Root = (VoidNode) old.Root.cascadeClone(null, this.Vars, ApplyMutationChance);
		this.FuncsSize = old.FuncsSize;
		this.Size = old.Size;
		int TotalNodes = this.updateGroups();

		System.out.println("Size: " + this.Size + "\nTotalNodes: " + TotalNodes);
	}

	public SpasticMonkey(int DesiredSize)
	{
		this.instantiateGroups();
		this.Vars = new GlobalVars();
		this.Root = (VoidNode) this.getNodeFromReturnType(ReturnType.Void, false, false);
		this.Size = 1;
		this.FuncsSize = 1;
		SingleLinkedList<Node> HotList = new SingleLinkedList<Node>();
		HotList.add(Root);
		this.Fling(HotList, DesiredSize);
	}

	public SpasticMonkey()
	{
		this(ConstantAutobot.getDesiredTreeSize());
	}

	private void instantiateGroups()
	{
		this.BoolGroup = new SingleLinkedList<Node>();
		this.DubGroup = new SingleLinkedList<Node>();
		this.IntGroup = new SingleLinkedList<Node>();
		this.VoidGroup = new SingleLinkedList<Node>();
	}

	private int updateGroups()
	{
		SingleLinkedList<Node> HotList = new SingleLinkedList<Node>();
		Node[] RootKids = this.Root.getChildren();

		for (int i = 0; i < RootKids.length; i++)
		{
			HotList.add(RootKids[i]);
		}

		while (!HotList.isEmpty())
		{
			Node CurrNode = HotList.pop();
			this.addNodeToRelevantGroup(CurrNode);
		}

		return this.BoolGroup.size() + this.DubGroup.size() + this.IntGroup.size() + this.VoidGroup.size();
	}

	private Node getNodeFromReturnType(ReturnType in, boolean wantsTerminals, boolean addToGroups)
	{
		Node result = this.getNodeFromNodeType(RandyAutobot.getRandomNodeTypeOfTypes(in, wantsTerminals), false);

		return result;
	}

	private Node getNodeFromReturnType(ReturnType in, boolean wantsTerminals)
	{
		Node result = this.getNodeFromNodeType(RandyAutobot.getRandomNodeTypeOfTypes(in, wantsTerminals));

		return result;
	}

	private Node getNodeFromNodeType(NodeType in)
	{
		return this.getNodeFromNodeType(in, true);
	}

	private Node getNodeFromNodeType(NodeType in, boolean includeInGroups)
	{
		Node result;

		switch (in)
		{
		case BinaryBoolBool:
			{
				result = new BinaryBoolBool();
				break;
			}
		case BinaryNumBool:
			{
				result = new BinaryNumBool();
				break;
			}
		case BinaryNumNum:
			{
				result = new BinaryNumNum();
				break;
			}
		case ConstDub:
			{
				result = new ConstDub();
				break;
			}
		case ConstInt:
			{
				result = new ConstInt();
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
		case GetVar:
			{
				result = new GetVarNode();
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
				result = new UnaryBool(RandyAutobot.getRandyUnaryBoolOp());
				break;
			}
		case UnaryNum:
			{
				result = new UnaryNum(RandyAutobot.getRandyUnaryNumOp());
				break;
			}
		case While:
			{
				result = new WhileNode();
				break;
			}
		case ConstBool:
			{
				result = new ConstBool();
				break;
			}
		case ConstVoid:
			{
				result = new ConstVoid();
				break;
			}
		default:
			{
				System.out.println("SpasticMonkey.getNodeFromNodeType(NodeType in) is being passed a null value");
				result = null;
				break;
			}
		}

		if (includeInGroups)
		{
			this.addNodeToRelevantGroup(result);
		}

		return result;
	}

	private void Fling(SingleLinkedList<Node> HotList, int DesiredSize)
	{
		while (!HotList.isEmpty())
		{
			Node CurrNode = HotList.pop();
			int NumKids = CurrNode.getNumChildren();
			Node[] Children = new Node[NumKids];
			//System.out.println("NumKids:\n\tAccording to int: " + Integer.toString(NumKids) + "\n\tAccording to array: " + Integer.toString(Children.length));

			for (int i = 0; i < Children.length; i++)
			{
				ReturnType[] returners = CurrNode.getExpectedArgumentsFromChildAt(i);
				NodeType[] WhiteList = NodeType.getNodeTypesFromReturnTypes(returners, !(this.FuncsSize < DesiredSize));
				//HelperAutobot.Logger.append(1, "\nCurrent Whitelist: " + HelperAutobot.getStringFromArr(WhiteList));

				NodeType SelectedNode = WhiteList[RandyAutobot.getRandyInt(0, WhiteList.length - 1)];
				Children[i] = this.getNodeFromNodeType(SelectedNode);
				Children[i].setParent(new ParentRef(CurrNode, i));
				this.Size++;

				if (!HelperAutobot.bulkEquals(SelectedNode, ConstantAutobot.getSimpleNodes()))
				{
					this.FuncsSize++;
				}

				HotList.add(Children[i]);
				//HelperAutobot.Logger.append(0, "Spastic Monkey funcSize: " + this.FuncsSize);
				//System.out.println("Children[i] " + Children[i]);
			}

			CurrNode.setChildren(Children);

			//System.out.println("Setting kids as: " + HelperAutobot.getStringFromArr(CurrNode.getChildren()));
		}
	}

	public SingleLinkedList<Node> getGroup(ReturnType in)
	{
		SingleLinkedList<Node> result;

		switch (in)
		{
		case Boolean:
			{
				result = this.BoolGroup;
				break;
			}
		case Double:
			{
				result = this.DubGroup;
				break;
			}
		case Integer:
			{
				result = this.IntGroup;
				break;
			}
		case Void:
			{
				result = this.VoidGroup;
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

	public int getSizeOfGroup(ReturnType in)
	{
		int result;

		switch (in)
		{
		case Boolean:
			{
				result = this.BoolGroup.size();
				break;
			}
		case Double:
			{
				result = this.DubGroup.size();
				break;
			}
		case Integer:
			{
				result = this.IntGroup.size();
				break;
			}
		case Void:
			{
				result = this.VoidGroup.size();
				break;
			}
		default:
			{
				result = 0;
				break;
			}
		}

		return result;
	}

	private void addNodeToRelevantGroup(Node cargo)
	{
		switch (cargo.getReturnType())
		{
		case Boolean:
			{
				this.BoolGroup.add(cargo);
				break;
			}
		case Double:
			{
				this.DubGroup.add(cargo);
				break;
			}
		case Integer:
			{
				this.IntGroup.add(cargo);
				break;
			}
		case Void:
			{
				this.VoidGroup.add(cargo);
				break;
			}
		}

		//System.out.println(this + " Node Added to: " + cargo.getReturnType() + " with size now: " + this.getSizeOfGroup(cargo.getReturnType()));
	}

	public int[] getRun()
	{
		//System.out.println("poop");
		int[] result = new int[ConstantAutobot.getDriverEndValue() - ConstantAutobot.getDriverStartValue()];
		long StartTime = System.nanoTime();
		
		

		for (int i = 0; i < result.length; i++)
		{
			this.Vars.setDriver(ConstantAutobot.getDriverStartValue() + i);
			this.Root.DooWop();
			result[i] = this.Vars.getAnswer();
		}

		long EndTime = System.nanoTime();
		this.TimeElapsed = EndTime - StartTime;

		return result;
	}

	public long getElapsedTime()
	{
		return this.TimeElapsed;
	}

	public String getSkeleton()
	{
		return "Size: " + this.Size + "\nSkeleton: " + this.Root.toStringBuilder(new StringBuilder()).toString();
	}

	public String getMeat()
	{
		return "Raw results: " + HelperAutobot.getStringFromArr(this.getRun()) + "\nElapsed Time: " + HelperAutobot.convertNanosecondsToSeconds(this.getElapsedTime());
	}

	public String getMeatAndSkeleton()
	{
		return "{\nSize: " + this.Size + "\nSkeleton: " + this.Root.toStringBuilder(new StringBuilder()).toString() + "\nRaw results: " + HelperAutobot.getStringFromArr(this.getRun()) + "\nElapsed Time: " + HelperAutobot.convertNanosecondsToSeconds(this.getElapsedTime()) + "\n}";
	}

	/*
	public SpasticMonkey[] MakeBabies(SpasticMonkey inMonkey2)
	{
		SpasticMonkey Monkey1 = new SpasticMonkey(this, true);
		SpasticMonkey Monkey2 = new SpasticMonkey(inMonkey2, true);

		/*
		 * int[] unNormed = new int[4]; unNormed[0] = getUnNormedValue(Monkey1, Monkey2, ReturnType.Boolean); unNormed[1] = getUnNormedValue(Monkey1, Monkey2, ReturnType.Double); unNormed[2] = getUnNormedValue(Monkey1, Monkey2, ReturnType.Integer); unNormed[3] = getUnNormedValue(Monkey1, Monkey2, ReturnType.Void);

		int[] unNormed = getUnNormedValues(inMonkey2);
		ReturnType[] RetNames = new ReturnType[] {
													ReturnType.Boolean,
													ReturnType.Double,
													ReturnType.Integer,
													ReturnType.Void };

		ReturnType SwapType = RandyAutobot.getWeightedRandomReturnType(RetNames, unNormed);

		SpasticMonkey[] result = new SpasticMonkey[] {
														Monkey1,
														Monkey2 };

		if (SwapType != null)
		{
			Node MonkeyBits1 = RandyAutobot.getRandyNodeFromList(Monkey1.getGroup(SwapType));
			Node MonkeyBits2 = RandyAutobot.getRandyNodeFromList(Monkey2.getGroup(SwapType));

			ParentRef tempRef = MonkeyBits2.getParentRef();
			MonkeyBits2.setParent(MonkeyBits1.getParentRef());
			MonkeyBits1.setParent(tempRef);

			System.out.println("SwapType: " + SwapType);
		}
		else
		{
			System.out.println("SwapType was null");
		}

		return result;
	}
	*/

	public SpasticMonkey[] MakeBabies(SpasticMonkey inMonkey2)
	{
		SpasticMonkey Monkey1 = new SpasticMonkey(this, true);
		SpasticMonkey Monkey2 = new SpasticMonkey(inMonkey2, true);

		System.out.println("Monkey1: " + Monkey1.getSkeleton() + "\nMonkey2: " + Monkey2.getSkeleton());
		
		//Combining node totals, to find swap points
		int[] unNormedValues = new int[ReturnType.getNumElements()];
		int[] Monkey1Vals = Monkey1.Root.getTotalOffspring();
		int[] Monkey2Vals = Monkey2.Root.getTotalOffspring();

		for (int i = 0; i < unNormedValues.length; i++)
		{
			unNormedValues[i] = Monkey1Vals[i] * Monkey2Vals[i];
		}

		ReturnType SwapType = RandyAutobot.getWeightedRandomReturnType(ReturnType.values(), unNormedValues);

		if (SwapType != null)
		{
			System.out.println("SwapType: " + SwapType);
			Node MonkeyBits1 = RandyAutobot.getRandyNodeFromList(Monkey1.getGroup(SwapType));
			Node MonkeyBits2 = RandyAutobot.getRandyNodeFromList(Monkey2.getGroup(SwapType));

			ParentRef tempRef = MonkeyBits2.getParentRef();
			MonkeyBits2.setParent(MonkeyBits1.getParentRef());
			MonkeyBits1.setParent(tempRef);
		}
		else
		{
			System.out.println("SwapType was null");
		}

		return new SpasticMonkey[] {
										Monkey1,
										Monkey2 };
	}

	private int[] getUnNormedValues(SpasticMonkey Monkey2)
	{
		int[] result = new int[4];

		result[0] = this.getSizeOfGroup(ReturnType.Boolean) * Monkey2.getSizeOfGroup(ReturnType.Boolean);
		result[1] = this.getSizeOfGroup(ReturnType.Double) * Monkey2.getSizeOfGroup(ReturnType.Double);
		result[2] = this.getSizeOfGroup(ReturnType.Integer) * Monkey2.getSizeOfGroup(ReturnType.Integer);
		result[3] = this.getSizeOfGroup(ReturnType.Void) * Monkey2.getSizeOfGroup(ReturnType.Void);

		return result;
	}
}
