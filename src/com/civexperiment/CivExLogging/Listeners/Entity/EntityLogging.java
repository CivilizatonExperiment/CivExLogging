
package com.civexperiment.CivExLogging.Listeners.Entity;

import com.civexperiment.CivExLogging.CivExLogging;
import com.civexperiment.CivExLogging.Database.Entity.EntityInfo;
import com.civexperiment.CivExLogging.Database.Entity.EntityInsert;
import java.util.logging.Level;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Horse;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.entity.EntitySpawnEvent;

/**
 *
 *
 */
public class EntityLogging implements Listener {

    CivExLogging plugin;

    public enum LogReason {

        DEATH,
        SPAWN
    }

    public EntityLogging(CivExLogging plugin) {
        this.plugin = plugin;
    }

    public EntityInfo getEntityInfo(Entity entity) {

        String entityName = (entity instanceof Player) ? ((Player) entity).getUniqueId().toString() : entity.getType().toString();

        String entityOwner = (entity instanceof Horse) ? (((Horse) entity).getOwner() != null ? ((Horse) entity).getOwner().getName() : null) : null;
        Double entityX = entity.getLocation().getX();
        Double entityY = entity.getLocation().getY();
        Double entityZ = entity.getLocation().getZ();
        Float entityPitch = entity.getLocation().getPitch();
        Float entityYaw = entity.getLocation().getYaw();
        Boolean entityPlayer = entity instanceof Player;

        boolean jockey;
        String entityLeftHand;
        String entityRightHand;

        if (entity instanceof LivingEntity) {
            LivingEntity livingEntity = (LivingEntity) entity;
            jockey = entity.getPassenger() != null;
            entityLeftHand = (livingEntity.getEquipment().getItemInMainHand() == null) ? null : livingEntity.getEquipment().getItemInMainHand().getType().toString();
            entityRightHand = (livingEntity.getEquipment().getItemInOffHand() == null) ? null : livingEntity.getEquipment().getItemInOffHand().getType().toString();
        } else {
            jockey = false;
            entityLeftHand = null;
            entityRightHand = null;
        }

        return new EntityInfo(entityName, entityOwner, jockey, entityX, entityY, entityZ, entityPitch, entityYaw, entityPlayer, entityLeftHand, entityRightHand);
    }

    @EventHandler
    void onSpawn(EntitySpawnEvent event) {

        if (event.getEntity().getType().equals(EntityType.EXPERIENCE_ORB) || event.getEntity().getType().equals(EntityType.DROPPED_ITEM)) {
            return;
        }

        LogReason logReason = LogReason.SPAWN;

        Long time = System.currentTimeMillis();
        String world = event.getEntity().getLocation().getWorld().getName();
        String cause = (event instanceof CreatureSpawnEvent) ? ((CreatureSpawnEvent) event).getSpawnReason().toString() : null;

        EntityInfo entityInfo = getEntityInfo(event.getEntity());

        sendToDatabase(time, world, cause, entityInfo, null, null, logReason);
    }

    @EventHandler
    void onDeath(EntityDeathEvent event) {
        LogReason logReason = LogReason.DEATH;

        LivingEntity entity = event.getEntity();

        Long time = System.currentTimeMillis();
        String world = entity.getLocation().getWorld().getName();
        String cause = entity.getLastDamageCause().getCause().toString();

        Integer xp = event.getDroppedExp();

        EntityInfo entityInfo = getEntityInfo(entity);
        EntityInfo killerInfo = null;

        if (event.getEntity().getLastDamageCause() instanceof EntityDamageByEntityEvent) {

            EntityDamageByEntityEvent evt = ((EntityDamageByEntityEvent) event.getEntity().getLastDamageCause());

            LivingEntity killer = null;

            if (evt.getDamager() instanceof LivingEntity) {
                killer = (LivingEntity) evt.getDamager();
            } else if (evt.getDamager() instanceof Projectile) {
                Projectile projectile = (Projectile) evt.getDamager();

                if (projectile.getShooter() instanceof LivingEntity) {
                    killer = (LivingEntity) projectile.getShooter();
                }
            }

            if (killer != null) {
                killerInfo = getEntityInfo(killer);
            }
        }

        sendToDatabase(time, world, cause, entityInfo, killerInfo, xp, logReason);
    }

    private void sendToDatabase(Long time, String world, String cause, EntityInfo entityInfo, EntityInfo killerInfo, Integer xp, LogReason logReason) {

        EntityInsert insert = new EntityInsert(time, world, cause, entityInfo, killerInfo, xp, logReason);
        plugin.database.queueAsyncInsertable(insert);
        plugin.logConsole(Level.INFO, "Inserted entity log into DB");
    }

}
