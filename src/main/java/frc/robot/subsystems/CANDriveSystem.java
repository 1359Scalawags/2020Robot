package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.controller.PIDController;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import frc.robot.Constants;
import frc.robot.Global;
import frc.robot.Robot;
import frc.robot.Utilities;
import frc.robot.Global.*;

public class CANDriveSystem extends SubsystemBase {
  private Encoders LeftEncoders = new Encoders();
  private Encoders RightEncoders = new Encoders();

  private Motors LeftMotors = new Motors();
  private Motors RightMotors = new Motors();

  private ADXRS450_Gyro driveGyro;
  private PIDController gyroControl;
  private DifferentialDrive robotDrive;

  private SpeedControllers speedsControllers = new SpeedControllers();

  private PIDControllers LeftControllers = new PIDControllers();
  private PIDControllers RightControllers = new PIDControllers();
  private PID_Values PIDA = new PID_Values();
  private PID_Values PIDB = new PID_Values();
  
  // private double speedA, speedB;

  boolean reverse = false;    

  public CANDriveSystem() {
      Global.initialize(LeftMotors, LeftControllers, LeftEncoders, PIDA, Constants.FrontLeftMotorID, Constants.BackLeftMotorID);
      Global.initialize(RightMotors, RightControllers, RightEncoders, PIDB, Constants.FrontRightMotorID, Constants.BackRightMotorID);

      speedsControllers.left = new SpeedControllerGroup(LeftMotors.a, LeftMotors.b);
      speedsControllers.right = new SpeedControllerGroup(RightMotors.a, RightMotors.b);
      robotDrive = new DifferentialDrive(speedsControllers.left, speedsControllers.right);

      /*//intialize values on the SmartDashboard
      SmartDashboard.putNumber("MotorA: P Gain", kP_A);
      SmartDashboard.putNumber("MotorA: I Gain", kI_A);
      SmartDashboard.putNumber("MotorA: D Gain", kD_A);
      SmartDashboard.putNumber("MotorA: Speed Setting", speedA);

      SmartDashboard.putNumber("MotorB: P Gain", kP_B);
      SmartDashboard.putNumber("MotorB: I Gain", kI_B);
      SmartDashboard.putNumber("MotorB: D Gain", kD_B);
      SmartDashboard.putNumber("MotorB: Speed Setting", speedB);

      SmartDashboard.putNumber("MotorA: Measured Velocity", leftEncoderA.getVelocity());
      SmartDashboard.putNumber("MotorB: Measured Velocity", leftEncoderB.getVelocity()); */
    }


    @Override
    public void periodic() {
      LeftControllers.setRPM(Robot.oi.DriverLStickY());
      RightControllers.setRPM(Robot.oi.DriverRStickY());
    }

    public void drive() {
  
    }
   

  public void reverseDirection() {
    if (reverse) {
	    reverse = false;
    } else {
      reverse = true;
    }
  }

  public void ResetGyro(){
    driveGyro.reset();
  }

  public double getAngle() {
    return driveGyro.getAngle();
  }

  public double getGyroRate() {
    return driveGyro.getRate();
  }

/*  public double getDistanceLeft() {
    return leftEncoder.getDistance();
}

public double getDistanceRight() {
    return rightEncoder.getDistance();
}

  public double getAverageDistance() {
	  return (getDistanceRight() + getDistanceLeft()) / 2;
  }
*/    
  public void driveForwardVision(double speed){
    final double scale = .01;
    double leftSpeed;
  	double rightSpeed;
  	double headingError = getAngle();
		
  	leftSpeed = Utilities.Clamp(Math.abs(speed) - headingError * scale, -Constants.maxMotorSpeed, Constants.maxMotorSpeed);
  	rightSpeed = Utilities.Clamp(Math.abs(speed) + headingError * scale, -Constants.maxMotorSpeed, Constants.maxMotorSpeed);
  	tankDrive(leftSpeed, rightSpeed);		
  }
    
  public void driveForward(double speed, double targetHeading) {
    final double scale = .01;
	  double leftSpeed;
	  double rightSpeed;
	  double headingError = getAngle() - targetHeading;
		
	  leftSpeed = Utilities.Clamp(Math.abs(speed) - headingError * scale, -Constants.maxMotorSpeed, Constants.maxMotorSpeed);
	  rightSpeed = Utilities.Clamp(Math.abs(speed) + headingError * scale, -Constants.maxMotorSpeed, Constants.maxMotorSpeed);
	  tankDrive(leftSpeed, rightSpeed);		
  }

  public void tankDrive(double leftSpeed, double rightSpeed) {
    if(reverse) {
      robotDrive.tankDrive(-rightSpeed, -leftSpeed);
      //m_drive2.tankDrive(-rightSpeed, -leftSpeed); //new
    } else {
      robotDrive.tankDrive(leftSpeed, rightSpeed);
      //m_drive2.tankDrive(rightSpeed, leftSpeed); //new
    }
  }
  public void driveBackward(double speed, double targetHeading) {
    final double scale = .01;
    double leftSpeed;
    double rightSpeed;
    double headingError = getAngle() - targetHeading;
            
    leftSpeed =Utilities.Clamp(-(speed) - headingError * scale, -Constants.maxMotorSpeed, Constants.maxMotorSpeed);
    rightSpeed = Utilities.Clamp(-(speed) + headingError * scale, -Constants.maxMotorSpeed, Constants.maxMotorSpeed);
    tankDrive(leftSpeed, rightSpeed);		
  }

  public void stop() {
    tankDrive(0,0);
  }

  public double convertToEncoderRate(double motorSpeed) {
    return Constants.fullDriveSpeed * motorSpeed; // feet per second
  }

  public void arcadeDrive(double moveSpeed, double turnSpeed) {
    robotDrive.arcadeDrive(moveSpeed, turnSpeed);
  }

  public void arcadeDrive(double moveSpeed, double maxTurnSpeed, double targetAngle) {
    double angleInput = driveGyro.getAngle();
    gyroControl.setSetpoint(targetAngle);
    double angleOutput = Utilities.Clamp(gyroControl.calculate(angleInput), -maxTurnSpeed, maxTurnSpeed);
    robotDrive.arcadeDrive(moveSpeed, angleOutput);
   
  }
  
  //Put methods for controlling this subsystem
  //here. Call these from Co

}