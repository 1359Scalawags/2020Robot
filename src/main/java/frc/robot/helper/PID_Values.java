package frc.robot.helper;
import frc.robot.Constants;

public class PID_Values {
    private double kP;
    private double kI;
    private double kD;
    private double kIz;
    private double kFf;
    private double[] arr = new double[5];

    public PID_Values(){
        this(Constants.drivePID_P, Constants.drivePID_I, Constants.drivePID_D, Constants.drivePID_Iz, Constants.drivePID_Ff);
    }
    public PID_Values(double[] arr_){
        this(arr_[0], arr_[1], arr_[2], arr_[3], arr_[4]);
    }
    public PID_Values(double kP_, double kI_, double kD_, double kIz_, double kFf_){
        kP = kP_ = arr[0];
        kI = kI_ = arr[1];
        kD = kD_ = arr[2];
        kIz = kIz_ = arr[3];
        kFf = kFf_ = arr[4];
    }

    public double[] toArray(){
        double[] result = {kP, kI, kD, kIz, kFf};
        return result;
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