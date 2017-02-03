package org.usfirst.frc.team1165.robot.subsystems;

import org.usfirst.frc.team1165.robot.Robot;
import org.usfirst.frc.team1165.robot.commands.ReportNavXValues;

//import org.usfirst.frc.team1165.robot.commands.ReportNavXValues;

import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.command.PIDSubsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class NavX_MXP_PID extends PIDSubsystem
{
    // proportional speed constant
    private static final double kP = 0.03;

    // integral speed constant
    private static final double kI = 0.00;

    // derivative speed constant
    private static final double kD = 0.00;

    public double rotateToAngleRate = 0;
    
    /* * This tuning parameter indicates how close to "on target" the PID
     * Controller will attempt to get.*/
     
    static final double kToleranceDegrees = 2.0f;

    // Initialize your subsystem here
    public NavX_MXP_PID()
    {
	super(kP, kI, kD);
	super.setInputRange(-180.0f, 180.0f);
	super.setOutputRange(-0.25, 0.25);
	super.setAbsoluteTolerance(kToleranceDegrees);
	super.getPIDController().setContinuous(true);
    }

    public void initDefaultCommand()
    {
	// Set the default command for a subsystem here.
    }

    public void setSetpoint(double setpoint)
    {
	super.setSetpoint(setpoint);
    }

    protected double returnPIDInput()
    {
	// Return your input value for the PID loop
	// e.g. a sensor, like a potentiometer:
	// yourPot.getAverageVoltage() / kYourMaxVoltage;
	return Robot.navXSource.pidInput();
    }

    protected void usePIDOutput(double output)
    {
	// Use output to drive your system, like a motor
	rotateToAngleRate = output;
	SmartDashboard.putNumber("PID Output", output);
	Robot.driveTrain.driveCartesian(Robot.oi.stick.getX(), Robot.oi.stick.getY(), Robot.navX.rotateToAngleRate,
		Robot.navXSource.getHeading());
    }
}