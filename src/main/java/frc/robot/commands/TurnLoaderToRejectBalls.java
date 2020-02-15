package frc.robot.commands;

//import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
//import frc.robot.Constants;
import frc.robot.Robot;
//import frc.robot.RobotMap;

public class TurnLoaderToRejectBalls extends CommandBase{

    public TurnLoaderToRejectBalls() {

    }

    @Override
    public void initialize() {
        Robot.loadingSystem.setLoadInMotors(-0.8);
        Robot.loadingSystem.setLoadUpMotors(0.0);
        Robot.loadingSystem.setLoadInMotors(0.0);
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
        Robot.loadingSystem.setLoadInMotors(0.0);
        Robot.loadingSystem.setLoadUpMotors(0.0);
        Robot.loadingSystem.setChamberLoadMotor(0.0);
    }
}