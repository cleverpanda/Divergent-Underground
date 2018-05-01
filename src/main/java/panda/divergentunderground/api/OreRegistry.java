package panda.divergentunderground.api;

import java.util.HashMap;
import java.util.Map;

import akka.japi.Pair;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import panda.divergentunderground.DivergentUnderground;
import panda.divergentunderground.init.ModItems;

public class OreRegistry {
	
	//This class holds the maps of mined block to ore drops
	private static Map<Pair<Block, Integer>,ItemStack> oreTable = new HashMap<>();
	
	
	public static void initOres(){

		
		addOre(Blocks.GOLD_ORE, ModItems.ORE_GOLD);
		addOre(Blocks.IRON_ORE, ModItems.ORE_IRON);
		//addOre(Blocks.COAL_ORE, ModItems.ROCK_STONE);
		//addOre(Blocks.LAPIS_ORE, ModItems.ROCK_STONE);
		//addOre(Blocks.QUARTZ_ORE, ModItems.ROCK_NETHERRACK);
		//addOre(Blocks.EMERALD_ORE, ModItems.ROCK_STONE);
		//addOre(Blocks.DIAMOND_ORE, ModItems.ROCK_STONE);
		//addOre(Blocks.REDSTONE_ORE, ModItems.ROCK_STONE);
		
	}
	
	public static void addOre(Block input, int i, Item output) {
		addOre(new Pair(input, i), new ItemStack(output,1));
	}

	public static void addOre(Block input, ItemStack output) {
		addOre(new Pair(input, 0), output);
	}

	public static void addOre(Block input, Item output) {
		addOre(new Pair(input, 0), new ItemStack(output));
	}
	
	public static void addOre(Pair input, ItemStack output) {

		if(!oreTable.containsKey(input)){
			oreTable.put(input,output);
		}
	}
	
	public static boolean hasOres(Pair input){
		return oreTable.containsKey(input);
	}
	
	public static ItemStack getOres(Pair input) {
		ItemStack result = oreTable.get(input);
		if (result.isEmpty())
			return ItemStack.EMPTY;
		return result;
	}

}
