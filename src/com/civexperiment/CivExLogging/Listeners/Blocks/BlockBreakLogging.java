package com.civexperiment.CivExLogging.Listeners.Blocks;

import com.civexperiment.CivExLogging.CivExLogging;
import com.civexperiment.CivExLogging.Database.Blocks.BlockBreakInsert;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

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


        }
    }

    void sendToDatabase(Player player, Block block)
    {
        BlockBreakInsert temp = new BlockBreakInsert(System.currentTimeMillis(), block.getWorld().getName(), "BREAK",
                player.getName(), 0,0, player.getLocation().getX(), player.getLocation().getY(), player.getLocation().getZ(),
                block.getX(), block.getY(), block.getZ(), "", "");

        plugin.database.queueAsyncInsertable(temp);
    }

}
