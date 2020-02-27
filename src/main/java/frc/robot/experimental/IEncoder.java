package frc.robot.experimental;

import edu.wpi.first.wpilibj.Sendable;

/**
 * Credit to chopshop FRC robotics team 166.
 */
public interface IEncoder extends Sendable {

    /**
     * Reset the encoder distance to zero.
     * 
     * This is useful for resetting position when a known reference is reached
     */
    void reset();

    /**
     * Gets the current Distance value from the encoder.
     * 
     * @return distance traveled
     */
    double getDistance();

    /**
     * Gets the current rate of rotation.
     * 
     * @return the current rate of rotation
     */
    double getRate();

    /**
     * Determine if the counter is not moving.
     *
     * @return true if the counter has not changed for the max period
     */
    default boolean isStopped() {
        return getRate() == 0;
    }

    /**
     * Determine which direction the encoder is going.
     *
     * @return true for one direction, false for the other
     */
    default boolean isMovingForward() {
        return getRate() > 0;
    }

}