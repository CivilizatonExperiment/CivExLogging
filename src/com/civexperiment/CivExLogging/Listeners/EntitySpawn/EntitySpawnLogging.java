/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.civexperiment.CivExLogging.Listeners.EntitySpawn;

import com.civexperiment.CivExLogging.CivExLogging;
import com.civexperiment.CivExLogging.Database.EntitySpawn.EntitySpawnInsert;
import java.util.logging.Level;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntitySpawnEvent;

/**
 *
 *
 */
public class EntitySpawnLogging implements Listener {

    private final CivExLogging plugin;

    public EntitySpawnLogging(CivExLogging plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    void onEntitySpawn(EntitySpawnEvent event) {

        if (event.getEntity() instanceof LivingEntity) {
            
            LivingEntity entity = (LivingEntity)event.getEntity();
            String entityType = event.getEntityType().toString();
            long time = System.currentTimeMillis();
            boolean jockey = entity.getPassenger() != null;
            int x = entity.getLocation().getBlockX();
            int y = entity.getLocation().getBlockY();
            int z = entity.getLocation().getBlockZ();
            
            sendToDatabase(entityType, time, jockey, x, y, z);
        }
    }

    private void sendToDatabase(String entityType, long time, boolean jockey, int x, int y, int z) {
        EntitySpawnInsert insert = new EntitySpawnInsert(entityType, time, jockey, x, y, z);
        
        plugin.database.queueAsyncInsertable(insert);
        plugin.logConsole(Level.INFO, "Inserted entity spawn into DB");
    }

}
