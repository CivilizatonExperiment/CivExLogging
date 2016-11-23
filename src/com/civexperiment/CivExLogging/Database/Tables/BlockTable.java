package com.civexperiment.CivExLogging.Database.Tables;

import java.sql.PreparedStatement;

/**
 * Created by Ryan on 11/18/2016.
 */
public class BlockTable implements Table
{
    @Override
    public String getName()
    {
        return "tbl_block_log";
    }

    @Override
    public String getStatement()
    {
        return "CREATE TABLE IF NOT EXISTS `tbl_block_log` (" +
                "`col_id` int(11) NOT NULL AUTO_INCREMENT," +
                "`col_timestamp` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' ON UPDATE CURRENT_TIMESTAMP," +
                "`col_world` varchar(255) NOT NULL," +
                "`col_action` varchar(255) NOT NULL," +
                "`col_player` varchar(255) NOT NULL," +
                "`col_item_held_right` varchar(255) NOT NULL," +
                "`col_item_held_left` varchar(255) NOT NULL," +
                "`col_player_x` double NOT NULL," +
                "`col_player_y` double NOT NULL," +
                "`col_player_z` double NOT NULL," +
                "`col_player_pitch` float NOT NULL," +
                "`col_player_yaw` float NOT NULL," +
                "`col_block_type` varchar(255) NOT NULL," +
                "`col_block_x` int(11) NOT NULL," +
                "`col_block_y` int(11) NOT NULL," +
                "`col_block_z` int(11) NOT NULL," +
                "`col_content` varchar(1000) NOT NULL," +
                "`col_reinforcement_health` varchar(255) NOT NULL," +
                "`col_group_name` varchar(255) NOT NULL," +
                "PRIMARY KEY (`col_id`)," +
                "KEY `col_timestamp` (`col_timestamp`)," +
                "KEY `col_action` (`col_action`)," +
                "KEY `col_player` (`col_player`)," +
                "KEY `col_block_x` (`col_block_x`)," +
                "KEY `col_block_y` (`col_block_y`)," +
                "KEY `col_block_z` (`col_block_z`)," +
                "KEY `col_world` (`col_world`)," +
                "KEY `col_item_held_right` (`col_item_held_right`)," +
                "KEY `col_item_held_left` (`col_item_held_left`)," +
                "KEY `col_cancelled` (`col_reinforcement_health`)," +
                "KEY `col_group_name` (`col_group_name`));";
    }
}
