package frc.robot.commands;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.Robot;


public class StartShooter extends CommandBase {

    public StartShooter() {
        addRequirements(Robot.shooterSystem);
    }

    @Override
    public void initialize() {
        Robot.shooterSystem.setShooterSpeed(Constants.MaxShooterSpeed, Constants.MaxShooterSpeed);
    }

    @Override
    public boolean isFinished() {
        return true;
        //return (Robot.shooterSystem.getShooterSpeedTop() != 0 && Robot.shooterSystem.getShooterSpeedBottom() != 0);
    }

}
