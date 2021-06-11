package net.dblsaiko.hctm.init;

import net.minecraft.util.Identifier;

import kotlin.properties.ReadOnlyProperty;
import kotlin.reflect.KProperty;
import org.jetbrains.annotations.NotNull;

public interface RegistryObject<T> extends ReadOnlyProperty<Object, T> {
    Identifier id();

    @NotNull
    T get();

    @NotNull
    @Override
    default T getValue(Object o, @NotNull KProperty<?> kProperty) {
        return this.get();
    }
}
