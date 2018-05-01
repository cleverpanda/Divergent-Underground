package panda.divergentunderground;

import net.minecraftforge.common.config.Configuration;



public class ConfigDivergentUnderground {
	
	public static float hardnessZeroSD;
	public static float hardnessOneSD;
	public static float hardnessTwoSD;
	public static float hardnessThreeSD;
	public static boolean doHardnessSlowdown;
	public static boolean doCompression;
	public static boolean doUpdateSound;
	public static boolean addVanillaOreRockSmelting;
	public static boolean doGemDrops;
	
	public static int colorHardnessZero;
	public static int colorHardnessOne;
	public static int colorHardnessTwo;
	public static int colorHardnessThree;
	
	
	private static final String MINING ="mining";
	private ConfigDivergentUnderground(){DivergentUnderground.logger.info("Loading Config");}
	
	public static void load(Configuration config) {
		config.load();
		
		doHardnessSlowdown = config.getBoolean("DoHardnessSlowdown", MINING, true, "Whether different hardness blocks slow down mining or not. Overrides other config options");
		hardnessZeroSD = config.getFloat("ReductionHardness0", MINING, 0, 0, 32767, "How many times harder to mine a hardness level 0 rock will be");
		hardnessOneSD = config.getFloat("ReductionHardness1", MINING, 1.5f, 0, 32767, "How many times harder to mine a hardness level 1 rock will be");
		hardnessTwoSD = config.getFloat("ReductionHardness2", MINING, 3.0f, 0, 32767, "How many times harder to mine a hardness level 2 rock will be");
		hardnessThreeSD = config.getFloat("ReductionHardness3", MINING, 5.0f, 0, 32767, "How many times harder to mine a hardness level 3 rock will be");
		doCompression = config.getBoolean("DoRockCompression", MINING, true, "Determines if stone should change hardness based on it's surroundings on random ticks");
		doUpdateSound  = config.getBoolean("DoUpdateSound", MINING, true, "Whether or not to play a sound when blocks are compressed or decompressed");
		addVanillaOreRockSmelting = config.getBoolean("addVanillaOreRockSmelting", MINING, true, "Whether or not to add smelting recipes for iron and gold rocks");
		doGemDrops = config.getBoolean("DoUncutGemDrops", MINING, true, "Enable to drop uncut gems");
		colorHardnessZero = Integer.decode(config.getString("ColorMultiplierHardnessZero", MINING, "0xFFFFFF", ""));
		colorHardnessOne = Integer.decode(config.getString("ColorMultiplierHardnessOne", MINING, "0xE5DBD7", ""));
		colorHardnessTwo = Integer.decode(config.getString("ColorMultiplierHardnessTwo", MINING, "0xBFB6B3", ""));
		colorHardnessThree = Integer.decode(config.getString("ColorMultiplierHardnessThree", MINING, "0xA59E9B", ""));
		
		
		if (config.hasChanged()) config.save();

	}
	
	

}
