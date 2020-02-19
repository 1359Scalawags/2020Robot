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

    public static final class Drive {
        public static final int CANBackLeftMotorID = 12;
        public static final int CANFrontLeftMotorID = 11;
        public static final int CANBackRightMotorID = 14;
        public static final int CANFrontRightMotorID = 13;
        public static final double maxMotorSpeed = 0.95;
        //public static final double fullDriveSpeed = 8;
        //public static int DriveLeftEncoderA = 6;
        //public static int DriveLeftEncoderB = 7;
        //public static int DriveRightEncoderA = 8;
        //public static int DriveRightEncoderB = 9;
        //public static int DriveDistanceUltrasonicA = 10;
        //public static int DriveDistanceUltrasonicB = 11;
        public static final double PID_P = 0.00001;
        public static final double PID_I = 0.000005;
        public static final double PID_D = 0.000001;
        public static final double PID_Iz = 0;
        public static final double PID_Ff = 0;
        public static final double StraightSpeed = -0.7;
        public static final double maxTurnRate = 0.75;
        public static final double maxRightTurnRate = 0.75;
        //public static final double maxLeftTurnRate = 0.75;
        public static final double AutoTurnAngleThreshold = 0.5;
        public static final double gyrokP =0.1;
        public static final double gyrokI =0.1;
        public static final double gyrokD =0.1;
        public static final double gyrokIz =0.1;
        public static final double gyrokFf =0.1;
        public static final double SystemScale =.01;
    }

    public static final class Shooter{
        //public static double MaxShooterSpeed = 1;
        public static final int PWMShootRotatorLeftRightID = 6;
        public static final int PWMShootRotatorUpDownID = 7;
        //public static int BallSpeedEncoderAID = 1;
        //public static int BallSpeedEncoderBID = 2;
        public static final double maxShooterTurnError = 0;
        public static final double maxShooterTurnRate = 1;
        public static final int CANTopBallMotorID = 3;
        public static final int CANBottomBallMotorID = 4;
        public static final double expectedDist =0;
    }

    public static final class Climb{
        public static final int CANClimbMotorID = 23;
        public static final int PWMRatchetServoID = 8;
        public static final double RatchetOpen = 1;
        public static final double RatchetClosed = 0;
        //public static int MaxHeightLimitID = 3;
        public static final int MinHeightLimitID = 4;
        public static final boolean LIMIT_PRESSED = true;
        public static final boolean LIMIT_NOTPRESSED = false;
        public static final double MAX_CLIMB_POSITION = 1;
        //public static double PotToInches = 35.0;
        //public static final double maxClimbSpeed = 1;
    }

    public static final class Load{
        // public static final double LoadShotMotor = 1;
        public static final int CANChamRotMotorID = 21; //TODO check if this id is correct?
        public static final int PWMLoadShotMotorID = 5;
        public static final int PWMLoadBallInMotorRearID = 0;
        public static final int PWMLoadBallInMotorFrontID = 4;
        public static final int PWMLoadBallUpMotorRightID = 1;
        public static final int PWMLoadBallUpMotorLeftID = 3;
        public static final int PWMLoadBallChamMotorID = 2;
        //public static double BallLoadUpSpeed = 0.001;
        //public static double BallLoadInSpeed = 0.001;
        //public static int BallLimitID = 0;
        //public static double BallPIDTolerance = 0d;
        public static final double maxChamberSpeed = 1;
    
    }

    public static final class ControlPanel{
        public static int PWMRotateControlPanelID = 9;                              
        public static int RotateEncoderAID = 20;
        public static int RotateEncoderBID = 21;
        public final static I2C.Port COLORSENSOR_I2C = I2C.Port.kOnboard;
    }

    public static final class Vision{
        public final static Link PixyLink = new I2CLink();
        public static final double SpeedTwordsBall = 0;
    }

    /*----------Operator Interface----------*/
    
    /***
     * ----------Dpad POV----------
     * 0 is UP
     * 45 is UP-RIGHT
     * 90 is RIGHT
     * 135 is DOWN-RIGHT
     * 180 is DOWN
     * 225 is DOWN-LEFT
     * 270 is LEFT
     * 315 is UP-LEFT
     * Returns to 0 going UP again
     * @return
     */
    
    public static int Abtn = 1;
    public static int Bbtn = 2;
    public static int Xbtn = 3;
    public static int Ybtn = 4;
    public static int LB = 5;
    public static int RB = 6;
    public static int SELECT = 7;
    public static int START = 8;
    public static int LEFTJoyBtn = 9;
    public static int RIGHTJoyBtn = 10;

    //TODO find our expectedDistance
    

    //TODO find maxShooterTurnRate
    


    /*
    public static int DpadUP = 0;
    public static int DpadRIGHT = 90;
    public static int DpadDOWN = 180;
    public static int DpadLEFT = 270;
    */
    
    /*----------BallSystem----------*/

    //Booleans, Integers, and Values
    //CAN Motors



    
    //PWM Motors                                        Motor #s 0, 1, 2, 3, 4, 5, 6, 7, 10, 14, 15
    

    

    
    //public static int DriveBuiltInAccelerometer = 10;
    //public static int DriveAnalogGyro = 11;

    public static final double controllerDeadZone = 0.1;
	public static double MAXRPM = 5700;

	
	
}
