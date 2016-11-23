package com.civexperiment.CivExLogging.Listeners.Blocks;

import com.civexperiment.CivExLogging.CivExLogging;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.block.SignChangeEvent;
import org.bukkit.material.Sign;
import vg.civcraft.mc.citadel.Citadel;
import vg.civcraft.mc.citadel.CitadelConfigManager;
import vg.civcraft.mc.citadel.PlayerState;
import vg.civcraft.mc.citadel.ReinforcementMode;
import vg.civcraft.mc.citadel.events.ReinforcementCreationEvent;
import vg.civcraft.mc.citadel.misc.CitadelStatics;
import vg.civcraft.mc.citadel.reinforcement.PlayerReinforcement;
import vg.civcraft.mc.citadel.reinforcementtypes.ReinforcementType;

/**
 * Created by Ryan on 11/23/2016.
 */
public class BlockPlaceLogging implements Listener
{
    public CivExLogging plugin;

    public BlockPlaceLogging(CivExLogging plugin)
    {
        this.plugin = plugin;
    }


    @EventHandler(priority = EventPriority.MONITOR)
    public void onPlayerPlaceEvent(BlockPlaceEvent event)
    {
        if (!event.isCancelled())
        {
            if (event.getBlock().getType() != Material.SIGN && event.getBlock().getType() != Material.SIGN_POST)
            {
                plugin.blockUtil.sendToDatabase(event.getPlayer(), "PLACE", event.getBlock(), "", "", "");
            }
        }
    }

    @EventHandler(priority = EventPriority.MONITOR)
    public void onChangeSign(SignChangeEvent event)
    {
        if (!event.isCancelled())
        {
            String lines = "";
            for (String line : event.getLines())
            {
                lines += line + "\n";
            }

            plugin.blockUtil.sendToDatabase(event.getPlayer(), "PLACE", event.getBlock(), lines, "", "");
        }
    }

    @EventHandler(priority = EventPriority.MONITOR)
    public void onPlayerReinforce(ReinforcementCreationEvent event)
    {
        if (!event.isCancelled())
        {
            Player p = event.getPlayer();
            PlayerReinforcement pr = (PlayerReinforcement) event.getReinforcement();
            Block b = event.getBlock();

            String output = plugin.blockUtil.getReinformentDurabilityString(pr, 0);
            String groupName = pr.getGroup().getName();

            plugin.blockUtil.sendToDatabase(p, "REINFORCE", b, "", output, groupName);
        }
    }

}
