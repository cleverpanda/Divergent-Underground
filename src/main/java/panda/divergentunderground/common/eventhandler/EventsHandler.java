package panda.divergentunderground.common.eventhandler;

import java.util.Iterator;
import java.util.List;
import java.util.Random;

import akka.japi.Pair;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemPickaxe;
import net.minecraft.item.ItemStack;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.WorldServer;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.chunk.storage.ExtendedBlockStorage;
import net.minecraftforge.event.entity.player.PlayerEvent.BreakSpeed;
import net.minecraftforge.event.world.BlockEvent.HarvestDropsEvent;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.Phase;
import net.minecraftforge.fml.common.gameevent.TickEvent.WorldTickEvent;
import net.minecraftforge.fml.relauncher.Side;
import panda.divergentunderground.ConfigDivergentUnderground;
import panda.divergentunderground.DivergentUnderground;
import panda.divergentunderground.api.GemRegistry;
import panda.divergentunderground.api.OreRegistry;
import panda.divergentunderground.api.RockRegistry;
import panda.divergentunderground.common.blocks.BlockHardStone;
import panda.divergentunderground.init.ModBlocks;

public class EventsHandler {
	
	  @SubscribeEvent(priority = EventPriority.LOWEST)
	  public void onHarvestDropsEvent(HarvestDropsEvent event) {
		  Block block = event.getState().getBlock();
		  ItemStack blockstack = block.getItem(event.getWorld(), event.getPos(), event.getState());
		  if(block instanceof BlockHardStone){
			  return;
		  }
		  if(!event.isSilkTouching()){
			  
			  if(RockRegistry.hasRocks(new Pair(block,blockstack.getMetadata()))){
				  replaceDrops(event,blockstack);//remove block drop and replace with rock items
				  event.setDropChance(1.0F); 
			  }
			  
			  if(OreRegistry.hasOres(new Pair(block,blockstack.getMetadata()))){
				  
				  Random rand = event.getWorld().rand;
				  int count = rand.nextInt(event.getFortuneLevel() + 2) - 1;
				  ItemStack ore = OreRegistry.getOres(new Pair(block,blockstack.getMetadata()));
				  
				  if (count < 0)
		            {
		                count = 0;
		            }
				  ore.setCount(count+1);
				  Block.spawnAsEntity(event.getWorld(), event.getPos(), ore);
				  event.setDropChance(1.0F); 
			  }

			  if(GemRegistry.hasGems(new Pair(block,blockstack.getMetadata())) && ConfigDivergentUnderground.doGemDrops){
				  
				  Random rand = event.getWorld().rand;
				  int count = rand.nextInt(event.getFortuneLevel() + 2) - 1;
				  ItemStack gem = GemRegistry.getGems(new Pair(block,blockstack.getMetadata()));
				  
				  if (count < 0)
		            {
		                count = 0;
		            }
				  
				  gem.setCount(count+1);
				  List<ItemStack> drops = event.getDrops();
				  for(int i = 0; i < drops.size(); i++){
						ItemStack stack = drops.get(i);
						
						if (ItemStack.areItemsEqualIgnoreDurability(stack,blockstack) || Block.getBlockFromItem(event.getState().getBlock().getItemDropped(event.getState(),event.getWorld().rand,event.getFortuneLevel())) == Blocks.AIR){
							drops.set(i, gem);
						}
						
					}
				  event.setDropChance(1.0F); 
			  }
			  
			  
		  }		
	}
	  
	  private void replaceDrops(HarvestDropsEvent e, ItemStack blockstack){
			List<ItemStack> drops = e.getDrops();
			boolean flag = true;
			int count =  2 + e.getWorld().rand.nextInt(2);
			if(e.getFortuneLevel()>0){
				count = 4;
			}
			
			ItemStack rock = RockRegistry.getRocks(new Pair(e.getState().getBlock(),blockstack.getMetadata()));
			rock.setCount(count);
			
			for(int i = 0; i < drops.size(); i++){
				ItemStack stack = drops.get(i);
				
				if (ItemStack.areItemsEqualIgnoreDurability(stack,blockstack) || Block.getBlockFromItem(e.getState().getBlock().getItemDropped(e.getState(),e.getWorld().rand,e.getFortuneLevel())) != Blocks.AIR){
					flag = false;
					drops.set(i, rock);
				}
				
			}
			if (flag){
				Block.spawnAsEntity(e.getWorld(), e.getPos(), rock);
			}
		}  
	  
	  @SubscribeEvent
		public void onBreakSpeed(BreakSpeed event) {
			if (!(event.getState().getBlock() instanceof BlockHardStone) || !ConfigDivergentUnderground.doHardnessSlowdown) {
				return;
			}

			ItemStack heldItemStack = event.getEntityPlayer().getHeldItemMainhand();
			if (heldItemStack.isEmpty() || !(heldItemStack.getItem() instanceof ItemPickaxe)) {
				return;
			}
			
			float slowdown = 1;
			
			switch(event.getState().getBlock().getMetaFromState(event.getState())){
			case 1:
				slowdown += ConfigDivergentUnderground.hardnessOneSD;
				break;
			case 2:
				slowdown += ConfigDivergentUnderground.hardnessTwoSD;
				break;
			case 3:
				slowdown += ConfigDivergentUnderground.hardnessThreeSD;
				break;
			default:
				slowdown += ConfigDivergentUnderground.hardnessZeroSD;
			}

			event.setNewSpeed(event.getOriginalSpeed() / slowdown);
		}
	  
	  protected int updateLCG = (new Random()).nextInt();
	  
		@SubscribeEvent//take into account depth and hardness
	    public void onWorldTick(WorldTickEvent event) {
			
			if (event.phase == Phase.START || event.side == Side.CLIENT || !ConfigDivergentUnderground.doCompression){
				return;
			}

			int i = event.world.getGameRules().getInt("randomTickSpeed");

			if (i > 0)
			{
				Iterator<Chunk> chunkIterator = ((WorldServer)event.world).getPlayerChunkMap().getChunkIterator();
				Iterator<Chunk> iterator = net.minecraftforge.common.ForgeChunkManager.getPersistentChunksIterableFor(event.world, chunkIterator);

				// The following loop mimics the built in random tick loop to provide random ticks for stone and hard stone blocks
				while (iterator.hasNext())
				{
					Chunk chunk = iterator.next();
					int j = chunk.x * 16;
					int k = chunk.z * 16;
					for (ExtendedBlockStorage extendedblockstorage : chunk.getBlockStorageArray())
					{
						if (extendedblockstorage != Chunk.NULL_BLOCK_STORAGE && !extendedblockstorage.isEmpty())
						{
							for (int i1 = 0; i1 < i; ++i1)
							{
								this.updateLCG = this.updateLCG * 3 + 1013904223;
								int j1 = this.updateLCG >> 2;
								int k1 = j1 & 15;
								int l1 = j1 >> 8 & 15;
								int i2 = j1 >> 16 & 15;
								IBlockState iblockstate = extendedblockstorage.get(k1, i2, l1);
								Block block = iblockstate.getBlock();

								if (block == Blocks.STONE || block == ModBlocks.HARD_STONE)
								{
									BlockPos pos = new BlockPos(k1 + j, i2 + extendedblockstorage.getYLocation(), l1 + k);
									boolean hard = block == ModBlocks.HARD_STONE;
									boolean shouldBeHard = BlockHardStone.isSurroundedByCompressingBlocks(event.world, pos, false);

									if (hard != shouldBeHard)
									{
										Block newBlock = shouldBeHard ? ModBlocks.HARD_STONE : Blocks.STONE;
										if(newBlock == ModBlocks.HARD_STONE){
											int y = event.world.getTopSolidOrLiquidBlock(pos).getY();
											event.world.setBlockState(pos, ((BlockHardStone)newBlock).getStateFromDepth(y, i2, shouldBeHard), 6 /*no block update, no re-render*/);
										}else{
											event.world.setBlockState(pos, newBlock.getStateFromMeta(0), 6 /*no block update, no re-render*/);
										}
										
										if (!shouldBeHard && ConfigDivergentUnderground.doUpdateSound) {
											event.world.playSound((EntityPlayer)null, pos, SoundEvents.BLOCK_CHORUS_FLOWER_DEATH, SoundCategory.BLOCKS, 0.2F, 1.0F);
										}
									}
								}
							}
						}
					}
				}
			}
	    }

}
