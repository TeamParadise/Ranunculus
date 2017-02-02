package org.usfirst.frc.team1165.robot.commands;

import org.usfirst.frc.team1165.robot.Robot;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class RotateToHeading extends Command
{

    public int buttonNumber;
    public static boolean rotateToAngle = false;
    
    public double targetHeading;
    public double currentHeading;

    public RotateToHeading(int buttonNumber)
    {
	// Use requires() here to declare subsystem dependencies
	requires(Robot.driveTrain);
	requires(Robot.navX);
	this.buttonNumber = buttonNumber;
	try
	{
	    SmartDashboard.putNumber("Finished Initialize", 0);
	    SmartDashboard.putNumber("Finished Initialize", 1);
	    if (buttonNumber == 3)
		targetHeading = -179.9f;
	    else if (buttonNumber == 4)
		Robot.navX.setSetpoint(179.9f);
	    else if (buttonNumber == 5)
		targetHeading = -90f;
	    else if (buttonNumber == 6)
		targetHeading = 90.0f;
	    SmartDashboard.putNumber("Finished Initialize", 2);
	    SmartDashboard.putNumber("Finished Initialize", 3);
	} catch (Exception e)
	{
	    DriverStation.reportError(e.getMessage(), false);
	}
	SmartDashboard.putNumber("Button Number", buttonNumber);
    }

    // Called just before this Command runs the first time
    protected void initialize()
    {
	Robot.navX.disable();
	Robot.navXSource.reset();
	Robot.navX.setSetpoint(targetHeading);
	Robot.navX.enable();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute()
    {
	currentHeading = Robot.navXSource.getHeading();
	SmartDashboard.putNumber("Heading", Robot.navXSource.getHeading());
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished()
    {
	return Robot.navX.onTarget();
    }

    // Called once after isFinished returns true
    protected void end()
    {
	Robot.navX.disable();
	Robot.driveTrain.driveCartesian(0, 0, 0, 0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted()
    {
	end();
    }
}