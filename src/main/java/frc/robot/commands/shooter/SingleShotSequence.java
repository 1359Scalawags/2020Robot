package frc.robot.commands.shooter;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.Constants;
import frc.robot.commands.load.FeedBallToShooter;

public class SingleShotSequence extends SequentialCommandGroup {
    public SingleShotSequence() {
        addCommands(
            new StartShooter(Constants.Shooter.shooterSpeedScale, Constants.Shooter.shooterSpeedScale),
            new FeedBallToShooter()
            // new StopShooter()
        );
    }
    
    // @Override
    // public void initialize() {

    // }

    // @Override
    // public boolean isFinished() {
    //     return true;
    // }

    
}