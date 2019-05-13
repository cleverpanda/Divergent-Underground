package panda.divergentunderground;

import java.util.Set;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.google.common.collect.Sets;

import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.oredict.OreDictionary;
import panda.divergentunderground.common.eventhandler.EventsHandler;
import panda.divergentunderground.common.world.StoneGenerator;
import panda.divergentunderground.experimental.CompatibilityPlugin;
import panda.divergentunderground.experimental.ICompatibilityPlugin;
import panda.divergentunderground.init.ModItems;
import panda.divergentunderground.init.ModRecipes;
import panda.divergentunderground.proxy.CommonProxy;
import panda.divergentunderground.registries.GemRegistry;
import panda.divergentunderground.registries.OreRegistry;
import panda.divergentunderground.registries.RockRegistry;
import net.minecraftforge.common.config.Configuration;


@Mod(modid = DivergentUnderground.MODID, name = DivergentUnderground.NAME, version = DivergentUnderground.VERSION,
dependencies = "after:biomesoplenty;after:thermalfoundation;after:cofhworld;after:thermalexpansion;after:ic2;after:mekanism;after:forestry;after:immersiveengineering;after:quark;after:basemetals;after:thaumcraft;after:basemetals;")
public class DivergentUnderground {
	public static final String MODID = "divergentunderground";
	public static final String VERSION = "0.59.0";
	public static final String NAME = "Divergent Underground";
	public static SimpleNetworkWrapper wrapper;
	
	@Mod.Instance(MODID)
	public static DivergentUnderground instance;

	@SidedProxy(clientSide = "panda.divergentunderground.proxy.ClientProxy", serverSide = "panda.divergentunderground.proxy.ServerProxy")
	public static CommonProxy proxy;
	public static final Logger logger = LogManager.getLogger(MODID);
	public Configuration config;
	
	public static final Set<ICompatibilityPlugin> COMPAT_PLUGINS = Sets.newHashSet();
	
	@EventHandler
	public void preinit(FMLPreInitializationEvent event){
		COMPAT_PLUGINS.addAll(CompatibilityPlugin.Gather.gather(event.getAsmData()));
		config = new Configuration(event.getSuggestedConfigurationFile());
		ConfigDivergentUnderground.load(config);
		
		GameRegistry.registerWorldGenerator(new StoneGenerator(), Integer.MAX_VALUE);
		MinecraftForge.EVENT_BUS.register(new EventsHandler());
		
		for (ICompatibilityPlugin plugin : COMPAT_PLUGINS)
            plugin.preInit();
	}
	
	@EventHandler
	public void init(FMLInitializationEvent event){
		RockRegistry.initRocks();
		OreRegistry.initOres();
		GemRegistry.initGems();
		ModRecipes.register();

		proxy.registerColorHandlers();
		proxy.registerOreDicts();
		
        for (ICompatibilityPlugin plugin : COMPAT_PLUGINS)
            plugin.init();
	}
	
	public static final CreativeTabs Tab = new CreativeTabs(DivergentUnderground.MODID) {
		@Override
		public ItemStack createIcon() {
			return new ItemStack(ModItems.ORE_IRON);
		}
	};
	
	public static void doDicts(String type,Item ore, Block block){
		OreDictionary.registerOre("ore"+type, block);
		OreDictionary.registerOre("ore"+type, ore);
		OreDictionary.registerOre("rockOre"+type,ore);
	}
}