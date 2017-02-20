package org.usfirst.frc.team1165.robot.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AutoPlaceGear extends CommandGroup
{

	public AutoPlaceGear()
	{
		addSequential(new DriveStraightNavX(0.4, 60, true));
		addSequential(new RotateToHeading(-90.0));
		addSequential(new StrafeToWall());
	}
}