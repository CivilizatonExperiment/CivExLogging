package com.civexperiment.CivExLogging.Database.Tables;

/**
 * Created by Ryan on 11/19/2016.
 */
public class DeathTable implements Table
{
    @Override
    public String getName()
    {
        return "tbl_death_log";
    }

    @Override
    public String getStatement()
    {
        return "CREATE TABLE IF NOT EXISTS `tbl_death_log` (" +
                "  `col_id` int(11) NOT NULL AUTO_INCREMENT," +
                "  `col_timestamp` timestamp NULL DEFAULT NULL," +
                "  `col_world` varchar(45) DEFAULT NULL," +
                "  `col_cause` varchar(45) DEFAULT NULL," +
                "  `col_victim_name` varchar(45) DEFAULT NULL," +
                "  `col_victim_owner` varchar(45) DEFAULT NULL," +
                "  `col_victim_left_hand` varchar(45) DEFAULT NULL," +
                "  `col_victim_right_hand` varchar(45) DEFAULT NULL," +
                "  `col_victim_x` double DEFAULT NULL," +
                "  `col_victim_y` double DEFAULT NULL," +
                "  `col_victim_z` double DEFAULT NULL," +
                "  `col_victim_pitch` float DEFAULT NULL," +
                "  `col_victim_yaw` float DEFAULT NULL," +
                "  `col_victim_player` tinyint(1) DEFAULT NULL," +
                "  `col_killer_name` varchar(45) DEFAULT NULL," +
                "  `col_killer_owner` varchar(45) DEFAULT NULL," +
                "  `col_killer_left_hand` varchar(45) DEFAULT NULL," +
                "  `col_killer_right_hand` varchar(45) DEFAULT NULL," +
                "  `col_killer_x` double DEFAULT NULL," +
                "  `col_killer_y` double DEFAULT NULL," +
                "  `col_killer_z` double DEFAULT NULL," +
                "  `col_killer_pitch` float DEFAULT NULL," +
                "  `col_killer_yaw` float DEFAULT NULL," +
                "  `col_killer_player` tinyint(1) DEFAULT NULL," +
                "  `col_xp` int(11) DEFAULT NULL," +
                "  PRIMARY KEY (`col_id`)," +
                "  KEY `col_timestamp` (`col_timestamp`)," +
                "  KEY `col_world` (`col_world`)," +
                "  KEY `col_cause` (`col_cause`)," +
                "  KEY `col_victim_name` (`col_victim_name`)," +
                "  KEY `col_victim_x` (`col_victim_x`)," +
                "  KEY `col_vitcim_z` (`col_victim_z`)," +
                "  KEY `col_killer_name` (`col_killer_name`)," +
                "  KEY `col_killer_x` (`col_killer_x`)," +
                "  KEY `col_killer_z` (`col_killer_z`)," +
                "  KEY `col_victim_player` (`col_victim_player`)," +
                "  KEY `col_killer_player` (`col_killer_player`)," +
                "  KEY `col_victim_owner` (`col_victim_owner`)," +
                "  KEY `col_killer_owner` (`col_killer_owner`));";
    }
}
