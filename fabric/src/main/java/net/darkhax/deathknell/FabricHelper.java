package net.darkhax.deathknell;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;

public class FabricHelper implements IHelper {

    @Override
    public TagKey<Item> createItemTag(ResourceLocation id) {

        return TagKey.create(Registries.ITEM, id);
    }
}