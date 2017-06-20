package EnemyLevelScaling.GeneticProgrammer.Mk5;

import java.util.HashMap;

import EnemyLevelScaling.GeneticProgrammer.Mk5.Enums.NodeType;
import EnemyLevelScaling.GeneticProgrammer.Mk5.Enums.ReturnType;
import EnemyLevelScaling.GeneticProgrammer.Mk5.Nodes.ConstBool;
import EnemyLevelScaling.GeneticProgrammer.Mk5.Nodes.ConstDub;
import EnemyLevelScaling.GeneticProgrammer.Mk5.Nodes.ConstInt;
import EnemyLevelScaling.GeneticProgrammer.Mk5.Nodes.ConstVoid;
import EnemyLevelScaling.GeneticProgrammer.Mk5.Nodes.DubGetterNode;
import EnemyLevelScaling.GeneticProgrammer.Mk5.Nodes.DubSetterNode;
import EnemyLevelScaling.GeneticProgrammer.Mk5.Nodes.GetVarNode;
import EnemyLevelScaling.GeneticProgrammer.Mk5.Nodes.IfElseNode;
import EnemyLevelScaling.GeneticProgrammer.Mk5.Nodes.IntGetterNode;
import EnemyLevelScaling.GeneticProgrammer.Mk5.Nodes.IntSetterNode;
import EnemyLevelScaling.GeneticProgrammer.Mk5.Nodes.WhileNode;
import EnemyLevelScaling.GeneticProgrammer.Mk5.Nodes.Interfaces.Node;
import EnemyLevelScaling.GeneticProgrammer.Mk5.Nodes.Interfaces.VoidNode;
import EnemyLevelScaling.GeneticProgrammer.Mk5.Nodes.OpNodes.BinaryBoolBool;
import EnemyLevelScaling.GeneticProgrammer.Mk5.Nodes.OpNodes.BinaryNumBool;
import EnemyLevelScaling.GeneticProgrammer.Mk5.Nodes.OpNodes.BinaryNumNum;
import EnemyLevelScaling.GeneticProgrammer.Mk5.Nodes.OpNodes.UnaryBool;
import EnemyLevelScaling.GeneticProgrammer.Mk5.Nodes.OpNodes.UnaryNum;

public class SpasticMonkey
{
	private GlobalVars Vars;
	private VoidNode Root;
	private int Size;
	private HashMap<ReturnType, ChainBelt<Node>> Groups;

	private long TimeElapsed;

	public SpasticMonkey(SpasticMonkey old, boolean ApplyMutateChance)
	{
		this.instantiateGroups();
		this.Vars = new GlobalVars();
		this.Root = (VoidNode) old.Root.cascadeClone(null, this.Vars, ApplyMutateChance);
		this.Size = old.Size;
		this.updateGroups();
	}

	public SpasticMonkey(int DesiredSize)
	{
		this.instantiateGroups();
		this.Vars = new GlobalVars();
		this.Root = (VoidNode) this.getNodeFromReturnType(ReturnType.Void);
		this.Size = 1;
		ChainBelt<Node> HotList = new ChainBelt<Node>();
		HotList.add(Root);
		this.Fling(HotList, DesiredSize);
	}

	private void instantiateGroups()
	{
		this.Groups = new HashMap<ReturnType, ChainBelt<Node>>();
		this.Groups.put(ReturnType.Boolean, new ChainBelt<Node>());
		this.Groups.put(ReturnType.Double, new ChainBelt<Node>());
		this.Groups.put(ReturnType.Integer, new ChainBelt<Node>());
		this.Groups.put(ReturnType.Void, new ChainBelt<Node>());
	}

	private void updateGroups()
	{
		ChainBelt<Node> HotList = new ChainBelt<Node>();
		Node[] RootKids = this.Root.getChildren();

		for (int i = 0; i < RootKids.length; i++)
		{
			HotList.add(RootKids[i]);
		}

		while (!HotList.isEmpty())
		{
			Node CurrNode = HotList.pop();
			this.Groups.get(CurrNode.getReturnType()).add(CurrNode);
		}
	}

	private Node getNodeFromReturnType(ReturnType in)
	{
		return this.getNodeFromNodeType(RandyAutobot.getRandomNodeTypeOfTypes(in));
	}

	private Node getNodeFromNodeType(NodeType in)
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

		return result;
	}

	private void Fling(ChainBelt<Node> HotList, int DesiredSize)
	{
		while (!HotList.isEmpty())
		{
			Node CurrNode = HotList.pop();
			Node[] Children = new Node[CurrNode.getNumChildren()];

			for (int i = 0; i < Children.length; i++)
			{
				ReturnType[] returners = CurrNode.getAllowedReturnTypesForChildAt(i);
				System.out.println("**ReturnTypes: " + HelperAutobot.getStringFromArrayOfReturnTypes(returners));
				ChainBelt<NodeType[]> WhiteList = ConstantAutobot
						.getReturnersOfType(returners, !(this.Size < DesiredSize));

				if (WhiteList.size() > 0)
				{
					NodeType CurrNodeType = RandyAutobot.getRandomNodeTypeOfTypes(WhiteList);
					Children[i] = this.getNodeFromNodeType(CurrNodeType);
					Children[i].setParent(new ParentRef(CurrNode, i));
					Size++;
					HotList.add(Children[i]);
					//System.out.println("Created new Node of type: " + CurrNodeType + " with ParentRef: " + Children[i].getParent().toString());
				}
			}

			CurrNode.setChildren(Children);
		}
	}

	public int[] getRun()
	{
		int[] result = new int[ConstantAutobot.getDriverEndValue() - ConstantAutobot.getDriverStartValue()];
		long StartTime = System.nanoTime();

		for (int i = 0; i < result.length; i++)
		{
			this.Vars.setDriver(ConstantAutobot.getDriverStartValue() + i);
			this.Root.DooWop();
			result[i] = this.Vars.getAnswer();
		}

		this.TimeElapsed = System.nanoTime() - StartTime;

		return result;
	}

	public long getElapsedTime()
	{
		return this.TimeElapsed;
	}

	public String getSkeleton()
	{
		return "Size: " + this.Size + "Skeleton: " + this.Root.toStringBuilder().toString();
	}

	public static SpasticMonkey[] MakeBabies(SpasticMonkey inMonkey1, SpasticMonkey inMonkey2)
	{
		SpasticMonkey Monkey1 = new SpasticMonkey(inMonkey1, true);
		SpasticMonkey Monkey2 = new SpasticMonkey(inMonkey2, true);
		
		int[] unNormed = new int[4];
		unNormed[0] = getUnNormedValue(Monkey1, Monkey2, ReturnType.Boolean);
		unNormed[1] = getUnNormedValue(Monkey1, Monkey2, ReturnType.Double);
		unNormed[2] = getUnNormedValue(Monkey1, Monkey2, ReturnType.Integer);
		unNormed[3] = getUnNormedValue(Monkey1, Monkey2, ReturnType.Void);
		ReturnType[] RetNames = new ReturnType[]{ReturnType.Boolean, ReturnType.Double, ReturnType.Integer, ReturnType.Void};

		ReturnType SwapType = RandyAutobot.getRandyReturnTypeGivenUnNormedAmounts(RetNames, unNormed);

		SpasticMonkey[] result = new SpasticMonkey[] { Monkey1, Monkey2 };

		if (SwapType != null)
		{
			Node MonkeyBits1 = RandyAutobot.getRandyNodeFromListOfArbitraryNodes(Monkey1.Groups.get(SwapType));
			Node MonkeyBits2 = RandyAutobot.getRandyNodeFromListOfArbitraryNodes(Monkey2.Groups.get(SwapType));

			ParentRef tempRef = MonkeyBits2.getParent();
			MonkeyBits2.setParent(MonkeyBits1.getParent());
			MonkeyBits1.setParent(tempRef);
			
			System.out.println("SwapType: " + SwapType);
		}
		else
		{
			System.out.println("SwapType was null");
		}

		return result;
	}

	private static int getUnNormedValue(SpasticMonkey Monkey1, SpasticMonkey Monkey2, ReturnType in)
	{
		ChainBelt<Node> ListA = Monkey1.Groups.get(in);
		ChainBelt<Node> ListB = Monkey2.Groups.get(in);
		
		int a = ListA.size();
		int b = ListB.size();
		int result = a * b;
		
		System.out.println("UnNormed Values for type " + in + ": " + a + " * " + b + " = " + result);
		
		return result;
	}
}
