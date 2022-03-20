package net.darkhax.deathknell;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.Tag;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;

public interface IHelper {

    TagKey<Item> createItemTag(ResourceLocation id);

    default TagKey<Item> createItemTag(String namespace, String path) {

        return this.createItemTag(new ResourceLocation(namespace, path));
    }
}
