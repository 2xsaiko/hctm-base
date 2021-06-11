package net.dblsaiko.hctm.init;

import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.Item.Settings;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

import org.jetbrains.annotations.NotNull;

public class ItemRegistry {
    private final String modId;
    private final Settings blockDefault;

    private List<InternalRegistryObject<? extends Item>> all = new ArrayList<>();

    public ItemRegistry(String modId) {
        this(modId, new Item.Settings());
    }

    public ItemRegistry(String modId, Item.Settings blockDefault) {
        this.modId = modId;
        this.blockDefault = blockDefault;
    }

    @NotNull
    public <T extends Item> RegistryObject<T> create(String name, T item) {
        var o = new RegistryObjectImpl<>(new Identifier(this.modId, name), item);
        this.all.add(o);
        return o;
    }

    @NotNull
    public <T extends Item> RegistryObject<T> createThen(String name, Supplier<T> item) {
        var o = new DeferredRegistryObjectImpl<>(new Identifier(this.modId, name), item);
        this.all.add(o);
        return o;
    }

    @NotNull
    public RegistryObject<BlockItem> create(String name, RegistryObject<? extends Block> block) {
        return this.createThen(name, () -> new BlockItem(block.get(), this.blockDefault));
    }

    public void register() {
        this.all.forEach(InternalRegistryObject::register);
    }

    public void unregister() {
        this.all.forEach(InternalRegistryObject::unregister);
    }

    private static final class RegistryObjectImpl<T extends Item> extends AbstractRegistryObject<T> {
        private final T item;

        private RegistryObjectImpl(Identifier id, T item) {
            super(id);
            this.item = item;
        }

        @Override
        protected T registerNew() {
            return Registry.register(Registry.ITEM, this.id(), this.item);
        }
    }

    private static final class DeferredRegistryObjectImpl<T extends Item> extends AbstractRegistryObject<T> {
        private final Supplier<T> item;

        private DeferredRegistryObjectImpl(Identifier id, Supplier<T> item) {
            super(id);
            this.item = item;
        }

        @Override
        protected T registerNew() {
            return Registry.register(Registry.ITEM, this.id(), this.item.get());
        }
    }
}
