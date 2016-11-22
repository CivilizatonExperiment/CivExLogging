
package com.civexperiment.CivExLogging.Database.Death;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.sql.Types;
import net.arcation.arcadion.interfaces.Insertable;

public class DeathInsert implements Insertable {

    Long time;

    String world;
    String cause;

    String victimName;
    String victimOwner;
    String victimLeftHand;
    String victimRightHand;
    Double victimX;
    Double victimY;
    Double victimZ;
    Float victimPitch;
    Float victimYaw;
    Integer victimPlayer;

    String killerName;
    String killerOwner;
    String killerLeftHand;
    String killerRightHand;
    Double killerX;
    Double killerY;
    Double killerZ;
    Float killerPitch;
    Float killerYaw;
    Integer killerPlayer;

    Integer xp;

    public DeathInsert(Long time,
            String world,
            String cause,
            String victimName,
            String victimOwner,
            String victimLeftHand,
            String victimRightHand,
            Double victimX,
            Double victimY,
            Double victimZ,
            Float victimPitch,
            Float victimYaw,
            Integer victimPlayer,
            String killerName,
            String killerOwner,
            String killerLeftHand,
            String killerRightHand,
            Double killerX,
            Double killerY,
            Double killerZ,
            Float killerPitch,
            Float killerYaw,
            Integer killerPlayer,
            Integer xp) {
        this.time = time;
        this.world = world;
        this.cause = cause;
        this.victimName = victimName;
        this.victimOwner = victimOwner;
        this.victimLeftHand = victimLeftHand;
        this.victimRightHand = victimRightHand;
        this.victimX = victimX;
        this.victimY = victimY;
        this.victimZ = victimZ;
        this.victimYaw = victimYaw;
        this.victimPitch = victimPitch;
        this.victimPlayer = victimPlayer;
        this.killerName = killerName;
        this.killerOwner = killerOwner;
        this.killerLeftHand = killerLeftHand;
        this.killerRightHand = killerRightHand;
        this.killerX = killerX;
        this.killerY = killerY;
        this.killerZ = killerZ;
        this.killerYaw = killerYaw;
        this.killerPitch = killerPitch;
        this.killerPlayer = killerPlayer;
        this.xp = xp;
    }

    @Override
    public void setParameters(PreparedStatement preparedStatement) throws SQLException {
        preparedStatement.setTimestamp(1, new Timestamp(time));
        preparedStatement.setString(2, world);
        preparedStatement.setString(3, cause);
        preparedStatement.setString(4, victimName);
        preparedStatement.setString(5, victimOwner);
        if (victimLeftHand != null) {
            preparedStatement.setString(6, victimLeftHand);
        } else {
            preparedStatement.setNull(6, Types.VARCHAR);
        }
        if (victimRightHand != null) {
            preparedStatement.setString(7, victimRightHand);
        } else {
            preparedStatement.setNull(7, Types.VARCHAR);
        }
        preparedStatement.setDouble(8, victimX);
        preparedStatement.setDouble(9, victimY);
        preparedStatement.setDouble(10, victimZ);
        preparedStatement.setFloat(11, victimPitch);
        preparedStatement.setFloat(12, victimYaw);
        preparedStatement.setInt(13, victimPlayer);

        if (killerName != null) {
            preparedStatement.setString(14, killerName);
        } else {
            preparedStatement.setNull(14, Types.VARCHAR);
        }

        if (killerName != null) {
            preparedStatement.setString(15, killerOwner);
        } else {
            preparedStatement.setNull(15, Types.VARCHAR);
        }

        if (killerLeftHand != null) {
            preparedStatement.setString(16, killerLeftHand);
        } else {
            preparedStatement.setNull(16, Types.VARCHAR);
        }

        if (killerRightHand != null) {
            preparedStatement.setString(17, killerRightHand);
        } else {
            preparedStatement.setNull(17, Types.VARCHAR);
        }

        if (killerX != null) {
            preparedStatement.setDouble(18, killerX);
        } else {
            preparedStatement.setNull(18, Types.INTEGER);
        }

        if (killerY != null) {
            preparedStatement.setDouble(19, killerY);
        } else {
            preparedStatement.setNull(19, Types.DOUBLE);
        }

        if (killerZ != null) {
            preparedStatement.setDouble(20, killerZ);
        } else {
            preparedStatement.setNull(20, Types.DOUBLE);
        }

        if (killerPitch != null) {
            preparedStatement.setFloat(21, killerPitch);
        } else {
            preparedStatement.setNull(21, Types.DOUBLE);
        }

        if (killerYaw != null) {
            preparedStatement.setFloat(22, killerYaw);
        } else {
            preparedStatement.setNull(22, Types.FLOAT);
        }

        if (killerPlayer != null) {
            preparedStatement.setInt(23, killerPlayer);
        } else {
            preparedStatement.setNull(23, Types.SMALLINT);
        }

        if (xp != null) {
            preparedStatement.setInt(24, xp);
        } else {
            preparedStatement.setNull(24, Types.INTEGER);
        }
    }

    @Override
    public String getStatement() {
        return "INSERT INTO `tbl_death_log` (`col_timestamp`,`col_world`,`col_cause`,`col_victim_name`,"
                + "`col_victim_owner`,`col_victim_left_hand`,`col_victim_right_hand`,`col_victim_x`,`col_victim_y`,"
                + "`col_victim_z`,`col_victim_pitch`,`col_victim_yaw`,`col_victim_player`,`col_killer_name`,"
                + "`col_killer_owner`,`col_killer_left_hand`,`col_killer_right_hand`,`col_killer_x`,`col_killer_y`,"
                + "`col_killer_z`,`col_killer_pitch`,`col_killer_yaw`,`col_killer_player`,`col_xp`) "
                + "VALUES ( ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
    }

}
