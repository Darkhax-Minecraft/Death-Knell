package net.darkhax.deathknell;

import net.fabricmc.fabric.api.tag.TagFactory;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.Tag;
import net.minecraft.world.item.Item;

public class FabricHelper implements IHelper {

    @Override
    public Tag.Named<Item> createItemTag(ResourceLocation id) {

        Constants.LOG.info("Create tag with id " + id.toString());
        return TagFactory.ITEM.create(id);
    }
}