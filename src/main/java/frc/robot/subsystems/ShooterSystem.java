package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj.CounterBase.EncodingType;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.Talon;
import frc.robot.Constants;
import frc.robot.helper.*;

/**
 *
 */
public class ShooterSystem extends SubsystemBase {

    
    private CanMotor topMotor;
    private CanMotor bottomMotor;
    private Spark shootRotatorA;
    private Spark shootRotatorB;
    private Spark shotLoader;

    public ShooterSystem(){
        topMotor = new CanMotor(Constants.CANTopBallMotorID);
        bottomMotor = new CanMotor(Constants.CANBottomBallMotorID);
        
        shootRotatorA = new Spark(Constants.PWMShootRotatorLeftRightID);
        shootRotatorA.setInverted(false);
        
        shotLoader = new Spark(Constants.PWMLoadShotMotorID);
        shotLoader.setInverted(false);

        //TODO Does this need to be inverted?
        shootRotatorB = new Spark(Constants.PWMShootRotatorUpDownID);
        shootRotatorB.setInverted(true);

        
        shotLoader = new Spark(Constants.PWMLoadShotMotorID);
        //addChild("ShooterLoader",shotLoader);
        shotLoader.setInverted(false);
    }
    
    /**
     * @param top Top shooter roller -1, 1
     * @param bottom Bottom shooter roller -1, 1
     */

    public void setShooterSpeed(double top, double bottom) {
        topMotor.setSpeed(top);
        bottomMotor.setSpeed(bottom);
    }

    public void setShotLoaderSpeed(double loader) {
        shotLoader.setSpeed(loader);
    }

    public double getShooterSpeedTop() {
        return topMotor.Motor().get();
    }
    public double getShooterSpeedBottom() {
        return topMotor.Motor().get();
    }

    // public Spark[] getRotateMotors(){
    //     Spark[] Motors = new Spark[2];
    //     Motors[0] = shootRotatorA;
    //     Motors[1] = shootRotatorB;
        
    //     return Motors;
    // }



	public void rotateShooter(double speed1, double speed2) {
        if(speed1 > Constants.maxShooterTurnRate)
            speed1=Constants.maxShooterTurnRate;
        else if(speed1 < -Constants.maxShooterTurnRate)
            speed1=-Constants.maxShooterTurnRate;

        if(speed2 > Constants.maxShooterTurnRate)
            speed2=Constants.maxShooterTurnRate;
        else if(speed2 < -Constants.maxShooterTurnRate)
            speed2=-Constants.maxShooterTurnRate;

        //TODO check if between limit switchs
        shootRotatorA.set(speed1);
        shootRotatorB.set(speed2);
	}


}
