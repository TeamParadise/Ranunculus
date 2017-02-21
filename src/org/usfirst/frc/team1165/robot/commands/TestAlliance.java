package org.usfirst.frc.team1165.robot.commands;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.DriverStation.Alliance;
import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class TestAlliance extends CommandGroup
{

	public TestAlliance()
	{
		if(DriverStation.getInstance().getAlliance() == Alliance.Red)
			addSequential(new DriveStraightNavX(0.5, 20, true));
		else
			addSequential(new DriveStraightNavX(0.5, -20, true));
	}
}