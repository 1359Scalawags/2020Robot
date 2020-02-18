package frc.robot.helper;
import frc.robot.Constants.Drive;

public class PID_Values {
    public final double kP;
    public final double kI;
    public final double kD;
    public final double kIz;
    public final double kFf;
    public final double[] arr = new double[5];

    public PID_Values(){
        this(Drive.PID_P, Drive.PID_I, Drive.PID_D, Drive.PID_Iz, Drive.PID_Ff);
    }
    public PID_Values(double[] arr_){
        this(arr_[0], arr_[1], arr_[2], arr_[3], arr_[4]);
    }
    public PID_Values(double kP_, double kI_, double kD_, double kIz_, double kFf_){
        kP = arr[0] =  kP_;
        kI = arr[1] = kI_;
        kD = arr[2] = kD_;
        kIz = arr[3] = kIz_;
        kFf = arr[4] = kFf_;
    }

    

    public boolean equals(PID_Values pid){
        return(this.kP == pid.kP && this.kI == pid.kI && this.kD == pid.kD && this.kIz == pid.kIz && this.kIz == pid.kFf);
    }

    public boolean equals(double[] pid_){
        double[] pid = toArray();
        for (int i=0; i<pid.length; i++) {
            if(pid[i] != pid_[i])
                return false;
        }
        return true;
    }

    public double[] toArray(){
        double[] result = {kP, kI, kD, kIz, kFf};
        return result;
    }
}