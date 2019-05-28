package panda.divergentunderground.registries;

import java.util.HashMap;
import java.util.Map;

import akka.japi.Pair;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import panda.divergentunderground.init.ModItems;

public class RockRegistry {
	
	//This class holds the maps of mined block to rock drops
	private static Map<Pair<Block, Integer>,ItemStack> rockTable = new HashMap<>();
	
	
	public static void initRocks(){
		addRock(Blocks.STONE,0, ModItems.ROCK_STONE);
		addRock(Blocks.STONE,1, ModItems.ROCK_GRANITE);
		addRock(Blocks.STONE,3, ModItems.ROCK_DIORITE);
		addRock(Blocks.STONE,5, ModItems.ROCK_ANDESITE);
		
		addRock(Blocks.IRON_ORE, ModItems.ROCK_STONE);
		addRock(Blocks.GOLD_ORE, ModItems.ROCK_STONE);
		addRock(Blocks.COAL_ORE, ModItems.ROCK_STONE);
		addRock(Blocks.LAPIS_ORE, ModItems.ROCK_STONE);
		addRock(Blocks.SANDSTONE,0, ModItems.ROCK_SANDSTONE);
		addRock(Blocks.RED_SANDSTONE,0, ModItems.ROCK_RED_SANDSTONE);
		addRock(Blocks.NETHERRACK, ModItems.ROCK_NETHERRACK);
		addRock(Blocks.END_STONE, ModItems.ROCK_ENDSTONE);
		addRock(Blocks.QUARTZ_ORE, ModItems.ROCK_NETHERRACK);
		addRock(Blocks.EMERALD_ORE, ModItems.ROCK_STONE);
		addRock(Blocks.DIAMOND_ORE, ModItems.ROCK_STONE);
		addRock(Blocks.REDSTONE_ORE, ModItems.ROCK_STONE);
		addRock(Blocks.LIT_REDSTONE_ORE, ModItems.ROCK_STONE);	
	}
	
	public static void addRock(Block input, int i, Item output) {
		addRock(new Pair(input, i), new ItemStack(output,1));
	}

	public static void addRock(Block input, ItemStack output) {
		addRock(new Pair(input, 0), output);
	}

	public static void addRock(Block input, Item output) {
		addRock(new Pair(input, 0), new ItemStack(output));
	}
	
	public static void addRock(Pair input, ItemStack output) {

		if(!rockTable.containsKey(input)){
			rockTable.put(input,output);
		}
	}
	
	public static boolean hasRocks(Pair input){
		return rockTable.containsKey(input);
	}
	
	public static ItemStack getRocks(Pair input) {
		ItemStack result = rockTable.get(input);
		if (result == null)
			return ItemStack.EMPTY;
		return result;
	}

}
