package frc.robot.helper;

import com.revrobotics.CANEncoder;
import com.revrobotics.CANSparkMax;
import com.revrobotics.ControlType;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.SpeedController;

import com.revrobotics.CANPIDController;

//import edu.wpi.first.wpilibj.SpeedController;
import frc.robot.Constants;
import frc.robot.helper.PID_Values;

public class CanMotor implements SpeedController{
    private CANEncoder encoder;
    private CANSparkMax motor;
    private CANPIDController controller;
    private double value;
    private PID_Values pid;

    private double scaleValue;


    private ControlType controlType;
    
// SpeedController speedController, SpeedController[] speedControllers 



    public CanMotor(int id){
        this(id, Constants.MAXRPM, ControlType.kVelocity ,new PID_Values());
    }

    public CanMotor(int id, double maxScaleValue, ControlType controlType, PID_Values pid) {
        this.controlType = controlType;
        this.scaleValue = maxScaleValue;
        this.pid = pid;
        this.motor = new CANSparkMax(id, MotorType.kBrushless);
        this.motor.restoreFactoryDefaults();
        this.motor.setInverted(false);
        this.controller.setOutputRange(-1, 1);
        this.encoder = motor.getEncoder();
        updatePID();
    }

    public double getSpeed(){
        return value;
    }

    public void setPID(double[] PID){
        pid = new PID_Values(PID);
        updatePID();
    }

    private void updatePID(){
        controller.setP(pid.kP);
        controller.setI(pid.kI);
        controller.setD(pid.kD);
        controller.setIZone(pid.kIz);
        controller.setFF(pid.kFf);
    }
    public PID_Values getPID(){
       return pid;
    }

    /**
     * Sets velocity as percentage of maximum RPM.
     * @param value_ A value between -1 and 1
     */
    private void setValue(double value_, boolean forcedUpdate) {
        if(value != value_){
            value = value_;
            updateSpeed();
        }
    }

    public void updateSpeed(){
        controller.setReference(value*Constants.MAXRPM, ControlType.kVelocity);
    }

    public CANEncoder Encoder(){
        return encoder;
    }
    // public SpeedController SpeedCont(){
    //     return speedCont;
    // }

    public CANSparkMax Motor(){
        return motor;
    }

    public CANPIDController Controller(){
        return controller;
    }
    
    /**
     * @return The current value of the motor between -1 and 1.
     */
    @Override
    public double get() {
        return this.value;
    }

    /**
     * Sets percentage value for the motor dependent on the current ControlType.
     * @param value A value between -1 and 1.
     */
    @Override
    public void set(double value) {
        this.setValue(value, false);
    }

    /**
     * Sets percentage value for the motor dependent on the current ControlType.
     * @param value A value between -1 and 1 that is multipled by the Scale.
     * @param forcedupdate Update the underlying motor object even if the value has not changed.
     * @see BrushlessCANMotor#getScale() BrushlessCANMotor.getScale()
     */ 
    public void set(double value, boolean forcedupdate) {
        if(value != this.value || forcedupdate) {
            this.value = value;
            updateMotor();
        }
    }

    private void updateMotor() {
        motor.getPIDController().setReference(value * this.scaleValue, this.controlType);
    }

    @Override
    public boolean getInverted() {
        return this.motor.getInverted();
    }

    @Override
    public void setInverted(boolean isInverted) {
        this.motor.setInverted(isInverted);
    }

    @Override
    public void disable() {
        this.value = 0;
        this.motor.disable();
    }

    @Override
    public void stopMotor() {
        this.value = 0;
        this.motor.stopMotor();
    }

    @Override
    public void pidWrite(double output) {
        this.motor.pidWrite(output);

    }
}