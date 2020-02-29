package frc.robot.commands.shooter;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.commands.load.FeedBallToShooter;

public class SingleShotSequence extends SequentialCommandGroup {

    public SingleShotSequence() {
        addCommands(
            new StartShooter(1,1),
            // new FeedBallToShooter(0.5),
            new FeedBallToShooter(),
            new StopShooter()
        );
    }
}