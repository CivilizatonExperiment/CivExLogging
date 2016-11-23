package com.civexperiment.CivExLogging.Listeners.Blocks;

import com.civexperiment.CivExLogging.CivExLogging;
import com.civexperiment.CivExLogging.Database.Blocks.BlockBreakInsert;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockState;
import org.bukkit.block.Chest;
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
import vg.civcraft.mc.namelayer.group.Group;

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
            sendToDatabase(event.getPlayer(), event.getBlock(), "", "BREAK", "");
        }
    }

    @EventHandler(priority = EventPriority.MONITOR)
    public void onReinforcementDamage(ReinforcementDamageEvent event)
    {
        if (!event.isCancelled())
        {
            String output = event.getReinforcement().getDurability() + "/";
            String groupName = "";

            if (event.getReinforcement() instanceof PlayerReinforcement)
            {
                output += ReinforcementType.getReinforcementType(((PlayerReinforcement)event.getReinforcement()).getStackRepresentation()).getHitPoints();
                PlayerReinforcement pr = (PlayerReinforcement)event.getReinforcement();
                groupName = pr.getGroup().getName();
            }


            sendToDatabase(event.getPlayer(), event.getBlock(), output, "DAMAGE", groupName);
        }
    }

    String getContents(BlockState bstate)
    {
        plugin.logConsole(Level.SEVERE, "getContents started.");

        StringBuilder sb = new StringBuilder();
        if (bstate instanceof InventoryHolder)
        {
            plugin.logConsole(Level.SEVERE, "block is instanceOf Inventory holder");
            Inventory inv = ((InventoryHolder) bstate).getInventory();

            for (ItemStack i : inv.getContents())
            {
                if (i != null && i.getType() != Material.AIR)
                {
                    sb.append(i.getAmount());
                    sb.append("x");
                    sb.append(i.getType());
                    sb.append(" ");
                }
            }
        }
        else if (bstate instanceof Sign)
        {
            Sign sign = (Sign) bstate;
            for (String line : sign.getLines())
            {
                sb.append(line + "\n");
            }
        }

        return sb.toString();
    }

    void sendToDatabase(Player player, Block block, String reinf, String action, String groupName)
    {
        String content = "";

        if (block.getState() instanceof InventoryHolder || block.getState() instanceof Sign)
        {
            content = getContents(block.getState());
        }

        String mainHand = player.getInventory().getItemInMainHand().getType().toString();
        String offHand = player.getInventory().getItemInOffHand().getType().toString();

        BlockBreakInsert temp = new BlockBreakInsert(System.currentTimeMillis(), block.getWorld().getName(), action,
                player.getUniqueId().toString(), mainHand, offHand, player.getLocation().getX(), player.getLocation().getY(),
                player.getLocation().getZ(), player.getLocation().getPitch(), player.getLocation().getYaw(), block.getType().toString(),
                block.getX(), block.getY(), block.getZ(), content, reinf, groupName);

        plugin.database.queueAsyncInsertable(temp);
    }

}
