package xyz.nucleoid.fantasy.util;

import com.mojang.serialization.Codec;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.RegistryLookupCodec;
import net.minecraft.world.BlockView;
import net.minecraft.world.ChunkRegion;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeKeys;
import net.minecraft.world.biome.source.BiomeArray;
import net.minecraft.world.biome.source.BiomeSource;
import net.minecraft.world.biome.source.FixedBiomeSource;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.chunk.ChunkSection;
import net.minecraft.world.chunk.ProtoChunk;
import net.minecraft.world.gen.chunk.ChunkGenerator;
import net.minecraft.world.gen.chunk.StructuresConfig;
import net.minecraft.world.Heightmap;
import net.minecraft.world.WorldAccess;
import net.minecraft.world.gen.StructureAccessor;

import java.util.Collections;
import java.util.Optional;

public class VoidChunkGenerator extends ChunkGenerator {
    public static final Codec<VoidChunkGenerator> CODEC = RegistryLookupCodec.of(Registry.BIOME_KEY)
            .xmap(VoidChunkGenerator::new, VoidChunkGenerator::getBiomeRegistry).stable().codec();

    private final Registry<Biome> biomeRegistry;

    public VoidChunkGenerator(Registry<Biome> biomeRegistry) {
        super(new FixedBiomeSource(biomeRegistry.getOrThrow(BiomeKeys.THE_VOID)), new StructuresConfig(Optional.empty(), Collections.emptyMap()));
        this.biomeRegistry = biomeRegistry;
    }

    private Registry<Biome> getBiomeRegistry() {
        return this.biomeRegistry;
    }

    @Override
    protected Codec<? extends ChunkGenerator> getCodec() {
        return CODEC;
    }

    @Override
    public ChunkGenerator withSeed(long seed) {
        return this; // Seed does not matter for a void world
    }

    @Override
    public void buildSurface(ChunkRegion region, Chunk chunk) {
        // No surface to build in a void world
    }

    @Override
    public void populateBiomes(Registry<Biome> biomeRegistry, Chunk chunk) {
        ChunkPos chunkPos = chunk.getPos();
        ((ProtoChunk)chunk).setBiomes(new BiomeArray(this.biomeRegistry, chunkPos, this.biomeSource));

        for (Biome biome : this.getBiomeSource().getBiomes()) {
            System.out.println(biome.getClass());
        }
    }

    @Override
    public void populateNoise(WorldAccess world, StructureAccessor accessor, Chunk chunk) {
        // No noise to populate in a void world
    }

    @Override
    public int getHeight(int x, int z, Heightmap.Type heightmapType) {
        return 0; // No height in a void world
    }

    @Override
    public BlockView getColumnSample(int x, int z) {
        return null;
    }

    @Override
    public void generateFeatures(ChunkRegion region, StructureAccessor accessor) {
        // no features to generate in a void world
    }
}

