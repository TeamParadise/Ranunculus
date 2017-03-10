package org.usfirst.frc.team1165.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AutoGearAwayBolierBlue extends CommandGroup
{

	public AutoGearAwayBolierBlue()
	{
			addSequential(new DriveStraightNavX(0.4, -85, true));
			addSequential(new RotateToHeading(30));
			addSequential(new LineWithVisionTape(), 5);
			addSequential(new StrafeToWall());
	}
}