package frc.robot.helper;

import java.util.ArrayList;
import frc.robot.interfaces.scheduler;

public class DashboardScheduler{
    ArrayList<scheduler> scheduled = new ArrayList<scheduler>();

    public void add(scheduler e){
        scheduled.add(e);
    }

    public void update(){
        for (scheduler subsystem : scheduled) {
            subsystem.updateDashboard();
        }
    }
}