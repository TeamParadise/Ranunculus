package org.usfirst.frc.team1165.robot.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class PlaceGearAutonomous extends CommandGroup
{

	public PlaceGearAutonomous()
	{
		addSequential(new DriveStraightNavX(1, 40, true));
		addSequential(new RotateToHeading(-90.0));
	}
}