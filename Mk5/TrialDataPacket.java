package EnemyLevelScaling.GeneticProgrammer.Mk5;

import java.util.HashMap;

public class TrialDataPacket
{
	private String Timestamp;
	private String Sector;
	private String Region;
	private String Mission;
	private String Faction;
	private int LvlLow;
	private int LvlHigh;
	private String Tileset;
	private String TilesetSpecifics;
	
	private int[] LvlNorm;
	private int[] LvlExim;
	private int[] TotalKills;
	
	public TrialDataPacket(HashMap<String, Object> in)
	{
		this.Timestamp = (String) in.get("Timestamp");
		this.Sector = (String) in.get("Sector");
		this.Region = (String) in.get("Region");
		this.Mission = (String) in.get("Mission");
		this.Faction = (String) in.get("Faction");
		this.LvlLow = Integer.parseInt((String) in.get("LvlLow"));
		this.LvlHigh = Integer.parseInt((String) in.get("LvlHigh"));
		this.Tileset = (String) in.get("Tileset");
		this.TilesetSpecifics = (String) in.get("TilesetSpecifics");
		
		String[] WaveData = (String[]) in.get("WaveData");
		this.LvlNorm = new int[WaveData.length];
		this.LvlExim = new int[WaveData.length];
		this.TotalKills = new int[WaveData.length];
		
		for(int i = 0; i < WaveData.length; i++)
		{
			String[] CurrLine = WaveData[i].split("\t+");
			
			this.LvlNorm[i] = Integer.parseInt(CurrLine[1]);
			this.LvlExim[i] = Integer.parseInt(CurrLine[2]);
		}
	}
}
