package frc.robot.commands.load;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Robot;

public class ManualChamberNextIndex extends CommandBase{
    public ManualChamberNextIndex(){
        addRequirements(Robot.loadingSystem);
    }

    @Override
    public void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    @Override
    public void execute() {
    //     double speed = Robot.oi.getRotatorSpeed();
    //     Robot.loadingSystem.rotateChamber(speed);
    }

    @Override
    public boolean isFinished(){
        return false;
    }

    public void end(boolean interrupted) {
        Robot.loadingSystem.rotateChamber(0);
    }


}