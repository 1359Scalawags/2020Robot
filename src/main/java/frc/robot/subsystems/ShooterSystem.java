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
        topMotor = new CanMotor(Constants.TopBallMotorID);
        bottomMotor = new CanMotor(Constants.BottomBallMotorID);
        
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
        topMotor.setSpeed(top);
        bottomMotor.setSpeed(bottom);
    }

    public double getShooterSpeedTop() {
        return topMotor.Motor().get();
    }
    public double getShooterSpeedBottom() {
        return topMotor.Motor().get();
    }

    public Spark[] getRotateMotors(){
        Spark[] Motors = new Spark[2];
        Motors[0] = shootRotatorA;
        Motors[1] = shootRotatorB;
        
        return Motors;
    }

	public void rotateShooter(double speed) {
        if(speed > Constants.maxShooterTurnRate)
            speed=Constants.maxShooterTurnRate;
        else if(speed < -Constants.maxShooterTurnRate)
            speed=-Constants.maxShooterTurnRate;

        //TODO check if between limit switchs
        shootRotatorA.set(speed);
        shootRotatorB.set(speed);
	}


}
