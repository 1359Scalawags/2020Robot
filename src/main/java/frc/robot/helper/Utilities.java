package frc.robot.helper;



public class Utilities {



	public Utilities() {

	}



	public static double NormalizeAngle(double angle) {

		while (angle > 180) {

			angle = angle - 360;

		}

		while (angle < -180) {

			angle = angle + 360;

		}

		return angle;

	}



	public static double Clamp(double value, double min, double max) { // clamp values of PID

		return Math.max(min, Math.min(max, value));

	}

}