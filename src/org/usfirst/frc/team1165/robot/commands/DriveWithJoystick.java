package org.usfirst.frc.team1165.robot.commands;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import org.usfirst.frc.team1165.robot.Robot;
import org.usfirst.frc.team1165.robot.RobotMap;

public class DriveWithJoystick extends Command
{

	public DriveWithJoystick()
	{
		// Use requires() here to declare subsystem dependencies
		requires(Robot.driveTrain);
	}

	// Called just before this Command runs the first time
	@Override
	protected void initialize()
	{
	}

	// Called repeatedly when this Command is scheduled to run
	@Override
	protected void execute()
	{
		// Reducing sensitivity of Joystick
		double x = -Robot.oi.stick.getX(); // * Robot.oi.stick.getX() * Robot.oi.stick.getX();
		double y = -Robot.oi.stick.getY(); // * Robot.oi.stick.getY() * Robot.oi.stick.getY();
		double twist = -Robot.oi.stick.getTwist() * Robot.oi.stick.getTwist() * Robot.oi.stick.getTwist();
		// stick.getTwist();
		if (Math.abs(x) < 0.1)
			x = 0;
		if (Math.abs(y) < 0.1)
			y = 0;
		if (Math.abs(twist) < 0.2)
			twist = 0;
		Robot.driveTrain.driveCartesian(x, y, twist, 0);
		Robot.navXSource.report();
		Robot.driveTrain.report();
	}

	// Make this return true when this Command no longer needs to run execute()
	@Override
	protected boolean isFinished()
	{
		return false;
	}

	// Called once after isFinished returns true
	@Override
	protected void end()
	{
	}
	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	@Override
	protected void interrupted()
	{
	}
}