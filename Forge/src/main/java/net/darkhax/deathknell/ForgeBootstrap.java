package net.darkhax.deathknell;

import net.minecraftforge.fml.common.Mod;

@Mod(Constants.MOD_ID)
public class ForgeBootstrap {
    
    public ForgeBootstrap() {

        CommonBootstrap.init();
    }
}