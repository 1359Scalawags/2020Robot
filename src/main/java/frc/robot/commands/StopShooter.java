package frc.robot.commands;
// import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Robot;


public class StopShooter extends CommandBase {

    public StopShooter() {
        addRequirements(Robot.shooterSystem);
        Robot.shooterSystem.setShooterSpeed(0,0);
    }

}
