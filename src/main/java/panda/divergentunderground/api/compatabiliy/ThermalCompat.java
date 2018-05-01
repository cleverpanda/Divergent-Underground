package panda.divergentunderground.api.compatabiliy;

import panda.divergentunderground.DivergentUnderground;
import panda.divergentunderground.api.OreRegistry;
import panda.divergentunderground.api.RockRegistry;
import panda.divergentunderground.common.blocks.BlockHardStone;
import panda.divergentunderground.init.ModBlocks;
import panda.divergentunderground.init.ModItems;
import panda.divergentunderground.proxy.ClientProxy;
import cofh.thermalfoundation.init.TFBlocks;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.oredict.OreDictionary;
import net.minecraftforge.registries.IForgeRegistry;

public class ThermalCompat {
	
	public static final Block HARD_COPPER = ModBlocks.simply(new BlockHardStone(TFBlocks.blockOre.getStateFromMeta(0),1),"thermal_hard_copper_ore");
	public static final Item ORE_COPPER = ModItems.makeOre("thermal_copper");
	
	public static final Block HARD_TIN = ModBlocks.simply(new BlockHardStone(TFBlocks.blockOre.getStateFromMeta(1),1),"thermal_hard_tin_ore");
	public static final Item ORE_TIN = ModItems.makeOre("thermal_tin");
	
	public static final Block HARD_SILVER = ModBlocks.simply(new BlockHardStone(TFBlocks.blockOre.getStateFromMeta(2),1),"thermal_hard_silver_ore");
	public static final Item ORE_SILVER = ModItems.makeOre("thermal_silver");
	
	public static final Block HARD_LEAD = ModBlocks.simply(new BlockHardStone(TFBlocks.blockOre.getStateFromMeta(3),1),"thermal_hard_lead_ore");
	public static final Item ORE_LEAD = ModItems.makeOre("thermal_lead");
	
	public static final Block HARD_ALUMINUM = ModBlocks.simply(new BlockHardStone(TFBlocks.blockOre.getStateFromMeta(4),1),"thermal_hard_aluminum_ore");
	public static final Item ORE_ALUMINUM = ModItems.makeOre("thermal_aluminum");
	
	public static final Block HARD_NICKEL = ModBlocks.simply(new BlockHardStone(TFBlocks.blockOre.getStateFromMeta(5),1),"thermal_hard_nickel_ore");
	public static final Item ORE_NICKEL = ModItems.makeOre("thermal_nickel");
	
	public static final Block HARD_PLATINUM = ModBlocks.simply(new BlockHardStone(TFBlocks.blockOre.getStateFromMeta(6),1),"thermal_hard_platinum_ore");
	public static final Item ORE_PLATINUM = ModItems.makeOre("thermal_platinum");
	
	public static final Block HARD_IRIDIUM = ModBlocks.simply(new BlockHardStone(TFBlocks.blockOre.getStateFromMeta(7),1),"thermal_hard_iridium_ore");
	public static final Item ORE_IRIDIUM = ModItems.makeOre("thermal_iridium");
	
	public static final Block HARD_MITHRIL = ModBlocks.simply(new BlockHardStone(TFBlocks.blockOre.getStateFromMeta(8),1),"thermal_hard_mithril_ore");
	public static final Item ORE_MITHRIL = ModItems.makeOre("thermal_mithril");
	

	
	public static void init(){
		if(DivergentUnderground.Thermalenabled){
			RockRegistry.addRock(TFBlocks.blockOre,0, ModItems.ROCK_STONE);
			RockRegistry.addRock(TFBlocks.blockOre,1, ModItems.ROCK_STONE);
			RockRegistry.addRock(TFBlocks.blockOre,2, ModItems.ROCK_STONE);
			RockRegistry.addRock(TFBlocks.blockOre,3, ModItems.ROCK_STONE);
			RockRegistry.addRock(TFBlocks.blockOre,4, ModItems.ROCK_STONE);
			RockRegistry.addRock(TFBlocks.blockOre,5, ModItems.ROCK_STONE);
			RockRegistry.addRock(TFBlocks.blockOre,6, ModItems.ROCK_STONE);
			RockRegistry.addRock(TFBlocks.blockOre,7, ModItems.ROCK_STONE);
			RockRegistry.addRock(TFBlocks.blockOre,8, ModItems.ROCK_STONE);
			
			OreRegistry.addOre(TFBlocks.blockOre,0, ORE_COPPER);
			OreRegistry.addOre(TFBlocks.blockOre,1, ORE_TIN);
			OreRegistry.addOre(TFBlocks.blockOre,2, ORE_SILVER);
			OreRegistry.addOre(TFBlocks.blockOre,3, ORE_LEAD);
			OreRegistry.addOre(TFBlocks.blockOre,4, ORE_ALUMINUM);
			OreRegistry.addOre(TFBlocks.blockOre,5, ORE_NICKEL);
			OreRegistry.addOre(TFBlocks.blockOre,6, ORE_PLATINUM);
			OreRegistry.addOre(TFBlocks.blockOre,7, ORE_IRIDIUM);
			OreRegistry.addOre(TFBlocks.blockOre,8, ORE_MITHRIL);		
		}
	}


	
	public static void registerBlocks(RegistryEvent.Register<Block> event) {
		IForgeRegistry<Block> registry = event.getRegistry();

		registry.register(HARD_COPPER);
		registry.register(HARD_TIN);
		registry.register(HARD_SILVER);
		registry.register(HARD_LEAD);
		registry.register(HARD_ALUMINUM);
		registry.register(HARD_NICKEL);
		registry.register(HARD_PLATINUM);
		registry.register(HARD_IRIDIUM);
		registry.register(HARD_MITHRIL);
	}
	
	
	public static void registerItems(RegistryEvent.Register<Item> event) {
		IForgeRegistry<Item> registry = event.getRegistry();
		
		registry.register(ORE_COPPER);
		registry.register(ORE_TIN);
		registry.register(ORE_SILVER);
		registry.register(ORE_LEAD);
		registry.register(ORE_ALUMINUM);
		registry.register(ORE_NICKEL);
		registry.register(ORE_PLATINUM);
		registry.register(ORE_IRIDIUM);
		registry.register(ORE_MITHRIL);
		ModItems.registerItemBlock(registry, HARD_COPPER);
		ModItems.registerItemBlock(registry, HARD_TIN);
		ModItems.registerItemBlock(registry, HARD_SILVER);
		ModItems.registerItemBlock(registry, HARD_LEAD);
		ModItems.registerItemBlock(registry, HARD_ALUMINUM);
		ModItems.registerItemBlock(registry, HARD_NICKEL);
		ModItems.registerItemBlock(registry, HARD_PLATINUM);
		ModItems.registerItemBlock(registry, HARD_IRIDIUM);
		ModItems.registerItemBlock(registry, HARD_MITHRIL);
	}
	
    public static void registerOreDicts(){
    	doDicts("Copper",ORE_COPPER,HARD_COPPER);
    	doDicts("Tin",ORE_TIN,HARD_TIN);
    	doDicts("Silver",ORE_SILVER,HARD_SILVER);
    	doDicts("Lead",ORE_LEAD,HARD_LEAD);
    	doDicts("Aluminum",ORE_ALUMINUM,HARD_ALUMINUM);
    	doDicts("Nickel",ORE_NICKEL,HARD_NICKEL);
    	doDicts("Platinum",ORE_PLATINUM,HARD_PLATINUM);
    	doDicts("Iridium",ORE_IRIDIUM,HARD_IRIDIUM);
    	doDicts("Mithril",ORE_MITHRIL,HARD_MITHRIL);
    }
    
    private static void doDicts(String type,Item Ore, Block block){
    	OreDictionary.registerOre("ore"+type, block);
    	OreDictionary.registerOre("rockOre"+type,Ore);
    }
    
    public static void registerModels(ModelRegistryEvent event) {
    	ClientProxy.registerItemModel(ThermalCompat.ORE_COPPER);
    	ClientProxy.registerBlockModel(ThermalCompat.HARD_COPPER);
    	ClientProxy.registerItemModel(ThermalCompat.ORE_TIN);
    	ClientProxy.registerBlockModel(ThermalCompat.HARD_TIN);
    	ClientProxy.registerItemModel(ThermalCompat.ORE_SILVER);
    	ClientProxy.registerBlockModel(ThermalCompat.HARD_SILVER);
    	ClientProxy.registerItemModel(ThermalCompat.ORE_LEAD);
    	ClientProxy.registerBlockModel(ThermalCompat.HARD_LEAD);
    	ClientProxy.registerItemModel(ThermalCompat.ORE_ALUMINUM);
    	ClientProxy.registerBlockModel(ThermalCompat.HARD_ALUMINUM);
    	ClientProxy.registerItemModel(ThermalCompat.ORE_NICKEL);
    	ClientProxy.registerBlockModel(ThermalCompat.HARD_NICKEL);
    	ClientProxy.registerItemModel(ThermalCompat.ORE_PLATINUM);
    	ClientProxy.registerBlockModel(ThermalCompat.HARD_PLATINUM);
    	ClientProxy.registerItemModel(ThermalCompat.ORE_IRIDIUM);
    	ClientProxy.registerBlockModel(ThermalCompat.HARD_IRIDIUM);
    	ClientProxy.registerItemModel(ThermalCompat.ORE_MITHRIL);
    	ClientProxy.registerBlockModel(ThermalCompat.HARD_MITHRIL);
    }
}
