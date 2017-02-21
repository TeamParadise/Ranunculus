package org.usfirst.frc.team1165.robot.commands;

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
			addSequential(new RotateToHeading(-45.0));
			addSequential(new DriveShooter(), 2);
			addSequential(new RotateToHeading(-45));
			addSequential(new RotateToHeading(-20));
			addSequential(new DriveStraightNavX(0.4, -90, true));
			addSequential(new RotateToHeading(-270.0));
		} else
		{
			addSequential(new RotateToHeading(45.0));
			addSequential(new DriveShooter(), 2);
			addSequential(new RotateToHeading(45));
			addSequential(new RotateToHeading(20));
			addSequential(new DriveStraightNavX(0.4, -90, true));
			addSequential(new RotateToHeading(270.0));
		}
	}
}