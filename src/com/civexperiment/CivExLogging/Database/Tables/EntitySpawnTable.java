package com.civexperiment.CivExLogging.Database.Tables;

/**
 * Created by Ryan on 11/19/2016.
 */
public class EntitySpawnTable implements Table
{

    @Override
    public String getName()
    {
        return "tbl_entity_spawn_log";
    }

    @Override
    public String getStatement()
    {
        return "CREATE TABLE IF NOT EXISTS `tbl_entity_spawn_log` (" +
                "  `col_id` int(11) NOT NULL AUTO_INCREMENT," +
                "  `col_entity_type` varchar(255) NOT NULL," +
                "  `col_timestamp` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' ON UPDATE CURRENT_TIMESTAMP," +
                "  `col_jocky` tinyint(1) NOT NULL," +
                "  `col_x` int(11) NOT NULL," +
                "  `col_y` int(11) NOT NULL," +
                "  `col_z` int(11) NOT NULL," +
                "  PRIMARY KEY (`col_id`)," +
                "  KEY `col_timestamp` (`col_timestamp`)," +
                "  KEY `col_entity_type` (`col_entity_type`)," +
                "  KEY `col_jocky` (`col_jocky`))";
    }
}
