package org.usfirst.frc.team1165.robot.commands;

import org.usfirst.frc.team1165.robot.Robot;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.DriverStation.Alliance;
import edu.wpi.first.wpilibj.command.CommandGroup;

public class AutoShootAndGearRed extends CommandGroup
{

	public AutoShootAndGearRed()
	{
			addSequential(new DriveStraightNavX(0.4, -34, true));
			addSequential(new RotateToHeading(45.0));
			addSequential(new DriveStraightNavX(0.4, true));
			addSequential(new EnableUltrasonicSetpoint());
			addSequential(new MoveShooterAuto(), 3);
			addSequential(new DriveStraightNavX(0.4, -116, true));
			addSequential(new RotateToHeading(90));
			//addSequential(new StrafeToWall());
			addSequential(new LineWithVisionTape(), 5);
			addSequential(new StrafeToWall());
	}
}