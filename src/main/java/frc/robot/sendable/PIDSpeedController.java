package frc.robot.sendable;

public interface PIDSpeedController extends SendableSpeedController {

    void setP(double p);
    void setI(double i);
    void setD(double d);
    default void setF(double f) {

    }
    void setSetpoint(double setPoint);

}