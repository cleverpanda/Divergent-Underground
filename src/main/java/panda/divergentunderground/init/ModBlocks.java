package panda.divergentunderground.init;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.block.BlockStone;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
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
	
	public static List<BlockHardStone> hardStones = new ArrayList<>();
	
	
	public static final Block HARD_STONE = simply(new BlockHardStone(Blocks.STONE.getDefaultState(),0,"blocks/stone"),"hard_stone");
	public static final Block HARD_GRANITE = simply(new BlockHardStone(Blocks.STONE.getDefaultState().withProperty(BlockStone.VARIANT, BlockStone.EnumType.GRANITE),0,"blocks/stone_granite"),"hard_granite");
	public static final Block HARD_DIORITE = simply(new BlockHardStone(Blocks.STONE.getDefaultState().withProperty(BlockStone.VARIANT, BlockStone.EnumType.DIORITE),0,"blocks/stone_diorite"),"hard_diorite");
	public static final Block HARD_ANDESITE = simply(new BlockHardStone(Blocks.STONE.getDefaultState().withProperty(BlockStone.VARIANT, BlockStone.EnumType.ANDESITE),0,"blocks/stone_andesite"),"hard_andesite");
	
	public static final Block HARD_IRON = simply(new BlockHardStone(Blocks.IRON_ORE.getDefaultState(),1,"blocks/iron_ore"),"hard_iron_ore");
	public static final Block HARD_GOLD = simply(new BlockHardStone(Blocks.GOLD_ORE.getDefaultState(),1,"blocks/gold_ore"),"hard_gold_ore");
	public static final Block HARD_COAL = simply(new BlockHardStone(Blocks.COAL_ORE.getDefaultState(),1,"blocks/coal_ore"),"hard_coal_ore");
	public static final Block HARD_DIAMOND = simply(new BlockHardStone(Blocks.DIAMOND_ORE.getDefaultState(),2,"blocks/diamond_ore"),"hard_diamond_ore");
	public static final Block HARD_EMERALD = simply(new BlockHardStone(Blocks.EMERALD_ORE.getDefaultState(),2,"blocks/emerald_ore"),"hard_emerald_ore");
	public static final Block HARD_LAPIS = simply(new BlockHardStone(Blocks.LAPIS_ORE.getDefaultState(),1,"blocks/lapis_ore"),"hard_lapis_ore");
	public static final Block HARD_REDSTONE = simply(new BlockHardStone(Blocks.REDSTONE_ORE.getDefaultState(),1,"blocks/redstone_ore"),"hard_redstone_ore");

	//public static final Block HARD_QUARTZ = simply(new BlockHardStone(Blocks.QUARTZ_ORE.getDefaultState(),1,"blocks/iron_ore"),"hard_iron_ore");
	
	public static final Block GRANITE_COBBLE = simplynormal(new Block(Material.ROCK).setResistance(10f).setHardness(1.5f),"granite_cobblestone");
	public static final Block ANDESITE_COBBLE = simplynormal(new Block(Material.ROCK).setResistance(10f).setHardness(1.5f),"andesite_cobblestone");
	public static final Block DIORITE_COBBLE = simplynormal(new Block(Material.ROCK).setResistance(10f).setHardness(1.5f),"diorite_cobblestone");
	
	
	public static Block simplynormal(Block block, String name) {
		return block.setRegistryName(DivergentUnderground.MODID, name).setTranslationKey(DivergentUnderground.MODID + "." + name).setCreativeTab(DivergentUnderground.Tab);
	}

	public static Block simply(BlockHardStone block, String name) {
		hardStones.add((BlockHardStone) block);
		return block.setRegistryName(DivergentUnderground.MODID, name).setTranslationKey(DivergentUnderground.MODID + "." + name).setCreativeTab(DivergentUnderground.Tab);
	}

	public static Block makeHardBlock(IBlockState replacement,int type,String texture, String modid,String name) {
		String end = "_rock";
		//0 = rock,1 = ore, 2= gem
		if(type > 0){
			end = "_ore";
		}
		return simply(new BlockHardStone(replacement,type,texture),modid + "_hard_"+ name + end);
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
