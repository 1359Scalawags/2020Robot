// RobotBuilder Version: 2.0
//
// This file was generated by RobotBuilder. It contains sections of
// code that are automatically generated and assigned by robotbuilder.
// These sections will be updated in the future when you export to
// Java from RobotBuilder. Do not put any code or make any change in
// the blocks indicating autogenerated code or it will be lost on an
// update. Deleting the comments indicating the section will prevent
// it from being updated in the future.

package frc.robot.deprecated;

//import frc.robot.commands.*;
//import edu.wpi.first.wpilibj2.command.PIDSubsystem;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
//import edu.wpi.first.wpilibj2.command.PIDSubsystem;
//import edu.wpi.first.wpilibj.PIDSource;
//import edu.wpi.first.wpilibj.PIDOutput;
//import edu.wpi.first.wpilibj.livewindow.LiveWindow;
//import edu.wpi.first.wpilibj.smartdashboard.SendableRegistry;
//BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=IMPORTS
import edu.wpi.first.wpilibj.CounterBase.EncodingType;
//import edu.wpi.first.wpilibj.controller.PIDController;
//import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Encoder;
//import edu.wpi.first.wpilibj.PIDSourceType;
import edu.wpi.first.wpilibj.Spark;
//import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.Talon;
import frc.robot.Constants;
//END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=IMPORTS
import frc.robot.helper.*;

/**
 *
 */
public class BallSystem extends SubsystemBase {

 
    private Encoder ballSpeedEncoder;

    //**chamRotator = Chamber Rotator

    private CanMotor ballMotorA;
    private CanMotor ballMotorB;
    /**
    * **The CHAMBER ROTATOR rotates the chamber
    */
    //private CanMotor chamRotator;
    
    /*
    private Spark ballMotorA;
    private Spark ballMotorB;
    private Spark queMotor;
    private SpeedControllerGroup ballShootMotors;
    private Spark trackMotorA;
    private Spark intakeMotorA;
    private Spark trackMotorB;
    private Spark intakeMotorB;
    */

    //private DigitalInput ballLimit;

    private Spark shootRotatorA;
    private Spark shootRotatorB;

    /*---------------Descriptions---------------*/
    /**
     * **The INTAKE at the bottom of the robot
     */
    private SpeedControllerGroup ballLoadInMotors;
     /**
     * **The UPTAKE into the CHAMBER LOADER
     */
    private SpeedControllerGroup ballLoadUpMotors;
    /**
    * **The CHAMBER LOADER puts balls into the 5 round chamber
    */
    /*------------------------------------------*/

    private Talon ballLoaderInA;
    private Talon ballLoaderInB;
    private Talon ballLoaderUpA;
    private Talon ballLoaderUpB;
    private Talon ballLoaderCham;
    private Spark shotLoader;

    //Initialize your subsystem here

    public BallSystem() {
        //chamRotator = new CanMotor(Constants.ChamRotMotorID);

        ballMotorA = new CanMotor(Constants.CANTopBallMotorID);
        
        ballMotorB = new CanMotor(Constants.CANBottomBallMotorID);
        
        //SendableRegistry.add(getController());//what is a parent? Do i need to add a child?
        //SendableRegistry.add(getController(), "controller");
        //super("BallSystem", 1.0, 0.0, 0.0); //super??
        //getPIDController().setContinuous(false);
        //getPIDController().setName("BallSystem", "PIDSubsystem Controller");
        //addChild(getPIDController());
        //addChild(getController());
        //END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=PID

        //BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTORS
        ballSpeedEncoder = new Encoder(Constants.BallSpeedEncoderAID, Constants.BallSpeedEncoderBID, false, EncodingType.k4X);
        //addChild("BallSpeedEncoder",ballSpeedEncoder);
        ballSpeedEncoder.setDistancePerPulse(1.0);
        //ballSpeedEncoder.setPIDSourceType(PIDSourceType.kRate);
                
        /*----------Shooter Management----------*/

        shootRotatorA = new Spark(Constants.PWMShootRotatorLeftRightID);
        shootRotatorA.setInverted(false);
        
        shootRotatorB = new Spark(Constants.PWMShootRotatorUpDownID);
        shootRotatorB.setInverted(true);
            
        /*----------Track Management----------*/ 

        //Ball Intake
        ballLoadInMotors = new SpeedControllerGroup(ballLoaderInA, ballLoaderInB);

        ballLoaderInA = new Talon(Constants.PWMLoadBallInMotorFrontID);
        //addChild("BallLoaderInA",ballLoaderInA);
        ballLoaderInA.setInverted(false);
        
        ballLoaderInB = new Talon(Constants.PWMLoadBallInMotorRearID);
        //addChild("BallLoaderInB",ballLoaderInB);
        ballLoaderInB.setInverted(false);



        //Ball Uptake
        ballLoadUpMotors = new SpeedControllerGroup(ballLoaderUpA, ballLoaderUpA); 
        
        ballLoaderUpA = new Talon(Constants.PWMLoadBallUpMotorRightID);
        //addChild("BallLoaderUpA",ballLoaderUpA);
        ballLoaderUpA.setInverted(false);
        
        ballLoaderUpB = new Talon(Constants.PWMLoadBallUpMotorLeftID);
        //addChild("BallLoaderUpB",ballLoaderUpB);
        ballLoaderUpB.setInverted(false);


        ballLoaderCham = new Talon(Constants.PWMLoadBallChamMotorID);
        //addChild("BallLoaderIntoChamber",ballLoaderCham);
        ballLoaderCham.setInverted(false);
        

        shotLoader = new Spark(Constants.PWMLoadShotMotorID);
        //addChild("ShooterLoader",shotLoader);
        shotLoader.setInverted(false);
        

        //ballLimit = new DigitalInput(Constants.BallLimitID);
        //addChild("BallLimit",ballLimit);

        //END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTORS

        //Use these to get going:
        //setSetpoint() -  Sets where the PID controller should move the system to
        //enable() - Enables the PID controller.
    }

    public Spark[] getRotateMotors(){
        Spark[] Motors = new Spark[2];
        Motors[0] = shootRotatorA;
        Motors[1] = shootRotatorB;
        
        return Motors;
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

    

    /**
     * Turns off all the loader motors
     */
    public void loaderOff(){
        this.ballLoadInMotors.set(0);
        this.ballLoadUpMotors.set(0);
        this.ballLoaderCham.set(0);
    }

    /**
     * Takes in balls and takes them up
     * @param speed Speed between 0 and 1
     */
    public void loaderIntakeAll(double speed){
        //Set speed of intake motor to be slower than chamber loader
        this.ballLoadInMotors.set(0.7 * speed);
        this.ballLoadUpMotors.set(0.8 * speed);
        this.ballLoaderCham.set(0.9 * speed);
    }

    /**
     * Makes sure balls do not enter the bot, stops all else
     * @param speed Speed between -1 and 0
     */
    public void keepBallsOut(double speed){
        this.ballLoadInMotors.set(0.9 * speed);
        this.ballLoadUpMotors.set(0);
        this.ballLoaderCham.set(0);
    } 


    /*
    public void generalLoading(double ballLoadUpSpd, double ballLoadInSpd){

        ballLoadInSpd = Constants.BallLoadInSpeed;
        ballLoadUpSpd = Constants.BallLoadUpSpeed;
    
        if(ballLoadInSpd <= 1, ballLoadUpSpd <= 1){
            ballLoadInSpd = ballLoadUpSpd
            ballLoadUpSpd = ballLoadUpSpd
        } else {
            ballLoadInSpd = 0
            ballLoadUpSpd = 0
        }
    
    }
    */

    /**
     * @param top Top shooter roller -1, 1
     * @param bottom Bottom shooter roller -1, 1
     */

    public void setShooterSpeed(double top, double bottom) {
        ballMotorA.setSpeed(top);
        ballMotorB.setSpeed(bottom);
    }

   /**
     * @param top Top laoder roller -1, 1
     * @param bottom Bottom loader roller -1, 1
     */

    public void setBallLoaderSpeed(double aMot, double bMot, double cMot, double dMot, double eMot) {
        ballLoaderUpA.set(aMot);
        ballLoaderUpB.set(bMot);
        ballLoaderInA.set(cMot);
        ballLoaderInB.set(dMot);
        ballLoaderCham.set(eMot);
    }

	public double getShooterPosition() {
		return shootRotatorA.getPosition();
	}

	public void rotateShooter(double speed) {
        if(speed > Constants.maxShooterTurnRate)
            speed=Constants.maxShooterTurnRate;
        else if(speed < -Constants.maxShooterTurnRate)
            speed=-Constants.maxShooterTurnRate;

        shootRotatorA.set(speed);
	}

}
