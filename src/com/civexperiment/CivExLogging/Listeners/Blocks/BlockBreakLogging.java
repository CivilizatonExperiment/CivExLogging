package com.civexperiment.CivExLogging.Listeners.Blocks;

import com.civexperiment.CivExLogging.CivExLogging;
import com.civexperiment.CivExLogging.Database.Blocks.BlockActionInsert;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockState;
import org.bukkit.block.Sign;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;
import vg.civcraft.mc.citadel.events.ReinforcementDamageEvent;
import vg.civcraft.mc.citadel.reinforcement.PlayerReinforcement;
import vg.civcraft.mc.citadel.reinforcementtypes.ReinforcementType;

import java.util.logging.Level;

/**
 * Created by Ryan on 11/18/2016.
 */
public class BlockBreakLogging implements Listener
{
    CivExLogging plugin;

    public BlockBreakLogging(CivExLogging plugin)
    {
        this.plugin = plugin;
    }

    @EventHandler(priority = EventPriority.MONITOR)
    public void onBlockBreak(BlockBreakEvent event)
    {
        if (!event.isCancelled()) //This is to not catch citadel stuff
        {
            plugin.blockUtil.sendToDatabase(event.getPlayer(), "BREAK", event.getBlock(), "", "", "");
        }
    }

    @EventHandler(priority = EventPriority.MONITOR)
    public void onReinforcementDamage(ReinforcementDamageEvent event)
    {
        if (!event.isCancelled())
        {
            String output = "";
            String groupName = "";

            if (event.getReinforcement() instanceof PlayerReinforcement)
            {
                PlayerReinforcement pr = (PlayerReinforcement)event.getReinforcement();
                output = plugin.blockUtil.getReinformentDurabilityString(pr, -1);
                groupName = pr.getGroup().getName();
            }

            plugin.blockUtil.sendToDatabase(event.getPlayer(), "DAMAGE", event.getBlock(), "", output, groupName);
        }
    }



}
