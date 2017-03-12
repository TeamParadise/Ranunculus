package org.usfirst.frc.team1165.robot.commands;

import org.usfirst.frc.team1165.robot.Robot;
import org.usfirst.frc.team1165.robot.subsystems.UltrasonicSensorSource.StrafeType;

import edu.wpi.first.wpilibj.command.Command;

public class EnableUltrasonicSetpoint extends Command
{

	public EnableUltrasonicSetpoint()
	{
		// Use requires() here to declare subsystem dependencies
		requires(Robot.ultrasonicPID);
		requires(Robot.driveTrain);
		requires(Robot.ultrasonicSensorSource);
	}

	// Called just before this Command runs the first time
	protected void initialize()
	{
		Robot.ultrasonicPID.setSetpoint();
		Robot.ultrasonicPID.enable();
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute()
	{
		// Do Nothing, PID is in control
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished()
	{
		return Robot.ultrasonicPID.onTarget() || Robot.ultrasonicPID.getUltrasonic() == StrafeType.NONE;
	}

	// Called once after isFinished returns true
	protected void end()
	{
		// Stop drive train?
		Robot.ultrasonicPID.disable();
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted()
	{
		end();
	}
}