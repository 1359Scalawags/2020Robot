// RobotBuilder Version: 2.0
//
// This file was generated by RobotBuilder. It contains sections of
// code that are automatically generated and assigned by robotbuilder.
// These sections will be updated in the future when you export to
// Java from RobotBuilder. Do not put any code or make any change in
// the blocks indicating autogenerated code or it will be lost on an
// update. Deleting the comments indicating the section will prevent
// it from being updated in the future.


package frc.robot.commands;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
//import frc.robot.Robot;

/**
 *
 */
public class IntakeRollerSpin extends CommandBase {

    private boolean intakeIn;
    private boolean intakeOut;
    private double intakeSpeed;

    public IntakeRollerSpin() {
        intakeIn = false;
        intakeOut = false;
        intakeSpeed = 0.1;
    }

    // Called just before this Command runs the first time
    @Override
    public void initialize() {
        SmartDashboard.putBoolean("Is_Intake_In", intakeIn);
        SmartDashboard.putBoolean("Is_Intake_Out", intakeOut);
        SmartDashboard.putNumber("Intake_Speed", intakeSpeed);
    }

    // Called repeatedly when this Command is scheduled to run
    @Override
    public void execute() {
        SmartDashboard.getBoolean("Is_Intake_In", intakeIn);
        SmartDashboard.getBoolean("Is_Intake_Out", intakeOut);
        SmartDashboard.getNumber("Intake_Speed", intakeSpeed);
    }

    // Make this return true when this Command no longer needs to run execute()
    @Override
    public boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    @Override
    public void end(boolean interrupted) {
    }


}
