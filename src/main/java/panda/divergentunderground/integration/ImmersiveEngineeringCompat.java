package panda.divergentunderground.integration;

import blusunrize.immersiveengineering.common.IEContent;
import blusunrize.immersiveengineering.common.blocks.BlockTypes_Ore;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
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

public class ImmersiveEngineeringCompat {

	public static final Block HARD_COPPER = ModBlocks.simply(new BlockHardStone(IEContent.blockOre.getStateFromMeta(BlockTypes_Ore.COPPER.getMeta()),BlockTypes_Ore.COPPER.getMeta()),"ie_hard_copper_ore");
	public static final Item ORE_COPPER = ModItems.makeOre("ie_copper");

	public static final Block HARD_ALUMINUM = ModBlocks.simply(new BlockHardStone(IEContent.blockOre.getStateFromMeta(BlockTypes_Ore.ALUMINUM.getMeta()),BlockTypes_Ore.ALUMINUM.getMeta()),"ie_hard_aluminum_ore");
	public static final Item ORE_ALUMINUM = ModItems.makeOre("ie_aluminum");
	
	public static final Block HARD_LEAD = ModBlocks.simply(new BlockHardStone(IEContent.blockOre.getStateFromMeta(BlockTypes_Ore.LEAD.getMeta()),BlockTypes_Ore.LEAD.getMeta()),"ie_hard_lead_ore");
	public static final Item ORE_LEAD = ModItems.makeOre("ie_lead");
	
	public static final Block HARD_SILVER = ModBlocks.simply(new BlockHardStone(IEContent.blockOre.getStateFromMeta(BlockTypes_Ore.SILVER.getMeta()),BlockTypes_Ore.SILVER.getMeta()),"ie_hard_silver_ore");
	public static final Item ORE_SILVER = ModItems.makeOre("ie_silver");
	
	public static final Block HARD_NICKEL = ModBlocks.simply(new BlockHardStone(IEContent.blockOre.getStateFromMeta(BlockTypes_Ore.NICKEL.getMeta()),BlockTypes_Ore.NICKEL.getMeta()),"ie_hard_nickel_ore");
	public static final Item ORE_NICKEL = ModItems.makeOre("ie_nickel");
	
	public static final Block HARD_URANIUM = ModBlocks.simply(new BlockHardStone(IEContent.blockOre.getStateFromMeta(BlockTypes_Ore.URANIUM.getMeta()),BlockTypes_Ore.URANIUM.getMeta()),"ie_hard_uranium_ore");
	public static final Item ORE_URANIUM = ModItems.makeOre("ie_uranium");
	
	
	public static void init(){

		RockRegistry.addRock(IEContent.blockOre,BlockTypes_Ore.COPPER.getMeta(), ModItems.ROCK_STONE);
		RockRegistry.addRock(IEContent.blockOre,BlockTypes_Ore.ALUMINUM.getMeta(), ModItems.ROCK_STONE);
		RockRegistry.addRock(IEContent.blockOre,BlockTypes_Ore.LEAD.getMeta(), ModItems.ROCK_STONE);
		RockRegistry.addRock(IEContent.blockOre,BlockTypes_Ore.SILVER.getMeta(), ModItems.ROCK_STONE);
		RockRegistry.addRock(IEContent.blockOre,BlockTypes_Ore.NICKEL.getMeta(), ModItems.ROCK_STONE);
		RockRegistry.addRock(IEContent.blockOre,BlockTypes_Ore.URANIUM.getMeta(), ModItems.ROCK_STONE);
			
		OreRegistry.addOre(IEContent.blockOre,BlockTypes_Ore.COPPER.getMeta(), ORE_COPPER);
		OreRegistry.addOre(IEContent.blockOre,BlockTypes_Ore.ALUMINUM.getMeta(),ORE_ALUMINUM);
		OreRegistry.addOre(IEContent.blockOre,BlockTypes_Ore.LEAD.getMeta(), ORE_LEAD);
		OreRegistry.addOre(IEContent.blockOre,BlockTypes_Ore.SILVER.getMeta(), ORE_SILVER);
		OreRegistry.addOre(IEContent.blockOre,BlockTypes_Ore.NICKEL.getMeta(), ORE_NICKEL);
		OreRegistry.addOre(IEContent.blockOre,BlockTypes_Ore.URANIUM.getMeta(), ORE_URANIUM);

		GameRegistry.addSmelting(ORE_COPPER, new ItemStack(IEContent.itemMetal,1,0), 0.3F);
		GameRegistry.addSmelting(ORE_ALUMINUM, new ItemStack(IEContent.itemMetal,1,1), 0.3F);
		GameRegistry.addSmelting(ORE_LEAD, new ItemStack(IEContent.itemMetal,1,2), 0.7F);
		GameRegistry.addSmelting(ORE_SILVER, new ItemStack(IEContent.itemMetal,1,3), 1.0F);
		GameRegistry.addSmelting(ORE_NICKEL, new ItemStack(IEContent.itemMetal,1,4), 1.0F);
		GameRegistry.addSmelting(ORE_URANIUM, new ItemStack(IEContent.itemMetal,1,5), 1.0F);

	}


	public static void registerBlocks(RegistryEvent.Register<Block> event) {
		IForgeRegistry<Block> registry = event.getRegistry();
		
		registry.register(HARD_COPPER);
		registry.register(HARD_ALUMINUM);
		registry.register(HARD_LEAD);
		registry.register(HARD_SILVER);
		registry.register(HARD_NICKEL); 
		registry.register(HARD_URANIUM);
	}


	public static void registerItems(RegistryEvent.Register<Item> event) {
		IForgeRegistry<Item> registry = event.getRegistry();
		
		registry.register(ORE_COPPER);
		registry.register(ORE_ALUMINUM);
		registry.register(ORE_LEAD);
		registry.register(ORE_SILVER);
		registry.register(ORE_NICKEL);
		registry.register(ORE_URANIUM);

		ModItems.registerItemBlock(registry, HARD_COPPER);
		ModItems.registerItemBlock(registry, HARD_ALUMINUM);
		ModItems.registerItemBlock(registry, HARD_LEAD);
		ModItems.registerItemBlock(registry, HARD_SILVER);
		ModItems.registerItemBlock(registry, HARD_NICKEL);
		ModItems.registerItemBlock(registry, HARD_URANIUM);

	}

	public static void registerOreDicts(){
		doDicts("Copper",ORE_COPPER,HARD_COPPER);
		doDicts("Aluminum",ORE_ALUMINUM,HARD_ALUMINUM);
		doDicts("Lead",ORE_LEAD,HARD_LEAD);
		doDicts("Silver",ORE_SILVER,HARD_SILVER);
		doDicts("Nickel",ORE_NICKEL,HARD_NICKEL);
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
		ClientProxy.registerItemModel(ORE_ALUMINUM);
		ClientProxy.registerBlockModel(HARD_ALUMINUM);
		ClientProxy.registerItemModel(ORE_LEAD);
		ClientProxy.registerBlockModel(HARD_LEAD);
		ClientProxy.registerItemModel(ORE_SILVER);
		ClientProxy.registerBlockModel(HARD_SILVER);
		ClientProxy.registerItemModel(ORE_NICKEL);
		ClientProxy.registerBlockModel(HARD_NICKEL);
		ClientProxy.registerItemModel(ORE_URANIUM);
		ClientProxy.registerBlockModel(HARD_URANIUM);
	}

}
