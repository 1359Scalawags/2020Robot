package frc.robot.helper;

import java.util.ArrayList;
import frc.robot.interfaces.scheduler;

public class DashboardScheduler{
    ArrayList<scheduler> scheduled = new ArrayList<scheduler>();
    private int frameCount;

    public DashboardScheduler() {
        frameCount = 0;
    }

    public void add(scheduler e){
        scheduled.add(e);
    }

    public void update(){
        if(frameCount == 10) {
            for (scheduler subsystem : scheduled) {
                subsystem.updateDashboard();
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