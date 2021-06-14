package net.dblsaiko.hctm.init;

import net.minecraft.item.ItemGroup;
import net.minecraft.util.Identifier;
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;

import net.dblsaiko.hctm.HctmBase;

import static net.dblsaiko.hctm.HctmBase.MOD_ID;

public class ItemGroups {
    public final ItemGroup all = FabricItemGroupBuilder.create(new Identifier(MOD_ID, "all"))
        .icon(() -> HctmBase.getInstance().items.getScrewdriver().getDefaultStack())
        .build();
}
