package org.usfirst.frc.team1165.robot.commands;

import org.usfirst.frc.team1165.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class StrafeToWall extends Command
{

	private double targetHeading;
	private double forwardSpeed = 1;
	private double initialAngle;
	private double distanceToWall = -1;
	private boolean enableDistanceToWall = false;
	
	public StrafeToWall()
	{
		requires(Robot.driveTrain);
		requires(Robot.ultrasonicSensorSource);
		requires(Robot.ultrasonicPID);
		targetHeading = Robot.navXSource.getHeading();
		distanceToWall = 4;
	}

	// Called just before this Command runs the first time
	protected void initialize()
	{
		initialAngle = Robot.navXSource.getHeading();
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute()
	{
		double twistCorrection = Robot.navXSource.getTwistCorrection(initialAngle);
		double powerCorrection = Robot.ultrasonicSensorSource.distancePower(distanceToWall, forwardSpeed);
		
		Robot.driveTrain.driveCartesian(powerCorrection, 0, twistCorrection, 0);
		SmartDashboard.putBoolean("Strafe To Wall Finished", false);
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished()
	{
		return Robot.ultrasonicSensorSource.gearUltrasonicAtDistance(distanceToWall);
	}

	// Called once after isFinished returns true
	protected void end()
	{
		Robot.driveTrain.driveCartesian(0, 0, 0, 0);
		SmartDashboard.putBoolean("Strafe To Wall Finished", true);
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted()
	{
	}
}