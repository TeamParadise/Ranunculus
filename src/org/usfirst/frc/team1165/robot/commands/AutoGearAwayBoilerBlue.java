package org.usfirst.frc.team1165.robot.commands;

import org.usfirst.frc.team1165.robot.Robot;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.DriverStation.Alliance;
import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AutoGearAwayBoilerBlue extends CommandGroup
{

	public AutoGearAwayBoilerBlue()
	{
			addSequential(new DriveStraightNavX(0.4, -85, true));
			addSequential(new RotateToHeading(30));
			addSequential(new LineWithVisionTape(), 5);
			addSequential(new StrafeToWall());
	}
}