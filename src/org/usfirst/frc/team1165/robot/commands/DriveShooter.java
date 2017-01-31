package org.usfirst.frc.team1165.robot.commands;

import org.usfirst.frc.team1165.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class DriveShooter extends Command
{
    public boolean disable = false;

    public DriveShooter()
    {
	// Use requires() here to declare subsystem dependencies
	disable = false;
	requires(Robot.shooter);
    }

    public DriveShooter(boolean disable)
    {
	this.disable = disable;
    }

    // Called just before this Command runs the first time
    protected void initialize()
    {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute()
    {
	if (!disable)
	{
	    Robot.shooter.feederWheel.enable();
	    Robot.shooter.shooterWheel.enable();
	    if (Robot.shooter.feederWheel.getSpeed() == 0)
		Robot.shooter.feederWheel.set(0.75);
	    else
		Robot.shooter.driveFeederWheelAtRPM(Robot.shooter.getFeederWheelRPM());

	    if (Robot.shooter.shooterWheel.getSpeed() == 0)
		Robot.shooter.shooterWheel.set(0.75);
	    else
		Robot.shooter.driveShooterWheelAtRPM(Robot.shooter.getShooterWheelRPM());
	    Robot.shooter.report();
	} else
	{
	    Robot.shooter.feederWheel.disable();
	    Robot.shooter.shooterWheel.disable();
	}
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