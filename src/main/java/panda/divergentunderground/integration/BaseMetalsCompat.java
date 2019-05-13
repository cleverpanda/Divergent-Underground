package panda.divergentunderground.integration;

import com.mcmoddev.lib.data.Names;
import com.mcmoddev.lib.init.Materials;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.registries.IForgeRegistry;
import panda.divergentunderground.DivergentUnderground;
import panda.divergentunderground.experimental.CompatibilityPlugin;
import panda.divergentunderground.experimental.ICompatibilityPlugin;
import panda.divergentunderground.init.ModBlocks;
import panda.divergentunderground.init.ModItems;
import panda.divergentunderground.proxy.ClientProxy;
import panda.divergentunderground.registries.OreRegistry;
import panda.divergentunderground.registries.RockRegistry;

@CompatibilityPlugin("basemetals")
public class BaseMetalsCompat implements ICompatibilityPlugin {

	private static final String modid = "basemetals";
	private static final String texturePath = "basemetals:blocks/";
	
	public static Block bmCopper = Materials.getMaterialByName("copper").getBlock(Names.ORE);
	public static Block bmTin = Materials.getMaterialByName("tin").getBlock(Names.ORE);
	public static Block bmZinc = Materials.getMaterialByName("zinc").getBlock(Names.ORE);
	public static Block bmSilver = Materials.getMaterialByName("silver").getBlock(Names.ORE);
	public static Block bmPlatinum = Materials.getMaterialByName("platinum").getBlock(Names.ORE);
	public static Block bmPewter = Materials.getMaterialByName("pewter").getBlock(Names.ORE);
	public static Block bmNickel = Materials.getMaterialByName("nickel").getBlock(Names.ORE);
	public static Block bmMercury = Materials.getMaterialByName("mercury").getBlock(Names.ORE);
	public static Block bmLead = Materials.getMaterialByName("lead").getBlock(Names.ORE);
	public static Block bmCupronickel = Materials.getMaterialByName("cupronickel").getBlock(Names.ORE);
	public static Block bmBismuth = Materials.getMaterialByName("bismuth").getBlock(Names.ORE);
	public static Block bmAntimony = Materials.getMaterialByName("antimony").getBlock(Names.ORE);
	public static Block bmAdamantine = Materials.getMaterialByName("adamantine").getBlock(Names.ORE);
	public static Block bmColdiron = Materials.getMaterialByName("coldiron").getBlock(Names.ORE);
	public static Block bmStarsteel = Materials.getMaterialByName("starsteel").getBlock(Names.ORE);
	
	
	public static final Block HARD_COPPER = ModBlocks.makeHardBlock(bmCopper.getDefaultState(),1,texturePath+"copper_ore",modid,"copper");
	public static final Item ORE_COPPER = ModItems.makeOre(modid,"copper");

	
	@Override
    public void preInit() {
        MinecraftForge.EVENT_BUS.register(this);
    }
	
	@Override
	public void init(){

		RockRegistry.addRock(bmCopper, ModItems.ROCK_STONE);
		OreRegistry.addOre(bmCopper,  ORE_COPPER);	


		//GameRegistry.addSmelting(ORE_COPPER, ModuleCore.getItems().ingotCopper, 0.5F);
		//GameRegistry.addSmelting(ORE_TIN, ModuleCore.getItems().ingotTin, 0.5F);

	}

	@SubscribeEvent
	public void registerBlocks(RegistryEvent.Register<Block> event) {
		IForgeRegistry<Block> registry = event.getRegistry();
		
		registry.register(HARD_COPPER);

	}

	@SubscribeEvent
	public void registerItems(RegistryEvent.Register<Item> event) {
		IForgeRegistry<Item> registry = event.getRegistry();

		registry.register(ORE_COPPER);
		ModItems.registerItemBlock(registry, HARD_COPPER);
		DivergentUnderground.doDicts("Copper",ORE_COPPER,HARD_COPPER);

	}

	@SideOnly(Side.CLIENT)
	@SubscribeEvent
	public void registerModels(ModelRegistryEvent event) {
		ClientProxy.registerItemModel(ORE_COPPER);
		ClientProxy.registerBlockModel(HARD_COPPER);
	}
}