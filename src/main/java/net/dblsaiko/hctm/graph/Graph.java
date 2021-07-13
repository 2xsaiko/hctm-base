package net.dblsaiko.hctm.graph;

import net.minecraft.nbt.NbtCompound;
import net.minecraft.util.math.ChunkPos;

import java.util.Collection;
import java.util.UUID;

public class Graph {
    private final UUID id;

    private Graph(UUID id) {
        this.id = id;
    }

    public Collection<ChunkPos> getChunks() {
        throw new IllegalStateException("not implemented"); // TODO
    }

    public UUID getId() {
        return this.id;
    }

    public NbtCompound writeNbt(NbtCompound nbt) {
        return nbt;
    }

    public static Graph create() {
        return new Graph(UUID.randomUUID());
    }

    public static Graph fromNbt(UUID id, NbtCompound nbt) {
        return new Graph(id);
    }
}
