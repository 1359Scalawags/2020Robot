// RobotBuilder Version: 2.0
//
// This file was generated by RobotBuilder. It contains sections of
// code that are automatically generated and assigned by robotbuilder.
// These sections will be updated in the future when you export to
// Java from RobotBuilder. Do not put any code or make any change in
// the blocks indicating autogenerated code or it will be lost on an
// update. Deleting the comments indicating the section will prevent
// it from being updated in the future.


package frc.robot.commands.autonomous;
// import edu.wpi.first.wpilibj2.command.CommandBase;
//import frc.robot.Robot;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.commands.drive.AutoDriveTurn;
import frc.robot.Constants.Drive;
//import sun.misc.Signal;
import frc.robot.commands.drive.AutoDriveForward;
import frc.robot.commands.shooter.SingleShotSequence;
import frc.robot.commands.shooter.AutoAimShooter;

/**
 *
 */
public class AutonomousCommand extends SequentialCommandGroup {

    public enum AutoMoveOptions{

        ForwardTurnAndShoot,
        ReversTurnforwardTurnAndShoot,
        TurnForwardAndShoot
    }

    public AutonomousCommand(AutoMoveOptions moveOption) {
        addCommands(new AutoDriveForward(Drive.AutoDriveDistance),
            new AutoDriveTurn(10),
            new AutoDriveTurn(-10));
        // switch(moveOption){
        //     case ForwardTurnAndShoot:
        //         addCommands(
        //             new AutoDriveForward(1),
        //             new AutoDriveTurn(90),
        //             new AutoAimShooter(),
        //             new SingleShotSequence()
        //         );
        //     break;

        //     case ReversTurnforwardTurnAndShoot:
        //         addCommands(
        //             new AutoDriveForward(-1),
        //             new AutoDriveTurn(-90),
        //             new AutoDriveForward(1),
        //             new AutoDriveTurn(-90),
        //             new AutoAimShooter(),
        //             new SingleShotSequence()
        //         );
        //     break;
            
        //     case TurnForwardAndShoot:
        //         addCommands(
        //             new AutoDriveTurn(180),
        //             new AutoDriveForward(1),
        //             new AutoAimShooter(),
        //             new SingleShotSequence()
        //         );
        //     break;
        // }
            
       
    }

}
0