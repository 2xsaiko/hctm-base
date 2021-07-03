package net.dblsaiko.serialization.ser;

import net.dblsaiko.serialization.Serialize;

public interface SerializeMapContext {
    <T> void serializeKey(Serialize<T> ser, T key) throws SerializerException;

    <T> void serializeValue(Serialize<T> ser, T value) throws SerializerException;

    default <T, U> void serializeEntry(Serialize<T> serKey, Serialize<U> serValue, T key, U value) throws SerializerException {
        this.serializeKey(serKey, key);
        this.serializeValue(serValue, value);
    }
}
