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

    private Encoder ballSpeedEncoder;

    //**chamRotator = Chamber Rotator

    /**
    * **The CHAMBER ROTATOR rotates the chamber
    */
    private CanMotor chamRotator;
    
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

    private DigitalInput ballLimit;


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

    public LoadingSystem() {
        chamRotator = new CanMotor(Constants.ChamRotMotorID);

        
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

            
        /*----------Track Management----------*/ 

        //Ball Intake
        ballLoadInMotors = new SpeedControllerGroup(ballLoaderInA, ballLoaderInB);

        ballLoaderInA = new Talon(Constants.LoadBallInMotorAID);
        //addChild("BallLoaderInA",ballLoaderInA);
        ballLoaderInA.setInverted(false);
        
        ballLoaderInB = new Talon(Constants.LoadBallInMotorBID);
        //addChild("BallLoaderInB",ballLoaderInB);
        ballLoaderInB.setInverted(false);



        //TODO Many problems that I see below

        //Ball Uptake
        ballLoadUpMotors = new SpeedControllerGroup(ballLoaderUpA, ballLoaderUpA); 
        
        ballLoaderUpA = new Talon(Constants.LoadBallUpMotorAID);
        //addChild("BallLoaderUpA",ballLoaderUpA);
        ballLoaderUpA.setInverted(false);
        
        ballLoaderUpB = new Talon(Constants.LoadBallUpMotorBID);
        //addChild("BallLoaderUpB",ballLoaderUpB);
        ballLoaderUpB.setInverted(false);


        ballLoaderCham = new Talon(Constants.LoadBallChamMotorID);
        //addChild("BallLoaderIntoChamber",ballLoaderCham);
        ballLoaderCham.setInverted(false);
        

        shotLoader = new Spark(Constants.LoadShotMotorID);
        //addChild("ShooterLoader",shotLoader);
        shotLoader.setInverted(false);
        

        //ballLimit = new DigitalInput(Constants.BallLimitID);
        //addChild("BallLimit",ballLimit);

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

    

    /**
     * Turns off all the loader motors
     */
    public void loaderOff(){
        this.ballLoadInMotors.set(0);
        this.ballLoadUpMotors.set(0);
        this.ballLoaderCham.set(0);
    }

    /**
     * Takes in balls and puts them into chamber
     * @param speed Speed between 0 and 1
     */
    public void loaderIntakeAll(double speed){
        //Set speed of intake motor(s) to be slower than chamber loader
        this.ballLoadInMotors.set(0.7 * speed);
        this.ballLoadUpMotors.set(0.8 * speed);
        this.ballLoaderCham.set(0.9 * speed);
    }

    /**
     * Makes sure balls do not enter the bot, STOPS all else
     * @param speed Speed between -1 and 0
     */
    public void rejectBalls(double speed){
        this.ballLoadInMotors.set(0.9 * speed);
        this.ballLoadUpMotors.set(0);
        this.ballLoaderCham.set(0);
    } 

    //TODO Write out code for enabling different funtions:
    //TODO loading, shooting, turning ON/OFF, or reversing

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
     * @param top Top loader roller -1, 1
     * @param bottom Bottom loader roller -1, 1
     */

    public void setBallLoaderSpeed(double aMot, double bMot, double cMot, double dMot, double eMot) {
        ballLoaderUpA.set(aMot);
        ballLoaderUpB.set(bMot);
        ballLoaderInA.set(cMot);
        ballLoaderInB.set(dMot);
        ballLoaderCham.set(eMot);
    }

}
