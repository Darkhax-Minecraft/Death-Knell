package net.darkhax.deathknell.common.impl.message;

import net.darkhax.deathknell.common.api.message.IDeathMessage;
import net.minecraft.network.chat.Component;

/**
 * A simple death message provider.
 */
public record DeathMessage(String key) implements IDeathMessage {

    public DeathMessage(String key) {
        this.key = "message.deathknell." + key;
    }

    @Override
    public Component getMessage(Object... args) {
        return Component.translatable(key, IDeathMessage.remapArgs(args));
    }

    @Override
    public Component getSubMessage(String alt, Object... args) {
        return Component.translatable(key + "." + alt, IDeathMessage.remapArgs(args));
    }
}