package frc.robot.commands.shooter;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Robot;

public class StartRotateChamber extends CommandBase{

    private double current;

    public StartRotateChamber(){
        addRequirements(Robot.loadingSystem);
    }

    @Override
    public void initialize() {
        current = Robot.loadingSystem.getChamPosition();
    }

    @Override
    public void execute() {
        // SmartDashboard.putNumber("key", current+1/5);
        Robot.loadingSystem.rotateChamberToPosition(current + 1/5);
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