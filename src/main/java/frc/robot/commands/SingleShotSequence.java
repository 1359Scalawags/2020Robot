package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;

public class SingleShotSequence extends SequentialCommandGroup {

    public SingleShotSequence() {
        addCommands(
            new StartShooter(1,1),
            new FeedBallToShooter(0.5),
            new StopShooter()
        );
    }
}