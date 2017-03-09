package org.usfirst.frc.team1165.robot.commands;

import org.usfirst.frc.team1165.robot.Robot;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.DriverStation.Alliance;
import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AutoGearAwayBolier extends CommandGroup
{

	public AutoGearAwayBolier()
	{
		boolean isRedAlliance = DriverStation.getInstance().getAlliance() == Alliance.Red ? true : false;

		if(isRedAlliance)
		{
			addSequential(new DriveStraightNavX(0.4, 101, true));
			addSequential(new RotateToHeading(-30));
			//addSequential(new StrafeToWall());
			addSequential(new LineWithVisionTape(Robot.ultrasonicSensorSource.getGearUltrasoniceReading()), 5);
			addSequential(new StrafeToWall());
		}
		else
		{
			addSequential(new DriveStraightNavX(0.4, -101, true));
			addSequential(new RotateToHeading(30));
			addSequential(new StrafeToWall(), 5);
			addSequential(new StrafeToWall());
		}
	}
}