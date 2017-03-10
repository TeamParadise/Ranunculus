package org.usfirst.frc.team1165.robot.commands;

import org.usfirst.frc.team1165.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class DriveStraightNavX extends Command
{
	private double forwardSpeed = -0.5;
	private double initialAngle;
	private double distanceToWall = -1;
	private boolean enableDistanceToWall = false;
	private boolean enableEncoder = false;
	private double encoderDistance = -1;

	public DriveStraightNavX()
	{
		requires(Robot.driveTrain);
		requires(Robot.navXSource);
		requires(Robot.encoder);
		Robot.navXSource.getHeading();
	}

	public DriveStraightNavX(double timeout)
	{
		// Use requires() here to declare subsystem dependencies
		this();
		setTimeout(timeout);
	}

	public DriveStraightNavX(double forwardSpeed, double timeout)
	{
		this(timeout);
		// Allows User to pass in positive values for forward
		this.forwardSpeed = forwardSpeed;
	}

	public DriveStraightNavX(double forwardSpeed, boolean enableDistanceToWall)
	{
		this();
		this.forwardSpeed = forwardSpeed;
		this.enableDistanceToWall = enableDistanceToWall;
		if (enableDistanceToWall)
			distanceToWall = 10;
	}

	public DriveStraightNavX(double forwardSpeed, double driveInches, boolean enableEncoder)
	{
		this();
		this.forwardSpeed = forwardSpeed;
		this.enableEncoder = enableEncoder;
		encoderDistance = driveInches;
	}

	// Called just before this Command runs the first time
	protected void initialize()
	{
		// Robot.navXSource.reset();
		if (enableEncoder)
		{
			Robot.driveTrain.reset();
			Robot.encoder.disable();
			Robot.encoder.setSetpoint(encoderDistance);
			SmartDashboard.putNumber("Desired Distance", encoderDistance);
			Robot.encoder.setOutputRange(-forwardSpeed, forwardSpeed);
			Robot.encoder.enable();
		}
		initialAngle = Robot.navXSource.getHeading();
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute()
	{
		double powerCorrection = 0.25;
		double twistCorrection = Robot.navXSource.getTwistCorrection(initialAngle);
		if (enableDistanceToWall)
			powerCorrection = Robot.ultrasonicSensorSource.distancePower(distanceToWall, forwardSpeed);
		else if (enableEncoder)
			powerCorrection = Robot.encoder.forwardSpeed;
		else
			powerCorrection = forwardSpeed;
		Robot.driveTrain.driveCartesian(0, powerCorrection, twistCorrection, 0);

		SmartDashboard.putNumber("Initial Angle", initialAngle);
		SmartDashboard.putNumber("Twist Correction", twistCorrection);
		Robot.navXSource.report();
		SmartDashboard.putBoolean("Drive Straight Complete", false);
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished()
	{
		return isTimedOut() || (enableEncoder && Robot.encoder.onTarget())
				|| (enableDistanceToWall && Robot.ultrasonicSensorSource.atDistance(distanceToWall));
				//|| Robot.driveTrain.atDistance(encoderDistance);
	}

	// Called once after isFinished returns true
	protected void end()
	{
		Robot.navX.navXController.disable();
		Robot.driveTrain.driveCartesian(0, 0, 0, 0);
		Robot.driveTrain.reset();
		Robot.encoder.disable();
		SmartDashboard.putBoolean("Drive Straight Complete", true);
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted()
	{
	}
}