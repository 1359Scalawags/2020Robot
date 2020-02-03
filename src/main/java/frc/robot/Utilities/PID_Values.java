package frc.robot.utilities;
import frc.robot.Constants;

public class PID_Values {
    private double kP;
    private double kI;
    private double kD;
    private double kIz;
    private double kFf;

    public PID_Values(){
        this(Constants.drivePID_P, Constants.drivePID_I, Constants.drivePID_D, Constants.drivePID_Iz, Constants.drivePID_Ff);
    }
    public PID_Values(double kP_, double kI_, double kD_, double kIz_, double kFf_){
        kP = kP_;
        kI = kI_;
        kD = kD_;
        kIz = kIz_;
        kFf = kFf_;
    }

    public double KP(){
        return kP;
    }
    public double KI(){
        return kI;
    }
    public double KD(){
        return kD;
    }
    public double KIz(){
        return kIz;
    }
    public double KFf(){
        return kFf;
    }
}