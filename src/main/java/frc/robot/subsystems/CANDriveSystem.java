package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.controller.PIDController;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import frc.robot.Constants.Drive;
//import frc.robot.Robot;
import frc.robot.helper.*;

import frc.robot.sendable.PIDSparkMax;
import frc.robot.sendable.SparkMaxEncoder;

public class CANDriveSystem extends SubsystemBase{  
  private PIDSparkMax[] leftMotors = new PIDSparkMax[2]; 
  // private CanMotor[] leftMotors = new CanMotor[2];
  private PIDSparkMax[] rightMotors = new PIDSparkMax[2];
  private SparkMaxEncoder[] leftEncoders = new SparkMaxEncoder[2];
  private SparkMaxEncoder[] rightEncoders = new SparkMaxEncoder[2];

  // private CanMotor rightMotorA;
  // private CanMotor rightMotorB;
  // private CanMotor leftMotorA;
  // private CanMotor leftMotorB;
  
  private SpeedControllerGroup leftControllerGroup;
  private SpeedControllerGroup rightControllerGroup;
  private DifferentialDrive diffDrive;
  private PID_Values gyroPids;
  private ADXRS450_Gyro driveGyro;
  private PIDController gyroControl;

  boolean reverse = false;    

  public CANDriveSystem() {
    leftMotors[0] = new PIDSparkMax(Drive.CANFrontLeftMotorID);
    leftMotors[1] = new PIDSparkMax(Drive.CANBackLeftMotorID);
    // leftMotors[0] = new CanMotor(Drive.CANFrontLeftMotorID);
    // leftMotors[1] = new CanMotor(Drive.CANBackLeftMotorID);


    //leftMotors[1].getMotorController().follow(leftMotors[0].getMotorController());

    leftEncoders[0] = leftMotors[0].getEncoder();
    leftEncoders[1] = leftMotors[1].getEncoder();

    rightMotors[0] = new PIDSparkMax(Drive.CANFrontRightMotorID);
    rightMotors[1] = new PIDSparkMax(Drive.CANBackRightMotorID);

    rightEncoders[0] = rightMotors[0].getEncoder();
    rightEncoders[1] = rightMotors[1].getEncoder();

    leftControllerGroup = new SpeedControllerGroup(leftMotors[0].getMotorController(), leftMotors[1].getMotorController());
    rightControllerGroup = new SpeedControllerGroup(rightMotors[0].getMotorController(), rightMotors[1].getMotorController());

    leftControllerGroup.setInverted(true);
    rightControllerGroup.setInverted(true);
    
    diffDrive = new DifferentialDrive(rightControllerGroup, leftControllerGroup);

    gyroPids = new PID_Values(Drive.gyrokP, Drive.gyrokI, Drive.gyrokD, Drive.gyrokIz, Drive.gyrokFf);
    driveGyro = new ADXRS450_Gyro();
    gyroControl = new PIDController(gyroPids.kP, gyroPids.kI, gyroPids.kD);

    leftMotors[0].setPID(Drive.PID_P, Drive.PID_I, Drive.PID_D, Drive.PID_Iz, Drive.PID_Ff);
    leftMotors[1].setPID(Drive.PID_P, Drive.PID_I, Drive.PID_D, Drive.PID_Iz, Drive.PID_Ff);
    rightMotors[0].setPID(Drive.PID_P, Drive.PID_I, Drive.PID_D, Drive.PID_Iz, Drive.PID_Ff);
    rightMotors[1].setPID(Drive.PID_P, Drive.PID_I, Drive.PID_D, Drive.PID_Iz, Drive.PID_Ff);

    // leftMotors[0].Encoder().setPositionConversionFactor(Drive.SystemScale);
    
  }

  @Override
  public void periodic() {
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

  public double getDistanceLeft() {
    return leftEncoders[0].getDistance(); //is one of these reversed values
}

public double getDistanceRight() {
    return rightEncoders[0].getDistance();

}

  public double getAverageDistance() {
	  return (getDistanceRight() + getDistanceLeft()) / 2;
  }
   
  // public void driveForwardVision(double speed){
  //   final double scale = .01;
  //   double leftSpeed;
  // 	double rightSpeed;
  // 	double headingError = getAngle();
		
  // 	leftSpeed = Utilities.Clamp(Math.abs(speed) - headingError * scale, -Constants.maxMotorSpeed, Constants.maxMotorSpeed);
  // 	rightSpeed = Utilities.Clamp(Math.abs(speed) + headingError * scale, -Constants.maxMotorSpeed, Constants.maxMotorSpeed);
  // 	tankDrive(leftSpeed, rightSpeed);		
  // }
    
  public void driveForward(double speed, double targetHeading) {
    final double scale = .01;
	  double leftSpeed;
	  double rightSpeed;
	  double headingError = getAngle() - targetHeading;
		
	  leftSpeed = Utilities.Clamp(Math.abs(speed) - headingError * scale, -Drive.maxMotorSpeed, Drive.maxMotorSpeed);
	  rightSpeed = Utilities.Clamp(Math.abs(speed) + headingError * scale, -Drive.maxMotorSpeed, Drive.maxMotorSpeed);
	  tankDrive(leftSpeed, rightSpeed);		
  }

  /**
   * Drive the robot using left/right joystick values
   * @param leftSpeed -1 to 1
   * @param rightSpeed -1 to 1
   */
  public void tankDrive(double leftSpeed, double rightSpeed) {
    if(reverse) {
      diffDrive.tankDrive(rightSpeed, leftSpeed);
    } else {
      diffDrive.tankDrive(-leftSpeed, -rightSpeed);
    }
  }
  public void driveBackward(double speed, double targetHeading) {
    //final double scale = .01;
    double leftSpeed;
    double rightSpeed;
    double headingError = getAngle() - targetHeading;
            
    leftSpeed =Utilities.Clamp(-(speed) - headingError * Drive.SystemScale, -Drive.maxMotorSpeed, Drive.maxMotorSpeed);
    rightSpeed = Utilities.Clamp(-(speed) + headingError * Drive.SystemScale, -Drive.maxMotorSpeed, Drive.maxMotorSpeed);
    tankDrive(leftSpeed, rightSpeed);		
  }

  public void stop() {
    tankDrive(0,0);
  }

  // public double convertToEncoderRate(double motorSpeed) {
  //   return Constants.fullDriveSpeed * motorSpeed; // feet per second
  // }

  public void arcadeDrive(double moveSpeed, double turnSpeed) {
    diffDrive.arcadeDrive(moveSpeed, turnSpeed);
  }

  /**
   * 
   * @param moveSpeed Robot movspeed between -1, 1
   * @param maxTurnSpeed Robot maximum turn speed -1, 1
   * @param targetAngle Target angle you want to turn to 0, 360
   */
  public void arcadeDrive(double moveSpeed, double maxTurnSpeed, double targetAngle) {
    double angleInput = driveGyro.getAngle();
    gyroControl.setSetpoint(targetAngle);
    double angleOutput = Utilities.Clamp(gyroControl.calculate(angleInput), -maxTurnSpeed, maxTurnSpeed);
    diffDrive.arcadeDrive(moveSpeed, angleOutput);
   
  }

  // @Override
  // public void updateDash(boolean Override){
    
  //   double rightmotors = SmartDashboard.getNumber("DriveRightMotors", 0);
  //   double leftmotors = SmartDashboard.getNumber("DriveLeftMotors", 0);
  //   double driveangle = SmartDashboard.getNumber("DriveGyroAngle", 0);

  //   if(driveangle != getAngle())
  //      SmartDashboard.putNumber("DriveGyroAngle", getAngle());
    
  //   if(Override){//drive PID, gyro pid

  //     if(rightControllerGroup.get() != rightmotors)
  //       rightControllerGroup.set(rightmotors);
  //     if(leftControllerGroup.get() != leftmotors)
  //       leftControllerGroup.set(leftmotors);
  //   }
  //   else{
  //     if(rightmotors != rightControllerGroup.get())
  //       SmartDashboard.putNumber("DriveRightMotors", rightmotors);
  //     if(leftmotors != leftControllerGroup.get())
  //       SmartDashboard.putNumber("DriveLeftMotors", leftmotors);
  //   }
    
  // }

  // @Override
  // public void putValues() {
  //   SmartDashboard.putNumber("DriveRightMotors", 0);
  //   SmartDashboard.putNumber("DriveleftMotors", 0);
  //   SmartDashboard.putNumber("DriveGyroAngle", 0);
  // }

  //Put methods for controlling this subsystem
  //here. Call these from Co

}