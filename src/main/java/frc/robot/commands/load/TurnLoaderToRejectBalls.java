package frc.robot.commands.load;

//import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
//import frc.robot.Constants;
import frc.robot.Robot;
//import frc.robot.RobotMap;

public class TurnLoaderToRejectBalls extends CommandBase{

    public TurnLoaderToRejectBalls() {
        addRequirements(Robot.loadingSystem);
    }

    @Override
    public void initialize() {
        Robot.loadingSystem.setLoadInMotors(-1);
    }

    @Override
    public boolean isFinished() {
        return true;
    }

    @Override
    public void end(boolean interrupted) {

    }
}