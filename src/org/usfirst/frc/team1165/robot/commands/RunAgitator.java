package org.usfirst.frc.team1165.robot.commands;

import java.util.Timer;
import java.util.TimerTask;

import org.usfirst.frc.team1165.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class RunAgitator extends Command
{

	public static Timer timer;
	int reversalTime = 0;
	public static double power = -0.5;
	public static int period = 2000;

	public RunAgitator()
	{
		// Use requires() here to declare subsystem dependencies
		reversalTime = 0;
		requires(Robot.agitator);
	}

	public RunAgitator(int reversalTime)
	{
		this();
		this.reversalTime = reversalTime;
	}

	// Called just before this Command runs the first time
	protected void initialize()
	{
		timer = new Timer();
		if (reversalTime != 0)
			timer.schedule(new ReverseMotor(reversalTime), 0, period);
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute()
	{
		if (reversalTime == 0)
		{
			if (Robot.shooter.isRunning() && Robot.servo.isServoUp())
				Robot.agitator.set(power);
			else if (Robot.shooter.isRunning() && !Robot.servo.isServoUp())
				Robot.agitator.set(-power);
			else
				Robot.agitator.set(0);
		} else
		{
			if (Robot.shooter.isRunning() && Robot.servo.isServoUp())
			{

				Robot.agitator.set(power);
			} else
				Robot.agitator.set(0);
		}
		SmartDashboard.putNumber("Agitator Power", Robot.agitator.getSpeed());
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

class ReverseMotor extends TimerTask
{
	int reverseCount = -1;
	int cycles = 1;

	public ReverseMotor(int forwardtime)
	{
		reverseCount = forwardtime / RunAgitator.period - 1;
	}

	public void run()
	{
		if (reverseCount > 0)
		{
			if (cycles++ >= reverseCount || RunAgitator.power > 0)
			{
				cycles = 1;
				RunAgitator.power *= -1;
			}
		} else
			RunAgitator.power *= -1;
	}
}