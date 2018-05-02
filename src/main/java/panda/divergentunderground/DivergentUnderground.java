package panda.divergentunderground;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLConstructionEvent;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import net.minecraftforge.fml.common.registry.GameRegistry;
import panda.divergentunderground.api.GemRegistry;
import panda.divergentunderground.api.OreRegistry;
import panda.divergentunderground.api.RockRegistry;
import panda.divergentunderground.api.compatabiliy.ThermalCompat;
import panda.divergentunderground.common.eventhandler.EventsHandler;
import panda.divergentunderground.common.world.StoneGenerator;
import panda.divergentunderground.init.ModItems;
import panda.divergentunderground.init.ModRecipes;
import panda.divergentunderground.proxy.CommonProxy;
import net.minecraftforge.common.config.Configuration;


@Mod(modid = DivergentUnderground.MODID, name = DivergentUnderground.NAME, version = DivergentUnderground.VERSION,
dependencies = "after:thermalfoundation@[2.4,);"+ "after:cofhworld;"+ "after:thermalexpansion;")
public class DivergentUnderground {
	public static final String MODID = "divergentunderground";
	public static final String VERSION = "0.47.1";
	public static final String NAME = "Divergent Underground";
	public static SimpleNetworkWrapper wrapper;
	
	@Mod.Instance(MODID)
	public static DivergentUnderground instance;

	@SidedProxy(clientSide = "panda.divergentunderground.proxy.ClientProxy", serverSide = "panda.divergentunderground.proxy.ServerProxy")
	public static CommonProxy proxy;
	public static final Logger logger = LogManager.getLogger(MODID);
	public Configuration config;
	
	public static boolean Thermalenabled;
	
	@EventHandler
	public void preinit(FMLPreInitializationEvent event){

		config = new Configuration(event.getSuggestedConfigurationFile());
		ConfigDivergentUnderground.load(config);
		
		GameRegistry.registerWorldGenerator(new StoneGenerator(), Integer.MAX_VALUE);
		MinecraftForge.EVENT_BUS.register(new EventsHandler());
		
		
	}
	
	@EventHandler
	public void init(FMLInitializationEvent event){
		RockRegistry.initRocks();
		OreRegistry.initOres();
		GemRegistry.initGems();
		ModRecipes.register();
		ThermalCompat.init();
		proxy.registerColorHandlers();
		proxy.registerOreDicts();
		
	}
	
	@Mod.EventHandler
	  public void onConstructionEvent(FMLConstructionEvent event) {
	    //if (Loader.isModLoaded("gamestages")) {
	     // this.moduleManager.registerModules(ModuleRequirementGameStages.class);
	    //}
		Thermalenabled = Loader.isModLoaded("thermalfoundation");

	  }
	
	public static final CreativeTabs Tab = new CreativeTabs(DivergentUnderground.MODID) {
		@Override
		public ItemStack getTabIconItem() {
			return new ItemStack(ModItems.ORE_IRON);
		}
	};
	
}