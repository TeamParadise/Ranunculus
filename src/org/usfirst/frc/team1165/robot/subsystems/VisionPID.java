package org.usfirst.frc.team1165.robot.subsystems;

import org.usfirst.frc.team1165.robot.Robot;

import edu.wpi.first.wpilibj.command.PIDSubsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class VisionPID extends PIDSubsystem
{
	// proportional speed constant
	private static final double kP = 0.03;

	// integral speed constant
	private static final double kI = 0.0;

	// derivative speed constant
	private static final double kD = 0.0;

	// Minimum and maximum allowable speeds:
	private final static double minSpeed = -0.3;
	private final static double maxSpeed = 0.3;

	private final static double tolerance = 5;

	public double output;

	// Initialize your subsystem here
	public VisionPID()
	{
		super(kP, kI, kD);
		setInputRange(0, Robot.usbCameraImageWidth);
		setOutputRange(minSpeed, maxSpeed);
		setAbsoluteTolerance(tolerance);
	}

	public void initDefaultCommand()
	{
		// Set the default command for a subsystem here.
		// setDefaultCommand(new MySpecialCommand());
	}

	public void setSetpoint()
	{
		super.setSetpoint(Robot.usbCameraImageWidth / 2.0);
	}

	protected double returnPIDInput()
	{
		// Return your input value for the PID loop
		// e.g. a sensor, like a potentiometer:
		// yourPot.getAverageVoltage() / kYourMaxVoltage;
		return Robot.visionGRIP.average;
	}

	protected void usePIDOutput(double output)
	{
		// Use output to drive your system, like a motor
		//this.output = output;
		double midlow = 300;
		double midhi = 340;
		double nextlow = 250;
		double nexthi = 390;
		double lopower = 0.3;
		double hipower = 0.4;
		if (isBetweenInclusive(midlow,midhi,Robot.visionGRIP.average))
			this.output = 0;
		else if (isBetweenInclusive(nextlow,midlow,Robot.visionGRIP.average))
			this.output = -lopower;
		else if (isBetweenInclusive(midhi,nexthi,Robot.visionGRIP.average))
			this.output = lopower;
		else if (Robot.visionGRIP.average < nextlow)
			this.output = -hipower;
		else if (Robot.visionGRIP.average > nexthi)
			this.output = hipower;
		else this.output = 0;
		SmartDashboard.putNumber("Vision PID Output", output);
	}
	
	protected boolean isBetweenInclusive(double low, double high, double value)
	{
		return (value >= low && value <= high);
	}
}