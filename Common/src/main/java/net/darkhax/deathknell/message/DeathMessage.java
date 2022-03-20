package net.darkhax.deathknell.message;

import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;

/**
 * A simple death message provider.
 */
public record DeathMessage(String key) implements IDeathMessage {

    public DeathMessage(String key) {

        this.key = "message.deathknell." + key;
    }

    @Override
    public Component getMessage(Object... args) {

        return new TranslatableComponent(key, IDeathMessage.remapArgs(args));
    }

    @Override
    public Component getSubMessage(String alt, Object... args) {

        return new TranslatableComponent(key + "." + alt, IDeathMessage.remapArgs(args));
    }
}