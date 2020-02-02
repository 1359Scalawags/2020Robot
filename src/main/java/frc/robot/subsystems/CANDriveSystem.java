package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.controller.PIDController;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import frc.robot.Constants;
import frc.robot.Robot;
import frc.robot.utilities.*;

// import frc.robot.Global;
// import frc.robot.Global.*;

public class CANDriveSystem extends SubsystemBase {

  
  private CanMotor[] leftMotors = new CanMotor[2];
  private CanMotor[] rightMotors = new CanMotor[2];
  
  private SpeedControllerGroup leftControllerGroup;
  private SpeedControllerGroup rightControllerGroup;
  private DifferentialDrive robotDrive;
  private PID_Values gyroPids;
  private ADXRS450_Gyro driveGyro;
  private PIDController gyroControl;

  boolean reverse = false;    

  public CANDriveSystem() {

     leftMotors[0] = new CanMotor(Constants.FrontLeftMotorID);
     leftMotors[1] = new CanMotor(Constants.BackLeftMotorID);

     rightMotors[0] = new CanMotor(Constants.FrontRightMotorID);
     rightMotors[1] = new CanMotor(Constants.BackRightMotorID);
    
     leftControllerGroup = new SpeedControllerGroup(leftMotors[0].SpeedCont(), leftMotors[1].SpeedCont());
     rightControllerGroup = new SpeedControllerGroup(rightMotors[0].SpeedCont(), rightMotors[1].SpeedCont());

     robotDrive = new DifferentialDrive(rightControllerGroup, rightControllerGroup);
     gyroPids = new PID_Values(Constants.gyrokP, Constants.gyrokI, Constants.gyrokD, Constants.gyrokIz, Constants.gyrokFf);
     driveGyro = new ADXRS450_Gyro();
     gyroControl = new PIDController(gyroPids.KP(), gyroPids.KI(), gyroPids.KD());
    }


    @Override
    public void periodic() {
      tankDrive(Robot.oi.DriverLStickY(), Robot.oi.DriverRStickY());
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