package net.darkhax.deathknell;

import net.fabricmc.api.ModInitializer;

public class FabricBootstrap implements ModInitializer {

    @Override
    public void onInitialize() {

        CommonBootstrap.init();
    }
}
