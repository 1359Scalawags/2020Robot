// package frc.robot.helper;

// import java.util.ArrayList;

// import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
// import frc.robot.interfaces.scheduler;
// import frc.robot.threading.DashThread;

// public class DashboardScheduler{
//     ArrayList<scheduler> scheduled = new ArrayList<scheduler>();
//     private int frameCount;
//     private boolean overriderState;

//     public DashboardScheduler() {
//         overriderState = false;

//         frameCount = 0;
//     }

//     public void add(scheduler e){
//         scheduled.add(e);
//     }

//     public void update(){
//         if(frameCount >= 10) {
//             overriderState = SmartDashboard.getBoolean("OverRideState", false);
//             for (scheduler subsystem : scheduled) {
//                 // new DashThread(subsystem, overriderState).start();// 15        
//                 subsystem.updateDash(overriderState);//15
//             }  
//             frameCount = 0;  // TODO: move this before the above FOR loop, if issues arise
//         }
//         frameCount++;
//     }

//     public void putValues(){
//         SmartDashboard.putBoolean("OverRideState", false);

//         for (scheduler subsystem : scheduled) {
//             subsystem.putValues();
//         }
//     }
// }