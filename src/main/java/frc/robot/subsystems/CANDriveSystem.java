package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Utilities;
import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.controller.PIDController;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
// import edu.wpi.first.wpilibj.Encoder;

import com.revrobotics.CANSparkMax;
import com.revrobotics.ControlType;
import com.revrobotics.CANEncoder;
import com.revrobotics.CANPIDController;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

/**
 *
 */
public class CANDriveSystem extends SubsystemBase {

    private CANEncoder leftEncoderA;
    private CANEncoder leftEncoderB;
    private CANEncoder rightEncoderA;
    private CANEncoder rightEncoderB;
    private CANSparkMax leftMotorA;
    private CANSparkMax rightMotorA;
    private CANSparkMax leftMotorB;
    private CANSparkMax rightMotorB;
    private ADXRS450_Gyro driveGyro;
    private PIDController gyroControl;
    private DifferentialDrive robotDrive;
    private SpeedControllerGroup leftMotors;
    private SpeedControllerGroup rightMotors;

    // private Encoder leftEncoder;
    // private Encoder rightEncoder;

    private CANPIDController leftControllerA;
    private CANPIDController leftControllerB;
    private CANPIDController rightControllerA;
    private CANPIDController rightControllerB;
    private double kP_A, kP_B, kI_A, kI_B, kD_A, kD_B, kIz_A, kIz_B, kFf_A, kFf_B;
    private double speedA, speedB;

    boolean reverse = false;    

    public CANDriveSystem() {

        //initialize variables using default values
        kP_A = kP_B = Constants.drivePID_P;
        kI_A = kI_B = Constants.drivePID_I;
        kD_A = kD_B = Constants.drivePID_D;
        kIz_A = kIz_B = Constants.drivePID_Iz;
        kFf_A = kFf_B = Constants.MOTORS_Ff;

        //initialize Motor A and all settings
        leftMotorA = new CANSparkMax(Constants.FrontLeftMotorID, MotorType.kBrushless);
        leftMotorA.restoreFactoryDefaults();
        leftMotorA.setInverted(false);
        leftControllerA = leftMotorA.getPIDController();
        leftControllerA.setP(kP_A);
        leftControllerA.setI(kI_A);
        leftControllerA.setD(kD_A);
        leftControllerA.setIZone(kIz_A);
        leftControllerA.setFF(kFf_A);
        leftControllerA.setOutputRange(-1, 1);

        //initialize Motor B and all settings
        leftMotorB = new CANSparkMax(Constants.BackLeftMotorID, MotorType.kBrushless);
        leftMotorB.restoreFactoryDefaults();
        leftMotorB.setInverted(false);
        leftControllerB = leftMotorB.getPIDController();
        leftControllerB.setP(kP_A);
        leftControllerB.setI(kI_A);
        leftControllerB.setD(kD_A);
        leftControllerB.setIZone(kIz_A);
        leftControllerB.setFF(kFf_A);
        leftControllerB.setOutputRange(-1, 1);

        rightMotorA = new CANSparkMax(Constants.FrontRightMotorID, MotorType.kBrushless);
        rightMotorA.restoreFactoryDefaults();
        rightMotorA.setInverted(false);
        rightControllerA = rightMotorA.getPIDController();
        rightControllerA.setP(kP_B);
        rightControllerA.setI(kI_B);
        rightControllerA.setD(kD_B);
        rightControllerA.setIZone(kIz_B);
        rightControllerA.setFF(kFf_B);
        rightControllerA.setOutputRange(-1, 1);

        //initialize Motor B and all settings
        rightMotorB = new CANSparkMax(Constants.BackRightMotorID, MotorType.kBrushless);
        rightMotorB.restoreFactoryDefaults();
        rightMotorB.setInverted(false);
        rightControllerB = rightMotorB.getPIDController();
        rightControllerB.setP(kP_B);
        rightControllerB.setI(kI_B);
        rightControllerB.setD(kD_B);
        rightControllerB.setIZone(kIz_B);
        rightControllerB.setFF(kFf_B);
        rightControllerB.setOutputRange(-1, 1);

        //obtain encoders from motor controllers
        leftEncoderA = leftMotorA.getEncoder();       
        leftEncoderB = leftMotorB.getEncoder();
        rightEncoderA = rightMotorA.getEncoder();
        rightEncoderB = rightMotorB.getEncoder();

        leftMotors = new SpeedControllerGroup(leftMotorA, leftMotorB);
        rightMotors = new SpeedControllerGroup(rightMotorA, rightMotorB);
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