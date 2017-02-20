package org.usfirst.frc.team1165.robot.commands;

import org.usfirst.frc.team1165.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class DriveStraightDistance extends Command
{
	private double targetHeading;
	private boolean enableEncoder = false;
	private double encoderDistance = -1;
	private double initialAngle;

	public DriveStraightDistance(double driveInches, boolean enableEncoder)
	{
		// Use requires() here to declare subsystem dependencies
		requires(Robot.driveTrain);
		requires(Robot.navXSource);
		requires(Robot.encoder);
		targetHeading = Robot.navXSource.getHeading();
		this.enableEncoder = enableEncoder;
		encoderDistance = driveInches;
		Robot.driveTrain.reset();
		Robot.encoder.disable();
		Robot.encoder.setSetpoint(encoderDistance);
		SmartDashboard.putNumber("Desired Distance", encoderDistance);
	}

	// Called just before this Command runs the first time
	protected void initialize()
	{
		initialAngle = Robot.navXSource.getHeading();
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute()
	{
		if(enableEncoder)
			Robot.encoder.enable();
		double powerCorrection = Robot.encoder.forwardSpeed;;
		double twistCorrection = Robot.navXSource.getTwistCorrection(initialAngle);
		Robot.driveTrain.driveCartesian(0, powerCorrection, twistCorrection, 0);

		SmartDashboard.putNumber("Initial Angle", initialAngle);
		SmartDashboard.putNumber("Twist Correction", twistCorrection);
		Robot.navXSource.report();
		SmartDashboard.putBoolean("Drive Straight Complete", false);

	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished()
	{
		return Robot.encoder.onTarget();
	}

	// Called once after isFinished returns true
	protected void end()
	{
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
