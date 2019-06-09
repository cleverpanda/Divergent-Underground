package panda.divergentunderground.common.world;

import java.util.Random;

import com.google.common.base.Predicate;

import net.minecraft.block.BlockStone;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.IChunkGenerator;
import net.minecraft.world.gen.feature.WorldGenMinable;
import net.minecraftforge.fml.common.IWorldGenerator;
import panda.divergentunderground.ConfigDivergentUnderground;
import panda.divergentunderground.common.blocks.BlockHardStone;
import panda.divergentunderground.init.ModBlocks;

public class FeatureGenerator implements IWorldGenerator{
	private final Predicate<IBlockState> predicate = new StonePredicate();
	@Override
	public void generate(Random random, int chunkX, int chunkZ, World world, IChunkGenerator chunkGenerator, IChunkProvider chunkProvider) {
		
		if (world.provider.getDimension() == 0) {
			if(ConfigDivergentUnderground.enableFossils){
				generateFossil(world, random, chunkX << 4, chunkZ << 4, 0, 64, 3 + random.nextInt(3), ConfigDivergentUnderground.fossilTries);
			}
			if(ConfigDivergentUnderground.enableBoulders){
				generateSingleOre(ModBlocks.BOULDER.getDefaultState(),world, random, chunkX << 4, chunkZ << 4, 0, 50, ConfigDivergentUnderground.boulderTries);
			}
		}
	}
	
	private void generateFossil(World world, Random random, int x, int z, int minY, int maxY, int size, int chances) {
		int deltaY = maxY - minY;
		
		for (int i = 0; i < chances; i++) {
			int cx = x + random.nextInt(16);
			int cz = z + random.nextInt(16);
			int cy = minY + random.nextInt(deltaY);
			BlockPos pos = new BlockPos(cx, cy, cz);
			int ty = world.getTopSolidOrLiquidBlock(new BlockPos(cx, cy, cz)).getY();
			WorldGenMinable generator = new WorldGenMinable(((BlockHardStone) ModBlocks.FOSSIL).getStateFromDepth(ty, cy, false).cycleProperty(BlockHardStone.DEPTH), size);
			generator.generate(world, random, pos);
		}
	}
	
	private void generateSingleOre(IBlockState state,World world, Random random, int x, int z, int minY, int maxY, int chances) {
		int deltaY = maxY - minY;
		
		for (int i = 0; i < chances; i++) {
			BlockPos pos = new BlockPos(x + random.nextInt(16), minY + random.nextInt(deltaY), z + random.nextInt(16));
			 if (state.getBlock().isReplaceableOreGen(state, world, pos, this.predicate)){
				 world.setBlockState(pos, state, 2);
			 }
		}
	}
	
    static class StonePredicate implements Predicate<IBlockState>{
        private StonePredicate(){}

        public boolean apply(IBlockState toTest)
        {
            if (toTest != null && toTest.getBlock() == Blocks.STONE){
                BlockStone.EnumType blockstone = toTest.getValue(BlockStone.VARIANT);
                return blockstone.isNatural();
            }
            return false;
        }
    }

}
