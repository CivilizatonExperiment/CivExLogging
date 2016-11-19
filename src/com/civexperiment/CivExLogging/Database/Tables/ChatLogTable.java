package com.civexperiment.CivExLogging.Database.Tables;

/**
 * Created by Ryan on 11/18/2016.
 */
public class ChatLogTable implements Table
{

    @Override
    public String getName()
    {
        return "tbl_chat_log";
    }

    @Override
    public String getStatement()
    {
        return "CREATE TABLE IF NOT EXISTS tbl_chat_log ("
            + "`col_id` int(10) unsigned NOT NULL AUTO_INCREMENT,"
            + "`col_timestamp` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',"
            + "`col_channel` varchar(255) NOT NULL,"
            + "`col_sender` varchar(16) NOT NULL,"
            + "`col_receiver` varchar(16) NOT NULL,"
            + "`col_message` varchar(255) NOT NULL,"
            + "PRIMARY KEY (`col_id`));";
    }
}
