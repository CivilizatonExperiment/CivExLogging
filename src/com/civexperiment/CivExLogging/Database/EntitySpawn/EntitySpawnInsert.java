
package com.civexperiment.CivExLogging.Database.EntitySpawn;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import net.arcation.arcadion.interfaces.Insertable;

/**
 * Created by mbach on 11/30/2016.
 */
public class EntitySpawnInsert implements Insertable {
    
    private final String entityType;
    private final long time;
    private final boolean jockey;
    private final int x;
    private final int y;
    private final int z;
    
    public EntitySpawnInsert(String entityType, long time, boolean jockey, int x, int y, int z) {
        this.entityType = entityType;
        this.time = time;
        this.jockey = jockey;
        this.x = x;
        this.y = y;
        this.z = z;
    }
    
    @Override
    public void setParameters(PreparedStatement ps) throws SQLException {
        ps.setString(1, entityType);
        ps.setTimestamp(2, new Timestamp(time));
        ps.setBoolean(3, jockey);
        ps.setInt(4, x);
        ps.setInt(5, y);
        ps.setInt(6, z);
    }

    @Override
    public String getStatement() {
        return "INSERT INTO `tbl_entity_spawn_log` (`col_entity_type`,`col_timestamp`,`col_jockey`,`col_x`,`col_y`,`col_z`) "
                + "VALUES( ?, ?, ?, ?, ?, ?)";
    }

}
