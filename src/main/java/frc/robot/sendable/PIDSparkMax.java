package frc.robot.sendable;

import com.revrobotics.CANPIDController;
import com.revrobotics.CANSparkMax;
import com.revrobotics.ControlType;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.smartdashboard.SendableBuilder;

/**
 * PIDSpeedController
 *
 * Derives PIDSpeedController to allow for use on a SparkMax Speed Controller.
 * It will act as a normal SparkMax with encoders, but will also be able to use
 * PID.
 * 
 * @author Andrew Martin
 * @since 2020-02-7
 */
public class PIDSparkMax implements PIDSpeedController {

    private final boolean isHallEffect = true;
    private final CANSparkMax sparkMax;
    private final SparkMaxEncoder encoder;
    private final CANPIDController sparkPID;
    private ControlType controlType = ControlType.kVelocity;

    public PIDSparkMax(final int id) {
        this.sparkMax = new CANSparkMax(id, MotorType.kBrushless);
        this.encoder = new SparkMaxEncoder(this.sparkMax.getEncoder());
        this.sparkPID = this.sparkMax.getPIDController();
        this.sparkMax.restoreFactoryDefaults();
    }

    public void setPID(double p, double i, double d, double iz, double ff) {
        sparkPID.setP(p);
        sparkPID.setI(i);
        sparkPID.setD(d);
        sparkPID.setIZone(iz);
        sparkPID.setFF(ff);
    }

    public CANSparkMax getMotorController() {
        return sparkMax;
    }

    public CANPIDController getPidController() {
        return sparkPID;
    }

    /**
     * Set the control type
     * 
     * @param controlType The controlType to set.
     */
    public void setControlType(final ControlType controlType) {
        this.controlType = controlType;
    }

    @Override
    public SparkMaxEncoder getEncoder() {
        return encoder;
    }

    @Override
    public void setP(final double kp) {
        sparkPID.setP(kp);
    }

    @Override
    public void setI(final double ki) {
        sparkPID.setI(ki);
    }

    @Override
    public void setD(final double kd) {
        sparkPID.setD(kd);
    }

    @Override
    public void setF(final double kf) {
        sparkPID.setFF(kf);
    }

    @Override
    public void setSetpoint(final double setPoint) {
        sparkPID.setReference(setPoint, controlType);
    }

    @Override
    public void initSendable(final SendableBuilder builder) {
        builder.setSmartDashboardType("Speed Controller");
        builder.setActuator(true);
        builder.setSafeState(this::disable);
        builder.addDoubleProperty("Value", this::get, this::set);
    }

    @Override
    public void set(final double speed) {
        sparkMax.set(speed);
    }

    @Override
    public double get() {
        return sparkMax.get();
    }

    @Override
    public void setInverted(final boolean isInverted) {
        sparkMax.setInverted(isInverted);
        //cannot reverse encoder when using Hall Effect encoder
        if(!isHallEffect) {
            encoder.setReverseDirection(isInverted);
        }
    }

    @Override
    public boolean getInverted() {
        return sparkMax.getInverted();
    }

    @Override
    public void disable() {
        sparkMax.disable();
    }

    @Override
    public void stopMotor() {
        sparkMax.stopMotor();
    }

    @Override
    public void pidWrite(final double output) {
        sparkMax.pidWrite(output);
    }
}