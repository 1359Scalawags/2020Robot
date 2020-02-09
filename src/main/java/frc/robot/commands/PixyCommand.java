package frc.robot.commands;

import java.util.ArrayList;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Robot;
import io.github.pseudoresonance.pixy2api.Pixy2;
import io.github.pseudoresonance.pixy2api.Pixy2CCC;
import io.github.pseudoresonance.pixy2api.Pixy2CCC.Block;

public class PixyCommand extends CommandBase{
    Pixy2 Pixy;
    public PixyCommand() {
        addRequirements(Robot.pixy);
    }

    // Called just before this Command runs the first time
    @Override
    public void initialize() {
        Pixy = Robot.pixy.getPixy();
    }

    // Called repeatedly when this Command is scheduled to run
    @Override
    public void execute() {
        Pixy2CCC detected = Pixy.getCCC();
        ArrayList<Block> blocks = detected.getBlocks();

        for(int i=0; i<blocks.size(); i++){
            blocks.get(i).getX();
            blocks.get(i).getY();
        }
    }

    // Make this return true when this Command no longer needs to run execute()
    @Override
    public boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    @Override
    public void end(boolean interrupted) {
        
    }
}