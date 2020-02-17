// RobotBuilder Version: 2.0
//
// This file was generated by RobotBuilder. It contains sections of
// code that are automatically generated and assigned by robotbuilder.
// These sections will be updated in the future when you export to
// Java from RobotBuilder. Do not put any code or make any change in
// the blocks indicating autogenerated code or it will be lost on an
// update. Deleting the comments indicating the section will prevent
// it from being updated in the future.


package frc.robot.commands.shooter;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants.Load;
import frc.robot.Robot;

/**
 *
 */
public class FeedBallToShooter extends CommandBase {
    

    private double duration;
    private double start;
    private Timer timer;

    public FeedBallToShooter(double duration) {
        addRequirements(Robot.shooterSystem);
        timer = new Timer();
        this.duration = duration;
    }


    // Called just before this Command runs the first time
    @Override
    public void initialize() {
        start = timer.get();
        Robot.shooterSystem.setShotLoaderSpeed(Load.LoadShotMotor);       
    }

    // Called repeatedly when this Command is scheduled to run
    @Override
    public void execute() {

    }

    // Make this return true when this Command no longer needs to run execute()
    @Override
    public boolean isFinished() {
        if(timer.get() - start >= duration) {
            return true;
        }
        return false;
    }

    // Called once after isFinished returns true
    @Override
    public void end(boolean interrupted) {
        Robot.shooterSystem.setShotLoaderSpeed(0);
    }


}
