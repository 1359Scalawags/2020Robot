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
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj2.command.button.*;
//import frc.robot.subsystems.*;


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
    public XboxController driverContoller;
    public JoystickButton shootButton;
    public JoystickButton lineupButton;
    public JoystickButton intakeBallButton;
    public JoystickButton controlPanelSetColorButton;
    public JoystickButton controlPanelSpinButton;
    public JoystickButton climbUpButton;
    public JoystickButton climbDownButton;
    public XboxController assistController;
    public JoystickButton autoDriveForwardButton;
    public JoystickButton autoDriveTurnRight90Button;

    Button autoDriveForward = new JoystickButton(driverContoller, RobotMap.driverxboxX);
    Button autoDriveTurn = new JoystickButton(driverContoller, RobotMap.driverxboxRT);

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS

    public OI() {
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTORS

        assistController = new XboxController(RobotMap.AssistController);

        climbDownButton = new JoystickButton(assistController, 7);
        climbDownButton.whileHeld(new ManualClimb(false));
        climbUpButton = new JoystickButton(assistController, 6);
        climbUpButton.whileHeld(new ManualClimb(true));
        controlPanelSpinButton = new JoystickButton(assistController, 5);
        controlPanelSpinButton.whenPressed(new ControlPanelSpin());
        controlPanelSetColorButton = new JoystickButton(assistController, 4);
        controlPanelSetColorButton.whenPressed(new ControlPanelSetColor());
        intakeBallButton = new JoystickButton(assistController, 3);
        intakeBallButton.whenPressed(new IntakeRollerSpin());
        lineupButton = new JoystickButton(assistController, 2);
        lineupButton.whenPressed(new LineupToShoot());
        shootButton = new JoystickButton(assistController, 1);
        //shootButton.whileHeld(new ShootBall());
        shootButton.whileHeld(new TestShooterSpeeds());
        driverContoller = new XboxController(0);
        autoDriveForwardButton = new JoystickButton(driverContoller, 2);
        autoDriveForwardButton.whileHeld(new AutoDriveForward());
        autoDriveTurnRight90Button = new JoystickButton(driverContoller, 3);
        autoDriveTurnRight90Button.whenPressed(new AutoDriveTurn(90));

        speedoButton = new JoystickButton(driverContoller, 1);
        speedoButton.whileHeld(new ManualDrive());


        // SmartDashboard Buttons
        SmartDashboard.putData("Autonomous Command", new AutonomousCommand());
        SmartDashboard.putData("ManualDrive", new ManualDrive());
        SmartDashboard.putData("AutoDriveForward", new AutoDriveForward());
        SmartDashboard.putData("AutoDriveTurnLeft", new AutoDriveTurn(90));
        SmartDashboard.putData("AutoDriveTurnRight", new AutoDriveTurn(-90));

        SmartDashboard.putData("ManualClimbDown", new ManualClimb(false));
        SmartDashboard.putData("ManualClimbUp", new ManualClimb(true));
        SmartDashboard.putData("ControlPanelSetColor", new ControlPanelSetColor());
        SmartDashboard.putData("ControlPanelSpin", new ControlPanelSpin());
        SmartDashboard.putData("IntakeRollerSpin", new IntakeRollerSpin());
        SmartDashboard.putData("IntakeTrackUp", new IntakeTrackUp());
        SmartDashboard.putData("LineupToShoot", new LineupToShoot());
        //SmartDashboard.putData("ShootBall", new ShootBall());
        SmartDashboard.putData("TestShooterSpeeds", new TestShooterSpeeds());
        

        // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTORS
    }

    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=FUNCTIONS
    public XboxController getDriverJoystick() {
        return driverContoller;
    }

    public boolean getAutoDriveForwardButton() {
		return autoDriveForwardButton.get();
    }

    public boolean getautoDriveTurnRight90Button() {
		return autoDriveTurnRight90Button.get();
	}

    public XboxController getAssistJoystick() {
        return assistController;
    }

    public double getMainTriggers() {
		return Math.max(driverContoller.getTriggerAxis(Hand.kLeft), driverContoller.getTriggerAxis(Hand.kRight));
	}

    public double getRStickY() {
		if (Math.abs(driverContoller.getY(Hand.kRight)) > Constants.controllerDeadZone) {
			return -(driverContoller.getY(Hand.kRight) * (.3 * getMainTriggers() + .7));
		} else {
			return 0;
		}
	}

    public double getLStickY() {
		if (Math.abs(driverContoller.getY(Hand.kLeft)) > Constants.controllerDeadZone) {
			return -(driverContoller.getY(Hand.kLeft) * (.3 * getMainTriggers() + .7));
		} else {
			return 0;
		}
  }
    
    public double getClimbSpeed() {
        return assistController.getY(Hand.kLeft);
    }

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=FUNCTIONS
}

