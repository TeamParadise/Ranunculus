package org.usfirst.frc.team1165.robot.subsystems;

import org.usfirst.frc.team1165.robot.RobotMap;
import org.usfirst.frc.team1165.robot.commands.MoveServo;
import org.usfirst.frc.team1165.robot.commands.SetServoUsingRPM;

import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class ServoSystem extends Subsystem
{

	Servo shooterServo;
	private boolean toggle = false;
	public int servoUp = 30;
	public int servoDown = 90;

	public ServoSystem()
	{
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
	
	public boolean isServoUp()
	{
		return shooterServo.getAngle() == servoUp;
	}

	public double findServoAngle()
	{
		return toggle ? servoUp : servoDown;
	}

	public void toggleServo()
	{
		toggle = !toggle;
	}

	public void report()
	{
		SmartDashboard.putNumber("Shooter Servo Angle", shooterServo.getAngle());
	}
}