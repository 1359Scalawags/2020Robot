package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.PWMSparkMax;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.Talon;
import frc.robot.Constants.Load;
import frc.robot.sendable.PIDSparkMax;
import frc.robot.interfaces.scheduler;

/**
 *
 */
public class LoadingSystem extends SubsystemBase implements scheduler{

    // private Talon ballLoaderInA;
    private Talon ballLoaderUp;
    private PIDSparkMax ballLoaderInFront;
    private Talon ballLoaderInRear;
        /**
    * **The CHAMBER ROTATOR rotates the chamber
    */
    private PIDSparkMax chamRotator;
    /**
    * **The ballLoadInMotors INTAKE from the bottom of the robot to the uptake
    */
    private SpeedControllerGroup ballLoadInMotors;

    private DigitalInput postShooterSensor;
    private int postShooterArraySlot = 3;
    private DigitalInput preLoadSensor;
    /**
    * **The BALL SLOTS are on the inside of the chamber
    */
    private boolean ballSlots[];


    /**
     * Index sensors show position of chamber rotation.
     */
    // private DigitalInput[] indexSensors;

    private DigitalInput fullRotation;
    


    //(value += x) and (value = value + x) are the same


    //TODO: when the new indexer is hit, reset the encoder for chamber rotator
    
    public boolean isFullRotation() {
        return (fullRotation.get() == Load.LIMIT_PRESSED);
    }

    public void setFullRotation(double rotationAngle) {
        if (Load.LIMIT_PRESSED){
            rotationAngle = 0;
        } else {

        }
    }

    // private int getSensorValue() {
    //     int value = 0;
    //     if(indexSensors[1].get()){
    //         value += 8;
    //     }
    //     if(indexSensors[2].get()){
    //         value += 4;
    //     }
    //     if(indexSensors[3].get()){
    //         value += 2;
    //     }
    //     if(indexSensors[4].get()){
    //         value += 1;
    //     }
    //     return value;
    // }

    public LoadingSystem() {

        ballSlots = new boolean[5];
        ballSlots[0] = false;
        ballSlots[1] = false;
        ballSlots[2] = false;
        ballSlots[3] = false;
        ballSlots[4] = false;

        fullRotation = new DigitalInput(Load.fullRotationLimitID);
        addChild("FullRotationLimit", fullRotation);

        preLoadSensor = new DigitalInput(Load.preLoadSensor);
        addChild("PreLoadSensor", preLoadSensor);
        postShooterSensor = new DigitalInput(Load.postShootSensor);
        addChild("PostShooterSensor", postShooterSensor);

        chamRotator = new PIDSparkMax(Load.CANChamRotatorMotorID);
        addChild("ChamberRotater", chamRotator);
        chamRotator.setInverted(true);
        
        ballLoaderUp = new Talon(Load.TalonUpperBallLoad);
        addChild("BallLoaderUpA", ballLoaderUp);
        ballLoaderUp.setInverted(false);

        //TODO: Fix ALL motor IDs
        ballLoaderInFront = new PIDSparkMax(Load.CANLowerBallLoadFrontID);
        addChild("LowerBallLoadA", ballLoaderInFront);

        ballLoaderInRear = new Talon(Load.TalonLowerBallLoadRearID);
        addChild("LowerBallLoadB", ballLoaderInRear);
        
        ballLoadInMotors = new SpeedControllerGroup(ballLoaderUp, ballLoaderInRear);
        
        // ballLoaderInA = new Talon(Load.PWMLowerBallLoad);
        // ballLoaderInA.setInverted(false);

        // indexSensors = new DigitalInput[5];
        // indexSensors[0] = new DigitalInput(Load.LoadSensorA);
        // addChild("IndexSensor0", preLoadSensor);
        // indexSensors[1] = new DigitalInput(Load.LoadSensorB);
        // addChild("IndexSensor1", preLoadSensor);
        // indexSensors[2] = new DigitalInput(Load.LoadSensorC);
        // addChild("DigitalInput2", preLoadSensor);
        // indexSensors[3] = new DigitalInput(Load.LoadSensorD);
        // addChild("DigitalInput3", preLoadSensor);
        // indexSensors[4] = new DigitalInput(Load.LoadSensorE);
        // addChild("DigitalInput4", preLoadSensor);

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
        this.ballLoaderInFront.set(speed);
    }

    public boolean isLoadingChamber() {
        return (this.ballLoadInMotors.get() != 0);
    }
     
	public boolean rotateChamber(double rotatorSpeed) {
        if(!isLoadingChamber()) {
            chamRotator.set(rotatorSpeed*Load.maxChamberSpeed); 
            return true;
        }
        return false;
    }

    public boolean isRotating() {
        return (chamRotator.get() != 0);
    }

    public boolean[] getBallSlots(){
        return new boolean[] {
            ballSlots[0], 
            ballSlots[1], 
            ballSlots[2], 
            ballSlots[3], 
            ballSlots[4]
        };
    }

    public void updateUpperChamberSlot() {
        ballSlots[postShooterArraySlot] = this.postShooterSensor.get();
    }

    public boolean isBallPreloading() {
        return preLoadSensor.get();
    }
    
    public void advanceBallSlots() {
        boolean tempLast = ballSlots[4];
        ballSlots[4] = ballSlots[3];
        ballSlots[3] = ballSlots[2];
        ballSlots[2] = ballSlots[1];
        ballSlots[1] = ballSlots[0];
        ballSlots[0] = tempLast;
    }

    @Override
    public void updateDash(boolean Override){    
    //     double chamSpeed = SmartDashboard.getNumber("CANChamberRotatorSpeed", 0);
    //     if(Override){
    //         //double[] pid = SmartDashboard.getNumberArray("ChamberRotatorPID", new double[1]);
    //         //double loadup = SmartDashboard.getNumber("PWMBallLoadUpMotors", 0);
    //         //double loadcham = SmartDashboard.getNumber("PWMBallLoadChamber", 0);

    //         // if(!chamRotator.getPID().equals(pid))
    //         //     chamRotator.setPID(pid);
    //         // if(loadup != ballLoadUpMotors.get())
    //         //     ballLoadUpMotors.set(loadup);
    //         // if(loadcham != ballLoaderCham.get())
    //         //     ballLoaderCham.set(loadcham);
            
    //         double loadin = SmartDashboard.getNumber("PWMBallLoadinMotors", 0);
    //         if(chamSpeed != chamRotator.get())    
    //             chamRotator.set(chamSpeed);
    //         if(loadin != ballLoadInMotors.get())
    //             ballLoadInMotors.set(loadin);
    //     }
    //     else{
    //         if(chamSpeed == chamRotator.get())
    //             SmartDashboard.putNumber("CANChamberRotatorSpeed", chamRotator.get());
    //     }
    }

    @Override
    public void putValues() {
    //     // SmartDashboard.putNumberArray("ChamberRotatorPID", chamRotator.getPID().toArray());
    //     SmartDashboard.putNumber("CANChamberRotatorSpeed", 0);
    //     SmartDashboard.putNumber("PWMBallLoadChamber", 0);
    //     SmartDashboard.putNumber("PWMBallLoadinMotors", 0);
    //     //SmartDashboard.putNumber("PWMBallLoadUpMotors", 0);
    }

    // public void setLoadUpMotors(double speed) {
    //     this.ballLoadUpMotors.set(speed);
    // }

    // public void setChamberLoadMotor(double speed) {
    //     this.ballLoaderCham.set(speed);
    // }

    /**
     * Tell the chamber that it has a ball loaded in the lowest slot.
     */
    // public void setBallLoaded() {
    //     if(this.isLoaderIndexed()) {
    //         int slot = getSensorValue() / 2;
    //         ballSlots[slot] = true;
    //     }
    // }

    /**
     * Tell the chamber that a ball was removed at the shooter.
     */
    // public void setBallShot() {
    //     if(this.isShooterIndexed()) {
    //         int slot = getSensorValue() + 5;
    //         slot %= 10;
    //         ballSlots[slot] = false;
    //     }
    // }

    // public boolean getIndex() {
    //     return indexer.get();
    // }

    // @Deprecated
    // public boolean isAtIndex() {
    //     return indexSensors[0].get();
    // }
    
    /**
    * **Lines up the index to allow shooting
    */
    // public boolean isShooterIndexed() {
    //     int v = getSensorValue();
    //     if(v==9 || v==7 || v==5 || v==3 || v==1){
    //         return true;
    //     }
    //     return false;
    // }

    /**
    * **Lines up the index to allow loading
    */
    // public boolean isLoaderIndexed() {
    //     int v = getSensorValue();
    //     if(v==8 || v==6 || v==4 || v==2 || v==0){
    //         return true;
    //     }
    //     return false;
    // }


}