package com.civexperiment.CivExLogging;

import com.civexperiment.CivExLogging.Listeners.ChatLogging;
import net.arcation.arcadion.interfaces.Arcadion;
import net.arcation.arcadion.interfaces.DatabaseManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;

/**
 * Created by Ryan on 11/16/2016.
 */
public class CivExLogging extends JavaPlugin
{
    public boolean debug = true;
    public Arcadion database;

    @Override
    public void onEnable()
    {
        database = DatabaseManager.getArcadion();

        if (!database.isActive())
        {
            stopServer();
        }
        else
        {
            createTable();
        }

        regEvents();
    }

    @Override
    public void onDisable()
    {

    }

    private void createTable()
    {
        try (Connection connection = database.getConnection())
        {
            String statementString = "CREATE TABLE IF NOT EXISTS tbl_chat_log ("
                    + "`col_id` int(10) unsigned NOT NULL AUTO_INCREMENT,"
                    + "`col_timestamp` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',"
                    + "`col_channel` varchar(255) NOT NULL,"
                    + "`col_sender` varchar(16) NOT NULL,"
                    + "`col_receiver` varchar(16) NOT NULL,"
                    + "`col_message` varchar(255) NOT NULL,"
                    + "PRIMARY KEY (`col_id`));";
            try (PreparedStatement statement = connection.prepareStatement(statementString))
            {
                statement.execute();
            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
            stopServer();
        }
    }

    private void stopServer()
    {
        this.getServer().dispatchCommand(this.getServer().getConsoleSender(), "stop");
    }

    private void regEvents()
    {
        getServer().getPluginManager().registerEvents(new ChatLogging(this), this);
    }

    public void logConsole(Level level, String msg)
    {
        if (debug)
        {
            getLogger().log(level, msg);
        } else {
            if(level == Level.SEVERE){
                getLogger().log(level, msg);
            }
        }

    }
}
