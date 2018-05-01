package panda.divergentunderground.init;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import net.minecraft.block.Block;
import net.minecraft.init.Items;
import net.minecraft.init.PotionTypes;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.CraftingManager;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.potion.PotionUtils;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.RegistryEvent.Register;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.oredict.OreDictionary;
import net.minecraftforge.registries.IForgeRegistry;
import panda.divergentunderground.ConfigDivergentUnderground;
import panda.divergentunderground.DivergentUnderground;
import panda.divergentunderground.common.crafting.FakeRecipe;

@EventBusSubscriber
public class ModRecipes {
	
	private ModRecipes(){DivergentUnderground.logger.info("Registering Recipes");}

	
	
	public static void register() {
		//removeRecipe(new ResourceLocation("minecraft:leather"));
		
		//rockTable.put(key, value);
		if(ConfigDivergentUnderground.addVanillaOreRockSmelting){
			GameRegistry.addSmelting(ModItems.ORE_IRON, new ItemStack(Items.IRON_INGOT), 0.7f);
			GameRegistry.addSmelting(ModItems.ORE_GOLD, new ItemStack(Items.GOLD_INGOT), 1.0f);
		}
		
	}
	
	@SubscribeEvent
	  public static void registerRecipes(Register<IRecipe> event) {
	    IForgeRegistry<IRecipe> registry = event.getRegistry();

	  }

	public static void removeFurnaceRecipe(ItemStack resultItem) {
		Map<ItemStack, ItemStack> recipes = FurnaceRecipes.instance().getSmeltingList();
		for (Iterator<Map.Entry<ItemStack, ItemStack>> entries = recipes.entrySet().iterator(); entries.hasNext();) {
			Map.Entry<ItemStack, ItemStack> entry = entries.next();
			ItemStack result = entry.getValue();
			if (ItemStack.areItemStacksEqual(result, resultItem)) {
				entries.remove();
			}
		}
	}
	
	  public static void removeRecipe(ResourceLocation name)
	  {
	    IRecipe recipe = CraftingManager.getRecipe(name);
	    if (recipe != null)
	    {
	    	DivergentUnderground.logger.info("Removing Recipe: " + name);
	      ForgeRegistries.RECIPES.register(new FakeRecipe(recipe));
	    }
	  }


	public static void addOredicts(String[] oreDictEntries, Block name) {
		addOredicts(oreDictEntries, new ItemStack(name));
	}

	public static void addOredicts(String[] oreDictEntries, Item name) {
		addOredicts(oreDictEntries, new ItemStack(name));
	}

	public static void addOredicts(String[] oreDictEntries, ItemStack itemStackName) {
		for (final String oreDictEntry : oreDictEntries)
			OreDictionary.registerOre(oreDictEntry, itemStackName);
	}
}