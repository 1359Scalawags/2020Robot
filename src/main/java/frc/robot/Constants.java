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

    public static int maxHeightLimitID = 8;
    public static int minHeightLimitID = 9;
    public static int climbMotorID = 8;
    public static int FrontLeftMotorID = 1;
    public static int BackLeftMotorID = 2;
    public static int FrontRightMotorID = 3;
    public static int
    //BallSystem Constant IDs
    public static int TopBallMotorID = 5;
    public static int BottomBallMotorID = 6;
    public static int QueBallMotorID = 9;
    public static int TrackMotorID = 7;
    public static int IntakeMotorID = 0;
    public static int BallLimitID = 10;
 
    //ControlPanelSystem Constant IDs
    public static int RotatoPotatoID = 11;
    public static int RotateEncoderAID = 12;
    public static int RotateEncoderBID = 13;
    public static int ColorSensorID = 2;

}
