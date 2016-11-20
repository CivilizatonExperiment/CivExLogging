package com.civexperiment.CivExLogging.Database.Tables;

/**
 * Created by Ryan on 11/19/2016.
 */
public class SessionTable implements Table
{
    @Override
    public String getName()
    {
        return "tbl_session_log";
    }

    @Override
    public String getStatement()
    {
        return "CREATE TABLE IF NOT EXISTS `tbl_session_log` (" +
                "  `col_id` int(11) NOT NULL," +
                "  `col_player_uuid` varchar(50) NOT NULL," +
                "  `col_player_name` varchar(32) NOT NULL," +
                "  `col_ip` varchar(15) NOT NULL," +
                "  `col_login` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' ON UPDATE CURRENT_TIMESTAMP," +
                "  `col_logout` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' ON UPDATE CURRENT_TIMESTAMP," +
                "  PRIMARY KEY (`col_id`)," +
                "  KEY `col_player_uuid` (`col_player_uuid`)," +
                "  KEY `col_player_name` (`col_player_name`)," +
                "  KEY `col_login` (`col_login`)," +
                "  KEY `col_logout` (`col_logout`)," +
                "  KEY `col_ip` (`col_ip`))";
    }
}
