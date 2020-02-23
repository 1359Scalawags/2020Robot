// RobotBuilder Version: 2.0
//
// This file was generated by RobotBuilder. It contains sections of
// code that are automatically generated and assigned by robotbuilder.
// These sections will be updated in the future when you export to
// Java from RobotBuilder. Do not put any code or make any change in
// the blocks indicating autogenerated code or it will be lost on an
// update. Deleting the comments indicating the section will prevent
// it from being updated in the future.


package frc.robot.commands.drive;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Robot;
//import frc.robot.Robot;
//import frc.robot.subsystems.DriveSystem;

/**
 *
 */
public class ManualDrive extends CommandBase {

    public ManualDrive() {
          addRequirements(Robot.driveSystem);
    }

    // Called just before this Command runs the first time
    @Override
    public void initialize() {
        // Robot.driveSystem.ResetGyro();
    }

    // Called repeatedly when this Command is scheduled to run
    @Override
    public void execute() {

        // Robot.oi.getDriverJoystick();
        Robot.driveSystem.ResetGyro();
        
        Robot.driveSystem.tankDrive(-Robot.oi.DriverLStickY(), -Robot.oi.DriverRStickY());	
        
        //SmartDashboard.putNumber("Elevator Height",Robot.climbSystem.getclimbSystemHeight());
		// SmartDashboard.putNumber("Gyro", Robot.canDriveSystem.getAngle());
		// SmartDashboard.putNumber("Encoder Distance", Robot.canDriveSystem.getDistanceLeft());
        // SmartDashboard.putNumber("Encoder Distance", Robot.canDriveSystem.getDistanceRight());        
        

    }

    // Make this return true when this Command no longer needs to run execute()
    @Override
    public boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    @Override
    public void end(boolean interrupted) {
    }


}
