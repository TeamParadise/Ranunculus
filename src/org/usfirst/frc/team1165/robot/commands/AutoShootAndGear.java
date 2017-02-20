package org.usfirst.frc.team1165.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AutoShootAndGear extends CommandGroup
{

	public AutoShootAndGear()
	{
		addSequential(new RotateToHeading(-45.0));
		addSequential(new EnableUltrasonicSetpoint(), 2);
		addSequential(new DriveShooter(), 2);
		addSequential(new RotateToHeading(-45));
		addSequential(new DriveStraightNavX(0.4, -150, true));
		addSequential(new RotateToHeading(-135.0));
	}
}
