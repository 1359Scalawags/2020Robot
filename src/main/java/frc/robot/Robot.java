// RobotBuilder Version: 2.0
//
// This file was generated by RobotBuilder. It contains sections of
// code that are automatically generated and assigned by robotbuilder.
// These sections will be updated in the future when you export to
// Java from RobotBuilder. Do not put any code or make any change in
// the blocks indicating autogenerated code or it will be lost on an
// update. Deleting the comments indicating the section will prevent
// it from being updated in the future.

package frc.robot;

import edu.wpi.first.hal.FRCNetComm.tInstances;
import edu.wpi.first.hal.FRCNetComm.tResourceType;
import edu.wpi.first.hal.HAL;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.commands.*;
import frc.robot.helper.DashboardScheduler;
import frc.robot.subsystems.*;
import frc.robot.commands.drive.*;
import frc.robot.commands.load.ManualChamberRotator;
import frc.robot.commands.autonomous.*;
import frc.robot.commands.shooter.*;

import frc.robot.Test.TestNetwork;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the TimedRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the build.properties file in the
 * project.
 */
public class Robot extends TimedRobot {

    
    public static CommandBase autonomousCommand;
    public static SendableChooser<CommandBase> chooser;

    public static OI oi;
    public static CANDriveSystem driveSystem;
    public static ClimbSystem climbSystem;
    public static ControlPanelSystem controlPanelSystem;
    public static LoadingSystem loadingSystem;
    public static ShooterSystem shooterSystem;
    public static NetworkSystem kNetwork;
    public static PixySystem pixy;
    public static DashboardScheduler dashboardScheduler;


    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS

        /**
         * This function is run when the robot is first started up and should be
         * used for any initialization code.
         */
    @Override
    public void robotInit() {

        chooser = new SendableChooser<CommandBase>();
        dashboardScheduler = new DashboardScheduler();
        System.out.println(">> Dashboard Scheduler Created");
        try {
            driveSystem = new CANDriveSystem(); //TODO when commenting this out it works perfectly, there is something wrong in the drive system!!!
            climbSystem = new ClimbSystem();
            loadingSystem = new LoadingSystem();
            controlPanelSystem = new ControlPanelSystem();
            shooterSystem = new ShooterSystem();
            kNetwork = new NetworkSystem();
            // pixy = new PixySystem(Constants.PixyLink);
            System.out.println(">> Subsystems Created");
        } catch (Exception ex) {
            System.out.println("!! An error occured while instantiating the subsystems !!");
            ex.printStackTrace();
        }

        try{
            
            // CommandScheduler.getInstance().registerSubsystem(pixy);
            // CommandScheduler.getInstance().setDefaultCommand(pixy, new PixyCommand());
        }
        catch(Exception ext){
            System.out.println(ext);
            ext.printStackTrace();
        }
        


        // OI must be constructed after subsystems. If the OI creates Commands
        //(which it very likely will), subsystems are not guaranteed to be
        // constructed yet. Thus, their requires() statements may grab null
        // pointers. Bad news. Don't move it.
        oi = new OI();

        
        try {
            CommandScheduler.getInstance().registerSubsystem(loadingSystem);
            CommandScheduler.getInstance().setDefaultCommand(loadingSystem, new ManualChamberRotator());

            // CommandScheduler.getInstance().registerSubsystem(driveSystem);
            // CommandScheduler.getInstance().setDefaultCommand(driveSystem, new ManualDrive());

            CommandScheduler.getInstance().registerSubsystem(kNetwork);
            CommandScheduler.getInstance().setDefaultCommand(kNetwork, new TestNetwork());
            System.out.println(">> Subsystems Registered and Default Commands Set.");
        } catch (Exception ex) {
            System.out.println("!! Unable to register subsystems and their default commands !!");
            ex.printStackTrace();
        }

        // CommandScheduler.getInstance().registerSubsystem(climbSystem);

        HAL.report(tResourceType.kResourceType_Framework, tInstances.kFramework_RobotBuilder);

        // Add commands to Autonomous Sendable Chooser

        // chooser.addOption("AutoDriveTurn", new AutoDriveTurn(90));
        // chooser.setDefaultOption("Autonomous Command", new AutonomousCommand());

        // SmartDashboard.putData("Auto mode", chooser);


        try {
            dashboardScheduler.add(shooterSystem);
            dashboardScheduler.add(loadingSystem);
            dashboardScheduler.add(controlPanelSystem);
            dashboardScheduler.add(climbSystem);
            dashboardScheduler.add(driveSystem);
            dashboardScheduler.putValues();
            System.out.println(">> Schedule updates to the Dashboard for subsystems");
        } catch (Exception ex) {
            System.out.println("!! Unable to add subsystems to Dashboard Scheduler !!");      
            ex.printStackTrace();
        }
        System.out.println("reaching the end of the world just fine");
     }

    @Override
    public void robotPeriodic(){
        // dashboardScheduler.update();  
        
    }

    /**
     * This function is called when the disabled button is hit.
     * You can use it to reset subsystems before shutting down.
     */
    @Override
    public void disabledInit(){
        CommandScheduler.getInstance().run();
    }

    @Override
    public void disabledPeriodic() {
        // CommandScheduler.getInstance().run();
    }

    @Override
    public void autonomousInit() {
        autonomousCommand = chooser.getSelected();
        // schedule the autonomous command (example)
        if (autonomousCommand != null) autonomousCommand.schedule();
    }

    /**
     * This function is called periodically during autonomous
     */
    @Override
    public void autonomousPeriodic() {
        dashboardScheduler.update();//TODO thjihngng!!!
        CommandScheduler.getInstance().run();
    }

    @Override
    public void teleopInit() {
        // This makes sure that the autonomous stops running when
        // teleop starts running. If you want the autonomous to
        // continue until interrupted by another command, remove
        // this line or comment it out.
        if (autonomousCommand != null) autonomousCommand.cancel();
    }

    /**
     * This function is called periodically during operator control
     */
    @Override
    public void teleopPeriodic() {
        dashboardScheduler.update();
        // CommandScheduler.getInstance().run();
    }

    @Override
    public void testInit() {
        // This makes sure that the autonomous stops running when
        // teleop starts running. If you want the autonomous to
        // continue until interrupted by another command, remove
        // this line or comment it out.
        
        // CommandBase testShooter = new TestShooterSpeeds();
        // CommandBase testCPSpin = new TestControlPanelSpinMotor();
        // testShooter.schedule();
        // SmartDashboard.putData("Test_Control_Panel_Spin", testCPSpin);
        // SmartDashboard.putData("Test_Shooter_Speeds", testShooter);
        // ShuffleboardTab testtab = Shuffleboard.getTab("Test2020");
        // testtab.add(testShooter);
        

    }

    /**
     * This function is called periodically during operator control
     */
    @Override
    public void testPeriodic() {
        dashboardScheduler.update();
        CommandScheduler.getInstance().run();
    }
}
