package net.dblsaiko.hctm.init;

import net.minecraft.util.Identifier;

import java.util.Objects;

import org.jetbrains.annotations.NotNull;

public abstract class AbstractRegistryObject<T> implements InternalRegistryObject<T> {
    private final Identifier id;
    private T obj;

    public AbstractRegistryObject(Identifier id) {
        this.id = id;
    }

    @Override
    public void register() {
        this.obj = this.registerNew();
    }

    protected abstract T registerNew();

    @Override
    public void unregister() {
        this.obj = null;
    }

    @Override
    public final Identifier id() {
        return this.id;
    }

    @Override
    @NotNull
    public final T get() {
        return Objects.requireNonNull(this.obj);
    }
}
