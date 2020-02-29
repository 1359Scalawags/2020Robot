package frc.robot.commands.climb;
import edu.wpi.first.wpilibj2.command.CommandBase;
//import frc.robot.Robot;
import frc.robot.Robot;

/**
 *
 */
public class LockRatchet extends CommandBase {

    public LockRatchet() {
    }

    @Override
    public void initialize() {
    }

    @Override
    public void execute() {
        Robot.climbSystem.lockRatchet();
    }

    @Override
    public boolean isFinished() {
        return Robot.climbSystem.isRatchetLocked();
    }

    @Override
    public void end(boolean interrupted) {
    }
}