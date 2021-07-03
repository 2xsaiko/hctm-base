package net.dblsaiko.serialization;

public interface Serde<T> extends Serialize<T>, Deserialize<T> {
    static <T> Serde<T> pair(Serialize<T> ser, Deserialize<T> de) {
        if (ser == de) {
            return (Serde<T>) ser;
        }

        throw new IllegalStateException("not implemented");
    }
}
