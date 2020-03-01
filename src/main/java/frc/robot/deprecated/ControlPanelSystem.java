// // RobotBuilder Version: 2.0
// //
// // This file was generated by RobotBuilder. It contains sections of
// // code that are automatically generated and assigned by robotbuilder.
// // These sections will be updated in the future when you export to
// // Java from RobotBuilder. Do not put any code or make any change in
// // the blocks indicating autogenerated code or it will be lost on an
// // update. Deleting the comments indicating the section will prevent
// // it from being updated in the future.

// package frc.robot.deprecated;

// //import frc.robot.commands.*;
// //import edu.wpi.first.wpilibj.livewindow.LiveWindow;
// import edu.wpi.first.wpilibj2.command.SubsystemBase;
// import frc.robot.Constants.ControlPanel;
// //import edu.wpi.first.wpilibj.PIDOutput;
// //import edu.wpi.first.wpilibj.PIDSource;
// //import edu.wpi.first.wpilibj.AnalogInput;
// import edu.wpi.first.wpilibj.CounterBase.EncodingType;
// import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
// import edu.wpi.first.wpilibj.util.Color;
// import edu.wpi.first.wpilibj.Encoder;
// //import edu.wpi.first.wpilibj.PIDSourceType;
// import edu.wpi.first.wpilibj.Spark;
// //import edu.wpi.first.wpilibj.SpeedController;
// // why is this not working?
// import com.revrobotics.ColorSensorV3;
// import frc.robot.interfaces.scheduler;
// //import com.revrobotics.ColorSensorV3.RawColor;
// //import com.revrobotics.ColorMatch;
// //import com.revrobotics.ColorMatchResult;


// /**
//  *
//  */
// public class ControlPanelSystem extends SubsystemBase implements scheduler{

//     // private final ColorMatch matcher = new ColorMatch();

//     // public static Color BLUE = ColorMatch.makeColor(0.13489306, 0.43538037, 0.42972437);

//     // public static Color RED = ColorMatch.makeColor(0.48934227, 0.36309862, 0.14753516);

//     // public static Color GREEN = ColorMatch.makeColor(0.17530347, 0.5667771, 0.25793532);

//     // public static Color YELLOW = ColorMatch.makeColor(0.31467456, 0.5550923, 0.13020141);
//     // public static Color BETWEEN = ColorMatch.makeColor(0.2308, 0.5239, 0.2452);

//    
//     private Spark rotateMotor;
//     private Encoder rotateEncoder;
//     //private double testRotations;
//     private final ColorSensorV3 colorSensor = new ColorSensorV3(ControlPanel.COLORSENSOR_I2C);
//     // private Color prevColor;
//     // private String prevColorName;
//     private String expected;
//     private Color currentColor;
//     private String currentColorName;
//     private int consistentCount;
//     private int inconsistentCount;
//     private double th =0.1;

//     private double[] rexpected = {0.13489306, 0.43538037, 0.42972437};
//     private double[] gexpected = {0.31467456, 0.5550923, 0.13020141};
//     private double[] bexpected = {0.48934227, 0.36309862, 0.14753516};
//     private double[] yexpected = {0.17530347, 0.5667771, 0.25793532};

//     public ControlPanelSystem() {
//         // matcher.addColorMatch(BLUE);

//         // matcher.addColorMatch(RED);

//         // matcher.addColorMatch(GREEN);

//         // matcher.addColorMatch(YELLOW);

//         // matcher.addColorMatch(BETWEEN);

//         rotateMotor = new Spark(ControlPanel.PWMRotateControlPanelID);
//         // addChild("RotateMotor",rotateMotor);
//         rotateMotor.setInverted(false);
        
//         rotateEncoder = new Encoder(ControlPanel.RotateEncoderAID, ControlPanel.RotateEncoderBID, false, EncodingType.k4X);
//         // addChild("RotateEncoder",rotateEncoder);
//         rotateEncoder.setDistancePerPulse(1.0);

//         //rotateEncoder.setPIDSourceType(PIDSourceType.kRate);
//     }
//     // public String getSensorColor() {
//     //     // prevColor = currentColor;
//     //     // currentColor = colorSensor.getColor();
//     //     // return currentColor;
//     //     Color detected = colorSensor.getColor();
//     //     ColorMatchResult matched = matcher.matchClosestColor(detected);

//     //     if(matched.color == BLUE) {
//     //         return "Blue";
//     //     } else if(matched.color == RED) {
//     //         return "Red";
//     //     } else if(matched.color == GREEN) {
//     //         return "Green";
//     //     } else if (matched.color == YELLOW) {
//     //         return "Yellow";
//     //     }

//     //     return "Color Not Found";
//     // }
//     // public String getWheelSensorColor() {
//     //     // prevColor = currentColor;
//     //     // currentColor = colorSensor.getColor();
//     //     // return currentColor;
//     //     Color detected = colorSensor.getColor();
//     //     ColorMatchResult matched = matcher.matchClosestColor(detected);

//     //     if(matched.color == BLUE) {
//     //         return "Red";
//     //     } else if(matched.color == RED) {
//     //         return "Blue";
//     //     } else if(matched.color == GREEN) {
//     //         return "Yellow";
//     //     } else if (matched.color == YELLOW) {
//     //         return "Green";
//     //     }

//     //     return "Color Not Found";
//     // }

//     public void setExpected(String col){
//         expected = col;
//     }

//     public String getScannerColorName(){
//         th = SmartDashboard.getNumber("Color thresh hold", th);

//         //prevColorName = currentColorName;
//         String wheelColor = getWheelColorName();

//         if(wheelColor=="Red")
//             currentColorName = "Blue";
//         else if(wheelColor=="Yellow")
//             currentColorName = "Green";
//         else if(wheelColor=="Blue")
//             currentColorName = "Red";
//         else if(wheelColor=="Green")
//             currentColorName = "Yellow";

//         return currentColorName;
//     }
    
//     public String getWheelColorName(){
//         th = SmartDashboard.getNumber("Color thresh hold", th);

//         //prevColorName = currentColorName;
//         getColor();

//         double r = currentColor.red;
//         double g = currentColor.green;
//         double b = currentColor.blue;

//         if(Math.abs(rexpected[0] - r) <=th && Math.abs(rexpected[1]- g) <=th && Math.abs(rexpected[2] -b) <=th)
//             currentColorName = "Red";
//         else if(Math.abs(yexpected[0] - r) <=th && Math.abs(yexpected[1] - g) <=th && Math.abs(yexpected[2] -b) <=th)
//             currentColorName = "Yellow";
//         else if(Math.abs(bexpected[0] - r) <=th && Math.abs(bexpected[1] - g) <=th && Math.abs(bexpected[2] -b) <=th)
//             currentColorName = "Blue";
//         else if(Math.abs(gexpected[0] - r) <=th && Math.abs(gexpected[1] - g) <=th && Math.abs(gexpected[2] -b) <=th)
//             currentColorName = "Green";
//         else
//             currentColorName = "not found";
//         return currentColorName;
//     }

//     public Color getColor() {
//         //prevColor = currentColor;
//         currentColor = colorSensor.getColor();

//         return currentColor;
//     }

//     @Override
//     public void periodic() {
//         //Put code here to be run every loop
        
//     }

//     @Override
//     public void updateDash(boolean Override){  
//         // if(SmartDashboard.getString("currentColor", null) != currentColorName)
//         //     SmartDashboard.putString("currentColor", currentColorName);

//         if(Override){
//             rotateMotor.set(SmartDashboard.getNumber("PWMControlPanelRotate", 0));
//             rexpected = SmartDashboard.getNumberArray("RedExpected", rexpected);
//             gexpected = SmartDashboard.getNumberArray("Greenexpected", gexpected);
//             bexpected = SmartDashboard.getNumberArray("BlueExpected", bexpected);
//             yexpected = SmartDashboard.getNumberArray("YellowExpected", yexpected);
//         }
//     }

//     @Override
//     public void putValues() {
//         SmartDashboard.putNumber("PWMControlPanelRotate", 0);
//         SmartDashboard.putNumberArray("RedExpected", rexpected);
//         SmartDashboard.putNumberArray("GreenExpected", gexpected);
//         SmartDashboard.putNumberArray("BlueExpected", bexpected);
//         SmartDashboard.putNumberArray("YellowExpected", yexpected);
//     }
// }


