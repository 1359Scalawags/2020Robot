package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Robot;
import frc.robot.Constants;

class OrientShooter extends CommandBase{

    public OrientShooter() {
        addRequirements(Robot.kNetwork);
    }

    @Override
    public void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    @Override
    public void execute() {
        double angle = Robot.kNetwork.getAngle();
        double distance = Robot.kNetwork.getDist();
        double motorState = distance/ Math.abs(distance);

        Robot.driveSystem.arcadeDrive(Constants.driveStraightSpeed, Constants.maxTurnRate, angle);
        Robot.driveSystem.arcadeDrive(Constants.driveStraightSpeed, Constants.maxTurnRate, Robot.driveSystem.getAngle());
        
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