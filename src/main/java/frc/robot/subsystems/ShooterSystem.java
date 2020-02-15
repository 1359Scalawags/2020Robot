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

    private Spark shootRotatorA;
    private Spark shootRotatorB;

    public ShooterSystem(){
        
        shootRotatorA = new Spark(Constants.ShootRotatorAID);
        shootRotatorA.setInverted(false);
        
        //TODO Does this need to be inverted?
        shootRotatorB = new Spark(Constants.ShootRotatorBID);
        shootRotatorB.setInverted(true);
    }
    

    public Spark[] getRotateMotors(){
        Spark[] Motors = new Spark[2];
        Motors[0] = shootRotatorA;
        Motors[1] = shootRotatorB;
        
        return Motors;
    }
    
	public double getShooterPosition() {
		return shootRotatorA.getPosition();
	}

	public void rotateShooter(double speed) {
        if(speed > Constants.maxShooterTurnRate)
            speed=Constants.maxShooterTurnRate;
        else if(speed < -Constants.maxShooterTurnRate)
            speed=-Constants.maxShooterTurnRate;

        //TODO check if between limit switchs
        shootRotatorA.set(speed);
	}

}