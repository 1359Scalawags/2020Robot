package frc.robot.subsystems;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class NetworkSystem extends SubsystemBase{
    NetworkTableInstance inst;

    NetworkTable NT;
    // NetworkTable Distance; 
    // NetworkTable Angle;

    NetworkTableEntry Distancevalue;
    NetworkTableEntry[] AngleValues;

    public NetworkSystem(){
        inst = NetworkTableInstance.getDefault();

        NT = inst.getTable("PI");

        // Distance = inst.getTable("DistanceTable");
        // Angle = inst.getTable("AngleTable");
        AngleValues = new NetworkTableEntry[2];
    }

    /**
     * 
     * @return Distance from target in network table
     */
    public double getDist(){
        Distancevalue = NT.getEntry("Distance");
        double value = Distancevalue.getDouble(-1);
        return value;
    }
    
    /**
     * 
     * @return X and Y angles from the network table in an array
     */
    public double[] getAngles(){
        double[] angles = new double[2];
        AngleValues[0] = NT.getEntry("XAngle");
        AngleValues[1] = NT.getEntry("YAngle");

        angles[0] = AngleValues[0].getDouble(-1);
        angles[1] = AngleValues[1].getDouble(-1);

        return angles;
    }

    public void initDefaultCommand(){

    }
}