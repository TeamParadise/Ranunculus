package org.usfirst.frc.team1165.robot.commands;

import org.usfirst.frc.team1165.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class RunPickup extends Command
{

	public RunPickup()
	{
		// Use requires() here to declare subsystem dependencies
		requires(Robot.pickup);
	}

	// Called just before this Command runs the first time
	protected void initialize()
	{
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute()
	{
//		if(Robot.driveTrain.isRunning() ||Robot.shooter.isRunning())
		if (Robot.oi.stick.getPOV() >= 0) //the button has been moved
			Robot.pickup.set(0.5);
		else
			Robot.pickup.set(0);
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished()
	{
		return false;
	}

	// Called once after isFinished returns true
	protected void end()
	{
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted()
	{
	}
}