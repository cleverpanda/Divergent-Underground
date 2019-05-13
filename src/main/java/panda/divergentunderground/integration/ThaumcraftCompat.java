package panda.divergentunderground.integration;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.common.registry.GameRegistry.ObjectHolder;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.oredict.OreDictionary;
import net.minecraftforge.registries.IForgeRegistry;
import panda.divergentunderground.ConfigDivergentUnderground;
import panda.divergentunderground.experimental.CompatibilityPlugin;
import panda.divergentunderground.experimental.ICompatibilityPlugin;
import panda.divergentunderground.init.ModBlocks;
import panda.divergentunderground.init.ModItems;
import panda.divergentunderground.proxy.ClientProxy;
import panda.divergentunderground.registries.GemRegistry;
import panda.divergentunderground.registries.OreRegistry;
import panda.divergentunderground.registries.RockRegistry;
import thaumcraft.api.blocks.BlocksTC;
import thaumcraft.api.items.ItemsTC;

//@CompatibilityPlugin("thaumcraft")
public class ThaumcraftCompat implements ICompatibilityPlugin {
	
	private static final String texturePath = "thaumcraft:blocks/";
	private static final String modid = "thaumcraft";
	
	
	@ObjectHolder("thaumcraft:ore_amber")
	public static final Block ore_amber = null; 
	
	@ObjectHolder("thaumcraft:ore_cinnabar")
	public static final Block ore_cinnabar = null; 
	
	@ObjectHolder("thaumcraft:ore_quartz")
	public static final Block ore_quartz = null; 
	
	public static Block HARD_AMBER;// = ModBlocks.makeHardBlock(ore_amber.getDefaultState(),2,texturePath+"ore_amber",modid,"amber");
	public static Block HARD_CINNABAR;// = ModBlocks.makeHardBlock(ore_cinnabar.getDefaultState(),1,texturePath+"ore_cinnabar",modid,"cinnabar");
	public static Block HARD_QUARTZ;// = ModBlocks.makeHardBlock(ore_quartz.getDefaultState(),1,texturePath+"ore_quartz",modid,"quartz");
	
	public static final Item GEM_AMBER = ModItems.makeGem(modid,"amber");
	public static final Item ORE_CINNABAR = ModItems.makeOre(modid,"cinnabar");
	
	@Override
	public void preInit() {
		MinecraftForge.EVENT_BUS.register(this);
	}

	@Override
	public void init(){

		RockRegistry.addRock(BlocksTC.oreAmber,0, ModItems.ROCK_STONE);
		RockRegistry.addRock(BlocksTC.oreCinnabar,0, ModItems.ROCK_STONE);
		RockRegistry.addRock(BlocksTC.oreQuartz,0, ModItems.ROCK_STONE);

		if(ConfigDivergentUnderground.doGemDrops){
		  GemRegistry.addGem(BlocksTC.oreAmber,0, GEM_AMBER);
		}
		OreRegistry.addOre(BlocksTC.oreCinnabar,0, ItemsTC.quicksilver);
		OreRegistry.addOre(BlocksTC.oreQuartz,0, Items.QUARTZ);

		GameRegistry.addSmelting(ORE_CINNABAR, new ItemStack(ItemsTC.quicksilver), 1.0F);
	}

	@SubscribeEvent(priority = EventPriority.LOWEST)
	public static void registerBlocks(RegistryEvent.Register<Block> event) {
		HARD_AMBER = ModBlocks.makeHardBlock(ore_amber.getDefaultState(),2,texturePath+"ore_amber",modid,"amber");
		HARD_CINNABAR = ModBlocks.makeHardBlock(ore_cinnabar.getDefaultState(),1,texturePath+"ore_cinnabar",modid,"cinnabar");
		HARD_QUARTZ = ModBlocks.makeHardBlock(ore_quartz.getDefaultState(),1,texturePath+"ore_quartz",modid,"quartz");
		
		
		IForgeRegistry<Block> registry = event.getRegistry();

		registry.register(HARD_AMBER);
		registry.register(HARD_CINNABAR);
		registry.register(HARD_QUARTZ);
	}

	@SubscribeEvent
	public void registerItems(RegistryEvent.Register<Item> event) {
		IForgeRegistry<Item> registry = event.getRegistry();

		registry.register(GEM_AMBER);
		registry.register(ORE_CINNABAR);

		ModItems.registerItemBlock(registry, HARD_AMBER);
		ModItems.registerItemBlock(registry, HARD_CINNABAR);
		ModItems.registerItemBlock(registry, HARD_QUARTZ);

		OreDictionary.registerOre("uncutAmber", GEM_AMBER);
		OreDictionary.registerOre("oreAmber", HARD_AMBER);
		OreDictionary.registerOre("oreCinnabar", HARD_CINNABAR);
		OreDictionary.registerOre("rockOreCinnabar",ORE_CINNABAR);
		OreDictionary.registerOre("oreQuartz", HARD_QUARTZ);

	}

	@SideOnly(Side.CLIENT)
	@SubscribeEvent
	public void registerModels(ModelRegistryEvent event) {
		ClientProxy.registerItemModel(ORE_CINNABAR);
		ClientProxy.registerItemModel(GEM_AMBER);
		ClientProxy.registerBlockModel(HARD_AMBER);
		ClientProxy.registerBlockModel(HARD_CINNABAR);
		ClientProxy.registerBlockModel(HARD_QUARTZ);
	}
}
