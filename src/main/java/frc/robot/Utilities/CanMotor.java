package frc.robot.utilities;

import com.revrobotics.CANEncoder;
import com.revrobotics.CANSparkMax;
import com.revrobotics.ControlType;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import com.revrobotics.CANPIDController;

import edu.wpi.first.wpilibj.SpeedController;
import frc.robot.Constants;
import frc.robot.utilities.PID_Values;

public class CanMotor{
    private CANEncoder encoder;
    // private SpeedController speedCont;
    private CANSparkMax motor;
    private CANPIDController controller;

    private double speed;
    
// SpeedController speedController, SpeedController[] speedControllers 
    public CanMotor(int id){
        this(id, new PID_Values());
    }

    public CanMotor(int id, PID_Values pid){
        motor = new CANSparkMax(id, MotorType.kBrushless);
        motor.restoreFactoryDefaults();
        motor.setInverted(false);
        controller = motor.getPIDController();
        controller.setP(pid.KP());
        controller.setI(pid.KI());
        controller.setD(pid.KD());
        controller.setIZone(pid.KIz());
        controller.setFF(pid.KFf());
        controller.setOutputRange(-1, 1);
        encoder = motor.getEncoder();
    }

    public void setRPM(double speed_) {
        if(speed != speed_)
            speed = speed_;
        updateRPM();
    }

    public void updateRPM(){
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