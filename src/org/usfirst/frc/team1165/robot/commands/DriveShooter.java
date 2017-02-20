package org.usfirst.frc.team1165.robot.commands;

import org.usfirst.frc.team1165.robot.Robot;
import org.usfirst.frc.team1165.robot.RobotMap;

import com.ctre.CANTalon.TalonControlMode;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class DriveShooter extends Command
{
	public DriveShooter()
	{
		// Use requires() here to declare subsystem dependencies
		requires(Robot.shooter);
	}

	// Called just before this Command runs the first time
	protected void initialize()
	{
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute()
	{
		if (SmartDashboard.getNumber(RobotMap.displayFeederWheelString) == 0)
		{
			Robot.shooter.setFeederWheelPower(SmartDashboard.getNumber(RobotMap.getFeederWheelValue));
		}
		else
		{
			Robot.shooter.driveFeederWheelAtRPM(Robot.shooter.getFeederWheelSetpoint());
		}

		if (SmartDashboard.getNumber(RobotMap.displayShooterWheelString) == 0)
		{
			Robot.shooter.setShooterWheelPower(SmartDashboard.getNumber(RobotMap.getShooterWheelValue));
		}
		else
		{
			Robot.shooter.driveShooterWheelAtRPM(Robot.shooter.getShooterWheelSetpoint());
		}
		Robot.shooter.report();
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished()
	{
		return !Robot.oi.stick.getRawButton(RobotMap.shooterRPMPortNumber) || isTimedOut();
	}

	// Called once after isFinished returns true
	protected void end()
	{
		//instead of set 0 set the power to 0 so the motor doesn't run to hold 0 rpm
		Robot.shooter.setFeederWheelPower(0);
		Robot.shooter.setShooterWheelPower(0);
		Robot.agitator.set(0);
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted()
	{
	}
}