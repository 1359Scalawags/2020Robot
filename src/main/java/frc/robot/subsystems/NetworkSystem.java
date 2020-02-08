package frc.robot.subsystems;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
//import edu.wpi.first.wpilibj.TimedRobot;
//import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj2.command.SubsystemBase;;

public class NetworkSystem extends SubsystemBase {
    NetworkTableInstance inst;
    NetworkTable Distance; 
    NetworkTableEntry Distancevalue;

    public NetworkSystem(){
        inst = NetworkTableInstance.getDefault();

        Distance = inst.getTable("DistanceTable");

        // .getString("nothing");
        // Distancevalue.setString("awsomeness");
    }

    public String getDist(){
        Distancevalue = Distance.getEntry("value");
        String str = Distancevalue.getString("Doesn't exist");
        return str;
        
        // SmartDashboard.putString("Dist", str);
    }

    public void initDefaultCommand(){

    }

    // public double returnDistanceFromTarget(){
    //     return distanceFromTarget;
    // }

    // public float returnAngleFromTarget(){
    //     return angleAtTarget;
    // }

    // public void getTableValues(){
    //     xPercentage = (float)xEntry.getDouble(0);
    //     distanceFromTarget = distance.getDouble(0);
    //     angleAtTarget = (float)angle.getDouble(0);
    //     //System.out.println("##############################################"+xPercentage);
    //     //System.out.println("%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%"+distancevalue);
    // }

    //not needed in final build
    //public void setTablePeriodic(){
    //  xEntry.setDouble(x);
    //  distance.setDouble(distancevalue);
    //
    //  if (x <= 1000){
    //      x += 1; // random values
    //  } else {
    //      x = 0;
    //  }
    //
    //  if (distancevalue <= 1000){
    //      distancevalue += 1; // random values
    //  } else {
    //      distancevalue = 0;
    //  }
    //
    // }

}