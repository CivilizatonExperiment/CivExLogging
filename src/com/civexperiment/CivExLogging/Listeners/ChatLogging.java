package com.civexperiment.CivExLogging.Listeners;

import com.civexperiment.CivExLogging.CivExLogging;
import com.civexperiment.CivExLogging.Database.ChatInsert;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import vg.civcraft.mc.civchat2.event.*;

import java.util.logging.Level;

/**
 * Created by Ryan on 11/16/2016.
 */
public class ChatLogging implements Listener
{

    CivExLogging plugin;

    public ChatLogging(CivExLogging plugin)
    {
        this.plugin = plugin;
    }

    @EventHandler(priority = EventPriority.MONITOR)
    public void onGlobalChatEvent(GlobalChatEvent event)
    {
        Player sender = event.getPlayer();
        String message = event.getMessage();

        sendToDatabase(sender.getName(), "", "Local", message, System.currentTimeMillis());
        plugin.logConsole(Level.INFO, "Logged a global message");
    }

    @EventHandler(priority = EventPriority.MONITOR)
    public void onGroupChat(GroupChatEvent event)
    {
        Player sender = event.getPlayer();
        String group = "[" + event.getGroup() + "]";
        String message = event.getMessage();

        sendToDatabase(sender.getName(), "", group, message, System.currentTimeMillis());
        plugin.logConsole(Level.INFO, "Logged a group message");
    }

    @EventHandler(priority = EventPriority.MONITOR)
    public void onPrivateMessage(PrivateMessageEvent event)
    {
        Player sender = event.getPlayer();
        Player receiver = event.getReceiver();
        String message = event.getMessage();

        sendToDatabase(sender.getName(), receiver.getName(), "PM", message, System.currentTimeMillis());
        plugin.logConsole(Level.INFO, "Logged a private message");
    }

    public void sendToDatabase(String sender, String receiver, String channel, String message, Long time)
    {
        ChatInsert insert = new ChatInsert(sender, receiver, channel, message, time);
        plugin.database.insert(insert);
        plugin.logConsole(Level.INFO, "Inserted into DB");
    }

}
