package net.dblsaiko.serialization;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;

import net.dblsaiko.serialization.ser.Serializer;
import net.dblsaiko.serialization.ser.SerializerException;

public interface Serialize<T> {
    void serialize(T self, Serializer serializer) throws SerializerException;

    // impls for standard types

    Serialize<Boolean> BOOL = (self, serializer) -> serializer.serializeBool(self);
    Serialize<Byte> BYTE = (self, serializer) -> serializer.serializeByte(self);
    Serialize<Short> SHORT = (self, serializer) -> serializer.serializeShort(self);
    Serialize<Integer> INT = (self, serializer) -> serializer.serializeInt(self);
    Serialize<Long> LONG = (self, serializer) -> serializer.serializeLong(self);
    Serialize<Float> FLOAT = (self, serializer) -> serializer.serializeFloat(self);
    Serialize<Double> DOUBLE = (self, serializer) -> serializer.serializeDouble(self);
    Serialize<Character> CHAR = (self, serializer) -> serializer.serializeChar(self);
    Serialize<String> STRING = (self, serializer) -> serializer.serializeString(self);
    Serialize<byte[]> BYTES = (self, serializer) -> serializer.serializeBytes(self);

    static <T> Serialize<Optional<T>> optional(Serialize<T> ser) {
        return (self, serializer) -> {
            if (self.isPresent()) {
                serializer.serializeSome(ser, self.get());
            } else {
                serializer.serializeNone();
            }
        };
    }

    static <T> Serialize<List<T>> list(Serialize<T> ser) {
        return (self, serializer) -> {
            serializer.serializeSeq(self.size(), ctx -> {
                for (T el : self) {
                    ctx.serializeElement(ser, el);
                }
            });
        };
    }

    static <T> Serialize<T[]> array(Serialize<T> ser) {
        return (self, serializer) -> {
            serializer.serializeSeq(self.length, ctx -> {
                for (T el : self) {
                    ctx.serializeElement(ser, el);
                }
            });
        };
    }

    static <K, V> Serialize<Map<K, V>> map(Serialize<K> serK, Serialize<V> serV) {
        return (self, serializer) -> {
            serializer.serializeMap(self.size(), ctx -> {
                for (Entry<K, V> entry : self.entrySet()) {
                    ctx.serializeEntry(serK, serV, entry.getKey(), entry.getValue());
                }
            });
        };
    }

    static <T> Serialize<T> record(String name, RecordStructure<T> structure) {
        return (self, serializer) -> {
            serializer.serializeRecord(name, structure.fieldCount(), structure::serializeTo);
        };
    }
}
