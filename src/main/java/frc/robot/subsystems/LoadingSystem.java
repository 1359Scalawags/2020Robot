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
public class LoadingSystem extends SubsystemBase {

    private Talon ballLoaderInA;
    private Talon ballLoaderInB;
    private Talon ballLoaderUpA;
    private Talon ballLoaderUpB;

    /*---------------Descriptions---------------*/
    /**
    * **The CHAMBER LOADER puts balls into the 5 round chamber
    */
    private Talon ballLoaderCham;

    /**
    * **The INTAKE at the bottom of the robot
    */
    private SpeedControllerGroup ballLoadInMotors;

    /**
    * **The UPTAKE into the CHAMBER LOADER
    */
    private SpeedControllerGroup ballLoadUpMotors;

    /**
    * **The CHAMBER ROTATOR rotates the chamber
    */
    private CanMotor chamRotator;

    /*------------------------------------------*/

    private DigitalInput ballLimit;
    private DigitalInput indexer;

    //Initialize your subsystem here

    public LoadingSystem() {
        //TODO this is using default PID values!!!
        chamRotator = new CanMotor(Constants.CANChamRotMotorID);

        //SendableRegistry.add(getController());//what is a parent? Do i need to add a child?
        //SendableRegistry.add(getController(), "controller");
        //super("BallSystem", 1.0, 0.0, 0.0); //super??
        //getPIDController().setContinuous(false);
        //getPIDController().setName("BallSystem", "PIDSubsystem Controller");
        //addChild(getPIDController());
        //addChild(getController());
        //END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=PID

        ballLoaderInA = new Talon(Constants.PWMLoadBallInMotorFrontID);
        //addChild("BallLoaderInA",ballLoaderInA);
        ballLoaderInA.setInverted(false);
        
        ballLoaderInB = new Talon(Constants.PWMLoadBallInMotorRearID);
        //addChild("BallLoaderInB",ballLoaderInB);
        ballLoaderInB.setInverted(false);

        //Ball Intake

        ballLoadInMotors = new SpeedControllerGroup(ballLoaderInA, ballLoaderInB);

        ballLoaderUpA = new Talon(Constants.PWMLoadBallUpMotorRightID);
        //addChild("BallLoaderUpA",ballLoaderUpA);
        ballLoaderUpA.setInverted(false);
        
        ballLoaderUpB = new Talon(Constants.PWMLoadBallUpMotorLeftID);
        //addChild("BallLoaderUpB",ballLoaderUpB);
        ballLoaderUpB.setInverted(false);
        
        //Ball Uptake
        ballLoadUpMotors = new SpeedControllerGroup(ballLoaderUpA, ballLoaderUpB); 


        ballLoaderCham = new Talon(Constants.PWMLoadBallChamMotorID);
        //addChild("BallLoaderIntoChamber",ballLoaderCham);
        ballLoaderCham.setInverted(false);

        //ballLimit = new DigitalInput(Constants.BallLimitID);
        //END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTORS

        //Use these to get going:
        //setSetpoint() -  Sets where the PID controller should move the system to
        //enable() - Enables the PID controller.
    }

    /*
    @Override
    public double getMeasurement() {
        return ballSpeedEncoder.getRate();
    }
    */

    /*
    @Override
    public double returnPIDInput() {
        // Return your input value for the PID loop
        // e.g. a sensor, like a potentiometer:
        // yourPot.getAverageVoltage() / kYourMaxVoltage;
        // return ballSpeedEncoder.pidGet();
    }
    */

    /*
    @Override
    public void useOutput(double output, double setpoint) {
        // Use output to drive your system, like a motor
        // e.g. yourMotor.set(output);
    }
    */

    public void setLoadInMotors(double speed) {
        this.ballLoadInMotors.set(speed);
    }

    public void setLoadUpMotors(double speed) {
        this.ballLoadUpMotors.set(speed);
    }

    public void setChamberLoadMotor(double speed) {
        this.ballLoaderCham.set(speed);
    }

    public boolean isLoadingChamber() {
        return (this.ballLoaderCham.get() != 0);
    }

    public boolean isOff() {
        return (ballLoadInMotors.get() == 0 && ballLoadUpMotors.get() == 0 && ballLoaderCham.get() == 0);
    }
    
    public boolean isOn() {
        return (ballLoadInMotors.get() != 0 && ballLoadUpMotors.get() != 0 && ballLoaderCham.get() != 0);
    }

    //TODO Write out code for enabling different funtions:
    //TODO loading, shooting, turning ON/OFF, or reversing
     
	public boolean rotateChamber(double rotatorSpeed) {
        // if(!isLoadingChamber()) {
            //TODO SmartDashbord value to make sure its correct
            //TODO Check the chamRotator CAN ID
            chamRotator.setSpeed(rotatorSpeed*Constants.maxChamberSpeed); 
            // return true;
        // }
        return false;
    }

    public boolean isRotating() {
        return (chamRotator.Encoder().getVelocity() != 0);
    }

    public Boolean getIndex(){
        return indexer.get();
    }
    
//Some example code on creating a group(s)
/*
    public void setBallLoaderSpeed(double aMot, double bMot, double cMot, double dMot, double eMot, double inMots, double upMots) {
        ballLoaderUpA.set(aMot);
        ballLoaderUpB.set(bMot);
        //OR 
        ballLoadUpMotors.set(inMots)

        ballLoaderInA.set(cMot);
        ballLoaderInB.set(dMot);
        //OR
        ballLoadInMotors.set(upMots)

        ballLoaderCham.set(eMot);
    }
*/

}
