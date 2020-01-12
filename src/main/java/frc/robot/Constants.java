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
    public static Double BallPIDTolerance = 0d;
    
    /*----------BallSystem----------*/

    //Motors                                        Motor #s 0, 1, 2, 3, 4, 5, 6, 7,
    public static int TopBallMotorID = 1;
    public static int BottomBallMotorID = 2;
    public static int QueBallMotorID = 3;
    public static int TrackMotorAID = 4;
	public static int TrackMotorBID = 5;
    public static int IntakeMotorAID = 6;
	public static int IntakeMotorBID = 7;

    //Sensors                                       Sensor #s 0, 1, 2
    public static int BallLimitID = 0;
    public static int BallSpeedEncoderAID = 1;
    public static int BallSpeedEncoderBID = 2;
	
    /*----------ClimbSystem----------*/

    //Motors                                        Motor #s 8
    public static int ClimbMotorID = 8;

    //Sensors                                       Sensor #s 3, 4
    public static int MaxHeightLimitID = 3;
    public static int MinHeightLimitID = 4;

    //Booleans
    public static boolean LIMIT_PRESSED = true;
    public static boolean LIMIT_NOTPRESSED = false;
    
 
    /*----------ControlPanelSystem----------*/
    
    //Motors                                        Motor #s 9, 10, 11
    public static int RotatoPotatoID = 9;
    public static int RotateEncoderAID = 10;
    public static int RotateEncoderBID = 11;

    //Sensors                                       Sensor #s 5
    public static int ColorSensorID = 2;
	
    /*----------DriveSystem----------*/

    //Motors                                        Motor #s 12, 13, 14, 15
    public static int FrontLeftMotorID = 1;
    public static int BackLeftMotorID = 2;
    public static int FrontRightMotorID = 3;
    public static int BackRightMotorID = 4;

    //Sensors                                       Sensor #s 6, 7, 8, 9, 10, 11, 12, 13
    public static int DriveLeftEncoderA = 6;
    public static int DriveLeftEncoderB = 7;
    public static int DriveRightEncoderA = 8;
    public static int DriveRightEncoderB = 9;
    public static int DriveAnalogAccelerometer = 10;
    public static int DriveAnalogGyro = 11;
    public static int DriveDistanceUltrasonicA = 12;
    public static int DriveDistanceUltrasonicB = 13;


}
