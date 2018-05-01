package panda.divergentunderground.init;

import net.minecraft.block.Block;
import net.minecraft.block.BlockStone;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.registries.IForgeRegistry;
import panda.divergentunderground.DivergentUnderground;
import panda.divergentunderground.common.blocks.BlockHardStone;


@EventBusSubscriber
public final class ModBlocks {
	
	private ModBlocks(){DivergentUnderground.logger.info("Registering Blocks");}
	
	public static final Block HARD_STONE = simply(new BlockHardStone(Blocks.STONE.getDefaultState(),false),"hard_stone");
	public static final Block HARD_GRANITE = simply(new BlockHardStone(Blocks.STONE.getDefaultState().withProperty(BlockStone.VARIANT, BlockStone.EnumType.GRANITE),false),"hard_granite");
	public static final Block HARD_DIORITE = simply(new BlockHardStone(Blocks.STONE.getDefaultState().withProperty(BlockStone.VARIANT, BlockStone.EnumType.DIORITE),false),"hard_diorite");
	public static final Block HARD_ANDESITE = simply(new BlockHardStone(Blocks.STONE.getDefaultState().withProperty(BlockStone.VARIANT, BlockStone.EnumType.ANDESITE),false),"hard_andesite");
	
	public static final Block HARD_IRON = simply(new BlockHardStone(Blocks.IRON_ORE.getDefaultState(),true),"hard_iron_ore");
	public static final Block HARD_GOLD = simply(new BlockHardStone(Blocks.GOLD_ORE.getDefaultState(),true),"hard_gold_ore");
	public static final Block HARD_COAL = simply(new BlockHardStone(Blocks.COAL_ORE.getDefaultState(),true),"hard_coal_ore");
	public static final Block HARD_DIAMOND = simply(new BlockHardStone(Blocks.DIAMOND_ORE.getDefaultState(),true),"hard_diamond_ore");
	public static final Block HARD_EMERALD = simply(new BlockHardStone(Blocks.EMERALD_ORE.getDefaultState(),true),"hard_emerald_ore");
	public static final Block HARD_LAPIS = simply(new BlockHardStone(Blocks.LAPIS_ORE.getDefaultState(),true),"hard_lapis_ore");
	public static final Block HARD_REDSTONE = simply(new BlockHardStone(Blocks.REDSTONE_ORE.getDefaultState(),true),"hard_redstone_ore");
	
	
	public static final Block GRANITE_COBBLE = simply(new Block(Material.ROCK),"granite_cobblestone");
	public static final Block ANDESITE_COBBLE = simply(new Block(Material.ROCK),"andesite_cobblestone");
	public static final Block DIORITE_COBBLE = simply(new Block(Material.ROCK),"diorite_cobblestone");
	
	
	

	private static Block simply(Block block, String name) {
		return block.setRegistryName(DivergentUnderground.MODID, name).setUnlocalizedName(DivergentUnderground.MODID + "." + name).setCreativeTab(DivergentUnderground.Tab);
	}

	@SubscribeEvent
	public static void register(RegistryEvent.Register<Block> event) {
		IForgeRegistry<Block> registry = event.getRegistry();

		registry.register(HARD_STONE);
		registry.register(HARD_GRANITE);
		registry.register(HARD_DIORITE);
		registry.register(HARD_ANDESITE);
		registry.register(HARD_IRON);
		registry.register(HARD_GOLD);
		registry.register(HARD_COAL);
		registry.register(HARD_DIAMOND);
		registry.register(HARD_EMERALD);
		registry.register(HARD_LAPIS);
		registry.register(HARD_REDSTONE);
		registry.register(GRANITE_COBBLE);
		registry.register(ANDESITE_COBBLE);
		registry.register(DIORITE_COBBLE);

		
	}
}
