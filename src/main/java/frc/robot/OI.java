package frc.robot;

//import frc.robot.commands.*;
//import frc.robot.commands.drive.*;
import frc.robot.commands.load.*;
import frc.robot.commands.shooter.*;
import frc.robot.commands.climb.*;
import frc.robot.commands.drive.ManualDrive;
//import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj2.command.button.*;
import frc.robot.Constants;
import frc.robot.helper.DPadButton;
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

    private XboxController assistController;
    private XboxController driverController;
    private DPadButton intakeBallsButton;
    private DPadButton rejectBallsButton;
    private JoystickButton autoDriveForwardButton;
    private JoystickButton autoDriveTurnRight;
    private JoystickButton manualIndexLoadChamber;
    private JoystickButton manualIndexShootChamber;
    private JoystickButton unlockClimber;
    private JoystickButton lockClimber;
    private JoystickButton singleShot;
    private JoystickButton driveRightFaster;
    private JoystickButton driveLeftFaster;

    // private JoystickButton speedoButton;
    // private JoystickButton startShooterButton;
    // private JoystickButton stopShooterButton;
    // private JoystickButton lineupButton;
    // private JoystickButton controlPanelSetColorButton;
    // private JoystickButton controlPanelSpinButton;
    // private JoystickButton climbUpButton;
    // private JoystickButton climbDownButton;
    // private JoystickButton manualClimb;
    // private JoystickButton AutoclimbUpButton;
    // private JoystickButton AutoclimbDownButton;
    // private JoystickButton autoDriveTurnLeft;
 
    public OI() {

      assistController = new XboxController(RobotMap.AssistController);
      driverController = new XboxController(RobotMap.DriverController);
 
      rejectBallsButton = new DPadButton(driverController, DPadButton.Direction.UP);//error
      rejectBallsButton.whenPressed(new TurnLoaderToRejectBalls());
 
      intakeBallsButton = new DPadButton(driverController, DPadButton.Direction.DOWN);
      intakeBallsButton.whenPressed(new TurnLoaderToIntakeBalls());
 
      unlockClimber = new JoystickButton(assistController, Constants.BACK);
      unlockClimber.whenPressed(new UnlockClimber());

      lockClimber = new JoystickButton(assistController, Constants.START);
      lockClimber.whenPressed(new LockRatchet());
 
      manualIndexLoadChamber = new JoystickButton(assistController, Constants.Ybtn);
      manualIndexLoadChamber.whenPressed(new IndexLoadChamber());
 
      manualIndexShootChamber = new JoystickButton(assistController, Constants.Xbtn);
      manualIndexShootChamber.whenPressed(new IndexShootChamber());
        
      singleShot = new JoystickButton(assistController, Constants.Abtn);
      singleShot.whenPressed(new SingleShotSequence());

      driveLeftFaster = new JoystickButton(driverController, Constants.LB);
      driveLeftFaster.whenPressed(new ManualDrive());

      driveRightFaster = new JoystickButton(driverController, Constants.RB);
      driveRightFaster.whenPressed(new ManualDrive());
 
      // climbDownButton = new JoystickButton(assistController, 7);
      // climbDownButton.whileHeld(new ManualClimb());
 
      // climbUpButton = new JoystickButton(assistController, 6);
      // climbUpButton.whileHeld(new ManualClimb());
      
      // manualClimb = new JoystickButton(assistController, 6);
      // manualClimb.whileHeld(new ManualClimb());
 
      // AutoclimbUpButton = new JoystickButton(assistController, RobotMap.AutoClimbUp);
      // AutoclimbUpButton.whileHeld(new AutoClimb(Constants.maxClimbSpeed));
 
      // AutoclimbDownButton = new JoystickButton(assistController, RobotMap.AutoClimbDown);
      // AutoclimbDownButton.whileHeld(new AutoClimb(Constants.maxClimbSpeed));
 
      // lineupButton = new JoystickButton(assistController, Constants.LB);
      // lineupButton.whenPressed(new aimShooter());
 
      // startShooterButton = new JoystickButton(assistController, Constants.RB);
      // startShooterButton.whileHeld(new StartShooter(1, 1));
 
      // stopShooterButton = new JoystickButton(assistController, Constants.RB);
      // stopShooterButton.whileHeld(new StopShooter());

      //DRIVER
 
      // autoDriveForwardButton = new JoystickButton(driverContoller, RobotMap.driverxboxX);
      // autoDriveForwardButton.whileHeld(new AutoDriveForward(1));
 
      // autoDriveTurnRight = new JoystickButton(driverContoller, RobotMap.driverxboxRT);
      // autoDriveTurnRight.whenPressed(new AutoDriveTurn(90));
 
      // autoDriveTurnLeft = new JoystickButton(driverContoller, RobotMap.driverxboxRT);
      // autoDriveTurnLeft.whenPressed(new AutoDriveTurn(-90));
        
      // SmartDashboard Buttons
      // SmartDashboard.putData("Aim Shooter", new aimShooter());
      // SmartDashboard.putData("AutoClimb", new AutoClimb(SmartDashboard.getNumber("Climber Speed", 0)));
      // SmartDashboard.putData("ManualClimber", new ManualClimb());
 
      // SmartDashboard.putData("Auto Drive Turn", new AutoDriveTurn(90);
      // SmartDashboard.putData("Autonomous Command", new AutonomousCommand());
 
      // SmartDashboard.putData("ManualDrive", new ManualDrive());
      // SmartDashboard.putData("AutoDriveForward", new AutoDriveForward(1));
      // SmartDashboard.putData("AutoDriveTurnLeft", new AutoDriveTurn(90));
      // SmartDashboard.putData("AutoDriveTurnRight", new AutoDriveTurn(-90));
      // SmartDashboard.putData("ManualClimb", new ManualClimb());
      // SmartDashboard.putData("ControlPanel Set Color", new ControlPanelSetColor());
      // SmartDashboard.putData("ControlPanel Spin", new ControlPanelSpin());
      // SmartDashboard.putData("IntakeBalls", new TurnLoaderToIntakeBalls());
      // SmartDashboard.putData("IntakeBalls", new TurnLoaderOff());
      // SmartDashboard.putData("RejectBalls", new TurnLoaderToRejectBalls());
      // SmartDashboard.putData("StartShootBall", new StartShooter(1, 1));
      // SmartDashboard.putData("StopShootBall", new StopShooter());
 
    }
 
    public boolean getAutoDriveForwardButton() {
      return autoDriveForwardButton.get();
    }
 
    public boolean getautoDriveTurnRight() {
      return autoDriveTurnRight.get();
    }
 
    public XboxController getAssistJoystick() {
        return assistController;
    }
 
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
    if (Math.abs(Robot.oi.driverController.getY(Hand.kLeft)) > Constants.controllerDeadZone) {
      return -(Robot.oi.driverController.getY(Hand.kLeft) * (.2 * Robot.oi.getMainTriggers() + .50));
    } else {
      return 0;
    }
  }
 
  public double DriverRStickY() {
    if (Math.abs(Robot.oi.driverController.getY(Hand.kRight)) > Constants.controllerDeadZone) {
      return -(Robot.oi.driverController.getY(Hand.kRight) * (.2 * Robot.oi.getMainTriggers() + .50));
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
    return Math.max(driverController.getTriggerAxis(Hand.kLeft), driverController.getTriggerAxis(Hand.kRight));
  }
 
  public double getClimbSpeed() {
    if(Math.abs(assistController.getY(Hand.kLeft)) > Constants.controllerDeadZone) {
        return assistController.getY(Hand.kLeft);
    } else {
      return 0;
    }
  }
 
  public double getRotatorSpeed() {
    if(Math.abs(assistController.getX(Hand.kRight)) > Constants.controllerDeadZone) {
        return assistController.getX(Hand.kRight);
    } else {
      return 0;
    }
  }

	public double getAimHorizontalSpeed() {
    if(Math.abs(assistController.getX(Hand.kLeft)) > Constants.controllerDeadZone) {
        return assistController.getX(Hand.kLeft);
    } else {
      return 0;
    }
  }

	public double getAimVerticalSpeed() {
    if(Math.abs(assistController.getY(Hand.kLeft)) > Constants.controllerDeadZone) {
        return assistController.getY(Hand.kLeft);
    } else {
      return 0;
    }
  }
  
  public XboxController getDriverJoystick() {
    return driverController;
  }

  /*// CREATING BUTTONS
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
    */

}