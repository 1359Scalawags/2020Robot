// RobotBuilder Version: 2.0
//
// This file was generated by RobotBuilder. It contains sections of
// code that are automatically generated and assigned by robotbuilder.
// These sections will be updated in the future when you export to
// Java from RobotBuilder. Do not put any code or make any change in
// the blocks indicating autogenerated code or it will be lost on an
// update. Deleting the comments indicating the section will prevent
// it from being updated in the future.


package frc.robot.subsystems;


//import frc.robot.commands.*;
//import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
//import edu.wpi.first.wpilibj.PIDOutput;
//import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.CounterBase.EncodingType;
import edu.wpi.first.wpilibj.Encoder;
//import edu.wpi.first.wpilibj.PIDSourceType;
import edu.wpi.first.wpilibj.Spark;
//import edu.wpi.first.wpilibj.SpeedController;
import com.revrobotics.ColorSensorV3;
import com.revrobotics.ColorSensorV3.RawColor;


/**
 *
 */
public class ControlPanelSystem extends SubsystemBase {

    private Spark rotateMotor;
    private Encoder rotateEncoder;
    private AnalogInput colorSensor;
    //private double testRotations;

    public ControlPanelSystem() {
        rotateMotor = new Spark(Constants.RotatoPotatoID);
        addChild("RotateMotor",rotateMotor);
        rotateMotor.setInverted(false);
        
        rotateEncoder = new Encoder(Constants.RotateEncoderAID, Constants.RotateEncoderBID, false, EncodingType.k4X);
        addChild("RotateEncoder",rotateEncoder);
        rotateEncoder.setDistancePerPulse(1.0);
        //rotateEncoder.setPIDSourceType(PIDSourceType.kRate);
                
        colorSensor = new AnalogInput(Constants.ColorSensorID);
        addChild("ColorSensor",colorSensor);
    }

    public class ColorSystem extends SubsystemBase {

        private final ColorSensorV3 colorSensor = new ColorSensorV3(Constants.COLORSENSOR_I2C);
    
        private Color prevColor;
        private String prevColorName;
        private Color currentColor;
        private String currentColorName;
        private int consistentCount;
        private int inconsistentCount;
        private double th =0.1;
    
        public ColorSystem() {      
            SmartDashboard.putNumber("Color thresh hold", th);
        }

    public String getColorName(){
        th = SmartDashboard.getNumber("Color thresh hold", th);

        prevColorName = currentColorName;
        Color frc.robot.subsystems.ControlPanelSystem.ColorSystem.getColor();

        double r = currentColor.red;
        double g = currentColor.green;
        double b = currentColor.blue;

        if(Math.abs(0.13489306 - r) <=th && Math.abs(0.43538037- g) <=th && Math.abs(0.42972437 -b) <=th)
            currentColorName = "Blue";
        else if(Math.abs(0.17530347 - r) <=th && Math.abs(0.5667771 - g) <=th && Math.abs(0.25793532 -b) <=th)
            currentColorName = "Green";
        else if(Math.abs(0.48934227 - r) <=th && Math.abs(0.36309862 - g) <=th && Math.abs(0.14753516 -b) <=th)
            currentColorName = "Red";
        else if(Math.abs(0.31467456 - r) <=th && Math.abs(0.5550923 - g) <=th && Math.abs(0.13020141 -b) <=th)
            currentColorName = "Yellow";
        else
            currentColorName = "not found";
        return currentColorName;
    }

    public Color getColor() {
        prevColor = currentColor;
        currentColor = colorSensor.getColor();

        return currentColor;
    }

    @Override
    public void periodic() {
        //Put code here to be run every loop
    }

    public void testSpin(double speed) {
        rotateMotor.set(speed);
    }

    public void stopTest() {
        rotateMotor.set(0);
    }

    public void resetTestRotations() {
        rotateEncoder.reset();
    }

    public double getTestRotations() {
        return rotateEncoder.get();
    }

}
}

