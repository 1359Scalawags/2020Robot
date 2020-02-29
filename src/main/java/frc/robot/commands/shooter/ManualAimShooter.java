package frc.robot.commands.shooter;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Robot;

public class ManualAimShooter extends CommandBase{

    public ManualAimShooter() {
        addRequirements(Robot.shooterSystem);
    }

}
