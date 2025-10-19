package net.darkhax.deathknell.common.impl;

import net.darkhax.deathknell.common.api.message.IDeathMessage;
import net.darkhax.deathknell.common.impl.message.DeathMessage;
import net.darkhax.deathknell.common.impl.message.DeathMessageRandom;
import net.darkhax.deathknell.common.mixin.AccessorCombatTracker;
import net.minecraft.core.component.DataComponents;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.contents.TranslatableContents;
import net.minecraft.tags.DamageTypeTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.damagesource.CombatEntry;
import net.minecraft.world.damagesource.CombatTracker;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.animal.IronGolem;
import net.minecraft.world.entity.animal.PolarBear;
import net.minecraft.world.entity.monster.Blaze;
import net.minecraft.world.entity.monster.CaveSpider;
import net.minecraft.world.entity.monster.Drowned;
import net.minecraft.world.entity.monster.Guardian;
import net.minecraft.world.entity.monster.Slime;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.AxeItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;

import java.util.Random;

public class DeathKnell {

    public static final String MOD_ID = "deathknell";
    public static final Random RNG = new Random();

    // Messages
    private static final IDeathMessage GENERIC_SLAIN = new DeathMessageRandom("thwarted", "bonked", "defeated", "butchered", "assassinate", "eliminated", "extinguished", "terminated", "done_in", "executed", "stopped", "stifle", "slaughter", "exterminated", "vanquished", "bested", "trounced", "ended", "perished", "demise");
    private static final IDeathMessage DEATH_BY_COOKIE = new DeathMessage("death_by_cookie");
    private static final IDeathMessage DEATH_BY_BOOK = new DeathMessage("death_by_book");
    private static final IDeathMessage DEATH_BY_AXE = new DeathMessage("death_by_axe");
    private static final IDeathMessage BURNED_ALIVE = new DeathMessageRandom("incinerated", "reduce_to_ash", "cooked_alive", "fire_out");
    private static final IDeathMessage SPIDER_VENOM = new DeathMessage("spider_venom");
    private static final IDeathMessage SLIME_DEATH = new DeathMessageRandom("dissolve", "slime_food");
    private static final IDeathMessage POLAR_BEAR_DEATH = new DeathMessageRandom("respect_habitat", "disturb_den");
    private static final IDeathMessage IRON_GOLEM_DEATH = new DeathMessage("saved_from_village");
    private static final IDeathMessage DROWNED_DEATH = new DeathMessage("watery_grave");
    private static final IDeathMessage GUARDIAN_DEATH = new DeathMessage("stared_down");
    private static final IDeathMessage PLAYER_DEATH = new DeathMessage("pwned");
    private static final IDeathMessage FALL_DEATH = new DeathMessageRandom("fall_bounce", "fall_gravity", "fall_parachute", "fall_stub", "free_fall");
    private static final IDeathMessage DROWN_DEATH = new DeathMessageRandom("drown_breath", "drown_fishes", "drown_fish_food", "drown_shark_bait", "drown_floundered");
    private static final IDeathMessage ELYTRA_WALL_DEATH = new DeathMessageRandom("elytra_wall_bang", "elytra_wall_crash");
    private static final IDeathMessage VOID_DEATH = new DeathMessageRandom("void_abyss", "void_infinity", "void_divide");


    private static CombatEntry getLastCombatEntry(AccessorCombatTracker tracker) {
        return tracker.deathknell$getEntries().isEmpty() ? null : tracker.deathknell$getEntries().get(tracker.deathknell$getEntries().size() - 1);
    }

    public static Component getCustomDeathMessage(Component original, CombatTracker tracker) {

        if (tracker instanceof AccessorCombatTracker combat) {

            final CombatEntry lastEntry = getLastCombatEntry(combat);
            final DamageSource source = lastEntry != null ? lastEntry.source() : null;
            final LivingEntity deadMob = combat.deathknell$getMob();
            final LivingEntity killer = deadMob.getKillCredit();

            if (killer == null) {
                if (source != null) {
                    if (source.is(DamageTypeTags.IS_FALL) && tryPercent(0.6f)) {
                        return FALL_DEATH.getMessage(deadMob);
                    }
                    if (source.is(DamageTypeTags.IS_DROWNING) && tryPercent(0.6f)) {
                        return DROWN_DEATH.getMessage(deadMob);
                    }
                    if (source.is(DamageTypes.FLY_INTO_WALL) && tryPercent(0.6f)) {
                        return ELYTRA_WALL_DEATH.getMessage(deadMob);
                    }
                    if (source.is(DamageTypeTags.ALWAYS_MOST_SIGNIFICANT_FALL) && tryPercent(0.6f)) {
                        return VOID_DEATH.getMessage(deadMob);
                    }
                }
            }

            if (killer != null) {

                final TranslatableContents translatable = original.getContents() instanceof TranslatableContents msg ? msg : null;

                final ItemStack murderWeapon = killer.getMainHandItem();
                final boolean wasGenericKill = wasGenericMobKill(translatable) || wasGenericPlayerKill(translatable);

                // Mob specific death messages
                switch (killer) {
                    case Blaze blaze when tryPercent(0.65f) -> {
                        return BURNED_ALIVE.getMessage(deadMob, killer);
                    }
                    case CaveSpider caveSpider when tryPercent(0.9f) -> {
                        return SPIDER_VENOM.getMessage(deadMob, killer);
                    }
                    case Slime slime when tryPercent(0.9f) -> {
                        return SLIME_DEATH.getMessage(deadMob, killer);
                    }
                    case PolarBear polarBear when tryPercent(0.9f) -> {
                        return POLAR_BEAR_DEATH.getMessage(deadMob, killer);
                    }
                    case IronGolem ironGolem when tryPercent(0.9f) -> {
                        return IRON_GOLEM_DEATH.getMessage(deadMob, killer);
                    }
                    case Drowned drowned when tryPercent(0.40f) -> {
                        return DROWNED_DEATH.getMessage(deadMob, killer);
                    }
                    case Guardian guardian when tryPercent(0.40f) -> {

                        return GUARDIAN_DEATH.getMessage(deadMob, killer);
                    }
                    case Player player when tryPercent(0.2f) -> {
                        return hasCustomHoverName(murderWeapon) ? PLAYER_DEATH.getSubMessage("item", deadMob, killer, murderWeapon) : PLAYER_DEATH.getMessage(deadMob, killer);
                    }
                    default -> {
                    }
                }

                // Handle kills with specific types of items when the item used was not customised.
                if (wasGenericKill) {
                    if (murderWeapon.is(Items.COOKIE)) {
                        return DEATH_BY_COOKIE.getMessage(deadMob, killer);
                    }
                    else if (murderWeapon.is(ItemTags.BOOKSHELF_BOOKS)) {
                        return DEATH_BY_BOOK.getMessage(deadMob, killer);
                    }
                    else if (murderWeapon.getItem() instanceof AxeItem || murderWeapon.is(ItemTags.AXES)) {
                        return DEATH_BY_AXE.getMessage(deadMob, killer);
                    }
                    // 85% chance to replace vanilla generic death messages
                    else if (tryPercent(0.85f)) {
                        return hasCustomHoverName(murderWeapon) ? GENERIC_SLAIN.getSubMessage("item", deadMob, killer, murderWeapon) : GENERIC_SLAIN.getMessage(deadMob, killer);
                    }
                }
            }
        }

        return original;
    }

    private static boolean hasCustomHoverName(ItemStack stack) {
        return stack.has(DataComponents.CUSTOM_NAME);
    }

    private static boolean wasGenericMobKill(TranslatableContents message) {
        return message != null && message.getKey().startsWith("death.attack.mob");
    }

    private static boolean wasGenericPlayerKill(TranslatableContents message) {
        return message != null && message.getKey().startsWith("death.attack.player");
    }

    private static boolean tryPercent(float percent) {
        return RNG.nextFloat() < percent;
    }
}