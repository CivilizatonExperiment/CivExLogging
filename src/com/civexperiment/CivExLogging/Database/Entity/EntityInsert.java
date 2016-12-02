
package com.civexperiment.CivExLogging.Database.Entity;

import com.civexperiment.CivExLogging.Listeners.Entity.EntityLogging.LogReason;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.sql.Types;
import net.arcation.arcadion.interfaces.Insertable;



public class EntityInsert implements Insertable {

    Long time;

    String world;
    String cause;

    EntityInfo entityInfo;
    EntityInfo killerInfo;

    LogReason logReason;

    private final Integer xp;

    public EntityInsert(Long time,
            String world,
            String cause,
            EntityInfo entityInfo,
            EntityInfo killerInfo,
            Integer xp,
            LogReason logReason) {
        this.time = time;
        this.world = world;
        this.cause = cause;
        this.entityInfo = entityInfo;
        this.killerInfo = killerInfo;
        this.xp = xp;
        this.logReason = logReason;
    }

    @Override
    public void setParameters(PreparedStatement preparedStatement) throws SQLException {

        preparedStatement.setTimestamp(1, new Timestamp(time));
        preparedStatement.setString(2, world);
        preparedStatement.setString(3, logReason.toString());

        if (cause != null) {
            preparedStatement.setString(4, cause);
        } else {
            preparedStatement.setNull(4, Types.VARCHAR);
        }

        preparedStatement.setString(5, entityInfo.getName());
        preparedStatement.setString(6, entityInfo.getOwner());

        preparedStatement.setBoolean(7, entityInfo.getIsJockey());

        if (entityInfo.getLeftHand() != null) {
            preparedStatement.setString(8, entityInfo.getLeftHand());
        } else {
            preparedStatement.setNull(8, Types.VARCHAR);
        }
        if (entityInfo.getRightHand() != null) {
            preparedStatement.setString(9, entityInfo.getRightHand());
        } else {
            preparedStatement.setNull(9, Types.VARCHAR);
        }

        preparedStatement.setDouble(10, entityInfo.getX());
        preparedStatement.setDouble(11, entityInfo.getY());
        preparedStatement.setDouble(12, entityInfo.getZ());
        preparedStatement.setFloat(13, entityInfo.getPitch());
        preparedStatement.setFloat(14, entityInfo.getYaw());
        preparedStatement.setBoolean(15, entityInfo.getIsPlayer());

        if (logReason.equals(LogReason.DEATH) && killerInfo != null) {
            preparedStatement.setString(16, killerInfo.getName());
            preparedStatement.setString(17, killerInfo.getOwner());
            preparedStatement.setBoolean(18, killerInfo.getIsJockey());

            if (killerInfo.getLeftHand() != null) {
                preparedStatement.setString(19, killerInfo.getLeftHand());
            } else {
                preparedStatement.setNull(19, Types.VARCHAR);
            }
            if (killerInfo.getRightHand() != null) {
                preparedStatement.setString(20, killerInfo.getRightHand());
            } else {
                preparedStatement.setNull(20, Types.VARCHAR);
            }

            preparedStatement.setDouble(21, killerInfo.getX());
            preparedStatement.setDouble(22, killerInfo.getY());
            preparedStatement.setDouble(23, killerInfo.getZ());
            preparedStatement.setFloat(24, killerInfo.getPitch());
            preparedStatement.setFloat(25, killerInfo.getYaw());
            preparedStatement.setBoolean(26, killerInfo.getIsPlayer());
            preparedStatement.setInt(27, xp);
        } else {

            preparedStatement.setNull(16, Types.VARCHAR);
            preparedStatement.setNull(17, Types.VARCHAR);
            preparedStatement.setNull(18, Types.BOOLEAN);
            preparedStatement.setNull(19, Types.VARCHAR);
            preparedStatement.setNull(20, Types.VARCHAR);
            preparedStatement.setNull(21, Types.DOUBLE);
            preparedStatement.setNull(22, Types.DOUBLE);
            preparedStatement.setNull(23, Types.DOUBLE);
            preparedStatement.setNull(24, Types.FLOAT);
            preparedStatement.setNull(25, Types.FLOAT);
            preparedStatement.setNull(26, Types.BOOLEAN);

            if (logReason.equals(LogReason.DEATH)) {
                preparedStatement.setInt(27, xp);
            } else {
                preparedStatement.setNull(27, Types.INTEGER);
            }
        }
    }

    @Override
    public String getStatement() {
        return "INSERT INTO `tbl_entity_log` (`col_timestamp`,`col_world`,`col_log_reason`,`col_cause`,`col_entity_name`,"
                + "`col_entity_owner`,`col_entity_jockey`,`col_entity_left_hand`,`col_entity_right_hand`,`col_entity_x`,`col_entity_y`,"
                + "`col_entity_z`,`col_entity_pitch`,`col_entity_yaw`,`col_entity_player`,`col_killer_name`,"
                + "`col_killer_owner`,`col_killer_jockey`,`col_killer_left_hand`,`col_killer_right_hand`,`col_killer_x`,`col_killer_y`,"
                + "`col_killer_z`,`col_killer_pitch`,`col_killer_yaw`,`col_killer_player`,`col_xp`) "
                + "VALUES ( ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
    }

}
