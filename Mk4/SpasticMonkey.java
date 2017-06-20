package EnemyLevelScaling.GeneticProgrammer.Mk4;

import java.util.LinkedList;

import EnemyLevelScaling.GeneticProgrammer.Mk4.Autobots.RandyAutobot;
import EnemyLevelScaling.GeneticProgrammer.Mk4.Nodes.DubAccumulator;
import EnemyLevelScaling.GeneticProgrammer.Mk4.Nodes.IfElseNode;
import EnemyLevelScaling.GeneticProgrammer.Mk4.Nodes.IntAccumulator;
import EnemyLevelScaling.GeneticProgrammer.Mk4.Nodes.WhileNode;
import EnemyLevelScaling.GeneticProgrammer.Mk4.Nodes.Mathy.AccOpNode;
import EnemyLevelScaling.GeneticProgrammer.Mk4.Nodes.Mathy.OpNode;
import EnemyLevelScaling.GeneticProgrammer.Mk4.Nodes.Standards.FuncNode;
import EnemyLevelScaling.GeneticProgrammer.Mk4.Nodes.Standards.Enums.NodeType;
import EnemyLevelScaling.GeneticProgrammer.Mk4.Nodes.Terminals.DubConstNode;
import EnemyLevelScaling.GeneticProgrammer.Mk4.Nodes.Terminals.IntConstNode;
import EnemyLevelScaling.GeneticProgrammer.Mk4.Nodes.Terminals.LookUpVarNode;
import EnemyLevelScaling.GeneticProgrammer.Mk4.Nodes.Terminals.ReadAcc;

public class SpasticMonkey
{
	private FuncNode Root;
	private IntAccumulator IntAcc;
	private DubAccumulator Result;
	private int Size;

	public SpasticMonkey(int DesiredSize)
	{
		this.IntAcc = new IntAccumulator();
		this.Result = new DubAccumulator();
		this.Root = this.createNode(NodeType.getRandomNonTerminal());
		this.Size = 1;
		LinkedList<FuncNode> HotList = new LinkedList<FuncNode>();
		HotList.add(Root);
		this.Fling(HotList, DesiredSize);
	}

	public int getSize()
	{
		return this.Size;
	}

	private void Fling(LinkedList<FuncNode> HotList, int DesiredSize)
	{
		while (!HotList.isEmpty())
		{
			FuncNode CurrNode = HotList.pop();
			NodeType CurrType = CurrNode.getType();
			FuncNode[] CurrChildren = new FuncNode[CurrType.getNumKids()];

			if (CurrType == NodeType.While)
			{
				CurrChildren[0] = this.createNode(NodeType.getRandomNumberReturner());
				CurrChildren[1] = this.createNode(NodeType.getRandomNumberReturner());
				CurrChildren[2] = this.createNode(NodeType.getRandomVoidReturner());
			}
			else if (CurrType == NodeType.IfElse)
			{
				CurrChildren[0] = this.createNode(NodeType.getRandomNumberReturner());
				CurrChildren[1] = this.createNode(NodeType.getRandomNumberReturner());
				CurrChildren[2] = this.createNode(NodeType.getRandomVoidReturner());
			}
			else
			{
				for (byte i = 0; i < CurrChildren.length; i++)
				{
					CurrChildren[i] = this.createBoundedNode(HotList, DesiredSize);
				}
			}

			CurrNode.setChildren(CurrChildren);
		}
	}
	
	private FuncNode createBoundedNode(LinkedList<FuncNode> HotList, int DesiredSize)
	{
		FuncNode result;
		
		if (this.Size < DesiredSize)
		{
			result = this.createNode(NodeType.getRandomNode());
		}
		else
		{
			result = this.createNode(NodeType.getRandomTerminal());
		}
		
		HotList.add(result);
		this.Size++;
		
		return result;
	}

	private FuncNode createNode(NodeType in)
	{
		FuncNode result = null;

		switch (in)
		{
		case AccOp:
			{
				if (RandyAutobot.flipCoin())
				{
					result = new AccOpNode(this.IntAcc);
				}
				else
				{
					result = new AccOpNode(this.Result);
				}
				break;
			}
		case DubConst:
			{
				result = new DubConstNode();
				break;
			}
		case IfElse:
			{
				result = new IfElseNode();
				break;
			}
		case IntConst:
			{
				result = new IntConstNode();
				break;
			}
		case LookUpVar:
			{
				result = new LookUpVarNode();
				break;
			}
		case Op:
			{
				result = new OpNode();
				break;
			}
		case ReadDubAcc:
			{
				result = new ReadAcc(this.Result);
				break;
			}
		case ReadIntAcc:
			{
				result = new ReadAcc(this.IntAcc);
				break;
			}
		case While:
			{
				result = new WhileNode();
				break;
			}
		default:
			{
				result = null;
				break;
			}
		}

		result.setChildren(new FuncNode[in.getNumKids()]);

		return result;
	}
}
