package net.darkhax.deathknell.mixin;

import net.darkhax.deathknell.CommonBootstrap;
import net.minecraft.network.chat.Component;
import net.minecraft.world.damagesource.CombatTracker;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(CombatTracker.class)
public abstract class MixinCombatTracker {

    @Inject(method = "getDeathMessage", at = @At("RETURN"), cancellable = true)
    public void getDeathMessage(CallbackInfoReturnable<Component> callback) {

        final CombatTracker self = (CombatTracker)(Object)this;
        callback.setReturnValue(CommonBootstrap.getCustomDeathMessage(callback.getReturnValue(), self));
    }
}