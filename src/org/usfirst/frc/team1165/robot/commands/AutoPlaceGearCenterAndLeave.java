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
public class AutoPlaceGearCenterAndLeave extends CommandGroup
{

	public AutoPlaceGearCenterAndLeave()
	{
			addSequential(new DriveStraightNavX(0.4, 60, true),5);
			addSequential(new RotateToHeading(-90.0),3);
			addSequential(new LineWithVisionTape(),3);
			addSequential(new StrafeToWall(),4);
			addSequential(new WaitCommand(5),5);
			addSequential(new StrafeAwayFromWall(20), 3);
			addSequential(new DriveStraightNavX(0.4, 72, true));
			addSequential(new RotateToHeading(90));
			addSequential(new DriveStraightNavX(0.4, 108, true));
	}
}