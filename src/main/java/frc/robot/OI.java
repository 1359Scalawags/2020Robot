// RobotBuilder Version: 2.0
//
// This file was generated by RobotBuilder. It contains sections of
// code that are automatically generated and assigned by robotbuilder.
// These sections will be updated in the future when you export to
// Java from RobotBuilder. Do not put any code or make any change in
// the blocks indicating autogenerated code or it will be lost on an
// update. Deleting the comments indicating the section will prevent
// it from being updated in the future.


package frc.robot;

import frc.robot.commands.*;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.button.*;
import frc.robot.subsystems.*;


/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
    //// CREATING BUTTONS
    // One type of button is a joystick button which is any button on a joystick.
    // You create one by telling it which joystick it's on and which button
    // number it is.
    // Joystick stick = new Joystick(port);
    // Button button = new JoystickButton(stick, buttonNumber);

    // There are a few additional built in buttons you can use. Additionally,
    // by subclassing Button you can create custom triggers and bind those to
    // commands the same as any other Button.

    //// TRIGGERING COMMANDS WITH BUTTONS
    // Once you have a button, it's trivial to bind it to a button in one of
    // three ways:

    // Start the command when the button is pressed and let it run the command
    // until it is finished as determined by it's isFinished method.
    // button.whenPressed(new ExampleCommand());

    // Run the command while the button is being held down and interrupt it once
    // the button is released.
    // button.whileHeld(new ExampleCommand());

    // Start the command when the button is released  and let it run the command
    // until it is finished as determined by it's isFinished method.
    // button.whenReleased(new ExampleCommand());


    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS
public JoystickButton speedoButton;
public Joystick driverJoystick;
public JoystickButton shootButton;
public JoystickButton lineupButton;
public JoystickButton intakeBallButton;
public JoystickButton controlPanelSetColorButton;
public JoystickButton controlPanelSpinButton;
public JoystickButton climbUpButton;
public JoystickButton climbDownButton;
public Joystick assistJoystick;

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS

    public OI() {
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTORS

assistJoystick = new Joystick(1);

climbDownButton = new JoystickButton(assistJoystick, 7);
climbDownButton.whileHeld(new ManualClimbDown());
climbUpButton = new JoystickButton(assistJoystick, 6);
climbUpButton.whileHeld(new ManualClimbUp());
controlPanelSpinButton = new JoystickButton(assistJoystick, 5);
controlPanelSpinButton.whenPressed(new ControlPanelSpin());
controlPanelSetColorButton = new JoystickButton(assistJoystick, 4);
controlPanelSetColorButton.whenPressed(new ControlPanelSetColor());
intakeBallButton = new JoystickButton(assistJoystick, 3);
intakeBallButton.whenPressed(new IntakeRollerSpin());
lineupButton = new JoystickButton(assistJoystick, 2);
lineupButton.whenPressed(new LineupToShoot());
shootButton = new JoystickButton(assistJoystick, 1);
shootButton.whileHeld(new ShootBall());
driverJoystick = new Joystick(0);

speedoButton = new JoystickButton(driverJoystick, 1);
speedoButton.whileHeld(new ManualDrive());


        // SmartDashboard Buttons
        SmartDashboard.putData("Autonomous Command", new AutonomousCommand());
        SmartDashboard.putData("ManualDrive", new ManualDrive());
        SmartDashboard.putData("AutoDriveForward", new AutoDriveForward());
        SmartDashboard.putData("AutoDriveTurn", new AutoDriveTurn());
        SmartDashboard.putData("ManualClimbDown", new ManualClimbDown());
        SmartDashboard.putData("ManualClimbUp", new ManualClimbUp());
        SmartDashboard.putData("ControlPanelSetColor", new ControlPanelSetColor());
        SmartDashboard.putData("ControlPanelSpin", new ControlPanelSpin());
        SmartDashboard.putData("IntakeRollerSpin", new IntakeRollerSpin());
        SmartDashboard.putData("IntakeTrackUp", new IntakeTrackUp());
        SmartDashboard.putData("LineupToShoot", new LineupToShoot());
        SmartDashboard.putData("ShootBall", new ShootBall());

        // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTORS
    }

    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=FUNCTIONS
public Joystick getDriverJoystick() {
        return driverJoystick;
    }

public Joystick getAssistJoystick() {
        return assistJoystick;
    }


    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=FUNCTIONS
}

