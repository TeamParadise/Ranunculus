package org.usfirst.frc.team1165.robot.subsystems;

import org.usfirst.frc.team1165.robot.RobotMap;
import org.usfirst.frc.team1165.robot.commands.MoveServo;
import org.usfirst.frc.team1165.robot.commands.SetServoUsingRPM;

import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class ServoSystem extends Subsystem
{

	Servo servo;
	Servo shooterServo;
	private boolean toggle = false;

	public ServoSystem()
	{
		servo = new Servo(RobotMap.rightServoPortNumber);
		shooterServo = new Servo(RobotMap.shooterServoPortNumber);
	}

	public void initDefaultCommand()
	{
		// Set the default command for a subsystem here.
		setDefaultCommand(new SetServoUsingRPM());
	}
	
	public double getCurrentAngle()
	{
		return shooterServo.getAngle();
	}

	public void setShooterServoPosition(double angle)
	{
		shooterServo.setAngle(angle);
	}
	
	public void setServoPosition(double angle)
	{
		servo.setAngle(angle);
	}

	public double findServoAngle()
	{
		return toggle ? 90 : -90;
	}

	public void toggleServo()
	{
		toggle = !toggle;
	}

	public void report()
	{
		SmartDashboard.putNumber("Servo Angle", servo.getAngle());
	}
}