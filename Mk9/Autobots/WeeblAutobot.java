package EnemyLevelScaling.GeneticProgrammer.Mk9.Autobots;

import java.util.HashMap;

import DataStructures.Linkages.SingleLinkedList.Unsorted.Mk01.SingleLinkedList;
import EnemyLevelScaling.GeneticProgrammer.Mk9.ParentRef;
import EnemyLevelScaling.GeneticProgrammer.Mk9.Abstracts.BaseNode;
import EnemyLevelScaling.GeneticProgrammer.Mk9.Abstracts.Recyclable;

public class WeeblAutobot
{
	private static HashMap<Class<?>, SingleLinkedList<Recyclable>> Garbage = new HashMap<Class<?>, SingleLinkedList<Recyclable>>();
	
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
	
	private static SingleLinkedList<Recyclable> requestPotential(Class<?> target)
	{
		SingleLinkedList<Recyclable> Pertinent = Garbage.get(target);

		if (Pertinent == null)
		{
			Pertinent = new SingleLinkedList<Recyclable>();
			Garbage.put(target, Pertinent);
		}

		return Pertinent;
	}
	
	public void Recycle(Recyclable target)
	{
		SingleLinkedList<Recyclable> Pertinent = requestPotential(target.getClass());
		Pertinent.add(target);
	}
}
