package frc.robot.commands;
import edu.wpi.first.wpilibj2.command.CommandBase;
//import frc.robot.Robot;
import frc.robot.Robot;

/**
 *
 */
public class UnlockClimber extends CommandBase {

    public UnlockClimber() {

    }

    // Called just before this Command runs the first time
    @Override
    public void initialize() {

    }

    // Called repeatedly when this Command is scheduled to run
    @Override
    public void execute() {
       Robot.climbSystem.unlockClimber();
    }

    @Override
    public boolean isFinished() {
        return !Robot.climbSystem.isClimberLocked();
    }

    @Override
    public void end(boolean interrupted) {
        // nothing to do here
    }
}