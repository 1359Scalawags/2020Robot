package frc.robot.commands.load;

//import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
//import frc.robot.Constants;
import frc.robot.Robot;
//import frc.robot.RobotMap;
import frc.robot.Constants.Load;

/*
Testing code push
*/

public class ManualRotateChamber extends CommandBase{

    public ManualRotateChamber() {
        addRequirements(Robot.loadingSystem);
    }

    @Override
    public void initialize() {
        Robot.loadingSystem.startChamberRotate(Load.chamberRotateSpeed);
    }

    @Override
    public boolean isFinished() {
        return true;
    }

}