package com.civexperiment.CivExLogging.Database.Blocks;

import net.arcation.arcadion.interfaces.Insertable;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;

/**
 * Created by Ryan on 11/18/2016.
 */
public class BlockActionInsert implements Insertable
{

    Long time;
    String world, action, player, content, reinfHealth, item_main, item_off, block_Material, group_name;
    int block_X, block_Y, block_Z;
    double player_X, player_Y, player_Z;
    float player_yaw, player_pitch;

    public BlockActionInsert(Long timestamp, String world, String action, String player, String item_main, String item_off,
                             double player_X, double player_Y, double player_Z, float player_pitch, float player_yaw,
                             String blockMaterial, int block_X, int block_Y, int block_Z, String content, String reinfHealth,
                             String group_name){

        this.time = timestamp;
        this.world = world;
        this.action = action;
        this.player = player;
        this.item_main = item_main;
        this.item_off = item_off;
        this.player_X = player_X;
        this.player_Y = player_Y;
        this.player_Z = player_Z;
        this.player_pitch = player_pitch;
        this.player_yaw = player_yaw;
        this.block_Material = blockMaterial;
        this.block_X = block_X;
        this.block_Y = block_Y;
        this.block_Z = block_Z;
        this.content = content;
        this.reinfHealth = reinfHealth;
        this.group_name = group_name;
    }


    @Override
    public void setParameters(PreparedStatement preparedStatement) throws SQLException
    {
        preparedStatement.setTimestamp(1, new Timestamp(time));
        preparedStatement.setString(2, world);
        preparedStatement.setString(3, action);
        preparedStatement.setString(4, player);
        preparedStatement.setString(5, item_main);
        preparedStatement.setString(6, item_off);
        preparedStatement.setDouble(7, player_X);
        preparedStatement.setDouble(8, player_Y);
        preparedStatement.setDouble(9, player_Z);
        preparedStatement.setFloat(10, player_pitch);
        preparedStatement.setFloat(11, player_yaw);
        preparedStatement.setString(12, block_Material);
        preparedStatement.setInt(13, block_X);
        preparedStatement.setInt(14, block_Y);
        preparedStatement.setInt(15, block_Z);
        preparedStatement.setString(16, content);
        preparedStatement.setString(17, reinfHealth);
        preparedStatement.setString(18, group_name);
    }

    @Override
    public String getStatement()
    {
        return "INSERT INTO `tbl_block_log` (`col_timestamp`, `col_world`,`col_action`,`col_player`,`col_item_held_right`," +
                "`col_item_held_left`,`col_player_x`,`col_player_y`,`col_player_z`,`col_player_pitch`,`col_player_yaw`," +
                "`col_block_type`,`col_block_x`,`col_block_y`,`col_block_z`,`col_content`,`col_reinforcement_health`, `col_group_name`) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
    }
}
