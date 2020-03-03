package frc.robot.commands.climb;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Robot;
import frc.robot.Constants.Climb;

/**
 *
 */
public class AutoClimberExtend extends CommandBase {
   
    public AutoClimberExtend() {

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
        Robot.climbSystem.moveTo(Climb.MAX_CLIMB_POSITION);
    }

    // Make this return true when this Command no longer needs to run execute()
    @Override
    public boolean isFinished() {
        return Robot.climbSystem.isAtTop();
    }

    // Called once after isFinished returns true
    @Override
    public void end(boolean interrupted) {
        Robot.climbSystem.stop();
    }


}
