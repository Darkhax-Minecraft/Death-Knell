package net.darkhax.deathknell;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.Tag;
import net.minecraft.world.item.Item;

public interface IHelper {

    Tag.Named<Item> createItemTag(ResourceLocation id);

    default Tag.Named<Item> createItemTag(String namespace, String path) {

        return this.createItemTag(new ResourceLocation(namespace, path));
    }
}
