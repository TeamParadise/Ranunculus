package org.usfirst.frc.team1165.robot.subsystems;

import org.usfirst.frc.team1165.robot.RobotMap;
import org.usfirst.frc.team1165.robot.commands.ReportUltrasonicValues;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.Ultrasonic;
import edu.wpi.first.wpilibj.Ultrasonic.Unit;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class UltrasonicSensorSource extends Subsystem
{

    public enum StrafeType
    {
	NONE, LEFT, RIGHT
    };

    Ultrasonic ultrasonicLeft;
    Ultrasonic ultrasonicRight;

    double previousReading = 0;
    double currentReading;

    // Initialize your subsystem here
    public UltrasonicSensorSource()
    {
	ultrasonicLeft = new Ultrasonic(RobotMap.ultrasonicLeftPingChannel, RobotMap.ultrasonicLeftEchoChannel);
	ultrasonicRight = new Ultrasonic(RobotMap.ultrasonicRightPingChannel, RobotMap.ultrasonicRightEchoChannel);
	// Enable
	ultrasonicLeft.setAutomaticMode(true);
	ultrasonicRight.setAutomaticMode(true);
	// set default value to inches
	ultrasonicLeft.setDistanceUnits(Unit.kInches);
	ultrasonicRight.setDistanceUnits(Unit.kInches);
    }

    public void initDefaultCommand()
    {
	// Set the default command for a subsystem here.
	setDefaultCommand(new ReportUltrasonicValues());
    }

    public void reportValues()
    {
	SmartDashboard.putNumber(RobotMap.displayUltrasonicLeftString, ultrasonicLeft.getRangeInches());
	SmartDashboard.putNumber(RobotMap.displayUltrasonicRightString, ultrasonicRight.getRangeInches());
	inchesPerSecond();
    }

    public void inchesPerSecond()
    {
	currentReading = ultrasonicRight.getRangeInches();
	SmartDashboard.putNumber("Inches Per Second", (currentReading - previousReading) / 0.05);
	previousReading = currentReading;
    }

    public double getUltrasonicReading(StrafeType direction)
    {
	if (direction == StrafeType.NONE)
	    return 0;
	else if (direction == StrafeType.LEFT)
	    return ultrasonicLeft.getRangeInches();
	return ultrasonicRight.getRangeInches();
    }
}