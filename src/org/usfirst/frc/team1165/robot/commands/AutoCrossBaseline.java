package org.usfirst.frc.team1165.robot.commands;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.DriverStation.Alliance;
import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class AutoCrossBaseline extends CommandGroup
{

	public AutoCrossBaseline()
	{
			addSequential(new DriveStraightNavX(0.4, 108, true),5);
	}
}