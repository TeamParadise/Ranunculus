package org.usfirst.frc.team1165.robot.commands;

import org.usfirst.frc.team1165.robot.Robot;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class RotateToHeading extends Command
{

    public int buttonNumber;
    public static boolean rotateToAngle = false;

    public RotateToHeading(int buttonNumber)
    {
	// Use requires() here to declare subsystem dependencies
	requires(Robot.driveTrain);
	requires(Robot.navX);
	this.buttonNumber = buttonNumber;
    }

    // Called just before this Command runs the first time
    protected void initialize()
    {
	try
	{
	SmartDashboard.putNumber("Finished Initialize", 0);
	Robot.navX.reset();
	SmartDashboard.putNumber("Finished Initialize", 1);
	if (buttonNumber == 3)
	    Robot.navX.setSetpoint(-179.9);
	else if (buttonNumber == 4)
	    Robot.navX.setSetpoint(179.9);
	else if (buttonNumber == 5)
	    Robot.navX.setSetpoint(-90.0);
	else if (buttonNumber == 6)
	    Robot.navX.setSetpoint(90);
	SmartDashboard.putNumber("Finished Initialize", 2);
	Robot.navX.enable();
	SmartDashboard.putNumber("Finished Initialize", 3);
	}
	catch(Exception e)
	{
	    DriverStation.reportError(e.getMessage(), false);
	}
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute()
    {
	Robot.driveTrain.driveCartesian(Robot.oi.stick.getX(), Robot.oi.stick.getY(), Robot.navX.rotateToAngleRate,
		Robot.navX.getHeading());
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished()
    {
	return Robot.navX.onTarget();
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