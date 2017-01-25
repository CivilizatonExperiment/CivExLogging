package com.civexperiment.CivExLogging.Database.Command;

import net.arcation.arcadion.interfaces.Insertable;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;

/**
 * Created by Ryan on 1/24/2017.
 */
public class CommandInsert implements Insertable
{
    String sender, command, args;
    Boolean isCanceled;
    Long time;

    public CommandInsert(String sender, String command, String args, Long time, boolean isCanceled)
    {
        this.sender = sender;
        this.command = command;
        this.args = args;
        this.time = time;
        this.isCanceled = isCanceled;
    }

    @Override
    public void setParameters(PreparedStatement preparedStatement) throws SQLException
    {
        preparedStatement.setTimestamp(1, new Timestamp(time));
        preparedStatement.setString(2, sender);
        preparedStatement.setString(3, command);
        preparedStatement.setString(4, args);
        preparedStatement.setBoolean(5, isCanceled);
    }

    @Override
    public String getStatement()
    {
        return "INSERT INTO `tbl_command_log` (`col_timestamp`,`col_player`,`col_command`,`col_arguments`," +
                "`col_canceled`) VALUES ( ?, ?, ?, ?, ?)";
    }
}
