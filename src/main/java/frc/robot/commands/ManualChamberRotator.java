package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Robot;

public class ManualChamberRotator extends CommandBase{
    public ManualChamberRotator(){
        addRequirements(Robot.shooterSystem);

        Robot.loadingSystem.rotateChamber(Robot.oi.getRotatorSpeed());
    }

}