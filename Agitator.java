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
		agitator = new CANTalon(RobotMap.arbitraryCANTalonNumber);
	}

	public void initDefaultCommand()
	{
		// Set the default command for a subsystem here.
		setDefaultCommand(new RunAgitator());
	}
	
	public void toggle()
	{
		toggle = !toggle;
	}
	
	public void run()
	{
		if(toggle)
			agitator.set(0.5);
		else
			agitator.set(0);
	}
}
