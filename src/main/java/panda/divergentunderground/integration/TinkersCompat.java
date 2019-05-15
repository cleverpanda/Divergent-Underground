package panda.divergentunderground.integration;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;
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
import slimeknights.tconstruct.shared.TinkerCommons;
import slimeknights.tconstruct.shared.block.BlockOre;

@CompatibilityPlugin("tconstruct")
public class TinkersCompat implements ICompatibilityPlugin{

	private static final String modid = "tconstruct";
	private static final String texturePath = "tconstruct:blocks/";
	
	private static Block blockOre = null;
	
	public static Block HARD_COBALT = null;
	public static final Item ORE_COBALT = ModItems.makeOre(modid,"cobalt");

	public static Block HARD_ARDITE = null;
	public static final Item ORE_ARDITE = ModItems.makeOre(modid,"ardite");
	
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
		
		
		blockOre = TinkerCommons.blockOre;
		
		DivergentUnderground.logger.info(blockOre);
		
		HARD_COBALT = ModBlocks.makeHardBlock(blockOre.getStateFromMeta(BlockOre.OreTypes.COBALT.getMeta()),1,texturePath+"ore_cobalt",modid,"cobalt");
		HARD_ARDITE = ModBlocks.makeHardBlock(blockOre.getStateFromMeta(BlockOre.OreTypes.ARDITE.getMeta()),1,texturePath+"ore_ardite",modid,"ardite");
		
		RockRegistry.addRock(blockOre,  ModItems.ROCK_NETHERRACK);	

		OreRegistry.addOre(blockOre,BlockOre.OreTypes.COBALT.getMeta(),  ORE_COBALT);	
		OreRegistry.addOre(blockOre,BlockOre.OreTypes.ARDITE.getMeta(),  ORE_ARDITE);

		registry.register(HARD_COBALT);
		registry.register(HARD_ARDITE);
	}

	@SubscribeEvent(priority = EventPriority.LOWEST)
	public void registerItems(RegistryEvent.Register<Item> event) {
		IForgeRegistry<Item> registry = event.getRegistry();

		registry.register(ORE_COBALT);
		registry.register(ORE_ARDITE);

		ModItems.registerItemBlock(registry, HARD_COBALT);
		ModItems.registerItemBlock(registry, HARD_ARDITE);
	
		DivergentUnderground.doDicts("Cobalt",ORE_COBALT,HARD_COBALT);
		DivergentUnderground.doDicts("Ardite",ORE_ARDITE,HARD_ARDITE);
		
		GameRegistry.addSmelting(ORE_COBALT, TinkerCommons.ingotCobalt, 1F);
		GameRegistry.addSmelting(ORE_ARDITE, TinkerCommons.ingotArdite, 1F);
		GameRegistry.addSmelting(HARD_COBALT, TinkerCommons.ingotCobalt, 1F);
		GameRegistry.addSmelting(HARD_ARDITE, TinkerCommons.ingotArdite, 1F);

	}

	@SideOnly(Side.CLIENT)
	@SubscribeEvent
	public void registerModels(ModelRegistryEvent event) {
		ClientProxy.registerItemModel(ORE_COBALT);
		ClientProxy.registerBlockModel(HARD_COBALT);
		ClientProxy.registerItemModel(ORE_ARDITE);
		ClientProxy.registerBlockModel(HARD_ARDITE);
	}

}
