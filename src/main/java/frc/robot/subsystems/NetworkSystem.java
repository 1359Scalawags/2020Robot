package frc.robot.subsystems;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj2.command.SubsystemBase;;

public class NetworkSystem extends SubsystemBase {
    NetworkTableInstance inst;
    NetworkTable Distance; 
    NetworkTableEntry Distancevalue;

    public NetworkSystem(){
        inst = NetworkTableInstance.getDefault();

        Distance = inst.getTable("DistanceTable");
    }

    public double getDist(){
        Distancevalue = Distance.getEntry("value");
        double value = Distancevalue.getDouble(-1);
        return value;
    }

    public void initDefaultCommand(){

    }
}