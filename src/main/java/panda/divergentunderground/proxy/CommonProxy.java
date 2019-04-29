package panda.divergentunderground.proxy;

import net.minecraft.item.ItemStack;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.oredict.OreDictionary;
import panda.divergentunderground.DivergentUnderground;
import panda.divergentunderground.init.ModBlocks;
import panda.divergentunderground.init.ModItems;
import panda.divergentunderground.integration.BiomesOPlentyCompat;
import panda.divergentunderground.integration.ForestryCompat;
import panda.divergentunderground.integration.ImmersiveEngineeringCompat;
import panda.divergentunderground.integration.IndustrialCraftCompat;
import panda.divergentunderground.integration.ThermalCompat;

public class CommonProxy {

    public static void registerModels(ModelRegistryEvent event) {}
    public void registerColorHandlers() {
	}
    
    public void registerOreDicts(){
    	OreDictionary.registerOre("toolChisel",new ItemStack(ModItems.CHISEL,1,OreDictionary.WILDCARD_VALUE));
    	OreDictionary.registerOre("oreIron", ModBlocks.HARD_IRON);
    	OreDictionary.registerOre("oreGold", ModBlocks.HARD_GOLD);	
    	OreDictionary.registerOre("oreCoal", ModBlocks.HARD_COAL);
    	OreDictionary.registerOre("oreDiamond", ModBlocks.HARD_DIAMOND);
    	OreDictionary.registerOre("oreEmerald", ModBlocks.HARD_EMERALD);
    	OreDictionary.registerOre("oreLapis", ModBlocks.HARD_LAPIS);
    	OreDictionary.registerOre("oreRedstone", ModBlocks.HARD_REDSTONE);
    	
    	OreDictionary.registerOre("stone", ModBlocks.HARD_STONE);
    	OreDictionary.registerOre("stoneGranite", ModBlocks.HARD_GRANITE);
    	OreDictionary.registerOre("stoneAndesite", ModBlocks.HARD_ANDESITE);
    	OreDictionary.registerOre("stoneDiorite", ModBlocks.HARD_DIORITE);
    	OreDictionary.registerOre("cobblestone", ModBlocks.ANDESITE_COBBLE);
    	OreDictionary.registerOre("cobblestone", ModBlocks.DIORITE_COBBLE);
    	OreDictionary.registerOre("cobblestone", ModBlocks.GRANITE_COBBLE);
    	
    	OreDictionary.registerOre("rock",ModItems.ROCK_STONE);
    	OreDictionary.registerOre("rock",ModItems.ROCK_ANDESITE);
    	OreDictionary.registerOre("rock",ModItems.ROCK_DIORITE);
    	OreDictionary.registerOre("rock",ModItems.ROCK_ENDSTONE);
    	OreDictionary.registerOre("rock",ModItems.ROCK_GRANITE);
    	OreDictionary.registerOre("rock",ModItems.ROCK_NETHERRACK);
    	OreDictionary.registerOre("rock",ModItems.ROCK_RED_SANDSTONE);
    	OreDictionary.registerOre("rock",ModItems.ROCK_SANDSTONE);
    	OreDictionary.registerOre("rockStone",ModItems.ROCK_STONE);
    	OreDictionary.registerOre("rockAndesite",ModItems.ROCK_ANDESITE);
    	OreDictionary.registerOre("rockDiorite",ModItems.ROCK_DIORITE);
    	OreDictionary.registerOre("rockEndstone",ModItems.ROCK_ENDSTONE);
    	OreDictionary.registerOre("rockGranite",ModItems.ROCK_GRANITE);
    	OreDictionary.registerOre("rockNetherrack",ModItems.ROCK_NETHERRACK);
    	OreDictionary.registerOre("rockSandstone",ModItems.ROCK_RED_SANDSTONE);
    	OreDictionary.registerOre("rockSandstone",ModItems.ROCK_SANDSTONE);
    	
    	//OreDictionary.registerOre("rockOreCopper",ModItems.ORE_COPPER);
    	OreDictionary.registerOre("rockOreGold",ModItems.ORE_GOLD);
    	OreDictionary.registerOre("rockOreIron",ModItems.ORE_IRON);
    	
    	OreDictionary.registerOre("uncutDiamond",ModItems.UNCUT_DIAMOND);
    	OreDictionary.registerOre("uncutEmerald",ModItems.UNCUT_EMERALD);
    	if(DivergentUnderground.Thermalenabled){
    		ThermalCompat.registerOreDicts();
		}
    	if(DivergentUnderground.BOPenabled){
			BiomesOPlentyCompat.registerOreDicts();
		}
		if(DivergentUnderground.ImmersiveEngineeringenabled){
			ImmersiveEngineeringCompat.registerOreDicts();
		}
		if(DivergentUnderground.Forestryenabled){
			ForestryCompat.registerOreDicts();
		}
		if(DivergentUnderground.IndustrialCraftenabled){
			IndustrialCraftCompat.registerOreDicts();
		}
    }
}
