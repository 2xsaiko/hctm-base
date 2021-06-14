package net.dblsaiko.hctm.init;

import net.minecraft.item.Item;

import static net.dblsaiko.hctm.HctmBase.MOD_ID;

public class Items {
    private final ItemRegistry reg = new ItemRegistry(MOD_ID);

    public final RegistryObject<Item> screwdriver;

    public Items(ItemGroups itemGroups) {
        this.screwdriver = this.reg.create("screwdriver", new Item(new Item.Settings().group(itemGroups.all)));
    }

    public Item getScrewdriver() {
        return this.screwdriver.get();
    }

    public void register() {
        this.reg.register();
    }
}
