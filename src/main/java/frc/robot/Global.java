package frc.robot;

import com.revrobotics.CANEncoder;
import com.revrobotics.CANPIDController;
import com.revrobotics.CANSparkMax;

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
}