package panda.divergentunderground.integration;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
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

@CompatibilityPlugin("ic2")
public class IndustrialCraftCompat implements ICompatibilityPlugin {
	
	private static final String texturePath = "ic2:blocks/resource/";
	private static final String modid = "ic2"; 
	
	public static final Block IC2_BLOCK = ForgeRegistries.BLOCKS.getValue(new ResourceLocation("ic2:resource"));
	
	public static final Block HARD_COPPER = ModBlocks.makeHardBlock(IC2_BLOCK.getStateFromMeta(1),1,texturePath+"copper_ore",modid,"copper");
	public static final Block HARD_LEAD = ModBlocks.makeHardBlock(IC2_BLOCK.getStateFromMeta(2),1,texturePath+"lead_ore",modid,"lead");
	public static final Block HARD_TIN = ModBlocks.makeHardBlock(IC2_BLOCK.getStateFromMeta(3),1,texturePath+"tin_ore",modid,"tin");

	public static final Block HARD_URANIUM = ModBlocks.makeHardBlock(IC2_BLOCK.getStateFromMeta(4),1,texturePath+"uranium_ore",modid,"uranium");
	
	public static final Item ORE_COPPER = ModItems.makeOre(modid,"copper");
	public static final Item ORE_TIN = ModItems.makeOre(modid,"tin");
	public static final Item ORE_LEAD = ModItems.makeOre(modid,"lead");
	public static final Item ORE_URANIUM = ModItems.makeOre(modid,"uranium");

    @Override
    public void preInit() {
        MinecraftForge.EVENT_BUS.register(this);
    }
	
	@Override
	public void init(){
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

	@SubscribeEvent
	public void registerBlocks(RegistryEvent.Register<Block> event) {
		IForgeRegistry<Block> registry = event.getRegistry();
	
		registry.register(HARD_COPPER);
		registry.register(HARD_TIN);
		registry.register(HARD_LEAD);
		registry.register(HARD_URANIUM);
	}
	
	@SubscribeEvent
	public void registerItems(RegistryEvent.Register<Item> event) {
		IForgeRegistry<Item> registry = event.getRegistry();

		registry.register(ORE_COPPER);
		registry.register(ORE_TIN);
		registry.register(ORE_LEAD);
		registry.register(ORE_URANIUM);

		ModItems.registerItemBlock(registry, HARD_COPPER);
		ModItems.registerItemBlock(registry, HARD_TIN);
		ModItems.registerItemBlock(registry, HARD_LEAD);
		ModItems.registerItemBlock(registry, HARD_URANIUM);
		
		DivergentUnderground.doDicts("Copper",ORE_COPPER,HARD_COPPER);
		DivergentUnderground.doDicts("Tin",ORE_TIN,HARD_TIN);
		DivergentUnderground.doDicts("Lead",ORE_LEAD,HARD_LEAD);
		DivergentUnderground.doDicts("Uranium",ORE_URANIUM,HARD_URANIUM);
	}

	@SideOnly(Side.CLIENT)
	@SubscribeEvent
    public void registerModels(ModelRegistryEvent event) {
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
