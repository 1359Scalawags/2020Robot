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
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Constants;
import frc.robot.helper.CanMotor;
import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj.AnalogPotentiometer;
//import edu.wpi.first.wpilibj.SpeedController;
//END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=IMPORTS

/**
 *  TODO: If motor logic is reversed then uncomment motor.setInverted()
 *  TODO: Find out the exact mechanics of the lift mechanism
 */
    public class ClimbSystem extends SubsystemBase {
    //private DigitalInput maxHeightLimit;
    private DigitalInput minHeightLimit;
    //public AnalogPotentiometer pot;

    private CanMotor climbMotor;
    private boolean climberLocked;
    private Servo ratchet;
    private boolean ratchetLocked;

    public ClimbSystem() {
        //maxHeightLimit = new DigitalInput(Constants.MaxHeightLimitID);
        //addChild("MaxHeightLimit",maxHeightLimit);
    
        minHeightLimit = new DigitalInput(Constants.MinHeightLimitID);
        addChild("MinHeightLimit",minHeightLimit);
       
        climbMotor = new CanMotor(Constants.CANClimbMotorID);
        SmartDashboard.putNumber("ClimbMotorValue", climbMotor.Motor().get());
        
        climberLocked = true;
        ratchetLocked = true;

        ratchet = new Servo(Constants.PWMRatchetServoID);
        ratchet.setPosition(Constants.RatchetClosed);
        climbMotor.Motor().setInverted(true);  
        
        // pot = new AnalogPotentiometer(Constants.CLIMBERPOTID);
    }

    @Override
    public void periodic() {

        double dashMotor = SmartDashboard.getNumber("ClimbMotorValue", 0.0);
        if(climbMotor.Motor().get() != dashMotor) {
            climbMotor.Motor().set(dashMotor);
        }
    }

    public void updateDashboard() {
        SmartDashboard.putNumber("ClimbSpeed", climbMotor.Encoder().getVelocity());
    }


    /**
     * Allows drivers to use, lock, and unlock the Climber.
     */
    public void unlockClimber() {
		climberLocked = false;
    }
    
    public void unlockRatchet() {
        ratchetLocked = false;
        ratchet.setPosition(Constants.RatchetOpen);
    }

    public void lockRatchet() {
        if(climbMotor.Motor().get() > 0) {
            climbMotor.Motor().set(0);
        }
       ratchetLocked = true; 
       ratchet.setPosition(Constants.RatchetClosed);
    }

	public boolean isClimberLocked() {
		return climberLocked;
    }
	
	// public boolean getClimberLock() {
	// 	return climberLocked;
    // }
    
    public boolean isAtTop() {
        return (climbMotor.Encoder().getPosition() >= Constants.MAX_CLIMB_POSITION);
    }

    public boolean isAtBottom() {
        return (minHeightLimit.get() == Constants.LIMIT_PRESSED);
    }

    public void stop() {
        climbMotor.setSpeed(0);
    }

    public double getPosition() {
        return this.climbMotor.Encoder().getPosition(); //com constant
        //return pot.get() * Constants.PotToInches;
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
            climbMotor.setSpeed(0);
        } else {
            if (speed > 0) {
                if (climbMotor.Encoder().getPosition() < Constants.MAX_CLIMB_POSITION && ratchetLocked == false) {
                    climbMotor.setSpeed(speed);
                } else {
                    climbMotor.setSpeed(0);
                }
            } else {
                if (minHeightLimit.get() == Constants.LIMIT_NOTPRESSED) {
                    climbMotor.setSpeed(speed);
                } else {
                    climbMotor.setSpeed(0);
                }

            }
        }
    }
}
