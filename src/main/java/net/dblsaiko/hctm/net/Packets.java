package net.dblsaiko.hctm.net;

import net.dblsaiko.hctm.init.NetworkRegistry;
import net.dblsaiko.hctm.init.net.ClientboundMsgDef;
import net.dblsaiko.hctm.init.net.ServerboundMsgDef;

import static net.dblsaiko.hctm.HctmBase.MOD_ID;

public class Packets {
    private final NetworkRegistry reg = new NetworkRegistry(MOD_ID);

    public final ServerboundMsgDef<DebugNetRequest> debugNetRequest = this.reg.registerServerbound("debug_net_request", DebugNetRequest.CODEC);
    public final ClientboundMsgDef<DebugNetResponse> debugNetResponse = this.reg.registerClientbound("debug_net_response", DebugNetResponse.CODEC);

    public void registerClientHandlers() {
        this.reg.registerClientHandlers();
    }

    public void registerServerHandlers() {
        this.reg.registerServerHandlers();
    }
}
