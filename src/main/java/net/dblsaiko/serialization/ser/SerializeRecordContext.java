package net.dblsaiko.serialization.ser;

import net.dblsaiko.serialization.Serialize;

public interface SerializeRecordContext {
    <T> void serializeField(Serialize<T> ser, String key, T value) throws SerializerException;
}
