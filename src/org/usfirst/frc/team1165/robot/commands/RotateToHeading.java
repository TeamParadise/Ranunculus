package org.usfirst.frc.team1165.robot.commands;

import org.usfirst.frc.team1165.robot.Robot;
import org.usfirst.frc.team1165.robot.subsystems.NavX_MXP_PID;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class RotateToHeading extends Command
{

	public int buttonNumber;
	public static boolean rotateToAngle = false;

	public double targetHeading;
	public double initialHeading;

	public RotateToHeading(double heading)
	{
		requires(Robot.driveTrain);
		requires(Robot.navXSource);
		targetHeading = heading;
	}

	// Called just before this Command runs the first time
	protected void initialize()
	{
		initialHeading = Robot.navXSource.getHeading();
		Robot.navX.navXController.disable();
		Robot.navXSource.reset();
		//targetHeading = Robot.navXSource.getHeading() - targetHeading;
		SmartDashboard.putNumber("Target Heading", targetHeading);
		Robot.navX.navXController.setSetpoint(targetHeading);
		Robot.navX.navXController.enable();
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute()
	{
		SmartDashboard.putNumber("NavX Target Heading", targetHeading);
		SmartDashboard.putBoolean("RotateToHeadingFinished", Robot.navX.navXController.onTarget());
		Robot.driveTrain.driveCartesian(Robot.oi.stick.getX(), Robot.oi.stick.getY(), Robot.navX.rotateToAngleRate,0);
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished()
	{
		return (Math.abs(Robot.navX.getTargetDifference()) < NavX_MXP_PID.kToleranceDegrees
		 && Robot.navX.navXController.onTarget()); /* ||
				(Math.abs(Robot.navX.getTargetDifference()) < NavX_MXP_PID.kToleranceDegrees*2 && Robot.navX.rotateToAngleRate < 0.2);
				*/
	}

	// Called once after isFinished returns true
	protected void end()
	{
		Robot.navX.navXController.disable();
		Robot.driveTrain.stop();
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted()
	{
		end();
	}
}