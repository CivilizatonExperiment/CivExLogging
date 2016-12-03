package com.civexperiment.CivExLogging.Listeners.Blocks;

import com.civexperiment.CivExLogging.CivExLogging;
import com.civexperiment.CivExLogging.Database.Blocks.BlockActionInsert;
import com.civexperiment.CivExLogging.Enums.Blocks.Action;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.block.BlockState;
import org.bukkit.block.Sign;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.*;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;
import org.bukkit.material.Attachable;
import org.bukkit.material.MaterialData;
import vg.civcraft.mc.citadel.reinforcement.PlayerReinforcement;
import vg.civcraft.mc.citadel.reinforcement.Reinforcement;
import vg.civcraft.mc.citadel.reinforcementtypes.ReinforcementType;

import javax.xml.crypto.dsig.keyinfo.KeyValue;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;

/**
 * Created by Ryan on 11/23/2016.
 */
public class BlockUtil
{
    CivExLogging plugin;
    ArrayList<BlockFace> blockFaces = new ArrayList<>();


    public BlockUtil(CivExLogging plugin)
    {
        this.plugin = plugin;

        blockFaces.add(BlockFace.UP);
        blockFaces.add(BlockFace.DOWN);
        blockFaces.add(BlockFace.NORTH);
        blockFaces.add(BlockFace.WEST);
        blockFaces.add(BlockFace.SOUTH);
        blockFaces.add(BlockFace.EAST);
    }

    String getContents(BlockState bstate)
    {
        StringBuilder sb = new StringBuilder();
        if (bstate instanceof InventoryHolder)
        {
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

    String getContents(Entity entity)
    {
        StringBuilder sb = new StringBuilder();
        if (entity instanceof ItemFrame)
        {
            ItemFrame itemF = (ItemFrame) entity;
            sb.append("Item: ");
            sb.append(itemF.getItem().getType().name());

            if (itemF.getItem().getEnchantments().size() > 0)
            {
                boolean first = true;
                sb.append(" | Enchants: ");
                Map<Enchantment, Integer> enchantMap = itemF.getItem().getEnchantments();
                for (Enchantment e : enchantMap.keySet())
                {
                    if (!first)
                    {
                        sb.append(", ");
                    }
                    else
                    {
                        first = false;
                    }
                    sb.append(e.getName() + " " + enchantMap.get(e));
                }

                enchantMap.clear();
                sb.append(" |");
            }

        }
        else if (entity instanceof Painting)
        {
            Painting paint = (Painting) entity;

            sb.append(paint.getArt().name());
        }
        else if (entity instanceof LeashHitch)
        {
            LeashHitch lh = (LeashHitch) entity;
            //TODO: Don't know if we can get the attached animal
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

        output = (currentHp + offset) + "/" + maxHp;

        return output;
    }

    ArrayList<Block> getAttachedBlocks(Block block)
    {
        ArrayList<Block> output = new ArrayList<>();
        for (BlockFace face : blockFaces)
        {
            Block secondary = block.getWorld().getBlockAt(block.getRelative(face).getLocation());
            MaterialData sData = secondary.getState().getData();

            if (sData instanceof Attachable)
            {
                if (block.equals(secondary.getRelative(((Attachable) sData).getAttachedFace())))
                {
                    output.add(secondary);
                }
            }
        }

        return output;
    }

    ArrayList<Entity> getAttachedEntitys(Block block)
    {
        double ESR = 1.5;
        ArrayList<Entity> output = new ArrayList<>();
        Collection<Entity> targetList = block.getWorld().getNearbyEntities(block.getLocation(), ESR, ESR, ESR);

        for (Entity e : targetList)
        {
            if (e instanceof Hanging)
            {
                output.add(e);
            }
        }

        return output;
    }

    void sendToDatabase(Player player, Action action, Block block, String content, String reinf, String groupName)
    {
        if (block.getState() instanceof InventoryHolder || (block.getState() instanceof Sign && content.equalsIgnoreCase("")))
        {
            content = getContents(block.getState());
        }

        String mainHand = player.getInventory().getItemInMainHand().getType().toString();
        String offHand = player.getInventory().getItemInOffHand().getType().toString();

        BlockActionInsert temp = new BlockActionInsert(System.currentTimeMillis(), block.getWorld().getName(), action.name(),
                player.getUniqueId().toString(), mainHand, offHand, player.getLocation().getX(), player.getLocation().getY(),
                player.getLocation().getZ(), player.getLocation().getPitch(), player.getLocation().getYaw(), block.getType().toString(),
                block.getX(), block.getY(), block.getZ(), content, reinf, groupName);

        plugin.database.queueAsyncInsertable(temp);
    }

    void sendToDatabase(Player player, Action action, Entity entity, String content, String reinf, String groupName)
    {
        if (entity instanceof Hanging)
        {
            content = getContents(entity);
        }

        String mainHand = player.getInventory().getItemInMainHand().getType().toString();
        String offHand = player.getInventory().getItemInOffHand().getType().toString();

        BlockActionInsert temp = new BlockActionInsert(System.currentTimeMillis(), entity.getLocation().getWorld().getName(), action.name(),
                player.getUniqueId().toString(), mainHand, offHand, player.getLocation().getX(), player.getLocation().getY(),
                player.getLocation().getZ(), player.getLocation().getPitch(), player.getLocation().getYaw(), entity.getType().toString(),
                (int) entity.getLocation().getX(), (int) entity.getLocation().getY(), (int) entity.getLocation().getZ(), content, reinf, groupName);

        plugin.database.queueAsyncInsertable(temp);
    }
}
