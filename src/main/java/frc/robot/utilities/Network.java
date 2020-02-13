package frc.robot.utilities;
import edu.wpi.first.networktables.*;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Network {
    NetworkTableInstance inst;
    NetworkTable Distance; 
    NetworkTableEntry Distancevalue;

    public Network(){
        inst = NetworkTableInstance.getDefault();
        Distance = inst.getTable("DistanceTable");
        TableEntryListener tb = new TableEntryListener(){
        
            @Override
            public void valueChanged(NetworkTable table, String key, NetworkTableEntry entry, NetworkTableValue value, int flags) {
                // TODO Auto-generated method stub
                SmartDashboard.putString("key", value.getString());
            }
        };

        Distance.addEntryListener("value", tb, tb.kFlags);
    }

    public void orient(){
        
    }

    public double getDist(){
        Distancevalue = Distance.getEntry("value");
        double value = Distancevalue.getDouble(-1);
        return value;
    }
}