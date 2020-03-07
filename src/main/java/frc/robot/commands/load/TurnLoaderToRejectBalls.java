package frc.robot.commands.load;

//import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
//import frc.robot.Constants;
import frc.robot.Robot;
//import frc.robot.RobotMap;
import frc.robot.Constants.Load;

public class TurnLoaderToRejectBalls extends CommandBase{

    public TurnLoaderToRejectBalls() {
        addRequirements(Robot.loadingSystem);
    }

    @Override
    public void initialize() {
        Robot.loadingSystem.setLoadInMotors(-Load.loadIn);
    }

    @Override
    public boolean isFinished() {
        return true;
    }

    @Override
    public void end(boolean interrupted) {

    }
}