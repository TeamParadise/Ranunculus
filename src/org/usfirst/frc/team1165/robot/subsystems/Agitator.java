package org.usfirst.frc.team1165.robot.subsystems;
 
import org.usfirst.frc.team1165.robot.RobotMap;
import org.usfirst.frc.team1165.robot.commands.RunAgitator;

import com.ctre.CANTalon;

import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Agitator extends Subsystem
{

	CANTalon agitator;
	
	private boolean toggle = false;
	public Agitator()
	{
		agitator = new CANTalon(RobotMap.CANTalonAgitator);
	}

	public void initDefaultCommand()
	{
		// Set the default command for a subsystem here.
		setDefaultCommand(new RunAgitator(4000));
 	}
	
	public double getSpeed()
	{
		return agitator.get();
	}
 	
	public void set(double power)
	{
		agitator.set(power);
	}
	
 	public void toggle()
	{
		toggle = !toggle;
	}
}