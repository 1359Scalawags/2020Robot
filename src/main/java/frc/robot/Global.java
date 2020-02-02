package frc.robot;

import com.revrobotics.CANEncoder;
import com.revrobotics.CANPIDController;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import frc.robot.Constants;

public class Global {
  
  public static class Encoders{
    public CANEncoder a, b;
  }
  public static class Motors{
    public CANSparkMax a, b;
  }
  public static class PIDControllers{
    public CANPIDController a, b;
  }
  public static class PID_Values{
    public double kP = Constants.drivePID_P;
    public double kI = Constants.drivePID_I;
    public double kD= Constants.drivePID_D;
    public double kIz= Constants.drivePID_Iz;
    public double kFf= Constants.MOTORS_Ff;
  }

  
  public static void initialize(Motors Motors_, PIDControllers Controllers_, Encoders Encoders_, PID_Values PID_){
    Motors_.a = new CANSparkMax(Constants.FrontLeftMotorID, MotorType.kBrushless);
    Motors_.a.restoreFactoryDefaults();
    Motors_.a.setInverted(false);
    Controllers_.a = Motors_.a.getPIDController();
    Controllers_.a.setP(PID_.kP);
    Controllers_.a.setI(PID_.kI);
    Controllers_.a.setD(PID_.kD);
    Controllers_.a.setIZone(PID_.kIz);
    Controllers_.a.setFF(PID_.kFf);
    Controllers_.a.setOutputRange(-1, 1);
    Encoders_.a = Motors_.a.getEncoder();

    Motors_.b = new CANSparkMax(Constants.FrontLeftMotorID, MotorType.kBrushless);
    Motors_.b.restoreFactoryDefaults();
    Motors_.b.setInverted(false);
    Controllers_.b = Motors_.b.getPIDController();
    Controllers_.b.setP(PID_.kP);
    Controllers_.b.setI(PID_.kI);
    Controllers_.b.setD(PID_.kD);
    Controllers_.b.setIZone(PID_.kIz);
    Controllers_.b.setFF(PID_.kFf);
    Controllers_.b.setOutputRange(-1, 1);
    Encoders_.b = Motors_.b.getEncoder();
  }
  

}