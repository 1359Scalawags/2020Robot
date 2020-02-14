package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Robot;
import frc.robot.Constants;

class OrientShooter extends CommandBase{

    public OrientShooter() {
        addRequirements(Robot.kNetwork);
        addRequirements(Robot.ballSystem);
    }

    @Override
    public void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    @Override
    public void execute() {
        RotateShooterPosition(Constants.maxShooterTurnRate, Robot.kNetwork.getAngle());

        double distance = Robot.kNetwork.getDist();
        double error = Constants.expectedDist - distance;//0, 100

        if(error > Constants.maxShooterTurnError)
            error = Constants.maxShooterTurnError;
        else if(error < -Constants.maxShooterTurnError)
            error = -Constants.maxShooterTurnError;

        double motorState  = error/Constants.maxShooterTurnError;

        Robot.driveSystem.arcadeDrive(motorState*Constants.driveStraightSpeed, Constants.maxTurnRate, Robot.driveSystem.getAngle());
    }

    /**
     * 
     * @param speed speed of motors -1. 1
     * @param expectedposition set expected position 0, 1
     */
    public void RotateShooterPosition(double speed, double angle){
        double nangle = angle/360;
        
        Robot.ballSystem.rotateShooter(speed*nangle);
        
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