package panda.divergentunderground.common.world;

import java.util.Random;

import cofh.thermalfoundation.block.BlockOre;
import cofh.thermalfoundation.init.TFBlocks;
import net.minecraft.block.Block;
import net.minecraft.block.BlockStone;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.IChunkGenerator;
import net.minecraftforge.fml.common.IWorldGenerator;
import panda.divergentunderground.DivergentUnderground;
import panda.divergentunderground.api.compatabiliy.ThermalCompat;
import panda.divergentunderground.common.blocks.BlockHardStone;
import panda.divergentunderground.init.ModBlocks;

public class StoneGenerator implements IWorldGenerator {
    public void generate(Random random, int chunkX, int chunkZ, World world, IChunkGenerator chunkGenerator, IChunkProvider chunkProvider) {
        if(!(world instanceof WorldServer)) {
            return;
        }

		int x = chunkX * 16 + 8;
		int z = chunkZ * 16 + 8 ;

        for (int x1 = x; x1 < x + 16; x1++) {
            for (int z1 = z; z1 < z + 16; z1++) {
                int y = world.getTopSolidOrLiquidBlock(new BlockPos(x1, 0, z1)).getY();
                for (int y1 = y; y1 >= 0; y1--) {
                    BlockPos pos = new BlockPos(x1, y1, z1);
                    IBlockState state = world.getBlockState(pos);
                    Block block = state.getBlock();

                    if (block == Blocks.STONE) {
                    	if(state.getValue(BlockStone.VARIANT) == BlockStone.EnumType.STONE){
                    		doStoneReplace(ModBlocks.HARD_STONE,world,pos,y,y1);
                    	}else
                    	if(state.getValue(BlockStone.VARIANT) == BlockStone.EnumType.GRANITE){
                    		doStoneReplace(ModBlocks.HARD_GRANITE,world,pos,y,y1);  
                    	}else
                        if(state.getValue(BlockStone.VARIANT) == BlockStone.EnumType.ANDESITE){
                        	doStoneReplace(ModBlocks.HARD_ANDESITE,world,pos,y,y1);  
                        }else
                        if(state.getValue(BlockStone.VARIANT) == BlockStone.EnumType.DIORITE){
                            doStoneReplace(ModBlocks.HARD_DIORITE,world,pos,y,y1);  
                        }                   	
                    }else
                    if(block == Blocks.IRON_ORE){
                    	doStoneReplace(ModBlocks.HARD_IRON,world,pos,y,y1);
                    }else
                    if(block == Blocks.GOLD_ORE){
                        doStoneReplace(ModBlocks.HARD_GOLD,world,pos,y,y1);
                    }else
                    if(block == Blocks.COAL_ORE){
                        doStoneReplace(ModBlocks.HARD_COAL,world,pos,y,y1);
                    }else
                    if(block == Blocks.DIAMOND_ORE){
                        doStoneReplace(ModBlocks.HARD_DIAMOND,world,pos,y,y1);
                    }else
                    if(block == Blocks.EMERALD_ORE){
                        doStoneReplace(ModBlocks.HARD_EMERALD,world,pos,y,y1);
                    }else
                    if(block == Blocks.LAPIS_ORE){
                        doStoneReplace(ModBlocks.HARD_LAPIS,world,pos,y,y1);
                    }else
                    if(block == Blocks.REDSTONE_ORE){
                        doStoneReplace(ModBlocks.HARD_REDSTONE,world,pos,y,y1);
                    }else
                    	
                    	if(DivergentUnderground.Thermalenabled && block == TFBlocks.blockOre){
                    		if(state.getValue(BlockOre.VARIANT)  == BlockOre.Type.COPPER){
                                doStoneReplace(ThermalCompat.HARD_COPPER,world,pos,y,y1);
                            }
                    	}
                }
            }            
        }
        
    }
    
    private void doStoneReplace(Block hardStoneBlock,World world,BlockPos pos, int y, int y1){
    	IBlockState newBlockState = ((BlockHardStone) hardStoneBlock).getStateFromDepth(y,y1,BlockHardStone.isSurroundedByCompressingBlocks(world, pos, true));
        if(newBlockState != null){
        	world.setBlockState(pos, newBlockState, 20 /*no block update, no observer checks*/);
        }
    }
    
    
}
