package panda.divergentunderground.integration;

import forestry.core.ModuleCore;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.oredict.OreDictionary;
import net.minecraftforge.registries.IForgeRegistry;
import panda.divergentunderground.DivergentUnderground;
import panda.divergentunderground.experimental.CompatibilityPlugin;
import panda.divergentunderground.experimental.ICompatibilityPlugin;
import panda.divergentunderground.init.ModBlocks;
import panda.divergentunderground.init.ModItems;
import panda.divergentunderground.proxy.ClientProxy;
import panda.divergentunderground.registries.OreRegistry;
import panda.divergentunderground.registries.RockRegistry;

@CompatibilityPlugin("forestry")
public class ForestryCompat implements ICompatibilityPlugin{

	private static final String modid = "forestry";
	private static final String texturePath = "forestry:blocks/ores/";
	
	public static final Block HARD_APATITE = ModBlocks.makeHardBlock(ModuleCore.getBlocks().resources.getStateFromMeta(0),1,texturePath+"apatite",modid,"apatite");
	
	public static final Block HARD_COPPER = ModBlocks.makeHardBlock(ModuleCore.getBlocks().resources.getStateFromMeta(1),1,texturePath+"copper",modid,"copper");
	public static final Item ORE_COPPER = ModItems.makeOre(modid,"copper");

	public static final Block HARD_TIN = ModBlocks.makeHardBlock(ModuleCore.getBlocks().resources.getStateFromMeta(2),1,texturePath+"tin",modid,"tin");
	public static final Item ORE_TIN = ModItems.makeOre(modid,"tin");
	
	@Override
    public void preInit() {
        MinecraftForge.EVENT_BUS.register(this);
    }
	
	@Override
	public void init(){

		RockRegistry.addRock(ModuleCore.getBlocks().resources,0, ModItems.ROCK_STONE);
		RockRegistry.addRock(ModuleCore.getBlocks().resources,1,  ModItems.ROCK_STONE);	
		RockRegistry.addRock(ModuleCore.getBlocks().resources,2,  ModItems.ROCK_STONE);
		
		OreRegistry.addOre(ModuleCore.getBlocks().resources,0,  ModuleCore.getItems().apatite);
		OreRegistry.addOre(ModuleCore.getBlocks().resources,1,  ORE_COPPER);	
		OreRegistry.addOre(ModuleCore.getBlocks().resources,2,  ORE_TIN);

		GameRegistry.addSmelting(ORE_COPPER, ModuleCore.getItems().ingotCopper, 0.5F);
		GameRegistry.addSmelting(ORE_TIN, ModuleCore.getItems().ingotTin, 0.5F);

	}

	@SubscribeEvent
	public void registerBlocks(RegistryEvent.Register<Block> event) {
		IForgeRegistry<Block> registry = event.getRegistry();
		
		registry.register(HARD_APATITE);
		registry.register(HARD_COPPER);
		registry.register(HARD_TIN);
	}

	@SubscribeEvent
	public void registerItems(RegistryEvent.Register<Item> event) {
		IForgeRegistry<Item> registry = event.getRegistry();

		registry.register(ORE_COPPER);
		registry.register(ORE_TIN);

		ModItems.registerItemBlock(registry, HARD_COPPER);
		ModItems.registerItemBlock(registry, HARD_TIN);
		ModItems.registerItemBlock(registry, HARD_APATITE);
		
		DivergentUnderground.doDicts("Copper",ORE_COPPER,HARD_COPPER);
		DivergentUnderground.doDicts("Tin",ORE_TIN,HARD_TIN);
		OreDictionary.registerOre("oreApatite", HARD_APATITE);
	}

	@SideOnly(Side.CLIENT)
	@SubscribeEvent
	public void registerModels(ModelRegistryEvent event) {
		ClientProxy.registerItemModel(ORE_COPPER);
		ClientProxy.registerBlockModel(HARD_COPPER);
		ClientProxy.registerItemModel(ORE_TIN);
		ClientProxy.registerBlockModel(HARD_TIN);
		ClientProxy.registerBlockModel(HARD_APATITE);
	}

}
