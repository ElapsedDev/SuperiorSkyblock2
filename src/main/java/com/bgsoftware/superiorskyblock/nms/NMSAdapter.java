package com.bgsoftware.superiorskyblock.nms;

import com.bgsoftware.superiorskyblock.api.island.Island;
import com.bgsoftware.superiorskyblock.api.key.Key;
import com.bgsoftware.superiorskyblock.api.wrappers.SuperiorPlayer;
import org.bukkit.Chunk;
import org.bukkit.ChunkSnapshot;
import org.bukkit.Location;
import org.bukkit.OfflinePlayer;
import org.bukkit.block.Biome;
import org.bukkit.block.Block;
import org.bukkit.block.CreatureSpawner;
import org.bukkit.command.defaults.BukkitCommand;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.generator.ChunkGenerator;
import org.bukkit.inventory.InventoryHolder;

import java.util.List;

public interface NMSAdapter {

    void registerCommand(BukkitCommand command);

    Key getBlockKey(ChunkSnapshot chunkSnapshot, int x, int y, int z);

    int getSpawnerDelay(CreatureSpawner creatureSpawner);

    void setWorldBorder(SuperiorPlayer superiorPlayer, Island island);

    void setSkinTexture(SuperiorPlayer superiorPlayer);

    default Object getCustomHolder(InventoryType inventoryType, InventoryHolder defaultHolder, String title){
        return defaultHolder;
    }

    void clearInventory(OfflinePlayer offlinePlayer);

    void playGeneratorSound(Location location);

    void setBiome(Chunk chunk, Biome biome);

    default void setBiome(ChunkGenerator.BiomeGrid biomeGrid, Biome biome){
        for(int x = 0; x < 16; x++){
            for(int z = 0; z < 16; z++){
                biomeGrid.setBiome(x, z, biome);
            }
        }
    }

    default Object getBlockData(Block block){
        return null;
    }

    Enchantment getGlowEnchant();

    default void regenerateChunk(Chunk chunk){
        chunk.getWorld().regenerateChunk(chunk.getX(), chunk.getZ());
    }

}
