package xyz.nucleoid.fantasy.util;

import com.mojang.serialization.Codec;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.structure.StructureManager;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.util.registry.DynamicRegistryManager;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.RegistryKey;
import net.minecraft.util.registry.RegistryLookupCodec;
import net.minecraft.world.*;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeKeys;
import net.minecraft.world.biome.source.BiomeAccess;
import net.minecraft.world.biome.source.FixedBiomeSource;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.StructureAccessor;
import net.minecraft.world.gen.chunk.ChunkGenerator;
import net.minecraft.world.gen.chunk.StructuresConfig;
import net.minecraft.world.gen.feature.StructureFeature;
import org.jetbrains.annotations.Nullable;

import java.util.Collections;
import java.util.Optional;
import java.util.function.Supplier;

public class VoidChunkGenerator extends ChunkGenerator {
    public static final Codec<VoidChunkGenerator> CODEC;

    private final Registry<Biome> biomeRegistry;

    public VoidChunkGenerator(Supplier<Biome> biome, Registry<Biome> biomeRegistry) {
        super(new FixedBiomeSource(biome), new StructuresConfig(Optional.empty(), Collections.emptyMap()));
        this.biomeRegistry = biomeRegistry;
    }

    public VoidChunkGenerator(Registry<Biome> biomeRegistry) {
        super(new FixedBiomeSource(biomeRegistry.getOrThrow(BiomeKeys.THE_VOID)), new StructuresConfig(Optional.empty(), Collections.emptyMap()));
        this.biomeRegistry = biomeRegistry;
    }

    public VoidChunkGenerator(RegistryKey<Biome> biome, Registry<Biome> biomeRegistry) {
        super(new FixedBiomeSource(biomeRegistry.getOrThrow(biome)), new StructuresConfig(Optional.empty(), Collections.emptyMap()));
        this.biomeRegistry = biomeRegistry;
    }

    public Registry<Biome> getBiomeRegistry() {
        return this.biomeRegistry;
    }

    @Override
    protected Codec<? extends ChunkGenerator> getCodec() {
        return CODEC;
    }

    @Override
    public ChunkGenerator withSeed(long seed) {
        return this;
    }

    @Override
    public void setStructureStarts(DynamicRegistryManager registryManager, StructureAccessor accessor, Chunk chunk, StructureManager manager, long seed) {
    }

    @Override
    public void addStructureReferences(StructureWorldAccess world, StructureAccessor accessor, Chunk chunk) {
    }

    @Override
    public void populateNoise(WorldAccess world, StructureAccessor accessor, Chunk chunk) {
    }

    @Override
    public int getHeight(int x, int z, Heightmap.Type heightmapType) {
        return 0;
    }

    @Override
    public BlockView getColumnSample(int x, int z) {
        return null;
    }

    @Override
    public void buildSurface(ChunkRegion region, Chunk chunk) {
    }

    @Override
    public void carve(long seed, BiomeAccess access, Chunk chunk, GenerationStep.Carver carver) {
    }

    @Override
    public void generateFeatures(ChunkRegion region, StructureAccessor accessor) {
    }

    @Override
    public void populateEntities(ChunkRegion region) {
    }

    @Nullable
    @Override
    public BlockPos locateStructure(ServerWorld world, StructureFeature<?> feature, BlockPos center, int radius, boolean skipExistingChunks) {
        return null;
    }

    @Override
    public boolean isStrongholdStartingChunk(ChunkPos chunkPos) {
        return false;
    }

    static {
        CODEC = RegistryLookupCodec.of(Registry.BIOME_KEY).xmap(VoidChunkGenerator::new, VoidChunkGenerator::getBiomeRegistry).stable().codec();
    }
}