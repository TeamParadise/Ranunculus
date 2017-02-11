package org.usfirst.frc.team1165.robot.subsystems;

import org.usfirst.frc.team1165.robot.Robot;

import edu.wpi.first.wpilibj.command.PIDSubsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class VisionPID extends PIDSubsystem
{
	// proportional speed constant
	private static final double kP = 0.03;

	// integral speed constant
	private static final double kI = 0.0;

	// derivative speed constant
	private static final double kD = 0.0;

	// Minimum and maximum allowable speeds:
	private final static double minSpeed = -0.5;
	private final static double maxSpeed = 0.5;

	private final static double tolerance = 5;

	private final int usbCameraImageWidth = 640;
	private final int usbCameraImageHeight = 480;

	// Initialize your subsystem here
	public VisionPID()
	{
		super(kP, kI, kD);
		setInputRange(0, usbCameraImageWidth);
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
		super.setSetpoint(usbCameraImageWidth / 2.0);
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
		Robot.driveTrain.driveCartesian(output, 0, 0, 0);
		SmartDashboard.putNumber("Vision PID Output", output);
	}
}