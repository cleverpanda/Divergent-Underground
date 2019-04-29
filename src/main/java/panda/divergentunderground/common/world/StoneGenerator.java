package panda.divergentunderground.common.world;

import java.util.Random;

import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.IChunkGenerator;
import net.minecraftforge.fml.common.IWorldGenerator;
import panda.divergentunderground.DivergentUnderground;
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
	
					for(BlockHardStone hardstone : ModBlocks.hardStones){
						if(hardstone.doStoneReplace(state, world, pos, y, y1)){
							if(state.getBlock().getRegistryName().getNamespace().equals("quark")){
								DivergentUnderground.logger.info(state);
							}
							break;
						}
					}                	
				}
			}            
		}
	}   
}
