package net.darkhax.deathknell;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;

public class ForgeHelper implements IHelper {

    @Override
    public TagKey<Item> createItemTag(ResourceLocation id) {

        return ItemTags.create(id);
    }
}