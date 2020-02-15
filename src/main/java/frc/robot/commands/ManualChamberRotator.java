package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Robot;

public class ManualChamberRotator extends CommandBase{
    public ManualChamberRotator(){
        addRequirements(Robot.loadingSystem);

    }

    @Override
    public void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    @Override
    public void execute() {
        Robot.loadingSystem.rotateChamber(Robot.oi.getRotatorSpeed());
    }

}