package frc.robot.commands.load;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants.Load;
import frc.robot.Robot;
import edu.wpi.first.wpilibj.Timer;

<<<<<<< HEAD
@Deprecated
=======
//TODO: Make this command do its inferred task of getting shots ready

>>>>>>> dda387480f3eb783e34b25189588479f0da49700
public class IndexLoadChamber extends CommandBase{
    private Timer indexerDelay;

    public IndexLoadChamber(){
        addRequirements(Robot.loadingSystem);
        indexerDelay = new Timer();
        
    }

    @Override
    public void initialize() {
        indexerDelay.reset();
        indexerDelay.start();
        Robot.loadingSystem.rotateChamber(Load.maxChamberSpeed);
    }

    @Override
    public void execute() {

    }

    // Make this return true when this Command no longer needs to run execute()
    @Override
    public boolean isFinished() {
        if(indexerDelay.get() > Load.indextimer)
        {
            if(Robot.loadingSystem.isLoaderIndexed()) {
                return true;
            }        
            if(indexerDelay.get() > Load.MaxIndexTime) {
                return true;
            }   
        }
        return false;    
    }

    // Called once after isFinished returns true
    @Override
    public void end(boolean interrupted) {
        Robot.loadingSystem.advanceBallSlots();
    }

}