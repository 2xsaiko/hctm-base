package net.dblsaiko.hctm.wire;

import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Stream;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;

public class NetworkGraph {
    private final Set<Node> nodes = new HashSet<>();
    private final Set<Edge> edges = new HashSet<>();

    private final Multimap<ChunkPos, Node> byChunk = HashMultimap.create();
    private final Multimap<BlockPos, Node> byBlock = HashMultimap.create();

    private final Multimap<Node, Edge> edgesByNode = HashMultimap.create();

    public Stream<NetworkPart<?>> nodes() {
        return this.nodes.stream().map(Node::part);
    }

    public Stream<NetworkPart<?>> nodesByChunk(ChunkPos chunk) {
        return this.byChunk.get(chunk).stream().map(Node::part);
    }

    public Stream<NetworkPart<?>> nodesByBlock(BlockPos pos) {
        return this.byBlock.get(pos).stream().map(Node::part);
    }

    public Set<NetworkGraph> split() {
        throw new IllegalStateException("not implemented");
    }

    public void merge(NetworkGraph other) {

    }

    public record Node(NetworkPart<?> part) {
    }

    public record Edge(Node a, Node b) {
    }
}
