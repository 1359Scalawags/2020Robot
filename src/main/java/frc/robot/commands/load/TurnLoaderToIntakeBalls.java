package frc.robot.commands.load;

//import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
//import frc.robot.Constants;
import frc.robot.Robot;
//import frc.robot.RobotMap;
import frc.robot.Constants.Load;

public class TurnLoaderToIntakeBalls extends CommandBase{

    //TODO: will need a timer for when sensor is tripped

    public TurnLoaderToIntakeBalls() {
        addRequirements(Robot.loadingSystem);
    }

    @Override
    public void initialize() {
        // TODO: check if a ball is already in loading slot before starting   
        Robot.loadingSystem.setLoadInMotors(Load.loadIn);
        
    }

    @Override 
    public void execute() {
        // TODO: when ball is sensed in loader, start a timer
        
    }

    @Override
    public boolean isFinished() {
        // TODO: is finished when timer is done
        return true;
    }

    @Override
    public void end(boolean interrupted) {
        
    }

}