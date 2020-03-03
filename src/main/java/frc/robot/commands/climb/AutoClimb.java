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

/**
 *
 */
public class AutoClimb extends CommandBase {
    double speed;
    public AutoClimb(double speed_) {
        speed = speed_;
    }

    // Called just before this Command runs the first time Shlarblenarfalorf
    @Override
    public void initialize() {
        //Climber should already be unlocked
        //Robot.climbSystem.unlockClimber();
    }

    // Called repeatedly when this Command is scheduled to run
    @Override
    public void execute() {
        Robot.climbSystem.MoveTo(speed);
    }

    // Make this return true when this Command no longer needs to run execute()
    @Override
    public boolean isFinished() {
        return (speed>0 && Robot.climbSystem.isAtTop()) || (speed <0 && Robot.climbSystem.isAtBottom());
    }

    // Called once after isFinished returns true
    @Override
    public void end(boolean interrupted) {
        Robot.climbSystem.stop();
    }


}
