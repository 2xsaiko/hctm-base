package net.dblsaiko.hctm.init;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.registry.Registry;
import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder.Factory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BlockEntityTypeRegistry {
    private final String modId;

    private final List<InternalRegistryObject<BlockEntityType<? extends BlockEntity>>> all = new ArrayList<>();

    public BlockEntityTypeRegistry(String modId) {
        this.modId = modId;
    }

    public <T extends BlockEntity> RegistryObject<BlockEntityType<T>> create(String name, Factory<T> factory, RegistryObject<? extends Block>... blocks) {
        RegistryObjectImpl<T> o = new RegistryObjectImpl<>(new Identifier(this.modId, name), factory, blocks);
        // noinspection unchecked
        this.all.add((InternalRegistryObject<BlockEntityType<? extends BlockEntity>>) (Object) o);
        return o;
    }

    public <T extends BlockEntity> RegistryObject<BlockEntityType<T>> create(String name, FactoryWithType<T> factory, RegistryObject<? extends Block>... blocks) {
        RegistryObjectImplWithType<T> o = new RegistryObjectImplWithType<>(new Identifier(this.modId, name), factory, blocks);
        // noinspection unchecked
        this.all.add((InternalRegistryObject<BlockEntityType<? extends BlockEntity>>) (Object) o);
        return o;
    }

    public void register() {
        this.all.forEach(InternalRegistryObject::register);
    }

    public void unregister() {
        this.all.forEach(InternalRegistryObject::unregister);
    }

    private static final class RegistryObjectImpl<T extends BlockEntity> extends AbstractRegistryObject<BlockEntityType<T>> {
        private final Factory<T> factory;
        private final RegistryObject<? extends Block>[] blocks;

        private RegistryObjectImpl(Identifier id, Factory<T> factory, RegistryObject<? extends Block>[] blocks) {
            super(id);
            this.factory = factory;
            this.blocks = blocks;
        }

        @Override
        public BlockEntityType<T> registerNew() {
            return Registry.register(
                Registry.BLOCK_ENTITY_TYPE,
                this.id(),
                FabricBlockEntityTypeBuilder.create(
                    this.factory,
                    Arrays.stream(this.blocks).map(RegistryObject::get).toArray(Block[]::new)
                ).build(null)
            );
        }
    }

    private static final class RegistryObjectImplWithType<T extends BlockEntity> extends AbstractRegistryObject<BlockEntityType<T>> {
        private final FactoryWithType<T> factory;
        private final RegistryObject<? extends Block>[] blocks;

        private RegistryObjectImplWithType(Identifier id, FactoryWithType<T> factory, RegistryObject<? extends Block>[] blocks) {
            super(id);
            this.factory = factory;
            this.blocks = blocks;
        }

        @Override
        public BlockEntityType<T> registerNew() {
            return Registry.register(
                Registry.BLOCK_ENTITY_TYPE,
                this.id(),
                FabricBlockEntityTypeBuilder.create(
                    (pos, state) -> this.factory.create(this.get(), pos, state),
                    Arrays.stream(this.blocks).map(RegistryObject::get).toArray(Block[]::new)
                ).build(null)
            );
        }
    }

    @FunctionalInterface
    public interface FactoryWithType<T extends BlockEntity> {
        T create(BlockEntityType<T> type, BlockPos pos, BlockState state);
    }
}
