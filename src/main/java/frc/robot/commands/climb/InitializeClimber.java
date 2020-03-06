package frc.robot.commands.climb;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Robot;
import frc.robot.Constants.Climb;
import frc.robot.subsystems.ClimbSystem;

//TODO: This class has not been implemented yet.
public class InitializeClimber extends CommandBase {
 
    public InitializeClimber() {
        addRequirements(Robot.climbSystem);
    }

    // Called just before this Command runs the first time
    @Override
    public void initialize() {
        
    }

    // Called repeatedly when this Command is scheduled to run
    @Override
    public void execute() {

    }

    // Make this return true when this Command no longer needs to run execute()
    @Override
    public boolean isFinished() {
        return true;
    }

    // Called once after isFinished returns true
    @Override
    public void end(boolean interrupted) {

    }


}