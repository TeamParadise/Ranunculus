package org.usfirst.frc.team1165.robot.commands;

import org.usfirst.frc.team1165.robot.Robot;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.DriverStation.Alliance;
import edu.wpi.first.wpilibj.command.CommandGroup;

public class AutoShootAndGear extends CommandGroup
{

	public AutoShootAndGear()
	{
		boolean isRedAlliance = DriverStation.getInstance().getAlliance() == Alliance.Red ? true : false;

		if (isRedAlliance)
		{
			addSequential(new DriveStraightNavX(0.4, -50, true));
			addSequential(new RotateToHeading(-45.0));
			addSequential(new DriveStraightNavX(0.4, true));
			addSequential(new EnableUltrasonicSetpoint());
			addSequential(new DriveShooter(), 3);
			addSequential(new DriveStraightNavX(0.4, -132, true));
			addSequential(new RotateToHeading(90));
			//addSequential(new StrafeToWall());
			addSequential(new LineWithVisionTape(Robot.ultrasonicSensorSource.getGearUltrasoniceReading()), 5);
			addSequential(new StrafeToWall());
		} else
		{
			addSequential(new DriveStraightNavX(0.4, -50, true));
			addSequential(new RotateToHeading(45));
			addSequential(new DriveStraightNavX(0.4, true));
			addSequential(new EnableUltrasonicSetpoint());
			addSequential(new DriveShooter(), 3);
			addSequential(new DriveStraightNavX(0.4, -132, true));
			addSequential(new RotateToHeading(90));
			//addSequential(new StrafeToWall());
			addSequential(new LineWithVisionTape(Robot.ultrasonicSensorSource.getGearUltrasoniceReading()), 5);
			addSequential(new StrafeToWall());
		}
	}
}