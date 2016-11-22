package com.civexperiment.CivExLogging.Listeners.Death;

import com.civexperiment.CivExLogging.CivExLogging;
import com.civexperiment.CivExLogging.Database.Death.DeathInsert;
import org.bukkit.event.Listener;

import java.util.logging.Level;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Horse;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDeathEvent;

/**
 * Created by Ryan on 11/16/2016.
 */
public class DeathLogging implements Listener {

    CivExLogging plugin;

    public DeathLogging(CivExLogging plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    void onDeath(EntityDeathEvent event) {
        LivingEntity victim = event.getEntity();

        Long time = System.currentTimeMillis();
        String world = victim.getLocation().getWorld().getName();
        String cause = victim.getLastDamageCause().getCause().toString();

        // If player, get uuid, else get entity type
        String victimName = (victim instanceof Player) ? ((Player) victim).getUniqueId().toString() : event.getEntityType().toString();
        
        // If victim is horse and horse has owner, set owner 
        String victimOwner = (victim instanceof Horse) ? ( ((Horse) victim).getOwner() != null ? ((Horse) victim).getOwner().getName() : null) : null;
        Double victimX = victim.getLocation().getX();
        Double victimY = victim.getLocation().getY();
        Double victimZ = victim.getLocation().getZ();
        Float victimPitch = victim.getLocation().getPitch();
        Float victimYaw = victim.getLocation().getYaw();
        Integer victimPlayer = (victim instanceof Player) ? 1 : 0;

        Integer xp = event.getDroppedExp();

        String victimLeftHand = (victim.getEquipment().getItemInMainHand() == null) ? null : victim.getEquipment().getItemInMainHand().getType().toString();
        String victimRightHand = (victim.getEquipment().getItemInOffHand() == null) ? null : victim.getEquipment().getItemInOffHand().getType().toString();

        String killerName = null;
        String killerOwner = null;
        Double killerX = null;
        Double killerY = null;
        Double killerZ = null;
        Float killerPitch = null;
        Float killerYaw = null;
        Integer killerPlayer = null;
        String killerLeftHand = null;
        String killerRightHand = null;

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

                killerName = (killer instanceof Player) ? ((Player) killer).getUniqueId().toString() : event.getEntityType().toString();
                killerOwner = (killer instanceof Horse) ? ( ((Horse) killer).getOwner() != null ? ((Horse) killer).getOwner().getName() : null) : null;
                killerX = killer.getLocation().getX();
                killerY = killer.getLocation().getY();
                killerZ = killer.getLocation().getZ();
                killerPitch = killer.getLocation().getPitch();
                killerYaw = killer.getLocation().getYaw();
                killerPlayer = (killer instanceof Player) ? 1 : 0;
                killerLeftHand = (killer.getEquipment().getItemInMainHand() == null) ? null : killer.getEquipment().getItemInMainHand().getType().toString();
                killerRightHand = (killer.getEquipment().getItemInOffHand() == null) ? null : killer.getEquipment().getItemInOffHand().getType().toString();

            }
        }
        sendToDatabase(time,
                world,
                cause,
                victimName,
                victimOwner,
                victimLeftHand,
                victimRightHand,
                victimX,
                victimY,
                victimZ,
                victimPitch,
                victimYaw,
                victimPlayer,
                killerName,
                killerOwner,
                killerLeftHand,
                killerRightHand,
                killerX,
                killerY,
                killerZ,
                killerPitch,
                killerYaw,
                killerPlayer,
                xp);
    }

    public void sendToDatabase(long time,
            String world,
            String cause,
            String victimName,
            String victimOwner,
            String victimLeftHand,
            String victimRightHand,
            Double victimX,
            Double victimY,
            Double victimZ,
            Float victimPitch,
            Float victimYaw,
            Integer victimPlayer,
            String killerName,
            String killerOwner,
            String killerLeftHand,
            String killerRightHand,
            Double killerX,
            Double killerY,
            Double killerZ,
            Float killerPitch,
            Float killerYaw,
            Integer killerPlayer,
            Integer xp) {
        DeathInsert insert = new DeathInsert(time,
                world,
                cause,
                victimName,
                victimOwner,
                victimLeftHand,
                victimRightHand,
                victimX,
                victimY,
                victimZ,
                victimPitch,
                victimYaw,
                victimPlayer,
                killerName,
                killerOwner,
                killerLeftHand,
                killerRightHand,
                killerX,
                killerY,
                killerZ,
                killerPitch,
                killerYaw,
                killerPlayer,
                xp);
        plugin.database.queueAsyncInsertable(insert);
        plugin.logConsole(Level.INFO, "Inserted death log into DB");
    }

}
