package com.civexperiment.CivExLogging.Database.Tables;

import java.sql.PreparedStatement;

/**
 * Created by Ryan on 11/18/2016.
 */
public interface Table
{
    /**
     * Simply has the name of the table in mysql
     * @return String of the name of the table
     */
    String getName();

    /**
     * This will have the table create if not exsits statement.
     * @return The MySQL command that needs to create the table.
     */
    String getStatement();
}
