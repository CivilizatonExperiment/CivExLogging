package com.civexperiment.CivExLogging.Listeners.Command;

import com.civexperiment.CivExLogging.CivExLogging;
import com.civexperiment.CivExLogging.Database.Command.CommandInsert;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

import java.util.UUID;
import java.util.logging.Level;

/**
 * Created by Ryan on 1/24/2017.
 */
public class CommandLogging implements Listener
{

    CivExLogging plugin;

    public CommandLogging(CivExLogging plugin)
    {
        this.plugin = plugin;
    }

    @EventHandler(priority = EventPriority.MONITOR)
    public void onCommand(PlayerCommandPreprocessEvent event)
    {
        Player p = event.getPlayer();
        String[] command = event.getMessage().split(" ");
        String args = "";
        for (int i = 1; i < command.length; i++)
        {
            args += " " + command[i];
        }

        sendToDatabase(p.getUniqueId(), command[0], args, System.currentTimeMillis(), event.isCancelled());
    }

    public void sendToDatabase(UUID player, String command, String args, long time, boolean isCanceled)
    {
        CommandInsert insert = new CommandInsert(player.toString(), command, args, time, isCanceled);
        plugin.database.queueAsyncInsertable(insert);
        plugin.logConsole(Level.INFO, "Command Inserted into DB");
    }

}
