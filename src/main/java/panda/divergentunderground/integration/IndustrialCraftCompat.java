package panda.divergentunderground.integration;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.oredict.OreDictionary;
import net.minecraftforge.registries.IForgeRegistry;
import panda.divergentunderground.common.blocks.BlockHardStone;
import panda.divergentunderground.init.ModBlocks;
import panda.divergentunderground.init.ModItems;
import panda.divergentunderground.proxy.ClientProxy;
import panda.divergentunderground.registries.OreRegistry;
import panda.divergentunderground.registries.RockRegistry;
//TODO
public class IndustrialCraftCompat {
	
	/*public static final Block HARD_APATITE = ModBlocks.simply(new BlockHardStone(ModuleCore.getBlocks().resources.getStateFromMeta(0),1),"forestry_hard_apatite_ore");
	
	public static final Block HARD_COPPER = ModBlocks.simply(new BlockHardStone(ModuleCore.getBlocks().resources.getStateFromMeta(1),1),"forestry_hard_copper_ore");
	public static final Item ORE_COPPER = ModItems.makeOre("forestry_copper");

	public static final Block HARD_TIN = ModBlocks.simply(new BlockHardStone(ModuleCore.getBlocks().resources.getStateFromMeta(2),1),"forestry_hard_tin_ore");
	public static final Item ORE_TIN = ModItems.makeOre("forestry_tin");*/
	


	public static void init(){

		/*RockRegistry.addRock(ModuleCore.getBlocks().resources,0, ModItems.ROCK_STONE);
		OreRegistry.addOre(ModuleCore.getBlocks().resources,1,  ModItems.ROCK_STONE);	
		OreRegistry.addOre(ModuleCore.getBlocks().resources,2,  ModItems.ROCK_STONE);

		GameRegistry.addSmelting(ORE_COPPER, ModuleCore.getItems().ingotCopper, 0.5F);
		GameRegistry.addSmelting(ORE_TIN, ModuleCore.getItems().ingotTin, 0.5F);*/

	}

	public static void registerBlocks(RegistryEvent.Register<Block> event) {
		IForgeRegistry<Block> registry = event.getRegistry();
/*		
		registry.register(HARD_APATITE);
		registry.register(HARD_COPPER);
		registry.register(HARD_TIN);*/
	}


	public static void registerItems(RegistryEvent.Register<Item> event) {
		IForgeRegistry<Item> registry = event.getRegistry();

/*		registry.register(ORE_COPPER);
		registry.register(ORE_TIN);

		ModItems.registerItemBlock(registry, HARD_COPPER);
		ModItems.registerItemBlock(registry, HARD_TIN);
		ModItems.registerItemBlock(registry, HARD_APATITE);
*/
	}

	public static void registerOreDicts(){
		//doDicts("Copper",ORE_COPPER,HARD_COPPER);
		//doDicts("Tin",ORE_TIN,HARD_TIN);
		//OreDictionary.registerOre("oreApatite", HARD_APATITE);
	}

	private static void doDicts(String type,Item Ore, Block block){
		//OreDictionary.registerOre("ore"+type, block);
		//OreDictionary.registerOre("rockOre"+type,Ore);
	}

	public static void registerModels(ModelRegistryEvent event) {
		//ClientProxy.registerItemModel(ORE_COPPER);
		//ClientProxy.registerBlockModel(HARD_COPPER);
		//ClientProxy.registerItemModel(ORE_TIN);
		//ClientProxy.registerBlockModel(HARD_TIN);

	}

}
