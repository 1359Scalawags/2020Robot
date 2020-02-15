package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.controller.PIDController;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Constants;
//import frc.robot.Robot;
import frc.robot.helper.*;

// import frc.robot.Global;
// import frc.robot.Global.*;

public class CANDriveSystem extends SubsystemBase {

  
  private CanMotor[] leftMotors = new CanMotor[2];
  private CanMotor[] rightMotors = new CanMotor[2];
  
  private SpeedControllerGroup leftControllerGroup;
  private SpeedControllerGroup rightControllerGroup;
  private DifferentialDrive diffDrive;
  private PID_Values gyroPids;
  private ADXRS450_Gyro driveGyro;
  private PIDController gyroControl;

  boolean reverse = false;    

  public CANDriveSystem() {

     leftMotors[0] = new CanMotor(Constants.FrontLeftMotorID);
     leftMotors[1] = new CanMotor(Constants.BackLeftMotorID);

     rightMotors[0] = new CanMotor(Constants.FrontRightMotorID);
     rightMotors[1] = new CanMotor(Constants.BackRightMotorID);
    
     leftControllerGroup = new SpeedControllerGroup(leftMotors[0].Motor(), leftMotors[1].Motor());
     rightControllerGroup = new SpeedControllerGroup(rightMotors[0].Motor(), rightMotors[1].Motor());

     diffDrive = new DifferentialDrive(rightControllerGroup, leftControllerGroup);
     gyroPids = new PID_Values(Constants.gyrokP, Constants.gyrokI, Constants.gyrokD, Constants.gyrokIz, Constants.gyrokFf);
     driveGyro = new ADXRS450_Gyro();
     gyroControl = new PIDController(gyroPids.KP(), gyroPids.KI(), gyroPids.KD());
  }

  @Override
  public void periodic() {
  }

  public void updateDashbord(){
    SmartDashboard.putNumber("RightMotorVelocity", rightMotors[0].Encoder().getVelocity());
    SmartDashboard.putNumber("LeftMotorVelocity", leftMotors[0].Encoder().getVelocity());
    SmartDashboard.putNumber("DriveGyroAngly", driveGyro.getAngle());
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
		
	  leftSpeed = Utilities.Clamp(Math.abs(speed) - headingError * scale, -Constants.maxMotorSpeed, Constants.maxMotorSpeed);
	  rightSpeed = Utilities.Clamp(Math.abs(speed) + headingError * scale, -Constants.maxMotorSpeed, Constants.maxMotorSpeed);
	  tankDrive(leftSpeed, rightSpeed);		
  }

  /**
   * Drive the robot using left/right joystick values
   * @param leftSpeed -1 to 1
   * @param rightSpeed -1 to 1
   */
  public void tankDrive(double leftSpeed, double rightSpeed) {
    if(reverse) {
      diffDrive.tankDrive(-rightSpeed, -leftSpeed);
      //m_drive2.tankDrive(-rightSpeed, -leftSpeed); //new
    } else {
      diffDrive.tankDrive(leftSpeed, rightSpeed);
      //m_drive2.tankDrive(rightSpeed, leftSpeed); //new
    }
  }
  public void driveBackward(double speed, double targetHeading) {
    //final double scale = .01;
    double leftSpeed;
    double rightSpeed;
    double headingError = getAngle() - targetHeading;
            
    leftSpeed =Utilities.Clamp(-(speed) - headingError * Constants.driveSystemScale, -Constants.maxMotorSpeed, Constants.maxMotorSpeed);
    rightSpeed = Utilities.Clamp(-(speed) + headingError * Constants.driveSystemScale, -Constants.maxMotorSpeed, Constants.maxMotorSpeed);
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
  
  //Put methods for controlling this subsystem
  //here. Call these from Co

}