package frc.robot;

import frc.robot.commands.*;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj2.command.button.*;
import frc.robot.Constants;
import frc.robot.helper.DPadButton;;
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


    public XboxController assistController;
    public XboxController driverContoller;
    public JoystickButton speedoButton;
    public JoystickButton shootButton;
    public JoystickButton lineupButton;
    public DPadButton intakeBallButton;
    public DPadButton uptakeBallButton;
    public JoystickButton controlPanelSetColorButton;
    public JoystickButton controlPanelSpinButton;
    public JoystickButton climbUpButton;
    public JoystickButton climbDownButton;
    public JoystickButton autoDriveForwardButton;
    public JoystickButton autoDriveTurnRight90Button;

    Button autoDriveForward;
    Button autoDriveTurn;


    public OI() {

        assistController = new XboxController(RobotMap.AssistController);

        //DPad Buttons *DPAD IS POV
        intakeBallButton = new DPadButton(assistController, DPadButton.Direction.DOWN);
        intakeBallButton.whenPressed(new IntakeRollerSpin());
        uptakeBallButton = new DPadButton(assistController, DPadButton.Direction.UP);
        uptakeBallButton.whenPressed(new IntakeRollerSpin());

        //Joystick Buttons *DPAD IS NOT A BUTTON
        climbDownButton = new JoystickButton(assistController, 7);
        climbDownButton.whileHeld(new ManualClimb());

        climbUpButton = new JoystickButton(assistController, 6);
        climbUpButton.whileHeld(new ManualClimb());

        controlPanelSpinButton = new JoystickButton(assistController, Constants.Xbtn);
        controlPanelSpinButton.whenPressed(new ControlPanelSpin());

        controlPanelSetColorButton = new JoystickButton(assistController, Constants.Ybtn);
        controlPanelSetColorButton.whenPressed(new ControlPanelSetColor());

        lineupButton = new JoystickButton(assistController, Constants.LB);
        lineupButton.whenPressed(new aimShooter());

        shootButton = new JoystickButton(assistController, Constants.RB);
        shootButton.whileHeld(new ShootBall());

        //DRIVER

        driverContoller = new XboxController(0);

        autoDriveForwardButton = new JoystickButton(driverContoller, 2);
        autoDriveForwardButton.whileHeld(new AutoDriveForward());

        autoDriveTurnRight90Button = new JoystickButton(driverContoller, 3);
        autoDriveTurnRight90Button.whenPressed(new AutoDriveTurn(90));

        speedoButton = new JoystickButton(driverContoller, 1);
        speedoButton.whileHeld(new ManualDrive());

        autoDriveForward = new JoystickButton(driverContoller, RobotMap.driverxboxX);
        autoDriveTurn = new JoystickButton(driverContoller, RobotMap.driverxboxRT);

        // SmartDashboard Buttons
        SmartDashboard.putData("Autonomous Command", new AutonomousCommand());
        SmartDashboard.putData("ManualDrive", new ManualDrive());
        SmartDashboard.putData("AutoDriveForward", new AutoDriveForward());
        SmartDashboard.putData("AutoDriveTurnLeft", new AutoDriveTurn(90));
        SmartDashboard.putData("AutoDriveTurnRight", new AutoDriveTurn(-90));
        SmartDashboard.putData("ManualClimbDown", new ManualClimb());
        SmartDashboard.putData("ManualClimbUp", new ManualClimb());
        SmartDashboard.putData("ControlPanelSetColor", new ControlPanelSetColor());
        SmartDashboard.putData("ControlPanelSpin", new ControlPanelSpin());
        SmartDashboard.putData("IntakeRollerSpin", new IntakeRollerSpin());
        SmartDashboard.putData("IntakeTrackUp", new IntakeTrackUp());
        SmartDashboard.putData("LineupToShoot", new aimShooter());
        SmartDashboard.putData("ShootBall", new ShootBall());
        //SmartDashboard.putData("TestShooterSpeeds", new TestShooterSpeeds());
        //Button autoDriveForward = new JoystickButton(driverContoller, RobotMap.driverxboxX);
        

    }

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

    /***
     * ----------Dpad POV----------
     * 0 is UP
     * 45 is UP-RIGHT
     * 90 is RIGHT
     * 135 is DOWN-RIGHT
     * 180 is DOWN
     * 225 is DOWN-LEFT
     * 270 is LEFT
     * 315 is UP-LEFT
     * Returns to 0 going UP again
     * @return
     */

    public int getAssistDPad() {
      return assistController.getPOV();
    }

    public boolean getAssistPovUp() {
      int pov = assistController.getPOV();
      return ( pov > 345 || pov < 15 );
    }

    public boolean getAssistPovRight() {
      int pov = assistController.getPOV();
      return ( pov > 75 || pov < 105 );
    }

    public boolean getAssistPovDown() {
      int pov = assistController.getPOV();
      return ( pov > 165 || pov < 195 );
    }

    public boolean getAssistPovLeft() {
      int pov = assistController.getPOV();
      return ( pov > 255 || pov < 285 );
    }
    
  public double DriverLStickY() {
    if (Math.abs(Robot.oi.driverContoller.getY(Hand.kLeft)) > Constants.controllerDeadZone) {
      return -(Robot.oi.driverContoller.getY(Hand.kLeft) * (.3 * Robot.oi.getMainTriggers() + .7));
    } else {
      return 0;
    }
  }

  public double DriverRStickY() {
    if (Math.abs(Robot.oi.driverContoller.getY(Hand.kRight)) > Constants.controllerDeadZone) {
      return -(Robot.oi.driverContoller.getY(Hand.kRight) * (.3 * Robot.oi.getMainTriggers() + .7));
    } else {
      return 0;
    }
  }

  public double AssistRStickY() {
    if (Math.abs(Robot.oi.assistController.getY(Hand.kRight)) > Constants.controllerDeadZone) {
      return -(Robot.oi.assistController.getY(Hand.kRight) * (.3 * Robot.oi.getMainTriggers() + .7));
    } else {
      return 0;
    }
  }

  public double AssistLStickY() {
    if (Math.abs(Robot.oi.assistController.getY(Hand.kLeft)) > Constants.controllerDeadZone) {
      return -(Robot.oi.assistController.getY(Hand.kLeft) * (.3 * Robot.oi.getMainTriggers() + .7));
    } else {
      return 0;
    }
  }

    public double getMainTriggers() {
		return Math.max(driverContoller.getTriggerAxis(Hand.kLeft), driverContoller.getTriggerAxis(Hand.kRight));
	}

    
    
    public double getClimbSpeed() {
        return assistController.getY(Hand.kLeft);
    }

	public double getRotatorSpeed() {
      return assistController.getX(Hand.kRight);
	}

}

