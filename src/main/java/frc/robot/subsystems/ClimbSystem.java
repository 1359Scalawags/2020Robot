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
//import edu.wpi.first.wpilibj.PIDOutput;
//import edu.wpi.first.wpilibj.PIDSource;
// BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=IMPORTS
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Spark;
import frc.robot.Constants;
//import edu.wpi.first.wpilibj.SpeedController;
//END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=IMPORTS

/**
 *
 */
public class ClimbSystem extends SubsystemBase {
    private DigitalInput maxHeightLimit;
    private DigitalInput minHeightLimit;
    private Spark climbMotor;
    private boolean climberLocked;

    public ClimbSystem() {
        maxHeightLimit = new DigitalInput(Constants.MaxHeightLimitID);
        addChild("MaxHeightLimit",maxHeightLimit);
    
        minHeightLimit = new DigitalInput(Constants.MinHeightLimitID);
        addChild("MinHeightLimit",minHeightLimit);
       
        climbMotor = new Spark(Constants.ClimbMotorID);
        addChild("ClimbMotor",climbMotor);
        climbMotor.setInverted(false);
    }

    @Override
    public void periodic() {
        // Put code here to be run every loop
    }

    /**
     * Allows drivers to use, lock, and unlock the Climber.
     */

    public void unlockClimber() {
		climberLocked = false;
	}
	
	public boolean getClimberLock() {
		return climberLocked;
    }
    
    public boolean isAtTop() {
        return (maxHeightLimit.get() == Constants.LIMIT_PRESSED);
    }

    public boolean isAtTBottom() {
        return (minHeightLimit.get() == Constants.LIMIT_PRESSED);
    }
    
    public void move(double speed) {

        if (climberLocked) {
            climbMotor.set(0);
        } else {
            if (speed > 0) {
                if (maxHeightLimit.get() == Constants.LIMIT_NOTPRESSED) {
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
