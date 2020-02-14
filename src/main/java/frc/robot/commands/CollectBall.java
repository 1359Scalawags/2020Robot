package frc.robot.commands;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Robot;

class CollectBall extends CommandBase{

    //TODO should these in the Constants file
    private double ballLoadSpdA;
    private double ballLoadSpdB;
    private double ballLoadSpdC;
    private double ballLoadSpdD;
    private double ballLoadSpdE;
    private double AvgBallLoadSpd;

    public CollectBall() {
        addRequirements(Robot.ballSystem);

        AvgBallLoadSpd = (ballLoadSpdA + ballLoadSpdB + ballLoadSpdC + ballLoadSpdD + ballLoadSpdE)/5;
    }

    // Called just before this Command runs the first time
    @Override
    public void initialize() {
        SmartDashboard.putNumber("Ball Loader Speed", AvgBallLoadSpd);
    }

    // Called repeatedly when this Command is scheduled to run
    @Override
    public void execute() {
        Robot.ballSystem.setBallLoaderSpeed(ballLoadSpdA, ballLoadSpdB, ballLoadSpdC, ballLoadSpdD, ballLoadSpdE);
    }

    // Make this return true when this Command no longer needs to run execute()
    @Override
    public boolean isFinished() {
        return !Robot.oi.getAutoDriveForwardButton();
    }

    // Called once after isFinished returns true
    @Override
    public void end(boolean interrupted) {
    }
}