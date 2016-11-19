package com.civexperiment.CivExLogging.Database.Tables;

/**
 * Created by Ryan on 11/19/2016.
 */
public class CommandTable implements Table
{
    @Override
    public String getName()
    {
        return "tbl_command_log";
    }

    @Override
    public String getStatement()
    {
        return "CREATE TABLE `tbl_command_log` (" +
                "  `col_id` int(13) NOT NULL AUTO_INCREMENT," +
                "  `col_timestamp` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' ON UPDATE CURRENT_TIMESTAMP," +
                "  `col_player` varchar(255) NOT NULL," +
                "  `col_command` varchar(255) NOT NULL," +
                "  `col_arguments` varchar(255) NOT NULL," +
                "  `col_cancelled` tinyint(1) NOT NULL," +
                "  PRIMARY KEY (`col_id`)," +
                "  KEY `col_timestamp` (`col_timestamp`)," +
                "  KEY `col_player` (`col_player`)," +
                "  KEY `col_command` (`col_command`)," +
                "  KEY `col_argument` (`col_arguments`)," +
                "  KEY `col_cancelled` (`col_cancelled`));";
    }
}
