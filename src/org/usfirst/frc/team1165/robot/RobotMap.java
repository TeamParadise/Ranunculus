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

    public static final int shooterRPMPortNumber = 9;

    public static final int ultrasonicLeftPingChannel = 0;
    public static final int ultrasonicLeftEchoChannel = 1;
    public static final int ultrasonicRightPingChannel = 2;
    public static final int ultrasonicRightEchoChannel = 3;
    
    public static final int shooterWheelPortNumber = 4;
    public static final int feederWheelPortNumber = 5;
    
    public static final int rightServoPortNumber = 0;
    public static final int rightServoButtonNumber = 11;

    public static final int rotateRobotN180 = 3;
    public static final int rotateRobot180 = 4;
    public static final int rotateRobotN90 = 5;
    public static final int rotateRobot90 = 6;

    public static final int enableUltrasonicPID = 10;

    public static final String getShooterWheelString = "Shooter Wheel RPM";
    public static final String getFeederWheelString = "Feeder Wheel RPM";

    public static final String displayShooterWheelString = "Display Shooter Wheel RPM";
    public static final String displayFeederWheelString = "Display Feeder Wheel RPM";

    public static final String displayUltrasonicLeftString = "Ultrasonic Left";
    public static final String displayUltrasonicRightString = "Ultrasonic Right";
    // If you are using multiple modules, make sure to define both the port
    // number and the module. For example you with a rangefinder:
    // public static int rangefinderPort = 1;
    // public static int rangefinderModule = 1;
}