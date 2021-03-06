package org.usfirst.frc.team1165.robot.commands;

import org.usfirst.frc.team1165.robot.Robot;
import org.usfirst.frc.team1165.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class SetServoUsingRPM extends Command
{

	public SetServoUsingRPM()
	{
		// Use requires() here to declare subsystem dependencies
		requires(Robot.servo);
	}

	// Called just before this Command runs the first time
	protected void initialize()
	{
		SmartDashboard.putNumber("Shooter Servo Angle", Robot.servo.getCurrentAngle());
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute()
	{
		if (atSpeed())
			Robot.servo.setShooterServoPosition(Robot.servo.servoUp);
		else
			Robot.servo.setShooterServoPosition(Robot.servo.servoDown);
		SmartDashboard.putNumber("Shooter Servo Angle", Robot.servo.getCurrentAngle());
		
		//Robot.servo.setServoPosition(Robot.servo.findServoAngle());
	}

	protected boolean atSpeed()
	{
		if(!Robot.oi.stick.getRawButton(RobotMap.shooterRPMPortNumber))
			return false;
		if(Math.abs(Math.abs(Robot.shooter.getFeederWheelRPM()) - Math.abs(Robot.shooter.getFeederWheelSetpoint())) < RobotMap.shooterTolerance &&
		   Math.abs(Math.abs(Robot.shooter.getShooterWheelRPM()) - Math.abs(Robot.shooter.getShooterWheelSetpoint())) < RobotMap.shooterTolerance)
			//both wheels reading rpm and on speed
			return true;
		else if(Robot.shooter.getFeederWheelRPM() == 0 &&
				Math.abs(Math.abs(Robot.shooter.getShooterWheelRPM()) - Math.abs(Robot.shooter.getShooterWheelSetpoint())) < RobotMap.shooterTolerance)
			//feeder not reading rpm and shooter on speed
			return true;
		else if(Math.abs(Math.abs(Robot.shooter.getFeederWheelRPM()) - Math.abs(Robot.shooter.getFeederWheelSetpoint())) < RobotMap.shooterTolerance &&
				Robot.shooter.getShooterWheelRPM() == 0)
					//both wheels reading rpm and on speed
					return true;
		else return false;
	}
	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished()
	{
		return false;
	}

	// Called once after isFinished returns true
	protected void end()
	{
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted()
	{
	}
}