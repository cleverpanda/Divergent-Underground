package panda.divergentunderground.integration;

import biomesoplenty.api.block.BOPBlocks;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.oredict.OreDictionary;
import net.minecraftforge.registries.IForgeRegistry;
import panda.divergentunderground.ConfigDivergentUnderground;
import panda.divergentunderground.experimental.CompatibilityPlugin;
import panda.divergentunderground.experimental.ICompatibilityPlugin;
import panda.divergentunderground.init.ModBlocks;
import panda.divergentunderground.init.ModItems;
import panda.divergentunderground.proxy.ClientProxy;
import panda.divergentunderground.registries.GemRegistry;
import panda.divergentunderground.registries.RockRegistry;

@CompatibilityPlugin("biomesoplenty")
public class BiomesOPlentyCompat implements ICompatibilityPlugin {

	private static final String texturePath = "biomesoplenty:blocks/";
	private static final String modid = "bop"; //Not true, but naming as shorthand
	
	public static final Block HARD_AMETHYST = ModBlocks.makeHardBlock(BOPBlocks.gem_ore.getStateFromMeta(0),2,texturePath+"amethyst_ore",modid,"amethyst");
	public static final Item GEM_AMETHYST = ModItems.makeGem(modid,"amethyst");
	
	public static final Block HARD_RUBY = ModBlocks.makeHardBlock(BOPBlocks.gem_ore.getStateFromMeta(1),2,texturePath+"ruby_ore",modid,"ruby");
	public static final Item GEM_RUBY = ModItems.makeGem(modid,"ruby");
	
	public static final Block HARD_PERIDOT = ModBlocks.makeHardBlock(BOPBlocks.gem_ore.getStateFromMeta(2),2,texturePath+"peridot_ore",modid,"peridot");
	public static final Item GEM_PERIDOT = ModItems.makeGem(modid,"peridot");
	
	public static final Block HARD_TOPAZ = ModBlocks.makeHardBlock(BOPBlocks.gem_ore.getStateFromMeta(3),2,texturePath+"topaz_ore",modid,"topaz");
	public static final Item GEM_TOPAZ = ModItems.makeGem(modid,"topaz");
	
	public static final Block HARD_TANZANITE = ModBlocks.makeHardBlock(BOPBlocks.gem_ore.getStateFromMeta(4),2,texturePath+"tanzanite_ore",modid,"tanzanite");
	public static final Item GEM_TANZANITE = ModItems.makeGem(modid,"tanzanite");
	
	public static final Block HARD_MALACHITE = ModBlocks.makeHardBlock(BOPBlocks.gem_ore.getStateFromMeta(5),2,texturePath+"malachite_ore",modid,"malachite");
	public static final Item GEM_MALACHITE = ModItems.makeGem(modid,"malachite");
	
	public static final Block HARD_SAPPHIRE = ModBlocks.makeHardBlock(BOPBlocks.gem_ore.getStateFromMeta(6),2,texturePath+"sapphire_ore",modid,"sapphire");
	public static final Item GEM_SAPPHIRE = ModItems.makeGem(modid,"sapphire");
	
	public static final Block HARD_AMBER = ModBlocks.makeHardBlock(BOPBlocks.gem_ore.getStateFromMeta(7),2,texturePath+"amber_ore",modid,"amber");
	public static final Item GEM_AMBER = ModItems.makeGem(modid,"amber");
	
    @Override
    public void preInit() {
        MinecraftForge.EVENT_BUS.register(this);
    }
	
	@Override
	public void init(){
		
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

	@SubscribeEvent
	public void registerBlocks(RegistryEvent.Register<Block> event) {
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
	
	@SubscribeEvent
	public void registerItems(RegistryEvent.Register<Item> event) {
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
    
    @SideOnly(Side.CLIENT)
    @SubscribeEvent
    public void registerModels(ModelRegistryEvent event) {
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
