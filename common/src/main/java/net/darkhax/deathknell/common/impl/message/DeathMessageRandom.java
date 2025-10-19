package net.darkhax.deathknell.common.impl.message;

import net.darkhax.deathknell.common.api.message.IDeathMessage;
import net.darkhax.deathknell.common.impl.DeathKnell;
import net.minecraft.network.chat.Component;

/**
 * A death message implementation that produces death messages from a predetermined range of variants.
 */
public record DeathMessageRandom(String... keys) implements IDeathMessage {

    @Override
    public Component getMessage(Object... args) {
        return Component.translatable("message.deathknell." + keys[DeathKnell.RNG.nextInt(keys.length)], IDeathMessage.remapArgs(args));
    }

    @Override
    public Component getSubMessage(String alt, Object... args) {
        return Component.translatable("message.deathknell." + keys[DeathKnell.RNG.nextInt(keys.length)] + "." + alt, IDeathMessage.remapArgs(args));
    }
}