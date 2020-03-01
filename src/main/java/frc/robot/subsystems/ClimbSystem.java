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

//import frc.robot.commands.*;
//import edu.wpi.first.wpilibj.livewindow.LiveWindow;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj.DigitalInput;
//import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Robot;
import frc.robot.Constants.Climb;
import frc.robot.helper.CanMotor;
import edu.wpi.first.wpilibj.Servo;
import frc.robot.interfaces.scheduler;
//import edu.wpi.first.wpilibj.SpeedController;
//END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=IMPORTS

public class ClimbSystem extends SubsystemBase { // implements scheduler{
    //private DigitalInput maxHeightLimit;
    private DigitalInput minHeightLimit;
    //public AnalogPotentiometer pot;

    private CanMotor climbMotor;
    private boolean climberLocked;
    private Servo ratchet;
    //private boolean ratchetLocked;

    public ClimbSystem() {

        minHeightLimit = new DigitalInput(Climb.MinHeightLimitID);
        addChild("MinHeightLimit",minHeightLimit);

        climbMotor = new CanMotor(Climb.CANClimbMotorID);
        addChild("ClimbMotor", climbMotor);
        
        climberLocked = true;
        //ratchetLocked = true;

        ratchet = new Servo(Climb.PWMRatchetServoID);
        addChild("ClimbRatchet", ratchet);

        ratchet.setPosition(Climb.RatchetClosed);

        climbMotor.Motor().setInverted(true);  
        climbMotor.Encoder().setPositionConversionFactor(Climb.CLIMBER_SCALE_TO_INCHES);
        
    }

    @Override
    public void periodic() {

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
        if(climbMotor.Motor().get() > 0) {
            climbMotor.Motor().set(0);
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
        return (climbMotor.Encoder().getPosition() >= Climb.MAX_CLIMB_POSITION);
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
        return this.climbMotor.Encoder().getPosition(); //com constant
    }

    public void resetPosition() {
        this.climbMotor.Encoder().setPosition(0);
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

    public void testMotor(double speed){
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