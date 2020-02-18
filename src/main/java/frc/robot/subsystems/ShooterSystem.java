package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Constants.Shooter;
import frc.robot.Constants.Load;
import frc.robot.helper.*;

import frc.robot.interfaces.scheduler;
/**
 *
 */
public class ShooterSystem extends SubsystemBase implements scheduler{

    
    private CanMotor topMotor;
    private CanMotor bottomMotor;
    private Spark shootRotatorX;
    private Spark shootRotatorY;
    private Spark shotLoader;

    public ShooterSystem(){
        topMotor = new CanMotor(Shooter.CANTopBallMotorID);
        bottomMotor = new CanMotor(Shooter.CANBottomBallMotorID);
        
        shootRotatorX = new Spark(Shooter.PWMShootRotatorLeftRightID);
        shootRotatorX.setInverted(false);

        shootRotatorY = new Spark(Shooter.PWMShootRotatorUpDownID);
        shootRotatorY.setInverted(true);
        
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
        shootRotatorX.set(speed1);
        shootRotatorY.set(speed2);
	}

    @Override
    public void updateDash(boolean Override) {
        if(Override){
            double rotx = SmartDashboard.getNumber("ShootRotatorXspeed", 0);
            double roty = SmartDashboard.getNumber("ShootRotatorYspeed", 0);
            double sloader = SmartDashboard.getNumber("ShotLoaderSpeed", 0);
            double stop = SmartDashboard.getNumber("ShooterSpeedtop", 0);
            double sbot = SmartDashboard.getNumber("ShooterSpeedbottom", 0);
            if(rotx != shootRotatorX.get())
                shootRotatorX.set(rotx);
            if(roty != shootRotatorY.get())
                shootRotatorY.set(roty);
            if(sloader != shotLoader.get())
                shotLoader.setSpeed(sloader);
            if(stop != topMotor.get())
                topMotor.set(stop);
            if(sbot != bottomMotor.get())
                bottomMotor.set(sbot);
        }
    }

    @Override
    public void putValues() {
        SmartDashboard.putNumber("ShootRotatorXspeed", 0);
        SmartDashboard.putNumber("ShootRotatorYspeed", 0);
        SmartDashboard.putNumber("ShotLoaderSpeed", 0);
        SmartDashboard.putNumber("ShooterSpeedtop", 0);
        SmartDashboard.putNumber("ShooterSpeedbottom", 0);
    }


}
