package org.usfirst.frc.team1165.robot.commands;

import org.usfirst.frc.team1165.robot.Robot;
import org.usfirst.frc.team1165.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Command;

public class MoveServo extends Command
{
    private double angle;
    public MoveServo(double angle)
    {
	this.angle = angle;
	requires(Robot.servo);
    }

    // Called just before this Command runs the first time
    protected void initialize()
    {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute()
    {
	Robot.servo.setServoPosition(Robot.servo.findServoAngle());
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished()
    {
	return true;
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