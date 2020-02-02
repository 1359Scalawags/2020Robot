package frc.robot;

import com.revrobotics.CANEncoder;
import com.revrobotics.CANPIDController;
import com.revrobotics.CANSparkMax;
import com.revrobotics.ControlType;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.SpeedControllerGroup;
import frc.robot.Constants;

public class Global {

  public static class Encoders{
    public CANEncoder a, b;
  }

  public static class SpeedControllers{
    public SpeedControllerGroup left, right;
  }

  public static class Motors{
    public CANSparkMax a, b;
  }

  public static class PIDControllers{
    public double speedA, speedB;
    public CANPIDController a, b;
    public void setRPM(double speed) {
      if(speedA != speed)
        speedA = speed;
      if(speedB != speed)
        speedB = speed;
      updateRPM();
    }

    public void setRPM(double speedA_, double speedB_) {
      if(speedA != speedA_)
        speedA = speedA_;
      if(speedB != speedB_)
        speedB = speedB_;
      updateRPM();
    }

    public void updateRPM(){
      a.setReference(speedA*Constants.MAXRPM, ControlType.kVelocity);
      b.setReference(speedB*Constants.MAXRPM, ControlType.kVelocity);
    }
  }

  public static class PID_Values{
    public double kP = Constants.drivePID_P;
    public double kI = Constants.drivePID_I;
    public double kD= Constants.drivePID_D;
    public double kIz= Constants.drivePID_Iz;
    public double kFf= Constants.MOTORS_Ff;

    public void set(double kP_, double kI_, double kD_, double kIz_, double kFf_){
      kP = kP_;
      kI = kI_;
      kD = kD_;
      kIz = kIz_;
      kFf = kFf_;
    }
  }

  public static void initialize(Motors Motors_, PIDControllers Controllers_, Encoders Encoders_, PID_Values PID_, int ID1, int ID2){
    Motors_.a = new CANSparkMax(ID1, MotorType.kBrushless);
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

    Motors_.b = new CANSparkMax(ID2, MotorType.kBrushless);
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