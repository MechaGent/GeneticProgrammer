package EnemyLevelScaling.GeneticProgrammer.Mk1;

import java.util.HashMap;
import java.util.LinkedList;

import EnemyLevelScaling.GeneticProgrammer.Mk1.Enums.NodeType;
import EnemyLevelScaling.GeneticProgrammer.Mk1.Nodes.AccAddNode;
import EnemyLevelScaling.GeneticProgrammer.Mk1.Nodes.AccMultNode;
import EnemyLevelScaling.GeneticProgrammer.Mk1.Nodes.AddNode;
import EnemyLevelScaling.GeneticProgrammer.Mk1.Nodes.ConstNode;
import EnemyLevelScaling.GeneticProgrammer.Mk1.Nodes.ExpressionNode;
import EnemyLevelScaling.GeneticProgrammer.Mk1.Nodes.MultNode;
import EnemyLevelScaling.GeneticProgrammer.Mk1.Nodes.PowerNode;
import EnemyLevelScaling.GeneticProgrammer.Mk1.Nodes.VarNode;
import Random.MkNeg01.XorShiftStarRng.Static.XorShiftStar1024;

public class RandoFormula
{
	private Accumulator Acc;
	private ExpressionNode Root;
	private LinkedList<ExpressionNode> WholeTree;
	private LinkedList<ExpressionNode> SwapNodes;
	private HashMap<String, Integer> Inputs;

	private RandoFormula()
	{
		
	}
	
	public RandoFormula(HashMap<String, Integer> inInputs, int TreeSizeDesired)
	{
		this.Inputs = inInputs;
		this.Acc = new Accumulator();
		this.WholeTree = new LinkedList<ExpressionNode>();
		this.SwapNodes = new LinkedList<ExpressionNode>();
		this.Root = this.getRandomInstancedExpression(TreeSizeDesired);
	}
	
	public RandoFormula copyAndMutate()
	{
		RandoFormula result = new RandoFormula();
		result.Acc = new Accumulator();
		result.Root = this.Root.Copy();
		result.WholeTree = this.WholeTree;
		result.Inputs = this.Inputs;
		return result;
	}
	
	public RandoFormula[] PooOutSomeBabies(RandoFormula in)
	{
		RandoFormula[] result = new RandoFormula[2];
		
		int Swap1 = XorShiftStar1024.nextInt(1, this.WholeTree.size() - 1);
		int Swap2 = XorShiftStar1024.nextInt(1, in.WholeTree.size() - 1);
		
		/*
		 * REMEMBER to refresh WholeTree and SwapNodes after mutating and swapping
		 */
		
		return result;
	}

	private ExpressionNode getRandomInstancedExpression(int TreeSizeDesired)
	{
		int TreeVal = TreeSizeDesired;
		LinkedList<ExpressionNode> HotNodes = new LinkedList<ExpressionNode>();
		ExpressionNode result = this.getRandomInstancedNode(NodeType.getRandomOpNodeType());
		HotNodes.add(result);
		TreeVal -= result.getType().getWorth();

		while (HotNodes.size() > 0)
		{
			ExpressionNode CurrNode = HotNodes.pop();
			this.WholeTree.add(CurrNode);
			
			if(!NodeType.isTerminal(CurrNode.getType()))
			{
				this.SwapNodes.add(CurrNode);
			}
			
			ExpressionNode[] KidNodes;

			switch (CurrNode.getType())
			{
			case AccAdd:
			case AccMult:
			{
				KidNodes = new ExpressionNode[1];
				KidNodes[0] = this.getRandomInstancedNode(NodeType.getRandomNodeType(TreeVal));
				HotNodes.add(KidNodes[0]);
				TreeVal -= KidNodes[0].getType().getWorth();
				break;
			}
			case Add:
			case Mult:
			case Power:
			{
				KidNodes = new ExpressionNode[2];
				KidNodes[0] = this.getRandomInstancedNode(NodeType.getRandomNodeType(TreeVal));
				HotNodes.add(KidNodes[0]);
				TreeVal -= KidNodes[0].getType().getWorth();
				KidNodes[1] = this.getRandomInstancedNode(NodeType.getRandomNodeType(TreeVal));
				HotNodes.add(KidNodes[1]);
				TreeVal -= KidNodes[1].getType().getWorth();
				break;
			}
			case Variable:
			case Constant:
			default:
			{
				KidNodes = new ExpressionNode[0];
				break;
			}

			}

			CurrNode.SetInputNodes(KidNodes);
		}

		return result;
	}

	private ExpressionNode getRandomInstancedNode(NodeType in)
	{
		ExpressionNode result;

		switch (in)
		{
		case AccAdd:
		{
			result = new AccAddNode(this.Acc, XorShiftStar1024.flipCoin());
			break;
		}
		case AccMult:
		{
			result = new AccMultNode(this.Acc, XorShiftStar1024.flipCoin());
			break;
		}
		case Add:
		{
			result = new AddNode(XorShiftStar1024.flipCoin());
			break;
		}
		case Constant:
		{
			result = new ConstNode(XorShiftStar1024.nextInt(0, Integer.MAX_VALUE));
			break;
		}
		case Mult:
		{
			result = new MultNode(XorShiftStar1024.flipCoin());
			break;
		}
		case Power:
		{
			result = new PowerNode();
			break;
		}
		case Variable:
		{
			result = new VarNode(XorShiftStar1024.pickString(this.Inputs.keySet().toArray(new String[this.Inputs.size()])), this.Inputs);
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

	public int getResult()
	{
		return this.Root.getValue();
	}
	
	public String getFormula()
	{
		return this.Root.toFormattedString().toString();
	}
	
	public void incrementInput(String Name)
	{
		this.Inputs.put(Name, this.Inputs.get(Name) + 1);
	}
	
	public void setInputValue(String Name, int Value)
	{
		this.Inputs.put(Name, Value);
	}

	public int getResultWithInputsOf(HashMap<String, Integer> inInputs)
	{
		HashMap<String, Integer> Original = this.Inputs;
		this.Inputs = inInputs;
		int result = this.getResult();
		this.Inputs = Original;
		return result;
	}
}
