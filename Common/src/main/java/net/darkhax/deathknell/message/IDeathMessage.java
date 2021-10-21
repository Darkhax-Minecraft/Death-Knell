package net.darkhax.deathknell.message;

import net.minecraft.commands.CommandSource;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;

import java.util.ArrayList;
import java.util.List;

public interface IDeathMessage {

    List<IDeathMessage> MESSAGES = new ArrayList<>();

    Component getMessage(Object... args);

    Component getSubMessage(String alt, Object... args);

    void dumpMessages(CommandSource source, Object... args);

    default void remapArgs(Object[] args) {

        for (int i = 0; i < args.length; i++) {

            final Object obj = args[i];

            if (obj instanceof LivingEntity living) {

                args[i] = ((LivingEntity) obj).getDisplayName();
            }

            else if (obj instanceof ItemStack stack) {

                args[i] = stack.getDisplayName();
            }
        }
    }
}