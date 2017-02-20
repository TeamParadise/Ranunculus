package org.usfirst.frc.team1165.robot.subsystems;

import org.usfirst.frc.team1165.robot.RobotMap;

import com.ctre.CANTalon;

import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Climber extends Subsystem
{

	CANTalon climber;
	// Put methods for controlling this subsystem
	public Climber()
	{
		climber = new CANTalon(RobotMap.CANTalonClimber);
	}

	public void initDefaultCommand()
	{
		// Set the default command for a subsystem here.
		// setDefaultCommand(new MySpecialCommand());
	}
	
	public void climb(double power)
	{
		climber.set(power);
	}
}