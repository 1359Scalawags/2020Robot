package frc.robot.commands.shooter;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Robot;
import frc.robot.Constants.Drive;
import frc.robot.Constants.Shooter;

public class aimShooter extends CommandBase{

    double angle;

    public aimShooter() {
        addRequirements(Robot.kNetwork, Robot.loadingSystem, Robot.driveSystem);
    }

    @Override
    public void initialize() {
        angle = Robot.driveSystem.getAngle();
    }

    // Called repeatedly when this Command is scheduled to run
    @Override
    public void execute() {
        rotate(Shooter.maxShooterTurnRate, Robot.kNetwork.getAngles());

        double distance = Robot.kNetwork.getDist();
        double error = Shooter.expectedDist - distance;//0, 100

        if(error > Shooter.maxShooterTurnError)
            error = Shooter.maxShooterTurnError;
        else if(error < -Shooter.maxShooterTurnError)
            error = -Shooter.maxShooterTurnError;

        double motorState  = error/Shooter.maxShooterTurnError;

        Robot.driveSystem.arcadeDrive(motorState*Drive.driveStraightSpeed, Drive.maxTurnRate, angle);
    }

    /**
     * @param speed speed of motors -1. 1
     * @param expectedposition set expected position 0, 1
     */
    public void rotate(double speed, double angles[]){

        double xangle = angles[0]/360;
        double yangle = angles[1]/360;
        
        Robot.shooterSystem.rotateShooter(speed*xangle, speed*yangle);
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