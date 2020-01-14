package frc.robot.subsystems;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
// import edu.wpi.first.wpilibj.TimedRobot;
//import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj2.command.SubsystemBase;;

public class NetworkSystem extends SubsystemBase {
    NetworkTableEntry xEntry;
    NetworkTableEntry distance;
    NetworkTableEntry angle;
    // not needed in final build
    //private double x;
   // private double distancevalue;
    // needed in final build
    float xPercentage;
    float angleAtTarget;
    double distanceFromTarget;
    
    public NetworkSystem(){
        NetworkTableInstance inst = NetworkTableInstance.getDefault();
        NetworkTable xTable = inst.getTable("XTable");
        NetworkTable targetDistanceTable = inst.getTable("DistanceTable");
        xEntry = xTable.getEntry("X");
        distance = targetDistanceTable.getEntry("DistanceFromTarget");
        angle = targetDistanceTable.getEntry("AngleFromTarget");
        //  x = 0; // random values
        // distanceFromTarget = 20; // random values
    }

    public void initDefaultCommand(){

    }

    public double returnDistanceFromTarget(){
        return distanceFromTarget;
    }

    public float returnAngleFromTarget(){
        return angleAtTarget;
    }

    public void getTableValues(){
        xPercentage = (float)xEntry.getDouble(0);
        distanceFromTarget = distance.getDouble(0);
        angleAtTarget = (float)angle.getDouble(0);
        // System.out.println("##############################################"+xPercentage);
        // System.out.println("%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%"+distancevalue);
    }

    // not needed in final build
    // public void setTablePeriodic(){
    //     xEntry.setDouble(x);
    //     distance.setDouble(distancevalue);
    //     if(x <= 1000){
    //         x += 1; // random values
    //     }
    //     else{
    //         x = 0;
    //     }
    //     if(distancevalue <= 1000){
    //         distancevalue += 1; // random values
    //     }
    //     else{
    //         distancevalue = 0;
    //     }
    // }

}