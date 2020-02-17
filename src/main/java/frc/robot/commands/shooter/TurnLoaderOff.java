package frc.robot.commands.shooter;

//import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
//import frc.robot.Constants;
import frc.robot.Robot;
//import frc.robot.RobotMap;

public class TurnLoaderOff extends CommandBase{

    public TurnLoaderOff() {
        addRequirements(Robot.loadingSystem);
    }

    @Override
    public void initialize() {
        Robot.loadingSystem.setLoadInMotors(0.0);
        Robot.loadingSystem.setLoadUpMotors(0.0);
        Robot.loadingSystem.setChamberLoadMotor(0.0);
    }

    // Make this return true when this Command no longer needs to run execute()
    @Override
    public boolean isFinished() {
        return true;
    }

}