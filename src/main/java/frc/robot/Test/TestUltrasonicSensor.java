package frc.robot.Test;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.PWMVictorSPX;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Robot;
import frc.robot.Constants.Drive;
import frc.robot.Constants;

public class TestUltrasonicSensor extends CommandBase{
    private static final double kHoldDistance = 12.0;

    // factor to convert sensor values to a distance in inches
    private static final double kValueToInches = 0.125;
    private static final int kUltrasonicPort = 0;
    private static boolean state = false;

    private final AnalogInput m_ultrasonic = new AnalogInput(kUltrasonicPort);

    public TestUltrasonicSensor(){
    }

    @Override
    public void execute(){
        double result=0;
        for(int i=0; i<50; i++){
            // sensor returns a value from 0-4095 that is scaled to inches
            double currentDistance = m_ultrasonic.getValue();

            // if(currentDistance != SmartDashboard.getNumber("currentDistance", -1))
            //     SmartDashboard.putNumber("CurrentDistance", currentDistance);
            result+=currentDistance;
        }
        result/=50;

        if(result != SmartDashboard.getNumber("CurrentDistanceRaw", -1))
            SmartDashboard.putNumber("CurrentDistanceRaw", result); 
            
        state = false;
    }

    // Make this return true when this Command no longer needs to run execute()
    @Override
    public boolean isFinished() {
        return state;
    }


    public void update(){
        // sensor returns a value from 0-4095 that is scaled to inches
        double currentDistance = m_ultrasonic.getValue() * kValueToInches;

        // convert distance error to a motor speed
        double currentSpeed = (kHoldDistance - currentDistance) * Drive.PID_P;

        // if(currentDistance != SmartDashboard.getNumber("currentDistance", -1))
        //     SmartDashboard.putNumber("CurrentDistance", currentDistance);
            
        if(currentDistance != SmartDashboard.getNumber("currentDistance", -1))
        SmartDashboard.putNumber("CurrentDistanceRaw", m_ultrasonic.getValue());
        
        // if(currentDistance != SmartDashboard.getNumber("currentDistance", -1))
        //     SmartDashboard.putNumber("currentVoltage", m_ultrasonic.getVoltage());
        // // drive robot
        // Robot.driveSystem.arcadeDrive(currentSpeed, 0);
    }
}