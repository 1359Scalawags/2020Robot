package frc.robot.commands.shooter;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Robot;

//TODO Make sure this gets finished for allowing moving up/down, left/right,
//TODO and having limits for each

public class ManualAimShooter extends CommandBase{

    public ManualAimShooter() {
        addRequirements(Robot.shooterSystem);
    }

    @Override
    public void initialize(){
    }

    @Override
    public void execute() {
        // Robot.shooterSystem.rotateHorizontally(speed);
        // Robot.shooterSystem.rotateVertically(speed);
        
    }

}
