package frc.robot.commands.shooter;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants.Load;
import frc.robot.Robot;
import edu.wpi.first.wpilibj.Timer;

public class StopRotateChamber extends CommandBase{

    public StopRotateChamber(){
        addRequirements(Robot.loadingSystem);
    }

    @Override
    public void initialize() {
    }

    @Override
    public void execute() {
        Robot.loadingSystem.rotateChamberToPosition(1);
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