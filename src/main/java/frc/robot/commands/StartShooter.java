package frc.robot.commands;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.Robot;


public class StartShooter extends CommandBase {

    public StartShooter() {
        addRequirements(Robot.shooterSystem);
        Robot.shooterSystem.setShooterSpeed(Constants.MaxShooterSpeed, Constants.MaxShooterSpeed);
    }

}
