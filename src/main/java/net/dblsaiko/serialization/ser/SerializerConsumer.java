package net.dblsaiko.serialization.ser;

@FunctionalInterface
public interface SerializerConsumer<T> {
    void accept(T value) throws SerializerException;
}
