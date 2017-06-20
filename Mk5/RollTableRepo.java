package EnemyLevelScaling.GeneticProgrammer.Mk5;

import java.util.LinkedList;

import EnemyLevelScaling.GeneticProgrammer.Mk5.Enums.NodeType;
import EnemyLevelScaling.GeneticProgrammer.Mk5.Enums.ReturnType;
import Masquerade.Mk1.Mask;

public class RollTableRepo
{
	private NodeType[][] RepoAll;
	private NodeType[][] RepoTerm;

	public RollTableRepo()
	{
		int NumVars = ReturnType.values().length;
		this.RepoAll = new NodeType[0][NeatMath.NeatMath.Pow(2, NumVars)];
		this.RepoTerm = new NodeType[0][NeatMath.NeatMath.Pow(2, NumVars)];

		for (byte i = 0; i < this.RepoAll.length; i++)
		{
			LinkedList<NodeType[]> CurrListAll = new LinkedList<NodeType[]>();
			int AllSize = 0;
			LinkedList<NodeType[]> CurrListTerm = new LinkedList<NodeType[]>();
			int TermSize = 0;

			if (Mask.getBoolAt(i, 0))
			{
				NodeType[] CurrAll = ConstantAutobot.getReturnersOfType(ReturnType.Boolean, false);
				NodeType[] CurrTerm = ConstantAutobot.getReturnersOfType(ReturnType.Boolean, true);
				CurrListAll.add(CurrAll);
				CurrListTerm.add(CurrTerm);
				AllSize += CurrAll.length;
				TermSize += CurrTerm.length;
			}
			if (Mask.getBoolAt(i, 1))
			{
				NodeType[] CurrAll = ConstantAutobot.getReturnersOfType(ReturnType.Double, false);
				NodeType[] CurrTerm = ConstantAutobot.getReturnersOfType(ReturnType.Double, true);
				CurrListAll.add(CurrAll);
				CurrListTerm.add(CurrTerm);
				AllSize += CurrAll.length;
				TermSize += CurrTerm.length;
			}
			if (Mask.getBoolAt(i, 2))
			{
				NodeType[] CurrAll = ConstantAutobot.getReturnersOfType(ReturnType.Integer, false);
				NodeType[] CurrTerm = ConstantAutobot.getReturnersOfType(ReturnType.Integer, true);
				CurrListAll.add(CurrAll);
				CurrListTerm.add(CurrTerm);
				AllSize += CurrAll.length;
				TermSize += CurrTerm.length;
			}
			if (Mask.getBoolAt(i, 3))
			{
				NodeType[] CurrAll = ConstantAutobot.getReturnersOfType(ReturnType.Void, false);
				NodeType[] CurrTerm = ConstantAutobot.getReturnersOfType(ReturnType.Void, true);
				CurrListAll.add(CurrAll);
				CurrListTerm.add(CurrTerm);
				AllSize += CurrAll.length;
				TermSize += CurrTerm.length;
			}

			NodeType[] TallyAll = new NodeType[AllSize];
			int AllCounter = 0;

			while (!CurrListAll.isEmpty())
			{
				NodeType[] CurrCargo = CurrListAll.pop();

				for (int j = 0; j < CurrCargo.length; j++)
				{
					TallyAll[AllCounter] = CurrCargo[j];
					AllCounter++;
				}
				
				System.out.println(HelperAutobot.getStringFromArrayOfNodeTypes(CurrCargo));
			}
			
			this.RepoAll[i] = TallyAll;

			NodeType[] TallyTerm = new NodeType[TermSize];
			int TermCounter = 0;

			while (!CurrListTerm.isEmpty())
			{
				NodeType[] CurrCargo = CurrListTerm.pop();

				for (int j = 0; j < CurrCargo.length; j++)
				{
					TallyTerm[TermCounter] = CurrCargo[j];
					TermCounter++;
				}
			}
			
			this.RepoTerm[i] = TallyTerm;
		}
	}

	public NodeType[] getTableFromMask(byte in, boolean wantsTerminalsOnly)
	{
		if(wantsTerminalsOnly)
		{
			return this.RepoTerm[in];
		}
		else
		{
			return this.RepoAll[in];
		}
	}
	
	public NodeType[] getTableFromReturnType(ReturnType in, boolean wantsTerminalsOnly)
	{
		return this.getTableFromMask((byte) this.getIndexOfReturnType(in), wantsTerminalsOnly);
	}
	
	public int getIndexOfReturnType(ReturnType in)
	{
		int result;

		switch (in)
		{
		case Boolean:
			{
				result = 0;
				break;
			}
		case Double:
			{
				result = 1;
				break;
			}
		case Integer:
			{
				result = 2;
				break;
			}
		case Void:
			{
				result = 3;
				break;
			}
		default:
			{
				result = -1;
				break;
			}

		}

		return result;
	}
}
