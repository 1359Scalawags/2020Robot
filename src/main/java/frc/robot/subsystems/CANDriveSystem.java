package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.Global.*;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Utilities;
import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.controller.PIDController;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
// import edu.wpi.first.wpilibj.Encoder;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

;
/**
 *
 */
public class CANDriveSystem extends SubsystemBase {
  private Encoders LeftEncoders = new Encoders();
  private Encoders RightEncoders = new Encoders();
  private Motors LeftMotors = new Motors();
  private Motors RightMotors = new Motors();

  private ADXRS450_Gyro driveGyro;
  private PIDController gyroControl;
  private DifferentialDrive robotDrive;
  private SpeedControllerGroup leftMotors;
  private SpeedControllerGroup rightMotors;

  private PIDControllers LeftControllers = new PIDControllers();
  private PIDControllers RightControllers = new PIDControllers();
  private PID_Values PIDA = new PID_Values();
  private PID_Values PIDB = new PID_Values();
  
  private double speedA, speedB;

  boolean reverse = false;    

  public CANDriveSystem() {

      //initialize variables using default values
      // PIDA.kP = PIDB.kP = Constants.drivePID_P;
      // PIDA.kI = kI = Constants.drivePID_I;
      // kD = kD = Constants.drivePID_D;
      // kIz = kIz = Constants.drivePID_Iz;
      // kFf = kFf = Constants.MOTORS_Ff;

      //initialize Motor A and all settings
      
      LeftMotors.a = new CANSparkMax(Constants.FrontLeftMotorID, MotorType.kBrushless);
      LeftMotors.a.restoreFactoryDefaults();
      LeftMotors.a.setInverted(false);
      LeftControllers.a = LeftMotors.a.getPIDController();
      LeftControllers.a.setP(PIDA.kP);
      LeftControllers.a.setI(PIDA.kI);
      LeftControllers.a.setD(PIDA.kD);
      LeftControllers.a.setIZone(PIDA.kIz);
      LeftControllers.a.setFF(PIDA.kFf);
      LeftControllers.a.setOutputRange(-1, 1);

      //initialize Motor B and all settings
      LeftMotors.b = new CANSparkMax(Constants.BackLeftMotorID, MotorType.kBrushless);
      LeftMotors.b.restoreFactoryDefaults();
      LeftMotors.b.setInverted(false);
      LeftControllers.b = LeftMotors.b.getPIDController();
      LeftControllers.b.setP(PIDB.kP);
      LeftControllers.b.setI(PIDB.kI);
      LeftControllers.b.setD(PIDB.kD);
      LeftControllers.b.setIZone(PIDA.kIz);
      LeftControllers.b.setFF(PIDA.kFf);
      LeftControllers.b.setOutputRange(-1, 1);

      RightMotors.a = new CANSparkMax(Constants.FrontLeftMotorID, MotorType.kBrushless);
      RightMotors.a.restoreFactoryDefaults();
      RightMotors.a.setInverted(false);
      RightControllers.a = LeftMotors.a.getPIDController();
      RightControllers.a.setP(PIDA.kP);
      RightControllers.a.setI(PIDA.kI);
      RightControllers.a.setD(PIDA.kD);
      RightControllers.a.setIZone(PIDA.kIz);
      RightControllers.a.setFF(PIDA.kFf);
      RightControllers.a.setOutputRange(-1, 1);

      //initialize Motor B and all settings
      RightMotors.b = new CANSparkMax(Constants.BackLeftMotorID, MotorType.kBrushless);
      RightMotors.b.restoreFactoryDefaults();
      RightMotors.b.setInverted(false);
      RightControllers.b = LeftMotors.b.getPIDController();
      RightControllers.b.setP(PIDB.kP);
      RightControllers.b.setI(PIDB.kI);
      RightControllers.b.setD(PIDB.kD);
      RightControllers.b.setIZone(PIDB.kIz);
      RightControllers.b.setFF(PIDB.kFf);
      RightControllers.b.setOutputRange(-1, 1);

      //obtain encoders from motor controllers
      LeftEncoders.a = LeftMotors.a.getEncoder();
      LeftEncoders.b = LeftMotors.b.getEncoder();
      RightEncoders.a = RightMotors.a.getEncoder();
      RightEncoders.b = RightMotors.b.getEncoder();

      leftMotors = new SpeedControllerGroup(LeftMotors.a, LeftMotors.b);
      rightMotors = new SpeedControllerGroup(RightMotors.a, RightMotors.b);
      robotDrive = new DifferentialDrive(leftMotors, rightMotors);

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