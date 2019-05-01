package panda.divergentunderground.experimental;

import net.minecraftforge.common.config.Configuration;

//MIT License (MIT) Copyright (c) 2018 TehNut
//https://github.com/TehNut/Soul-Shards-Respawn
public interface ICompatibilityPlugin {
	
    boolean isEnabled = false;

	default void handleConfiguration(Configuration configuration) {

    }

    default void preInit() {

    }

    default void init() {

    }

    default void postInit() {

    }
}