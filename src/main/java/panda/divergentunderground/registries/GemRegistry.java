package panda.divergentunderground.registries;

import java.util.HashMap;
import java.util.Map;

import akka.japi.Pair;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import panda.divergentunderground.init.ModBlocks;
import panda.divergentunderground.init.ModItems;

public class GemRegistry {
	//This class holds the maps of mined block to gem drops
		private static Map<Pair<Block, Integer>,ItemStack> gemTable = new HashMap<>();
		
		
		public static void initGems(){

			
			addGem(Blocks.EMERALD_ORE, ModItems.UNCUT_EMERALD);
			addGem(Blocks.DIAMOND_ORE, ModItems.UNCUT_DIAMOND);	
		}
		
		public static void addGem(Block input, int i, Item output) {
			addGem(new Pair(input, i), new ItemStack(output,1));
		}

		public static void addGem(Block input, ItemStack output) {
			addGem(new Pair(input, 0), output);
		}

		public static void addGem(Block input, Item output) {
			addGem(new Pair(input, 0), new ItemStack(output));
		}
		
		public static void addGem(Pair input, ItemStack output) {

			if(!gemTable.containsKey(input)){
				gemTable.put(input,output);
			}
		}
		
		public static boolean hasGems(Pair input){
			return gemTable.containsKey(input);
		}
		
		public static ItemStack getGems(Pair input) {
			ItemStack result = gemTable.get(input);
			if (result == null)
				return ItemStack.EMPTY;
			return result;
		}

	}
