package panda.divergentunderground.common.blocks;

import java.util.Random;

import akka.japi.Pair;
import net.minecraft.block.Block;
import net.minecraft.block.BlockOre;
import net.minecraft.block.BlockStone;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.properties.PropertyInteger;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import panda.divergentunderground.ConfigDivergentUnderground;
import panda.divergentunderground.DivergentUnderground;
import panda.divergentunderground.registries.GemRegistry;
import panda.divergentunderground.registries.OreRegistry;
import panda.divergentunderground.registries.RockRegistry;

public class BlockHardStone extends BlockOre {
	
	@Override
	public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
		if(playerIn.getHeldItem(hand) != ItemStack.EMPTY && playerIn.getHeldItem(hand).getItem() == Items.NETHER_STAR){
			worldIn.setBlockState(pos, state.cycleProperty(DEPTH));
		}
		
		return alias.getBlock().onBlockActivated(worldIn, pos, state, playerIn, hand, facing, hitX, hitY, hitZ);
	}

	public static final PropertyInteger DEPTH = PropertyInteger.create("hardness", 0,3);
	
	private IBlockState alias;
	private int type;//0 = rock,1 = ore, 2= gem
	public String textureLocation;
	public BlockHardStone(IBlockState replacement,int type,String texture) {
		super();
		this.setDefaultState(this.blockState.getBaseState().withProperty(DEPTH, 0));
		alias = replacement;
		this.type = type;
        setHardness(1.5F);
        setResistance(10.0F);
        setSoundType(SoundType.STONE);
        this.setHarvestLevel("pickaxe", 0);
        this.textureLocation = texture;
	}
	
	@Override
	public ItemStack getPickBlock(IBlockState state, RayTraceResult target, World world, BlockPos pos,EntityPlayer player) {

		return new ItemStack(this);
	}
	
	public boolean doStoneReplace(IBlockState oldstate,World world,BlockPos pos, int y, int y1){
		if(oldstate != alias){
			return false;
		}
    	IBlockState newBlockState = this.getStateFromDepth(y,y1,isSurroundedByCompressingBlocks(world, pos, true));
        if(newBlockState != null){
        	world.setBlockState(pos, newBlockState, 20 /*no block update, no observer checks*/);
        	return true;
        }
        return false;
        
    }

	public static int getStoneColor(IBlockState state)
    {
        switch(state.getValue(DEPTH)){
        case 1:
        	return ConfigDivergentUnderground.colorHardnessOne;
        case 2:
        	return ConfigDivergentUnderground.colorHardnessTwo;
        case 3:
        	return ConfigDivergentUnderground.colorHardnessThree;
        default:
        	return ConfigDivergentUnderground.colorHardnessZero;
        }
        
    }
	
	public IBlockState tryMatch(IBlockState state){
		if(!state.equals(alias)){
			return null;
		}
		return alias;
	}
	
	@Override
	public IBlockState getStateFromMeta(int meta)
    {
        return this.getDefaultState().withProperty(DEPTH, meta);
    }
	
	public IBlockState getStateFromDepth(int top,int ypos,boolean compressed){
		int layerHeight = top/3;
        int layer = (int) Math.ceil(ypos/(float)layerHeight);
        int hardness = (3-layer)+(compressed?1:0);
        if(hardness < 0){
        	hardness = 0;
        }
        return getStateFromMeta(hardness>3? 3: hardness);
	}
	
	@Override
	public int quantityDropped(Random random)
    {
			int count = 2 + random.nextInt(3);
			return count < 5? count : 4;
    }
	@Override
    public int getExpDrop(IBlockState state, net.minecraft.world.IBlockAccess world, BlockPos pos, int fortune)
    {if(this.type > 0){return 0;}
		return alias.getBlock().getExpDrop(state, world, pos, fortune);
    }
	
	
    @SideOnly(Side.CLIENT)
    @Override
    public void randomDisplayTick(IBlockState stateIn, World worldIn, BlockPos pos, Random rand)
    {
    	alias.getBlock().randomDisplayTick(stateIn, worldIn, pos, rand);
    }
    
    @Override
    public void onBlockClicked(World worldIn, BlockPos pos, EntityPlayer playerIn)
    {
        alias.getBlock().onBlockClicked(worldIn, pos, playerIn);
    }

    @Override
    public void onEntityWalk(World worldIn, BlockPos pos, Entity entityIn)
    {
        alias.getBlock().onEntityWalk(worldIn, pos, entityIn);
    }

	@Override
	public void getDrops(NonNullList<ItemStack> drops, IBlockAccess world, BlockPos pos, IBlockState state, int fortune) {
		Random rand = ((World)world).rand;
		if(this.type == 1 || !ConfigDivergentUnderground.doGemDrops){
			alias.getBlock().getDrops(drops, world, pos, alias, fortune);
			
			for(int i = 0; i < drops.size(); i++){
				ItemStack stack = drops.get(i);
				if (stack.getItem() == alias.getBlock().getItemDropped(state, rand, fortune) &&
						Block.getBlockFromItem(alias.getBlock().getItemDropped(alias,rand,fortune)) != Blocks.AIR){
					drops.remove(stack);
				}
			}

			int count = rand.nextInt(fortune + 2) - 1;
			if(OreRegistry.hasOres(new Pair(alias.getBlock(),alias.getBlock().getMetaFromState(alias)))){
			ItemStack ore = OreRegistry.getOres(new Pair(alias.getBlock(),alias.getBlock().getMetaFromState(alias)));

			if (count < 0)
			{
				count = 0;
			}
			ore.setCount(count+1);
			drops.add(ore);
			}
		}else
			if(this.type == 2){
				alias.getBlock().getDrops(drops, world, pos, alias, fortune);
				
				for(int i = 0; i < drops.size(); i++){
					ItemStack stack = drops.get(i);
					if (stack.getItem() == alias.getBlock().getItemDropped(state, rand, fortune) &&
							Block.getBlockFromItem(alias.getBlock().getItemDropped(alias,rand,fortune)) == Blocks.AIR){
						drops.remove(stack);
					}
				}

				int count = rand.nextInt(fortune + 2) - 1;
				if(GemRegistry.hasGems(new Pair(alias.getBlock(),alias.getBlock().getMetaFromState(alias)))){
				ItemStack gem = GemRegistry.getGems(new Pair(alias.getBlock(),alias.getBlock().getMetaFromState(alias)));
				DivergentUnderground.logger.info(gem);
				if (count < 0)
				{
					count = 0;
				}
				gem.setCount(count+1);
				drops.add(gem);
				}
			}
		drops.add(new ItemStack(getItemDropped(state, rand, fortune),quantityDropped(rand)));
	}


	@Override
	public int getHarvestLevel(IBlockState state) {
		int aliasLevel = alias.getBlock().getHarvestLevel(alias);
		int metaLevel = getMetaFromState(state);
		int level = 0;
		if(aliasLevel>metaLevel){
			level = aliasLevel;
		}else{
			level = metaLevel;
		}
		return level;	
	}

	@Override
    public int getMetaFromState(IBlockState state)
    {
        return state.getValue(DEPTH);
    }

    @Override
    protected BlockStateContainer createBlockState()
    {
        return new BlockStateContainer(this, DEPTH);
    }
    
    @Override
    public Item getItemDropped(IBlockState state, Random rand, int fortune)
    {
        return RockRegistry.getRocks(new Pair(alias.getBlock(),alias.getBlock().getMetaFromState(alias))).getItem();
    }

    @Override
    public int damageDropped(IBlockState state)
    {
        return 0;
    }
    
    @Override
    public MapColor getMapColor(IBlockState state, IBlockAccess worldIn, BlockPos pos)
    {
        return alias.getBlock().getMapColor(state, worldIn, pos);
    }
    
	@Override
    protected ItemStack getSilkTouchDrop(IBlockState state)
    {
		
        Item item = Item.getItemFromBlock(alias.getBlock());

        if (item == Items.AIR)
        {
            return ItemStack.EMPTY;
        }
        else
        {
            int i = 0;

            if (item.getHasSubtypes())
            {
                i = this.getMetaFromState(state);
            }

            return new ItemStack(item, 1, i);
        }
    }
	
	@Override
	public int getLightValue(IBlockState state, IBlockAccess world, BlockPos pos) {

		return alias.getLightValue();
	}

    public static boolean isCompressingBlock(Block block) {
		return block instanceof BlockStone ||
				block instanceof BlockHardStone ||
			block == Blocks.BEDROCK ||
			block == Blocks.DIRT ||
			block == Blocks.SANDSTONE ||
			block == Blocks.RED_SANDSTONE ||
			block == Blocks.STAINED_HARDENED_CLAY ||
			block == Blocks.HARDENED_CLAY ||
			(block instanceof BlockOre);
	}

    public static boolean isSurroundedByCompressingBlocks(World world, BlockPos pos, boolean withinChunk)
	{
		return 
            isCompressingBlock(world.getBlockState(pos.up()).getBlock()) &&
			isCompressingBlock(world.getBlockState(pos.down()).getBlock()) &&
            // Don't check outside of chunk, may make some exposed stone hard, but it cleans itself up quickly
			((pos.getZ() % 16 == 0  && withinChunk) || isCompressingBlock(world.getBlockState(pos.north()).getBlock())) &&
			((pos.getZ() % 16 == 15 && withinChunk) || isCompressingBlock(world.getBlockState(pos.south()).getBlock())) &&
			((pos.getX() % 16 == 15 && withinChunk) || isCompressingBlock(world.getBlockState(pos.east() ).getBlock())) &&
			((pos.getX() % 16 == 0  && withinChunk) || isCompressingBlock(world.getBlockState(pos.west() ).getBlock()));
	}

}
