package frc.robot.commands.load;

//import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
//import frc.robot.Constants;
import frc.robot.Robot;
//import frc.robot.RobotMap;
import frc.robot.Constants.Load;

public class TurnLoaderToIntakeBalls extends CommandBase{

    public TurnLoaderToIntakeBalls() {
        addRequirements(Robot.loadingSystem);
    }

    @Override
    public void initialize() {
        // Robot.loadingSystem.setLoadInMotors(0.7);
        // Robot.loadingSystem.setLoadUpMotors(0.8);
        // Robot.loadingSystem.setChamberLoadMotor(0.9);

        
        Robot.loadingSystem.setLoadInMotors(Load.laodIn);
        //Robot.loadingSystem.setLoadUpMotors(Load.laodUp);
        //Robot.loadingSystem.setChamberLoadMotor(Load.chamberLoad);
        
    }

    // Make this return true when this Command no longer needs to run execute()
    @Override
    public boolean isFinished() {
        return true;
    }

}