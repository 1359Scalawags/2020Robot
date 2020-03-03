package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.DigitalInput;
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
    private Talon ballLoaderUpA;
    private PIDSparkMax[] ballLoaderIn;

    private DigitalInput postShooterSensor;
    private int postShooterArraySlot = 3;
    private DigitalInput preLoadSensor;
    /**
    * **The BALL SLOTS are on the inside of the chamber
    */
    private boolean ballSlots[];

    /**
    * **The CHAMBER ROTATOR rotates the chamber
    */
    private Talon chamRotator;
    /**
     * Index sensors show position of chamber rotation.
     */
    private DigitalInput[] indexSensors;
    
    /**
    * **The INTAKE at the bottom of the robot
    */
    private SpeedControllerGroup ballLoadInMotors;

    //(value += x) and (value = value + x) are the same

    private int getSensorValue() {
        int value = 0;
        if(indexSensors[1].get()){
            value += 8;
        }
        if(indexSensors[2].get()){
            value += 4;
        }
        if(indexSensors[3].get()){
            value += 2;
        }
        if(indexSensors[4].get()){
            value += 1;
        }
        return value;
    }

    public LoadingSystem() {

        indexSensors = new DigitalInput[5];
        indexSensors[0] = new DigitalInput(Load.LoadSensorA);
        indexSensors[1] = new DigitalInput(Load.LoadSensorB);
        indexSensors[2] = new DigitalInput(Load.LoadSensorC);
        indexSensors[3] = new DigitalInput(Load.LoadSensorD);
        indexSensors[4] = new DigitalInput(Load.LoadSensorE);

        ballSlots = new boolean[5];
        ballSlots[0] = false;
        ballSlots[1] = false;
        ballSlots[2] = false;
        ballSlots[3] = false;
        ballSlots[4] = false;

        preLoadSensor = new DigitalInput(Load.preLoadSenor);
        postShooterSensor = new DigitalInput(Load.postShootSensor);

        chamRotator = new Talon(Load.PWMChamRotMotorID);
        chamRotator.setInverted(true);

        
        ballLoaderUpA = new Talon(Load.PWMUpperBallLoad);
        ballLoaderUpA.setInverted(false);

        ballLoaderIn = new PIDSparkMax[2];

        ballLoaderIn[0] = new PIDSparkMax(Load.CANLowerBallLoadA);
        ballLoaderIn[1] = new PIDSparkMax(Load.CANLowerBallLoadB);
        // ballLoaderInA = new Talon(Load.PWMLowerBallLoad);
        // ballLoaderInA.setInverted(false);
        
        ballLoadInMotors = new SpeedControllerGroup(ballLoaderUpA, ballLoaderIn);
        
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

    // public void setLoadUpMotors(double speed) {
    //     this.ballLoadUpMotors.set(speed);
    // }

    // public void setChamberLoadMotor(double speed) {
    //     this.ballLoaderCham.set(speed);
    // }

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

    // public boolean getIndex() {
    //     return indexer.get();
    // }

    @Deprecated
    public boolean isAtIndex() {
        return indexSensors[0].get();
    }
    
    /**
    * **Lines up the index to allow shooting
    */
    public boolean isShooterIndexed() {
        int v = getSensorValue();
        if(v==9 || v==7 || v==5 || v==3 || v==1){
            return true;
        }
        return false;
    }

    /**
    * **Lines up the index to allow loading
    */
    public boolean isLoaderIndexed() {
        int v = getSensorValue();
        if(v==8 || v==6 || v==4 || v==2 || v==0){
            return true;
        }
        return false;
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

    /**
     * Tell the chamber that it has a ball loaded in the lowest slot.
     */
    public void setBallLoaded() {
        if(this.isLoaderIndexed()) {
            int slot = getSensorValue() / 2;
            ballSlots[slot] = true;
        }
    }

    /**
     * Tell the chamber that a ball was removed at the shooter.
     */
    public void setBallShot() {
        if(this.isShooterIndexed()) {
            int slot = getSensorValue() + 5;
            slot %= 10;
            ballSlots[slot] = false;
        }
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

    public void updateDash(boolean Override){    
        double chamSpeed = SmartDashboard.getNumber("CANChamberRotatorSpeed", 0);
        if(Override){
            //double[] pid = SmartDashboard.getNumberArray("ChamberRotatorPID", new double[1]);
            //double loadup = SmartDashboard.getNumber("PWMBallLoadUpMotors", 0);
            //double loadcham = SmartDashboard.getNumber("PWMBallLoadChamber", 0);

            // if(!chamRotator.getPID().equals(pid))
            //     chamRotator.setPID(pid);
            // if(loadup != ballLoadUpMotors.get())
            //     ballLoadUpMotors.set(loadup);
            // if(loadcham != ballLoaderCham.get())
            //     ballLoaderCham.set(loadcham);
            
            double loadin = SmartDashboard.getNumber("PWMBallLoadinMotors", 0);
            if(chamSpeed != chamRotator.getSpeed())
                chamRotator.set(chamSpeed);
            if(loadin != ballLoadInMotors.get())
                ballLoadInMotors.set(loadin);
        }
        else{
            if(chamSpeed == chamRotator.getSpeed())
                SmartDashboard.putNumber("CANChamberRotatorSpeed", chamRotator.getSpeed());
        }
    }

    @Override
    public void putValues() {
        // SmartDashboard.putNumberArray("ChamberRotatorPID", chamRotator.getPID().toArray());
        SmartDashboard.putNumber("CANChamberRotatorSpeed", 0);
        SmartDashboard.putNumber("PWMBallLoadChamber", 0);
        SmartDashboard.putNumber("PWMBallLoadinMotors", 0);
        //SmartDashboard.putNumber("PWMBallLoadUpMotors", 0);
    }


}