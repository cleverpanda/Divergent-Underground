package panda.divergentunderground.common.blocks;

import java.util.Random;

import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import panda.divergentunderground.init.ModItems;

public class BlockFossil extends BlockHardStone{

	public BlockFossil() {
		super(1, "");
	}
	
	@Override
    public int getExpDrop(IBlockState state, IBlockAccess world, BlockPos pos, int fortune)
    {
		return 2;
    }
	
	@Override
	public void getDrops(NonNullList<ItemStack> drops, IBlockAccess world, BlockPos pos, IBlockState state, int fortune) {
		Random rand = ((World)world).rand;
			
		int count = rand.nextInt(state.getValue(DEPTH)+1)+1;
		ItemStack ore = new ItemStack(Items.BONE);
		ore.setCount(count);
		drops.add(ore);
		
		drops.add(new ItemStack(getItemDropped(state, rand, fortune),quantityDropped(rand)));
	}
	
    @Override
    public Item getItemDropped(IBlockState state, Random rand, int fortune)
    {
        return ModItems.ROCK_STONE;
    }
}
