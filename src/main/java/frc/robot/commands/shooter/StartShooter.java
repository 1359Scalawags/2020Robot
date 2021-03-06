package frc.robot.commands.shooter;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Robot;


public class StartShooter extends CommandBase {

    private double targetSpeedTop;
    private double targetSpeedBottom;

    public StartShooter(double top, double bottom) {
        addRequirements(Robot.shooterSystem);
        targetSpeedTop = top;
        targetSpeedBottom = bottom;
    }

    @Override
    public void initialize() {
        // TODO: need to turn on targeting LED's
        Robot.shooterSystem.setShooterSpeed(targetSpeedTop, targetSpeedBottom);
    }

    @Override
    public boolean isFinished() {
        if(Robot.shooterSystem.getShooterSpeedTop() > 0.98 * targetSpeedTop && Robot.shooterSystem.getShooterSpeedBottom() > 0.98 * targetSpeedBottom ) {
            return true;
        }
        return false;
        //return (Robot.shooterSystem.getShooterSpeedTop() != 0 && Robot.shooterSystem.getShooterSpeedBottom() != 0);
    }

}
