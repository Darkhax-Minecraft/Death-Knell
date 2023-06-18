package net.darkhax.deathknell.mixin;

import net.minecraft.world.damagesource.CombatEntry;
import net.minecraft.world.damagesource.CombatTracker;
import net.minecraft.world.entity.LivingEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

import java.util.List;

@Mixin(CombatTracker.class)
public interface AccessorCombatTracker {

    @Accessor("entries")
    List<CombatEntry> deathknell$getEntries();

    @Accessor("mob")
    LivingEntity deathknell$getMob();
}