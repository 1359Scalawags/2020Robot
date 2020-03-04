package frc.robot.commands.vision;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Robot;
import io.github.pseudoresonance.pixy2api.Pixy2CCC.Block;

public class PixyCommand extends CommandBase{
    public PixyCommand() {
        addRequirements(Robot.pixy);
        addRequirements(Robot.driveSystem);
    }

    // Called just before this Command runs the first time
    @Override
    public void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    @Override
    public void execute() {
        DriveTwordsBall();
    }

    // Make this return true when this Command no longer needs to run execute()
    @Override
    public boolean isFinished() {
        return false;
    }

    void DriveTwordsBall(){
        // Block block = Robot.pixy.getClosest();
        // double angle = block.getAngle();
        
        // Robot.driveSystem.arcadeDrive(Vision.SpeedTwordsBall, Drive.maxRightTurnRate, angle);
    }

    // Called once after isFinished returns true
    @Override
    public void end(boolean interrupted) {
        
    }
}