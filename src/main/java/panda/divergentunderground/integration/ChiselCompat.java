package panda.divergentunderground.integration;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.GameRegistry.ObjectHolder;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.oredict.OreDictionary;
import net.minecraftforge.registries.IForgeRegistry;
import panda.divergentunderground.experimental.CompatibilityPlugin;
import panda.divergentunderground.experimental.ICompatibilityPlugin;
import panda.divergentunderground.init.ModBlocks;
import panda.divergentunderground.init.ModItems;
import panda.divergentunderground.proxy.ClientProxy;
import panda.divergentunderground.registries.RockRegistry;
import team.chisel.common.block.BlockCarvable;
import team.chisel.common.init.ChiselBlocks;

@CompatibilityPlugin("chis77el")
public class ChiselCompat implements ICompatibilityPlugin {
	
	private static final String texturePath = "chisel:blocks/";
	private static final String modid = "chisel"; 
	
	@ObjectHolder("chisel:limestone2")
	public static final Block limestone = null; 
	
	@ObjectHolder("chisel:marble2")
	public static final Block marble = null; 
	
	public static Block HARD_MARBLE = null;
	public static Block HARD_LIMESTONE = null;
	
	public static final Item ROCK_MARBLE = ModItems.makeRock(modid,"marble");
	public static final Item ROCK_LIMESTONE = ModItems.makeRock(modid,"limestone");

    @Override
    public void preInit() {
        MinecraftForge.EVENT_BUS.register(this);
    }
	
	@Override
	public void init(){

	}

	@SubscribeEvent(priority = EventPriority.LOWEST)
	public void registerBlocks(RegistryEvent.Register<Block> event) {
		IForgeRegistry<Block> registry = event.getRegistry();
		
		HARD_LIMESTONE = ModBlocks.makeHardBlock(limestone.getDefaultState().withProperty(((BlockCarvable) limestone).getMetaProp(), 7),0,texturePath+"limestone/raw",modid,"limestone");
		HARD_MARBLE = ModBlocks.makeHardBlock(marble.getDefaultState().withProperty(((BlockCarvable) marble).getMetaProp(), 7),0,texturePath+"marble/raw",modid,"marble");
		
			RockRegistry.addRock(HARD_LIMESTONE, ROCK_LIMESTONE);	
			RockRegistry.addRock(limestone,7, ROCK_LIMESTONE);
			
			RockRegistry.addRock(HARD_MARBLE, ROCK_MARBLE);	
			RockRegistry.addRock(marble,7, ROCK_MARBLE);	

		    registry.register(HARD_LIMESTONE);
			registry.register(HARD_MARBLE);
	}
	
	@SubscribeEvent
	public void registerItems(RegistryEvent.Register<Item> event) {
		IForgeRegistry<Item> registry = event.getRegistry();

			registry.register(ROCK_MARBLE);
			ModItems.registerItemBlock(registry, HARD_MARBLE);
			OreDictionary.registerOre("rockMarble",ROCK_MARBLE);
			OreDictionary.registerOre("stoneMarble",ROCK_MARBLE);

			registry.register(ROCK_LIMESTONE);
			ModItems.registerItemBlock(registry, HARD_LIMESTONE);
			OreDictionary.registerOre("rockLimestone",ROCK_LIMESTONE);
			OreDictionary.registerOre("stoneLimestone",ROCK_LIMESTONE);
	}
	
	@SideOnly(Side.CLIENT)
	@SubscribeEvent
    public void registerModels(ModelRegistryEvent event) {

			ClientProxy.registerItemModel(ROCK_MARBLE);
			ClientProxy.registerBlockModel(HARD_MARBLE);

			ClientProxy.registerBlockModel(HARD_LIMESTONE);
			ClientProxy.registerItemModel(ROCK_LIMESTONE);
	}
}