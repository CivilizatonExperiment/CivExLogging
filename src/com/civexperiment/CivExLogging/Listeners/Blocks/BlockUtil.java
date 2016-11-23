package com.civexperiment.CivExLogging.Listeners.Blocks;

import com.civexperiment.CivExLogging.CivExLogging;
import com.civexperiment.CivExLogging.Database.Blocks.BlockActionInsert;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockState;
import org.bukkit.block.Sign;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;
import vg.civcraft.mc.citadel.reinforcement.PlayerReinforcement;
import vg.civcraft.mc.citadel.reinforcement.Reinforcement;
import vg.civcraft.mc.citadel.reinforcementtypes.ReinforcementType;

import java.util.logging.Level;

/**
 * Created by Ryan on 11/23/2016.
 */
public class BlockUtil
{
    CivExLogging plugin;

    public BlockUtil(CivExLogging plugin)
    {
        this.plugin = plugin;
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

    int getReinforcementMaxDurability(PlayerReinforcement pr)
    {
        return ReinforcementType.getReinforcementType(pr.getStackRepresentation()).getHitPoints();
    }

    String getReinformentDurabilityString(PlayerReinforcement pr, int offset)
    {
        String output = "";

        int maxHp = getReinforcementMaxDurability(pr);
        int currentHp = pr.getDurability();

        output = (currentHp + offset)+ "/" + maxHp;

        return output;
    }

    void sendToDatabase(Player player, String action, Block block, String content,String reinf, String groupName)
    {
        if (block.getState() instanceof InventoryHolder || (block.getState() instanceof Sign && content == ""))
        {
            content = getContents(block.getState());
        }

        String mainHand = player.getInventory().getItemInMainHand().getType().toString();
        String offHand = player.getInventory().getItemInOffHand().getType().toString();

        BlockActionInsert temp = new BlockActionInsert(System.currentTimeMillis(), block.getWorld().getName(), action,
                player.getUniqueId().toString(), mainHand, offHand, player.getLocation().getX(), player.getLocation().getY(),
                player.getLocation().getZ(), player.getLocation().getPitch(), player.getLocation().getYaw(), block.getType().toString(),
                block.getX(), block.getY(), block.getZ(), content, reinf, groupName);

        plugin.database.queueAsyncInsertable(temp);
    }

}
