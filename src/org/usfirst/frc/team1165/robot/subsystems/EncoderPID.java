package org.usfirst.frc.team1165.robot.subsystems;

import org.usfirst.frc.team1165.robot.Robot;

import edu.wpi.first.wpilibj.command.PIDSubsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class EncoderPID extends PIDSubsystem
{

	// proportional speed constant
	private static final double kP = 0.75;

	// integral speed constant
	private static final double kI = 0.00;

	// derivative speed constant
	private static final double kD = 4.00;
	
	public double forwardSpeed;
	
	//absolute Tolerance
	private static final double absoluteTolerance = 5;
	// Initialize your subsystem here
	public EncoderPID()
	{
		super(kP, kI, kD);
		setInputRange(-200, 200);
		resetOutputRange();
		setAbsoluteTolerance(absoluteTolerance);
	}

	public void resetOutputRange()
	{
		setOutputRange(-1,1);
	}
	
	public void initDefaultCommand()
	{
		// Set the default command for a subsystem here.
		// setDefaultCommand(new MySpecialCommand());
	}

	protected double returnPIDInput()
	{
		// Return your input value for the PID loop
		// e.g. a sensor, like a potentiometer:
		// yourPot.getAverageVoltage() / kYourMaxVoltage;
		return Robot.driveTrain.averageEncoderDistance();
	}

	protected void usePIDOutput(double output)
	{
		forwardSpeed = output;
		SmartDashboard.putNumber("Encoder PID Output", output);
		SmartDashboard.putNumber("Encoder Setpoint", this.getSetpoint());

	}
}