package EnemyLevelScaling.GeneticProgrammer.Mk8.Autobots;

import java.util.HashMap;

import DataStructures.Linkages.SingleLinkedList.Unsorted.Mk01.SingleLinkedList;
import EnemyLevelScaling.GeneticProgrammer.Mk8.GlobalVarsPool;
import EnemyLevelScaling.GeneticProgrammer.Mk8.ParentRef;
import EnemyLevelScaling.GeneticProgrammer.Mk8.SpasticMonkey;
import EnemyLevelScaling.GeneticProgrammer.Mk8.Enums.OpTypes.BinBoolBoolOp;
import EnemyLevelScaling.GeneticProgrammer.Mk8.Enums.OpTypes.BinNumBoolOp;
import EnemyLevelScaling.GeneticProgrammer.Mk8.Enums.OpTypes.BinNumNumOp;
import EnemyLevelScaling.GeneticProgrammer.Mk8.Enums.OpTypes.UnaryBoolOp;
import EnemyLevelScaling.GeneticProgrammer.Mk8.Enums.OpTypes.UnaryNumNumOp;
import EnemyLevelScaling.GeneticProgrammer.Mk8.Nodes.AbstractClasses.BaseNode;
import EnemyLevelScaling.GeneticProgrammer.Mk8.Nodes.AbstractClasses.Recyclable;
import EnemyLevelScaling.GeneticProgrammer.Mk8.Nodes.ReturnsBool.BinBoolBoolNode;
import EnemyLevelScaling.GeneticProgrammer.Mk8.Nodes.ReturnsBool.BinNumBoolNode;
import EnemyLevelScaling.GeneticProgrammer.Mk8.Nodes.ReturnsBool.ConstBoolNode;
import EnemyLevelScaling.GeneticProgrammer.Mk8.Nodes.ReturnsBool.UnaryBoolBoolNode;
import EnemyLevelScaling.GeneticProgrammer.Mk8.Nodes.ReturnsNumber.BinNumNumNode;
import EnemyLevelScaling.GeneticProgrammer.Mk8.Nodes.ReturnsNumber.ConstNumNode;
import EnemyLevelScaling.GeneticProgrammer.Mk8.Nodes.ReturnsNumber.NumGetterNode;
import EnemyLevelScaling.GeneticProgrammer.Mk8.Nodes.ReturnsNumber.UnaryNumNumNode;
import EnemyLevelScaling.GeneticProgrammer.Mk8.Nodes.ReturnsNumber.VarGetterNode;
import EnemyLevelScaling.GeneticProgrammer.Mk8.Nodes.ReturnsVoid.ConstVoidNode;
import EnemyLevelScaling.GeneticProgrammer.Mk8.Nodes.ReturnsVoid.IfElseNode;
import EnemyLevelScaling.GeneticProgrammer.Mk8.Nodes.ReturnsVoid.NumSetterNode;
import EnemyLevelScaling.GeneticProgrammer.Mk8.Nodes.ReturnsVoid.WhileNode;

public class WeeblAutobot
{
	private static HashMap<Class<?>, SingleLinkedList<Recyclable>> Garbage = new HashMap<Class<?>, SingleLinkedList<Recyclable>>();
	
	public static WhileNode requestNewWhileNode(ParentRef inParent)
	{
		SingleLinkedList<Recyclable> Pertinent = requestPotential(WhileNode.class);

		if (Pertinent.size() == 0)
		{
			return new WhileNode(inParent);
		}
		else
		{
			return ((WhileNode) Pertinent.pop()).unRecycleMe(inParent);
		}
	}
	
	public static VarGetterNode requestNewVarGetterNode(ParentRef inParent, GlobalVarsPool inGlobalVars, int inIndex)
	{
		SingleLinkedList<Recyclable> Pertinent = requestPotential(VarGetterNode.class);

		if (Pertinent.size() == 0)
		{
			return new VarGetterNode(inParent, inGlobalVars, inIndex);
		}
		else
		{
			return ((VarGetterNode) Pertinent.pop()).unRecycleMe(inParent, inGlobalVars, inIndex);
		}
	}
	
	public static UnaryNumNumNode requestNewUnaryNumNumNode(ParentRef inParent, UnaryNumNumOp inOp)
	{
		SingleLinkedList<Recyclable> Pertinent = requestPotential(UnaryNumNumNode.class);

		if (Pertinent.size() == 0)
		{
			return new UnaryNumNumNode(inParent, inOp);
		}
		else
		{
			return ((UnaryNumNumNode) Pertinent.pop()).unRecycleMe(inParent, inOp);
		}
	}
	
	public static UnaryBoolBoolNode requestNewUnaryBoolNode(ParentRef inParent, UnaryBoolOp inOp)
	{
		SingleLinkedList<Recyclable> Pertinent = requestPotential(UnaryBoolBoolNode.class);

		if (Pertinent.size() == 0)
		{
			return new UnaryBoolBoolNode(inParent, inOp);
		}
		else
		{
			return ((UnaryBoolBoolNode) Pertinent.pop()).unRecycleMe(inParent, inOp);
		}
	}
	
	public static NumSetterNode requestNewNumSetterNode(ParentRef inParent, GlobalVarsPool inVars)
	{
		SingleLinkedList<Recyclable> Pertinent = requestPotential(NumSetterNode.class);

		if (Pertinent.size() == 0)
		{
			return new NumSetterNode(inParent, inVars);
		}
		else
		{
			return ((NumSetterNode) Pertinent.pop()).unRecycleMe(inParent, inVars);
		}
	}
	
	public static NumGetterNode requestNewNumGetterNode(ParentRef inParent, GlobalVarsPool inVars)
	{
		SingleLinkedList<Recyclable> Pertinent = requestPotential(NumGetterNode.class);

		if (Pertinent.size() == 0)
		{
			return new NumGetterNode(inParent, inVars);
		}
		else
		{
			return ((NumGetterNode) Pertinent.pop()).unRecycleMe(inParent, inVars);
		}
	}
	
	public static IfElseNode requestNewIfElseNode(ParentRef inParent)
	{
		SingleLinkedList<Recyclable> Pertinent = requestPotential(IfElseNode.class);

		if (Pertinent.size() == 0)
		{
			return new IfElseNode(inParent);
		}
		else
		{
			return ((IfElseNode) Pertinent.pop()).unRecycleMe(inParent);
		}
	}
	
	public static ConstVoidNode requestNewConstVoidNode(ParentRef inParent)
	{
		SingleLinkedList<Recyclable> Pertinent = requestPotential(ConstVoidNode.class);

		if (Pertinent.size() == 0)
		{
			return new ConstVoidNode(inParent);
		}
		else
		{
			return ((ConstVoidNode) Pertinent.pop()).unRecycleMe(inParent);
		}
	}
	
	public static ConstNumNode requestNewConstNumNode(ParentRef inParent, double inValue)
	{
		SingleLinkedList<Recyclable> Pertinent = requestPotential(ConstNumNode.class);

		if (Pertinent.size() == 0)
		{
			return new ConstNumNode(inParent, inValue);
		}
		else
		{
			return ((ConstNumNode) Pertinent.pop()).unRecycleMe(inParent, inValue);
		}
	}
	
	public static ConstBoolNode requestNewConstBoolNode(ParentRef inParent, boolean inValue)
	{
		SingleLinkedList<Recyclable> Pertinent = requestPotential(ConstBoolNode.class);

		if (Pertinent.size() == 0)
		{
			return new ConstBoolNode(inParent, inValue);
		}
		else
		{
			return ((ConstBoolNode) Pertinent.pop()).unRecycleMe(inParent, inValue);
		}
	}
	
	public static BinNumNumNode requestNewBinNumNumNode(ParentRef inParent, BinNumNumOp inOp)
	{
		SingleLinkedList<Recyclable> Pertinent = requestPotential(BinNumNumNode.class);

		if (Pertinent.size() == 0)
		{
			return new BinNumNumNode(inParent, inOp);
		}
		else
		{
			return ((BinNumNumNode) Pertinent.pop()).unRecycleMe(inParent, inOp);
		}
	}

	public static BinNumBoolNode requestNewBinNumBoolNode(ParentRef inParent, BinNumBoolOp inOp)
	{
		SingleLinkedList<Recyclable> Pertinent = requestPotential(BinNumBoolNode.class);

		if (Pertinent.size() == 0)
		{
			return new BinNumBoolNode(inParent, inOp);
		}
		else
		{
			return ((BinNumBoolNode) Pertinent.pop()).unRecycleMe(inParent, inOp);
		}
	}

	public static BinBoolBoolNode requestNewBinBoolBoolNode(ParentRef inParent, BinBoolBoolOp inOp)
	{
		SingleLinkedList<Recyclable> Pertinent = requestPotential(BinBoolBoolNode.class);

		if (Pertinent.size() == 0)
		{
			return new BinBoolBoolNode(inParent, inOp);
		}
		else
		{
			return ((BinBoolBoolNode) Pertinent.pop()).unRecycleMe(inParent, inOp);
		}
	}

	public static ParentRef requestNewParentRef(BaseNode inParent, int inIndex)
	{
		SingleLinkedList<Recyclable> Pertinent = requestPotential(ParentRef.class);

		if (Pertinent.size() == 0)
		{
			return new ParentRef(inParent, inIndex);
		}
		else
		{
			return ((ParentRef) Pertinent.pop()).unRecycleMe(inParent, inIndex);
		}
	}
	
	public static ParentRef requestNewParentRef(ParentRef old)
	{
		SingleLinkedList<Recyclable> Pertinent = requestPotential(ParentRef.class);

		if (Pertinent.size() == 0)
		{
			return new ParentRef(old.getParent(), old.getIndex());
		}
		else
		{
			return ((ParentRef) Pertinent.pop()).unRecycleMe(old.getParent(), old.getIndex());
		}
	}
	
	public static GlobalVarsPool requestNewGlobalVarsPool()
	{
		SingleLinkedList<Recyclable> Pertinent = requestPotential(GlobalVarsPool.class);

		if (Pertinent.size() == 0)
		{
			return new GlobalVarsPool();
		}
		else
		{
			return ((GlobalVarsPool) Pertinent.pop()).unRecycleMe();
		}
	}
	
	public static SpasticMonkey requestNewSpasticMonkey(int DesiredSize)
	{
		SingleLinkedList<Recyclable> Pertinent = requestPotential(SpasticMonkey.class);

		if (Pertinent.size() == 0)
		{
			return new SpasticMonkey(DesiredSize);
		}
		else
		{
			return ((SpasticMonkey) Pertinent.pop()).unRecycleMe(DesiredSize);
		}
	}
	
	public static SpasticMonkey requestNewSpasticMonkey(SpasticMonkey old)
	{
		SingleLinkedList<Recyclable> Pertinent = requestPotential(SpasticMonkey.class);

		if (Pertinent.size() == 0)
		{
			return new SpasticMonkey(old);
		}
		else
		{
			return ((SpasticMonkey) Pertinent.pop()).unRecycleMe(old);
		}
	}

	private static SingleLinkedList<Recyclable> requestPotential(Class<?> target)
	{
		SingleLinkedList<Recyclable> Pertinent = Garbage.get(target);

		if (Pertinent == null)
		{
			Garbage.put(target, new SingleLinkedList<Recyclable>());
		}

		return Pertinent;
	}

	public static void Recycle(Recyclable target)
	{
		SingleLinkedList<Recyclable> Pertinent = requestPotential(target.getClass());
		Pertinent.add(target);
	}
}
