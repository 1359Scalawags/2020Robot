package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Constants.Shooter;
import frc.robot.helper.*;

import frc.robot.interfaces.scheduler;
/**
 *
 */
public class ShooterSystem extends SubsystemBase implements scheduler{

    
    private CanMotor topMotor;
    private CanMotor bottomMotor;
    private Spark shootRotatorA;
    private Spark shootRotatorB;
    private Spark shotLoader;

    public ShooterSystem(){
        topMotor = new CanMotor(Shooter.CANTopBallMotorID);
        bottomMotor = new CanMotor(Shooter.CANBottomBallMotorID);
        
        shootRotatorA = new Spark(Shooter.PWMShootRotatorLeftRightID);
        shootRotatorA.setInverted(false);

        shootRotatorB = new Spark(Shooter.PWMShootRotatorUpDownID);
        shootRotatorB.setInverted(true);
        
        shotLoader = new Spark(Shooter.PWMLoadShotMotorID);
        shotLoader.setInverted(false);
    }
    
    /**
     * @param top Top shooter roller -1, 1
     * @param bottom Bottom shooter roller -1, 1
     */

    public void setShooterSpeed(double top, double bottom) {
        topMotor.set(top);
        bottomMotor.set(bottom);
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
        if(speed1 > Shooter.maxShooterTurnRate)
            speed1=Shooter.maxShooterTurnRate;
        else if(speed1 < -Shooter.maxShooterTurnRate)
            speed1=-Shooter.maxShooterTurnRate;

        if(speed2 > Shooter.maxShooterTurnRate)
            speed2=Shooter.maxShooterTurnRate;
        else if(speed2 < -Shooter.maxShooterTurnRate)
            speed2=-Shooter.maxShooterTurnRate;

        //TODO check if between limit switchs
        shootRotatorA.set(speed1);
        shootRotatorB.set(speed2);
	}

    @Override
    public void updateDashboard() {
        shootRotatorA.set(SmartDashboard.getNumber("ShootRotatorX speed", 0));
        shootRotatorB.set(SmartDashboard.getNumber("ShootRotatorY speed", 0));
        shotLoader.setSpeed(SmartDashboard.getNumber("ShotLoader Speed", 0));
        topMotor.set(SmartDashboard.getNumber("ShooterSpeed top", 0));
        bottomMotor.set(SmartDashboard.getNumber("ShooterSpeed bottom", 0));
    }

    @Override
    public void putValues() {
        // TODO Auto-generated method stub
        SmartDashboard.putNumber("ShootRotatorX speed", 0);
        SmartDashboard.putNumber("ShootRotatorY speed", 0);
        SmartDashboard.putNumber("ShotLoader Speed", 0);
        SmartDashboard.putNumber("ShooterSpeed top", 0);
        SmartDashboard.putNumber("ShooterSpeed bottom", 0);
    }


}
