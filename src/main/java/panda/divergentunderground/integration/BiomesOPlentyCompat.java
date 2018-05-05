package panda.divergentunderground.integration;

import biomesoplenty.api.block.BOPBlocks;
import biomesoplenty.common.block.BlockBOPGemOre;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.oredict.OreDictionary;
import net.minecraftforge.registries.IForgeRegistry;
import panda.divergentunderground.ConfigDivergentUnderground;
import panda.divergentunderground.common.blocks.BlockHardStone;
import panda.divergentunderground.init.ModBlocks;
import panda.divergentunderground.init.ModItems;
import panda.divergentunderground.proxy.ClientProxy;
import panda.divergentunderground.registries.GemRegistry;
import panda.divergentunderground.registries.RockRegistry;

public class BiomesOPlentyCompat {

	public static final Block HARD_AMETHYST = ModBlocks.simply(new BlockHardStone(BOPBlocks.gem_ore.getStateFromMeta(0),2),"bop_hard_amethyst_ore");
	public static final Item GEM_AMETHYST = ModItems.makeGem("bop_amethyst");
	
	public static final Block HARD_RUBY = ModBlocks.simply(new BlockHardStone(BOPBlocks.gem_ore.getStateFromMeta(1),2),"bop_hard_ruby_ore");
	public static final Item GEM_RUBY = ModItems.makeGem("bop_ruby");
	
	public static final Block HARD_PERIDOT = ModBlocks.simply(new BlockHardStone(BOPBlocks.gem_ore.getStateFromMeta(2),2),"bop_hard_peridot_ore");
	public static final Item GEM_PERIDOT = ModItems.makeGem("bop_peridot");
	
	public static final Block HARD_TOPAZ = ModBlocks.simply(new BlockHardStone(BOPBlocks.gem_ore.getStateFromMeta(3),2),"bop_hard_topaz_ore");
	public static final Item GEM_TOPAZ = ModItems.makeGem("bop_topaz");
	
	public static final Block HARD_TANZANITE = ModBlocks.simply(new BlockHardStone(BOPBlocks.gem_ore.getStateFromMeta(4),2),"bop_hard_tanzanite_ore");
	public static final Item GEM_TANZANITE = ModItems.makeGem("bop_tanzanite");
	
	public static final Block HARD_MALACHITE = ModBlocks.simply(new BlockHardStone(BOPBlocks.gem_ore.getStateFromMeta(5),2),"bop_hard_malachite_ore");
	public static final Item GEM_MALACHITE = ModItems.makeGem("bop_malachite");
	
	public static final Block HARD_SAPPHIRE = ModBlocks.simply(new BlockHardStone(BOPBlocks.gem_ore.getStateFromMeta(6),2),"bop_hard_sapphire_ore");
	public static final Item GEM_SAPPHIRE = ModItems.makeGem("bop_sapphire");
	
	public static final Block HARD_AMBER = ModBlocks.simply(new BlockHardStone(BOPBlocks.gem_ore.getStateFromMeta(7),2),"bop_hard_amber_ore");
	public static final Item GEM_AMBER = ModItems.makeGem("bop_amber");
	

	public static void init(){
		
			RockRegistry.addRock(BOPBlocks.gem_ore,0, ModItems.ROCK_ENDSTONE);
			RockRegistry.addRock(BOPBlocks.gem_ore,1, ModItems.ROCK_STONE);
			RockRegistry.addRock(BOPBlocks.gem_ore,2, ModItems.ROCK_STONE);
			RockRegistry.addRock(BOPBlocks.gem_ore,3, ModItems.ROCK_STONE);
			RockRegistry.addRock(BOPBlocks.gem_ore,4, ModItems.ROCK_STONE);
			RockRegistry.addRock(BOPBlocks.gem_ore,5, ModItems.ROCK_STONE);
			RockRegistry.addRock(BOPBlocks.gem_ore,6, ModItems.ROCK_STONE);
			RockRegistry.addRock(BOPBlocks.gem_ore,7, ModItems.ROCK_STONE);

			if(ConfigDivergentUnderground.doGemDrops){
				GemRegistry.addGem(BOPBlocks.gem_ore,0, GEM_AMETHYST);
				GemRegistry.addGem(BOPBlocks.gem_ore,1, GEM_RUBY);
				GemRegistry.addGem(BOPBlocks.gem_ore,2, GEM_PERIDOT);
				GemRegistry.addGem(BOPBlocks.gem_ore,3, GEM_TOPAZ);
				GemRegistry.addGem(BOPBlocks.gem_ore,4, GEM_TANZANITE);
				GemRegistry.addGem(BOPBlocks.gem_ore,5, GEM_MALACHITE);
				GemRegistry.addGem(BOPBlocks.gem_ore,6, GEM_SAPPHIRE);
				GemRegistry.addGem(BOPBlocks.gem_ore,7, GEM_AMBER);
			}
	}

	public static void registerBlocks(RegistryEvent.Register<Block> event) {
		IForgeRegistry<Block> registry = event.getRegistry();

		registry.register(HARD_AMETHYST);
		registry.register(HARD_RUBY);
		registry.register(HARD_PERIDOT);
		registry.register(HARD_TOPAZ);
		registry.register(HARD_TANZANITE);
		registry.register(HARD_MALACHITE);
		registry.register(HARD_SAPPHIRE);
		registry.register(HARD_AMBER);

	}
	
	public static void registerItems(RegistryEvent.Register<Item> event) {
		IForgeRegistry<Item> registry = event.getRegistry();
		
		registry.register(GEM_AMETHYST);
		registry.register(GEM_RUBY);
		registry.register(GEM_PERIDOT);
		registry.register(GEM_TOPAZ);
		registry.register(GEM_TANZANITE);
		registry.register(GEM_MALACHITE);
		registry.register(GEM_SAPPHIRE);
		registry.register(GEM_AMBER);
		ModItems.registerItemBlock(registry, HARD_AMETHYST);
		ModItems.registerItemBlock(registry, HARD_RUBY);
		ModItems.registerItemBlock(registry, HARD_PERIDOT);
		ModItems.registerItemBlock(registry, HARD_TOPAZ);
		ModItems.registerItemBlock(registry, HARD_TANZANITE);
		ModItems.registerItemBlock(registry, HARD_MALACHITE);
		ModItems.registerItemBlock(registry, HARD_SAPPHIRE);
		ModItems.registerItemBlock(registry, HARD_AMBER);
	}
	
    public static void registerOreDicts(){
    	doDicts("Amethyst",GEM_AMETHYST);
    	doDicts("Ruby",GEM_RUBY);
    	doDicts("Peridot",GEM_PERIDOT);
    	doDicts("Topaz",GEM_TOPAZ);
    	doDicts("Tanzanite",GEM_TANZANITE);
    	doDicts("Malachite",GEM_MALACHITE);
    	doDicts("Sapphire",GEM_SAPPHIRE);
    	doDicts("Amber",GEM_AMBER);
    }
    
    private static void doDicts(String type,Item ore){
    	OreDictionary.registerOre("uncut"+type, ore);
    }
    
    public static void registerModels(ModelRegistryEvent event) {
    	ClientProxy.registerItemModel(GEM_AMETHYST);
    	ClientProxy.registerItemModel(GEM_RUBY);
    	ClientProxy.registerItemModel(GEM_PERIDOT);
    	ClientProxy.registerItemModel(GEM_TOPAZ);
    	ClientProxy.registerItemModel(GEM_TANZANITE);
    	ClientProxy.registerItemModel(GEM_MALACHITE);
    	ClientProxy.registerItemModel(GEM_SAPPHIRE);
    	ClientProxy.registerItemModel(GEM_AMBER);
    	ClientProxy.registerBlockModel(HARD_AMETHYST);
    	ClientProxy.registerBlockModel(HARD_RUBY);
    	ClientProxy.registerBlockModel(HARD_PERIDOT);
    	ClientProxy.registerBlockModel(HARD_TOPAZ);
    	ClientProxy.registerBlockModel(HARD_TANZANITE);
    	ClientProxy.registerBlockModel(HARD_MALACHITE);
    	ClientProxy.registerBlockModel(HARD_SAPPHIRE);
    	ClientProxy.registerBlockModel(HARD_AMBER);

    }
}
