package net.dblsaiko.serialization.ser;

import net.dblsaiko.serialization.Serialize;

public interface Serializer {
    void serializeBool(boolean v) throws SerializerException;

    void serializeByte(byte v) throws SerializerException;

    void serializeShort(short v) throws SerializerException;

    void serializeInt(int v) throws SerializerException;

    void serializeLong(long v) throws SerializerException;

    void serializeFloat(float v) throws SerializerException;

    void serializeDouble(double v) throws SerializerException;

    void serializeChar(char v) throws SerializerException;

    void serializeString(String v) throws SerializerException;

    void serializeBytes(byte[] v) throws SerializerException;

    void serializeNone() throws SerializerException;

    <T> void serializeSome(Serialize<T> ser, T v) throws SerializerException;

    // len = -1 means unknown
    void serializeSeq(long len, SerializerConsumer<SerializeSeqContext> op) throws SerializerException;

    // len = -1 means unknown
    void serializeMap(long len, SerializerConsumer<SerializeMapContext> op) throws SerializerException;

    void serializeRecord(String name, long len, SerializerConsumer<SerializeRecordContext> op) throws SerializerException;

    void serializeRecordVariant(String name, int variantIndex, String variant, long len, SerializerConsumer<SerializeRecordVariantContext> op) throws SerializerException;
}
