package frc.robot.commands;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.Robot;
import frc.robot.RobotMap;

public class TurnLoaderOff extends CommandBase{

    public TurnLoaderOff() {

    }

    @Override
    public void initialize() {
        
    }

    // Called repeatedly when this Command is scheduled to run
    @Override
    public void execute() {
        Robot.loadingSystem.setLoadInMotor(0.8);

       
    }

    // Make this return true when this Command no longer needs to run execute()
    @Override
    public boolean isFinished() {
        return Robot.loadingSystem.isOff();
    }

    // Called once after isFinished returns true
    @Override
    public void end(boolean interrupted) {
        //not much to do here
    }
}