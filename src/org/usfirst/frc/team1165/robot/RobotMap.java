package org.usfirst.frc.team1165.robot;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap
{
	// For example to map the left and right motors, you could define the
	// following variables to use with your drivetrain subsystem.
	// public static int leftMotor = 1;
	// public static int rightMotor = 2;

	public static final int joystickPort = 0;

	public static final int shooterRPMPortNumber = 1;
	
	public static final int climbButton = 2;
	
	public static final int placeGearButton = 12;

	public static final int ultrasonicPickupLeftPingChannel = 0;
	public static final int ultrasonicPickupLeftEchoChannel = 1;
	public static final int ultrasonicPickupRightPingChannel = 2;
	public static final int ultrasonicPickupRightEchoChannel = 3;

	public static final int shooterWheelPortNumber = 4;
	public static final int feederWheelPortNumber = 5;
	public static final double shooterTolerance = 100;

	public static final int shooterServoPortNumber = 0;
	public static final int shooterServoButtonNumber = 11;

	public static final int rotateRobotN180 = 3;
	public static final int rotateRobot180 = 4;
	public static final int rotateRobotN90 = 5;
	public static final int rotateRobot90 = 6;
	
	public static final int enableVisionPID = 12;

	public static final int enableUltrasonicPID = 10;
	
	//Encoder Ports
	public static final int backLeftEncoderPort1 = 0;
	public static final int backLeftEncoderPort2 = 1;
	
	public static final int backRightEncoderPort1 = 2;
	public static final int backRightEncoderPort2 = 3;

	public static final int ultrasonicGearPingChannel = 4;
	public static final int ultrasonicGearEchoChannel = 5;

	public static final String getShooterWheelString = "Shooter Wheel RPM";
	public static final String getFeederWheelString = "Feeder Wheel RPM";

	public static final String displayShooterWheelString = "Display Shooter Wheel RPM";
	public static final String displayFeederWheelString = "Display Feeder Wheel RPM";
	
	public static final String displayShooterWheelValue = "Display Shooter Wheel Value";
	public static final String displayFeederWheelValue = "Display Feeder Wheel Value";
	
	public static final String getShooterWheelValue = "Shooter Wheel Value";
	public static final String getFeederWheelValue = "Feeder Wheel Value";

	public static final String displayUltrasonicLeftString = "Ultrasonic Left";
	public static final String displayUltrasonicRightString = "Ultrasonic Right";
	public static final String displayGearUltrasonicString = "Gear Ultrasonic";
	
	public static final int CANTalonDriveFrontLeft = 0;
	public static final int CANTalonDriveRearLeft = 1;
	public static final int CANTalonDriveFrontRight =2;
	public static final int CANTalonDriveRearRight = 3;
	public static final int CANTalonShooter = 4;
	public static final int CANTalonShooterFeeder = 5;
	public static final int CANTalonAgitator = 6;
	public static final int CANTalonPickup= 7;
	public static final int CANTalonClimber= 8;
	
	
	
	// If you are using multiple modules, make sure to define both the port
	// number and the module. For example you with a rangefinder:
	// public static int rangefinderPort = 1;
	// public static int rangefinderModule = 1;
}