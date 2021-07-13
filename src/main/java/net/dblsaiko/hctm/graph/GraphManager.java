package net.dblsaiko.hctm.graph;

import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtIo;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.WorldSavePath;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.world.PersistentState;
import net.minecraft.world.chunk.WorldChunk;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;

public class GraphManager extends PersistentState {
    private final ServerWorld world;

    private Multimap<ChunkPos, UUID> graphsInChunk = HashMultimap.create();
    private Map<UUID, Graph> graphs = new HashMap<>();
    private Set<UUID> toUnload = new HashSet<>();

    private GraphManager(ServerWorld world) {
        this.world = world;
    }

    public void onChunkLoad(WorldChunk chunk) {
        for (UUID uuid : this.graphsInChunk.get(chunk.getPos())) {
            this.loadGraph(uuid);
        }
    }

    public void onChunkUnload(WorldChunk chunk) {
        ChunkPos pos = chunk.getPos();
        List<Graph> graphs = this.graphs.values().stream()
            .filter(g -> g.getChunks().stream().allMatch(cp -> cp.equals(pos) || !this.world.isChunkLoaded(cp.x, cp.z)))
            .toList();

        for (Graph graph : graphs) {
            this.unloadGraph(graph);
        }
    }

    private boolean loadGraph(UUID uuid) {
        this.toUnload.remove(uuid);

        if (this.graphs.containsKey(uuid)) {
            return true;
        }

        throw new IllegalStateException("not implemented");
    }

    private boolean write(Graph graph) {
        Path dir = this.world.getServer().getSavePath(WorldSavePath.ROOT).resolve("hctm-graphs");

        try {
            UUID id = graph.getId();
            String fileName = "%016X%016X.nbt.gz".formatted(id.getMostSignificantBits(), id.getLeastSignificantBits());
            String prefix = fileName.substring(0, 2);
            Path output = Files.createDirectories(dir.resolve(prefix)).resolve(fileName);
            NbtCompound nbt = graph.writeNbt(new NbtCompound());
            NbtIo.writeCompressed(nbt, output.toFile());
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }

        return true;
    }

    private Graph read(UUID id) {
        String fileName = "%016X%016X.nbt.gz".formatted(id.getMostSignificantBits(), id.getLeastSignificantBits());
        Path input = this.world.getServer().getSavePath(WorldSavePath.ROOT)
            .resolve("hctm-graphs")
            .resolve(fileName.substring(0, 2))
            .resolve(fileName);

        try {
            NbtCompound nbtCompound = NbtIo.readCompressed(input.toFile());
            Graph.fromNbt(id, nbtCompound);
        } catch (FileNotFoundException e) {
            return null;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }

        throw new IllegalStateException("not implemented");
    }

    private void unloadGraph(Graph graph) {
        this.toUnload.add(graph.getId());
    }

    private void unloadGraph0(Graph graph) {
        NbtCompound nbt = graph.writeNbt(new NbtCompound());
    }

    public void tick() {
        for (UUID uuid : this.toUnload) {
            Graph g = this.graphs.get(uuid);
            this.unloadGraph0(g);
        }

        this.toUnload.clear();
    }

    @Override
    public NbtCompound writeNbt(NbtCompound nbt) {
        throw new IllegalStateException("not implemented"); // TODO
    }

    private static GraphManager load(ServerWorld world, NbtCompound nbt) {
        return new GraphManager(world);
    }

    public static GraphManager get(ServerWorld world) {
        return world.getPersistentStateManager().getOrCreate(
            c -> GraphManager.load(world, c),
            () -> new GraphManager(world),
            "hctm-graph"
        );
    }
}
