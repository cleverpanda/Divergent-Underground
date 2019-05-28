package panda.divergentunderground.init;

import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.registries.IForgeRegistry;
import panda.divergentunderground.DivergentUnderground;

@EventBusSubscriber
public final class ModSounds {
	private ModSounds(){DivergentUnderground.logger.info("Registering Sounds");}

	public static final SoundEvent BOULDER_FALLING = simply("boulder_falling");

	private static SoundEvent simply(String name) {
		ResourceLocation resourceLocation = new ResourceLocation(DivergentUnderground.MODID, name);
		 return new SoundEvent(resourceLocation).setRegistryName(resourceLocation);
	}

	@SubscribeEvent
	public static void register(RegistryEvent.Register<SoundEvent> event) {
		IForgeRegistry<SoundEvent> registry = event.getRegistry();

		registry.register(BOULDER_FALLING);
	}
}
