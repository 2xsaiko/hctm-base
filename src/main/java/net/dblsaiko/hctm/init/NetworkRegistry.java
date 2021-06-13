package net.dblsaiko.hctm.init;

import net.minecraft.util.Identifier;
import com.mojang.serialization.Codec;

import java.util.HashMap;
import java.util.Map;

import net.dblsaiko.hctm.init.net.ClientboundMsgDef;
import net.dblsaiko.hctm.init.net.ServerboundMsgDef;

public class NetworkRegistry {
    private final String modId;
    private final Map<String, ServerboundMsgDef<?>> sbDefs = new HashMap<>();
    private final Map<String, ClientboundMsgDef<?>> cbDefs = new HashMap<>();

    public NetworkRegistry(String modId) {
        this.modId = modId;
    }

    public <T> ServerboundMsgDef<T> registerServerbound(String name, Codec<T> codec) {
        this.checkUnregistered(name);
        Identifier id = new Identifier(this.modId, name);
        ServerboundMsgDef<T> def = new ServerboundMsgDef<>(id, codec);
        this.sbDefs.put(name, def);
        return def;
    }

    public <T> ClientboundMsgDef<T> registerClientbound(String name, Codec<T> codec) {
        this.checkUnregistered(name);
        Identifier id = new Identifier(this.modId, name);
        ClientboundMsgDef<T> def = new ClientboundMsgDef<>(id, codec);
        this.cbDefs.put(name, def);
        return def;
    }

    private void checkUnregistered(String name) {
        if (this.sbDefs.containsKey(name)) {
            throw new IllegalStateException("Duplicate registering of packet '%s'".formatted(name));
        }
    }

    public void registerClientHandlers() {
        for (ClientboundMsgDef<?> value : this.cbDefs.values()) {
            value.registerHandler();
        }
    }

    public void registerServerHandlers() {
        for (ServerboundMsgDef<?> value : this.sbDefs.values()) {
            value.registerHandler();
        }
    }

    public enum Side {
        SERVER,
        CLIENT,
    }
}
