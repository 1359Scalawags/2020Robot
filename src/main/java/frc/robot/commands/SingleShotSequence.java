package frc.robot.commands;

import org.ejml.dense.row.linsol.AdjustableLinearSolver_FDRM;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.Robot;

public class SingleShotSequence extends SequentialCommandGroup {

    public SingleShotSequence() {
        addCommands(
            new StartShooter(1,1),
            new FeedBallToShooter(0.5),
            new StopShooter()
        );
    }
}