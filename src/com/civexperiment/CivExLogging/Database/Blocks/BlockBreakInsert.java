package com.civexperiment.CivExLogging.Database.Blocks;

import net.arcation.arcadion.interfaces.Insertable;

import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Created by Ryan on 11/18/2016.
 */
public class BlockBreakInsert implements Insertable
{

    Long time;
    String world, action, player, content, reinfHealth;
    int item_right, item_left, block_X, block_Y, block_Z;
    double player_X, player_Y, player_Z;

    public BlockBreakInsert(Long timestamp, String world, String action, String player, int item_right, int item_left,
                            double player_X, double player_Y, double player_Z,int block_X, int block_Y, int block_Z,
                            String content, String reinfHealth){

        this.time = timestamp;
        this.world = world;
        this.action = action;
        this.player = player;
        this.item_right = item_right;
        this.item_left = item_left;
        this.player_X = player_X;
        this.player_Y = player_Y;
        this.player_Z = player_Z;
        this.block_X = block_X;
        this.block_Y = block_Y;
        this.block_Z = block_Z;
        this.content = content;
        this.reinfHealth = reinfHealth;
    }


    @Override
    public void setParameters(PreparedStatement preparedStatement) throws SQLException
    {

    }

    @Override
    public String getStatement()
    {
        return null;
    }
}
