package net.darkhax.deathknell;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.Tag;
import net.minecraft.world.item.Item;

public class ForgeHelper implements IHelper {

    @Override
    public Tag.Named<Item> createItemTag(ResourceLocation id) {

        return ItemTags.createOptional(id);
    }
}