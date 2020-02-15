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
    private DigitalInput maxHeightLimit;
    private DigitalInput minHeightLimit;
    public AnalogPotentiometer pot;

    // TODO: Find out if this motor is a NEO on CAN network
    private CanMotor climbMotor;
    private boolean climberLocked;
    private Servo ratchetLock;
    private boolean servoLocked;

    public ClimbSystem() {
        maxHeightLimit = new DigitalInput(Constants.MaxHeightLimitID);
        addChild("MaxHeightLimit",maxHeightLimit);
    
        minHeightLimit = new DigitalInput(Constants.MinHeightLimitID);
        addChild("MinHeightLimit",minHeightLimit);
       
        // TODO: change if a NEO
        climbMotor = new CanMotor(Constants.ClimbMotorID);
        
        climberLocked = true;
        servoLocked = true;

        //climbMotor.setInverted(false);  
        
        // pot = new AnalogPotentiometer(Constants.CLIMBERPOTID);
    }

    @Override
    public void periodic() {

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
    
    public void allowExtend() {
        servoLocked = false;
    }

    public void preventExtend() {
       servoLocked = true; 
    }

	public boolean isLocked() {
		return climberLocked;
    }
	
	public boolean getClimberLock() {
		return climberLocked;
    }
    
    public boolean isAtTop() {
        return (maxHeightLimit.get() == Constants.LIMIT_PRESSED);
    }

    public boolean isAtBottom() {
        return (minHeightLimit.get() == Constants.LIMIT_PRESSED);
    }

    public void stop() {
        climbMotor.set(0);
    }

    public double getPosition() {
        return pot.get() * Constants.PotToInches;
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
                if (maxHeightLimit.get() == Constants.LIMIT_NOTPRESSED && servoLocked == false) {
                    climbMotor.set(speed);
                } else {
                    climbMotor.set(0);
                }
            } else {
                if (minHeightLimit.get() == Constants.LIMIT_NOTPRESSED) {
                    climbMotor.set(speed);
                } else {
                    climbMotor.set(0);
                }

            }
        }
    }
}
