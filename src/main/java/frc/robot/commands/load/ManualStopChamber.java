package frc.robot.commands.load;

//import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
//import frc.robot.Constants;
import frc.robot.Robot;
//import frc.robot.RobotMap;

/*
Testing code push
*/

public class ManualStopChamber extends CommandBase{

    public ManualStopChamber() {
        addRequirements(Robot.loadingSystem);
    }

    @Override
    public void initialize() {
        Robot.loadingSystem.stopChamberRotate();
    }

    @Override
    public boolean isFinished() {
        return true;
    }

}