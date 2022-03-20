package net.darkhax.deathknell.message;

import net.darkhax.deathknell.Constants;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;

/**
 * A death message implementation that produces death messages from a predetermined range of variants.
 */
public record DeathMessageRandom(String... keys) implements IDeathMessage {

    @Override
    public Component getMessage(Object... args) {

        return new TranslatableComponent("message.deathknell." + keys[Constants.RAND.nextInt(keys.length)], IDeathMessage.remapArgs(args));
    }

    @Override
    public Component getSubMessage(String alt, Object... args) {

        return new TranslatableComponent("message.deathknell." + keys[Constants.RAND.nextInt(keys.length)] + "." + alt, IDeathMessage.remapArgs(args));
    }
}