package net.darkhax.deathknell;

import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.tags.Tag;
import net.minecraft.world.damagesource.CombatTracker;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;

public class CommonBootstrap {

    private static Tag.Named<Item> COOKIES = bind("cookies");
    private static Tag.Named<Item> BOOKS = bind("books");

    public static void init() {

        Constants.LOG.info("init");
    }

    public static Component getCustomDeathMessage(Component original, CombatTracker tracker) {

        for (Item value : COOKIES.getValues()) {

            System.out.println(value);
        }

        System.out.println(COOKIES.contains(Items.COOKIE));

        final LivingEntity deadMob = tracker.getMob();

        //@Nullable
        final LivingEntity killer = tracker.getKiller();

        //@Nullable
        final TranslatableComponent translatable = original instanceof TranslatableComponent msg ? msg : null;

        if (killer != null) {

            final ItemStack murderWeapon = killer.getMainHandItem();

            if (wasGenericMobKill(translatable, true) || wasGenericPlayerKill(translatable, true)) {

                if (murderWeapon.is(COOKIES)) {

                    return getKilledByMessage("message.deathknell.death_by_cookie", killer, deadMob);
                }

                if (murderWeapon.is(BOOKS)) {

                    return getKilledByMessage("message.deathknell.death_by_book", killer, deadMob);
                }
            }
        }

        return original;
    }

    private static Component getKilledByMessage(String key, LivingEntity firstName, LivingEntity secondName) {

        return new TranslatableComponent(key, firstName.getDisplayName(), secondName.getDisplayName());
    }

    private static boolean wasGenericMobKill(TranslatableComponent message, boolean includeItemKill) {

        return message != null && ("death.attack.mob".equals(message.getKey()) || (includeItemKill && "death.attack.mob.item".equals(message.getKey())));
    }

    private static boolean wasGenericPlayerKill(TranslatableComponent message, boolean includeItemKill) {

        return message != null && ("death.attack.player".equals(message.getKey()) || (includeItemKill && "death.attack.player.item".equals(message.getKey())));
    }

    private static Tag.Named<Item> bind(String tagId) {

        return Services.HELPER.createItemTag(Constants.MOD_ID, tagId);
    }
}