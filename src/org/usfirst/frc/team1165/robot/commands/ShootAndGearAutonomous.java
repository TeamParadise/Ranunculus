package org.usfirst.frc.team1165.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class ShootAndGearAutonomous extends CommandGroup
{

	public ShootAndGearAutonomous()
	{
		addSequential(new RotateToHeading(-45.0));
		addSequential(new EnableUltrasonicSetpoint(), 2);
		addSequential(new DriveShooter(), 2);
		addSequential(new RotateToHeading(-45));
		addSequential(new DriveStraightNavX(0.4, -50, true));
		addSequential(new RotateToHeading(-135.0));
	}
}
