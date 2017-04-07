package org.usfirst.frc.team1165.robot.commands;

import org.usfirst.frc.team1165.robot.Robot;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.DriverStation.Alliance;
import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AutoGearAwayBoilerBlueOrRightGearOnly extends CommandGroup
{

	public AutoGearAwayBoilerBlueOrRightGearOnly()
	{
			addSequential(new DriveStraightNavX(0.4, -85, true),5);
			addSequential(new RotateToHeading(30),3);
			addSequential(new LineWithVisionTape(), 5);
			addSequential(new StrafeToWall(),5);
	}
}