// RobotBuilder Version: 2.0
//
// This file was generated by RobotBuilder. It contains sections of
// code that are automatically generated and assigned by robotbuilder.
// These sections will be updated in the future when you export to
// Java from RobotBuilder. Do not put any code or make any change in
// the blocks indicating autogenerated code or it will be lost on an
// update. Deleting the comments indicating the section will prevent
// it from being updated in the future.


package frc.robot.commands.climb;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Robot;
import frc.robot.subsystems.ClimbSystem;

/**
 *
 */
public class ManualClimb extends CommandBase {

    /**
     * 
     * @param up Move in the up direction.
     */

    public ManualClimb() {

    }

    // Called just before this Command runs the first time
    @Override
    public void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    @Override
    public void execute() {
        double currentPosition = Robot.climbSystem.getPosition();
        double speed = Robot.oi.getClimbSpeed();
        double newPosition = currentPosition + speed;
        Robot.climbSystem.MoveTo(newPosition);
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
