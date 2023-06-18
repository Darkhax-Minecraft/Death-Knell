package net.darkhax.deathknell.message;

import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;

/**
 * This interface defines a factory for generating death messages.
 */
public interface IDeathMessage {

    /**
     * Generates the message to display for this death message.
     *
     * @param args The message parameters such as victim name and killer name.
     * @return The death message to display.
     */
    Component getMessage(Object... args);

    /**
     * Generates a sub-variant of the death message.
     *
     * @param alt  The variant suffix to generate.
     * @param args The message parameters such as victim name and killer name.
     * @return The death message to display.
     */
    Component getSubMessage(String alt, Object... args);

    /**
     * Remaps certain message parameter types into their display friendly counterparts.
     * <p>
     * LivingEntity -> LivingEntity#getDisplayName
     * <p>
     * ItemStack -> ItemStack#getDisplayName
     *
     * @param args The message parameters to remap.
     * @return The remapped message parameters.
     */
    static Object[] remapArgs(Object[] args) {

        final Object[] remapped = new Object[args.length];

        for (int i = 0; i < args.length; i++) {

            Object obj = args[i];

            if (obj instanceof LivingEntity living) {

                obj = ((LivingEntity) obj).getDisplayName();
            }

            else if (obj instanceof ItemStack stack) {

                obj = stack.getDisplayName();
            }

            remapped[i] = obj;
        }

        return remapped;
    }
}