package org.usfirst.frc.team1165.robot.subsystems;

import org.usfirst.frc.team1165.robot.RobotMap;
import org.usfirst.frc.team1165.robot.commands.RunPickup;

import com.ctre.CANTalon;

import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Pickup extends Subsystem
{

	// Put methods for controlling this subsystem
	// here. Call these from Commands.
	
	CANTalon pickup;
	
	private boolean toggle = false;
	public Pickup()
	{
		pickup = new CANTalon(RobotMap.CANTalonPickup);
	}

	public void initDefaultCommand()
	{
		// Set the default command for a subsystem here.
		setDefaultCommand(new RunPickup());
	}
	
	public void set(double power)
	{
		pickup.set(power);
	}
	
	public void toggle()
	{
		toggle = !toggle;
	}
}