package org.usfirst.frc.team1165.robot.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;

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
		addSequential(new WaitCommand(3));
		addSequential(new StrafeAwayFromWall(20), 3);
		addSequential(new DriveStraightNavX(0.4, 72, true));
		addSequential(new RotateToHeading(90));
		addSequential(new DriveStraightNavX(0.4, 108, true));
	}
}