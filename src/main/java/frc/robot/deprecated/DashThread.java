// package frc.robot.threading;

// import frc.robot.interfaces.scheduler;

// public class DashThread extends Thread {
//     scheduler subsystem;
//     boolean overriderState;
//     public DashThread(scheduler subsystem, boolean overriderState){
//         this.subsystem = subsystem;
//         this.overriderState = overriderState;
//     }
//     public void run(){
//         subsystem.updateDash(overriderState);
//     }
// }