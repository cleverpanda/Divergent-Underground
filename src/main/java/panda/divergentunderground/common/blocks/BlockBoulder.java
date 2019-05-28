package panda.divergentunderground.common.blocks;

import java.util.List;
import java.util.Random;

import net.minecraft.block.BlockFalling;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyInteger;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.item.EntityFallingBlock;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import panda.divergentunderground.ConfigDivergentUnderground;
import panda.divergentunderground.DivergentUnderground;
import panda.divergentunderground.init.ModSounds;

public class BlockBoulder extends BlockFalling {

	public static final PropertyInteger DEPTH = PropertyInteger.create("hardness", 0,3);
	public static int toWait = 0;
	public static int elapsed = 0;
	public static boolean playedSound = false;
	
	public BlockBoulder(int delay) {
		toWait = delay;
		this.setHardness(40F);
		this.setResistance(2000F);
		this.setDefaultState(this.blockState.getBaseState().withProperty(DEPTH, 0));
	}
	public BlockBoulder() {
		this(14);
	}
	
	@Override
	public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
		if(playerIn.getHeldItem(hand) != ItemStack.EMPTY && playerIn.getHeldItem(hand).getItem() == Items.NETHER_STAR){
			worldIn.setBlockState(pos, state.cycleProperty(DEPTH));
		}

		return super.onBlockActivated(worldIn, pos, state, playerIn, hand, facing, hitX, hitY, hitZ);
	}
	
	@Override
    public void updateTick(World worldIn, BlockPos pos, IBlockState state, Random rand)
    {    		 
        	 if (!worldIn.isRemote && (worldIn.isAirBlock(pos.down()) || canFallThrough(worldIn.getBlockState(pos.down()))) && pos.getY() >= 0)
             { 
        		 if(!playedSound){
        			 worldIn.playSound(null, pos.getX(), pos.getY(), pos.getZ(), ModSounds.BOULDER_FALLING, SoundCategory.BLOCKS, 1.0F, 1.2F);
        			 playedSound = true;
        		 }
        		 worldIn.scheduleUpdate(pos, this, this.tickRate(worldIn));
        		 elapsed++;
        		 //DivergentUnderground.logger.info(elapsed);
        		 if(elapsed >= getWaitTimeForDepth(state)){
        			 playedSound = false;
        			 //worldIn.playSound((EntityPlayer)null, pos, SoundEvents.BLOCK_CHORUS_FLOWER_DEATH, SoundCategory.BLOCKS, 1F, 1.0F);
        			 doFalling(worldIn, pos,state,rand);
        			 elapsed = 0;
        		 }
             }
    }
	
	private int getWaitTimeForDepth(IBlockState state){
		int t = 0;
		float base = 9.333F;
		switch( this.getMetaFromState(state)){
			case 0:
				if(ConfigDivergentUnderground.hardnessOneSD != 0F){
					t = Math.round(base*ConfigDivergentUnderground.hardnessOneSD);
				}else{
					t = Math.round(base);
				}
				break;
			case 1:
				t = Math.round(base*ConfigDivergentUnderground.hardnessOneSD);
				break;
			case 2:
				t = Math.round(base*ConfigDivergentUnderground.hardnessTwoSD);
				break;
			case 3:
				t = Math.round(base*ConfigDivergentUnderground.hardnessThreeSD);
				break;
		}
		return t;
	}
	
	
	
	public void doFalling(World worldIn, BlockPos pos, IBlockState state, Random rand){
        int i = 32;

        if (!fallInstantly && worldIn.isAreaLoaded(pos.add(-32, -32, -32), pos.add(32, 32, 32)))
        {
            if (!worldIn.isRemote)
            {
                EntityFallingBlock entityfallingblock = new EntityFallingBlock(worldIn, (double)pos.getX() + 0.5D, (double)pos.getY(), (double)pos.getZ() + 0.5D, worldIn.getBlockState(pos)){
                    @Override
                	public void onCollideWithPlayer(EntityPlayer entityIn){
                    	entityIn.attackEntityFrom(DamageSource.FALLING_BLOCK, 8);
                    }
                };
                this.onStartFalling(entityfallingblock);
                worldIn.spawnEntity(entityfallingblock);
            }
        }
        else
        {
            state = worldIn.getBlockState(pos);
            worldIn.setBlockToAir(pos);
            BlockPos blockpos;

            for (blockpos = pos.down(); (worldIn.isAirBlock(blockpos) || canFallThrough(worldIn.getBlockState(blockpos))) && blockpos.getY() > 0; blockpos = blockpos.down())
            {
                ;
            }

            if (blockpos.getY() > 0)
            {
                worldIn.setBlockState(blockpos.up(), state); //Forge: Fix loss of state information during world gen.
            }
        }
	}
	
	@Override
	public void addInformation(ItemStack stack, World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
		super.addInformation(stack, worldIn, tooltip, flagIn);
		tooltip.add("This feature is in development.");
	}

	@Override
    public Material getMaterial(IBlockState state)
    {
        return Material.ROCK;
    }
	
	@Override
    public Item getItemDropped(IBlockState state, Random rand, int fortune)
    {
        return Items.AIR;
    }
	
	@Override
    protected void onStartFalling(EntityFallingBlock fallingEntity)
    {
        fallingEntity.setHurtEntities(true);
    }
    
	@Override
    public void onEndFalling(World worldIn, BlockPos pos, IBlockState fallingState, IBlockState hitState)
    {
		
    }

	@Override
    public void onBroken(World worldIn, BlockPos pos)
    {

    }
	
	@Override
	public IBlockState getStateFromMeta(int meta)
    {
        return this.getDefaultState().withProperty(DEPTH, meta);
    }
	
	@Override
    public int getMetaFromState(IBlockState state)
    {
		if(state.getBlock() instanceof BlockHardStone){
			return state.getValue(DEPTH);
		}
        return 0;
    }

    @Override
    protected BlockStateContainer createBlockState()
    {
        return new BlockStateContainer(this, DEPTH);
    }
	
}
