package frc.robot.subsystems;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj2.command.SubsystemBase;;

public class NetworkSystem extends SubsystemBase {
    NetworkTableInstance inst;

    NetworkTable Distance; 
    NetworkTable Angle;

    NetworkTableEntry Distancevalue;
    NetworkTableEntry AngleValue;

    public NetworkSystem(){
        inst = NetworkTableInstance.getDefault();

        Distance = inst.getTable("DistanceTable");
        Angle = inst.getTable("AngleTable");
    }

    public double getDist(){
        Distancevalue = Distance.getEntry("value");
        double value = Distancevalue.getDouble(-1);
        return value;
    }

    public double getAngle(){
        AngleValue = Angle.getEntry("Value");
        double angle = AngleValue.getDouble(-1);
        return angle;
    }

    public void initDefaultCommand(){

    }
}