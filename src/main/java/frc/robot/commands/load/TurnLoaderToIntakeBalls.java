package frc.robot.commands.load;

//import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
//import frc.robot.Constants;
import frc.robot.Robot;
//import frc.robot.RobotMap;

public class TurnLoaderToIntakeBalls extends CommandBase{

    public TurnLoaderToIntakeBalls() {
        addRequirements(Robot.loadingSystem);
    }

    @Override
    public void initialize() {
        // Robot.loadingSystem.setLoadInMotors(0.7);
        // Robot.loadingSystem.setLoadUpMotors(0.8);
        // Robot.loadingSystem.setChamberLoadMotor(0.9);

        
        Robot.loadingSystem.setLoadInMotors(1);
        Robot.loadingSystem.setLoadUpMotors(1);
        Robot.loadingSystem.setChamberLoadMotor(1);
        
    }

    // Make this return true when this Command no longer needs to run execute()
    @Override
    public boolean isFinished() {
        return true;
    }

}