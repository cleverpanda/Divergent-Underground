package panda.divergentunderground.integration;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.common.registry.GameRegistry.ObjectHolder;
import net.minecraftforge.oredict.OreDictionary;
import net.minecraftforge.registries.IForgeRegistry;
import panda.divergentunderground.DivergentUnderground;
import panda.divergentunderground.common.blocks.BlockHardStone;
import panda.divergentunderground.init.ModBlocks;
import panda.divergentunderground.init.ModItems;
import panda.divergentunderground.proxy.ClientProxy;
import panda.divergentunderground.registries.OreRegistry;
import panda.divergentunderground.registries.RockRegistry;
import ic2.core.block.BlockOre;

public class IndustrialCraftCompat {
	
	//@ObjectHolder("ic2:resource") 
	public static final Block IC2_BLOCK = ForgeRegistries.BLOCKS.getValue(new ResourceLocation("ic2:resource"));
	
	public static final Block HARD_COPPER = ModBlocks.simply(new BlockHardStone(IC2_BLOCK.getStateFromMeta(1),1,"ic2:blocks/resource/copper_ore"),"ic2_hard_copper_ore");
	public static final Block HARD_LEAD = ModBlocks.simply(new BlockHardStone(IC2_BLOCK.getStateFromMeta(2),1,"ic2:blocks/resource/lead_ore"),"ic2_hard_lead_ore");
	public static final Block HARD_TIN = ModBlocks.simply(new BlockHardStone(IC2_BLOCK.getStateFromMeta(3),1,"ic2:blocks/resource/tin_ore"),"ic2_hard_tin_ore");

	public static final Block HARD_URANIUM = ModBlocks.simply(new BlockHardStone(IC2_BLOCK.getStateFromMeta(4),1,"ic2:blocks/resource/uranium_ore"),"ic2_hard_uranium_ore");
	
	public static final Item ORE_COPPER = ModItems.makeOre("ic2_copper");
	public static final Item ORE_TIN = ModItems.makeOre("ic2_tin");
	public static final Item ORE_LEAD = ModItems.makeOre("ic2_lead");
	public static final Item ORE_URANIUM = ModItems.makeOre("ic2_uranium");

	public static void init(){
		RockRegistry.addRock(IC2_BLOCK,1, ModItems.ROCK_STONE);
		RockRegistry.addRock(IC2_BLOCK,2, ModItems.ROCK_STONE);
		RockRegistry.addRock(IC2_BLOCK,3, ModItems.ROCK_STONE);
		RockRegistry.addRock(IC2_BLOCK,4, ModItems.ROCK_STONE);
		
		OreRegistry.addOre(IC2_BLOCK,1,  ORE_COPPER);	
		OreRegistry.addOre(IC2_BLOCK,2,  ORE_LEAD);
		OreRegistry.addOre(IC2_BLOCK,3,  ORE_TIN);
		OreRegistry.addOre(IC2_BLOCK,4,  ORE_URANIUM);

		//GameRegistry.addSmelting(ORE_COPPER, ModuleCore.getItems().ingotCopper, 0.5F);
		//GameRegistry.addSmelting(ORE_TIN, ModuleCore.getItems().ingotTin, 0.5F);*/

	}

	public static void registerBlocks(RegistryEvent.Register<Block> event) {
		IForgeRegistry<Block> registry = event.getRegistry();
	
		registry.register(HARD_COPPER);
		registry.register(HARD_TIN);
		registry.register(HARD_LEAD);
		registry.register(HARD_URANIUM);
	}

	public static void registerItems(RegistryEvent.Register<Item> event) {
		IForgeRegistry<Item> registry = event.getRegistry();

		registry.register(ORE_COPPER);
		registry.register(ORE_TIN);
		registry.register(ORE_LEAD);
		registry.register(ORE_URANIUM);

		ModItems.registerItemBlock(registry, HARD_COPPER);
		ModItems.registerItemBlock(registry, HARD_TIN);
		ModItems.registerItemBlock(registry, HARD_LEAD);
		ModItems.registerItemBlock(registry, HARD_URANIUM);
	}

	public static void registerOreDicts(){
		doDicts("Copper",ORE_COPPER,HARD_COPPER);
		doDicts("Tin",ORE_TIN,HARD_TIN);
		doDicts("Lead",ORE_LEAD,HARD_LEAD);
		doDicts("Uranium",ORE_URANIUM,HARD_URANIUM);
	}

	private static void doDicts(String type,Item ore, Block block){
		OreDictionary.registerOre("ore"+type, block);
		OreDictionary.registerOre("ore"+type, ore);
		OreDictionary.registerOre("rockOre"+type,ore);
	}

	public static void registerModels(ModelRegistryEvent event) {
		ClientProxy.registerItemModel(ORE_COPPER);
		ClientProxy.registerBlockModel(HARD_COPPER);
		ClientProxy.registerItemModel(ORE_TIN);
		ClientProxy.registerBlockModel(HARD_TIN);
		ClientProxy.registerItemModel(ORE_LEAD);
		ClientProxy.registerBlockModel(HARD_LEAD);
		ClientProxy.registerItemModel(ORE_URANIUM);
		ClientProxy.registerBlockModel(HARD_URANIUM);
	}

}
