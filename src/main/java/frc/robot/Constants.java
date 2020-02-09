/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.I2C;
import io.github.pseudoresonance.pixy2api.links.I2CLink;
import io.github.pseudoresonance.pixy2api.links.Link;

/**
 * The Constants class provides a convenient place for teams to hold robot-wide numerical or boolean
 * constants.  This class should not be used for any other purpose.  All constants should be
 * declared globally (i.e. public static).  Do not put anything functional in this class.
 *
 * <p>It is advised to statically import this class (or one of its inner classes) wherever the
 * constants are needed, to reduce verbosity.
 */
public final class Constants {

    /*----------Operator Interface----------*/
    
        //TODO: ADD THIS IN
    /*
    public static int Abtn = x;
    public static int Bbtn = x;
    public static int Xbtn = x;
    public static int Ybtn = x;
    public static int DpadUP = x;
    public static int DpadDOWN = x;
    public static int DpadLEFT = x;
    public static int DpadRIGHT = x;
    public static int RT = x;
    public static int RB = x;
    public static int LT = x;
    public static int LB = x;
    public static int Joy1btn = x;
    public static int Joy2btn = x;
    */
    
    /*----------BallSystem----------*/

    //Booleans, Integers, and Values

    
    //Motors                                        Motor #s 0, 1, 2, 3, 4, 5, 6, 7, 10, 14, 15
    public static int TopBallMotorID = 1;
    public static int BottomBallMotorID = 2;
    public static int LoadBallInMotorAID = 3;
    public static int LoadBallInMotorBID = 4;
	public static int LoadBallUpMotorAID = 5;
    public static int LoadBallUpMotorBID = 6;
	public static int LoadBallChamMotorID = 7;
	public static int LoadShotMotorID = 14;
	public static int LoadChamMotorID = 15;
    public static int ChamRotMotorID = 10;

    //Motor Speeds
    public static double BallLoadUpSpeed = 0.001;
    public static double BallLoadInSpeed = 0.001;

    //Sensors                                       Sensor #s 0, 1, 2
    public static int BallLimitID = 0;
    public static int BallSpeedEncoderAID = 1;
    public static int BallSpeedEncoderBID = 2;

    //PID
    public static double BallPIDTolerance = 0d;
	
    /*----------ClimbSystem----------*/

    //Booleans, Integers, and Values


    //Motors                                        Motor #s 8
    public static int ClimbMotorID = 8;

    //Sensors                                       Sensor #s 3, 4
    public static int MaxHeightLimitID = 3;
    public static int MinHeightLimitID = 4;

    //Booleans
    public static boolean LIMIT_PRESSED = true;
    public static boolean LIMIT_NOTPRESSED = false;
    
    //Potentiometers
    public static  int CLIMBERPOTID = 8;    //Change if NEEDED...
                                            //IF it was changed then let Allan know ASAP!

    //Defined
    public static double PotToInches = 35.0;    //Change if NEEDED...  Pot = POTENTIOMETER
                                                //IF it was changed then let Allan know ASAP!

    /*----------ControlPanelSystem----------*/
    
    //Booleans, Integers, and Values


    //Motors                                        Motor #s 9
    public static int RotatoPotatoID = 9;

    //Sensors                              
    public static int RotateEncoderAID = 20;
    public static int RotateEncoderBID = 21;

    /*----------DriveSystem----------*/

    //Booleans, Integers, and Values
    public static final double maxMotorSpeed = 0.95;
    public static final double fullDriveSpeed = 8;

    //Motors                                        Motor #s 11, 12, 13, 14
    public static int FrontLeftMotorID = 11;
    public static int BackLeftMotorID = 12;
    public static int FrontRightMotorID = 13;
    public static int BackRightMotorID = 14;

    //Sensors                                       Sensor #s 6, 7, 8, 9, 10, 11, 12, 13
    public static int DriveLeftEncoderA = 6;
    public static int DriveLeftEncoderB = 7;
    public static int DriveRightEncoderA = 8;
    public static int DriveRightEncoderB = 9;
    //public static int DriveBuiltInAccelerometer = 10;
    //public static int DriveAnalogGyro = 11;
    public static int DriveDistanceUltrasonicA = 10;
    public static int DriveDistanceUltrasonicB = 11;

    //PID
    public static double drivePID_P;
    public static double drivePID_I;
    public static double drivePID_D;
    public static double drivePID_Iz = 0;
    public static double drivePID_Ff = 0;

    public static final double controllerDeadZone = 0.1;
    public static final double driveStraightSpeed = -0.7;
    public static final double maxTurnRate = 0.75;
    public static final double maxRightTurnRate = 0.75;
    public static final double maxLeftTurnRate = 0.75;
    public static final double mindistance = 0.5;


	public static double MAXRPM = 5700;


    public static double gyrokP =0;
    public static double gyrokI =0;
    public static double gyrokD =0;
    public static double gyrokIz =0;
    public static double gyrokFf =0;


    
    public final static I2C.Port COLORSENSOR_I2C = I2C.Port.kOnboard;
    public final static Link PixyLink = new I2CLink();
    // public final static Link PixyLink = new SPILink();
}
