package org.usfirst.frc.team1165.robot.commands;

import org.usfirst.frc.team1165.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class StrafeAwayFromWall extends Command
{

	private double forwardSpeed = 0.85;
	private double initialAngle;
	private double distanceFromWall = -1;
	
	public StrafeAwayFromWall(double distance)
	{
		requires(Robot.driveTrain);
		requires(Robot.ultrasonicSensorSource);
		requires(Robot.ultrasonicPID);
		Robot.navXSource.getHeading();
		distanceFromWall = distance;
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
		double powerCorrection = Robot.ultrasonicSensorSource.gearDistanceAway(distanceFromWall, forwardSpeed);
		
		Robot.driveTrain.driveCartesian(powerCorrection, 0, twistCorrection, 0);
		SmartDashboard.putBoolean("Strafe Away From Wall Finished", false);
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished()
	{
		return Robot.ultrasonicSensorSource.gearUltrasonicAtDistanceAway(distanceFromWall);
	}

	// Called once after isFinished returns true
	protected void end()
	{
		// Stop drive train?
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted()
	{
		end();
	}
}