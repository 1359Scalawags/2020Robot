package frc.robot.helper;

import java.util.ArrayList;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.interfaces.scheduler;

public class DashboardScheduler{
    ArrayList<scheduler> scheduled = new ArrayList<scheduler>();
    private int frameCount;
    private boolean overriderState;

    public DashboardScheduler() {
        SmartDashboard.putBoolean("OverRideState", false);
        overriderState = false;

        frameCount = 0;
    }

    public void add(scheduler e){
        scheduled.add(e);
    }

    public void update(){
        if(frameCount == 10) {
            overriderState = SmartDashboard.getBoolean("OverRideState", false);
            for (scheduler subsystem : scheduled) {
                subsystem.updateDash(overriderState);
            }  
            frameCount = 0;  // TODO: move this before for loop if issues arise
        }
        frameCount++;
    }

    public void putValues(){
        for (scheduler subsystem : scheduled) {
            subsystem.putValues();
        }
    }
}