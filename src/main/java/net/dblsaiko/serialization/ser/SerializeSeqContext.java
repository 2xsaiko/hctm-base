package net.dblsaiko.serialization.ser;

import net.dblsaiko.serialization.Serialize;

public interface SerializeSeqContext {
    <T> void serializeElement(Serialize<T> ser, T value) throws SerializerException;
}
