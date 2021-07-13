package net.dblsaiko.hctm;

import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;

import java.util.Objects;

import net.dblsaiko.hctm.common.wire.WireNetworkStateKt;
import net.dblsaiko.hctm.init.ItemGroups;
import net.dblsaiko.hctm.init.Items;
import net.dblsaiko.hctm.net.Packets;
import net.dblsaiko.hctm.net.ServerNetHandler;

public class HctmBase {
    public static final String MOD_ID = "hctm-base";

    private static HctmBase INSTANCE;

    public final ItemGroups itemGroups = new ItemGroups();
    public final Items items = new Items(this.itemGroups);

    public final Packets packets = new Packets();
    public final ServerNetHandler serverNetHandler = new ServerNetHandler(this.packets);

    private HctmBase() {}

    public static void initialize() {
        HctmBase mod = new HctmBase();
        mod.items.register();
        mod.serverNetHandler.register();

        ServerTickEvents.END_WORLD_TICK.register(world -> {
            WireNetworkStateKt.getWireNetworkState(world).getController().flushUpdates();
        });

        INSTANCE = mod;
    }

    public static HctmBase getInstance() {
        return Objects.requireNonNull(INSTANCE);
    }
}
