package panda.divergentunderground.proxy;

import javax.annotation.Nullable;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.renderer.color.IBlockColor;
import net.minecraft.item.Item;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import panda.divergentunderground.common.blocks.BlockHardStone;
import panda.divergentunderground.init.ModBlocks;
import panda.divergentunderground.init.ModItems;

@Mod.EventBusSubscriber(Side.CLIENT)
public class ClientProxy extends CommonProxy {

	@SubscribeEvent
    public static void registerModels(ModelRegistryEvent event) {
		registerItemModel(ModItems.ROCK_STONE);
		registerItemModel(ModItems.ROCK_ANDESITE);
		registerItemModel(ModItems.ROCK_DIORITE);
		registerItemModel(ModItems.ROCK_GRANITE);
		registerItemModel(ModItems.ROCK_SANDSTONE);
		registerItemModel(ModItems.ROCK_RED_SANDSTONE);
		registerItemModel(ModItems.ROCK_NETHERRACK);
		registerItemModel(ModItems.ROCK_ENDSTONE);
		registerItemModel(ModItems.ORE_GOLD);
		registerItemModel(ModItems.ORE_IRON);
		registerItemModel(ModItems.UNCUT_DIAMOND);
		registerItemModel(ModItems.UNCUT_EMERALD);
		registerItemModel(ModItems.CHISEL);
		registerBlockModel(ModBlocks.HARD_STONE);
		registerBlockModel(ModBlocks.HARD_GRANITE);
		registerBlockModel(ModBlocks.HARD_DIORITE);
		registerBlockModel(ModBlocks.HARD_ANDESITE);
		registerBlockModel(ModBlocks.HARD_IRON);
		registerBlockModel(ModBlocks.HARD_GOLD);
		registerBlockModel(ModBlocks.HARD_COAL);
		registerBlockModel(ModBlocks.HARD_DIAMOND);
		registerBlockModel(ModBlocks.HARD_EMERALD);
		registerBlockModel(ModBlocks.HARD_LAPIS);
		registerBlockModel(ModBlocks.HARD_REDSTONE);
		registerBlockModel(ModBlocks.ANDESITE_COBBLE);
		registerBlockModel(ModBlocks.DIORITE_COBBLE);
		registerBlockModel(ModBlocks.GRANITE_COBBLE);

	}
	
	@Override
	public void registerColorHandlers() {
			Minecraft.getMinecraft().getBlockColors().registerBlockColorHandler(new IBlockColor()
	        {
	            public int colorMultiplier(IBlockState state, @Nullable IBlockAccess worldIn, @Nullable BlockPos pos, int tintIndex)
	            {
	                return worldIn != null && pos != null ? BlockHardStone.getStoneColor(state) : 0xFFFFFFF;
	            }
	        }, ModBlocks.hardStones.toArray(new Block[ModBlocks.hardStones.size()]));		
	}

	public static void registerBlockModel(Block block) {
		registerItemModel(Item.getItemFromBlock(block));
	}

	public static void registerItemModel(Item item) {
		ModelLoader.setCustomModelResourceLocation(item, 0, new ModelResourceLocation(item.getRegistryName(), "inventory"));
	}
}
