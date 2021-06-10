package frc.robot.helper;

import frc.robot.Robot;

public class LoaderBallSlot {
    
    // private double[] indexPositions = {0.0, 0.1, 0.2, 0.3, 0.4, 0.5, 0.6, 0.7, 0.8, 0.9};
    // private double[] shotIndexPositions = {0.1, 0.3, 0.5, 0.7, 0.9};
    // private double[] loadIndexPositions = {0.0, 0.2, 0.4, 0.6, 0.8};

    private boolean hasBall;
    private double basePosition;


    public LoaderBallSlot(double basePosition) {
        hasBall = false;
    }

    private double normalize(double position) {
        if(position >= 1) {
            position = position - 1;
        }
        return position;
    }

    public boolean isShotAligned(double wheelPosition) {
        wheelPosition = Robot.loadingSystem.getChamEncoderDistance();
        if(Utilities.isCloseTo(0.5, normalize(wheelPosition + basePosition), 0.02)) {
            return true;
        }
        else return false;
    }

    public double getCurrentPosition(double wheelPosition) {
        double current = basePosition + wheelPosition;
        if(current > 1) {
            current -= 1;
        }
        return current;
    }

    public void insertBall() {

    }

    public void removeBall() {

    }

    public boolean isFull() {
        return hasBall;
    }
}