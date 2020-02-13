package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.Robot;
import io.github.pseudoresonance.pixy2api.Pixy2CCC.Block;

public class PixyCommand extends CommandBase{
    public PixyCommand() {
        addRequirements(Robot.pixy);
    }

    // Called just before this Command runs the first time
    @Override
    public void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    @Override
    public void execute() {
        // Robot.pixy.GotoClosest();
        TurnToClosest();
    }

    // Make this return true when this Command no longer needs to run execute()
    @Override
    public boolean isFinished() {
        return false;
    }

    void TurnToClosest(){
        Block block = Robot.pixy.getClosest();
        double angle = block.getAngle();
        
        Robot.driveSystem.arcadeDrive(0.0, Constants.maxRightTurnRate, angle);
    }

    // Called once after isFinished returns true
    @Override
    public void end(boolean interrupted) {
        
    }
}