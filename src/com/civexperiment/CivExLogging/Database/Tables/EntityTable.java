
package com.civexperiment.CivExLogging.Database.Tables;


public class EntityTable implements Table
{
    @Override
    public String getName()
    {
        return "tbl_entity_log";
    }

    @Override
    public String getStatement()
    {
        return "CREATE TABLE IF NOT EXISTS `tbl_entity_log` (" +
                "  `col_id` int(11) NOT NULL AUTO_INCREMENT," +
                "  `col_timestamp` timestamp NULL DEFAULT NULL," +
                "  `col_world` varchar(45) DEFAULT NULL," +
                "  `col_log_reason` varchar(45) DEFAULT NULL," + 
                "  `col_cause` varchar(45) DEFAULT NULL," +
                "  `col_entity_name` varchar(45) DEFAULT NULL," +
                "  `col_entity_owner` varchar(45) DEFAULT NULL," +
                "  `col_entity_jockey` tinyint(1) DEFAULT NULL," +
                "  `col_entity_left_hand` varchar(45) DEFAULT NULL," +
                "  `col_entity_right_hand` varchar(45) DEFAULT NULL," +
                "  `col_entity_x` double DEFAULT NULL," +
                "  `col_entity_y` double DEFAULT NULL," +
                "  `col_entity_z` double DEFAULT NULL," +
                "  `col_entity_pitch` float DEFAULT NULL," +
                "  `col_entity_yaw` float DEFAULT NULL," +
                "  `col_entity_player` tinyint(1) DEFAULT NULL," +
                "  `col_killer_name` varchar(45) DEFAULT NULL," +
                "  `col_killer_owner` varchar(45) DEFAULT NULL," +
                "  `col_killer_jockey` tinyint(1) DEFAULT NULL," +
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
                "  KEY `col_log_reason` (`col_log_reason`)," +
                "  KEY `col_cause` (`col_cause`)," +
                "  KEY `col_entity_name` (`col_entity_name`)," +
                "  KEY `col_entity_x` (`col_entity_x`)," +
                "  KEY `col_entity_z` (`col_entity_z`)," +
                "  KEY `col_killer_name` (`col_killer_name`)," +
                "  KEY `col_killer_x` (`col_killer_x`)," +
                "  KEY `col_killer_z` (`col_killer_z`)," +
                "  KEY `col_entity_player` (`col_entity_player`)," +
                "  KEY `col_killer_player` (`col_killer_player`)," +
                "  KEY `col_entity_owner` (`col_entity_owner`)," +
                "  KEY `col_killer_owner` (`col_killer_owner`));";
    }
}
