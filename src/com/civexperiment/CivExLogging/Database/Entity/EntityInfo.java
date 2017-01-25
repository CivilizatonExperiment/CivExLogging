
package com.civexperiment.CivExLogging.Database.Entity;


public class EntityInfo {

    private final String name;
    private final String owner;
    private final boolean jockey;
    private final Double x;
    private final Double y;
    private final Double z;
    private final Float pitch;
    private final Float yaw;
    private final boolean isPlayer;
    private final String leftHand;
    private final String rightHand;

    public EntityInfo(String name, String owner, boolean jockey, Double x, Double y, Double z, Float pitch, Float yaw, boolean isPlayer, String leftHand, String rightHand) {
        this.name = name;
        this.owner = owner;
        this.jockey = jockey;
        this.x = x;
        this.y = y;
        this.z = z;
        this.pitch = pitch;
        this.yaw = yaw;
        this.isPlayer = isPlayer;
        this.leftHand = leftHand;
        this.rightHand = rightHand;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @return the owner
     */
    public String getOwner() {
        return owner;
    }

    /**
     * @return the jockey
     */
    public boolean getIsJockey() {
        return jockey;
    }

    /**
     * @return the x
     */
    public Double getX() {
        return x;
    }

    /**
     * @return the y
     */
    public Double getY() {
        return y;
    }

    /**
     * @return the z
     */
    public Double getZ() {
        return z;
    }

    /**
     * @return the pitch
     */
    public Float getPitch() {
        return pitch;
    }

    /**
     * @return the yaw
     */
    public Float getYaw() {
        return yaw;
    }

    /**
     * @return the isPlayer
     */
    public boolean getIsPlayer() {
        return isPlayer;
    }

    /**
     * @return the leftHand
     */
    public String getLeftHand() {
        return leftHand;
    }

    /**
     * @return the rightHand
     */
    public String getRightHand() {
        return rightHand;
    }

}
