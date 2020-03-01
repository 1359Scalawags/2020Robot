package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Constants.*;

import frc.robot.interfaces.scheduler;
import frc.robot.sendable.PIDSparkMax;
/**
 *
 */
public class ShooterSystem extends SubsystemBase implements scheduler{

    
    private PIDSparkMax topMotor;
    private PIDSparkMax bottomMotor;
    private Spark shootRotatorX;
    private Spark shootRotatorY;
    private Spark shotLoader;

    private DigitalInput rightLimit;
    private DigitalInput leftLimit;
    private DigitalInput upLimit;
    private DigitalInput downLimit;

    public ShooterSystem(){
        
        rightLimit = new DigitalInput(Shooter.shotRightLimit);
        leftLimit = new DigitalInput(Shooter.shotLeftLimit);
        upLimit = new DigitalInput(Shooter.shotUpLimit);
        downLimit = new DigitalInput(Shooter.shotDownLimit);

        topMotor = new PIDSparkMax(Shooter.CANTopBallMotorID);
        bottomMotor = new PIDSparkMax(Shooter.CANBottomBallMotorID);
        
        shootRotatorX = new Spark(Shooter.PWMShootRotatorLeftRightID);
        shootRotatorX.setInverted(false);

        shootRotatorY = new Spark(Shooter.PWMShootRotatorUpDownID);
        shootRotatorY.setInverted(false);
        
        shotLoader = new Spark(Load.PWMLoadShotMotorID);
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
        return topMotor.get();
    }
    public double getShooterSpeedBottom() {
        return topMotor.get();
    }

    // public Spark[] getRotateMotors(){
    //     Spark[] Motors = new Spark[2];
    //     Motors[0] = shootRotatorA;
    //     Motors[1] = shootRotatorB;
        
    //     return Motors;
    // }

	public void rotateShooter(double Horizontal, double Vetical) {
        rotateHorizontally(Horizontal);
        rotateVertically(Vetical);
    }
    
    /**
     * @param speed Positive is right, negative is left
     */
    public void rotateHorizontally(double speed) {
        if(speed > 0 && rightLimit.get() != Shooter.LIMIT_PRESSED) {
            shootRotatorX.set(speed);            
        } else if(speed < 0 && leftLimit.get() != Shooter.LIMIT_PRESSED) {
            shootRotatorX.set(speed);
        } else {
            shootRotatorX.set(0);
        }
    }

    /**
     * @param speed Positive is up, negative is down
     */
    public void rotateVertically(double speed) {
        if(speed > 0 && upLimit.get() != Shooter.LIMIT_PRESSED) {
            shootRotatorY.set(speed);            
        } else if(speed < 0 && downLimit.get() != Shooter.LIMIT_PRESSED) {
            shootRotatorY.set(speed);
        } else {
            shootRotatorY.set(0);
        }
    }

    @Override
    public void updateDash(boolean Override) {
        if(Override){
            double rotx = SmartDashboard.getNumber("PWMShootRotatorXspeed", 0);
            double roty = SmartDashboard.getNumber("PWMShootRotatorYspeed", 0);
            double stop = SmartDashboard.getNumber("CANShooterSpeedtop", 0);
            double sbot = SmartDashboard.getNumber("CANShooterSpeedbottom", 0);
            double sloader = SmartDashboard.getNumber("PWMShotLoaderSpeed", 0);
            if(rotx != shootRotatorX.get())
                rotateShooter(rotx, roty);
            else if(roty != shootRotatorY.get())
                rotateShooter(rotx, roty);
            if(sloader != shotLoader.get())
                shotLoader.setSpeed(sloader);
            if(stop != topMotor.get())
                setShooterSpeed(stop, sbot);
            else if(sbot != bottomMotor.get())
                setShooterSpeed(stop, sbot);
        }
    }

    @Override
    public void putValues() {
        SmartDashboard.putNumber("PWMShootRotatorXspeed", 0);
        
        SmartDashboard.putNumber("PWMShootRotatorYspeed", 0);
        SmartDashboard.putNumber("PWMShotLoaderSpeed", 0);
        SmartDashboard.putNumber("CANShooterSpeedtop", 0);
        SmartDashboard.putNumber("CANShooterSpeedbottom", 0);
    }

}
