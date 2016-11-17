package com.civexperiment.CivExLogging.Database;

import net.arcation.arcadion.interfaces.Insertable;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;

/**
 * Created by Ryan on 11/16/2016.
 */
public class ChatInsert implements Insertable
{

    String sender, receiver, channel, message;
    Long time;

    public ChatInsert(String sender, String receiver, String channel, String message, Long time){
        this.sender = sender;
        this.receiver = receiver;
        this.channel = channel;
        this.time = time;
        this.message = message;
    }

    @Override
    public void setParameters(PreparedStatement preparedStatement) throws SQLException
    {
        preparedStatement.setTimestamp(1, new Timestamp(time));
        preparedStatement.setString(2, channel);
        preparedStatement.setString(3, sender);
        preparedStatement.setString(4, receiver);
        preparedStatement.setString(5, message);
    }

    @Override
    public String getStatement()
    {
        return "INSERT INTO `tbl_chat_log` (`col_timestamp`,`col_channel`,`col_sender`,`col_receiver`," +
                "`col_message`) VALUES ( ?, ?, ?, ?, ?)";
    }
}
