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
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.util.Color;
import edu.wpi.first.wpilibj.Encoder;
//import edu.wpi.first.wpilibj.PIDSourceType;
import edu.wpi.first.wpilibj.Spark;
//import edu.wpi.first.wpilibj.SpeedController;
// why is this not working?
import com.revrobotics.ColorSensorV3;
import com.revrobotics.ColorSensorV3.RawColor;
import com.revrobotics.ColorMatch;
import com.revrobotics.ColorMatchResult;


/**
 *
 */
public class ControlPanelSystem extends SubsystemBase {

    private final ColorMatch matcher = new ColorMatch();

    public static Color BLUE = ColorMatch.makeColor(0.13489306, 0.43538037, 0.42972437);

    public static Color RED = ColorMatch.makeColor(0.48934227, 0.36309862, 0.14753516);

    public static Color GREEN = ColorMatch.makeColor(0.17530347, 0.5667771, 0.25793532);

    public static Color YELLOW = ColorMatch.makeColor(0.31467456, 0.5550923, 0.13020141);

    // TODO: Is this a NEO or brushed motor? Probably brushed.
    private Spark rotateMotor;
    private Encoder rotateEncoder;
    //private double testRotations;
    private final ColorSensorV3 colorSensor = new ColorSensorV3(Constants.COLORSENSOR_I2C);
    private Color prevColor;
    private String prevColorName;
    private Color currentColor;
    private String currentColorName;
    private int consistentCount;
    private int inconsistentCount;
    private double th =0.1;

    public ControlPanelSystem() {
        matcher.addColorMatch(BLUE);

        matcher.addColorMatch(RED);

        matcher.addColorMatch(GREEN);

        matcher.addColorMatch(YELLOW);


        rotateMotor = new Spark(Constants.RotatoPotatoID);
        addChild("RotateMotor",rotateMotor);
        rotateMotor.setInverted(false);
        
        rotateEncoder = new Encoder(Constants.RotateEncoderAID, Constants.RotateEncoderBID, false, EncodingType.k4X);
        addChild("RotateEncoder",rotateEncoder);
        rotateEncoder.setDistancePerPulse(1.0);
        //rotateEncoder.setPIDSourceType(PIDSourceType.kRate);
                
        SmartDashboard.putString("Color Result", getWheelSensorColor());
    }

  
    public String getWheelSensorColor() {
        // prevColor = currentColor;
        // currentColor = colorSensor.getColor();
        // return currentColor;
        Color detected = colorSensor.getColor();
        ColorMatchResult matched = matcher.matchClosestColor(detected);

        if(matched.color == BLUE) {
            return "Red";
        } else if(matched.color == RED) {
            return "Blue";
        } else if(matched.color == GREEN) {
            return "Yellow";
        } else if (matched.color == YELLOW) {
            return "Green";
        }

        return "Color Not Found";
    }

    @Override
    public void periodic() {
        //Put code here to be run every loop
        
    }

}


