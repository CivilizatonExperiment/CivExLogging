package com.civexperiment.CivExLogging;

import com.civexperiment.CivExLogging.Database.Tables.*;
import com.civexperiment.CivExLogging.Listeners.Blocks.BlockBreakLogging;
import com.civexperiment.CivExLogging.Listeners.Blocks.BlockPlaceLogging;
import com.civexperiment.CivExLogging.Listeners.Blocks.BlockUtil;
import com.civexperiment.CivExLogging.Listeners.Chat.ChatLogging;
import com.civexperiment.CivExLogging.Listeners.Death.DeathLogging;
import net.arcation.arcadion.interfaces.Arcadion;
import net.arcation.arcadion.interfaces.DatabaseManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;

/**
 * Created by Ryan on 11/16/2016.
 */
public class CivExLogging extends JavaPlugin
{
    public boolean debug = true;

    ArrayList<Table> tableArrayList;

    public BlockUtil blockUtil;
    public Arcadion database;

    @Override
    public void onEnable()
    {
        tableArrayList = new ArrayList<Table>();
        tableArrayList.add(new ChatTable());
        tableArrayList.add(new BlockTable());
        tableArrayList.add(new DeathTable());

        database = DatabaseManager.getArcadion();

        if (!database.isActive())
        {
            stopServer();
        }
        else
        {
            createTables();
            blockUtil = new BlockUtil(this);
        }

        regEvents();
    }

    @Override
    public void onDisable()
    {

    }

    private void createTables()
    {
        try (Connection connection = database.getConnection())
        {
            for(Table t : tableArrayList){
                try (PreparedStatement statement = connection.prepareStatement(t.getStatement()))
                {
                    statement.execute();
                }
                catch (SQLException ex)
                {
                    logConsole(Level.SEVERE, "Creating table [" + t.getName() + "] failed below is the stacktrace for" +
                            "that statement.");
                    ex.printStackTrace();
                    stopServer();
                }
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
        getServer().getPluginManager().registerEvents(new DeathLogging(this), this);
        getServer().getPluginManager().registerEvents(new BlockBreakLogging(this), this);
        getServer().getPluginManager().registerEvents(new BlockPlaceLogging(this), this);
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
