package org.usfirst.frc.team1165.robot.subsystems;

import org.usfirst.frc.team1165.robot.Robot;
import org.usfirst.frc.team1165.robot.subsystems.UltrasonicSensorSource.StrafeType;

import edu.wpi.first.wpilibj.command.PIDSubsystem;

/**
 *
 */
public class UltrasonicPID extends PIDSubsystem
{
    // proportional speed constant
    private static final double kP = 7.0;

    // integral speed constant
    private static final double kI = 0.018;

    // derivative speed constant
    private static final double kD = 1.5;

    // distance in inches the robot wants to stay from an object
    private static final double kHoldDistance = 4;

    // maximun distance in inches we expect the robot to see
    private static final double kMaxDistance = 15.0;

    // Minimum and maximum allowable set point positions:
    private final static double minPosition = 0;
    private final static double maxPosition = 15;

    // Minimum and maximum allowable speeds:
    private final static double minSpeed = -0.85;
    private final static double maxSpeed = 0.85;

    // Tolerance for reaching set point:
    private final static double tolerance = 0.16;

    private static int finished = 0;

    public StrafeType direction = StrafeType.NONE;

    // Initialize your subsystem here
    public UltrasonicPID()
    {
	super(kP, kI, kD);
	setInputRange(minPosition, maxPosition);
	setOutputRange(minSpeed, maxSpeed);
	setAbsoluteTolerance(0.5);
	// Use these to get going:
	// setSetpoint() - Sets where the PID controller should move the system
	// to
	// enable() - Enables the PID controller.
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
	return Robot.ultrasonicSensorSource.getUltrasonicReading(getUltrasonic());
    }
    
    public void setSetpoint()
    {
	super.setSetpoint(kHoldDistance);
    }

    public StrafeType getUltrasonic()
    {
	//Find which ultrasonic sensor to use
	if (Robot.ultrasonicSensorSource.ultrasonicLeft.getRangeInches() < kHoldDistance)
	{
	    if (Robot.ultrasonicSensorSource.ultrasonicRight.getRangeInches() < kHoldDistance)
	    {
		// Neither sees the boiler opening. You are ready to shoot.
		direction = StrafeType.NONE;
	    } else
	    {
		// Left does not see boiler opening but right does. Strafe
		// right.
		direction = StrafeType.RIGHT;
	    }
	} else
	{
	    if (Robot.ultrasonicSensorSource.ultrasonicRight.getRangeInches() < kHoldDistance)
	    {
		// Left sees the boiler opening but right does not. Strafe Left
		direction = StrafeType.LEFT;
	    } else
	    {
		// Both controllers see greater than kHoldDistance inches; you
		// are clearly not near the boiler
		// Get in position and try again.
		direction = StrafeType.NONE;
	    }
	}
	return direction;
    }

    protected void usePIDOutput(double output)
    {
	// Use output to drive your system, like a motor
	if(getUltrasonic() == StrafeType.LEFT)
	    Robot.driveTrain.driveCartesian(output, 0, 0, 0);
	else if(getUltrasonic() == StrafeType.RIGHT)
	    Robot.driveTrain.driveCartesian(-output, 0, 0, 0);
    }
}