/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

/**
 * The Constants class provides a convenient place for teams to hold robot-wide numerical or boolean
 * constants.  This class should not be used for any other purpose.  All constants should be
 * declared globally (i.e. public static).  Do not put anything functional in this class.
 *
 * <p>It is advised to statically import this class (or one of its inner classes) wherever the
 * constants are needed, to reduce verbosity.
 */
public final class Constants {
    public static Double ballPIDTolerance = 0d;

    /*----------ClimbSystem----------*/

    //Motors
    public static int ClimbMotorID = 8;

    //Sensors
    public static int MaxHeightLimitID = 8;
    public static int MinHeightLimitID = 9;

    //Booleans
    public static boolean LIMIT_PRESSED = true;
    public static boolean LIMIT_NOTPRESSED = false;
    
    /*----------DriveSystem----------*/

    //Motors
    public static int FrontLeftMotorID = 1;
    public static int BackLeftMotorID = 2;
    public static int FrontRightMotorID = 3;
    public static int BackRightMotorID = 4;

    //Sensors
    public static int DriveLeftEncoderA = 2;
    public static int DriveLeftEncoderB = 3;
    public static int DriveRightEncoderA = 4;
    public static int DriveRightEncoderB = 5;
    public static int DriveAnalogAccelerometer = 0;
    public static int DriveAnalogGyro = 1;
    public static int DriveDistanceUltrasonicA = 6;
    public static int DriveDistanceUltrasonicB = 7;

    /*----------BallSystem----------*/
    public static int TopBallMotorID = 5;
    public static int BottomBallMotorID = 6;
    public static int QueBallMotorID = 9;
    public static int TrackMotorAID = 7;
    public static int IntakeMotorAID = 0;
    
    /* v Maybe add Motor IDs here v */
    /*
    public static int TrackMotorBID = 7;
    public static int IntakeMotorBID = 0;
    */

    //Sensors
    public static int BallLimitID = 10;
    public static int BallSpeedEncoderAID = 0;
    public static int BallSpeedEncoderBID = 0;
 
    /*----------ControlPanelSystem----------*/
    
    //Motors
    public static int RotatoPotatoID = 11;
    public static int RotateEncoderAID = 12;
    public static int RotateEncoderBID = 13;

    //Sensors
    public static int ColorSensorID = 2;

    /*----------ADD SYSTEM HERE----------*/

}
