package frc.robot.commands;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
//import frc.robot.Robot;
import frc.robot.Robot;

/**
 *
 */
public class TestControlPanelSpinMotor extends CommandBase {

    private double spinSpeed;
    private double motorRotations;

    public TestControlPanelSpinMotor() {
        spinSpeed = 0;
        motorRotations = 0;
    }

    // Called just before this Command runs the first time
    @Override
    public void initialize() {
        SmartDashboard.putNumber("CP Spin Speed", spinSpeed);
        SmartDashboard.putNumber("Target CP Motor Rotations", motorRotations);
        // Robot.controlPanelSystem.resetTestRotations();
    }

    // Called repeatedly when this Command is scheduled to run
    @Override
    public void execute() {
        spinSpeed = SmartDashboard.getNumber("CP Spin Speed", 0);
        motorRotations = SmartDashboard.getNumber("Target CP Motor Rotations", 0);
        // Robot.controlPanelSystem.testSpin(spinSpeed);
        //SmartDashboard.putNumber("Actual CP Motor Rotations", Robot.controlPanelSystem.getTestRotations());
    }

    // Make this return true when this Command no longer needs to run execute()
    @Override
    public boolean isFinished() {
        // if(Robot.controlPanelSystem.getTestRotations() >= motorRotations) {
        //     return true;
        // } else {
        //     return false;
        // }
        // test methods don't exist anymore
        return true;
    }

    // Called once after isFinished returns true
    @Override
    public void end(boolean interrupted) {
        // Robot.controlPanelSystem.stopTest();
    }


}
