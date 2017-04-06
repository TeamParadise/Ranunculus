package org.usfirst.frc.team1165.robot.commands;

import org.usfirst.frc.team1165.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;


public class LineWithVisionTape extends Command
{
	private double distanceToWall = 5;
	private double targetHeading;
	private double forwardSpeed = -0.75;
	private double initialAngle;
	private double distance;
	public LineWithVisionTape()
	{
		// Use requires() here to declare subsystem dependencies
		requires(Robot.visionPID);
		requires(Robot.driveTrain);
	}
	// Called just before this Command runs the first time
	protected void initialize()
	{
		initialAngle = Robot.navXSource.getHeading();
		Robot.visionPID.setSetpoint();
		Robot.visionPID.enable();
		distance = Robot.ultrasonicSensorSource.getGearUltrasoniceReading();
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute()
	{
			SmartDashboard.putBoolean("Vision PID On Target", Robot.visionPID.onTarget());
			double twistCorrection = Robot.navXSource.getTwistCorrection(initialAngle);
			double powerCorrection = Robot.visionPID.output;
			double strafeCorrection = Robot.ultrasonicSensorSource.gearDistancePower(distance, forwardSpeed)/2.0;
			if (Math.abs(strafeCorrection) < 0.75) strafeCorrection = Math.signum(strafeCorrection)*0.75;
			Robot.driveTrain.driveCartesian(strafeCorrection, powerCorrection, twistCorrection, 0);
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished()
	{
		return Robot.ultrasonicSensorSource.gearUltrasonicAtDistance(distanceToWall);
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
