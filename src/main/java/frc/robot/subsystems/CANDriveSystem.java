package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Utilities;
import edu.wpi.first.wpilibj.ADXRS450_Gyro;
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

    private CANEncoder speedEncoderA;
    private CANEncoder speedEncoderB;
    private CANSparkMax motorA;
    private CANSparkMax motorB;
    private ADXRS450_Gyro driveGyro;
    private PIDController gyroControl;
    private DifferentialDrive robotDrive;
    // private Encoder leftEncoder;
    // private Encoder rightEncoder;

    private CANPIDController controllerA;
    private CANPIDController controllerB;
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
        motorA = new CANSparkMax(Constants.FrontLeftMotorID, MotorType.kBrushless);
        motorA.restoreFactoryDefaults();
        motorA.setInverted(false);
        controllerA = motorA.getPIDController();
        controllerA.setP(kP_A);
        controllerA.setI(kI_A);
        controllerA.setD(kD_A);
        controllerA.setIZone(kIz_A);
        controllerA.setFF(kFf_A);
        controllerA.setOutputRange(-1, 1);

        //initialize Motor B and all settings
        motorB = new CANSparkMax(Constants.FrontRightMotorID, MotorType.kBrushless);
        motorB.restoreFactoryDefaults();
        motorB.setInverted(false);
        controllerB = motorB.getPIDController();
        controllerB.setP(kP_B);
        controllerB.setI(kI_B);
        controllerB.setD(kD_B);
        controllerB.setIZone(kIz_B);
        controllerB.setFF(kFf_B);
        controllerB.setOutputRange(-1, 1);

        //obtain encoders from motor controllers
        speedEncoderA = motorA.getEncoder();       
        speedEncoderB = motorB.getEncoder();

        //intialize values on the SmartDashboard
        SmartDashboard.putNumber("MotorA: P Gain", kP_A);
        SmartDashboard.putNumber("MotorA: I Gain", kI_A);
        SmartDashboard.putNumber("MotorA: D Gain", kD_A);
        SmartDashboard.putNumber("MotorA: Speed Setting", speedA);

        SmartDashboard.putNumber("MotorB: P Gain", kP_B);
        SmartDashboard.putNumber("MotorB: I Gain", kI_B);
        SmartDashboard.putNumber("MotorB: D Gain", kD_B);
        SmartDashboard.putNumber("MotorB: Speed Setting", speedB);

        SmartDashboard.putNumber("MotorA: Measured Velocity", speedEncoderA.getVelocity());
        SmartDashboard.putNumber("MotorB: Measured Velocity", speedEncoderB.getVelocity());
      
    }
  
    /***
     * Motor speeds are set with regard to the MAXRPM constant
     * @param speedA A value from -1 to 1
     * @param speedB A value from -1 to 1
     */
    public void setMotorRPM(double speedA, double speedB) {
        controllerA.setReference(speedA*Constants.maxTurnRate, ControlType.kVelocity);
        controllerB.setReference(speedB*Constants.maxTurnRate, ControlType.kVelocity);
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