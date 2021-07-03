package net.dblsaiko.serialization;

import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Supplier;

import net.dblsaiko.serialization.fn.Function3;
import net.dblsaiko.serialization.fn.Function4;
import net.dblsaiko.serialization.fn.Function5;
import net.dblsaiko.serialization.fn.Function6;
import net.dblsaiko.serialization.fn.Function7;
import net.dblsaiko.serialization.fn.Function8;
import net.dblsaiko.serialization.fn.Function9;
import net.dblsaiko.serialization.ser.SerializeRecordContext;
import net.dblsaiko.serialization.ser.SerializerException;

public interface RecordStructure<T> {
    int fieldCount();

    void serializeTo(SerializeRecordContext ctx) throws SerializerException;

    static <T> Builder<T> builder() {
        throw new IllegalStateException("not implemented"); // TODO
    }

    interface Builder<T> {
        Components0<T> components();

        <F1> Components1<T, F1> components(RecordField<T, F1> f1);

        <F1, F2> Components2<T, F1, F2> components(
            RecordField<T, F1> f1,
            RecordField<T, F2> f2
        );

        <F1, F2, F3> Components3<T, F1, F2, F3> components(
            RecordField<T, F1> f1,
            RecordField<T, F2> f2,
            RecordField<T, F3> f3
        );

        <F1, F2, F3, F4> Components4<T, F1, F2, F3, F4> components(
            RecordField<T, F1> f1,
            RecordField<T, F2> f2,
            RecordField<T, F3> f3,
            RecordField<T, F4> f4
        );

        <F1, F2, F3, F4, F5> Components5<T, F1, F2, F3, F4, F5> components(
            RecordField<T, F1> f1,
            RecordField<T, F2> f2,
            RecordField<T, F3> f3,
            RecordField<T, F4> f4,
            RecordField<T, F5> f5
        );

        <F1, F2, F3, F4, F5, F6> Components6<T, F1, F2, F3, F4, F5, F6> components(
            RecordField<T, F1> f1,
            RecordField<T, F2> f2,
            RecordField<T, F3> f3,
            RecordField<T, F4> f4,
            RecordField<T, F5> f5,
            RecordField<T, F6> f6
        );

        <F1, F2, F3, F4, F5, F6, F7>
        Components7<T, F1, F2, F3, F4, F5, F6, F7> components(
            RecordField<T, F1> f1,
            RecordField<T, F2> f2,
            RecordField<T, F3> f3,
            RecordField<T, F4> f4,
            RecordField<T, F5> f5,
            RecordField<T, F6> f6,
            RecordField<T, F7> f7
        );

        <F1, F2, F3, F4, F5, F6, F7, F8>
        Components8<T, F1, F2, F3, F4, F5, F6, F7, F8> components(
            RecordField<T, F1> f1,
            RecordField<T, F2> f2,
            RecordField<T, F3> f3,
            RecordField<T, F4> f4,
            RecordField<T, F5> f5,
            RecordField<T, F6> f6,
            RecordField<T, F7> f7,
            RecordField<T, F8> f8
        );

        <F1, F2, F3, F4, F5, F6, F7, F8, F9>
        Components9<T, F1, F2, F3, F4, F5, F6, F7, F8, F9> components(
            RecordField<T, F1> f1,
            RecordField<T, F2> f2,
            RecordField<T, F3> f3,
            RecordField<T, F4> f4,
            RecordField<T, F5> f5,
            RecordField<T, F6> f6,
            RecordField<T, F7> f7,
            RecordField<T, F8> f8,
            RecordField<T, F9> f9
        );
    }

    interface RecordField<T, F> {
        static <T, F> RecordField<T, F> of(Serde<F> sd, String name, Function<T, F> extractor) {
            throw new IllegalStateException("not implemented");
        }

        String name();

        Serde<F> serde();

        F get(T record);
    }

    interface Components0<T> {
        RecordStructure<T> pack(T instance);

        RecordStructure<T> pack(Supplier<T> constructor);
    }

    interface Components1<T, F1> {
        RecordStructure<T> pack(Function<F1, T> constructor);
    }

    interface Components2<T, F1, F2> {
        RecordStructure<T> pack(BiFunction<F1, F2, T> constructor);
    }

    interface Components3<T, F1, F2, F3> {
        RecordStructure<T> pack(Function3<F1, F2, F3, T> constructor);
    }

    interface Components4<T, F1, F2, F3, F4> {
        RecordStructure<T> pack(Function4<F1, F2, F3, F4, T> constructor);
    }

    interface Components5<T, F1, F2, F3, F4, F5> {
        RecordStructure<T> pack(Function5<F1, F2, F3, F4, F5, T> constructor);
    }

    interface Components6<T, F1, F2, F3, F4, F5, F6> {
        RecordStructure<T> pack(Function6<F1, F2, F3, F4, F5, F6, T> constructor);
    }

    interface Components7<T, F1, F2, F3, F4, F5, F6, F7> {
        RecordStructure<T> pack(Function7<F1, F2, F3, F4, F5, F6, F7, T> constructor);
    }

    interface Components8<T, F1, F2, F3, F4, F5, F6, F7, F8> {
        RecordStructure<T> pack(Function8<F1, F2, F3, F4, F5, F6, F7, F8, T> constructor);
    }

    interface Components9<T, F1, F2, F3, F4, F5, F6, F7, F8, F9> {
        RecordStructure<T> pack(Function9<F1, F2, F3, F4, F5, F6, F7, F8, F9, T> constructor);
    }
}
