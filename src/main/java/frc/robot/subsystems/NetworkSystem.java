package frc.robot.subsystems;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class NetworkSystem extends SubsystemBase{
    NetworkTableInstance inst;

    NetworkTable Distance; 
    NetworkTable Angle;

    NetworkTableEntry Distancevalue;
    NetworkTableEntry[] AngleValues;

    public NetworkSystem(){
        inst = NetworkTableInstance.getDefault();

        Distance = inst.getTable("DistanceTable");
        Angle = inst.getTable("AngleTable");
        AngleValues = new NetworkTableEntry[2];
    }

    public double getDist(){
        Distancevalue = Distance.getEntry("value");
        double value = Distancevalue.getDouble(-1);
        return value;
    }

    public double[] getAngles(){
        double[] angles = new double[2];
        AngleValues[0] = Angle.getEntry("XValue");
        AngleValues[1] = Angle.getEntry("YValue");

        angles[0] = AngleValues[0].getDouble(-1);
        angles[1] = AngleValues[1].getDouble(-1);

        return angles;
    }

    public void initDefaultCommand(){

    }
}