package net.dblsaiko.hctm.init;

public interface InternalRegistryObject<T> extends RegistryObject<T> {
    void register();

    void unregister();
}
