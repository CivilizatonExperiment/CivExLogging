package com.civexperiment.CivExLogging.Listeners.Blocks;

import com.civexperiment.CivExLogging.CivExLogging;
import com.civexperiment.CivExLogging.Enums.Blocks.Action;
import org.bukkit.block.Block;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.hanging.HangingBreakByEntityEvent;
import vg.civcraft.mc.citadel.events.ReinforcementDamageEvent;
import vg.civcraft.mc.citadel.reinforcement.PlayerReinforcement;

import java.util.ArrayList;

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
            ArrayList<Block> attachedBlocks = plugin.blockUtil.getAttachedBlocks(event.getBlock());
            ArrayList<Entity> attachedEntitys = plugin.blockUtil.getAttachedEntitys(event.getBlock());

            if (attachedBlocks.size() > 0)
            {
                for (Block b : attachedBlocks)
                {
                    plugin.blockUtil.sendToDatabase(event.getPlayer(), Action.BREAK, b, "", "", "");
                }
            }

            if (attachedEntitys.size() > 0)
            {
                for (Entity e : attachedEntitys)
                {
                    plugin.blockUtil.sendToDatabase(event.getPlayer(), Action.BREAK, e, "", "", "");
                }
            }

            plugin.blockUtil.sendToDatabase(event.getPlayer(), Action.BREAK, event.getBlock(), "", "", "");
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
                PlayerReinforcement pr = (PlayerReinforcement) event.getReinforcement();
                output = plugin.blockUtil.getReinformentDurabilityString(pr, -1);
                groupName = pr.getGroup().getName();
            }

            plugin.blockUtil.sendToDatabase(event.getPlayer(), Action.DAMAGE, event.getBlock(), "", output, groupName);
        }
    }

    @EventHandler(priority = EventPriority.MONITOR)
    public void onHangingBreakEvent(HangingBreakByEntityEvent event)
    {
        if (event.getRemover() instanceof Player)
        {
            Player p = (Player) event.getRemover();

            plugin.blockUtil.sendToDatabase(p, Action.BREAK, event.getEntity(), "", "", "");
        }
    }


}
