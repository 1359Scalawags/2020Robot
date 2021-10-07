// RobotBuilder Version: 2.0
//
// This file was generated by RobotBuilder. It contains sections of
// code that are automatically generated and assigned by robotbuilder.
// These sections will be updated in the future when you export to
// Java from RobotBuilder. Do not put any code or make any change in
// the blocks indicating autogenerated code or it will be lost on an
// update. Deleting the comments indicating the section will prevent
// it from being updated in the future.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

import com.revrobotics.ControlType;

import edu.wpi.first.wpilibj.DigitalInput;
import frc.robot.Constants.Climb;
import frc.robot.helper.Utilities;
import frc.robot.sendable.PIDSparkMax;
import frc.robot.sendable.SparkMaxEncoder;
import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class ClimbSystem extends SubsystemBase {

    private DigitalInput minHeightLimit;

    private PIDSparkMax climbMotor;
    private SparkMaxEncoder climbEncoder;
    private boolean climberLocked;
    private Servo ratchet;
    //private boolean ratchetLocked;

    public ClimbSystem() {

        minHeightLimit = new DigitalInput(Climb.MinHeightLimitID);
        addChild("MinHeightLimit",minHeightLimit);

        climbMotor = new PIDSparkMax(Climb.CANClimbMotorID);
        climbMotor.setControlType(ControlType.kPosition);
        addChild("ClimbMotor", climbMotor);
        
        climbEncoder = climbMotor.getEncoder();
        addChild("ClimbEncoder", climbEncoder);
        
        climberLocked = true;
        SmartDashboard.putBoolean("ClimberLocked", climberLocked);

        ratchet = new Servo(Climb.PWMRatchetServoID);
        addChild("ClimbRatchet", ratchet);
        ratchet.setPosition(Climb.RatchetClosed);

        climbMotor.setInverted(true);  
        climbEncoder.setPositionConversionFactor(Climb.CLIMBER_SCALE_TO_INCHES);
        climbEncoder.reset();
    }

    @Override
    public void periodic() {
        SmartDashboard.putBoolean("ClimberLocked", climberLocked);
        if(isAtBottom() && climbEncoder.getRate() < 0) {
            stop();
            resetPosition();
        }
    }

    /**
     * Allows drivers to use, lock, and unlock the Climber.
     */
    public void unlockClimber() {
        climberLocked = false;
    }

    public void unlockRatchet() {
        ratchet.setPosition(Climb.RatchetOpen);
        //ratchetLocked = false;
    }

    public void lockRatchet() {
        if(climbMotor.get() > 0) {
            climbMotor.set(0);
        }
        ratchet.setPosition(Climb.RatchetClosed);
        //ratchetLocked = true; 
    }

    public boolean isClimberLocked() {
        return climberLocked;
    }

    public boolean isRatchetLocked() {
        double difference = Math.abs(ratchet.get() - Climb.RatchetOpen);
        return difference > 0.25;
    }

    public boolean isAtTop() {
        return (climbEncoder.getDistance() >= Climb.MAX_CLIMB_POSITION);
    }

    public boolean isAtBottom() {
        return (minHeightLimit.get() == Climb.LIMIT_PRESSED);
    }

    public void stop() {
        climbMotor.set(0);
    }

    /**
     * 
     * @return Returns the position of climber in Inches
     */
    public double getPosition() {
        return climbEncoder.getDistance(); //com constant
    }

    public void resetPosition() {
        climbEncoder.reset();
    }

    /**
     * Primary method for moving the climber mechanism. 
     * @param position The desired elevation in inches
     * @return Returns true if movement is allowed, false if not
     */
    public boolean moveTo(double position) {
        double currentPosition = climbEncoder.getDistance();
        position = Utilities.Clamp(position, Climb.MIN_CLIMB_POSITION, Climb.MAX_CLIMB_POSITION);
        if(!isClimberLocked()) {
            if( !isRatchetLocked() || (position < currentPosition) ) {
                climbMotor.setSetpoint(position);
                return true;
            }
        } else {
            stop();
        }
        return false;
    }

    /**
     * 
     * @param speed Positive numbers elevate...negative numbers climb.
     */
    
    public void move(double speed) {
        if (climberLocked) {
            climbMotor.set(0);
        } else {
            if (speed > 0) {
                if (!isAtTop() && !isRatchetLocked()) {
                    climbMotor.set(speed);
                } else {
                    climbMotor.set(0);
                 }
             } else {
                 if (minHeightLimit.get() == Climb.LIMIT_NOTPRESSED) {
                     climbMotor.set(speed);
                 } else {
                     climbMotor.set(0);
                 }
             }
         }
     }

    public void setClimbMotor(double speed){
        climbMotor.set(speed);
    }

    // @Override
    // public void updateDash(boolean Override) {
    //     double motorClimbSpeed = climbMotor.get();
    //     double dashClimbSpeed = SmartDashboard.getNumber("CANClimbSpeed", 0);

    //     boolean RachetState = SmartDashboard.getBoolean("PWMRatchetState", false);
    //     if(Override){
    //         if(dashClimbSpeed != motorClimbSpeed)
    //             move(dashClimbSpeed);
    //         if(RachetState != isRatchetLocked()){
    //             if(RachetState)
    //                 lockRatchet();
    //             else
    //                 unlockRatchet();
    //         }
    //     }
    //     else{
    //         if(RachetState != isRatchetLocked())
    //             SmartDashboard.putBoolean("PWMRatchetState", isRatchetLocked());
    //         if(dashClimbSpeed != motorClimbSpeed)
    //             SmartDashboard.putNumber("CANClimbSpeed", motorClimbSpeed);
    //     }
    // }

    // @Override
    // public void putValues() {
    //     SmartDashboard.putBoolean("PWMRatchetState", isRatchetLocked());
    //     SmartDashboard.putNumber("CANClimbSpeed", climbMotor.get());
    // }
}