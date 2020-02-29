// package frc.robot.commands.load;

// import edu.wpi.first.wpilibj2.command.CommandBase;
// import frc.robot.Robot;

// public class ManualChamberNextIndex extends CommandBase{

//     public ManualChamberNextIndex(){
//         addRequirements(Robot.loadingSystem);
//     }

//     @Override
//     public void initialize() {
//     }

//     @Override
//     public void execute() {
//         //TODO make it so that when the Y button is pressed,
//         //TODO the chamber goes to the next index regardless of loading or shooting


//         //double speed = Robot.oi.getRotatorSpeed();
//         //Robot.loadingSystem.rotateChamber(speed);
//     }

//     @Override
//     public boolean isFinished(){
//         return false;
//     }

//     public void end(boolean interrupted) {
//         Robot.loadingSystem.rotateChamber(0);
//     }


// }