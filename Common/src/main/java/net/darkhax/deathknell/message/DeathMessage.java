package net.darkhax.deathknell.message;

import net.minecraft.Util;
import net.minecraft.commands.CommandSource;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;

public class DeathMessage implements IDeathMessage {

    private final String key;

    public DeathMessage(String key) {

        this.key = "message.deathknell." + key;
        IDeathMessage.MESSAGES.add(this);
    }

    @Override
    public Component getMessage(Object... args) {

        remapArgs(args);
        return new TranslatableComponent(key, args);
    }

    @Override
    public Component getSubMessage(String alt, Object... args) {

        remapArgs(args);
        return new TranslatableComponent(key + "." + alt, args);
    }

    @Override
    public void dumpMessages(CommandSource source, Object... args) {

        remapArgs(args);
        source.sendMessage(this.getMessage(args), Util.NIL_UUID);
    }
}