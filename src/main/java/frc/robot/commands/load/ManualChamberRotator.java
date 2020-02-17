package frc.robot.commands.load;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Robot;

public class ManualChamberRotator extends CommandBase{
    public ManualChamberRotator(){
        addRequirements(Robot.loadingSystem);
    }

    @Override
    public void initialize() {
        double speed = Robot.oi.getRotatorSpeed();
        Robot.loadingSystem.rotateChamber(speed);
    }

    // Called repeatedly when this Command is scheduled to run
    @Override
    public void execute() {
    }

    @Override
    public boolean isFinished(){
        return !Robot.loadingSystem.isRotating();
    }

    public void end(boolean interrupted) {
        Robot.loadingSystem.rotateChamber(0);
    }


}