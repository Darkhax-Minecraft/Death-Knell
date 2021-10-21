package net.darkhax.deathknell.message;

import net.darkhax.deathknell.Constants;
import net.minecraft.Util;
import net.minecraft.commands.CommandSource;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;

public class DeathMessageRandom implements IDeathMessage {

    private final String[] keys;

    public DeathMessageRandom(String... keys) {

        this.keys = keys;
        IDeathMessage.MESSAGES.add(this);
    }

    @Override
    public Component getMessage(Object... args) {

        remapArgs(args);
        return new TranslatableComponent("message.deathknell." + keys[Constants.RAND.nextInt(keys.length)], args);
    }

    @Override
    public Component getSubMessage(String alt, Object... args) {

        remapArgs(args);
        return new TranslatableComponent("message.deathknell." + keys[Constants.RAND.nextInt(keys.length)] + "." + alt, args);
    }

    @Override
    public void dumpMessages(CommandSource source, Object... args) {

        remapArgs(args);
        for (String key : this.keys) {

            source.sendMessage(new TranslatableComponent("message.deathknell." + key, args), Util.NIL_UUID);
        }
    }
}