package frc.robot.helper;

import java.util.ArrayList;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.scheduler;

public class DashboardScheduler{
    ArrayList<scheduler> scheduled;

    public void add(scheduler e){
        scheduled.add(e);
    }

    public void update(){
        for (scheduler subsystem : scheduled) {
            subsystem.updateDashboard();
        }
    }
}