package org.usfirst.frc.team1165.robot.commands;

import org.usfirst.frc.team1165.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class LineWithVisionTape extends Command
{

	boolean useDistance = false;
	private double distance = -1;
	private double targetHeading;
	private double forwardSpeed = -0.75;
	private double initialAngle;
	public LineWithVisionTape()
	{
		// Use requires() here to declare subsystem dependencies
		requires(Robot.visionPID);
		requires(Robot.driveTrain);
	}
	
	public LineWithVisionTape(double distance)
	{
		this();
		useDistance = true;
		this.distance = distance;
		targetHeading = Robot.navXSource.getHeading();
	}

	// Called just before this Command runs the first time
	protected void initialize()
	{
		initialAngle = Robot.navXSource.getHeading();
		Robot.visionPID.setSetpoint();
		Robot.visionPID.enable();
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute()
	{
		SmartDashboard.putBoolean("Vision PID On Target", Robot.visionPID.onTarget());
		if(useDistance)
		{
			double twistCorrection = Robot.navXSource.getTwistCorrection(initialAngle);
			double powerCorrection = Robot.ultrasonicSensorSource.gearDistancePower(distance, forwardSpeed);
			Robot.driveTrain.driveCartesian(powerCorrection, 0, twistCorrection, 0);
		}
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished()
	{
		return useDistance?Robot.visionPID.onTarget() || Robot.visionGRIP.filterContoursEmpty():Robot.ultrasonicSensorSource.atDistance(distance);
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
