package EnemyLevelScaling.GeneticProgrammer.Mk5;

import java.util.HashMap;

import EnemyLevelScaling.GeneticProgrammer.Mk5.Enums.NodeType;
import EnemyLevelScaling.GeneticProgrammer.Mk5.Enums.ReturnType;

public class GeneticProgrammer
{
	public static void main(String[] args)
	{
		HashMap<String, Double> inVars = new HashMap<String, Double>();
		inVars.put("TestVar1", 69.0d);
		inVars.put("TestVar2", -69.0d);
		inVars.put("TestVar3", 96.0d);
		inVars.put("TestVar4", -96.0d);
		ConstantAutobot.setVariables(inVars);
		
		NodeType[] ReturnsBool = ConstantAutobot.getReturnersOfType(ReturnType.Boolean, false);
		
		System.out.println(HelperAutobot.getStringFromArrayOfNodeTypes(ConstantAutobot.getRollTableForMask(ReturnType.Boolean, false)));
		
		SpasticMonkey Monkey1 = new SpasticMonkey(30);
		SpasticMonkey Monkey2 = new SpasticMonkey(30);
		SpasticMonkey[] MonkeyBabies = SpasticMonkey.MakeBabies(Monkey1, Monkey2);
		String m1 = Monkey1.getSkeleton();
		String m2 = Monkey2.getSkeleton();
		String m3 = MonkeyBabies[0].getSkeleton();
		String m4 = MonkeyBabies[1].getSkeleton();
		
		System.out.println(m1 + "\n" + m2 + "\n" + m3 + "\n" + m4);
	}
}
