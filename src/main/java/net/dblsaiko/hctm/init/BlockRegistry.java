package net.dblsaiko.hctm.init;

import net.minecraft.block.Block;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

import java.util.ArrayList;
import java.util.List;

import org.jetbrains.annotations.NotNull;

public class BlockRegistry {
    private final String modId;

    private List<InternalRegistryObject<? extends Block>> all = new ArrayList<>();

    public BlockRegistry(String modId) {
        this.modId = modId;
    }

    @NotNull
    public <T extends Block> RegistryObject<T> create(String name, T block) {
        RegistryObjectImpl<T> o = new RegistryObjectImpl<>(new Identifier(this.modId, name), block);
        this.all.add(o);
        return o;
    }

    public void register() {
        this.all.forEach(InternalRegistryObject::register);
    }

    public void unregister() {
        this.all.forEach(InternalRegistryObject::unregister);
    }

    private static final class RegistryObjectImpl<T extends Block> extends AbstractRegistryObject<T> {
        private final T block;

        private RegistryObjectImpl(Identifier id, T block) {
            super(id);
            this.block = block;
        }

        @Override
        protected T registerNew() {
            return Registry.register(Registry.BLOCK, this.id(), this.block);
        }
    }
}
