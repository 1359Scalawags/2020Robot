package frc.robot.helper;

public class LoaderBallSlot {
    
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