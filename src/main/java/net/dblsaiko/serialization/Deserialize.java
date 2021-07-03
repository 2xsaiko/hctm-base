package net.dblsaiko.serialization;

import net.dblsaiko.serialization.de.Deserializer;
import net.dblsaiko.serialization.de.DeserializerException;

public interface Deserialize<T> {
    T deserialize(Deserializer deserializer) throws DeserializerException;
}
