package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.Robot;

public class IndexChamberRotator extends CommandBase{
    public IndexChamberRotator(){
        addRequirements(Robot.loadingSystem);


        Robot.loadingSystem.rotateChamber(Constants.maxChamberSpeed);
    }

}