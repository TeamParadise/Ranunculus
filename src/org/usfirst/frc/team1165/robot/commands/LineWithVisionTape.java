package org.usfirst.frc.team1165.robot.commands;

import org.usfirst.frc.team1165.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class LineWithVisionTape extends Command
{

	public LineWithVisionTape()
	{
		// Use requires() here to declare subsystem dependencies
		requires(Robot.visionPID);
		requires(Robot.driveTrain);
	}

	// Called just before this Command runs the first time
	protected void initialize()
	{
		Robot.visionPID.setSetpoint();
		Robot.visionPID.enable();
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute()
	{
		SmartDashboard.putBoolean("Vision PID On Target", Robot.visionPID.onTarget());
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished()
	{
		return Robot.visionPID.onTarget() || Robot.visionGRIP.filterContoursEmpty();
	}

	// Called once after isFinished returns true
	protected void end()
	{
		Robot.visionPID.disable();
		SmartDashboard.putBoolean("Vision PID On Target", true);
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted()
	{
		end();
	}
}
