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
	Ultrasonic gearUltrasonic;

	double previousReading = 0;
	double currentReading;
	double leftMin = 333;
	double leftMax = -333;
	double rightMin = 333;
	double rightMax = -333;
	double gearMin = 333;
	double gearMax = -333;

	// Initialize your subsystem here
	public UltrasonicSensorSource()
	{
		ultrasonicLeft = new Ultrasonic(RobotMap.ultrasonicPickupLeftPingChannel, RobotMap.ultrasonicPickupLeftEchoChannel);
		ultrasonicRight = new Ultrasonic(RobotMap.ultrasonicPickupRightPingChannel, RobotMap.ultrasonicPickupRightEchoChannel);
		gearUltrasonic = new Ultrasonic(RobotMap.ultrasonicGearPingChannel, RobotMap.ultrasonicGearEchoChannel);
		// Enable
		ultrasonicLeft.setAutomaticMode(true);
		ultrasonicRight.setAutomaticMode(true);
		gearUltrasonic.setAutomaticMode(true);
		// set default value to inches
		ultrasonicLeft.setDistanceUnits(Unit.kInches);
		ultrasonicRight.setDistanceUnits(Unit.kInches);
	    gearUltrasonic.setDistanceUnits(Unit.kInches);
	}

	public void initDefaultCommand()
	{
		// Set the default command for a subsystem here.
		setDefaultCommand(new ReportUltrasonicValues());
	}
	
	public double getGearUltrasoniceReading()
	{
		return gearUltrasonic.getRangeInches();
	}
	public double distancePower(double distance, double power)
	{
		if(ultrasonicLeft.getRangeInches() < distance * 2 && distance > -1)
		{
			//forward
			return 0.15;
		}
		return power;
	}
	
	public double gearDistancePower(double distance, double power)
	{
		if((gearUltrasonic.getRangeInches() < distance * 2 && distance > -1) || Math.abs(power)<0.75)
		{
			//forward
			return -0.75;
		}
		else return power;
	}
	
	public double gearDistanceAway(double distance, double power)
	{
		if(gearUltrasonic.getRangeInches() < distance / 2 && distance > -1)
		{
			return power;
		}
		return 0.65;
		
	}
	
	public boolean gearUltrasonicAtDistanceAway(double distance)
	{
		return gearUltrasonic.getRangeInches() > distance && distance > -1;
	}
	
	public boolean atDistance(double distance)
	{
		return ultrasonicLeft.getRangeInches() < distance && distance > -1;
	}
	
	public boolean gearUltrasonicAtDistance(double distance)
	{
		return gearUltrasonic.getRangeInches() < distance && distance > -1;
	}

	public void reportValues()
	{
		leftMin = Math.min(leftMin,ultrasonicLeft.getRangeInches());
		leftMax = Math.max(leftMax,ultrasonicLeft.getRangeInches());
		rightMin = Math.min(rightMin,ultrasonicRight.getRangeInches());
		rightMax = Math.max(rightMax,ultrasonicRight.getRangeInches());
		gearMin = Math.min(gearMin,gearUltrasonic.getRangeInches());
		gearMax = Math.max(gearMax,gearUltrasonic.getRangeInches());
		SmartDashboard.putNumber(RobotMap.displayUltrasonicLeftString, ultrasonicLeft.getRangeInches());
		SmartDashboard.putNumber(RobotMap.displayUltrasonicRightString, ultrasonicRight.getRangeInches());
		SmartDashboard.putNumber(RobotMap.displayGearUltrasonicString, gearUltrasonic.getRangeInches());
		SmartDashboard.putString(RobotMap.displayUltrasonicLeftRangeString, RangeAsString(leftMin,leftMax));
		SmartDashboard.putString(RobotMap.displayUltrasonicRightRangeString, RangeAsString(rightMin,rightMax));
		SmartDashboard.putString(RobotMap.displayGearUltrasonicRangeString, RangeAsString(gearMin,gearMax));
		inchesPerSecond();
	}

	public String RangeAsString(double min, double max)
	{
		return Integer.toString((int)min)+"-"+Integer.toString((int)max);
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