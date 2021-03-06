package frc.robot.commands.shooter;
// import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Robot;


public class StopShooter extends CommandBase {

    public StopShooter() {
        addRequirements(Robot.shooterSystem);
    }

    @Override
    public void initialize() {
       Robot.shooterSystem.setShooterSpeed(0,0);
       Robot.shooterSystem.setShotLoaderSpeed(0);
    }

    @Override
    public boolean isFinished() {
        return (Robot.shooterSystem.getShooterSpeedTop() == 0 && Robot.shooterSystem.getShooterSpeedBottom() == 0);
    }

    @Override
    public void end(boolean interrupted) {
        //TODO: turn off targeting LED's
    }
}
