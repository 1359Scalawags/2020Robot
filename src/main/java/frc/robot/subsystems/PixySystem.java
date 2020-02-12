// RobotBuilder Version: 2.0
//
// This file was generated by RobotBuilder. It contains sections of
// code that are automatically generated and assigned by robotbuilder.
// These sections will be updated in the future when you export to
// Java from RobotBuilder. Do not put any code or make any change in
// the blocks indicating autogenerated code or it will be lost on an
// update. Deleting the comments indicating the section will prevent
// it from being updated in the future.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.Robot;

import java.util.ArrayList;

import io.github.pseudoresonance.pixy2api.*;
import io.github.pseudoresonance.pixy2api.Pixy2CCC.Block;
import io.github.pseudoresonance.pixy2api.links.*;


/**
 *
 */
public class PixySystem extends SubsystemBase {

    private final Pixy2 pixy;

    public PixySystem() {      
        this(new SPILink());
    }

    public PixySystem(Link link) {
        pixy = new Pixy2(link);
        pixy.init();
    }

    public void GotoClosest(){
        Block block = getClosest();
        double angle = block.getAngle();
        
        Robot.driveSystem.arcadeDrive(0.0, Constants.maxRightTurnRate, angle);
    }

    public Block getClosest(){
        int bestIndex = -1;
        double bestRadius = -1;

        ArrayList<Block> blocks = pixy.getCCC().getBlocks();

        for(int i=0; i<blocks.size(); i++){
            Block block = blocks.get(i);
            double radius = (block.getHeight()+block.getWidth())/2;

            if(radius > bestRadius){
                bestIndex = i;
                bestRadius = radius;
            }
        }

        return blocks.get(bestIndex);
    }

    public Pixy2 getPixy() {
        return pixy;
    }

}

