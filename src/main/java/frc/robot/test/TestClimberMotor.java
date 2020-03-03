package frc.robot.test;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Robot;

/**
 *
 */
public class TestClimberMotor extends CommandBase {

    /**
     * 
     * @param up Move in the up direction.
     */

    public TestClimberMotor() {

    }

    // Called just before this Command runs the first time
    @Override
    public void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    @Override
    public void execute() {
        double speed = Robot.oi.getClimbSpeed();

//        Robot.climbSystem.moveTo(speed); //change speed to position when you know how to make it stop arguing when changed to position.

    }

    // Make this return true when this Command no longer needs to run execute()
    @Override
    public boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    @Override
    public void end(boolean interrupted) {
        Robot.climbSystem.stop();
    }


}
