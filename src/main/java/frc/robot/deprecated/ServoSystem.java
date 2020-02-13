package frc.robot.deprecated;

import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class ServoSystem extends SubsystemBase{
	Servo servo;

    public void ServoSystem(){
		servo = new Servo(0);
	}
	
	public void move(){
		// double val = SmartDashboard.getNumber("Servo Value", 0);
		double val =-20;
		servo.set(val);
	}
}