package frc.robot.commands.load;

//import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
//import frc.robot.Constants;
import frc.robot.Robot;
//import frc.robot.RobotMap;
import frc.robot.Constants.Load;
import edu.wpi.first.wpilibj.Timer;

public class TurnLoaderToIntakeBalls extends CommandBase{    
    private double start;
    private Timer timer;

    public TurnLoaderToIntakeBalls() {
        addRequirements(Robot.loadingSystem);
        timer = new Timer();
    }

    @Override
    public void initialize() {
        timer.reset();
        // TODO: check if a ball is already in loading slot before starting   
        Robot.loadingSystem.setLoadInMotors(Load.loadIn);
        Robot.loadingSystem.getBallSlots();
    }

    @Override 
    public void execute() {
        // TODO: when ball is sensed in loader, start a timer
       
        if (Robot.loadingSystem.isBallPreloading() && timer.get() == 0){
            timer.start();
            start = timer.get();
        }
    }
    @Override
    public boolean isFinished() {
        // TODO: is finished when timer is done
        // if(timer.get()-start > Load.timerMaxTime) {
        //     return true;
        // }
        // else{
        //     return false;
        // }

        return false;
    }

    @Override
    public void end(boolean interrupted) {
        
    }

}