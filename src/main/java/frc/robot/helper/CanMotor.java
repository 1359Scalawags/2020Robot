package frc.robot.helper;

import com.revrobotics.CANEncoder;
import com.revrobotics.CANSparkMax;
import com.revrobotics.ControlType;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import com.revrobotics.CANPIDController;

//import edu.wpi.first.wpilibj.SpeedController;
import frc.robot.Constants;
import frc.robot.helper.PID_Values;

public class CanMotor{
    private CANEncoder encoder;
    // private SpeedController speedCont;
    private CANSparkMax motor;
    private CANPIDController controller;
    private double speed;
    private PID_Values pid;
    
// SpeedController speedController, SpeedController[] speedControllers 
    public CanMotor(int id){
        this(id, new PID_Values());
    }

    public CanMotor(int id, PID_Values pid_){
        pid = pid_;
        motor = new CANSparkMax(id, MotorType.kBrushless);
        motor.restoreFactoryDefaults();
        motor.setInverted(false);
        controller = motor.getPIDController();
        updatePID();
        controller.setOutputRange(-1, 1);
        encoder = motor.getEncoder();
    }
    public double getSpeed(){
        return speed;
    }

    public void setPID(double[] PID){
        pid = new PID_Values(PID);
        updatePID();
    }

    private void updatePID(){
        controller.setP(pid.kP);
        controller.setI(pid.kI);
        controller.setD(pid.kD);
        controller.setIZone(pid.kIz);
        controller.setFF(pid.kFf);
    }
    public PID_Values getPID(){
       return pid;
    }

    /**
     * Sets velocity as percentage of maximum RPM.
     * @param speed_ A value between -1 and 1
     */
    public void setSpeed(double speed_) {
        if(speed != speed_)
            speed = speed_;
        updateSpeed();
    }

    public void updateSpeed(){
        controller.setReference(speed*Constants.MAXRPM, ControlType.kVelocity);
    }

    public CANEncoder Encoder(){
        return encoder;
    }
    // public SpeedController SpeedCont(){
    //     return speedCont;
    // }
    public CANSparkMax Motor(){
        return motor;
    }
    public CANPIDController Controller(){
        return controller;
    }
}