package org.usfirst.frc.team1165.robot.subsystems;

import org.usfirst.frc.team1165.robot.RobotMap;
import org.usfirst.frc.team1165.robot.commands.ReportUltrasonicValues;

import edu.wpi.first.wpilibj.Ultrasonic;
import edu.wpi.first.wpilibj.Ultrasonic.Unit;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class UltrasonicSensorSource extends Subsystem
{

    Ultrasonic ultrasonicLeft;
    Ultrasonic ultrasonicRight;

    // Initialize your subsystem here
    public UltrasonicSensorSource()
    {
	ultrasonicLeft = new Ultrasonic(RobotMap.ultrasonicLeftPingChannel, RobotMap.ultrasonicLeftEchoChannel);
	ultrasonicRight = new Ultrasonic(RobotMap.ultrasonicRightPingChannel, RobotMap.ultrasonicRightEchoChannel);
	//Enable
	ultrasonicLeft.setAutomaticMode(true);
	ultrasonicRight.setAutomaticMode(true);
	//set default value to inches
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
	SmartDashboard.putNumber("Ultrasonic Left", ultrasonicLeft.getRangeInches());
	SmartDashboard.putNumber("Ultrasonic Right", ultrasonicRight.getRangeInches());
    }
    
    public double getUltrasonic(boolean a)
    {
	if(a)
	    return ultrasonicLeft.getRangeInches();
	return ultrasonicRight.getRangeInches();
    }
}