package panda.divergentunderground;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import net.minecraftforge.fml.common.registry.GameRegistry;
import panda.divergentunderground.api.OreRegistry;
import panda.divergentunderground.api.RockRegistry;
import panda.divergentunderground.common.eventhandler.EventsHandler;
import panda.divergentunderground.common.world.StoneGenerator;
import panda.divergentunderground.init.ModItems;
import panda.divergentunderground.init.ModRecipes;
import panda.divergentunderground.proxy.CommonProxy;
import net.minecraftforge.common.config.Configuration;


@Mod(modid = DivergentUnderground.MODID, name = DivergentUnderground.NAME, version = DivergentUnderground.VERSION)
public class DivergentUnderground {
	public static final String MODID = "divergentunderground";
	public static final String VERSION = "0.44.0";
	public static final String NAME = "Divergent Underground";
	public static SimpleNetworkWrapper wrapper;
	
	@Mod.Instance(MODID)
	public static DivergentUnderground instance;

	@SidedProxy(clientSide = "panda.divergentunderground.proxy.ClientProxy", serverSide = "panda.divergentunderground.proxy.ServerProxy")
	public static CommonProxy proxy;
	public static final Logger logger = LogManager.getLogger(MODID);
	public Configuration config;
	
	@EventHandler
	public void preinit(FMLPreInitializationEvent event){

		config = new Configuration(event.getSuggestedConfigurationFile());
		ConfigDivergentUnderground.load(config);
		ModRecipes.register();
		GameRegistry.registerWorldGenerator(new StoneGenerator(), Integer.MAX_VALUE);
		
		MinecraftForge.EVENT_BUS.register(new EventsHandler());
	}
	
	@EventHandler
	public void init(FMLInitializationEvent event){
		RockRegistry.initRocks();
		OreRegistry.initOres();
		proxy.registerColorHandlers();
	}
	
	public static final CreativeTabs Tab = new CreativeTabs(DivergentUnderground.MODID) {
		@Override
		public ItemStack getTabIconItem() {
			return new ItemStack(ModItems.ORE_IRON);
		}
	};
	
}