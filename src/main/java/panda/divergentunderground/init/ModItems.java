package panda.divergentunderground.init;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.registries.IForgeRegistry;
import panda.divergentunderground.DivergentUnderground;
import panda.divergentunderground.common.items.ItemChisel;
import panda.divergentunderground.integration.BiomesOPlentyCompat;
import panda.divergentunderground.integration.ForestryCompat;
import panda.divergentunderground.integration.ImmersiveEngineeringCompat;
import panda.divergentunderground.integration.IndustrialCraftCompat;
import panda.divergentunderground.integration.MekanismCompat;
import panda.divergentunderground.integration.ThermalCompat;


@EventBusSubscriber
public final class ModItems {
	
	private ModItems(){DivergentUnderground.logger.info("Registering Items");}

	public static final Item ORE_GOLD = makeOre("gold");
	public static final Item ORE_IRON = makeOre("iron");
	//public static final Item ORE_COPPER = makeOre("copper");
	
	public static final Item ROCK_ANDESITE = makeRock("andesite");
	public static final Item ROCK_DIORITE = makeRock("diorite");
	public static final Item ROCK_GRANITE = makeRock("granite");
	public static final Item ROCK_NETHERRACK = makeRock("netherrack");
	public static final Item ROCK_ENDSTONE = makeRock("endstone");
	public static final Item ROCK_SANDSTONE = makeRock("sandstone");
	public static final Item ROCK_RED_SANDSTONE = makeRock("red_sandstone");
	public static final Item ROCK_STONE = makeRock("stone");
	
	public static final Item UNCUT_EMERALD = makeGem("emerald");
	public static final Item UNCUT_DIAMOND = makeGem("diamond");
	public static final Item CHISEL = simply(new ItemChisel(),"chisel");

	public static Item makeOre(String key){
		return simply(new Item(), "ore_"+key);
	}
	
	private static Item makeRock(String key){
		return simply(new Item(), "rock_"+key);
	}
	
	public static Item makeGem(String key){
		return simply(new Item(), "gem_raw_"+key);
	}
	
	private static Item simply(Item item, String name) { 
		return item.setRegistryName(DivergentUnderground.MODID, name).setTranslationKey(DivergentUnderground.MODID + "." + name).setCreativeTab(DivergentUnderground.Tab);
	}

	@SubscribeEvent
	public static void register(RegistryEvent.Register<Item> event) {
		IForgeRegistry<Item> registry = event.getRegistry();
		
		registry.register(ROCK_STONE);
		registry.register(ROCK_ANDESITE);
		registry.register(ROCK_DIORITE);
		registry.register(ROCK_GRANITE);
		registry.register(ROCK_SANDSTONE);
		registry.register(ROCK_RED_SANDSTONE);
		registry.register(ROCK_NETHERRACK);
		registry.register(ROCK_ENDSTONE);
		registry.register(ORE_GOLD);
		registry.register(ORE_IRON);
		//registry.register(ORE_COPPER);
		registry.register(UNCUT_EMERALD);
		registry.register(UNCUT_DIAMOND);
		registry.register(CHISEL);
		
		registerItemBlock(registry, ModBlocks.HARD_STONE);
		registerItemBlock(registry, ModBlocks.HARD_GRANITE);
		registerItemBlock(registry, ModBlocks.HARD_ANDESITE);
		registerItemBlock(registry, ModBlocks.HARD_DIORITE);
		registerItemBlock(registry, ModBlocks.HARD_IRON);
		registerItemBlock(registry, ModBlocks.HARD_GOLD);
		registerItemBlock(registry, ModBlocks.HARD_COAL);
		registerItemBlock(registry, ModBlocks.HARD_DIAMOND);
		registerItemBlock(registry, ModBlocks.HARD_EMERALD);
		registerItemBlock(registry, ModBlocks.HARD_LAPIS);
		registerItemBlock(registry, ModBlocks.HARD_REDSTONE);
		registerItemBlock(registry, ModBlocks.ANDESITE_COBBLE);
		registerItemBlock(registry, ModBlocks.DIORITE_COBBLE);
		registerItemBlock(registry, ModBlocks.GRANITE_COBBLE);
		
		if(DivergentUnderground.Thermalenabled){
			ThermalCompat.registerItems(event);
		}
		if(DivergentUnderground.BOPenabled){
			BiomesOPlentyCompat.registerItems(event);
		}
		if(DivergentUnderground.ImmersiveEngineeringenabled){
			ImmersiveEngineeringCompat.registerItems(event);
		}
		if(DivergentUnderground.Forestryenabled){
			ForestryCompat.registerItems(event);
		}
		if(DivergentUnderground.IndustrialCraftenabled){
			IndustrialCraftCompat.registerItems(event);
		}
		if(DivergentUnderground.Mekanismenabled){
			MekanismCompat.registerItems(event);
		}
	}

	public static void registerItemBlock(IForgeRegistry<Item> registry, Block block) {
		registry.register(new ItemBlock(block).setRegistryName(block.getRegistryName()));
	}
}
