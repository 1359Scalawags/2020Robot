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

    
    private CanMotor ballMotorA;
    private CanMotor ballMotorB;
    private Spark shootRotatorA;
    private Spark shootRotatorB;
    private Spark shotLoader;

    public ShooterSystem(){
        ballMotorA = new CanMotor(Constants.TopBallMotorID);
        ballMotorB = new CanMotor(Constants.BottomBallMotorID);
        
        shootRotatorA = new Spark(Constants.ShootRotatorAID);
        shootRotatorA.setInverted(false);
        
        shotLoader = new Spark(Constants.LoadShotMotorID);
        shotLoader.setInverted(false);

        //TODO Does this need to be inverted?
        shootRotatorB = new Spark(Constants.ShootRotatorBID);
        shootRotatorB.setInverted(true);

        
        shotLoader = new Spark(Constants.LoadShotMotorID);
        //addChild("ShooterLoader",shotLoader);
        shotLoader.setInverted(false);
    }
    
    /**
     * @param top Top shooter roller -1, 1
     * @param bottom Bottom shooter roller -1, 1
     */

    public void setShooterSpeed(double top, double bottom) {
        ballMotorA.setSpeed(top);
        ballMotorB.setSpeed(bottom);
    }

    public Spark[] getRotateMotors(){
        Spark[] Motors = new Spark[2];
        Motors[0] = shootRotatorA;
        Motors[1] = shootRotatorB;
        
        return Motors;
    }

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
