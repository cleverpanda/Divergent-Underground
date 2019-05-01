package panda.divergentunderground.integration;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;
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
import vazkii.quark.world.feature.Basalt;
import vazkii.quark.world.feature.Biotite;
import vazkii.quark.world.feature.RevampStoneGen;
import vazkii.quark.world.feature.UndergroundBiomes;

@CompatibilityPlugin("quark")
public class QuarkCompat implements ICompatibilityPlugin {
	
	private static final String modid = "quark";
	private static final String texturePath = "quark:blocks/";
	
	//public static final Block HARD_BASALT = ModBlocks.makeHardBlock(Basalt.basalt.getDefaultState(),0,texturePath+"stone_basalt",modid,"basalt");
	public static final Block HARD_MARBLE = ModBlocks.makeHardBlock(RevampStoneGen.marble.getDefaultState(),0,texturePath+"stone_marble",modid,"marble");
	public static final Block HARD_LIMESTONE = ModBlocks.makeHardBlock(RevampStoneGen.limestone.getDefaultState(),0,texturePath+"stone_limestone",modid,"limestone");
	
	public static final Item ROCK_BASALT = ModItems.makeRock(modid,"basalt");
	public static final Item ROCK_MARBLE = ModItems.makeRock(modid,"marble");
	public static final Item ROCK_LIMESTONE = ModItems.makeRock(modid,"limestone");
	
	public static final Item ROCK_FIRESTONE = ModItems.makeRock(modid,"firestone");
	public static final Item ROCK_ICYSTONE = ModItems.makeRock(modid,"icystone");
	
	public static final Block BASALT_COBBLE = ModBlocks.simply(new Block(Material.ROCK).setResistance(10f).setHardness(1.5f),modid+"_basalt_cobblestone");
	public static final Block MARBLE_COBBLE = ModBlocks.simply(new Block(Material.ROCK).setResistance(10f).setHardness(1.5f),modid+"_limestone_cobblestone");
	public static final Block LIMESTONE_COBBLE = ModBlocks.simply(new Block(Material.ROCK).setResistance(10f).setHardness(1.5f),modid+"_marble_cobblestone");
	
    @Override
    public void preInit() {
        MinecraftForge.EVENT_BUS.register(this);
    }
	
	@Override
	public void init(){
		RockRegistry.addRock(Basalt.basalt, ROCK_BASALT);
		RockRegistry.addRock(HARD_MARBLE, ROCK_MARBLE);	
		RockRegistry.addRock(HARD_LIMESTONE, ROCK_LIMESTONE);	
		RockRegistry.addRock(RevampStoneGen.limestone,0, ROCK_LIMESTONE);	
		RockRegistry.addRock(RevampStoneGen.marble,0, ROCK_MARBLE);	
		RockRegistry.addRock(Biotite.biotite_ore, ModItems.ROCK_ENDSTONE);
		if(UndergroundBiomes.firestoneEnabled){
			RockRegistry.addRock(UndergroundBiomes.firestoneState.getBlock(),0, ROCK_FIRESTONE);
		}
		if(UndergroundBiomes.icystoneEnabled){
			RockRegistry.addRock(UndergroundBiomes.icystoneState.getBlock(),1, ROCK_ICYSTONE);
		}
		
		GameRegistry.addSmelting(new ItemStack(LIMESTONE_COBBLE), new ItemStack(RevampStoneGen.marble), 0.1f);
		GameRegistry.addSmelting(new ItemStack(BASALT_COBBLE), new ItemStack(Basalt.basalt), 0.1f);
		GameRegistry.addSmelting(new ItemStack(MARBLE_COBBLE), new ItemStack(RevampStoneGen.limestone), 0.1f);
		
	}

	@SubscribeEvent
	public void registerBlocks(RegistryEvent.Register<Block> event) {
		IForgeRegistry<Block> registry = event.getRegistry();
	
		//registry.register(HARD_BASALT);
		registry.register(HARD_MARBLE);
		registry.register(HARD_LIMESTONE);
		registry.register(BASALT_COBBLE);
		registry.register(MARBLE_COBBLE);
		registry.register(LIMESTONE_COBBLE);
	}
	
	@SubscribeEvent
	public void registerItems(RegistryEvent.Register<Item> event) {
		IForgeRegistry<Item> registry = event.getRegistry();

		registry.register(ROCK_BASALT);
		registry.register(ROCK_MARBLE);
		registry.register(ROCK_LIMESTONE);
		registry.register(ROCK_FIRESTONE);
		registry.register(ROCK_ICYSTONE);

		//ModItems.registerItemBlock(registry, HARD_BASALT);
		ModItems.registerItemBlock(registry, HARD_MARBLE);
		ModItems.registerItemBlock(registry, HARD_LIMESTONE);
		ModItems.registerItemBlock(registry, BASALT_COBBLE);
		ModItems.registerItemBlock(registry, MARBLE_COBBLE);
		ModItems.registerItemBlock(registry, LIMESTONE_COBBLE);
		
		OreDictionary.registerOre("rockBasalt",ROCK_BASALT);
		OreDictionary.registerOre("rockIcystone",ROCK_ICYSTONE);
		OreDictionary.registerOre("rockFirestone",ROCK_FIRESTONE);
		OreDictionary.registerOre("rockLimestone",ROCK_MARBLE);
		OreDictionary.registerOre("rockMarble",ROCK_LIMESTONE);
	}
	
	@SideOnly(Side.CLIENT)
	@SubscribeEvent
    public void registerModels(ModelRegistryEvent event) {
		ClientProxy.registerItemModel(ROCK_MARBLE);
		ClientProxy.registerBlockModel(HARD_MARBLE);
		ClientProxy.registerItemModel(ROCK_BASALT);
		//ClientProxy.registerBlockModel(HARD_BASALT);
		ClientProxy.registerItemModel(ROCK_LIMESTONE);
		ClientProxy.registerBlockModel(HARD_LIMESTONE);
		ClientProxy.registerItemModel(ROCK_FIRESTONE);
		ClientProxy.registerItemModel(ROCK_ICYSTONE);
		ClientProxy.registerBlockModel(BASALT_COBBLE);
		ClientProxy.registerBlockModel(MARBLE_COBBLE);
		ClientProxy.registerBlockModel(LIMESTONE_COBBLE);
	}
}

